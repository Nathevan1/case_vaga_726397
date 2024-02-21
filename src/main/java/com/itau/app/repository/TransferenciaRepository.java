package com.itau.app.repository;

import com.itau.app.repository.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    List<Transferencia> findByNomeClienteAndData(String nomeCliente, LocalDate data);

}
