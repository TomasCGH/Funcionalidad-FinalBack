package co.edu.uco.backend.businesslogic.facade.impl;

import co.edu.uco.backend.businesslogic.businesslogic.impl.EstadoReservaBusinessLogicImpl;
import co.edu.uco.backend.businesslogic.businesslogic.EstadoReservaBusinessLogic;
import co.edu.uco.backend.businesslogic.facade.EstadoReservaFacade;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.data.dao.factory.Factory;
import co.edu.uco.backend.dto.EstadoReservaDTO;

import java.util.List;
import java.util.UUID;

public class EstadoReservaFacadeImpl implements EstadoReservaFacade {

    private DAOFactory daoFactory;
    private EstadoReservaBusinessLogic estadoReservaBusinessLogic;

    public EstadoReservaFacadeImpl() throws BackEndException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        estadoReservaBusinessLogic = new EstadoReservaBusinessLogicImpl(daoFactory);
    }

    @Override
    public void registrarNuevoEstadoReserva(EstadoReservaDTO estadoReserva) {

    }

    @Override
    public void modificarEstadoReservaExistente(UUID estadoReservaId, EstadoReservaDTO estadoReserva) {

    }

    @Override
    public void darBajaDefinitivamenteEstadoReservaExistente(UUID estadoReservaId) {

    }


    @Override
    public EstadoReservaDTO consultarEstadoReservaPorId(UUID estadoReservaId) {
        return null;
    }

    @Override
    public List<EstadoReservaDTO> consultarEstadoReservas(EstadoReservaDTO filtro) {
        return List.of();
    }


}
