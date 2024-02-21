package com.itau.app.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransferenciaDestinoDomain {

    private String codigoBanco;
    private String numeroAgencia;
    private String numeroConta;
    private TipoContaDomain tipoConta;
}
