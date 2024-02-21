package com.itau.app.adapter.controller;

import com.itau.app.adapter.api.request.ConsultaSaldoRequest;
import com.itau.app.adapter.api.request.TipoContaRequest;
import com.itau.app.adapter.api.response.ConsultaSaldoResponse;
import com.itau.app.adapter.api.response.StatusContaResponse;
import com.itau.app.application.domain.ConsultaSaldoDomain;
import com.itau.app.application.service.ConsultaSaldoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@Tag(name = "ConsultaSaldoController, Saldo, Cliente", description = "Controller para Consulta de Saldo da Conta do Cliente")
@RestController
@RequestMapping("/bancoitau")
public class ConsultaSaldoController {

    private final ConsultaSaldoService service;

    @Autowired
    public ConsultaSaldoController(ConsultaSaldoService service) {
        this.service = service;
    }

    @GetMapping(value = "/saldocliente")
    @Operation(summary = "Retorna o Saldo do Cliente, baseado no filtro feito pelo Nome do Cliente")
    public ResponseEntity<ConsultaSaldoResponse> execute(
            @RequestBody @Valid ConsultaSaldoRequest request
    ) throws IOException {

        ConsultaSaldoDomain domain = new ConsultaSaldoDomain(
                request.getNomeCliente()
        );

        var action = service.execute(domain);

        ConsultaSaldoResponse response = new ConsultaSaldoResponse();

        response.setNomeCliente(action.getNomeCliente());
        response.setNumeroConta(action.getNumeroConta());
        response.setNumeroAgencia(action.getNumeroAgencia());
        response.setTipoConta(TipoContaRequest.valueOf(action.getTipoConta().name()));
        response.setSaldoConta(action.getSaldoConta().toString());
        response.setStatusConta(StatusContaResponse.valueOf(action.getStatusConta().name()));

        return ResponseEntity.ok().body(response);
    }
}
