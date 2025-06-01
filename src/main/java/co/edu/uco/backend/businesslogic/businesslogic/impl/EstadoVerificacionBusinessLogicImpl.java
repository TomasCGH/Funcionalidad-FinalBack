package co.edu.uco.backend.businesslogic.businesslogic.impl;

import co.edu.uco.backend.businesslogic.businesslogic.EstadoVerificacionBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.EstadoVerificacionDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.entity.EstadoVerificacionEntity;

import java.util.List;
import java.util.UUID;

public class EstadoVerificacionBusinessLogicImpl implements EstadoVerificacionBusinessLogic {

    private DAOFactory factory;
    public EstadoVerificacionBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoEstadoVerificacion(EstadoVerificacionDomain estadoVerificacion) throws BackEndException {
        EstadoVerificacionEntity estadoVerificacionEntity = null;
        factory.getEstadoVerificacionDAO().crear(estadoVerificacionEntity);
    }

    @Override
    public void modificarEstadoVerificacionExistente(UUID estadoVerificacionId, EstadoVerificacionDomain estadoVerificacion) throws BackEndException {
        EstadoVerificacionEntity estadoVerificacionEntity = null;
        factory.getEstadoVerificacionDAO().modificar(estadoVerificacionId, estadoVerificacionEntity);
    }

    @Override
    public void darBajaDefinitivamenteEstadoVerificacionExistente(UUID estadoVerificacionId) throws BackEndException {
        EstadoVerificacionEntity estadoVerificacionEntity = null;
        factory.getEstadoVerificacionDAO().eliminar(estadoVerificacionId);
    }

    @Override
    public EstadoVerificacionDomain consultarEstadoVerificacionPorId(UUID estadoVerificacionId) throws BackEndException {
        EstadoVerificacionEntity estadoVerificacionEntity = null;
        factory.getEstadoVerificacionDAO().consultarPorId(estadoVerificacionId);
        return null;
    }

    @Override
    public List<EstadoVerificacionDomain> consultarEstadoVerificacions(EstadoVerificacionDomain filtro) throws BackEndException {
        EstadoVerificacionEntity estadoVerificacionFilter = null;
        List<EstadoVerificacionEntity> estadoVerificacionEntities = factory.getEstadoVerificacionDAO().consultar(estadoVerificacionFilter);
        List<EstadoVerificacionDomain> datosARetornar = null;

        return datosARetornar;
    }
}
