package com.itau.app.gateway;

import com.itau.app.gateway.request.BacenRequestGtw;
import com.itau.app.repository.model.MockBacenListModel;

import java.io.IOException;

public interface BacenGateway {

    MockBacenListModel execute(BacenRequestGtw request) throws IOException;
}
