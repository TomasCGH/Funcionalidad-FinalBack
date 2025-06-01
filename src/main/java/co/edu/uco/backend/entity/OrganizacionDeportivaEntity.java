package co.edu.uco.backend.entity;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;

import java.util.List;
import java.util.UUID;

public final class OrganizacionDeportivaEntity extends UsuarioEntity {


    private String documentoExistencia;
    private String correoAdministrativo;
    private String paginaWeb;
    private List<EncargadoEntity> encargados;
    private List<CanchaEntity> canchas;
    private EstadoVerificacionEntity estadoVerificacion;

    public OrganizacionDeportivaEntity() {
        super();
        setDocumentoExistencia(UtilTexto.getInstance().obtenerValorDefecto());
        setCorreoAdministrativo(UtilTexto.getInstance().obtenerValorDefecto());
        setPaginaWeb(UtilTexto.getInstance().obtenerValorDefecto());
        setEncargados(List.of());
        setCanchas(List.of());
        setEstadoVerificacion(EstadoVerificacionEntity.obtenerEstadoVerificacionDefecto());
    }

    public OrganizacionDeportivaEntity(final UUID id) {
        super(id);
        setDocumentoExistencia(UtilTexto.getInstance().obtenerValorDefecto());
        setCorreoAdministrativo(UtilTexto.getInstance().obtenerValorDefecto());
        setPaginaWeb(UtilTexto.getInstance().obtenerValorDefecto());
        setEncargados(List.of());
        setCanchas(List.of());
        setEstadoVerificacion(EstadoVerificacionEntity.obtenerEstadoVerificacionDefecto());
    }

    public OrganizacionDeportivaEntity(
            final UUID id,
            final String nombre,
            final String username,
            final String contrasena,
            final String prefijoTelefono,
            final String telefono,
            final String documentoExistencia,
            final String correoAdministrativo,
            final String paginaWeb,
            final List<EncargadoEntity> encargados,
            final List<CanchaEntity> canchas,
            final EstadoVerificacionEntity estadoVerificacion
    ) {
        super(id, nombre, username, contrasena, prefijoTelefono, telefono);
        setDocumentoExistencia(documentoExistencia);
        setCorreoAdministrativo(correoAdministrativo);
        setPaginaWeb(paginaWeb);
        setEncargados(encargados);
        setCanchas(canchas);
        setEstadoVerificacion(estadoVerificacion);
    }

    public static OrganizacionDeportivaEntity obtenerOrganizacionDeportivaDefecto() {
        return new OrganizacionDeportivaEntity();
    }

    public static OrganizacionDeportivaEntity obtenerValorDefecto(final OrganizacionDeportivaEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, obtenerOrganizacionDeportivaDefecto());
    }

    public String getDocumentoExistencia() {
        return documentoExistencia;
    }

    public void setDocumentoExistencia(final String documentoExistencia) {
        this.documentoExistencia = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(documentoExistencia);
    }

    public String getCorreoAdministrativo() {
        return correoAdministrativo;
    }

    public void setCorreoAdministrativo(final String correoAdministrativo) {
        this.correoAdministrativo = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(correoAdministrativo);
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(final String paginaWeb) {
        this.paginaWeb = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(paginaWeb);
    }

    public List<EncargadoEntity> getEncargados() {
        return encargados;
    }

    public void setEncargados(final List<EncargadoEntity> encargados) {
        this.encargados = UtilObjeto.getInstance().obtenerValorDefecto(encargados, List.of());
    }

    public List<CanchaEntity> getCanchas() {
        return canchas;
    }

    public void setCanchas(final List<CanchaEntity> canchas) {
        this.canchas = UtilObjeto.getInstance().obtenerValorDefecto(canchas, List.of());
    }

    public EstadoVerificacionEntity getEstadoVerificacion() {
        return estadoVerificacion;
    }

    public void setEstadoVerificacion(final EstadoVerificacionEntity estadoVerificacion) {
        this.estadoVerificacion = EstadoVerificacionEntity.obtenerValorDefecto(estadoVerificacion);
    }
}

