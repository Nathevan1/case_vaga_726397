package com.itau.app.gateway.request;

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
public class BacenRequestGtw {

    private Long idTransacao;
    private BigDecimal valorTransacao;
    private LocalDate dataTransacao;
}
