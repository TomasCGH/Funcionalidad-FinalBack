package co.edu.uco.backend.entity;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;
import java.util.UUID;

/**
 * Entidad para Encargado, extiende UsuarioEntity y agrega:
 * - tipoDocumento  (CD o CE)
 * - numeroDocumento
 * - correo         (autogenerado en business logic)
 * - organizacionId (UUID de la organización deportiva)
 */
public final class EncargadoEntity extends UsuarioEntity {
    private String tipoDocumento;
    private String numeroDocumento;
    private String correo;
    private UUID organizacionId;

    public EncargadoEntity() {
        super();
        setTipoDocumento("");
        setNumeroDocumento("");
        setCorreo("");
        setOrganizacionId(UtilUUID.obtenerValorDefecto());
    }

    public EncargadoEntity(final UUID id) {
        super(id);
        setTipoDocumento("");
        setNumeroDocumento("");
        setCorreo("");
        setOrganizacionId(UtilUUID.obtenerValorDefecto());
    }

    public EncargadoEntity(
            UUID id,
            String nombre,
            String username,
            String contrasena,
            String prefijoTelefono,
            String telefono,
            String tipoDocumento,
            String numeroDocumento,
            String correo,
            UUID organizacionId) {
        super(id, nombre, username, contrasena, prefijoTelefono, telefono);
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
        setCorreo(correo);
        setOrganizacionId(organizacionId);
    }

    /**
     * Crea un EncargadoEntity “vacío” (todos los campos con valores por defecto).
     * Esto es análogo a obtenerClienteDefecto() en ClienteEntity.
     */
    public static EncargadoEntity obtenerEncargadoDefecto() {
        return new EncargadoEntity();
    }

    /**
     * Si la entidad recibida es null o su ID es el UUID por defecto, devuelve la instancia vacía;
     * en caso contrario, devuelve la misma instancia.
     * Equivalente a obtenerValorDefecto(entity) en ClienteEntity.
     */
    public static EncargadoEntity obtenerValorDefecto(final EncargadoEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, obtenerEncargadoDefecto());
    }

    // ————— GETTERS Y SETTERS —————

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        // Si es null o vacío, se asigna cadena vacía
        this.tipoDocumento = (tipoDocumento == null)
                ? ""
                : UtilObjeto.getInstance().validarCadena(tipoDocumento);
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = (numeroDocumento == null)
                ? ""
                : UtilObjeto.getInstance().validarCadena(numeroDocumento);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = (correo == null)
                ? ""
                : UtilObjeto.getInstance().validarCadena(correo);
    }

    public UUID getOrganizacionId() {
        return organizacionId;
    }

    public void setOrganizacionId(UUID organizacionId) {
        if (organizacionId == null || UtilUUID.esValorDefecto(organizacionId)) {
            this.organizacionId = UtilUUID.obtenerValorDefecto();
        } else {
            this.organizacionId = organizacionId;
        }
    }
}
