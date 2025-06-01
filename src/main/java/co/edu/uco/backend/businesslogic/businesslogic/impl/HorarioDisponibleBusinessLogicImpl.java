package co.edu.uco.backend.businesslogic.businesslogic.impl;

import co.edu.uco.backend.businesslogic.businesslogic.HorarioDisponibleBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.HorarioDisponibleDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.entity.HorarioDisponibleEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HorarioDisponibleBusinessLogicImpl implements HorarioDisponibleBusinessLogic {

    private DAOFactory factory;
    public HorarioDisponibleBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;

    }

    @Override
    public void registrarNuevoHorarioDisponible(UUID canchaId, HorarioDisponibleDomain horarioDisponible) throws BackEndException {
        HorarioDisponibleEntity horarioDisponibleEntity = null;
        //TODO: Validar cancha
        factory.getHorarioDisponibleDAO().crear(horarioDisponibleEntity);
    }

    @Override
    public void modificarHorarioDisponibleExistente(UUID canchaId, UUID horarioDisponinbleId, HorarioDisponibleDomain horarioDisponible) throws BackEndException {
        HorarioDisponibleEntity horarioDisponibleEntity = null;
        //TODO: Validar cancha
        factory.getHorarioDisponibleDAO().modificar(horarioDisponinbleId, horarioDisponibleEntity);
    }


    @Override
    public void darBajaDefinitivamenteHorarioDisponibleExistente(UUID canchaId, UUID horarioDisponibleId) throws BackEndException {
        HorarioDisponibleEntity horarioDisponibleEntity = null;
        //TODO: Validar cancha
        factory.getHorarioDisponibleDAO().eliminar(horarioDisponibleId);
    }

    @Override
    public HorarioDisponibleDomain consultarHorarioDisponiblePorId(UUID canchaId, UUID horarioDisponibleId) throws BackEndException {
        HorarioDisponibleEntity horarioDisponibleEntity = null;
        //TODO: Validar cancha
        factory.getHorarioDisponibleDAO().consultarPorId(horarioDisponibleId);
        return null;
    }

    @Override
    public List<HorarioDisponibleDomain> consultarHorariosDisponiblesPorCancha(UUID canchaId, HorarioDisponibleDomain filtro) throws BackEndException {
        HorarioDisponibleEntity horarioDisponibleFilter = null;
        List<HorarioDisponibleEntity> horarioDisponibleEntities = factory.getHorarioDisponibleDAO().consultar(horarioDisponibleFilter);
        List<HorarioDisponibleDomain> datosARetornar = null;
        return datosARetornar;
    }


}
