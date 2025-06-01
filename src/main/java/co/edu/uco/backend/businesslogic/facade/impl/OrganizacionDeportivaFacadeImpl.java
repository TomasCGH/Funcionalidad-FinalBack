package co.edu.uco.backend.businesslogic.facade.impl;

import co.edu.uco.backend.businesslogic.businesslogic.domain.OrganizacionDeportivaDomain;
import co.edu.uco.backend.businesslogic.businesslogic.impl.OrganizacionDeportivaBusinessLogicImpl;
import co.edu.uco.backend.businesslogic.businesslogic.OrganizacionDeportivaBusinessLogic;
import co.edu.uco.backend.businesslogic.facade.OrganizacionDeportivaFacade;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.data.dao.factory.Factory;
import co.edu.uco.backend.dto.OrganizacionDeportivaDTO;
import co.edu.uco.backend.dto.UsuarioDTO;

import java.util.List;
import java.util.UUID;

public class OrganizacionDeportivaFacadeImpl implements OrganizacionDeportivaFacade {

    private DAOFactory daoFactory;
    private OrganizacionDeportivaBusinessLogic organizacionDeportivaBusinessLogic;

    public OrganizacionDeportivaFacadeImpl() throws BackEndException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        organizacionDeportivaBusinessLogic = new OrganizacionDeportivaBusinessLogicImpl(daoFactory);
    }


    @Override
    public void registrarNuevaOrganizacionDeportiva(OrganizacionDeportivaDTO organizacionDeportiva) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            OrganizacionDeportivaDomain organizacionDeportivaDomain= null; //TODO: magia de convertir de DTO a Domain
            organizacionDeportivaBusinessLogic.registrarNuevaOrganizacionDeportiva(organizacionDeportivaDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de registrar la informacion de la nueva organizacion deportiva, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registrar la informacion de la nueva organizacion deportiva ";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void modificarOrganizacionDeportivaExistente(UUID orgId, OrganizacionDeportivaDTO organizacionDeportiva) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            OrganizacionDeportivaDomain organizacionDeportivaDomain= null; //TODO: magia de convertir de DTO a Domain
            organizacionDeportivaBusinessLogic.modificarOrganizacionDeportivaExistente(orgId, organizacionDeportivaDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de modificar la informacion de la organizacion deportiva, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la informacion de la organizacion deportiva ";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void darBajaDefinitivamenteOrganizacionDeportivaExistente(UUID orgId) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            OrganizacionDeportivaDomain organizacionDeportivaDomain= null; //TODO: magia de convertir de DTO a Domain
            organizacionDeportivaBusinessLogic.darBajaDefinitivamenteOrganizacionDeportivaExistente(orgId);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de modificar la informacion de la organizacion deportiva, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la informacion de la organizacion deportiva ";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public OrganizacionDeportivaDTO consultarOrganizacionDeportivaPorId(UUID orgId) throws BackEndException {
        try {
            var organizacionDeportivaDomainResultado = organizacionDeportivaBusinessLogic.consultarOrganizacionDeportivaPorId(orgId);
            //TODO: Magia de convertir de domain a DTO de respuesta
            return null;
        } catch (BackEndException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de consultar la informacion de la organizacion deportiva con el identificador deseado, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de consultar la informacion de la organizacion deportiva con el identificador deseado...";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<OrganizacionDeportivaDTO> consultarOrganizacionesDeportivas(OrganizacionDeportivaDTO filtro) throws BackEndException {
        try {
            // 1. TODO: convertir DTO(filtro) -> Domain
            // CanchaDomain filtroDomain = CanchaAssembler.toDomain(filtro);
            OrganizacionDeportivaDomain filtroDomain = null;
            // 2. Consultar lista global de canchas
            List<OrganizacionDeportivaDomain> dominios = organizacionDeportivaBusinessLogic.consultarOrganizacionesDeportivas(filtroDomain);
            // 3. TODO: convertir cada Domain -> DTO
            // return dominios.stream().map(CanchaAssembler::toDto).collect(Collectors.toList());
            return List.of();
        } catch (BackEndException ex) {
            throw ex;
        } catch (Exception ex) {
            var mensajeUsuario = "Se ha presentado un problema inesperado al consultar todos las organizaciones deportivas";
            var mensajeTecnico = "Excepción inesperada listando todas las organizaciones deportivas";
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void aceptarOrganizacion(UUID orgId) {

    }

    @Override
    public void rechazarOrganizacion(UUID orgId) {

    }

    @Override
    public UsuarioDTO iniciarSesion(String username, String rawPassword, String ipAdress, String userAgent) {
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




