package co.edu.uco.backend.businesslogic.businesslogic;

import co.edu.uco.backend.businesslogic.businesslogic.domain.ResenaDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

import java.util.List;
import java.util.UUID;

public interface ResenaBusinessLogic {

    void registrarNuevaResena(UUID reserva, ResenaDomain resena) throws BackEndException;

    void modificarResenaExistente(UUID reservaId, UUID resenaId, ResenaDomain resena) throws BackEndException;

    void darBajaDefinitivamenteResenaExistente(UUID reservaId, UUID resenaId) throws BackEndException;

    ResenaDomain consultarResenaPorReserva(UUID reservaId, UUID resenaId) throws BackEndException;

    List<ResenaDomain> consultarResenas(UUID reservaId, ResenaDomain filtro) throws BackEndException;

}
