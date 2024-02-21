package com.itau.app.application.service;

import com.itau.app.application.domain.TransferenciaBacenDomain;
import com.itau.app.application.domain.TransferenciaDomain;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface TransferenciaService {

    TransferenciaBacenDomain execute(final TransferenciaDomain transferenciaDomain) throws IOException;
}
