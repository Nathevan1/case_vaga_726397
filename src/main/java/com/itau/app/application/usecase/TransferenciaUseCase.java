package com.itau.app.application.usecase;

import com.itau.app.application.domain.ConsultaSaldoDomain;
import com.itau.app.application.domain.SaldoDomain;
import com.itau.app.application.domain.TransferenciaBacenDomain;
import com.itau.app.application.domain.TransferenciaDomain;
import com.itau.app.application.exception.ConsultaSaldoUseCaseException;
import com.itau.app.application.exception.ValidacaoTransferenciaException;
import com.itau.app.application.service.TransferenciaService;
import com.itau.app.repository.TransferenciaRepository;
import com.itau.app.repository.entity.Transferencia;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TransferenciaUseCase  implements TransferenciaService {

    private static final Logger logger = LoggerFactory.getLogger(TransferenciaUseCase.class);

    @Autowired
    private final ValidacaoTransferenciaUseCase validacaoTransferenciaUseCase;

    @Autowired
    private final ConsultaSaldoUseCase consultaSaldoUseCase;

    @Autowired
    private final TransferenciaRepository repository;

    @Autowired
    private final BacenCircuitBrakerUseCase bacenCircuitBrakerUseCase;

    @Override
    public TransferenciaBacenDomain execute(TransferenciaDomain transferenciaDomain) throws IOException {

        try {
            SaldoDomain saldo = consultaSaldoUseCase.execute(new ConsultaSaldoDomain(transferenciaDomain.getNomeCliente()));
            validacaoTransferenciaUseCase.execute(transferenciaDomain, saldo);

            List<Transferencia> transacaoHoje = repository.findByNomeClienteAndData(transferenciaDomain.getNomeCliente(), LocalDate.now());
            validacaoTransferenciaUseCase.execute(transacaoHoje, transferenciaDomain.getValorTransferencia());

            repository.save(mapper(transferenciaDomain));

            return bacenCircuitBrakerUseCase.execute(transferenciaDomain);

        } catch (Exception e) {
            if(e instanceof ValidacaoTransferenciaException) {
                logger.error("Erro - TransferenciaUseCase.java ", e);
                throw new ConsultaSaldoUseCaseException(e.getMessage());
            } else {
                logger.error("Erro - TransferenciaUseCase.java ", e);
                throw new ConsultaSaldoUseCaseException("Ocorreu um erro ao tentar fazer uma Transferência para outra conta. Tente Novamente mais tarde!");
            }
        }

    }

    private Transferencia mapper(TransferenciaDomain domain){
        Transferencia transferencia = new Transferencia();
        transferencia.setNomeCliente(domain.getNomeCliente());
        transferencia.setValorTransferencia(domain.getValorTransferencia());
        transferencia.setData(domain.getDataTransferencia());
        transferencia.setCodigoBancoDestino(domain.getTransferenciaDestino().getCodigoBanco());
        transferencia.setNumeroAgenciaDestino(domain.getTransferenciaDestino().getNumeroAgencia());
        transferencia.setNumeroContaDestino(domain.getTransferenciaDestino().getNumeroConta());
        transferencia.setTipoContaDestino(domain.getTransferenciaDestino().getTipoConta().name());

        return transferencia;
    }
}
