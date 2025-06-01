package co.edu.uco.backend.dto;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;

import java.util.UUID;

public final class ClienteDTO extends UsuarioDTO {

    public ClienteDTO() {
        super();
    }

    public ClienteDTO(final UUID id) {
        super(id);
    }

    public ClienteDTO(
            final UUID id,
            final String nombre,
            final String username,
            final String contrasena,
            final String prefijoTelefono,
            final String telefono
    ) {
        super(id, nombre, username, contrasena, prefijoTelefono, telefono);
    }

    public static ClienteDTO obtenerValorDefecto() {
        return new ClienteDTO();
    }

    public static ClienteDTO obtenerValorDefecto(final ClienteDTO cliente) {
        return UtilObjeto.getInstance().obtenerValorDefecto(cliente, obtenerValorDefecto());
    }
}
