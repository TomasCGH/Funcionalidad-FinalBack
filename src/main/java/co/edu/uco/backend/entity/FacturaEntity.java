package co.edu.uco.backend.entity;

import co.edu.uco.backend.crosscutting.utilitarios.*;

import java.util.UUID;
import java.time.LocalDate;

public final class FacturaEntity {

    private UUID id;
    private String identificador;
    private ReservaEntity reserva;
    private LocalDate fechaGeneracion;
    private double total;

    public FacturaEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setIdentificador(UtilTexto.getInstance().obtenerValorDefecto());
        setReserva(ReservaEntity.obtenerReservaDefecto());
        setFechaGeneracion(UtilFecha.obtenerValorDefecto((LocalDate) null));
        setTotal(UtilDouble.obtenerValorDefecto(0.0));
    }

    public FacturaEntity(final UUID id) {
        this();
        setId(id);
    }

    public FacturaEntity(final UUID id, final String identificador, final ReservaEntity reserva, final LocalDate fechaGeneracion, final double total) {
        setId(id);
        setIdentificador(identificador);
        setReserva(reserva);
        setFechaGeneracion(fechaGeneracion);
        setTotal(total);
    }

    public static FacturaEntity obtenerFacturaDefecto() {
        return new FacturaEntity();
    }

    public static FacturaEntity obtenerValorDefecto(final FacturaEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, obtenerFacturaDefecto());
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(final String identificador) {
        this.identificador = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(identificador);
    }

    public ReservaEntity getReserva() {
        return reserva;
    }

    public void setReserva(final ReservaEntity reserva) {
        this.reserva = ReservaEntity.obtenerValorDefecto(reserva);
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(final LocalDate fechaGeneracion) {
        this.fechaGeneracion = UtilFecha.obtenerValorDefecto(fechaGeneracion);
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(final double total) {
        this.total = UtilDouble.obtenerValorDefecto(total);
    }
}

