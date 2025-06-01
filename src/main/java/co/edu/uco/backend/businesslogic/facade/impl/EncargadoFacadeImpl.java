package co.edu.uco.backend.businesslogic.facade.impl;

import co.edu.uco.backend.businesslogic.businesslogic.domain.EncargadoDomain;
import co.edu.uco.backend.businesslogic.businesslogic.impl.EncargadoBusinessLogicImpl;
import co.edu.uco.backend.businesslogic.businesslogic.EncargadoBusinessLogic;
import co.edu.uco.backend.businesslogic.facade.EncargadoFacade;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.data.dao.factory.Factory;
import co.edu.uco.backend.dto.EncargadoDTO;
import co.edu.uco.backend.dto.UsuarioDTO;

import java.util.List;
import java.util.UUID;

public class EncargadoFacadeImpl implements EncargadoFacade {

    private DAOFactory daoFactory;
    private EncargadoBusinessLogic encargadoBusinessLogic;

    public EncargadoFacadeImpl() throws BackEndException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        encargadoBusinessLogic = new EncargadoBusinessLogicImpl(daoFactory);
    }


    @Override
    public void registrarNuevoEncargado(UUID orgId, EncargadoDTO domain) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            EncargadoDomain encargadoDomain= null; //TODO: magia de convertir de DTO a Domain
            encargadoBusinessLogic.registrarNuevoEncargado(orgId,encargadoDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de registrar la informacion del nuevo encargado, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registrar la informacion del nuevo encargado";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }

    }

    @Override
    public void modificarEncargadoExistente(UUID orgId, UUID encargadoID, EncargadoDTO domain) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            EncargadoDomain encargadoDomain= null; //TODO: magia de convertir de DTO a Domain
            encargadoBusinessLogic.modificarEncargadoExistente(orgId,encargadoID,encargadoDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de modificar la informacion del encargado, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la informacion del encargado";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }

    }

    @Override
    public void darBajaDefinitivamenteEncargadoExistente(UUID orgId, UUID encargadoId) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            EncargadoDomain encargadoDomain= null; //TODO: magia de convertir de DTO a Domain
            encargadoBusinessLogic.darBajaDefinitivamenteEncargadoExistente(orgId,encargadoId);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de registrar la informacion del nuevo encargado, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registrar la informacion del nuevo encargado";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }

    }

    @Override
    public EncargadoDTO consultarEncargadoPorId(UUID orgId, UUID encargadoId) throws BackEndException {
        try {
            var encargadoDomainResultado = encargadoBusinessLogic.consultarEncargadoPorId(orgId,encargadoId);;
            //TODO: Magia de convertir de domain a DTO de respuesta
            return null;
        } catch (BackEndException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de consultar la informacion de la dimension con el identificador deseado, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de consultar la informacion de la dimension con el identificador deseado...";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<EncargadoDTO> consultarEncargadosPorOrganizacion(UUID orgId, EncargadoDTO filtro) throws BackEndException {
        try {
            // 1. TODO: convertir DTO(filtro) -> Domain
            // CanchaDomain filtroDomain = CanchaAssembler.toDomain(filtro);
            EncargadoDomain filtroDomain = null;
            // 2. Consultar lista de domains
            List<EncargadoDomain> dominios = encargadoBusinessLogic.consultarEncargadosPorOrganizacion(orgId, filtroDomain);
            // 3. TODO: convertir cada Domain -> DTO
            // return dominios.stream().map(CanchaAssembler::toDto).collect(Collectors.toList());
            return List.of();
        } catch (BackEndException ex) {
            throw ex;
        } catch (Exception ex) {
            var mensajeUsuario = "Se ha presentado un problema inesperado al listar encargados de la organización";
            var mensajeTecnico = "Excepción inesperada listando encargados de org " + orgId;
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public String activarCuentaEncargado(String tokenDeActivacion, String rawPasswordNueva) {
        return "";
    }

    @Override
    public UsuarioDTO iniciarSesion(String username, String rawPassword,String ipAdress, String userAgent) {
        return null;
    }

    @Override
    public void cerrarSesion(UUID usuarioId) {

    }

    @Override
    public void recuperarContrasena(String username) {

    }

    @Override
    public void cambiarContrasena(UUID usuarioId, String rawPasswordActual, String rawPasswordNueva) {

    }

    @Override
    public UsuarioDTO consultarUsuarioPorId(UUID usuarioId) {
        return null;
    }

    @Override
    public List<UsuarioDTO> listarUsuarios(UsuarioDTO filtro) {
        return List.of();
    }

}
