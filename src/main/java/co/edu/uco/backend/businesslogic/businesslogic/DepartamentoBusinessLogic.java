package co.edu.uco.backend.businesslogic.businesslogic;

import co.edu.uco.backend.businesslogic.businesslogic.domain.DepartamentoDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

import java.util.List;
import java.util.UUID;

public interface DepartamentoBusinessLogic {

    void registrarNuevoDepartamento(DepartamentoDomain domain) throws BackEndException;

    void modificarDepartamentoExistente(UUID departamentoId, DepartamentoDomain domain) throws BackEndException;

    void darBajaDefinitivamenteDepartamentoExistente(UUID departamentoId) throws BackEndException;

    DepartamentoDomain consultarDepartamentoPorId(UUID departamentoId) throws BackEndException;

    List<DepartamentoDomain> consultarDepartamentos(DepartamentoDomain filtro) throws BackEndException;

}
