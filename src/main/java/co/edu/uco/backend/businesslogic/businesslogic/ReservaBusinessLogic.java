package co.edu.uco.backend.businesslogic.businesslogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.ReservaDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

import java.util.List;
import java.util.UUID;

public interface ReservaBusinessLogic {

    void registrarNuevaReserva(UUID clienteID, ReservaDomain reserva) throws BackEndException;

    void confirmarReserva(UUID clienteId, UUID idReserva, ReservaDomain reserva) throws BackEndException;

    void cancelarReservaPorCliente(UUID clienteId, UUID reservaId, ReservaDomain reserva) throws BackEndException;

    ReservaDomain consultarReservaPorCliente(UUID clienteId, UUID reservaId) throws BackEndException;

    List<ReservaDomain> listarReservasPorCliente(UUID clienteId, ReservaDomain filtro) throws BackEndException;

    void finalizarReserva(UUID clienteId, UUID reservaId);

    void cancelarReservaPorOrganizacion(UUID orgId, UUID reservaId) throws BackEndException;

    List<ReservaDomain> listarReservasPorCancha(UUID orgId, UUID canchaId) throws BackEndException;


}
