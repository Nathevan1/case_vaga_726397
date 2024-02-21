package com.itau.app.repository.inicializacao;

import com.itau.app.repository.CadastroMockRepository;
import com.itau.app.repository.SaldoRepository;
import com.itau.app.repository.entity.Saldo;
import com.itau.app.repository.model.MockDataListModel;
import com.itau.app.repository.model.MockDataModel;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class CadastroInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CadastroInitializer.class);

    @Autowired
    private SaldoRepository saldoRepository;

    @Autowired
    private CadastroMockRepository mock;

    @Override
    public void run(String... args) throws Exception {

        if(saldoRepository.count() == 0) {

            MockDataModel mockDataModel = mock.execute();

            for(MockDataListModel item: mockDataModel.getMock()){

                Saldo saldo = new Saldo();
                saldo.setNomeCliente(item.getNomeCliente());
                saldo.setNumeroConta(item.getNumeroConta());
                saldo.setNumeroAgencia(item.getNumeroAgencia());
                saldo.setTipoConta(item.getTipoConta());
                saldo.setStatusConta(item.getStatusConta());
                saldo.setSaldoConta(new BigDecimal(item.getSaldoConta()));

                saldoRepository.save(saldo);
            }

            logger.info("Dados mockados foram criados e salvos no banco de dados.");
        } else {
            logger.info("Os dados de cadastro não foram mockados, porque o Banco de Dados não está vazio");
        }
    }
}
