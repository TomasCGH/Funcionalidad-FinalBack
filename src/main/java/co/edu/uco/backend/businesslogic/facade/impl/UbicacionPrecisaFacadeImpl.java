package co.edu.uco.backend.businesslogic.facade.impl;

import co.edu.uco.backend.businesslogic.businesslogic.domain.UbicacionPrecisaDomain;
import co.edu.uco.backend.businesslogic.businesslogic.impl.UbicacionPrecisaBusinessLogicImpl;
import co.edu.uco.backend.businesslogic.businesslogic.UbicacionPrecisaBusinessLogic;
import co.edu.uco.backend.businesslogic.facade.UbicacionPrecisaFacade;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.data.dao.factory.Factory;
import co.edu.uco.backend.dto.UbicacionPrecisaDTO;

import java.util.UUID;

public class UbicacionPrecisaFacadeImpl implements UbicacionPrecisaFacade {

    private DAOFactory daoFactory;
    private UbicacionPrecisaBusinessLogic ubicacionPrecisaBusinessLogic;

    public UbicacionPrecisaFacadeImpl() throws BackEndException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        ubicacionPrecisaBusinessLogic = new UbicacionPrecisaBusinessLogicImpl(daoFactory);
    }


    @Override
    public void registrarNuevaUbicacionPrecisa(UUID canchaId, UbicacionPrecisaDTO ubicacionPrecisa) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            UbicacionPrecisaDomain ubicacionPrecisaDomain= null; //TODO: magia de convertir de DTO a Domain
            ubicacionPrecisaBusinessLogic.registrarNuevaUbicacionPrecisa(canchaId,ubicacionPrecisaDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de registrar la informacion de ubicacion precisa, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registrar la informacion de la ubicacion precisa";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void modificarUbicacionPrecisaExistente(UUID canchaId, UUID ubicacionPrecisaId, UbicacionPrecisaDTO ubicacionPrecisa) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            UbicacionPrecisaDomain ubicacionPrecisaDomain= null; //TODO: magia de convertir de DTO a Domain
            ubicacionPrecisaBusinessLogic.modificarUbicacionPrecisaExistente(canchaId,ubicacionPrecisaId,ubicacionPrecisaDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de registrar la informacion de ubicacion precisa, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registrar la informacion de la ubicacion precisa";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void eliminarUbicacionPrecisa(UUID canchaId, UUID ubicacionPrecisaId) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            UbicacionPrecisaDomain ubicacionPrecisaDomain= null; //TODO: magia de convertir de DTO a Domain
            ubicacionPrecisaBusinessLogic.eliminarUbicacionPrecisa(canchaId,ubicacionPrecisaId);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de eliminar la informacion de la ubicacion precisa, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de eliminar la informacion de la ubicacion precisa";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public UbicacionPrecisaDTO consultarUbicacionPrecisaPorId(UUID canchaId, UUID ubicacionPrecisaId) throws BackEndException {
        try {
            var ubicacionPrecisaDomainResultado = ubicacionPrecisaBusinessLogic.consultarUbicacionPrecisaPorId(canchaId,ubicacionPrecisaId);;
            //TODO: Magia de convertir de domain a DTO de respuesta
            return null;
        } catch (BackEndException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de consultar la informacion de la ubicacion precisa con el identificador deseado, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de consultar la informacion de la ubicacion precisa con el identificador deseado...";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
