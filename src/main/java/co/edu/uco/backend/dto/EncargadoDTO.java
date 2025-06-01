package co.edu.uco.backend.dto;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

/**
 * DTO para Encargado, extiende UsuarioDTO y agrega:
 * - tipoDocumento  (CD o CE)
 * - numeroDocumento
 * - correo         (autogenerado)
 * - organizacionId (UUID de la organización deportiva)
 */
public final class EncargadoDTO extends UsuarioDTO {
    private String tipoDocumento;
    private String numeroDocumento;
    private String correo;
    private UUID organizacionId;

    public EncargadoDTO() {
        super();
    }

    public EncargadoDTO(final UUID id) {
        super(id);
    }

    public EncargadoDTO(
            final UUID id,
            final String nombre,
            final String username,
            final String contrasena,
            final String prefijoTelefono,
            final String telefono,
            final String tipoDocumento,
            final String numeroDocumento,
            final String correo,
            final UUID organizacionId
    ) {
        super(id, nombre, username, contrasena, prefijoTelefono, telefono);
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
        setCorreo(correo);
        setOrganizacionId(organizacionId);
    }

    public static EncargadoDTO obtenerValorDefecto() {
        return new EncargadoDTO();
    }

    public static EncargadoDTO obtenerValorDefecto(final EncargadoDTO encargado) {
        return UtilObjeto.getInstance().obtenerValorDefecto(encargado, obtenerValorDefecto());
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = UtilObjeto.getInstance().validarCadena(tipoDocumento);
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = UtilObjeto.getInstance().validarCadena(numeroDocumento);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = UtilObjeto.getInstance().validarCadena(correo);
    }

    public UUID getOrganizacionId() {
        return organizacionId;
    }

    public void setOrganizacionId(UUID organizacionId) {
        // Usamos directamente los métodos estáticos de UtilUUID
        if (organizacionId == null || UtilUUID.esValorDefecto(organizacionId)) {
            this.organizacionId = UtilUUID.obtenerValorDefecto();
        } else {
            this.organizacionId = organizacionId;
        }
    }
}
