package co.edu.uco.backend.businesslogic.businesslogic.impl;

import co.edu.uco.backend.businesslogic.businesslogic.HorarioEspecialBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.HorarioEspecialDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.entity.HorarioEspecialEntity;

import java.util.List;
import java.util.UUID;

public class HorarioEspecialBusinessLogicImpl implements HorarioEspecialBusinessLogic {

    private DAOFactory factory;
    public HorarioEspecialBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoHorarioEspecial(UUID canchaId, HorarioEspecialDomain horarioEspecial) throws BackEndException {
        HorarioEspecialEntity horarioEspecialEntity = null;
        //TODO: Validar cancha
        factory.getHorarioEspecialDAO().crear(horarioEspecialEntity);
    }

    @Override
    public void modificarHorarioEspecialExistente(UUID canchaId, UUID horarioEspecialId, HorarioEspecialDomain horarioEspecial) throws BackEndException {
        HorarioEspecialEntity horarioEspecialEntity = null;
        //TODO: Validar cancha
        factory.getHorarioEspecialDAO().modificar(horarioEspecialId,horarioEspecialEntity);
    }


    @Override
    public void darBajaDefinitivamenteHorarioEspecialExistente(UUID canchaId, UUID horarioEspecialId) throws BackEndException {
        HorarioEspecialEntity horarioEspecialEntity = null;
        //TODO: Validar cancha
        factory.getHorarioEspecialDAO().eliminar(horarioEspecialId);
    }

    @Override
    public HorarioEspecialDomain consultarHorarioEspecialPorId(UUID canchaId, UUID horarioEspecialId) throws BackEndException {
        HorarioEspecialEntity horarioEspecialEntity = null;
        //TODO: Validar cancha
        factory.getHorarioEspecialDAO().consultarPorId(horarioEspecialId);
        return null;
    }

    @Override
    public List<HorarioEspecialDomain> consultarHorariosEspecialesPorCancha(UUID canchaId, HorarioEspecialDomain horarioEspecial) throws BackEndException {
        HorarioEspecialEntity horarioEspecialFilter = null;
        List<HorarioEspecialEntity> horarioEspecialEntities = factory.getHorarioEspecialDAO().consultar(horarioEspecialFilter);
        List<HorarioEspecialDomain> datosARetornar = null;
        return datosARetornar;
    }

}
