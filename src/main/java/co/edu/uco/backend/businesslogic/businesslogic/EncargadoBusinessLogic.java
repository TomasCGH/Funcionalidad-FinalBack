package co.edu.uco.backend.businesslogic.businesslogic;

import co.edu.uco.backend.businesslogic.businesslogic.domain.EncargadoDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

import java.util.List;
import java.util.UUID;

/**
 * Define las operaciones de negocio para el módulo Encargado.
 * Extiende UsuarioBusinessLogic para heredar métodos generales de usuario.
 */
public interface EncargadoBusinessLogic extends UsuarioBusinessLogic {

    /**
     * Registra un nuevo Encargado en la capa de negocio.
     * @param encargado datos del Encargado a crear
     * @throws BackEndException en caso de error de validación o DAO
     */
    void registrarNuevoEncargado(EncargadoDomain encargado) throws BackEndException;

    /**
     * Modifica un Encargado existente.
     * @param encargadoId UUID del Encargado a modificar
     * @param encargado datos actualizados del Encargado
     * @throws BackEndException en caso de no encontrar el ID o falla de validación
     */
    void modificarEncargadoExistente(UUID encargadoId, EncargadoDomain encargado) throws BackEndException;

    /**
     * Elimina definitivamente el Encargado con el UUID dado.
     * @param encargadoId UUID del Encargado a eliminar
     * @throws BackEndException si no existe o falla DAO
     */
    void darBajaDefinitivamenteEncargadoExistente(UUID encargadoId) throws BackEndException;

    /**
     * Consulta un Encargado por su UUID.
     * @param encargadoId UUID a buscar
     * @return EncargadoDomain con la información del Encargado
     * @throws BackEndException si no existe o falla DAO
     */
    EncargadoDomain consultarEncargadoPorId(UUID encargadoId) throws BackEndException;

    /**
     * Consulta todos los Encargados que cumplan con los criterios en filtro.
     * @param filtro un EncargadoDomain con los campos a filtrar (por ejemplo: nombre, username, etc.)
     * @return lista de EncargadoDomain que coinciden con el filtro
     * @throws BackEndException si falla DAO
     */
    List<EncargadoDomain> consultarEncargados(EncargadoDomain filtro) throws BackEndException;
}
