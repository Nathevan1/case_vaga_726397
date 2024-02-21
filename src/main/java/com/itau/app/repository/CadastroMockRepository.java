package com.itau.app.repository;

import com.itau.app.repository.model.MockDataModel;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface CadastroMockRepository {

    MockDataModel execute() throws IOException;
}
