package co.edu.uco.backend.businesslogic.businesslogic.domain;

import co.edu.uco.backend.crosscutting.utilitarios.*;
import java.util.UUID;

public abstract class UsuarioDomain {

    private UUID id;
    private String nombre;
    private String username;
    private String contrasena;
    private String prefijoTelefono;
    private String telefono;

    protected UsuarioDomain() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setUsername(UtilTexto.getInstance().obtenerValorDefecto());
        setContrasena("");  // Inicializar siempre como "" (no null)
        setPrefijoTelefono(UtilTexto.getInstance().obtenerValorDefecto());
        setTelefono(UtilTexto.getInstance().obtenerValorDefecto());
    }

    protected UsuarioDomain(final UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setUsername(UtilTexto.getInstance().obtenerValorDefecto());
        setContrasena("");
        setPrefijoTelefono(UtilTexto.getInstance().obtenerValorDefecto());
        setTelefono(UtilTexto.getInstance().obtenerValorDefecto());
    }

    protected UsuarioDomain(
            final UUID id,
            final String nombre,
            final String username,
            final String contrasena,
            final String prefijoTelefono,
            final String telefono
    ) {
        setId(id);
        setNombre(nombre);
        setUsername(username);
        setContrasena(contrasena);
        setPrefijoTelefono(prefijoTelefono);
        setTelefono(telefono);
    }

    static UsuarioDomain obtenerUsuarioDefecto() {
        return new UsuarioDomain() {};
    }

    static UsuarioDomain obtenerValorDefecto(final UsuarioDomain domain) {
        return UtilObjeto.getInstance().obtenerValorDefecto(domain, obtenerUsuarioDefecto());
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

    protected void setNombre(final String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(nombre);
    }

    protected void setUsername(final String username) {
        this.username = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(username);
    }

    protected void setContrasena(final String contrasena) {
        // No recortamos ni quitamos espacios: si vienen, los conservamos
        this.contrasena = (contrasena == null ? "" : contrasena);
    }

    protected void setPrefijoTelefono(final String prefijoTelefono) {
        this.prefijoTelefono = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(prefijoTelefono);
    }

    protected void setTelefono(final String telefono) {
        this.telefono = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(telefono);
    }
}
