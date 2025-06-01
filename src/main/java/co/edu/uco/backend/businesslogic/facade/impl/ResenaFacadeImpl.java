package co.edu.uco.backend.businesslogic.facade.impl;

import co.edu.uco.backend.businesslogic.businesslogic.impl.ResenaBusinessLogicImpl;
import co.edu.uco.backend.businesslogic.businesslogic.ResenaBusinessLogic;
import co.edu.uco.backend.businesslogic.facade.ResenaFacade;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.data.dao.factory.Factory;
import co.edu.uco.backend.dto.ResenaDTO;

import java.util.List;
import java.util.UUID;

public class ResenaFacadeImpl implements ResenaFacade {

    private DAOFactory daoFactory;
    private ResenaBusinessLogic resenaBusinessLogic;

    public ResenaFacadeImpl() throws BackEndException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        resenaBusinessLogic = new ResenaBusinessLogicImpl(daoFactory);
    }


    @Override
    public void registrarNuevaResena(UUID reserva, ResenaDTO resena) {

    }

    @Override
    public void modificarResenaExistente(UUID reservaId, UUID resenaId, ResenaDTO resena) {

    }

    @Override
    public void darBajaDefinitivamenteResenaExistente(UUID reservaId, UUID resenaId) {

    }

    @Override
    public ResenaDTO consultarResenaPorReserva(UUID reservaId, UUID resenaId) {
        return null;
    }

    @Override
    public List<ResenaDTO> consultarResenas(UUID reservaId, ResenaDTO filtro) {
        return List.of();
    }
}
