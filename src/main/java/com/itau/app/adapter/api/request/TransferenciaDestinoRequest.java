package com.itau.app.adapter.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Schema(name = "TransferenciaDestinoRequest", description = "DTO de Requisição da Conta de Destino, para realizar uma transferência de valor entre contas")
public class TransferenciaDestinoRequest {

    @Schema(name = "codigoBanco", description = "Campo para receber o Código de Registro do Banco destino da transferência realizada pelo Cliente", example = "341")
    @NotBlank(message = "O Código do Banco de Destino não pode ser nulo ou vazio")
    @Pattern(regexp = "\\d+", message = "O Código do Banco de Destino deve conter apenas números")
    @Size(min = 3, max = 10, message = "O Código do Banco de Destino deve ter no mínimo 3 e no máximo 10 números")
    private String codigoBanco;

    @Schema(name = "numeroAgencia", description = "Campo para receber o Numero da Agencia, sem caracteres especiais, do Banco destino da transferência realizada pelo Cliente", example = "22333")
    @NotBlank(message = "O Número da Agência de Destino não pode ser nulo ou vazio")
    @Pattern(regexp = "\\d+", message = "O Número da Agência de Destino deve conter apenas números")
    @Size(min = 3, max = 10, message = "O Número da Agência de Destino deve ter no mínimo 3 e no máximo 10 números")
    private String numeroAgencia;

    @Schema(name = "numeroConta", description = "Campo para receber o Número da Conta, sem caracteres especiais, do Banco destino da transferência realizada pelo Cliente", example = "12548")
    @NotBlank(message = "O Número da Conta de Destino não pode ser nulo ou vazio")
    @Pattern(regexp = "\\d+", message = "O Número da Conta de Destino deve conter apenas números")
    @Size(min = 3, max = 10, message = "O Número da Conta de Destino deve ter no mínimo 3 e no máximo 10 números")
    private String numeroConta;

    @Schema(name = "tipoConta", description = "Campo para receber o Tipo de Conta do Banco destino da transferência realizada pelo Cliente", example = "CORRENTE")
    @NotBlank(message = "O Tipo da Conta de Destino não pode ser nulo ou vazio")
    private TipoContaRequest tipoConta;
}
