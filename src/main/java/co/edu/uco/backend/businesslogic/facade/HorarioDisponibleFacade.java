package co.edu.uco.backend.businesslogic.facade;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.HorarioDisponibleDTO;

import java.util.List;
import java.util.UUID;

public interface HorarioDisponibleFacade {

    void registrarNuevoHorarioDisponible(UUID canchaId, HorarioDisponibleDTO horarioDisponible) throws BackEndException;

    void modificarHorarioDisponibleExistente(UUID canchaId,UUID horarioDisponinbleID, HorarioDisponibleDTO horarioDisponible) throws BackEndException;

    void darBajaDefinitivamenteHorarioDisponibleExistente(UUID canchaId, UUID horarioDisponibleId) throws BackEndException;

    HorarioDisponibleDTO consultarHorarioDisponiblePorId(UUID canchaId, UUID horarioDisponibleId) throws BackEndException;

    List<HorarioDisponibleDTO> consultarHorariosDisponiblesPorCancha(UUID canchaId, HorarioDisponibleDTO filtro) throws BackEndException;

}
