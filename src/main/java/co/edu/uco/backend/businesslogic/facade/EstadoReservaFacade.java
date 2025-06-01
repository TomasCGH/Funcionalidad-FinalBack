package co.edu.uco.backend.businesslogic.facade;

import co.edu.uco.backend.dto.EstadoReservaDTO;

import java.util.List;
import java.util.UUID;

public interface EstadoReservaFacade {

    void registrarNuevoEstadoReserva(EstadoReservaDTO estadoReserva);

    void modificarEstadoReservaExistente(UUID estadoReservaId, EstadoReservaDTO estadoReserva);

    void darBajaDefinitivamenteEstadoReservaExistente(UUID estadoReservaId);

    EstadoReservaDTO consultarEstadoReservaPorId(UUID estadoReservaId);

    List<EstadoReservaDTO> consultarEstadoReservas(EstadoReservaDTO filtro);

}

