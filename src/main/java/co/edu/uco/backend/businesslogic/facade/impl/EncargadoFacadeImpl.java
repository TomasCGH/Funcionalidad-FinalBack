package co.edu.uco.backend.businesslogic.facade.impl;

import co.edu.uco.backend.businesslogic.assembler.encargado.dto.EncargadoDTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.EncargadoDomain;
import co.edu.uco.backend.businesslogic.businesslogic.EncargadoBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.impl.EncargadoBusinessLogicImpl;
import co.edu.uco.backend.businesslogic.facade.EncargadoFacade;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.data.dao.factory.Factory;
import co.edu.uco.backend.dto.EncargadoDTO;
import co.edu.uco.backend.dto.UsuarioDTO;

import java.util.List;
import java.util.UUID;

/**
 * Implementación de EncargadoFacade.
 * Sigue la misma estructura que ClienteFacadeImpl, adaptada para Encargado.
 */
public class EncargadoFacadeImpl implements EncargadoFacade {

    private final DAOFactory daoFactory;
    private final EncargadoBusinessLogic encargadoBusinessLogic;

    public EncargadoFacadeImpl() throws BackEndException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        encargadoBusinessLogic = new EncargadoBusinessLogicImpl(daoFactory);
    }

    @Override
    public void registrarNuevoEncargado(EncargadoDTO encargado) throws BackEndException {
        daoFactory.abrirConexion();
        try {
            daoFactory.iniciarTransaccion();

            EncargadoDomain domain = EncargadoDTOAssembler.getInstance().toDomain(encargado);
            encargadoBusinessLogic.registrarNuevoEncargado(domain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada tratando de registrar la información del nuevo Encargado.";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registrar el Encargado.";
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void modificarEncargadoExistente(UUID encargadoId, EncargadoDTO encargado) throws BackEndException {
        daoFactory.abrirConexion();
        try {
            daoFactory.iniciarTransaccion();

            EncargadoDomain domain = EncargadoDTOAssembler.getInstance().toDomain(encargado);
            encargadoBusinessLogic.modificarEncargadoExistente(encargadoId, domain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada tratando de modificar la información del Encargado.";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar el Encargado.";
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void darBajaDefinitivamenteEncargadoExistente(UUID encargadoId) throws BackEndException {
        daoFactory.abrirConexion();
        try {
            daoFactory.iniciarTransaccion();

            encargadoBusinessLogic.darBajaDefinitivamenteEncargadoExistente(encargadoId);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada tratando de eliminar la información del Encargado.";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de eliminar el Encargado.";
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public EncargadoDTO consultarEncargadoPorId(UUID encargadoId) throws BackEndException {
        daoFactory.abrirConexion();
        try {
            var domainResultado = encargadoBusinessLogic.consultarEncargadoPorId(encargadoId);
            return EncargadoDTOAssembler.getInstance().toDTO(domainResultado);
        } catch (BackEndException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción inesperada tratando de consultar la información del Encargado con el identificador deseado.";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de consultar el Encargado con el identificador deseado.";
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<EncargadoDTO> consultarEncargados(EncargadoDTO filtro) throws BackEndException {
        daoFactory.abrirConexion();
        try {
            EncargadoDomain filtroDomain = EncargadoDTOAssembler.getInstance().toDomain(filtro);
            List<EncargadoDomain> dominios = encargadoBusinessLogic.consultarEncargados(filtroDomain);
            return EncargadoDTOAssembler.getInstance().toDTOs(dominios);
        } catch (BackEndException ex) {
            throw ex;
        } catch (Exception ex) {
            var mensajeUsuario = "Se ha presentado un problema inesperado al consultar todos los Encargados.";
            var mensajeTecnico = "Excepción inesperada listando todos los Encargados.";
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public UsuarioDTO iniciarSesion(String username, String rawPassword, String ipAddress, String agentUser) {
        // Implementación pendiente o no requerida para Encargado
        return null;
    }

    @Override
    public void cerrarSesion(UUID usuarioId) {
        // Implementación pendiente o no requerida para Encargado
    }

    @Override
    public void recuperarContrasena(String username) {
        // Implementación pendiente o no requerida para Encargado
    }

    @Override
    public void cambiarContrasena(UUID usuarioId, String rawPasswordActual, String rawPasswordNueva) {
        // Implementación pendiente o no requerida para Encargado
    }

    @Override
    public UsuarioDTO consultarUsuarioPorId(UUID usuarioId) {
        // Puede reutilizar consultarEncargadoPorId o devolver null si no aplica
        return null;
    }

    @Override
    public List<UsuarioDTO> listarUsuarios(UsuarioDTO filtro) {
        // No aplica para Encargado; retornar lista vacía o lanzar excepción si se prefiere
        return List.of();
    }
}
