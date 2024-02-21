package com.itau.app.adapter.api.response;

import com.itau.app.adapter.api.request.TipoContaRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Setter;

@Setter
@Schema(name = "ConsultaSaldoResponse", description = "DTO de retorno da Consulta de Saldo do Cliente")
public class ConsultaSaldoResponse {

    @Schema(name = "nomeCliente", description = "Campo que retorna o Nome Completo do Cliente titular da conta", example = "João da Silva")
    public String nomeCliente;

    @Schema(name = "numeroAgencia", description = "Campo que retorna o Número da Agência do Cliente titular da conta", example = "1020")
    public String numeroAgencia;

    @Schema(name = "numeroConta", description = "Campo que retorna o Número da Conta do Cliente titular da conta", example = "22333")
    public String numeroConta;

    @Schema(name = "tipoConta", description = "Campo que retorna o Tipo de Conta do Cliente titular da conta", example = "POUPANCA")
    public TipoContaRequest tipoConta;

    @Schema(name = "saldoConta", description = "Campo que retorna o Saldo da Conta do Cliente titular da conta", example = "10000.00")
    public String saldoConta;

    @Schema(name = "statusConta", description = "Campo que retorna o Status da Conta do Cliente titular da conta", example = "ATIVA")
    public StatusContaResponse statusConta;
}
