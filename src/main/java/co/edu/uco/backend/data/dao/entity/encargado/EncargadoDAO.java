package co.edu.uco.backend.data.dao.entity.encargado;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.entity.CreateDAO;
import co.edu.uco.backend.data.dao.entity.DeleteDAO;
import co.edu.uco.backend.data.dao.entity.RetrieveDAO;
import co.edu.uco.backend.data.dao.entity.UpdateDAO;
import co.edu.uco.backend.entity.EncargadoEntity;

import java.util.List;
import java.util.UUID;

public interface EncargadoDAO extends
        CreateDAO<EncargadoEntity>,
        RetrieveDAO<EncargadoEntity, UUID>,
        UpdateDAO<EncargadoEntity, UUID>,
        DeleteDAO<UUID> {

    List<EncargadoEntity> listAll() throws BackEndException;
}
