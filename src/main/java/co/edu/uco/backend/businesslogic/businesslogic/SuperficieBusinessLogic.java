package co.edu.uco.backend.businesslogic.businesslogic;

import co.edu.uco.backend.businesslogic.businesslogic.domain.SuperficieDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

import java.util.List;
import java.util.UUID;

public interface SuperficieBusinessLogic {

    void registrarNuevoSuperficie(SuperficieDomain superficie) throws BackEndException;

    void modificarSuperficieExistente(UUID id, SuperficieDomain superficie) throws BackEndException;

    void darBajaDefinitivamenteSuperficieExistente(UUID id) throws BackEndException;

    SuperficieDomain consultarSuperficiePorId(UUID id) throws BackEndException;

    List<SuperficieDomain> consultarSuperficies(SuperficieDomain filtro) throws BackEndException;

}
