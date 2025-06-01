package co.edu.uco.backend.businesslogic.businesslogic;

import co.edu.uco.backend.businesslogic.businesslogic.domain.EstadoReservaDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

import java.util.List;
import java.util.UUID;

public interface EstadoReservaBusinessLogic {

    void registrarNuevoEstadoReserva(EstadoReservaDomain estadoReserva) throws BackEndException;

    void modificarEstadoReservaExistente(UUID estadoReservaId, EstadoReservaDomain estadoReserva) throws BackEndException;

    void darBajaDefinitivamenteEstadoReservaExistente(UUID estadoReservaId) throws BackEndException;

    EstadoReservaDomain consultarEstadoReservaPorId(UUID estadoReservaId) throws BackEndException;

    List<EstadoReservaDomain> consultarEstadoReservas(EstadoReservaDomain filtro) throws BackEndException;

}
