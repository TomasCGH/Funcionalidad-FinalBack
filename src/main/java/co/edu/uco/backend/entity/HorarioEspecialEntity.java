package co.edu.uco.backend.entity;

import co.edu.uco.backend.crosscutting.utilitarios.UtilFecha;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;
import java.time.LocalTime;
import java.time.LocalDate;

public final class HorarioEspecialEntity {

    private UUID id;
    private CanchaEntity cancha;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String motivo;

    public HorarioEspecialEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setCancha(CanchaEntity.obtenerCanchaDefecto());
        setFechaInicio(UtilFecha.obtenerValorDefecto());
        setFechaFin(UtilFecha.obtenerValorDefecto());
        setHoraInicio(LocalTime.MIDNIGHT);
        setHoraFin(LocalTime.MIDNIGHT);
        setMotivo(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public HorarioEspecialEntity(final UUID id) {
        this();
        setId(id);
    }

    public HorarioEspecialEntity(
            final UUID id,
            final CanchaEntity cancha,
            final LocalDate fechaInicio,
            final LocalDate fechaFin,
            final LocalTime horaInicio,
            final LocalTime horaFin,
            final String motivo
    ) {
        setId(id);
        setCancha(cancha);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setHoraInicio(horaInicio);
        setHoraFin(horaFin);
        setMotivo(motivo);
    }

    public static HorarioEspecialEntity obtenerHorarioEspecialDefecto() {
        return new HorarioEspecialEntity();
    }

    public static HorarioEspecialEntity obtenerValorDefecto(final HorarioEspecialEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, obtenerHorarioEspecialDefecto());
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public CanchaEntity getCancha() {
        return cancha;
    }

    public void setCancha(final CanchaEntity cancha) {
        this.cancha = CanchaEntity.obtenerValorDefecto(cancha);
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(final LocalDate fechaInicio) {
        this.fechaInicio = UtilFecha.obtenerValorDefecto(fechaInicio);
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(final LocalDate fechaFin) {
        this.fechaFin = UtilFecha.obtenerValorDefecto(fechaFin);
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(final LocalTime horaInicio) {
        this.horaInicio = UtilObjeto.getInstance().obtenerValorDefecto(horaInicio, LocalTime.MIDNIGHT);
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(final LocalTime horaFin) {
        this.horaFin = UtilObjeto.getInstance().obtenerValorDefecto(horaFin, LocalTime.MIDNIGHT);
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(final String motivo) {
        this.motivo = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(motivo);
    }
}

