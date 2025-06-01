package co.edu.uco.backend.businesslogic.facade;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.EncargadoDTO;

import java.util.List;
import java.util.UUID;

public interface EncargadoFacade extends UsuarioFacade {

    void registrarNuevoEncargado(UUID orgId, EncargadoDTO domain) throws BackEndException;

    void modificarEncargadoExistente(UUID orgId, UUID encargadoID, EncargadoDTO domain) throws BackEndException;

    void darBajaDefinitivamenteEncargadoExistente(UUID orgId, UUID encargadoId) throws BackEndException;

    EncargadoDTO consultarEncargadoPorId(UUID orgId, UUID encargadoId) throws BackEndException;

    List<EncargadoDTO> consultarEncargadosPorOrganizacion(UUID orgId, EncargadoDTO filtro) throws BackEndException;

    String activarCuentaEncargado(String tokenDeActivacion, String rawPasswordNueva);

}
