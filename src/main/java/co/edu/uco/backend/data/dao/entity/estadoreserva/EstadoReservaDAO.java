package co.edu.uco.backend.data.dao.entity.estadoreserva;

import co.edu.uco.backend.data.dao.entity.CreateDAO;
import co.edu.uco.backend.data.dao.entity.DeleteDAO;
import co.edu.uco.backend.data.dao.entity.RetrieveDAO;
import co.edu.uco.backend.data.dao.entity.UpdateDAO;
import co.edu.uco.backend.entity.EstadoReservaEntity;

import java.util.UUID;

public interface EstadoReservaDAO extends
        CreateDAO<EstadoReservaEntity>,
        RetrieveDAO<EstadoReservaEntity, UUID>,
        UpdateDAO<EstadoReservaEntity, UUID>,
        DeleteDAO<UUID> {

}
