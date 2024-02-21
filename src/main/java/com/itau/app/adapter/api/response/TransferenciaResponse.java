package com.itau.app.adapter.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Setter;

@Setter
@Schema(name = "TransferenciaResponse", description = "DTO para retornar as informações da transferência realizada pelo cliente proprietário da conta")
public class TransferenciaResponse {

    @Schema(name = "status", description = "Campo que informa o Status da Transferência realizada", example = "SUCESSO")
    public StatusTransferenciaResponse status;

    @Schema(name = "data", description = "Campo que informa a Data da Transferência realizada", example = "2024-02-20T15:45:30.123456")
    public String data;

    @Schema(name = "valor", description = "Campo que informa o Valor Monetário da Transferência realizada", example = "15.56")
    public String valor;
}
