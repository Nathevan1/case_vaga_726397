package com.itau.app.repository.mock;

import com.google.gson.Gson;
import com.itau.app.repository.BacenMockRepository;
import com.itau.app.repository.model.MockBacenModel;
import com.sun.tools.javac.Main;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class BacenMockRepositoryImpl implements BacenMockRepository {


    @Override
    public MockBacenModel execute() throws IOException {
        Gson gson = new Gson();

        try (
                InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("MockBacen.json");
                InputStreamReader reader = new InputStreamReader(inputStream)
        ){
            return gson.fromJson(reader, MockBacenModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
