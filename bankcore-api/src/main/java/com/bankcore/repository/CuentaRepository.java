package com.bankcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankcore.domain.Cuenta;
import java.util.Optional;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
    boolean existsByNumeroCuenta(String numeroCuenta);
}
