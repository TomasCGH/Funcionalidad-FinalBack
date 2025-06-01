package co.edu.uco.backend.businesslogic.businesslogic.domain;

import co.edu.uco.backend.crosscutting.utilitarios.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public final class HorarioEspecialDomain {

    private UUID id;
    private CanchaDomain cancha;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String motivo;

    HorarioEspecialDomain() {
        setId(UtilUUID.obtenerValorDefecto());
        setCancha(CanchaDomain.obtenerCanchaDefecto());
        setFechaInicio(UtilFecha.obtenerValorDefecto());
        setFechaFin(UtilFecha.obtenerValorDefecto());
        setHoraInicio(LocalTime.MIDNIGHT);
        setHoraFin(LocalTime.MIDNIGHT);
        setMotivo(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public HorarioEspecialDomain(final UUID id) {
        this(); // Reutiliza el constructor vacío y luego sobrescribe el id
        setId(id);
    }

    public HorarioEspecialDomain(
            final UUID id,
            final CanchaDomain cancha,
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

    static HorarioEspecialDomain obtenerHorarioEspecialDefecto() {
        return new HorarioEspecialDomain();
    }

    static HorarioEspecialDomain obtenerValorDefecto(final HorarioEspecialDomain domain) {
        return UtilObjeto.getInstance().obtenerValorDefecto(domain, obtenerHorarioEspecialDefecto());
    }

    public UUID getId() {
        return id;
    }

    private void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public CanchaDomain getCancha() {
        return cancha;
    }

    private void setCancha(final CanchaDomain cancha) {
        this.cancha = CanchaDomain.obtenerValorDefecto(cancha);
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    private void setFechaInicio(final LocalDate fechaInicio) {
        this.fechaInicio = UtilFecha.obtenerValorDefecto(fechaInicio);
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    private void setFechaFin(final LocalDate fechaFin) {
        this.fechaFin = UtilFecha.obtenerValorDefecto(fechaFin);
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    private void setHoraInicio(final LocalTime horaInicio) {
        this.horaInicio = UtilObjeto.getInstance().obtenerValorDefecto(horaInicio, LocalTime.MIDNIGHT);
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    private void setHoraFin(final LocalTime horaFin) {
        this.horaFin = UtilObjeto.getInstance().obtenerValorDefecto(horaFin, LocalTime.MIDNIGHT);
    }

    public String getMotivo() {
        return motivo;
    }

    private void setMotivo(final String motivo) {
        this.motivo = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(motivo);
    }
}
