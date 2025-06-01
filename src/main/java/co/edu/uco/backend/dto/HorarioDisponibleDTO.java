package co.edu.uco.backend.dto;

import co.edu.uco.backend.crosscutting.constants.DiaSemana;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.time.LocalTime;
import java.util.UUID;

public final class HorarioDisponibleDTO {

    private UUID id;
    private CanchaDTO cancha;
    private DiaSemana dia;
    private LocalTime horaApertura;
    private LocalTime horaCierre;

    public HorarioDisponibleDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setCancha(CanchaDTO.obtenerValorDefecto());
        setDia(UtilObjeto.getInstance().obtenerValorDefecto(null, DiaSemana.obtenerValorPorDefecto()));
        setHoraApertura(UtilObjeto.getInstance().obtenerValorDefecto(null, LocalTime.MIDNIGHT));
        setHoraCierre(UtilObjeto.getInstance().obtenerValorDefecto(null, LocalTime.MIDNIGHT));
    }

    public HorarioDisponibleDTO(final UUID id) {
        setId(id);
        setCancha(CanchaDTO.obtenerValorDefecto());
        setDia(UtilObjeto.getInstance().obtenerValorDefecto(null, DiaSemana.obtenerValorPorDefecto()));
        setHoraApertura(UtilObjeto.getInstance().obtenerValorDefecto(null, LocalTime.MIDNIGHT));
        setHoraCierre(UtilObjeto.getInstance().obtenerValorDefecto(null, LocalTime.MIDNIGHT));
    }

    public HorarioDisponibleDTO(
            final UUID id,
            final CanchaDTO cancha,
            final DiaSemana dia,
            final LocalTime horaApertura,
            final LocalTime horaCierre
    ) {
        setId(id);
        setCancha(cancha);
        setDia(dia);
        setHoraApertura(horaApertura);
        setHoraCierre(horaCierre);
    }

    public static HorarioDisponibleDTO obtenerValorDefecto() {
        return new HorarioDisponibleDTO();
    }

    public static HorarioDisponibleDTO obtenerValorDefecto(final HorarioDisponibleDTO dto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(dto, obtenerValorDefecto());
    }

    public UUID getId() {
        return id;
    }

    public CanchaDTO getCancha() {
        return cancha;
    }

    public DiaSemana getDia() {
        return dia;
    }

    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public HorarioDisponibleDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public HorarioDisponibleDTO setCancha(final CanchaDTO cancha) {
        this.cancha = CanchaDTO.obtenerValorDefecto(cancha);
        return this;
    }

    public HorarioDisponibleDTO setDia(final DiaSemana dia) {
        this.dia = UtilObjeto.getInstance().obtenerValorDefecto(dia, DiaSemana.obtenerValorPorDefecto());
        return this;
    }

    public HorarioDisponibleDTO setHoraApertura(final LocalTime horaApertura) {
        this.horaApertura = UtilObjeto.getInstance().obtenerValorDefecto(horaApertura, LocalTime.MIDNIGHT);
        return this;
    }

    public HorarioDisponibleDTO setHoraCierre(final LocalTime horaCierre) {
        this.horaCierre = UtilObjeto.getInstance().obtenerValorDefecto(horaCierre, LocalTime.MIDNIGHT);
        return this;
    }
}
