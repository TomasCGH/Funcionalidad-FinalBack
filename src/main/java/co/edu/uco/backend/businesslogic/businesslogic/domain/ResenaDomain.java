package co.edu.uco.backend.businesslogic.businesslogic.domain;

import co.edu.uco.backend.crosscutting.utilitarios.*;

import java.time.LocalDate;
import java.util.UUID;

public final class ResenaDomain {

    private UUID id;
    private ReservaDomain reserva;
    private int calificacion;
    private String comentario;
    private LocalDate fecha;

    ResenaDomain() {
        setId(UtilUUID.obtenerValorDefecto());
        setReserva(ReservaDomain.obtenerReservaDefecto());
        setCalificacion(UtilEntero.obtenerValorDefecto(null));
        setComentario(UtilTexto.getInstance().obtenerValorDefecto());
        setFecha(UtilFecha.obtenerValorDefecto((LocalDate) null));
    }

    public ResenaDomain(final UUID id) {
        this();
        setId(id);
    }

    public ResenaDomain(final UUID id, final ReservaDomain reserva, final int calificacion,
                        final String comentario, final LocalDate fecha) {
        setId(id);
        setReserva(reserva);
        setCalificacion(calificacion);
        setComentario(comentario);
        setFecha(fecha);
    }

    static ResenaDomain obtenerResenaDefecto() {
        return new ResenaDomain();
    }

    static ResenaDomain obtenerValorDefecto(final ResenaDomain domain) {
        return UtilObjeto.getInstance().obtenerValorDefecto(domain, obtenerResenaDefecto());
    }

    public UUID getId() {
        return id;
    }

    private void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public ReservaDomain getReserva() {
        return reserva;
    }

    private void setReserva(final ReservaDomain reserva) {
        this.reserva = ReservaDomain.obtenerValorDefecto(reserva);
    }

    public int getCalificacion() {
        return calificacion;
    }

    private void setCalificacion(final int calificacion) {
        this.calificacion = UtilEntero.obtenerValorDefecto(calificacion);
    }

    public String getComentario() {
        return comentario;
    }

    private void setComentario(final String comentario) {
        this.comentario = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(comentario);
    }

    public LocalDate getFecha() {
        return fecha;
    }

    private void setFecha(final LocalDate fecha) {
        this.fecha = UtilFecha.obtenerValorDefecto(fecha);
    }
}
