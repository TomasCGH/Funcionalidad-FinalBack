package co.edu.uco.backend.data.dao.entity.resena;

import co.edu.uco.backend.data.dao.entity.CreateDAO;
import co.edu.uco.backend.data.dao.entity.DeleteDAO;
import co.edu.uco.backend.data.dao.entity.RetrieveDAO;
import co.edu.uco.backend.data.dao.entity.UpdateDAO;
import co.edu.uco.backend.entity.ResenaEntity;

import java.util.UUID;

public interface ResenaDAO extends
        CreateDAO<ResenaEntity>,
        RetrieveDAO<ResenaEntity, UUID>,
        UpdateDAO<ResenaEntity, UUID>,
        DeleteDAO<UUID> {

}
