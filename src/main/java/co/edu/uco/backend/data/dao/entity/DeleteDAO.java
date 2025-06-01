package co.edu.uco.backend.data.dao.entity;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

public interface DeleteDAO<ID> {
    void eliminar(ID id) throws BackEndException;
}
