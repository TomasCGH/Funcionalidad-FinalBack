package co.edu.uco.backend.entity;

import co.edu.uco.backend.crosscutting.utilitarios.UtilFecha;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public final class ReservaEntity {

    private UUID id;
    private ClienteEntity cliente;
    private CanchaEntity cancha;
    private LocalDate fechaReserva;
    private LocalDate fechaUsoCancha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private EstadoReservaEntity estado;

    public ReservaEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setCliente(ClienteEntity.obtenerClienteDefecto());
        setCancha(CanchaEntity.obtenerCanchaDefecto());
        setFechaReserva(UtilFecha.obtenerValorDefecto((LocalDate) null));
        setFechaUsoCancha(UtilFecha.obtenerValorDefecto((LocalDate) null));
        setHoraInicio(LocalTime.MIDNIGHT);
        setHoraFin(LocalTime.MIDNIGHT);
        setEstado(EstadoReservaEntity.obtenerEstadoReservaDefecto());
    }

    public ReservaEntity(final UUID id) {
        this();
        setId(id);
    }

    public ReservaEntity(final UUID id, final ClienteEntity cliente, final CanchaEntity cancha,
                         final LocalDate fechaReserva, final LocalDate fechaUsoCancha,
                         final LocalTime horaInicio, final LocalTime horaFin,
                         final EstadoReservaEntity estado) {
        setId(id);
        setCliente(cliente);
        setCancha(cancha);
        setFechaReserva(fechaReserva);
        setFechaUsoCancha(fechaUsoCancha);
        setHoraInicio(horaInicio);
        setHoraFin(horaFin);
        setEstado(estado);
    }

    public static ReservaEntity obtenerReservaDefecto() {
        return new ReservaEntity();
    }

    public static ReservaEntity obtenerValorDefecto(final ReservaEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, obtenerReservaDefecto());
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(final ClienteEntity cliente) {
        this.cliente = ClienteEntity.obtenerValorDefecto(cliente);
    }

    public CanchaEntity getCancha() {
        return cancha;
    }

    public void setCancha(final CanchaEntity cancha) {
        this.cancha = CanchaEntity.obtenerValorDefecto(cancha);
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(final LocalDate fechaReserva) {
        this.fechaReserva = UtilFecha.obtenerValorDefecto(fechaReserva);
    }

    public LocalDate getFechaUsoCancha() {
        return fechaUsoCancha;
    }

    public void setFechaUsoCancha(final LocalDate fechaUsoCancha) {
        this.fechaUsoCancha = UtilFecha.obtenerValorDefecto(fechaUsoCancha);
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

    public EstadoReservaEntity getEstado() {
        return estado;
    }

    public void setEstado(final EstadoReservaEntity estado) {
        this.estado = EstadoReservaEntity.obtenerValorDefecto(estado);
    }
}

