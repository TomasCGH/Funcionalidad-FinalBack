package co.edu.uco.backend.businesslogic.facade.impl;

import co.edu.uco.backend.businesslogic.businesslogic.impl.ReservaBusinessLogicImpl;
import co.edu.uco.backend.businesslogic.businesslogic.ReservaBusinessLogic;
import co.edu.uco.backend.businesslogic.facade.ReservaFacade;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.data.dao.factory.Factory;
import co.edu.uco.backend.dto.ReservaDTO;

import java.util.List;
import java.util.UUID;

public class ReservaFacadeImpl implements ReservaFacade {

    private DAOFactory daoFactory;
    private ReservaBusinessLogic reservaBusinessLogic;

    public ReservaFacadeImpl() throws BackEndException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        reservaBusinessLogic = new ReservaBusinessLogicImpl(daoFactory);
    }


    @Override
    public void registrarNuevaReserva(UUID clienteID, ReservaDTO reserva) {

    }

    @Override
    public void confirmarReserva(UUID clienteId, UUID idReserva, ReservaDTO reserva) {

    }

    @Override
    public void cancelarReservaPorCliente(UUID clienteId, UUID reservaId, ReservaDTO reserva) {

    }

    @Override
    public ReservaDTO consultarReservaPorCliente(UUID clienteId, UUID reservaId) {
        return null;
    }

    @Override
    public List<ReservaDTO> listarReservasPorCliente(UUID clienteId, ReservaDTO filtro) {
        return List.of();
    }

    @Override
    public void finalizarReserva(UUID clienteId, UUID reservaId) {

    }

    @Override
    public void cancelarReservaPorOrganizacion(UUID orgId, UUID reservaId) {

    }

    @Override
    public List<ReservaDTO> listarReservasPorCancha(UUID orgId, UUID canchaId) {
        return List.of();
    }
}
