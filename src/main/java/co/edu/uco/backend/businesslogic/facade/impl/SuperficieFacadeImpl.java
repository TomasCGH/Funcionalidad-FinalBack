package co.edu.uco.backend.businesslogic.facade.impl;

import co.edu.uco.backend.businesslogic.businesslogic.domain.OrganizacionDeportivaDomain;
import co.edu.uco.backend.businesslogic.businesslogic.domain.SuperficieDomain;
import co.edu.uco.backend.businesslogic.businesslogic.impl.SuperficieBusinessLogicImpl;
import co.edu.uco.backend.businesslogic.businesslogic.SuperficieBusinessLogic;
import co.edu.uco.backend.businesslogic.facade.SuperficieFacade;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.data.dao.factory.Factory;
import co.edu.uco.backend.dto.SuperficieDTO;

import java.util.List;
import java.util.UUID;

public class SuperficieFacadeImpl implements SuperficieFacade {

    private DAOFactory daoFactory;
    private SuperficieBusinessLogic superficieBusinessLogic;

    public SuperficieFacadeImpl() throws BackEndException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        superficieBusinessLogic = new SuperficieBusinessLogicImpl(daoFactory);
    }

    @Override
    public void registrarNuevoSuperficie(SuperficieDTO superficie) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            SuperficieDomain superficieDomain= null; //TODO: magia de convertir de DTO a Domain
            superficieBusinessLogic.registrarNuevoSuperficie(superficieDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de registrar la informacion de la nueva superficie, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registrar la informacion de la nueva superficie  ";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void modificarSuperficieExistente(UUID superficieId, SuperficieDTO superficie) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            SuperficieDomain superficieDomain= null; //TODO: magia de convertir de DTO a Domain
            superficieBusinessLogic.modificarSuperficieExistente(superficieId, superficieDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de modificar la informacion de la superficie, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la informacion de la superficie  ";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void darBajaDefinitivamenteSuperficieExistente(UUID superficieId) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            SuperficieDomain superficieDomain= null; //TODO: magia de convertir de DTO a Domain
            superficieBusinessLogic.darBajaDefinitivamenteSuperficieExistente(superficieId);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de modificar la informacion de la superficie, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la informacion de la superficie  ";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public SuperficieDTO consultarSuperficiePorId(UUID superficieId) throws BackEndException {
        try {
            var superficieDomainResultado = superficieBusinessLogic.consultarSuperficiePorId(superficieId);
            //TODO: Magia de convertir de domain a DTO de respuesta
            return null;
        } catch (BackEndException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de consultar la informacion de la superficie con el identificador deseado, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de consultar la informacion de la superficie con el identificador deseado...";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<SuperficieDTO> consultarSuperficies(SuperficieDTO filtro) throws BackEndException {
        try {
            // 1. TODO: convertir DTO(filtro) -> Domain
            // CanchaDomain filtroDomain = CanchaAssembler.toDomain(filtro);
            SuperficieDomain filtroDomain = null;
            // 2. Consultar lista global de canchas
            List<SuperficieDomain> dominios = superficieBusinessLogic.consultarSuperficies(filtroDomain);
            // 3. TODO: convertir cada Domain -> DTO
            // return dominios.stream().map(CanchaAssembler::toDto).collect(Collectors.toList());
            return List.of();
        } catch (BackEndException ex) {
            throw ex;
        } catch (Exception ex) {
            var mensajeUsuario = "Se ha presentado un problema inesperado al consultar todos las superficies";
            var mensajeTecnico = "Excepción inesperada listando todas las superficies";
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
