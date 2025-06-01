package co.edu.uco.backend.businesslogic.businesslogic.impl;

import co.edu.uco.backend.businesslogic.businesslogic.ResenaBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.ResenaDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.entity.ResenaEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ResenaBusinessLogicImpl implements ResenaBusinessLogic {

    private DAOFactory factory;
    public ResenaBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevaResena(UUID reserva, ResenaDomain resena) throws BackEndException {
        ResenaEntity resenaEntity = null;
        //TODO: Validar reserva
        factory.getResenaDAO().crear(resenaEntity);
    }

    @Override
    public void modificarResenaExistente(UUID reservaId, UUID resenaId, ResenaDomain resena) throws BackEndException {
        ResenaEntity resenaEntity = null;
        //TODO: Validar reserva
        factory.getResenaDAO().modificar(resenaId, resenaEntity);
    }

    @Override
    public void darBajaDefinitivamenteResenaExistente(UUID reservaId, UUID resenaId) throws BackEndException {
        ResenaEntity resenaEntity = null;
        //TODO: Validar reserva
        factory.getResenaDAO().eliminar(resenaId);
    }

    @Override
    public ResenaDomain consultarResenaPorReserva(UUID reservaId, UUID resenaId) throws BackEndException {
        ResenaEntity resenaEntity = null;
        //TODO: Validar reserva
        factory.getResenaDAO().consultarPorId(resenaId);
        return null;
    }

    @Override
    public List<ResenaDomain> consultarResenas(UUID reservaId, ResenaDomain filtro) throws BackEndException {
        ResenaEntity resenaFilter = null;
        List<ResenaEntity> resenaEntities = factory.getResenaDAO().consultar(resenaFilter);
        List<ResenaDomain> datosARetornar = null;
        return datosARetornar;
    }
}
