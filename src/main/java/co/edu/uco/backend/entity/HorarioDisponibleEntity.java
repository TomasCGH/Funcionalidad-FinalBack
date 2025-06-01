package co.edu.uco.backend.entity;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.backend.crosscutting.constants.DiaSemana;
import java.time.LocalTime;

import java.util.UUID;

public final class HorarioDisponibleEntity {

    private UUID id;
    private CanchaEntity cancha;
    private DiaSemana dia;
    private LocalTime horaApertura;
    private LocalTime horaCierre;

    public HorarioDisponibleEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setCancha(CanchaEntity.obtenerCanchaDefecto());
        setDia(UtilObjeto.getInstance().obtenerValorDefecto(null, DiaSemana.obtenerValorPorDefecto()));
        setHoraApertura(UtilObjeto.getInstance().obtenerValorDefecto(null, LocalTime.MIDNIGHT));
        setHoraCierre(UtilObjeto.getInstance().obtenerValorDefecto(null, LocalTime.MIDNIGHT));
    }

    public HorarioDisponibleEntity(final UUID id) {
        setId(id);
        setCancha(CanchaEntity.obtenerCanchaDefecto());
        setDia(UtilObjeto.getInstance().obtenerValorDefecto(null, DiaSemana.obtenerValorPorDefecto()));
        setHoraApertura(UtilObjeto.getInstance().obtenerValorDefecto(null, LocalTime.MIDNIGHT));
        setHoraCierre(UtilObjeto.getInstance().obtenerValorDefecto(null, LocalTime.MIDNIGHT));
    }

    public HorarioDisponibleEntity(
            final UUID id,
            final CanchaEntity cancha,
            final DiaSemana dia,
            final LocalTime horaApertura,
            final LocalTime horaCierre) {
        setId(id);
        setCancha(cancha);
        setDia(dia);
        setHoraApertura(horaApertura);
        setHoraCierre(horaCierre);
    }

    public static HorarioDisponibleEntity obtenerHorarioDisponibleDefecto() {
        return new HorarioDisponibleEntity();
    }

    public static HorarioDisponibleEntity obtenerValorDefecto(final HorarioDisponibleEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, obtenerHorarioDisponibleDefecto());
    }

    public UUID getId() {
        return id;
    }

    public CanchaEntity getCancha() {
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

    public void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public void setCancha(final CanchaEntity cancha) {
        this.cancha = CanchaEntity.obtenerValorDefecto(cancha);
    }

    public void setDia(final DiaSemana dia) {
        this.dia = UtilObjeto.getInstance().obtenerValorDefecto(dia, DiaSemana.obtenerValorPorDefecto());
    }

    public void setHoraApertura(final LocalTime horaApertura) {
        this.horaApertura = UtilObjeto.getInstance().obtenerValorDefecto(horaApertura, LocalTime.MIDNIGHT);
    }

    public void setHoraCierre(final LocalTime horaCierre) {
        this.horaCierre = UtilObjeto.getInstance().obtenerValorDefecto(horaCierre, LocalTime.MIDNIGHT);
    }

}
