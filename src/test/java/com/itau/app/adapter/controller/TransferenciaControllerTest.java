package com.itau.app.adapter.controller;

import com.itau.app.adapter.api.request.TipoContaRequest;
import com.itau.app.adapter.api.request.TransferenciaDestinoRequest;
import com.itau.app.adapter.api.request.TransferenciaRequest;
import com.itau.app.application.domain.TransferenciaDestinoDomain;
import com.itau.app.application.exception.ConsultaSaldoUseCaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;

@SpringBootTest
class TransferenciaControllerTest {

    @Autowired
    private final TransferenciaController controller = Mockito.mock(TransferenciaController.class);

    @Test
    void testeController() throws IOException {

        Assertions.assertThrows(ConsultaSaldoUseCaseException.class, () -> {
            controller.execute(getTransferenciaRequest());
        });
    }

    private TransferenciaRequest getTransferenciaRequest() {

        TransferenciaDestinoRequest transferenciaDestinoRequest = new TransferenciaDestinoRequest();
        transferenciaDestinoRequest.setCodigoBanco("011");
        transferenciaDestinoRequest.setNumeroAgencia("0111");
        transferenciaDestinoRequest.setNumeroConta("1234");
        transferenciaDestinoRequest.setTipoConta(TipoContaRequest.CORRENTE);

        TransferenciaRequest request = new TransferenciaRequest();
        request.setNomeCliente("Nome da Silva");
        request.setNumeroAgencia("0001");
        request.setNumeroConta("001");
        request.setTipoConta(TipoContaRequest.CORRENTE);
        request.setValorTransferencia("115.00");
        request.setTransferenciaDestino(transferenciaDestinoRequest);

        return request;
    }
}
