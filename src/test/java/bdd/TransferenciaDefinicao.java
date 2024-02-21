package bdd;

import com.itau.app.adapter.api.request.ConsultaSaldoRequest;
import com.itau.app.adapter.api.request.TipoContaRequest;
import com.itau.app.adapter.api.request.TransferenciaDestinoRequest;
import com.itau.app.adapter.api.request.TransferenciaRequest;
import com.itau.app.adapter.api.response.ConsultaSaldoResponse;
import com.itau.app.adapter.api.response.TransferenciaResponse;
import com.itau.app.adapter.controller.ConsultaSaldoController;
import com.itau.app.adapter.controller.TransferenciaController;
import com.itau.app.application.exception.ConsultaSaldoUseCaseException;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferenciaDefinicao {

    @Autowired
    private ConsultaSaldoController consultaSaldoController;

    private final ConsultaSaldoRequest consultaSaldoRequest = new ConsultaSaldoRequest();

    private ResponseEntity<ConsultaSaldoResponse> consultaSaldoResponse;


    @Autowired
    private TransferenciaController transferenciaController;

    private final TransferenciaRequest transferenciaRequest = new TransferenciaRequest();

    private ResponseEntity<TransferenciaResponse> transferenciaResponseResponseEntity;

    private String mensagemErro = "";

    @Dado("Eu sou o cliente {string}")
    public void euSouOCliente(String nomeCliente) throws IOException {
        consultaSaldoRequest.setNomeCliente(nomeCliente);
    }

    @Quando("Quero fazer uma transferência para o banco {string} agencia {string} e conta {string} do tipo {string} no valor de {string}")
    public void queroFazerUmaTransferênciaParaOBancoAgenciaEContaNoValorDe(String banco, String agencia, String conta, String tipo, String valor) throws IOException {

        consultaSaldoResponse = consultaSaldoController.execute(consultaSaldoRequest);

        transferenciaRequest.setNomeCliente(consultaSaldoResponse.getBody().nomeCliente);
        transferenciaRequest.setNumeroAgencia(consultaSaldoResponse.getBody().numeroAgencia);
        transferenciaRequest.setNumeroConta(consultaSaldoResponse.getBody().numeroConta);
        transferenciaRequest.setTipoConta(consultaSaldoResponse.getBody().tipoConta);
        transferenciaRequest.setValorTransferencia(valor);

        TransferenciaDestinoRequest transferenciaDestinoRequest = new TransferenciaDestinoRequest();
        transferenciaDestinoRequest.setCodigoBanco(banco);
        transferenciaDestinoRequest.setNumeroAgencia(agencia);
        transferenciaDestinoRequest.setNumeroConta(conta);
        transferenciaDestinoRequest.setTipoConta(TipoContaRequest.valueOf(tipo.toUpperCase()));

        transferenciaRequest.setTransferenciaDestino(transferenciaDestinoRequest);

        try {
            transferenciaResponseResponseEntity = transferenciaController.execute(transferenciaRequest);
        } catch (ConsultaSaldoUseCaseException e){
            mensagemErro = e.getMessage();
        }
    }

    @Entao("Eu faço a transferência e recebo confirmação com status {string}")
    public void euFaçoATransferênciaEReceboConfirmaçãoComStatus(String statusCode) {

        assertEquals(transferenciaResponseResponseEntity.getStatusCode().value(), Integer.parseInt(statusCode));
    }

    @Entao("Eu não faco a transferencia porque a conta deste cliente está {string} e recebi a mensagem {string}")
    public void euNãoFacoATransferenciaPorqueAContaDesteClienteEstáERecebiAMensagem(String status, String mensagem) {

        Assertions.assertEquals(mensagemErro, mensagem);
        Assertions.assertEquals(consultaSaldoResponse.getBody().statusConta.name(), status);
    }
}
