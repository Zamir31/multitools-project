package com.bankcore;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Transaccion(
        TipoTransaccion tipoTransaccion,
        BigDecimal cantidad,
        LocalDateTime fecha,
        String descripcion,
        String numeroCuentaOrigen,
        String numeroCuentaDestino) {
}
