package co.edu.uco.backend.businesslogic.businesslogic.domain;

import co.edu.uco.backend.crosscutting.utilitarios.*;

import java.time.LocalDate;
import java.util.UUID;

public final class FacturaDomain {

    private UUID id;
    private String identificador;
    private ReservaDomain reserva;
    private LocalDate fechaGeneracion;
    private double total;

    FacturaDomain() {
        setId(UtilUUID.obtenerValorDefecto());
        setIdentificador(UtilTexto.getInstance().obtenerValorDefecto());
        setReserva(ReservaDomain.obtenerReservaDefecto());
        setFechaGeneracion(UtilFecha.obtenerValorDefecto((LocalDate) null));
        setTotal(UtilDouble.obtenerValorDefecto(0.0));
    }

    public FacturaDomain(final UUID id) {
        this();
        setId(id);
    }

    public FacturaDomain(final UUID id, final String identificador, final ReservaDomain reserva, final LocalDate fechaGeneracion, final double total) {
        setId(id);
        setIdentificador(identificador);
        setReserva(reserva);
        setFechaGeneracion(fechaGeneracion);
        setTotal(total);
    }

    static FacturaDomain obtenerFacturaDefecto() {
        return new FacturaDomain();
    }

    static FacturaDomain obtenerValorDefecto(final FacturaDomain domain) {
        return UtilObjeto.getInstance().obtenerValorDefecto(domain, obtenerFacturaDefecto());
    }

    public UUID getId() {
        return id;
    }

    private void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public String getIdentificador() {
        return identificador;
    }

    private void setIdentificador(final String identificador) {
        this.identificador = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(identificador);
    }

    public ReservaDomain getReserva() {
        return reserva;
    }

    private void setReserva(final ReservaDomain reserva) {
        this.reserva = ReservaDomain.obtenerValorDefecto(reserva);
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    private void setFechaGeneracion(final LocalDate fechaGeneracion) {
        this.fechaGeneracion = UtilFecha.obtenerValorDefecto(fechaGeneracion);
    }

    public double getTotal() {
        return total;
    }

    private void setTotal(final double total) {
        this.total = UtilDouble.obtenerValorDefecto(total);
    }
}
