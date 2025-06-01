package co.edu.uco.backend.data.dao.entity.departamento;

import co.edu.uco.backend.data.dao.entity.CreateDAO;
import co.edu.uco.backend.data.dao.entity.DeleteDAO;
import co.edu.uco.backend.data.dao.entity.RetrieveDAO;
import co.edu.uco.backend.data.dao.entity.UpdateDAO;
import co.edu.uco.backend.entity.DepartamentoEntity;

import java.util.UUID;

public interface DepartamentoDAO extends
        CreateDAO<DepartamentoEntity>,
        RetrieveDAO<DepartamentoEntity, UUID>,
        UpdateDAO<DepartamentoEntity, UUID>,
        DeleteDAO<UUID> {

}
