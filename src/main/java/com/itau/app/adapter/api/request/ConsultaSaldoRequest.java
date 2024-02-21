package com.itau.app.adapter.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Schema(name = "ConsultaSaldoRequest", description = "DTO de Requisição para consulta do Saldo do Cliente")
public class ConsultaSaldoRequest {

    @Schema(name = "nomeCliente", description = "Campo para receber o Nome Completo do Cliente", example = "João da Silva")
    @NotBlank(message = "O Nome do Cliente não pode ser vazio ou nulo")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Somente Letras e espaços são permitidos para o Nome do Cliente")
    private String nomeCliente;
}
