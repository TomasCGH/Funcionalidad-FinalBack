package co.edu.uco.backend.businesslogic.facade.impl;

import co.edu.uco.backend.businesslogic.businesslogic.domain.DimensionDomain;
import co.edu.uco.backend.businesslogic.businesslogic.impl.DimensionBusinessLogicImpl;
import co.edu.uco.backend.businesslogic.businesslogic.DimensionBusinessLogic;
import co.edu.uco.backend.businesslogic.facade.DimensionFacade;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.data.dao.factory.Factory;
import co.edu.uco.backend.dto.DimensionDTO;


import java.util.UUID;

public class DimensionFacadeImpl implements DimensionFacade {

    private DAOFactory daoFactory;
    private DimensionBusinessLogic dimensionBusinessLogic;

    public DimensionFacadeImpl() throws BackEndException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        dimensionBusinessLogic = new DimensionBusinessLogicImpl(daoFactory);
    }


    @Override
    public void registrarNuevaDimension(UUID canchaId, DimensionDTO dimension) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            DimensionDomain dimensionDomain= null; //TODO: magia de convertir de DTO a Domain
            dimensionBusinessLogic.registrarNuevaDimension(canchaId,dimensionDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de registrar la informacion de la dimension, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registrar la informacion de la dimension";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void modificarDimensionExistente(UUID canchaId, UUID dimensionId, DimensionDTO dimension) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            DimensionDomain dimensionDomain= null; //TODO: magia de convertir de DTO a Domain
            dimensionBusinessLogic.modificarDimensionExistente(canchaId,dimensionId,dimensionDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de registrar la informacion de la dimension, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registrar la informacion de la dimension";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void eliminarDimension(UUID canchaId, UUID dimensionId) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            DimensionDomain dimensionDomain= null; //TODO: magia de convertir de DTO a Domain
            dimensionBusinessLogic.eliminarDimension(canchaId,dimensionId);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de eliminar la informacion de la dimension, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de eliminar la informacion de la dimension";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public DimensionDTO consultarDimensionPorId(UUID canchaId, UUID dimensionId) throws BackEndException {
        try {
            var departamentoDomainResultado = dimensionBusinessLogic.consultarDimensionPorId(canchaId,dimensionId);;
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


}
