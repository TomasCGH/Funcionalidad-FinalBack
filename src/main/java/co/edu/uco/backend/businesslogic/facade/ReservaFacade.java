package co.edu.uco.backend.businesslogic.facade;

import co.edu.uco.backend.dto.ReservaDTO;

import java.util.List;
import java.util.UUID;

public interface ReservaFacade {

    void registrarNuevaReserva(UUID clienteID, ReservaDTO reserva);

    void confirmarReserva(UUID clienteId, UUID idReserva, ReservaDTO reserva);

    void cancelarReservaPorCliente(UUID clienteId, UUID reservaId, ReservaDTO reserva);

    ReservaDTO consultarReservaPorCliente(UUID clienteId, UUID reservaId);

    List<ReservaDTO> listarReservasPorCliente(UUID clienteId, ReservaDTO filtro);

    void finalizarReserva(UUID clienteId, UUID reservaId);

    void cancelarReservaPorOrganizacion(UUID orgId, UUID reservaId);

    List<ReservaDTO> listarReservasPorCancha(UUID orgId, UUID canchaId);

}
