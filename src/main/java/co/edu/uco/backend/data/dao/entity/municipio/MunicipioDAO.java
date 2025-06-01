package co.edu.uco.backend.data.dao.entity.municipio;

import co.edu.uco.backend.data.dao.entity.CreateDAO;
import co.edu.uco.backend.data.dao.entity.DeleteDAO;
import co.edu.uco.backend.data.dao.entity.RetrieveDAO;
import co.edu.uco.backend.data.dao.entity.UpdateDAO;
import co.edu.uco.backend.entity.MunicipioEntity;

import java.util.UUID;

public interface MunicipioDAO extends
        CreateDAO<MunicipioEntity>,
        RetrieveDAO<MunicipioEntity, UUID>,
        UpdateDAO<MunicipioEntity, UUID>,
        DeleteDAO<UUID> {

}
