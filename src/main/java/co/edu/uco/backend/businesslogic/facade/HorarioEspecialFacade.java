package co.edu.uco.backend.businesslogic.facade;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.HorarioEspecialDTO;

import java.util.List;
import java.util.UUID;

public interface HorarioEspecialFacade {

    void registrarNuevoHorarioEspecial(UUID canchaId, HorarioEspecialDTO horarioEspecial) throws BackEndException;

    void modificarHorarioEspecialExistente(UUID canchaId,UUID horarioEspecialId, HorarioEspecialDTO horarioEspecial) throws BackEndException;

    void darBajaDefinitivamenteHorarioEspecialExistente(UUID canchaId, UUID horarioEspecialId) throws BackEndException;

    HorarioEspecialDTO consultarHorarioEspecialPorId(UUID canchaId, UUID horarioEspecialId) throws BackEndException;

    List<HorarioEspecialDTO> consultarHorariosEspecialesPorCancha(UUID canchaId, HorarioEspecialDTO horarioEspecial) throws BackEndException;

}
