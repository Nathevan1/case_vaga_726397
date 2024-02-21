package com.itau.app.application.usecase;

import com.itau.app.application.domain.SaldoDomain;
import com.itau.app.application.domain.StatusContaDomain;
import com.itau.app.application.domain.TransferenciaDomain;
import com.itau.app.application.exception.ValidacaoTransferenciaException;
import com.itau.app.repository.entity.Transferencia;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ValidacaoTransferenciaUseCase {

    private static final Logger logger = LoggerFactory.getLogger(ValidacaoTransferenciaUseCase.class);

    public TransferenciaDomain execute(final TransferenciaDomain transferencia, final SaldoDomain saldo){

        if(!(saldo.getStatusConta().name().equals(StatusContaDomain.ATIVA.name())))
            throw new ValidacaoTransferenciaException("O Status da Conta deve ser ativo, para realizar alguma transação");

        if((transferencia.getValorTransferencia().compareTo(saldo.getSaldoConta())) > 0) {
            throw new ValidacaoTransferenciaException("Não há Saldo suficiente para realizar essa transação");
        }

        if(!Objects.equals(saldo.getNumeroConta(), transferencia.getNumeroConta()))
            throw new ValidacaoTransferenciaException("O Número da Conta não confere");

        if(!Objects.equals(saldo.getNumeroAgencia(), transferencia.getNumeroAgencia()))
            throw new ValidacaoTransferenciaException("O Número da Agência não confere");

        return transferencia;

    }

    public void execute(List<Transferencia> transferenciaList, BigDecimal valorTransferencia) {

        if(!transferenciaList.isEmpty()){

            BigDecimal transacaoDia = BigDecimal.valueOf(0.0);
            for(Transferencia item: transferenciaList){
                transacaoDia = transacaoDia.add(item.getValorTransferencia()).add(valorTransferencia);
            }

            if(transacaoDia.compareTo(new BigDecimal("1000.00")) > 0)
                throw new ValidacaoTransferenciaException("Limite diário de transferência é R$1000.00.");

        } else {
            logger.info("Cliente não possui transações de transferência registradas");
        }
    }
}
