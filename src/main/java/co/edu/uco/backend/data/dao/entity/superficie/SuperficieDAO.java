package co.edu.uco.backend.data.dao.entity.superficie;

import co.edu.uco.backend.data.dao.entity.CreateDAO;
import co.edu.uco.backend.data.dao.entity.DeleteDAO;
import co.edu.uco.backend.data.dao.entity.RetrieveDAO;
import co.edu.uco.backend.data.dao.entity.UpdateDAO;
import co.edu.uco.backend.entity.SuperficieEntity;

import java.util.UUID;

public interface SuperficieDAO extends
        CreateDAO<SuperficieEntity>,
        RetrieveDAO<SuperficieEntity, UUID>,
        UpdateDAO<SuperficieEntity, UUID>,
        DeleteDAO<UUID> {

}
