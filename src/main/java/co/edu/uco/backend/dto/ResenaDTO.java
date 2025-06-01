package co.edu.uco.backend.dto;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.backend.crosscutting.utilitarios.UtilFecha;
import co.edu.uco.backend.crosscutting.utilitarios.UtilEntero;

import java.time.LocalDate;
import java.util.UUID;

public final class ResenaDTO {

    private UUID id;
    private ReservaDTO reserva;
    private int calificacion;
    private String comentario;
    private LocalDate fecha;

    public ResenaDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setReserva(ReservaDTO.obtenerValorDefecto());
        setCalificacion(UtilEntero.obtenerValorDefecto(0));
        setComentario(UtilTexto.getInstance().obtenerValorDefecto());
        setFecha(UtilFecha.obtenerValorDefecto((LocalDate) null));
    }

    public ResenaDTO(final UUID id) {
        this();
        setId(id);
    }

    public ResenaDTO(
            final UUID id,
            final ReservaDTO reserva,
            final int calificacion,
            final String comentario,
            final LocalDate fecha
    ) {
        setId(id);
        setReserva(reserva);
        setCalificacion(calificacion);
        setComentario(comentario);
        setFecha(fecha);
    }

    public static ResenaDTO obtenerValorDefecto() {
        return new ResenaDTO();
    }

    public static ResenaDTO obtenerValorDefecto(final ResenaDTO dto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(dto, obtenerValorDefecto());
    }

    public UUID getId() {
        return id;
    }

    public ReservaDTO getReserva() {
        return reserva;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public ResenaDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public ResenaDTO setReserva(final ReservaDTO reserva) {
        this.reserva = ReservaDTO.obtenerValorDefecto(reserva);
        return this;
    }

    public ResenaDTO setCalificacion(final int calificacion) {
        this.calificacion = UtilEntero.obtenerValorDefecto(calificacion);
        return this;
    }

    public ResenaDTO setComentario(final String comentario) {
        this.comentario = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(comentario);
        return this;
    }

    public ResenaDTO setFecha(final LocalDate fecha) {
        this.fecha = UtilFecha.obtenerValorDefecto(fecha);
        return this;
    }
}
