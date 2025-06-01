package co.edu.uco.backend.data.dao.entity.estadoverificacion;

import co.edu.uco.backend.data.dao.entity.CreateDAO;
import co.edu.uco.backend.data.dao.entity.DeleteDAO;
import co.edu.uco.backend.data.dao.entity.RetrieveDAO;
import co.edu.uco.backend.data.dao.entity.UpdateDAO;
import co.edu.uco.backend.entity.EstadoVerificacionEntity;

import java.util.UUID;

public interface EstadoVerificacionDAO extends
        CreateDAO<EstadoVerificacionEntity>,
        RetrieveDAO<EstadoVerificacionEntity, UUID>,
        UpdateDAO<EstadoVerificacionEntity, UUID>,
        DeleteDAO<UUID> {

}
