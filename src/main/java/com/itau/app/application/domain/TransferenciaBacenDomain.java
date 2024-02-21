package com.itau.app.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TransferenciaBacenDomain {

    private StatusTransferenciaDomain status;
    private LocalDate data;
    private BigDecimal valor;
}
