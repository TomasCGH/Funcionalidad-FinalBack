package co.edu.uco.backend.businesslogic.businesslogic.domain;

import co.edu.uco.backend.crosscutting.constants.DiaSemana;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;
import java.time.LocalTime;
import java.util.UUID;


public final class HorarioDisponibleDomain {
    private UUID id;
    private CanchaDomain cancha;
    private DiaSemana dia;
    private LocalTime horaApertura;
    private LocalTime horaCierre;

    HorarioDisponibleDomain() {
        setId(UtilUUID.obtenerValorDefecto());
        setCancha(CanchaDomain.obtenerCanchaDefecto());
        setDia(UtilObjeto.getInstance().obtenerValorDefecto(null, DiaSemana.obtenerValorPorDefecto()));
        setHoraApertura(UtilObjeto.getInstance().obtenerValorDefecto(null, LocalTime.MIDNIGHT));
        setHoraCierre(UtilObjeto.getInstance().obtenerValorDefecto(null, LocalTime.MIDNIGHT));
    }

    public HorarioDisponibleDomain(final UUID id) {
        setId(id);
        setCancha(CanchaDomain.obtenerCanchaDefecto());
        setDia(UtilObjeto.getInstance().obtenerValorDefecto(null, DiaSemana.obtenerValorPorDefecto()));
        setHoraApertura(UtilObjeto.getInstance().obtenerValorDefecto(null, LocalTime.MIDNIGHT));
        setHoraCierre(UtilObjeto.getInstance().obtenerValorDefecto(null, LocalTime.MIDNIGHT));
    }

    public HorarioDisponibleDomain(
            final UUID id,
            final CanchaDomain cancha,
            final DiaSemana dia,
            final LocalTime horaApertura,
            final LocalTime horaCierre) {
        setId(id);
        setCancha(cancha);
        setDia(dia);
        setHoraApertura(horaApertura);
        setHoraCierre(horaCierre);
    }

    static HorarioDisponibleDomain obtenerHorarioDisponibleDefecto() {
        return new HorarioDisponibleDomain();
    }

    static HorarioDisponibleDomain obtenerValorDefecto(final HorarioDisponibleDomain domain) {
        return UtilObjeto.getInstance().obtenerValorDefecto(domain, obtenerHorarioDisponibleDefecto());
    }

    public UUID getId() {
        return id;
    }

    public CanchaDomain getCancha() {
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


    private void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    private void setCancha(final CanchaDomain cancha) {
        this.cancha = CanchaDomain.obtenerValorDefecto(cancha);
    }

    private void setDia(final DiaSemana dia) {
        this.dia = UtilObjeto.getInstance().obtenerValorDefecto(dia, DiaSemana.obtenerValorPorDefecto());
    }

    private void setHoraApertura(final LocalTime horaApertura) {
        this.horaApertura = UtilObjeto.getInstance().obtenerValorDefecto(horaApertura, LocalTime.MIDNIGHT);
    }

    private void setHoraCierre(final LocalTime horaCierre) {
        this.horaCierre = UtilObjeto.getInstance().obtenerValorDefecto(horaCierre, LocalTime.MIDNIGHT);
    }
}
