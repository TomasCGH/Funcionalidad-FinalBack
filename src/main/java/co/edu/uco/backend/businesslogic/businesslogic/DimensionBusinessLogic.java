package co.edu.uco.backend.businesslogic.businesslogic;

import co.edu.uco.backend.businesslogic.businesslogic.domain.DimensionDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

import java.util.UUID;

public interface DimensionBusinessLogic {

    void registrarNuevaDimension(UUID canchaId,DimensionDomain dimension) throws BackEndException;
    void modificarDimensionExistente(UUID canchaId, UUID dimensionId, DimensionDomain dimension) throws BackEndException;
    void eliminarDimension(UUID canchaId, UUID dimensionId) throws BackEndException;
    DimensionDomain consultarDimensionPorId(UUID canchaId, UUID dimensionId) throws BackEndException;


}
