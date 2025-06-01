package co.edu.uco.backend.businesslogic.facade;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.CanchaDTO;

import java.util.List;
import java.util.UUID;

public interface CanchaFacade {

    void registrarNuevaCancha(UUID orgId, CanchaDTO cancha) throws BackEndException;

    void modificarCanchaExistente(UUID orgId, UUID canchaId, CanchaDTO cancha) throws BackEndException;

    void darBajaDefinitivamenteCanchaExistente(UUID orgId, UUID canchaId) throws BackEndException;

    CanchaDTO consultarCanchaPorOrganizacion(UUID orgId, UUID canchaId) throws BackEndException;

    List<CanchaDTO> listarCanchasPorOrganizacion(UUID orgId, CanchaDTO filtro) throws BackEndException;

    CanchaDTO consultarCanchaPorId(UUID canchaId) throws BackEndException;

    List<CanchaDTO> consultarTodasCanchas(CanchaDTO filtro) throws BackEndException;

}
