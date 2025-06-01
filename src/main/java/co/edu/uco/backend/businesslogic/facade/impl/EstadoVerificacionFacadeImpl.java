package co.edu.uco.backend.businesslogic.facade.impl;

import co.edu.uco.backend.businesslogic.businesslogic.impl.EstadoVerificacionBusinessLogicImpl;
import co.edu.uco.backend.businesslogic.businesslogic.EstadoVerificacionBusinessLogic;
import co.edu.uco.backend.businesslogic.facade.EstadoVerificacionFacade;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.data.dao.factory.Factory;
import co.edu.uco.backend.dto.EstadoVerificacionDTO;

import java.util.List;
import java.util.UUID;

public class EstadoVerificacionFacadeImpl implements EstadoVerificacionFacade {

    private DAOFactory daoFactory;
    private EstadoVerificacionBusinessLogic estadoVerificacionBusinessLogic;

    public EstadoVerificacionFacadeImpl() throws BackEndException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        estadoVerificacionBusinessLogic = new EstadoVerificacionBusinessLogicImpl(daoFactory);
    }

    @Override
    public void registrarNuevoEstadoVerificacion(EstadoVerificacionDTO dto) {

    }

    @Override
    public void modificarEstadoVerificacionExistente(UUID estadoVerificacionId, EstadoVerificacionDTO estadoVerificacion) {

    }

    @Override
    public void darBajaDefinitivamenteEstadoVerificacionExistente(UUID estadoVerificacionId) {

    }


    @Override
    public EstadoVerificacionDTO consultarEstadoVerificacionPorId(UUID id) {
        return null;
    }

    @Override
    public List<EstadoVerificacionDTO> consultarEstadoVerificacions(EstadoVerificacionDTO filtro) {
        return List.of();
    }


}
