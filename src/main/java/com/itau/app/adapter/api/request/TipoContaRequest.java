package com.itau.app.adapter.api.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TipoContaRequest", description = "ENUM para classificar os tipos de Conta utilizados pelo cliente")
public enum TipoContaRequest {
    CORRENTE, POUPANCA, SALARIO, INVESTIMENTO
}
