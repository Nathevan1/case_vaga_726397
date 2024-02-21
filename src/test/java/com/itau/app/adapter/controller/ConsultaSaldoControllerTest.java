package com.itau.app.adapter.controller;

import com.itau.app.adapter.api.request.ConsultaSaldoRequest;
import com.itau.app.adapter.api.response.ConsultaSaldoResponse;
import com.itau.app.application.domain.ConsultaSaldoDomain;
import com.itau.app.application.domain.SaldoDomain;
import com.itau.app.application.domain.StatusContaDomain;
import com.itau.app.application.domain.TipoContaDomain;
import com.itau.app.application.exception.ConsultaSaldoUseCaseException;
import com.itau.app.application.service.ConsultaSaldoService;
import com.itau.app.application.usecase.ConsultaSaldoUseCase;
import com.itau.app.repository.SaldoRepository;
import com.itau.app.repository.entity.Saldo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ConsultaSaldoControllerTest {

    @Autowired
    private final ConsultaSaldoController controller = Mockito.mock(ConsultaSaldoController.class);

    private final SaldoRepository saldoRepository = Mockito.mock(SaldoRepository.class);

    @Autowired
    private final ConsultaSaldoService useCase = Mockito.mock(ConsultaSaldoService.class);


    @Test
    @DisplayName("Testa rota de Controller para Consulta de Saldo do Cliente")
    void testeController() {

        Mockito.when(saldoRepository.findByNomeCliente("Teste da Silva"))
                .thenReturn(getListSaldo());

        ConsultaSaldoRequest request = new ConsultaSaldoRequest();
        request.setNomeCliente("Teste da Silva");

        Assertions.assertThrows(ConsultaSaldoUseCaseException.class, () -> {
            controller.execute(request);
        });
    }

    @Test
    @DisplayName("Testa rota de Controller para Consulta de Saldo do Cliente, com retorno de exception")
    void testeControllerException() throws IOException {
        ConsultaSaldoRequest request = new ConsultaSaldoRequest();
        request.setNomeCliente("Teste da Silva");

        Assertions.assertThrows(ConsultaSaldoUseCaseException.class, () -> {
            controller.execute(request);
        });
    }

    private List<Saldo> getListSaldo() {

        List<Saldo> list = new ArrayList<>();
        Saldo saldo = new Saldo();

        saldo.setId(11L);
        saldo.setNomeCliente("Teste da Silva");
        saldo.setNumeroConta("11111");
        saldo.setNumeroAgencia("1111");
        saldo.setTipoConta("POUPANCA");
        saldo.setStatusConta("ATIVA");
        saldo.setSaldoConta(new BigDecimal("1150.00"));

        list.add(saldo);

        return list;
    }

    private SaldoDomain getSaldoDomain() {
        return new SaldoDomain(
                Long.getLong("11L"),
                "Teste da Silva",
                "11111",
                "1111",
                TipoContaDomain.POUPANCA,
                new BigDecimal("1150.00"),
                StatusContaDomain.ATIVA
        );
    }
}
