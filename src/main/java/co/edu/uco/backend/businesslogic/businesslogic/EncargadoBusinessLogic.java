package co.edu.uco.backend.businesslogic.businesslogic;

import co.edu.uco.backend.businesslogic.businesslogic.domain.EncargadoDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

import java.util.List;
import java.util.UUID;

public interface EncargadoBusinessLogic extends UsuarioBusinessLogic {

    void registrarNuevoEncargado(UUID orgId, EncargadoDomain domain) throws BackEndException;

    void modificarEncargadoExistente(UUID orgId, UUID encargadoID, EncargadoDomain domain) throws BackEndException;

    void darBajaDefinitivamenteEncargadoExistente(UUID orgId, UUID encargadoId) throws BackEndException;

    EncargadoDomain consultarEncargadoPorId(UUID orgId, UUID encargadoId) throws BackEndException;

    List<EncargadoDomain> consultarEncargadosPorOrganizacion(UUID orgId, EncargadoDomain filtro) throws BackEndException;

    String activarCuentaEncargado(String tokenDeActivacion, String rawPasswordNueva);

}
