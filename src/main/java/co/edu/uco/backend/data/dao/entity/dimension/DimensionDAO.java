package co.edu.uco.backend.data.dao.entity.dimension;

import co.edu.uco.backend.data.dao.entity.CreateDAO;
import co.edu.uco.backend.data.dao.entity.DeleteDAO;
import co.edu.uco.backend.data.dao.entity.RetrieveDAO;
import co.edu.uco.backend.data.dao.entity.UpdateDAO;
import co.edu.uco.backend.entity.DimensionEntity;

import java.util.UUID;

public interface DimensionDAO extends
        CreateDAO<DimensionEntity>,
        RetrieveDAO<DimensionEntity, UUID>,
        UpdateDAO<DimensionEntity, UUID>,
        DeleteDAO<UUID> {

}
