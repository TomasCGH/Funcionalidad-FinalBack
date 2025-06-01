package co.edu.uco.backend.data.dao.entity.ubicacionprecisa;

import co.edu.uco.backend.data.dao.entity.CreateDAO;
import co.edu.uco.backend.data.dao.entity.DeleteDAO;
import co.edu.uco.backend.data.dao.entity.RetrieveDAO;
import co.edu.uco.backend.data.dao.entity.UpdateDAO;
import co.edu.uco.backend.entity.UbicacionPrecisaEntity;

import java.util.UUID;

public interface UbicacionPrecisaDAO extends
        CreateDAO<UbicacionPrecisaEntity>,
        RetrieveDAO<UbicacionPrecisaEntity, UUID>,
        UpdateDAO<UbicacionPrecisaEntity, UUID>,
        DeleteDAO<UUID> {

}
