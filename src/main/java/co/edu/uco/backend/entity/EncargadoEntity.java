package co.edu.uco.backend.entity;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class EncargadoEntity extends UsuarioEntity {

    private String correo;
    private String tipoDocumento;
    private String documento;
    private OrganizacionDeportivaEntity organizacion;

    public EncargadoEntity() {
        super();
        setCorreo(UtilTexto.getInstance().obtenerValorDefecto());
        setTipoDocumento(UtilTexto.getInstance().obtenerValorDefecto());
        setDocumento(UtilTexto.getInstance().obtenerValorDefecto());
        setOrganizacion(OrganizacionDeportivaEntity.obtenerOrganizacionDeportivaDefecto());
    }

    public EncargadoEntity(final UUID id) {
        super(id);
        setCorreo(UtilTexto.getInstance().obtenerValorDefecto());
        setTipoDocumento(UtilTexto.getInstance().obtenerValorDefecto());
        setDocumento(UtilTexto.getInstance().obtenerValorDefecto());
        setOrganizacion(OrganizacionDeportivaEntity.obtenerOrganizacionDeportivaDefecto());
    }

    public EncargadoEntity(final UUID id, final String nombre, final String username,
                           final String contrasena, final String prefijoTelefono, final String telefono,
                           final String correo, final String tipoDocumento, final String documento,
                           final OrganizacionDeportivaEntity organizacion) {
        super(id, nombre, username, contrasena, prefijoTelefono, telefono);
        setCorreo(correo);
        setTipoDocumento(tipoDocumento);
        setDocumento(documento);
        setOrganizacion(organizacion);
    }

    public static EncargadoEntity obtenerEncargadoDefecto() {
        return new EncargadoEntity();
    }

    public static EncargadoEntity obtenerValorDefecto(final EncargadoEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, obtenerEncargadoDefecto());
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(final String correo) {
        this.correo = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(correo);
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(final String tipoDocumento) {
        this.tipoDocumento = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(tipoDocumento);
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(final String documento) {
        this.documento = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(documento);
    }

    public OrganizacionDeportivaEntity getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(final OrganizacionDeportivaEntity organizacion) {
        this.organizacion = OrganizacionDeportivaEntity.obtenerValorDefecto(organizacion);
    }

}

