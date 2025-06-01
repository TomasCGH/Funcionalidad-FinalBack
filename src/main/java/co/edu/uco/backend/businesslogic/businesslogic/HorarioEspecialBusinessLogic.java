package co.edu.uco.backend.businesslogic.businesslogic;

import co.edu.uco.backend.businesslogic.businesslogic.domain.HorarioEspecialDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

import java.util.List;
import java.util.UUID;

public interface HorarioEspecialBusinessLogic {

    void registrarNuevoHorarioEspecial(UUID canchaId, HorarioEspecialDomain horarioEspecial) throws BackEndException;

    void modificarHorarioEspecialExistente(UUID canchaId,UUID horarioEspecialId, HorarioEspecialDomain horarioEspecial) throws BackEndException;

    void darBajaDefinitivamenteHorarioEspecialExistente(UUID canchaId, UUID horarioEspecialId) throws BackEndException;

    HorarioEspecialDomain consultarHorarioEspecialPorId(UUID canchaId, UUID horarioEspecialId) throws BackEndException;

    List<HorarioEspecialDomain> consultarHorariosEspecialesPorCancha(UUID canchaId, HorarioEspecialDomain horarioEspecial) throws BackEndException;

}
