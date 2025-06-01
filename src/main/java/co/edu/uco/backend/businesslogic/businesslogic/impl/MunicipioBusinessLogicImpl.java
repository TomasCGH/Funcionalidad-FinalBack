package co.edu.uco.backend.businesslogic.businesslogic.impl;

import co.edu.uco.backend.businesslogic.businesslogic.MunicipioBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.MunicipioDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.entity.MunicipioEntity;

import java.util.List;
import java.util.UUID;

public class MunicipioBusinessLogicImpl implements MunicipioBusinessLogic {

    private DAOFactory factory;
    public MunicipioBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoMunicipio(UUID departamentoId, MunicipioDomain municipio) throws BackEndException {
        MunicipioEntity municipioEntity = null;
        //TODO: Validar departamento
        factory.getMunicipioDAO().crear(municipioEntity);
    }

    @Override
    public void modificarMunicipioExistente(UUID departamentoId, UUID municipioId, MunicipioDomain municipio) throws BackEndException {
        MunicipioEntity municipioEntity = null;
        //TODO: Validar departamento
        factory.getMunicipioDAO().modificar(municipioId,municipioEntity);
    }

    @Override
    public void darBajaDefinitivamenteMunicipioExistente(UUID departamentoId, UUID municipioId) throws BackEndException {
        MunicipioEntity municipioEntity = null;
        //TODO: Validar departamento
        factory.getMunicipioDAO().eliminar(municipioId);
    }

    @Override
    public MunicipioDomain consultarMunicipioPorId(UUID departamentoId, UUID municipioId) throws BackEndException {
        MunicipioEntity municipioEntity = null;
        //TODO: Validar departamento
        factory.getMunicipioDAO().consultarPorId(municipioId);
        return null;
    }

    @Override
    public List<MunicipioDomain> consultarMunicipios(UUID departamentoID, MunicipioDomain filtro) throws BackEndException {
        MunicipioEntity municipioFilter = null;
        List<MunicipioEntity> municipioEntities = factory.getMunicipioDAO().consultar(municipioFilter);
        List<MunicipioDomain> datosARetornar = null;
        return datosARetornar;
    }
}
