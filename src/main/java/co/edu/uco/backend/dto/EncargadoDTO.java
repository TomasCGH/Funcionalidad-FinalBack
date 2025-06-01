package co.edu.uco.backend.dto;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class EncargadoDTO extends UsuarioDTO {

    private String correo;
    private String tipoDocumento;
    private String documento;
    private OrganizacionDeportivaDTO organizacion;

    public EncargadoDTO() {
        super();
        setCorreo(UtilTexto.getInstance().obtenerValorDefecto());
        setTipoDocumento(UtilTexto.getInstance().obtenerValorDefecto());
        setDocumento(UtilTexto.getInstance().obtenerValorDefecto());
        setOrganizacion(OrganizacionDeportivaDTO.obtenerValorDefecto());
    }

    public EncargadoDTO(final UUID id) {
        super(id);
        setCorreo(UtilTexto.getInstance().obtenerValorDefecto());
        setTipoDocumento(UtilTexto.getInstance().obtenerValorDefecto());
        setDocumento(UtilTexto.getInstance().obtenerValorDefecto());
        setOrganizacion(OrganizacionDeportivaDTO.obtenerValorDefecto());
    }

    public EncargadoDTO(
            final UUID id,
            final String nombre,
            final String username,
            final String contrasena,
            final String prefijoTelefono,
            final String telefono,
            final String correo,
            final String tipoDocumento,
            final String documento,
            final OrganizacionDeportivaDTO organizacion
    ) {
        super(id, nombre, username, contrasena, prefijoTelefono, telefono);
        setCorreo(correo);
        setTipoDocumento(tipoDocumento);
        setDocumento(documento);
        setOrganizacion(organizacion);
    }

    public static EncargadoDTO obtenerValorDefecto() {
        return new EncargadoDTO();
    }

    public static EncargadoDTO obtenerValorDefecto(final EncargadoDTO dto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(dto, obtenerValorDefecto());
    }

    public String getCorreo() {
        return correo;
    }

    public EncargadoDTO setCorreo(final String correo) {
        this.correo = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(correo);
        return this;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public EncargadoDTO setTipoDocumento(final String tipoDocumento) {
        this.tipoDocumento = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(tipoDocumento);
        return this;
    }

    public String getDocumento() {
        return documento;
    }

    public EncargadoDTO setDocumento(final String documento) {
        this.documento = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(documento);
        return this;
    }

    public OrganizacionDeportivaDTO getOrganizacion() {
        return organizacion;
    }

    public EncargadoDTO setOrganizacion(final OrganizacionDeportivaDTO organizacion) {
        this.organizacion = OrganizacionDeportivaDTO.obtenerValorDefecto(organizacion);
        return this;
    }
}
