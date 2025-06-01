package co.edu.uco.backend.businesslogic.businesslogic.domain;

import co.edu.uco.backend.crosscutting.utilitarios.*;
import java.util.UUID;

public final class EncargadoDomain extends UsuarioDomain {

    private String correo;
    private String tipoDocumento;
    private String documento;
    private OrganizacionDeportivaDomain organizacion;

    EncargadoDomain() {
        super();
        setCorreo(UtilTexto.getInstance().obtenerValorDefecto());
        setTipoDocumento(UtilTexto.getInstance().obtenerValorDefecto());
        setDocumento(UtilTexto.getInstance().obtenerValorDefecto());
        setOrganizacion(OrganizacionDeportivaDomain.obtenerOrganizacionDeportivaDefecto());
    }

    public EncargadoDomain(final UUID id) {
        super(id);
        setCorreo(UtilTexto.getInstance().obtenerValorDefecto());
        setTipoDocumento(UtilTexto.getInstance().obtenerValorDefecto());
        setDocumento(UtilTexto.getInstance().obtenerValorDefecto());
        setOrganizacion(OrganizacionDeportivaDomain.obtenerOrganizacionDeportivaDefecto());
    }

    public EncargadoDomain(final UUID id, final String nombre, final String username,
                           final String contrasena, final String prefijoTelefono, final String telefono,
                           final String correo, final String tipoDocumento, final String documento,
                           final OrganizacionDeportivaDomain organizacion) {
        super(id, nombre, username, contrasena, prefijoTelefono, telefono);
        setCorreo(correo);
        setTipoDocumento(tipoDocumento);
        setDocumento(documento);
        setOrganizacion(organizacion);
    }

    static EncargadoDomain obtenerEncargadoDefecto() {
        return new EncargadoDomain();
    }

    static EncargadoDomain obtenerValorDefecto(final EncargadoDomain domain) {
        return UtilObjeto.getInstance().obtenerValorDefecto(domain, obtenerEncargadoDefecto());
    }

    public String getCorreo() {
        return correo;
    }

    private void setCorreo(final String correo) {
        this.correo = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(correo);
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    private void setTipoDocumento(final String tipoDocumento) {
        this.tipoDocumento = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(tipoDocumento);
    }

    public String getDocumento() {
        return documento;
    }

    private void setDocumento(final String documento) {
        this.documento = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(documento);
    }

    public OrganizacionDeportivaDomain getOrganizacion() {
        return organizacion;
    }

    private void setOrganizacion(final OrganizacionDeportivaDomain organizacion) {
        this.organizacion = OrganizacionDeportivaDomain.obtenerValorDefecto(organizacion);
    }
}
