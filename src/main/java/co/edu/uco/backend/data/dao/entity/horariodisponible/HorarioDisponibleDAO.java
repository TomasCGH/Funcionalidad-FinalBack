package co.edu.uco.backend.data.dao.entity.horariodisponible;

import co.edu.uco.backend.data.dao.entity.CreateDAO;
import co.edu.uco.backend.data.dao.entity.DeleteDAO;
import co.edu.uco.backend.data.dao.entity.RetrieveDAO;
import co.edu.uco.backend.data.dao.entity.UpdateDAO;
import co.edu.uco.backend.entity.HorarioDisponibleEntity;

import java.util.UUID;

public interface HorarioDisponibleDAO extends
        CreateDAO<HorarioDisponibleEntity>,
        RetrieveDAO<HorarioDisponibleEntity, UUID>,
        UpdateDAO<HorarioDisponibleEntity, UUID>,
        DeleteDAO<UUID> {

}
