package co.edu.uco.backend.data.dao.entity.horarioespecial.impl.postgresql;

import co.edu.uco.backend.data.dao.entity.horarioespecial.HorarioEspecialDAO;
import co.edu.uco.backend.entity.HorarioEspecialEntity;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class HorarioEspecialPostgreSQLDAO implements HorarioEspecialDAO {

    private final Connection connection;

    public HorarioEspecialPostgreSQLDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(HorarioEspecialEntity entity) {

    }

    @Override
    public void eliminar(UUID id) {

    }

    @Override
    public List<HorarioEspecialEntity> consultar(HorarioEspecialEntity entity) {
        return List.of();
    }

    @Override
    public HorarioEspecialEntity consultarPorId(UUID id) {
        return null;
    }


    @Override
    public void modificar(UUID uuid, HorarioEspecialEntity entity) {

    }
}
