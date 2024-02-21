package com.itau.app.adapter.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Schema(name = "TransferenciaRequest", description = "DTO de Requisição da Conta de Destino, para realizar uma transferência de valor entre contas")
public class TransferenciaRequest {

    @Schema(name = "nomeCliente", description = "Campo para receber o Nome Completo do Cliente titular da Conta", example = "João da Silva")
    @NotBlank(message = "O Nome do Cliente não pode ser vazio ou nulo")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Somente Letras e Espaços são permitidos para o Nome do Cliente")
    private String nomeCliente;

    @Schema(name = "numeroAgencia", description = "Campo para receber o Número da Agência do Cliente titular da Conta", example = "1025")
    @NotBlank(message = "O Número da Agência não pode ser nulo ou vazio")
    @Pattern(regexp = "\\d+", message = "O Número da Agência deve conter apenas números")
    @Size(min = 3, max = 10, message = "O Número da Agência deve ter no mínimo 3 e no máximo 10 números")
    private String numeroAgencia;

    @Schema(name = "numeroConta", description = "Campo para receber o Número da Conta do Cliente titular da Conta", example = "22333")
    @NotBlank(message = "O Número da Conta não pode ser nulo ou vazio")
    @Pattern(regexp = "\\d+", message = "O Número da Conta deve conter apenas números")
    @Size(min = 3, max = 10, message = "O Número da Conta deve ter no mínimo 3 e no máximo 10 números")
    private String numeroConta;

    @Schema(name = "tipoConta", description = "Campo para receber o Tipo de Conta do Cliente titular da Conta", example = "CORRENTE")
    @NotBlank(message = "O Tipo da Conta não pode ser nulo ou vazio")
    private TipoContaRequest tipoConta;

    @Schema(name = "valorTransferencia", description = "Campo para receber o Valor da Transferência realizada pelo Cliente titular da Conta", example = "952.56")
    @NotBlank(message = "O código do Banco não pode ser nulo ou vazio")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "O Valor da Transferência aceita apenas valor monetário, usando '.' [ponto], para sinalizar a casa decimal")
    @Size(min = 3, max = 7, message = "Quantidade de caracteres inválida, para o Valor da Transferência")
    private String valorTransferencia;

    @Schema(name = "transferenciaDestino", description = "Objeto para receber todos os dados da Conta de Destino da transferência realizada pelo Cliente Titular da Conta")
    private TransferenciaDestinoRequest transferenciaDestino;
}
