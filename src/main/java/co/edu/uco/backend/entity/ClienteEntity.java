package co.edu.uco.backend.entity;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;

import java.util.UUID;

public final class ClienteEntity extends UsuarioEntity {

    public ClienteEntity() {
        super();
    }

    ClienteEntity(final UUID id) {
        super(id);
    }

    public ClienteEntity(final UUID id, final String nombre, final String username,
                         final String contrasena, final String prefijoTelefono, final String telefono) {
        super(id, nombre, username, contrasena, prefijoTelefono, telefono);
    }

    public static ClienteEntity obtenerClienteDefecto() {
        return new ClienteEntity();
    }

    public static ClienteEntity obtenerValorDefecto(final ClienteEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, obtenerClienteDefecto());
    }
}

