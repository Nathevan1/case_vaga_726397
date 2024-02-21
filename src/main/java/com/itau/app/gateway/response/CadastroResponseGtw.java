package com.itau.app.gateway.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class CadastroResponseGtw {

    private String nomeCliente;
    private String numeroConta;
    private String numeroAgencia;
    private TipoContaResponseGtw tipoConta;
    private BigDecimal saldoConta;
    private StatusContaResponseGtw statusConta;

}
