package co.edu.uco.backend.data.dao.entity.horarioespecial;

import co.edu.uco.backend.data.dao.entity.CreateDAO;
import co.edu.uco.backend.data.dao.entity.DeleteDAO;
import co.edu.uco.backend.data.dao.entity.RetrieveDAO;
import co.edu.uco.backend.data.dao.entity.UpdateDAO;
import co.edu.uco.backend.entity.HorarioEspecialEntity;

import java.util.UUID;

public interface HorarioEspecialDAO extends
        CreateDAO<HorarioEspecialEntity>,
        RetrieveDAO<HorarioEspecialEntity, UUID>,
        UpdateDAO<HorarioEspecialEntity, UUID>,
        DeleteDAO<UUID> {

}
