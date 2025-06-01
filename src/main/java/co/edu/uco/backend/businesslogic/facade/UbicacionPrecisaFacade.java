package co.edu.uco.backend.businesslogic.facade;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.UbicacionPrecisaDTO;

import java.util.UUID;

public interface UbicacionPrecisaFacade {

    void registrarNuevaUbicacionPrecisa(UUID canchaId, UbicacionPrecisaDTO ubicacionPrecisa) throws BackEndException;
    void modificarUbicacionPrecisaExistente(UUID canchaId, UUID ubicacionPrecisaId, UbicacionPrecisaDTO ubicacionPrecisa) throws BackEndException;
    void eliminarUbicacionPrecisa(UUID canchaId, UUID ubicacionPrecisaId) throws BackEndException;
    UbicacionPrecisaDTO consultarUbicacionPrecisaPorId(UUID canchaId, UUID ubicacionPrecisaId) throws BackEndException;


}
