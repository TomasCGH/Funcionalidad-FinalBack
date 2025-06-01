package co.edu.uco.backend.businesslogic.businesslogic.impl;

import co.edu.uco.backend.businesslogic.businesslogic.EstadoReservaBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.EstadoReservaDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.entity.EstadoReservaEntity;

import java.util.List;
import java.util.UUID;

public class EstadoReservaBusinessLogicImpl implements EstadoReservaBusinessLogic {

    private DAOFactory factory;
    public EstadoReservaBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoEstadoReserva(EstadoReservaDomain estadoReserva) throws BackEndException {
        EstadoReservaEntity estadoReservaEntity = null;
        factory.getEstadoReservaDAO().crear(estadoReservaEntity);

    }

    @Override
    public void modificarEstadoReservaExistente(UUID estadoReservaId, EstadoReservaDomain estadoReserva) throws BackEndException {
        EstadoReservaEntity estadoReservaEntity = null;
        factory.getEstadoReservaDAO().modificar(estadoReservaId,estadoReservaEntity);
    }

    @Override
    public void darBajaDefinitivamenteEstadoReservaExistente(UUID estadoReservaId) throws BackEndException {
        EstadoReservaEntity estadoReservaEntity = null;
        factory.getEstadoReservaDAO().eliminar(estadoReservaId);
    }

    @Override
    public EstadoReservaDomain consultarEstadoReservaPorId(UUID estadoReservaId) throws BackEndException {
        EstadoReservaEntity estadoReservaEntity = null;
        factory.getEstadoReservaDAO().consultarPorId(estadoReservaId);
        return null;
    }

    @Override
    public List<EstadoReservaDomain> consultarEstadoReservas(EstadoReservaDomain filtro) throws BackEndException {
        EstadoReservaEntity estadoReservaFilter = null;
        List<EstadoReservaEntity> estadoReservaEntities = factory.getEstadoReservaDAO().consultar(estadoReservaFilter);
        List<EstadoReservaDomain> datosARetornar = null;
        return datosARetornar;
    }
}
