package co.edu.uco.backend.businesslogic.businesslogic;

import co.edu.uco.backend.businesslogic.businesslogic.domain.ClienteDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

import java.util.List;
import java.util.UUID;

public interface ClienteBusinessLogic extends UsuarioBusinessLogic {

    void registrarNuevoCliente(ClienteDomain cliente) throws BackEndException;

    void modificarClienteExistente(UUID clienteId, ClienteDomain cliente) throws BackEndException;

    void darBajaDefinitivamenteClienteExistente(UUID clienteId) throws BackEndException;

    ClienteDomain consultarClientePorId(UUID clienteId) throws BackEndException;

    List<ClienteDomain> consultarClientes(ClienteDomain filtro) throws BackEndException;

}
