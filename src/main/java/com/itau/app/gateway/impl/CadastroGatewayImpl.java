package com.itau.app.gateway.impl;

import com.itau.app.gateway.CadastroGateway;
import com.itau.app.gateway.request.CadastroRequestGtw;
import com.itau.app.gateway.response.CadastroResponseGtw;
import com.itau.app.gateway.response.StatusContaResponseGtw;
import com.itau.app.gateway.response.TipoContaResponseGtw;
import com.itau.app.repository.CadastroMockRepository;
import com.itau.app.repository.model.MockDataListModel;
import com.itau.app.repository.model.MockDataModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class CadastroGatewayImpl implements CadastroGateway {

    @Autowired
    private final CadastroMockRepository repository;

    @Override
    public CadastroResponseGtw execute(CadastroRequestGtw request) throws IOException {

        MockDataModel mock = repository.execute();

        MockDataListModel item = mock.getMock()
                .stream()
                .filter(c -> c.getNomeCliente().equals(request.getNomeCliente()))
                .findFirst()
                .get();

        return new CadastroResponseGtw(
                item.getNomeCliente(),
                item.getNumeroConta(),
                item.getNumeroAgencia(),
                TipoContaResponseGtw.valueOf(item.getTipoConta().toUpperCase()),
                new BigDecimal(item.getSaldoConta()),
                StatusContaResponseGtw.valueOf(item.getStatusConta())
        );

    }
}
