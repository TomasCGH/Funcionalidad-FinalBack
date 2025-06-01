package co.edu.uco.backend.businesslogic.facade;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.DepartamentoDTO;

import java.util.List;
import java.util.UUID;

public interface DepartamentoFacade {

    void registrarNuevoDepartamento(DepartamentoDTO departamento) throws BackEndException;

    void modificarDepartamentoExistente(UUID departamentoId, DepartamentoDTO departamento) throws BackEndException;

    void darBajaDefinitivamenteDepartamentoExistente(UUID departamentoId) throws BackEndException;

    DepartamentoDTO consultarDepartamentoPorId(UUID departamentoId) throws BackEndException;

    List<DepartamentoDTO> consultarDepartamentos(DepartamentoDTO filtro) throws BackEndException;

}
