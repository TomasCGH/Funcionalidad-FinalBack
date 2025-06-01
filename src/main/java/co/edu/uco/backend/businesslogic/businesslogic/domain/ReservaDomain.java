package co.edu.uco.backend.businesslogic.businesslogic.domain;

import co.edu.uco.backend.crosscutting.utilitarios.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public final class ReservaDomain {

    private UUID id;
    private ClienteDomain cliente;
    private CanchaDomain cancha;
    private LocalDate fechaReserva;
    private LocalDate fechaUsoCancha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private EstadoReservaDomain estado;

    ReservaDomain() {
        setId(UtilUUID.obtenerValorDefecto());
        setCliente(ClienteDomain.obtenerClienteDefecto());
        setCancha(CanchaDomain.obtenerCanchaDefecto());
        setFechaReserva(UtilFecha.obtenerValorDefecto((LocalDate) null));
        setFechaUsoCancha(UtilFecha.obtenerValorDefecto((LocalDate) null));
        setHoraInicio(LocalTime.MIDNIGHT);
        setHoraFin(LocalTime.MIDNIGHT);
        setEstado(EstadoReservaDomain.obtenerEstadoReservaDefecto());
    }

    public ReservaDomain(final UUID id) {
        this();
        setId(id);
    }

    public ReservaDomain(final UUID id, final ClienteDomain cliente, final CanchaDomain cancha,
                         final LocalDate fechaReserva, final LocalDate fechaUsoCancha,
                         final LocalTime horaInicio, final LocalTime horaFin,
                         final EstadoReservaDomain estado) {
        setId(id);
        setCliente(cliente);
        setCancha(cancha);
        setFechaReserva(fechaReserva);
        setFechaUsoCancha(fechaUsoCancha);
        setHoraInicio(horaInicio);
        setHoraFin(horaFin);
        setEstado(estado);
    }

    static ReservaDomain obtenerReservaDefecto() {
        return new ReservaDomain();
    }

    static ReservaDomain obtenerValorDefecto(final ReservaDomain domain) {
        return UtilObjeto.getInstance().obtenerValorDefecto(domain, obtenerReservaDefecto());
    }

    public UUID getId() {
        return id;
    }

    private void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public ClienteDomain getCliente() {
        return cliente;
    }

    private void setCliente(final ClienteDomain cliente) {
        this.cliente = ClienteDomain.obtenerValorDefecto(cliente);
    }

    public CanchaDomain getCancha() {
        return cancha;
    }

    private void setCancha(final CanchaDomain cancha) {
        this.cancha = CanchaDomain.obtenerValorDefecto(cancha);
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    private void setFechaReserva(final LocalDate fechaReserva) {
        this.fechaReserva = UtilFecha.obtenerValorDefecto(fechaReserva);
    }

    public LocalDate getFechaUsoCancha() {
        return fechaUsoCancha;
    }

    private void setFechaUsoCancha(final LocalDate fechaUsoCancha) {
        this.fechaUsoCancha = UtilFecha.obtenerValorDefecto(fechaUsoCancha);
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

    public EstadoReservaDomain getEstado() {
        return estado;
    }

    private void setEstado(final EstadoReservaDomain estado) {
        this.estado = EstadoReservaDomain.obtenerValorDefecto(estado);
    }
}
