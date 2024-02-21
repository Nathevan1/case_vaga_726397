package com.itau.app.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaldoDomain {

    private Long idCliente;
    private String nomeCliente;
    private String numeroConta;
    private String numeroAgencia;
    private TipoContaDomain tipoConta;
    private BigDecimal saldoConta;
    private StatusContaDomain statusConta;

    public SaldoDomain(String nomeCliente, String numeroConta, String numeroAgencia, TipoContaDomain tipoContaDomain, BigDecimal saldoConta, StatusContaDomain statusContaDomain) {
        this.setNomeCliente(nomeCliente);
        this.setNumeroConta(numeroConta);
        this.setNumeroAgencia(numeroAgencia);
        this.setTipoConta(tipoContaDomain);
        this.setSaldoConta(saldoConta);
        this.setStatusConta(statusContaDomain);
    }
}
