package co.edu.uco.backend.data.dao.entity;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

public interface UpdateDAO<E, ID> {
    void modificar(ID id, E entity) throws BackEndException;
}
