package co.edu.uco.backend.businesslogic.businesslogic;

import co.edu.uco.backend.businesslogic.businesslogic.domain.EstadoVerificacionDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

import java.util.List;
import java.util.UUID;

public interface EstadoVerificacionBusinessLogic {

    void registrarNuevoEstadoVerificacion(EstadoVerificacionDomain estadoVerificacion) throws BackEndException;

    void modificarEstadoVerificacionExistente(UUID estadoVerificacionId, EstadoVerificacionDomain estadoVerificacion) throws BackEndException;

    void darBajaDefinitivamenteEstadoVerificacionExistente(UUID estadoVerificacionId) throws BackEndException;

    EstadoVerificacionDomain consultarEstadoVerificacionPorId(UUID estadoVerificacionId) throws BackEndException;

    List<EstadoVerificacionDomain> consultarEstadoVerificacions(EstadoVerificacionDomain filtro) throws BackEndException;

}
