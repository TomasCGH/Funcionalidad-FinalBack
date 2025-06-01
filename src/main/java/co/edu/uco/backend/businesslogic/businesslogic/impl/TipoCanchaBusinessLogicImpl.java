package co.edu.uco.backend.businesslogic.businesslogic.impl;

import co.edu.uco.backend.businesslogic.businesslogic.TipoCanchaBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.TipoCanchaDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.entity.TipoCanchaEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TipoCanchaBusinessLogicImpl implements TipoCanchaBusinessLogic {

    private DAOFactory factory;
    public TipoCanchaBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoTipoCancha(TipoCanchaDomain tipoCancha) throws BackEndException {
        TipoCanchaEntity tipoCanchaEntity = null;
        factory.getTipoCanchaDAO().crear(tipoCanchaEntity);
    }

    @Override
    public void modificarTipoCanchaExistente(UUID tipoCanchaId, TipoCanchaDomain tipoCancha) throws BackEndException {
        TipoCanchaEntity tipoCanchaEntity = null;
        factory.getTipoCanchaDAO().modificar(tipoCanchaId,tipoCanchaEntity);
    }

    @Override
    public void darBajaDefinitivamenteTipoCanchaExistente(UUID tipoCanchaId) throws BackEndException {
        TipoCanchaEntity tipoCanchaEntity = null;
        factory.getTipoCanchaDAO().eliminar(tipoCanchaId);
    }

    @Override
    public TipoCanchaDomain consultarTipoCanchaPorId(UUID tipoCanchaId) throws BackEndException {
        TipoCanchaEntity tipoCanchaEntity = null;
        factory.getTipoCanchaDAO().consultarPorId(tipoCanchaId);
        return null;
    }

    @Override
    public List<TipoCanchaDomain> consultarTipoCanchas(TipoCanchaDomain filtro) throws BackEndException {
        TipoCanchaEntity tipoCanchaFilter = null;
        List<TipoCanchaEntity> tipoCanchaEntities = factory.getTipoCanchaDAO().consultar(tipoCanchaFilter);
        List<TipoCanchaDomain> datosARetornar = null;
        return datosARetornar;
    }
}
