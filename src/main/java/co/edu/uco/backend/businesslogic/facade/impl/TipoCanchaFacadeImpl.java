package co.edu.uco.backend.businesslogic.facade.impl;

import co.edu.uco.backend.businesslogic.businesslogic.domain.TipoCanchaDomain;
import co.edu.uco.backend.businesslogic.businesslogic.impl.TipoCanchaBusinessLogicImpl;
import co.edu.uco.backend.businesslogic.businesslogic.TipoCanchaBusinessLogic;
import co.edu.uco.backend.businesslogic.facade.TipoCanchaFacade;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.data.dao.factory.Factory;
import co.edu.uco.backend.dto.TipoCanchaDTO;

import java.util.List;
import java.util.UUID;

public class TipoCanchaFacadeImpl implements TipoCanchaFacade {

    private DAOFactory daoFactory;
    private TipoCanchaBusinessLogic tipoCanchaBusinessLogic;

    public TipoCanchaFacadeImpl() throws BackEndException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        tipoCanchaBusinessLogic = new TipoCanchaBusinessLogicImpl(daoFactory);
    }


    @Override
    public void registrarNuevoTipoCancha(TipoCanchaDTO tipoCancha) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            TipoCanchaDomain tipoCanchaDomain= null; //TODO: magia de convertir de DTO a Domain
            tipoCanchaBusinessLogic.registrarNuevoTipoCancha(tipoCanchaDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de registrar la informacion del nuevo tipo de cancha , para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registrar la informacion del nuevo tipo de cancha ";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void modificarTipoCanchaExistente(UUID tipoCanchaId, TipoCanchaDTO tipoCancha) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            TipoCanchaDomain tipoCanchaDomain= null; //TODO: magia de convertir de DTO a Domain
            tipoCanchaBusinessLogic.modificarTipoCanchaExistente(tipoCanchaId, tipoCanchaDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de modificar la informacion del tipo de cancha , para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la informacion del tipo de cancha ";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void darBajaDefinitivamenteTipoCanchaExistente(UUID tipoCanchaId) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            TipoCanchaDomain tipoCanchaDomain= null; //TODO: magia de convertir de DTO a Domain
            tipoCanchaBusinessLogic.darBajaDefinitivamenteTipoCanchaExistente(tipoCanchaId);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de modificar la informacion del tipo de cancha , para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la informacion del tipo de cancha ";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public TipoCanchaDTO consultarTipoCanchaPorId(UUID tipoCanchaId) throws BackEndException {
        try {
            var tipoCanchaDomainResultado = tipoCanchaBusinessLogic.consultarTipoCanchaPorId(tipoCanchaId);
            //TODO: Magia de convertir de domain a DTO de respuesta
            return null;
        } catch (BackEndException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de consultar la informacion del tipo de cancha con el identificador deseado, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de consultar la informacion del tipo de cancha con el identificador deseado...";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<TipoCanchaDTO> consultarTipoCanchas(TipoCanchaDTO filtro) throws BackEndException {
        try {
            // 1. TODO: convertir DTO(filtro) -> Domain
            // CanchaDomain filtroDomain = CanchaAssembler.toDomain(filtro);
            TipoCanchaDomain filtroDomain = null;
            // 2. Consultar lista global de canchas
            List<TipoCanchaDomain> dominios = tipoCanchaBusinessLogic.consultarTipoCanchas(filtroDomain);
            // 3. TODO: convertir cada Domain -> DTO
            // return dominios.stream().map(CanchaAssembler::toDto).collect(Collectors.toList());
            return List.of();
        } catch (BackEndException ex) {
            throw ex;
        } catch (Exception ex) {
            var mensajeUsuario = "Se ha presentado un problema inesperado al consultar todos los tipos de cancha";
            var mensajeTecnico = "Excepción inesperada listando todas los tipos de cancha";
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}

