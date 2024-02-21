package com.itau.app.repository;

import com.itau.app.repository.entity.Saldo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaldoRepository extends JpaRepository<Saldo, Long> {

    List<Saldo> findByNomeCliente(String nomeCliente);
}
