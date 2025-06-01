package co.edu.uco.backend.businesslogic.businesslogic.impl;

import co.edu.uco.backend.businesslogic.businesslogic.SuperficieBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.SuperficieDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.entity.SuperficieEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SuperficieBusinessLogicImpl implements SuperficieBusinessLogic {

    private DAOFactory factory;
    public SuperficieBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoSuperficie(SuperficieDomain superficie) throws BackEndException {
        SuperficieEntity superficieEntity = null;
        factory.getSuperficieDAO().crear(superficieEntity);
    }

    @Override
    public void modificarSuperficieExistente(UUID superficieId, SuperficieDomain superficie) throws BackEndException {
        SuperficieEntity superficieEntity = null;
        factory.getSuperficieDAO().modificar(superficieId,superficieEntity);
    }

    @Override
    public void darBajaDefinitivamenteSuperficieExistente(UUID superficieId) throws BackEndException {
        SuperficieEntity superficieEntity = null;
        factory.getSuperficieDAO().eliminar(superficieId);
    }

    @Override
    public SuperficieDomain consultarSuperficiePorId(UUID superficieId) throws BackEndException {
        SuperficieEntity superficieEntity = null;
        factory.getSuperficieDAO().consultarPorId(superficieId);
        return null;
    }

    @Override
    public List<SuperficieDomain> consultarSuperficies(SuperficieDomain filtro) throws BackEndException {
        SuperficieEntity superficieFilter = null;
        List<SuperficieEntity> superficieEntities = factory.getSuperficieDAO().consultar(superficieFilter);
        List<SuperficieDomain> datosARetornar = null;
        return datosARetornar;
    }
}
