package com.itau.app.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCliente;
    private BigDecimal valorTransferencia;
    private LocalDate data;
    private String codigoBancoDestino;
    private String numeroAgenciaDestino;
    private String numeroContaDestino;
    private String tipoContaDestino;
}
