package co.edu.uco.backend.entity;
import co.edu.uco.backend.crosscutting.utilitarios.*;

import java.util.UUID;

public class UsuarioEntity {

    private UUID id;
    private String nombre;
    private String username;
    private String contrasena;
    private String prefijoTelefono;
    private String telefono;

    protected UsuarioEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setUsername(UtilTexto.getInstance().obtenerValorDefecto());
        setContrasena(UtilTexto.getInstance().obtenerValorDefecto());
        setPrefijoTelefono(UtilTexto.getInstance().obtenerValorDefecto());
        setTelefono(UtilTexto.getInstance().obtenerValorDefecto());
    }

    protected UsuarioEntity(final UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setUsername(UtilTexto.getInstance().obtenerValorDefecto());
        setContrasena(UtilTexto.getInstance().obtenerValorDefecto());
        setPrefijoTelefono(UtilTexto.getInstance().obtenerValorDefecto());
        setTelefono(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public UsuarioEntity(final UUID id, final String nombre, final String username, final String contrasena,
                         final String prefijoTelefono, final String telefono) {
        setId(id);
        setNombre(nombre);
        setUsername(username);
        setContrasena(contrasena);
        setPrefijoTelefono(prefijoTelefono);
        setTelefono(telefono);
    }

    public static UsuarioEntity obtenerUsuarioDefecto() {
        return new UsuarioEntity() {};
    }

    public static UsuarioEntity obtenerValorDefecto(final UsuarioEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, obtenerUsuarioDefecto());
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsername() {
        return username;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getPrefijoTelefono() {
        return prefijoTelefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public void setNombre(final String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(nombre);
    }

    public void setUsername(final String username) {
        this.username = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(username);
    }

    public void setContrasena(final String contrasena) {
        this.contrasena = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(contrasena);
    }

    public void setPrefijoTelefono(final String prefijoTelefono) {
        this.prefijoTelefono = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(prefijoTelefono);
    }

    public void setTelefono(final String telefono) {
        this.telefono = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(telefono);
    }
}

