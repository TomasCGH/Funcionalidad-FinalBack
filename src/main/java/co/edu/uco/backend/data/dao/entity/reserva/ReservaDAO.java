package co.edu.uco.backend.data.dao.entity.reserva;

import co.edu.uco.backend.data.dao.entity.CreateDAO;
import co.edu.uco.backend.data.dao.entity.DeleteDAO;
import co.edu.uco.backend.data.dao.entity.RetrieveDAO;
import co.edu.uco.backend.data.dao.entity.UpdateDAO;
import co.edu.uco.backend.entity.ReservaEntity;

import java.util.UUID;

public interface ReservaDAO extends
        CreateDAO<ReservaEntity>,
        RetrieveDAO<ReservaEntity, UUID>,
        UpdateDAO<ReservaEntity, UUID>,
        DeleteDAO<UUID> {

}
