package com.itau.app.application.usecase;

import com.itau.app.application.domain.ConsultaSaldoDomain;
import com.itau.app.application.domain.SaldoDomain;
import com.itau.app.application.domain.StatusContaDomain;
import com.itau.app.application.domain.TipoContaDomain;
import com.itau.app.application.exception.ConsultaSaldoUseCaseException;
import com.itau.app.application.service.ConsultaSaldoService;
import com.itau.app.repository.SaldoRepository;
import com.itau.app.repository.entity.Saldo;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ConsultaSaldoUseCase implements ConsultaSaldoService {

    private static final Logger logger = LoggerFactory.getLogger(ConsultaSaldoUseCase.class);

    @Autowired
    private final SaldoRepository saldoRepository;

    @Override
    public SaldoDomain execute(ConsultaSaldoDomain consultaSaldoDomain) throws IOException {

        try{
            Saldo cliente = saldoRepository.findByNomeCliente(consultaSaldoDomain.getNomeCliente())
                    .stream()
                    .findFirst()
                    .get();

            return new SaldoDomain(
                    cliente.getId(),
                    cliente.getNomeCliente(),
                    cliente.getNumeroConta(),
                    cliente.getNumeroAgencia(),
                    TipoContaDomain.valueOf(cliente.getTipoConta()),
                    cliente.getSaldoConta(),
                    StatusContaDomain.valueOf(cliente.getStatusConta())
            );

        } catch (Exception e) {
            logger.error("Erro - ConsultaSaldoUseCase.java ", e);
            throw new ConsultaSaldoUseCaseException("Ocorreu um erro ao tentar Consultar o Saldo do Cliente. Tente Novamente mais tarde!");
        }
    }
}
