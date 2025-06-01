package co.edu.uco.backend.businesslogic.facade;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.ClienteDTO;

import java.util.List;
import java.util.UUID;

public interface ClienteFacade extends UsuarioFacade {

    void registrarNuevoCliente(ClienteDTO cliente) throws BackEndException;

    void modificarClienteExistente(UUID clienteId, ClienteDTO cliente) throws BackEndException;

    void darBajaDefinitivamenteClienteExistente(UUID clienteId) throws BackEndException;

    ClienteDTO consultarClientePorId(UUID clienteId) throws BackEndException;

    List<ClienteDTO> consultarClientes(ClienteDTO filtro) throws BackEndException;

}
