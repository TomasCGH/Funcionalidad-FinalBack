package co.edu.uco.backend.businesslogic.businesslogic.impl;

import co.edu.uco.backend.businesslogic.assembler.cliente.entity.ClienteEntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.ClienteBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.ClienteDomain;
import co.edu.uco.backend.businesslogic.businesslogic.domain.UsuarioDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.entity.ClienteEntity;

import java.util.List;
import java.util.UUID;

public class ClienteBusinessLogicImpl implements ClienteBusinessLogic {

    private final DAOFactory factory;

    public ClienteBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoCliente(ClienteDomain cliente) throws BackEndException {
        //  1. Cliente-POL-0001. Asegurar que el número de celular sea único en el sistema.
        validarNoExistaClienteConMismoTelefono(cliente.getTelefono());

        //  2. Cliente-POL-0002. Asegurar que no existe otro Cliente con el mismo nombre de usuario
        validarNoExistaClienteConMismoUsername(cliente.getUsername());

        //  3. Cliente-POL-0003 y Cliente-POL-0004. Validar que la contraseña cumpla con los requisitos de seguridad.
        //Asegurar que los datos requeridos para llevar a cabo la acción sean válidos a nivel de tipo de dato, longitud,
        // obligatoriedad, formato y rango.
        validarIntegridadInformacionRegistrarNuevoCliente(cliente);

        // 4. Generar ID y recrear domain
        UUID id = generarIdentificadorNuevoCliente();
        ClienteDomain toCreate = new ClienteDomain(
                id,
                cliente.getNombre(),
                cliente.getUsername(),
                cliente.getContrasena(),
                cliente.getPrefijoTelefono(),
                cliente.getTelefono()
        );

        //  5. Registrar cliente siempre y cuando se cumplan todas las políticas
        ClienteEntity entity = ClienteEntityAssembler.getInstance().toEntity(toCreate);
        factory.getClienteDAO().crear(entity);
    }

    @Override
    public void modificarClienteExistente(UUID clienteId, ClienteDomain datosActualizados) throws BackEndException {
        //  1. Verificar que el UUID proporcionado exista en la base de datos
        ClienteDomain existente = cargarClienteExistente(clienteId);

        //  2. Cliente-POL-0012. En caso de solo modificar algunos datos los otros deben continuar iguales.
        ClienteDomain merged = mezclarCamposCliente(existente, datosActualizados);

        //  3. Cliente-POL-0013. Verificar si el número telefónico o nombre de usuario a cambiar ya existe en otra cuenta,
        // en ese caso no se podrá cambiar
        validarNoExistaClienteConMismoTelefonoActualizar(clienteId, merged.getTelefono());
        validarNoExistaClienteConMismoUsernameActualizar(clienteId, merged.getUsername());

        //  4. Cliente-POL-0015. Asegurar que los datos requeridos para llevar a cabo la acción sean válidos a nivel de tipo de dato,
        //  longitud, obligatoriedad, formato y rango.
        validarIntegridadInformacionRegistrarNuevoCliente(merged);

        // Ejecutar update siempre y cuando se cumplan las políticas
        ClienteEntity entity = ClienteEntityAssembler.getInstance().toEntity(merged);
        factory.getClienteDAO().modificar(clienteId, entity);
    }

    @Override
    public void darBajaDefinitivamenteClienteExistente(UUID clienteId) throws BackEndException {
        cargarClienteExistente(clienteId);
        factory.getClienteDAO().eliminar(clienteId);
    }

    @Override
    public ClienteDomain consultarClientePorId(UUID clienteId) throws BackEndException {
        var entity = factory.getClienteDAO().consultarPorId(clienteId);
        return ClienteEntityAssembler.getInstance().toDomain(entity);
    }

    @Override
    public List<ClienteDomain> consultarClientes(ClienteDomain filtro) throws BackEndException {
        ClienteEntity filterEntity = ClienteEntityAssembler.getInstance().toEntity(filtro);
        List<ClienteEntity> entities = factory.getClienteDAO().consultar(filterEntity);
        return ClienteEntityAssembler.getInstance().toDomain(entities);
    }

    @Override
    public UsuarioDomain iniciarSesion(String username, String rawPassword, String ipAddress, String userAgent) {
        // No implementado ...
        return null;
    }

    @Override
    public void cerrarSesion(UUID usuarioId) { /* no implementado */ }

    @Override
    public void recuperarContrasena(String username) { /* no implementado */ }

    @Override
    public void cambiarContrasena(UUID usuarioId, String rawPasswordActual, String rawPasswordNueva) { /* no implementado */ }

    @Override
    public UsuarioDomain consultarUsuarioPorId(UUID usuarioId) { return null; }

    @Override
    public List<UsuarioDomain> listarUsuarios(UsuarioDomain filtro) { return List.of(); }

    // ———————————————————————————————————————————
    // Métodos privados auxiliares
    // ———————————————————————————————————————————

    private void validarIntegridadInformacionRegistrarNuevoCliente(ClienteDomain cliente) throws BackEndException {
        validarIntegridadNombreCliente(cliente.getNombre());
        validarIntegridadUsernameCliente(cliente.getUsername());
        validarIntegridadContrasena(cliente.getContrasena());
        validarIntegridadPrefijo(cliente.getPrefijoTelefono());
        validarIntegridadTelefono(cliente.getTelefono());
    }

    private void validarIntegridadNombreCliente(String nombre) throws BackEndException {
        //  1. Obligatoriedad: El nombre del cliente debe ser un dato obligatorio
        if (UtilTexto.getInstance().estaVacia(nombre)) {
            throw BusinessLogicBackEndException.reportar("Error de obligatoriedad, el nombre del cliente es un dato obligatorio que debe ingresar");
        }

        //  2. Longitud: Debe estar entre 2 y 100 caracteres
        String val = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(nombre);
        if (val.length() < 2 || val.length() > 100) {
            throw BusinessLogicBackEndException.reportar("Longitud del nombre incorrecta. el nombre del cliente debe tener entre 2 y 100 carácteres y el ingresado tiene "+ nombre.length());
        }

        //  3. Formato: Solo puede contener letras y espacios
        if (!UtilTexto.getInstance().contieneSoloLetrasEspacios(nombre)) {
            throw BusinessLogicBackEndException.reportar("Formato del nombre incorrecto, el nombre del cliente no puede contener números o carácteres especiales, solamente letras y espacios.");
        }
    }

    private void validarIntegridadUsernameCliente(String username) throws BackEndException {
        //  1. Obligatoriedad: El username del cliente debe ser un dato obligatorio
        if (UtilTexto.getInstance().estaVacia(username)) {
            throw BusinessLogicBackEndException.reportar("Error de obligatoriedad, el nombre de usuario es un dato obligatorio que debe ingresar");
        }

        //  2. Longitud: Debe estar entre 5 y 30 caracteres
        String val = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(username);
        if (val.length() < 5 || val.length() > 30) {
            throw BusinessLogicBackEndException.reportar("Longitud del nombre de usuario incorrecto, el nombre del usuario debe tener entre 5 y 30 caracteres y el ingresado tiene "+ username.length());
        }

        //  3. Formato: Solo puede contener letras, números y espacios
        if (!UtilTexto.getInstance().contieneSoloLetrasNumerosEspacios(username)) {
            throw BusinessLogicBackEndException.reportar("Formato del nombre de usuario incorrecto, el nombre de usuario no puede contener carácteres especiales, solamente números, letras y espacios.");
        }
    }

    private void validarIntegridadContrasena(String contrasena) throws BackEndException {
        //  1. Obligatoriedad: La contraseña del cliente debe ser un dato obligatorio
        if (UtilTexto.getInstance().estaVacia(contrasena)) {
            throw BusinessLogicBackEndException.reportar("Error de obligatoriedad, la contraseña es un dato obligatorio que debe ingresar");
        }

        //  2. Longitud: Debe estar entre 8 y 24 caracteres
        String val = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(contrasena);
        if (val.length() < 8 || val.length() > 24) {
            throw BusinessLogicBackEndException.reportar("Longitud de la contraseña incorrecta, la contraseña debe tener entre 8 y 24 caracteres y la ingresada tiene "+ contrasena.length());
        }

        //  3. Formato: Solo puede contener letras, números, espacios y carácteres especiales permitidos
        if (!UtilTexto.getInstance().contieneSoloAlfanumericoEspaciosEspeciales(contrasena)) {
            throw BusinessLogicBackEndException.reportar("Formato de la contraseña incorrecto, la contraseña solo puede contener letras, números, espacios y carácteres especiales permitidos(@,-,_,.,$...) ");
        }
    }

    private void validarIntegridadPrefijo(String prefijo) throws BackEndException {
        //  1. Obligatoriedad: La contraseña del cliente debe ser un dato obligatorio
        if (UtilTexto.getInstance().estaVacia(prefijo)) {
            throw BusinessLogicBackEndException.reportar("Error de obligatoriedad, el prefijo telefónico es un dato obligatorio que debe ingresar");
        }

        //  2. Longitud: Debe estar entre 2 y 5 carácteres
        String val = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(prefijo);
        if (val.length() < 2 || val.length() > 5) {
            throw BusinessLogicBackEndException.reportar("Longitud de prefijo incorrecta, el prefijo telefónico debe tener entre 2 y 5 caracteres y el ingresado tiene " + prefijo.length());
        }

        //  3. Formato: Solo puede contener el símbolo + y su numeral
        if (!UtilTexto.getInstance().contieneSoloNumerosYMas(prefijo)) {
            throw BusinessLogicBackEndException.reportar("Formato de prefijo incorrecto. El prefijo solo puede contener números y el símbolo '+'. Ej(+57) ");
        }
    }

    private void validarIntegridadTelefono(String telefono) throws BackEndException {
        //  1. Obligatoriedad: El teléfono del cliente debe ser un dato obligatorio
        if (UtilTexto.getInstance().estaVacia(telefono)) {
            throw BusinessLogicBackEndException.reportar("Error de obligatoriedad, el número de teléfono es un dato obligatorio que debe ingresar");
        }

        //  2. Longitud: Debe estar entre 7 y 15 carácteres
        String val = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(telefono);
        if (val.length() < 7 || val.length() > 15) {
            throw BusinessLogicBackEndException.reportar("Longitud de teléfono incorrecta, el número de teléfono debe tener entre 7 y 15 caracteres y el ingresado tiene " + telefono.length());
        }

        //  3. Formato: Contiene solamente dígitos de 0 a 9
        if (!UtilTexto.getInstance().contieneSoloNumeros(telefono)) {
            throw BusinessLogicBackEndException.reportar("Formato de teléfono incorrecto, el número telefónico solo puede contener dígitos (0-9)");
        }
    }

    private UUID generarIdentificadorNuevoCliente() throws BackEndException {
        UUID nuevoId;
        do {
            nuevoId = UtilUUID.generarNuevoUUID();
        } while (!UtilUUID.esValorDefecto(factory.getClienteDAO().consultarPorId(nuevoId).getId()));
        return nuevoId;
    }

    private ClienteDomain cargarClienteExistente(UUID clienteId) throws BackEndException {
        var entity = factory.getClienteDAO().consultarPorId(clienteId);
        if (UtilUUID.esValorDefecto(entity.getId())) {
            throw BusinessLogicBackEndException.reportar("No existe cliente con id: " + clienteId);
        }
        return ClienteEntityAssembler.getInstance().toDomain(entity);
    }

    private ClienteDomain mezclarCamposCliente(ClienteDomain original, ClienteDomain actualizaciones) {
        String nombre   = UtilTexto.getInstance().estaVacia(actualizaciones.getNombre())
                ? original.getNombre()   : actualizaciones.getNombre().trim();
        String username = UtilTexto.getInstance().estaVacia(actualizaciones.getUsername())
                ? original.getUsername() : actualizaciones.getUsername().trim();
        String contra   = UtilTexto.getInstance().estaVacia(actualizaciones.getContrasena())
                ? original.getContrasena(): actualizaciones.getContrasena();
        String prefijo  = UtilTexto.getInstance().estaVacia(actualizaciones.getPrefijoTelefono())
                ? original.getPrefijoTelefono() : actualizaciones.getPrefijoTelefono();
        String telefono = UtilTexto.getInstance().estaVacia(actualizaciones.getTelefono())
                ? original.getTelefono() : actualizaciones.getTelefono();

        return new ClienteDomain(
                original.getId(),
                nombre, username, contra, prefijo, telefono
        );
    }

    // unicidad teléfono para creación de nuevo cliente
    private void validarNoExistaClienteConMismoTelefono(String telefono) throws BackEndException {
        var filtro = new ClienteEntity(); filtro.setTelefono(telefono.trim());
        if (!factory.getClienteDAO().consultar(filtro).isEmpty()) {
            throw BusinessLogicBackEndException.reportar("Ya existe un usuario con ese teléfono, Por favor ingrese un télefono diferente");
        }
    }
    // unicidad nombre usuario para creación de nuevo cliente
    private void validarNoExistaClienteConMismoUsername(String username) throws BackEndException {
        var filtro = new ClienteEntity(); filtro.setUsername(username.trim());
        if (!factory.getClienteDAO().consultar(filtro).isEmpty()) {
            throw BusinessLogicBackEndException.reportar("Ya existe ese nombre de usuario, Por favor ingrese un nombre de usuario diferente");
        }
    }

    // unicidad teléfono en actualización de un cliente existente(excluyo el propio ID)
    private void validarNoExistaClienteConMismoTelefonoActualizar(UUID idActual, String telefono) throws BackEndException {
        var filtro = new ClienteEntity(); filtro.setTelefono(telefono.trim());
        for (var e : factory.getClienteDAO().consultar(filtro)) {
            if (!e.getId().equals(idActual)) {
                throw BusinessLogicBackEndException.reportar("Ya existe un usuario con ese teléfono, Por favor ingrese un télefono diferente");
            }
        }
    }

    // unicidad nombre usuario en actualización de un cliente existente
    private void validarNoExistaClienteConMismoUsernameActualizar(UUID idActual, String username) throws BackEndException {
        var filtro = new ClienteEntity(); filtro.setUsername(username.trim());
        for (var e : factory.getClienteDAO().consultar(filtro)) {
            if (!e.getId().equals(idActual)) {
                throw BusinessLogicBackEndException.reportar("Ya existe ese nombre de usuario, Por favor ingrese un nombre de usuario diferente");
            }
        }
    }
}
