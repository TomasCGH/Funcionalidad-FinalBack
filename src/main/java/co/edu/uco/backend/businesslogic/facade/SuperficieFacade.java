package co.edu.uco.backend.businesslogic.facade;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.SuperficieDTO;

import java.util.List;
import java.util.UUID;

public interface SuperficieFacade {

    void registrarNuevoSuperficie(SuperficieDTO superficie) throws BackEndException;

    void modificarSuperficieExistente(UUID id, SuperficieDTO superficie) throws BackEndException;

    void darBajaDefinitivamenteSuperficieExistente(UUID id) throws BackEndException;

    SuperficieDTO consultarSuperficiePorId(UUID id) throws BackEndException;

    List<SuperficieDTO> consultarSuperficies(SuperficieDTO filtro) throws BackEndException;

}
