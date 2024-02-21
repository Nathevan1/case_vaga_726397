package com.itau.app.repository.mock;

import com.google.gson.Gson;
import com.itau.app.repository.CadastroMockRepository;
import com.itau.app.repository.model.MockDataModel;
import com.sun.tools.javac.Main;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class CadastroMockRepositoryImpl implements CadastroMockRepository {

    @Override
    public MockDataModel execute() throws IOException {

        Gson gson = new Gson();

        try (
                InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("MockData.json");
                InputStreamReader reader = new InputStreamReader(inputStream)
                ){
            return gson.fromJson(reader, MockDataModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
