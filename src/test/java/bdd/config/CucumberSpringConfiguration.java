package bdd.config;

import com.itau.app.AppApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = AppApplication.class)
public class CucumberSpringConfiguration {
    // Configuração de contexto do Spring para o Cucumber
}