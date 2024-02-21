package com.itau.app.application.usecase;

import com.itau.app.application.domain.*;
import com.itau.app.application.exception.ValidacaoTransferenciaException;
import com.itau.app.repository.entity.Transferencia;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.misusing.InvalidUseOfMatchersException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ValidacaoTransferenciaUseCaseTest {

    @Autowired
    private final ValidacaoTransferenciaUseCase validacaoTransferenciaUseCase = Mockito.mock(ValidacaoTransferenciaUseCase.class);

    @Test
    @DisplayName("Passa em todas as validações e retorna o objeto domain de Transferência")
    void testExecute() {
        TransferenciaDomain transferenciaDomain = new TransferenciaDomain();

        transferenciaDomain.setIdTransacao(1L);
        transferenciaDomain.setNomeCliente("Joao Rodrigues");
        transferenciaDomain.setNumeroAgencia("004");
        transferenciaDomain.setNumeroConta("0004");
        transferenciaDomain.setTipoConta(TipoContaDomain.INVESTIMENTO);
        transferenciaDomain.setValorTransferencia(new BigDecimal("500.00"));
        transferenciaDomain.setDataTransferencia(LocalDate.now());
        new TransferenciaDestinoDomain(
                "123",
                "111",
                "2222",
                TipoContaDomain.CORRENTE
        );


        SaldoDomain saldoDomain = new SaldoDomain(
                "Joao Rodrigues",
                "0004",
                "004",
                TipoContaDomain.INVESTIMENTO,
                new BigDecimal("500.00"),
                StatusContaDomain.ATIVA
        );

        TransferenciaDomain result = validacaoTransferenciaUseCase.execute(
                transferenciaDomain, saldoDomain
        );

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getNomeCliente(), transferenciaDomain.getNomeCliente());
        Assertions.assertEquals(result.getNomeCliente(), saldoDomain.getNomeCliente());
    }

    @Test
    void teste2(){
        Transferencia transferencia1 = new Transferencia();
        transferencia1.setId(123L);
        transferencia1.setNomeCliente("Novo Teste Dantesco");
        transferencia1.setValorTransferencia(new BigDecimal("999"));
        transferencia1.setData(LocalDate.now());
        transferencia1.setCodigoBancoDestino("001");
        transferencia1.setNumeroAgenciaDestino("0011");
        transferencia1.setNumeroContaDestino("11111");
        transferencia1.setTipoContaDestino("CORRENTE");

        List<Transferencia> list = new ArrayList<>();
        list.add(transferencia1);

        validacaoTransferenciaUseCase.execute(list, new BigDecimal("0.1"));
    }

    @Test
    void teste2falha(){
        Transferencia transferencia1 = new Transferencia();
        transferencia1.setId(11L);
        transferencia1.setNomeCliente("Pessoa Rica Um");
        transferencia1.setValorTransferencia(new BigDecimal("1000.00"));
        transferencia1.setData(LocalDate.now());
        transferencia1.setCodigoBancoDestino("001");
        transferencia1.setNumeroAgenciaDestino("0011");
        transferencia1.setNumeroContaDestino("11111");
        transferencia1.setTipoContaDestino("CORRENTE");

        Transferencia transferencia2 = new Transferencia();
        transferencia1.setId(321L);
        transferencia1.setNomeCliente("Pessoa Rica Dois");
        transferencia1.setValorTransferencia(new BigDecimal("1000.00"));
        transferencia1.setData(LocalDate.now());
        transferencia1.setCodigoBancoDestino("002");
        transferencia1.setNumeroAgenciaDestino("0022");
        transferencia1.setNumeroContaDestino("22222");
        transferencia1.setTipoContaDestino("SALARIO");

        List<Transferencia> list = new ArrayList<>();
        list.add(transferencia1);
        list.add(transferencia2);

        Assertions.assertThrows(NullPointerException.class, () -> {
            validacaoTransferenciaUseCase.execute(list, new BigDecimal("10.00"));
        });
    }

}
