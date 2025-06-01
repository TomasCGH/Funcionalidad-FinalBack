package co.edu.uco.backend.dto;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.backend.crosscutting.utilitarios.UtilFecha;
import co.edu.uco.backend.crosscutting.utilitarios.UtilDouble;

import java.util.UUID;
import java.time.LocalDate;

public final class FacturaDTO {

    private UUID id;
    private String identificador;
    private ReservaDTO reserva;
    private LocalDate fechaGeneracion;
    private double total;

    public FacturaDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setIdentificador(UtilTexto.getInstance().obtenerValorDefecto());
        setReserva(ReservaDTO.obtenerValorDefecto());
        setFechaGeneracion(UtilFecha.obtenerValorDefecto((LocalDate) null));
        setTotal(UtilDouble.obtenerValorDefecto(0.0));
    }

    public FacturaDTO(final UUID id) {
        setId(id);
        setIdentificador(UtilTexto.getInstance().obtenerValorDefecto());
        setReserva(ReservaDTO.obtenerValorDefecto());
        setFechaGeneracion(UtilFecha.obtenerValorDefecto((LocalDate) null));
        setTotal(UtilDouble.obtenerValorDefecto(0.0));
    }

    public FacturaDTO(
            final UUID id,
            final String identificador,
            final ReservaDTO reserva,
            final LocalDate fechaGeneracion,
            final double total
    ) {
        setId(id);
        setIdentificador(identificador);
        setReserva(reserva);
        setFechaGeneracion(fechaGeneracion);
        setTotal(total);
    }

    public static FacturaDTO obtenerValorDefecto() {
        return new FacturaDTO();
    }

    public static FacturaDTO obtenerValorDefecto(final FacturaDTO dto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(dto, obtenerValorDefecto());
    }

    public UUID getId() {
        return id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public ReservaDTO getReserva() {
        return reserva;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public double getTotal() {
        return total;
    }

    public FacturaDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public FacturaDTO setIdentificador(final String identificador) {
        this.identificador = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(identificador);
        return this;
    }

    public FacturaDTO setReserva(final ReservaDTO reserva) {
        this.reserva = ReservaDTO.obtenerValorDefecto(reserva);
        return this;
    }

    public FacturaDTO setFechaGeneracion(final LocalDate fechaGeneracion) {
        this.fechaGeneracion = UtilFecha.obtenerValorDefecto(fechaGeneracion);
        return this;
    }

    public FacturaDTO setTotal(final double total) {
        this.total = UtilDouble.obtenerValorDefecto(total);
        return this;
    }
}
