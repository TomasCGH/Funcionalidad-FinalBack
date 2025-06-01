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

    /** Constructor por defecto: inicializa cadenas como "" y UUID como valor por defecto. */
    public EncargadoDomain() {
        super();
        // Nunca pasamos null: utilizamos "" directamente
        setTipoDocumento("");
        setNumeroDocumento("");
        setCorreo("");
        setOrganizacionId(UtilUUID.obtenerValorDefecto());
    }

    /** Constructor que recibe sólo el ID (para buscar o eliminar). */
    public EncargadoDomain(final UUID id) {
        super(id);
        setTipoDocumento("");
        setNumeroDocumento("");
        setCorreo("");
        setOrganizacionId(UtilUUID.obtenerValorDefecto());
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
            final UUID organizacionId
    ) {
        // Llamamos al constructor padre para inicializar los atributos de UsuarioDomain
        super(id, nombre, username, contrasena, prefijoTelefono, telefono);

        // Para los Strings, si el parámetro viene null o vacío, asignamos "".
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
        setCorreo(correo);

        // Para el UUID, si es null o es valor por defecto, asignamos UtilUUID.obtenerValorDefecto()
        setOrganizacionId(organizacionId);
    }

    /** Devuelve un EncargadoDomain con valores por defecto (sin ningún null interno). */
    public static EncargadoDomain obtenerValorDefecto() {
        return new EncargadoDomain();
    }

    /** Si la instancia pasada es null, devuelve el valor por defecto; si no, la misma instancia. */
    public static EncargadoDomain obtenerValorDefecto(final EncargadoDomain domain) {
        // UtilObjeto.obtenerValorDefecto(domain, defaultDomain) solo se usa para objetos complejos
        return UtilObjeto.getInstance().obtenerValorDefecto(domain, obtenerValorDefecto());
    }

    // ———— Getters y Setters ————

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        // Nunca asignamos null: si entra null o blanco, queda como ""
        if (tipoDocumento == null || tipoDocumento.isBlank()) {
            this.tipoDocumento = "";
        } else {
            // UtilObjeto.validarCadena(...) quita espacios sobrantes, etc.
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
        // Si es null o es el UUID por defecto, asignamos el UUID por defecto
        if (organizacionId == null || UtilUUID.esValorDefecto(organizacionId)) {
            this.organizacionId = UtilUUID.obtenerValorDefecto();
        } else {
            this.organizacionId = organizacionId;
        }
    }
}
