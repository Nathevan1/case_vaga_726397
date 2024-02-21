package com.itau.app.repository.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MockDataListModel {

    @JsonProperty("nomeCliente")
    private String nomeCliente;

    @JsonProperty("numeroAgencia")
    private String numeroAgencia;

    @JsonProperty("numeroConta")
    private String numeroConta;

    @JsonProperty("tipoConta")
    private String tipoConta;

    @JsonProperty("saldoConta")
    private String saldoConta;

    @JsonProperty("statusConta")
    private String statusConta;
}
