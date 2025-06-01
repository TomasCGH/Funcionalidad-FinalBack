package co.edu.uco.backend.data.dao.entity.tipocancha;

import co.edu.uco.backend.data.dao.entity.CreateDAO;
import co.edu.uco.backend.data.dao.entity.DeleteDAO;
import co.edu.uco.backend.data.dao.entity.RetrieveDAO;
import co.edu.uco.backend.data.dao.entity.UpdateDAO;
import co.edu.uco.backend.entity.TipoCanchaEntity;

import java.util.UUID;

public interface TipoCanchaDAO extends
        CreateDAO<TipoCanchaEntity>,
        RetrieveDAO<TipoCanchaEntity, UUID>,
        UpdateDAO<TipoCanchaEntity, UUID>,
        DeleteDAO<UUID> {

}
