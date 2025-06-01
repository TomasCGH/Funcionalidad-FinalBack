package co.edu.uco.backend.dto;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilFecha;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public final class ReservaDTO {

    private UUID id;
    private ClienteDTO cliente;
    private CanchaDTO cancha;
    private LocalDate fechaReserva;
    private LocalDate fechaUsoCancha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private EstadoReservaDTO estado;

    public ReservaDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setCliente(ClienteDTO.obtenerValorDefecto());
        setCancha(CanchaDTO.obtenerValorDefecto());
        setFechaReserva(UtilFecha.obtenerValorDefecto((LocalDate) null));
        setFechaUsoCancha(UtilFecha.obtenerValorDefecto((LocalDate) null));
        setHoraInicio(UtilObjeto.getInstance().obtenerValorDefecto(null, LocalTime.MIDNIGHT));
        setHoraFin(UtilObjeto.getInstance().obtenerValorDefecto(null, LocalTime.MIDNIGHT));
        setEstado(EstadoReservaDTO.obtenerValorDefecto());
    }

    public ReservaDTO(final UUID id) {
        this();
        setId(id);
    }

    public ReservaDTO(
            final UUID id,
            final ClienteDTO cliente,
            final CanchaDTO cancha,
            final LocalDate fechaReserva,
            final LocalDate fechaUsoCancha,
            final LocalTime horaInicio,
            final LocalTime horaFin,
            final EstadoReservaDTO estado
    ) {
        setId(id);
        setCliente(cliente);
        setCancha(cancha);
        setFechaReserva(fechaReserva);
        setFechaUsoCancha(fechaUsoCancha);
        setHoraInicio(horaInicio);
        setHoraFin(horaFin);
        setEstado(estado);
    }

    public static ReservaDTO obtenerValorDefecto() {
        return new ReservaDTO();
    }

    public static ReservaDTO obtenerValorDefecto(final ReservaDTO dto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(dto, obtenerValorDefecto());
    }

    public UUID getId() {
        return id;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public CanchaDTO getCancha() {
        return cancha;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public LocalDate getFechaUsoCancha() {
        return fechaUsoCancha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public EstadoReservaDTO getEstado() {
        return estado;
    }

    public ReservaDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public ReservaDTO setCliente(final ClienteDTO cliente) {
        this.cliente = ClienteDTO.obtenerValorDefecto(cliente);
        return this;
    }

    public ReservaDTO setCancha(final CanchaDTO cancha) {
        this.cancha = CanchaDTO.obtenerValorDefecto(cancha);
        return this;
    }

    public ReservaDTO setFechaReserva(final LocalDate fechaReserva) {
        this.fechaReserva = UtilFecha.obtenerValorDefecto(fechaReserva);
        return this;
    }

    public ReservaDTO setFechaUsoCancha(final LocalDate fechaUsoCancha) {
        this.fechaUsoCancha = UtilFecha.obtenerValorDefecto(fechaUsoCancha);
        return this;
    }

    public ReservaDTO setHoraInicio(final LocalTime horaInicio) {
        this.horaInicio = UtilObjeto.getInstance().obtenerValorDefecto(horaInicio, LocalTime.MIDNIGHT);
        return this;
    }

    public ReservaDTO setHoraFin(final LocalTime horaFin) {
        this.horaFin = UtilObjeto.getInstance().obtenerValorDefecto(horaFin, LocalTime.MIDNIGHT);
        return this;
    }

    public ReservaDTO setEstado(final EstadoReservaDTO estado) {
        this.estado = EstadoReservaDTO.obtenerValorDefecto(estado);
        return this;
    }
}
