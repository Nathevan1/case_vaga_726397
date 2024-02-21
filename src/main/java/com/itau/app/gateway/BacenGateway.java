package com.itau.app.gateway;

import com.itau.app.gateway.request.BacenRequestGtw;
import com.itau.app.repository.model.MockBacenListModel;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface BacenGateway {

    MockBacenListModel execute(BacenRequestGtw request) throws IOException;
}
