package com.itau.app.repository;

import com.itau.app.repository.model.MockBacenModel;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface BacenMockRepository {

    MockBacenModel execute() throws IOException;
}
