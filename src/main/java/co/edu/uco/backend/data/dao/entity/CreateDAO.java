package co.edu.uco.backend.data.dao.entity;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

public interface CreateDAO<E> {
    void crear(E entity) throws BackEndException;
}
