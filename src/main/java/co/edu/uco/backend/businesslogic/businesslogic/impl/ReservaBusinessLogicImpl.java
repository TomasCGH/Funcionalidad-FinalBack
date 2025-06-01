package co.edu.uco.backend.businesslogic.businesslogic.impl;

import co.edu.uco.backend.businesslogic.businesslogic.ReservaBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.ReservaDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.entity.ReservaEntity;

import java.util.List;
import java.util.UUID;

public class ReservaBusinessLogicImpl implements ReservaBusinessLogic {

    private DAOFactory factory;
    public ReservaBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevaReserva(UUID clienteID, ReservaDomain reserva) throws BackEndException {
        ReservaEntity reservaEntity = null;
        //TODO: Validar cliente
        factory.getReservaDAO().crear(reservaEntity);
    }

    @Override
    public void confirmarReserva(UUID clienteId, UUID idReserva, ReservaDomain reserva) throws BackEndException {
        ReservaEntity reservaEntity = null;
        //TODO: Validar cliente
        factory.getReservaDAO().modificar(idReserva,reservaEntity);
    }


    @Override
    public void cancelarReservaPorCliente(UUID clienteId, UUID reservaId, ReservaDomain reserva) throws BackEndException {
        ReservaEntity reservaEntity = null;
        //TODO: Validar cliente
        factory.getReservaDAO().modificar(reservaId,reservaEntity);
    }

    @Override
    public ReservaDomain consultarReservaPorCliente(UUID clienteId, UUID reservaId) throws BackEndException {
        ReservaEntity reservaEntity = null;
        //TODO: Validar cliente
        factory.getReservaDAO().consultarPorId(reservaId);
        return null;
    }

    @Override
    public List<ReservaDomain> listarReservasPorCliente(UUID clienteId, ReservaDomain filtro) throws BackEndException {
        ReservaEntity reservaFilter = null;
        //TODO: Validar cliente
        List<ReservaEntity> reservaEntities = factory.getReservaDAO().consultar(reservaFilter);
        List<ReservaDomain> datosARetornar = null;
        return datosARetornar;
    }

    @Override
    public void finalizarReserva(UUID clienteId, UUID reservaId) {
        //TODO: Implementar logica
    }


    @Override
    public void cancelarReservaPorOrganizacion(UUID orgId, UUID reservaId) throws BackEndException {
        ReservaEntity reservaEntity = null;
        //TODO: Validar organizacion y encargado
        factory.getReservaDAO().modificar(reservaId,reservaEntity);
    }

    @Override
    public List<ReservaDomain> listarReservasPorCancha(UUID orgId, UUID canchaId) throws BackEndException {
        ReservaEntity reservaFilter = null;
        //TODO: Validar organizacion y cancha
        List<ReservaEntity> reservaEntities = factory.getReservaDAO().consultar(reservaFilter);
        List<ReservaDomain> datosARetornar = null;
        return datosARetornar;
    }


}
