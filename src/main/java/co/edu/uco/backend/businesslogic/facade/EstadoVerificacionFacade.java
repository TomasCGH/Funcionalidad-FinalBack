package co.edu.uco.backend.businesslogic.facade;

import co.edu.uco.backend.dto.EstadoVerificacionDTO;

import java.util.List;
import java.util.UUID;

public interface EstadoVerificacionFacade {

    void registrarNuevoEstadoVerificacion(EstadoVerificacionDTO estadoVerificacion);

    void modificarEstadoVerificacionExistente(UUID estadoVerificacionId, EstadoVerificacionDTO estadoVerificacion);

    void darBajaDefinitivamenteEstadoVerificacionExistente(UUID estadoVerificacionId);

    EstadoVerificacionDTO consultarEstadoVerificacionPorId(UUID estadoVerificacionId);

    List<EstadoVerificacionDTO> consultarEstadoVerificacions(EstadoVerificacionDTO filtro);

}
