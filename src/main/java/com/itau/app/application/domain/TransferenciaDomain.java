package com.itau.app.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaDomain {

    private Long idTransacao;
    private String nomeCliente;
    private String numeroAgencia;
    private String numeroConta;
    private TipoContaDomain tipoConta;
    private BigDecimal valorTransferencia;
    private TransferenciaDestinoDomain transferenciaDestino;
    private LocalDate dataTransferencia;

    public TransferenciaDomain(String nomeCliente, String numeroAgencia, String numeroConta, TipoContaDomain tipoContaDomain, BigDecimal valorTransferencia, TransferenciaDestinoDomain transferenciaDestinoDomain, LocalDate dataTransferencia) {
        this.setNomeCliente(nomeCliente);
        this.setNumeroAgencia(numeroAgencia);
        this.setNumeroConta(numeroConta);
        this.setTipoConta(tipoContaDomain);
        this.setValorTransferencia(valorTransferencia);
        this.setTransferenciaDestino(transferenciaDestinoDomain);
        this.setDataTransferencia(dataTransferencia);
    }
}
