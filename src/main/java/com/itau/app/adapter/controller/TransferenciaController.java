package com.itau.app.adapter.controller;

import com.itau.app.adapter.api.request.TransferenciaRequest;
import com.itau.app.adapter.api.response.StatusTransferenciaResponse;
import com.itau.app.adapter.api.response.TransferenciaResponse;
import com.itau.app.application.domain.TipoContaDomain;
import com.itau.app.application.domain.TransferenciaDestinoDomain;
import com.itau.app.application.domain.TransferenciaDomain;
import com.itau.app.application.service.TransferenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@Tag(name = "TransferenciaController, Transferencia, Cliente", description = "Controller para Transferência de Valor entre Contas")
@RestController
@RequestMapping("/bancoitau")
@AllArgsConstructor
public class TransferenciaController {

    @Autowired
    private final TransferenciaService service;

    @PostMapping(value = "/transferencia")
    @Operation(summary = "Retorna Dados da Transferência realizada e Status da mesma")
    public ResponseEntity<TransferenciaResponse> execute(
            @RequestBody @Valid TransferenciaRequest request
    ) throws IOException {

        TransferenciaDomain domain = new TransferenciaDomain(
                request.getNomeCliente(),
                request.getNumeroAgencia(),
                request.getNumeroConta(),
                TipoContaDomain.valueOf(request.getTipoConta().name()),
                new BigDecimal(request.getValorTransferencia()),
                new TransferenciaDestinoDomain(
                    request.getTransferenciaDestino().getCodigoBanco(),
                    request.getTransferenciaDestino().getNumeroAgencia(),
                    request.getTransferenciaDestino().getNumeroConta(),
                    TipoContaDomain.valueOf(request.getTransferenciaDestino().getTipoConta().name())
                ),
                LocalDate.now()
        );

        var action = service.execute(domain);

        TransferenciaResponse response = new TransferenciaResponse();
        response.setData(action.getData().toString());
        response.setStatus(StatusTransferenciaResponse.valueOf(action.getStatus().name()));
        response.setValor(action.getValor().toString());

        return ResponseEntity.ok().body(response);

    }
}
