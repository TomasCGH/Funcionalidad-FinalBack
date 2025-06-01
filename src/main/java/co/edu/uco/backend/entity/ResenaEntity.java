package co.edu.uco.backend.entity;

import co.edu.uco.backend.crosscutting.utilitarios.*;

import java.time.LocalDate;
import java.util.UUID;

public final class ResenaEntity {

    private UUID id;
    private ReservaEntity reserva;
    private int calificacion;
    private String comentario;
    private LocalDate fecha;

    public ResenaEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setReserva(ReservaEntity.obtenerReservaDefecto());
        setCalificacion(UtilEntero.obtenerValorDefecto(null));
        setComentario(UtilTexto.getInstance().obtenerValorDefecto());
        setFecha(UtilFecha.obtenerValorDefecto((LocalDate) null));
    }

    public ResenaEntity(final UUID id) {
        this();
        setId(id);
    }

    public ResenaEntity(final UUID id, final ReservaEntity reserva, final int calificacion,
                        final String comentario, final LocalDate fecha) {
        setId(id);
        setReserva(reserva);
        setCalificacion(calificacion);
        setComentario(comentario);
        setFecha(fecha);
    }

    public static ResenaEntity obtenerResenaDefecto() {
        return new ResenaEntity();
    }

    public static ResenaEntity obtenerValorDefecto(final ResenaEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, obtenerResenaDefecto());
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public ReservaEntity getReserva() {
        return reserva;
    }

    public void setReserva(final ReservaEntity reserva) {
        this.reserva = ReservaEntity.obtenerValorDefecto(reserva);
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(final int calificacion) {
        this.calificacion = UtilEntero.obtenerValorDefecto(calificacion);
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(final String comentario) {
        this.comentario = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(comentario);
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(final LocalDate fecha) {
        this.fecha = UtilFecha.obtenerValorDefecto(fecha);
    }
}

