package co.edu.uco.backend.data.dao.entity.factura;

import co.edu.uco.backend.data.dao.entity.CreateDAO;
import co.edu.uco.backend.data.dao.entity.DeleteDAO;
import co.edu.uco.backend.data.dao.entity.RetrieveDAO;
import co.edu.uco.backend.data.dao.entity.UpdateDAO;
import co.edu.uco.backend.entity.FacturaEntity;

import java.util.UUID;

public interface FacturaDAO extends
        CreateDAO<FacturaEntity>,
        RetrieveDAO<FacturaEntity, UUID>,
        UpdateDAO<FacturaEntity, UUID>,
        DeleteDAO<UUID> {

}
