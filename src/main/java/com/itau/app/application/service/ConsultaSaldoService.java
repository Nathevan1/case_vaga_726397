package com.itau.app.application.service;

import com.itau.app.application.domain.ConsultaSaldoDomain;
import com.itau.app.application.domain.SaldoDomain;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ConsultaSaldoService {

    SaldoDomain execute(final ConsultaSaldoDomain consultaSaldoDomain) throws IOException;
}
