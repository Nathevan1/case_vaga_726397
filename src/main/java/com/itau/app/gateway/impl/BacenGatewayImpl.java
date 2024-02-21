package com.itau.app.gateway.impl;

import com.itau.app.gateway.BacenGateway;
import com.itau.app.gateway.request.BacenRequestGtw;
import com.itau.app.repository.BacenMockRepository;
import com.itau.app.repository.model.MockBacenListModel;
import com.itau.app.repository.model.MockBacenModel;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Random;

@Service
@AllArgsConstructor
public class BacenGatewayImpl implements BacenGateway {

    @Autowired
    private final BacenMockRepository repository;

    @Override
    public MockBacenListModel execute(BacenRequestGtw request) throws IOException {
        MockBacenModel mock = repository.execute();
        Random random = new Random();

        int statusAleatorio = random.nextInt(mock.getMock().size());

        return mock.getMock().get(statusAleatorio);
    }
}
