package com.itau.app.application.usecase;

import com.itau.app.application.domain.*;
import com.itau.app.repository.TransferenciaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.mock;

@SpringBootTest
class TransferenciaUseCaseTest {

    @Mock
    private final TransferenciaUseCase transferenciaUseCase = mock(TransferenciaUseCase.class);

    @Autowired
    private final ValidacaoTransferenciaUseCase validacaoTransferenciaUseCase = mock(ValidacaoTransferenciaUseCase.class);

    @Autowired
    private final ConsultaSaldoUseCase consultaSaldoUseCase = mock(ConsultaSaldoUseCase.class);

    @Autowired
    private final TransferenciaRepository repository = mock(TransferenciaRepository.class);

    @Autowired
    private final BacenCircuitBrakerUseCase bacenCircuitBrakerUseCase = mock(BacenCircuitBrakerUseCase.class);

    @Test
    void testUseCase() throws IOException {
        var result = transferenciaUseCase.execute(getTransferenciaDomain());
        Assertions.assertNotNull(result);
    }

    private TransferenciaDomain getTransferenciaDomain() {

        TransferenciaDomain transferenciaDomain = new TransferenciaDomain();

        transferenciaDomain.setIdTransacao(1L);
        transferenciaDomain.setNomeCliente("Joao Rodrigues");
        transferenciaDomain.setNumeroAgencia("004");
        transferenciaDomain.setNumeroConta("0004");
        transferenciaDomain.setTipoConta(TipoContaDomain.INVESTIMENTO);
        transferenciaDomain.setValorTransferencia(new BigDecimal("500.00"));
        transferenciaDomain.setDataTransferencia(LocalDate.now());
        TransferenciaDestinoDomain transferenciaDestinoDomain = new TransferenciaDestinoDomain(
                "123",
                "111",
                "2222",
                TipoContaDomain.CORRENTE
        );
        transferenciaDomain.setTransferenciaDestino(transferenciaDestinoDomain);

        return transferenciaDomain;
    }


}
