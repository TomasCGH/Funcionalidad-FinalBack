package co.edu.uco.backend.businesslogic.facade.impl;

import co.edu.uco.backend.businesslogic.businesslogic.domain.CanchaDomain;
import co.edu.uco.backend.businesslogic.businesslogic.impl.CanchaBusinessLogicImpl;
import co.edu.uco.backend.businesslogic.businesslogic.CanchaBusinessLogic;
import co.edu.uco.backend.businesslogic.facade.CanchaFacade;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.data.dao.factory.Factory;
import co.edu.uco.backend.dto.CanchaDTO;

import java.util.List;
import java.util.UUID;

public class CanchaFacadeImpl implements CanchaFacade {

    private DAOFactory daoFactory;
    private CanchaBusinessLogic canchaBusinessLogic;

    public CanchaFacadeImpl() throws BackEndException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        canchaBusinessLogic = new CanchaBusinessLogicImpl(daoFactory);
    }


    @Override
    public void registrarNuevaCancha(UUID orgId, CanchaDTO cancha) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            CanchaDomain canchaDomain = null; //TODO: magia de convertir de DTO a Domain
            canchaBusinessLogic.registrarNuevaCancha(orgId, canchaDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de registrar la informacion de la nueva cancha, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registrar la informacion de la nueva cancha";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }

    }

    @Override
    public void modificarCanchaExistente(UUID orgId, UUID canchaId, CanchaDTO cancha) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            CanchaDomain canchaDomain = null; //TODO: magia de convertir de DTO a Domain
            canchaBusinessLogic.modificarCanchaExistente(orgId, canchaId, canchaDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de modificar la informacion de la cancha, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la informacion cancha";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }

    }

    @Override
    public void darBajaDefinitivamenteCanchaExistente(UUID orgId, UUID canchaId) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            CanchaDomain canchaDomain = null; //TODO: magia de convertir de DTO a Domain
            canchaBusinessLogic.darBajaDefinitivamenteCanchaExistente(orgId, canchaId);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de eliminar la informacion de la cancha, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de eliminar la informacion de la cancha";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public CanchaDTO consultarCanchaPorOrganizacion(UUID orgId, UUID canchaId) throws BackEndException {
        try {
            // 1. Consultar el domain
            CanchaDomain canchaDomain = canchaBusinessLogic.consultarCanchaPorOrganizacion(orgId, canchaId);
            // 2. TODO: convertir Domain -> DTO
            // CanchaDTO dto = CanchaAssembler.toDto(canchaDomain);
            return null;
        } catch (BackEndException ex) {
            throw ex;
        } catch (Exception ex) {
            var mensajeUsuario = "Se ha presentado un problema inesperado al consultar la cancha de la organización";
            var mensajeTecnico = "Excepción inesperada consultando cancha " + canchaId + " de org " + orgId;
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<CanchaDTO> listarCanchasPorOrganizacion(UUID orgId, CanchaDTO filtro) throws BackEndException {
        try {
            // 1. TODO: convertir DTO(filtro) -> Domain
            // CanchaDomain filtroDomain = CanchaAssembler.toDomain(filtro);
            CanchaDomain filtroDomain = null;
            // 2. Consultar lista de domains
            List<CanchaDomain> dominios = canchaBusinessLogic.listarCanchasPorOrganizacion(orgId, filtroDomain);
            // 3. TODO: convertir cada Domain -> DTO
            // return dominios.stream().map(CanchaAssembler::toDto).collect(Collectors.toList());
            return List.of();
        } catch (BackEndException ex) {
            throw ex;
        } catch (Exception ex) {
            var mensajeUsuario = "Se ha presentado un problema inesperado al listar canchas de la organización";
            var mensajeTecnico = "Excepción inesperada listando canchas de org " + orgId;
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public CanchaDTO consultarCanchaPorId(UUID canchaId) throws BackEndException {
        try {
            var canchaDomainResultado = canchaBusinessLogic.consultarCanchaPorId(canchaId);;
            //TODO: Magia de convertir de domain a DTO de respuesta
            return null;
        } catch (BackEndException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de consultar la informacion de la cancha con el identificador deseado, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de consultar la informacion de la cancha con el identificador deseado...";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }

    }

    @Override
    public List<CanchaDTO> consultarTodasCanchas(CanchaDTO filtro) throws BackEndException {
        try {
            // 1. TODO: convertir DTO(filtro) -> Domain
            // CanchaDomain filtroDomain = CanchaAssembler.toDomain(filtro);
            CanchaDomain filtroDomain = null;
            // 2. Consultar lista global de canchas
            List<CanchaDomain> dominios = canchaBusinessLogic.consultarTodasCanchas(filtroDomain);
            // 3. TODO: convertir cada Domain -> DTO
            // return dominios.stream().map(CanchaAssembler::toDto).collect(Collectors.toList());
            return List.of();
        } catch (BackEndException ex) {
            throw ex;
        } catch (Exception ex) {
            var mensajeUsuario = "Se ha presentado un problema inesperado al consultar todas las canchas";
            var mensajeTecnico = "Excepción inesperada listando todas las canchas";
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        } finally {
            daoFactory.cerrarConexion();
        }
    }


}
