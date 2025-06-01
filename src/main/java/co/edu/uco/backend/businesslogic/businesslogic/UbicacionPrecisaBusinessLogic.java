package co.edu.uco.backend.businesslogic.businesslogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.UbicacionPrecisaDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

import java.util.UUID;

public interface UbicacionPrecisaBusinessLogic {

    void registrarNuevaUbicacionPrecisa(UUID canchaId,UbicacionPrecisaDomain ubicacionPrecisa) throws BackEndException;
    void modificarUbicacionPrecisaExistente(UUID canchaId, UUID ubicacionPrecisaId, UbicacionPrecisaDomain ubicacionPrecisa) throws BackEndException;
    void eliminarUbicacionPrecisa(UUID canchaId, UUID ubicacionPrecisaId) throws BackEndException;
    UbicacionPrecisaDomain consultarUbicacionPrecisaPorId(UUID canchaId, UUID ubicacionPrecisaId) throws BackEndException;

}
