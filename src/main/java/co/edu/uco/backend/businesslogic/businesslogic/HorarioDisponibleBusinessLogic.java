package co.edu.uco.backend.businesslogic.businesslogic;

import co.edu.uco.backend.businesslogic.businesslogic.domain.HorarioDisponibleDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

import java.util.List;
import java.util.UUID;

public interface HorarioDisponibleBusinessLogic {

    void registrarNuevoHorarioDisponible(UUID canchaId, HorarioDisponibleDomain horarioDisponible) throws BackEndException;

    void modificarHorarioDisponibleExistente(UUID canchaId,UUID horarioDisponinbleID, HorarioDisponibleDomain horarioDisponible) throws BackEndException;

    void darBajaDefinitivamenteHorarioDisponibleExistente(UUID canchaId, UUID horarioDisponibleId) throws BackEndException;

    HorarioDisponibleDomain consultarHorarioDisponiblePorId(UUID canchaId, UUID horarioDisponibleId) throws BackEndException;

    List<HorarioDisponibleDomain> consultarHorariosDisponiblesPorCancha(UUID canchaId, HorarioDisponibleDomain filtro) throws BackEndException;

}
