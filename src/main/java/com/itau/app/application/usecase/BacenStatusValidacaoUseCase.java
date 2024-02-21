package com.itau.app.application.usecase;

import com.itau.app.application.domain.StatusTransferenciaDomain;
import com.itau.app.application.domain.TransferenciaBacenDomain;
import com.itau.app.application.domain.TransferenciaDomain;
import com.itau.app.application.exception.BacenBadRequestException;
import com.itau.app.application.exception.BacenRateLimitException;
import com.itau.app.gateway.BacenGateway;
import com.itau.app.gateway.request.BacenRequestGtw;
import com.itau.app.repository.model.MockBacenListModel;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class BacenStatusValidacaoUseCase {

    private static final Logger logger = LoggerFactory.getLogger(BacenStatusValidacaoUseCase.class);

    @Autowired
    private final BacenGateway gateway;

    public TransferenciaBacenDomain execute(TransferenciaDomain domain) throws IOException {

        MockBacenListModel bacen = gateway.execute(new BacenRequestGtw(
                domain.getIdTransacao(),
                domain.getValorTransferencia(),
                domain.getDataTransferencia()
        ));

        if(bacen.getCodigo().equals("500")){
            throw new BacenBadRequestException("Ocorreu uma falha ao Consultar API do Bacen. Tente novamente mais tarde!");
        }

        if(bacen.getCodigo().equals("429")){
            throw new BacenRateLimitException("Ocorreu uma falha de Rate Limit ao Consultar API do Bacen. Tente novamente mais tarde!");
        }

        logger.info("API Bacen - Sucesso");

        return new TransferenciaBacenDomain(
                StatusTransferenciaDomain.valueOf(bacen.getStatus()),
                domain.getDataTransferencia(),
                domain.getValorTransferencia()
        );
    }
}
