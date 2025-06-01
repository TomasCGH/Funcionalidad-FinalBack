package co.edu.uco.backend.businesslogic.facade.impl;

import co.edu.uco.backend.businesslogic.assembler.cliente.dto.ClienteDTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.ClienteDomain;
import co.edu.uco.backend.businesslogic.businesslogic.impl.ClienteBusinessLogicImpl;
import co.edu.uco.backend.businesslogic.businesslogic.ClienteBusinessLogic;
import co.edu.uco.backend.businesslogic.facade.ClienteFacade;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.data.dao.factory.Factory;
import co.edu.uco.backend.dto.ClienteDTO;
import co.edu.uco.backend.dto.UsuarioDTO;

import java.util.List;
import java.util.UUID;

public class ClienteFacadeImpl implements ClienteFacade {

    private final DAOFactory daoFactory;
    private final ClienteBusinessLogic clienteBusinessLogic;

    public ClienteFacadeImpl() throws BackEndException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        clienteBusinessLogic = new ClienteBusinessLogicImpl(daoFactory);
    }

    @Override
    public void registrarNuevoCliente(ClienteDTO cliente) throws BackEndException {
        daoFactory.abrirConexion();
        try {
            daoFactory.iniciarTransaccion();

            ClienteDomain clienteDomain = ClienteDTOAssembler.getInstance().toDomain(cliente);
            clienteBusinessLogic.registrarNuevoCliente(clienteDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de registrar la informacion del nuevo cliente, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registrar la informacion del nuevo cliente";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void modificarClienteExistente(UUID clienteId, ClienteDTO cliente) throws BackEndException {
        daoFactory.abrirConexion();
        try {
            daoFactory.iniciarTransaccion();

            ClienteDomain clienteDomain = ClienteDTOAssembler.getInstance().toDomain(cliente);
            clienteBusinessLogic.modificarClienteExistente(clienteId, clienteDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de modificar la informacion del cliente, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la informacion del cliente";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void darBajaDefinitivamenteClienteExistente(UUID clienteId) throws BackEndException {
        daoFactory.abrirConexion();
        try {
            daoFactory.iniciarTransaccion();

            clienteBusinessLogic.darBajaDefinitivamenteClienteExistente(clienteId);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de eliminar la informacion cliente, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de eliminar la informacion del cliente";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public ClienteDTO consultarClientePorId(UUID clienteId) throws BackEndException {
        daoFactory.abrirConexion();
        try {
            var clienteDomainResultado = clienteBusinessLogic.consultarClientePorId(clienteId);
            return ClienteDTOAssembler.getInstance().toDTO(clienteDomainResultado);
        } catch (BackEndException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de consultar la informacion del cliente con el identificador deseado, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de consultar la informacion del cliente con el identificador deseado...";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<ClienteDTO> consultarClientes(ClienteDTO filtro) throws BackEndException {
        daoFactory.abrirConexion();
        try {

            ClienteDomain filtroDomain = ClienteDTOAssembler.getInstance().toDomain(filtro);
            List<ClienteDomain> dominios = clienteBusinessLogic.consultarClientes(filtroDomain);
            return ClienteDTOAssembler.getInstance().toDTOs(dominios);
        } catch (BackEndException ex) {
            throw ex;
        } catch (Exception ex) {
            var mensajeUsuario = "Se ha presentado un problema inesperado al consultar todos los clientes";
            var mensajeTecnico = "Excepción inesperada listando todas los clientes";
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public UsuarioDTO iniciarSesion(String username, String rawPassword, String ipAdress, String agentUser) {
        return null;
    }

    @Override
    public void cerrarSesion(UUID usuarioId) {
        //Sin implementar
    }

    @Override
    public void recuperarContrasena(String username) {
        //Sin implementar
    }

    @Override
    public void cambiarContrasena(UUID usuarioId, String rawPasswordActual, String rawPasswordNueva) {
        //Sin implementar
    }

    @Override
    public UsuarioDTO consultarUsuarioPorId(UUID usuarioId) {
        return null;
    }

    @Override
    public List<UsuarioDTO> listarUsuarios(UsuarioDTO filtro) {
        return List.of();
    }
}


