package co.edu.uco.backend.businesslogic.businesslogic.domain;

import co.edu.uco.backend.crosscutting.utilitarios.*;
import java.util.UUID;

public final class ClienteDomain extends UsuarioDomain {

    ClienteDomain() {
        super();
    }

    ClienteDomain(final UUID id) {
        super(id);
    }

    public ClienteDomain(final UUID id, final String nombre, final String username,
                         final String contrasena, final String prefijoTelefono, final String telefono) {
        super(id, nombre, username, contrasena, prefijoTelefono, telefono);
    }

    static ClienteDomain obtenerClienteDefecto() {
        return new ClienteDomain();
    }

    static ClienteDomain obtenerValorDefecto(ClienteDomain domain) {
        return UtilObjeto.getInstance().obtenerValorDefecto(domain, obtenerClienteDefecto());
    }
}
