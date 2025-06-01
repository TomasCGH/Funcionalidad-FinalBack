package co.edu.uco.backend.businesslogic.businesslogic.domain;

import co.edu.uco.backend.crosscutting.utilitarios.*;
import java.util.List;
import java.util.UUID;

public final class OrganizacionDeportivaDomain extends UsuarioDomain {

    private String documentoExistencia;
    private String correoAdministrativo;
    private String paginaWeb;
    private List<EncargadoDomain> encargados;
    private List<CanchaDomain> canchas;
    private EstadoVerificacionDomain estadoVerificacion;

    OrganizacionDeportivaDomain() {
        super();
        setDocumentoExistencia(UtilTexto.getInstance().obtenerValorDefecto());
        setCorreoAdministrativo(UtilTexto.getInstance().obtenerValorDefecto());
        setPaginaWeb(UtilTexto.getInstance().obtenerValorDefecto());
        setEncargados(List.of());
        setCanchas(List.of());
        setEstadoVerificacion(EstadoVerificacionDomain.obtenerEstadoVerificacionDefecto());
    }

    public OrganizacionDeportivaDomain(final UUID id) {
        super(id);
        setDocumentoExistencia(UtilTexto.getInstance().obtenerValorDefecto());
        setCorreoAdministrativo(UtilTexto.getInstance().obtenerValorDefecto());
        setPaginaWeb(UtilTexto.getInstance().obtenerValorDefecto());
        setEncargados(List.of());
        setCanchas(List.of());
        setEstadoVerificacion(EstadoVerificacionDomain.obtenerEstadoVerificacionDefecto());
    }

    public OrganizacionDeportivaDomain(
            final UUID id,
            final String nombre,
            final String username,
            final String contrasena,
            final String prefijoTelefono,
            final String telefono,
            final String documentoExistencia,
            final String correoAdministrativo,
            final String paginaWeb,
            final List<EncargadoDomain> encargados,
            final List<CanchaDomain> canchas,
            final EstadoVerificacionDomain estadoVerificacion
    ) {
        super(id, nombre, username, contrasena, prefijoTelefono, telefono);
        setDocumentoExistencia(documentoExistencia);
        setCorreoAdministrativo(correoAdministrativo);
        setPaginaWeb(paginaWeb);
        setEncargados(encargados);
        setCanchas(canchas);
        setEstadoVerificacion(estadoVerificacion);
    }

    static OrganizacionDeportivaDomain obtenerOrganizacionDeportivaDefecto() {
        return new OrganizacionDeportivaDomain();
    }

    static OrganizacionDeportivaDomain obtenerValorDefecto(final OrganizacionDeportivaDomain domain) {
        return UtilObjeto.getInstance().obtenerValorDefecto(domain, obtenerOrganizacionDeportivaDefecto());
    }

    public String getDocumentoExistencia() {
        return documentoExistencia;
    }

    private void setDocumentoExistencia(final String documentoExistencia) {
        this.documentoExistencia = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(documentoExistencia);
    }

    public String getCorreoAdministrativo() {
        return correoAdministrativo;
    }

    private void setCorreoAdministrativo(final String correoAdministrativo) {
        this.correoAdministrativo = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(correoAdministrativo);
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    private void setPaginaWeb(final String paginaWeb) {
        this.paginaWeb = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(paginaWeb);
    }

    public List<EncargadoDomain> getEncargados() {
        return encargados;
    }

    private void setEncargados(final List<EncargadoDomain> encargados) {
        this.encargados = UtilObjeto.getInstance().obtenerValorDefecto(encargados, List.of());
    }

    public List<CanchaDomain> getCanchas() {
        return canchas;
    }

    private void setCanchas(final List<CanchaDomain> canchas) {
        this.canchas = UtilObjeto.getInstance().obtenerValorDefecto(canchas, List.of());
    }

    public EstadoVerificacionDomain getEstadoVerificacion() {
        return estadoVerificacion;
    }

    private void setEstadoVerificacion(final EstadoVerificacionDomain estadoVerificacion) {
        this.estadoVerificacion = EstadoVerificacionDomain.obtenerValorDefecto(estadoVerificacion);
    }


}
