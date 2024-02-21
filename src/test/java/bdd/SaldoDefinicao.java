package bdd;

import com.itau.app.adapter.api.request.ConsultaSaldoRequest;
import com.itau.app.adapter.api.response.ConsultaSaldoResponse;
import com.itau.app.adapter.controller.ConsultaSaldoController;
import com.itau.app.application.exception.ConsultaSaldoUseCaseException;
import com.itau.app.application.usecase.ValidacaoTransferenciaUseCase;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaldoDefinicao {

    @Autowired
    private ConsultaSaldoController consultaSaldoController;

    private final ConsultaSaldoRequest consultaSaldoRequest = new ConsultaSaldoRequest();

    private ResponseEntity<ConsultaSaldoResponse> consultaSaldoResponseResponseEntity;

    private String mensagemErro = "";

    @Dado("Eu tenho o nome {string} do cliente definido")
    public void euTenhoODoClienteDefinido(String nomeCliente) {
        consultaSaldoRequest.setNomeCliente(nomeCliente);
    }

    @Quando("Eu pesquiso na API de Saldo")
    public void euPesquisoNoAPIDeSaldo() throws IOException {
        consultaSaldoResponseResponseEntity = consultaSaldoController.execute(consultaSaldoRequest);
    }

    @Entao("Eu obtenho os dados de Cadastro e Saldo do Cliente com status {string}")
    public void euObtenhoOsDadosDeCadastroESaldoDoCliente(String statusCode) {
        assertEquals(consultaSaldoResponseResponseEntity.getStatusCode().value(), Integer.parseInt(statusCode));
    }

    @Quando("Eu pesquiso na API de Saldo e não encontro o cliente")
    public void euPesquisoNaAPIDeSaldoENãoEncontroOCliente() throws IOException {
        try {
            consultaSaldoResponseResponseEntity = consultaSaldoController.execute(consultaSaldoRequest);
        } catch (ConsultaSaldoUseCaseException e){
            mensagemErro = e.getMessage();
        }

    }

    @Entao("Eu obtenho a mensagem de erro {string}")
    public void euObtenho(String mensagem) {
        Assertions.assertEquals(mensagemErro, mensagem);
    }
}
