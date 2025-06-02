package co.edu.uco.backend.dto;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;

import java.util.UUID;

/**
 * DTO para Encargado, extiende UsuarioDTO y agrega:
 * - tipoDocumento  (CD o CE)
 * - numeroDocumento
 * - correo         (autogenerado)
 * - organizacion   (etiqueta: IMMER, INDER u OLIMPO)
 */
public final class EncargadoDTO extends UsuarioDTO {
    private String tipoDocumento;
    private String numeroDocumento;
    private String correo;
    private String organizacion;

    public EncargadoDTO() {
        super();
        initValoresDefecto();
    }

    public EncargadoDTO(final UUID id) {
        super(id);
        initValoresDefecto();
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
            final String organizacion
    ) {
        super(id, nombre, username, contrasena, prefijoTelefono, telefono);
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
        setCorreo(correo);
        setOrganizacion(organizacion);
    }

    private void initValoresDefecto() {
        this.tipoDocumento   = "";
        this.numeroDocumento = "";
        this.correo          = "";
        this.organizacion    = "";
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

    // Aunque no se envíe desde el front, se incluye para que la capa de respuesta pueda devolverlo
    public void setCorreo(String correo) {
        this.correo = UtilObjeto.getInstance().validarCadena(correo);
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = UtilObjeto.getInstance().validarCadena(organizacion).toUpperCase();
    }
}
