package co.edu.uco.backend.data.dao.entity;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

import java.util.List;

public interface RetrieveDAO<E, ID> {
    List<E> consultar(E filtro) throws BackEndException;
    E consultarPorId(ID id) throws BackEndException;
}
