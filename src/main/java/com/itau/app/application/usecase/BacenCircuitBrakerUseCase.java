package com.itau.app.application.usecase;

import com.itau.app.application.domain.StatusTransferenciaDomain;
import com.itau.app.application.domain.TransferenciaBacenDomain;
import com.itau.app.application.domain.TransferenciaDomain;
import com.itau.app.application.exception.BacenCircuitBrakerUseCaseException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class BacenCircuitBrakerUseCase {

    private static final Logger logger = LoggerFactory.getLogger(BacenCircuitBrakerUseCase.class);

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    private BacenStatusValidacaoUseCase bacenStatusValidacaoUseCase;

    public TransferenciaBacenDomain execute(TransferenciaDomain domain) {

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        return circuitBreaker.run(() -> {
            try {
                logger.info("Inicio Circuit Breaker");
                return bacenStatusValidacaoUseCase.execute(domain);
            } catch (IOException e) {
                logger.error("Ocorreu uma falha na tentativa de Registrar a transação no Bacen", e);
                throw new BacenCircuitBrakerUseCaseException("Ocorreu uma falha na tentativa de Registrar a transação no Bacen");
            }
        }, throwable -> execute());
    }

    private TransferenciaBacenDomain execute(){
        return new TransferenciaBacenDomain(
                StatusTransferenciaDomain.PROCESSAMENTO,
                LocalDate.now(),
                new BigDecimal("0")
        );
    }
}
