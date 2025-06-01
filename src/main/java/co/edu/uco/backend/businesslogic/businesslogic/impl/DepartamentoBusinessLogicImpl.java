package co.edu.uco.backend.businesslogic.businesslogic.impl;

import co.edu.uco.backend.businesslogic.businesslogic.DepartamentoBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.DepartamentoDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.entity.DepartamentoEntity;

import java.util.List;
import java.util.UUID;

public class DepartamentoBusinessLogicImpl implements DepartamentoBusinessLogic {

    private DAOFactory factory;
    public DepartamentoBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoDepartamento(DepartamentoDomain domain) throws BackEndException {
        DepartamentoEntity departamentoEntity = null;
        factory.getDepartamentoDAO().crear(departamentoEntity);
    }

    @Override
    public void modificarDepartamentoExistente(UUID departamentoId, DepartamentoDomain domain) throws BackEndException {
        DepartamentoEntity departamentoEntity = null;
        factory.getDepartamentoDAO().modificar(departamentoId,departamentoEntity);
    }

    @Override
    public void darBajaDefinitivamenteDepartamentoExistente(UUID departamentoId) throws BackEndException {
        DepartamentoEntity departamentoEntity = null;
        factory.getDepartamentoDAO().eliminar(departamentoId);
    }

    @Override
    public DepartamentoDomain consultarDepartamentoPorId(UUID departamentoId) throws BackEndException {
        DepartamentoEntity departamentoEntity = null;
        factory.getDepartamentoDAO().consultarPorId(departamentoId);
        return null;
    }

    @Override
    public List<DepartamentoDomain> consultarDepartamentos(DepartamentoDomain filtro) throws BackEndException {
        DepartamentoEntity departamentoFilter = null;
        List<DepartamentoEntity> departamentoEntities = factory.getDepartamentoDAO().consultar(departamentoFilter);
        List<DepartamentoDomain> datosARetornar = null;

        return datosARetornar;
    }
}
