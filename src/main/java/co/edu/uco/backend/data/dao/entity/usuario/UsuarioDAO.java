package co.edu.uco.backend.data.dao.entity.usuario;

import co.edu.uco.backend.data.dao.entity.CreateDAO;
import co.edu.uco.backend.data.dao.entity.DeleteDAO;
import co.edu.uco.backend.data.dao.entity.RetrieveDAO;
import co.edu.uco.backend.data.dao.entity.UpdateDAO;
import co.edu.uco.backend.entity.OrganizacionDeportivaEntity;

import java.util.UUID;

public interface UsuarioDAO extends
        CreateDAO<OrganizacionDeportivaEntity>,
        RetrieveDAO<OrganizacionDeportivaEntity, UUID>,
        UpdateDAO<OrganizacionDeportivaEntity, UUID>,
        DeleteDAO<UUID> {
}
