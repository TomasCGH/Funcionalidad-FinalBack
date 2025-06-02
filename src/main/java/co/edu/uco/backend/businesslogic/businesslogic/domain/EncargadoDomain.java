package co.edu.uco.backend.businesslogic.businesslogic.domain;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

/**
 * Dominio para Encargado, extiende UsuarioDomain y agrega:
 * - tipoDocumento  (CD o CE)
 * - numeroDocumento
 * - correo         (autogenerado)
 * - organizacionId (UUID de la organización deportiva)
 * - organizacion   (etiqueta: IMMER, INDER u OLIMPO)
 *
 * En toda parte, garantizamos que ningún atributo quede null:
 *   • Para Strings, usamos "" (cadena vacía) como valor por defecto.
 *   • Para UUID, usamos UtilUUID.obtenerValorDefecto().
 */
public final class EncargadoDomain extends UsuarioDomain {
    private String tipoDocumento;
    private String numeroDocumento;
    private String correo;
    private UUID organizacionId;
    private String organizacion;

    /** Constructor por defecto: inicializa cadenas como "" y UUID como valor por defecto. */
    public EncargadoDomain() {
        super();
        setTipoDocumento("");
        setNumeroDocumento("");
        setCorreo("");
        setOrganizacionId(UtilUUID.obtenerValorDefecto());
        setOrganizacion("");
    }

    /** Constructor que recibe sólo el ID (para buscar o eliminar). */
    public EncargadoDomain(final UUID id) {
        super(id);
        setTipoDocumento("");
        setNumeroDocumento("");
        setCorreo("");
        setOrganizacionId(UtilUUID.obtenerValorDefecto());
        setOrganizacion("");
    }

    /**
     * Constructor completo: recibe todos los campos de Encargado.
     * Validamos cada argumento y, si es null o inválido, asignamos el valor por defecto.
     */
    public EncargadoDomain(
            final UUID id,
            final String nombre,
            final String username,
            final String contrasena,
            final String prefijoTelefono,
            final String telefono,
            final String tipoDocumento,
            final String numeroDocumento,
            final String correo,
            final UUID organizacionId,
            final String organizacion
    ) {
        super(id, nombre, username, contrasena, prefijoTelefono, telefono);
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
        setCorreo(correo);
        setOrganizacionId(organizacionId);
        setOrganizacion(organizacion);
    }

    /** Devuelve un EncargadoDomain con valores por defecto (sin ningún null interno). */
    public static EncargadoDomain obtenerValorDefecto() {
        return new EncargadoDomain();
    }

    /** Si la instancia pasada es null, devuelve el valor por defecto; si no, la misma instancia. */
    public static EncargadoDomain obtenerValorDefecto(final EncargadoDomain domain) {
        return UtilObjeto.getInstance().obtenerValorDefecto(domain, obtenerValorDefecto());
    }

    // ———— Getters y Setters ————

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        if (tipoDocumento == null || tipoDocumento.isBlank()) {
            this.tipoDocumento = "";
        } else {
            this.tipoDocumento = UtilObjeto.getInstance().validarCadena(tipoDocumento);
        }
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        if (numeroDocumento == null || numeroDocumento.isBlank()) {
            this.numeroDocumento = "";
        } else {
            this.numeroDocumento = UtilObjeto.getInstance().validarCadena(numeroDocumento);
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null || correo.isBlank()) {
            this.correo = "";
        } else {
            this.correo = UtilObjeto.getInstance().validarCadena(correo);
        }
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

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        if (organizacion == null || organizacion.isBlank()) {
            this.organizacion = "";
        } else {
            this.organizacion = UtilObjeto.getInstance().validarCadena(organizacion).toUpperCase();
        }
    }
}
