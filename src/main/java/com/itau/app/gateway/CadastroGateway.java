package com.itau.app.gateway;

import com.itau.app.gateway.request.CadastroRequestGtw;
import com.itau.app.gateway.response.CadastroResponseGtw;

import java.io.IOException;

public interface CadastroGateway {
    CadastroResponseGtw execute(CadastroRequestGtw request) throws IOException;
}
