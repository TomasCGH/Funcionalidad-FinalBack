package co.edu.uco.backend.businesslogic.facade;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.EncargadoDTO;

import java.util.List;
import java.util.UUID;

/**
 * Facade para el módulo Encargado.
 * Extiende UsuarioFacade y expone operaciones CRUD específicas para Encargado.
 */
public interface EncargadoFacade extends UsuarioFacade {

    void registrarNuevoEncargado(EncargadoDTO encargado) throws BackEndException;

    void modificarEncargadoExistente(UUID encargadoId, EncargadoDTO encargado) throws BackEndException;

    void darBajaDefinitivamenteEncargadoExistente(UUID encargadoId) throws BackEndException;

    EncargadoDTO consultarEncargadoPorId(UUID encargadoId) throws BackEndException;

    List<EncargadoDTO> consultarEncargados(EncargadoDTO filtro) throws BackEndException;

}
