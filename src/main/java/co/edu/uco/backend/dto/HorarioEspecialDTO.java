package co.edu.uco.backend.dto;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.backend.crosscutting.utilitarios.UtilFecha;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public final class HorarioEspecialDTO {

    private UUID id;
    private CanchaDTO cancha;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String motivo;

    public HorarioEspecialDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setCancha(CanchaDTO.obtenerValorDefecto());
        setFechaInicio(UtilFecha.obtenerValorDefecto((LocalDate) null));
        setFechaFin(UtilFecha.obtenerValorDefecto((LocalDate) null));
        setHoraInicio(UtilObjeto.getInstance().obtenerValorDefecto(null, LocalTime.MIDNIGHT));
        setHoraFin(UtilObjeto.getInstance().obtenerValorDefecto(null, LocalTime.MIDNIGHT));
        setMotivo(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public HorarioEspecialDTO(final UUID id) {
        this();
        setId(id);
    }

    public HorarioEspecialDTO(
            final UUID id,
            final CanchaDTO cancha,
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

    public static HorarioEspecialDTO obtenerValorDefecto() {
        return new HorarioEspecialDTO();
    }

    public static HorarioEspecialDTO obtenerValorDefecto(final HorarioEspecialDTO dto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(dto, obtenerValorDefecto());
    }

    public UUID getId() {
        return id;
    }

    public CanchaDTO getCancha() {
        return cancha;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public String getMotivo() {
        return motivo;
    }

    public HorarioEspecialDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public HorarioEspecialDTO setCancha(final CanchaDTO cancha) {
        this.cancha = CanchaDTO.obtenerValorDefecto(cancha);
        return this;
    }

    public HorarioEspecialDTO setFechaInicio(final LocalDate fechaInicio) {
        this.fechaInicio = UtilFecha.obtenerValorDefecto(fechaInicio);
        return this;
    }

    public HorarioEspecialDTO setFechaFin(final LocalDate fechaFin) {
        this.fechaFin = UtilFecha.obtenerValorDefecto(fechaFin);
        return this;
    }

    public HorarioEspecialDTO setHoraInicio(final LocalTime horaInicio) {
        this.horaInicio = UtilObjeto.getInstance().obtenerValorDefecto(horaInicio, LocalTime.MIDNIGHT);
        return this;
    }

    public HorarioEspecialDTO setHoraFin(final LocalTime horaFin) {
        this.horaFin = UtilObjeto.getInstance().obtenerValorDefecto(horaFin, LocalTime.MIDNIGHT);
        return this;
    }

    public HorarioEspecialDTO setMotivo(final String motivo) {
        this.motivo = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(motivo);
        return this;
    }
}
