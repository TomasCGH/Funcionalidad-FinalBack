package co.edu.uco.backend.businesslogic.facade;

import co.edu.uco.backend.dto.ResenaDTO;

import java.util.List;
import java.util.UUID;

public interface ResenaFacade {

    void registrarNuevaResena(UUID reserva, ResenaDTO resena);

    void modificarResenaExistente(UUID reservaId, UUID resenaId, ResenaDTO resena);

    void darBajaDefinitivamenteResenaExistente(UUID reservaId, UUID resenaId);

    ResenaDTO consultarResenaPorReserva(UUID reservaId, UUID resenaId);

    List<ResenaDTO> consultarResenas(UUID reservaId, ResenaDTO filtro);

}
