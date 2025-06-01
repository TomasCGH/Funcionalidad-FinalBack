package co.edu.uco.backend.dto;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public class UsuarioDTO {

    private UUID id;
    private String nombre;
    private String username;
    private String contrasena;
    private String prefijoTelefono;
    private String telefono;

    public UsuarioDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setUsername(UtilTexto.getInstance().obtenerValorDefecto());
        setContrasena(UtilTexto.getInstance().obtenerValorDefecto());
        setPrefijoTelefono(UtilTexto.getInstance().obtenerValorDefecto());
        setTelefono(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public UsuarioDTO(final UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setUsername(UtilTexto.getInstance().obtenerValorDefecto());
        setContrasena(UtilTexto.getInstance().obtenerValorDefecto());
        setPrefijoTelefono(UtilTexto.getInstance().obtenerValorDefecto());
        setTelefono(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public UsuarioDTO(
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

    public static UsuarioDTO obtenerUsuarioDefecto() {
        return new UsuarioDTO() {};
    }

    public static UsuarioDTO obtenerValorDefecto(final UsuarioDTO dto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(dto, obtenerUsuarioDefecto());
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

    public UsuarioDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public UsuarioDTO setNombre(final String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(nombre);
        return this;
    }

    public UsuarioDTO setUsername(final String username) {
        this.username = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(username);
        return this;
    }

    public UsuarioDTO setContrasena(final String contrasena) {
        this.contrasena = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(contrasena);
        return this;
    }

    public UsuarioDTO setPrefijoTelefono(final String prefijoTelefono) {
        this.prefijoTelefono = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(prefijoTelefono);
        return this;
    }

    public UsuarioDTO setTelefono(final String telefono) {
        this.telefono = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(telefono);
        return this;
    }
}
