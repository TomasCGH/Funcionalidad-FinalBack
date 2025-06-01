package co.edu.uco.backend.crosscutting.utilitarios;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class UtilFecha {

    private static final LocalDate FECHA_DEFECTO = LocalDate.of(1900, 1, 1);
    private static final LocalDateTime FECHA_HORA_DEFECTO = LocalDateTime.of(1900, 1, 1, 0, 0);

    private UtilFecha() {
        super();
    }

    public static LocalDate obtenerValorDefecto(final LocalDate fecha) {
        return UtilObjeto.getInstance().obtenerValorDefecto(fecha, FECHA_DEFECTO);
    }

    public static LocalDateTime obtenerValorDefecto(final LocalDateTime fechaHora) {
        return UtilObjeto.getInstance().obtenerValorDefecto(fechaHora, FECHA_HORA_DEFECTO);
    }

    public static LocalDate obtenerValorDefecto() {
        return FECHA_DEFECTO;
    }
}
