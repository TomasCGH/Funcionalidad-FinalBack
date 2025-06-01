package co.edu.uco.backend.dto;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.List;
import java.util.UUID;

public final class OrganizacionDeportivaDTO extends UsuarioDTO {

    private String documentoExistencia;
    private String correoAdministrativo;
    private String paginaWeb;
    private List<EncargadoDTO> encargados;
    private List<CanchaDTO> canchas;
    private EstadoVerificacionDTO estadoVerificacion;

    public OrganizacionDeportivaDTO() {
        super();
        setDocumentoExistencia(UtilTexto.getInstance().obtenerValorDefecto());
        setCorreoAdministrativo(UtilTexto.getInstance().obtenerValorDefecto());
        setPaginaWeb(UtilTexto.getInstance().obtenerValorDefecto());
        setEncargados(List.of());
        setCanchas(List.of());
        setEstadoVerificacion(EstadoVerificacionDTO.obtenerValorDefecto());
    }

    public OrganizacionDeportivaDTO(final UUID id) {
        super(id);
        setDocumentoExistencia(UtilTexto.getInstance().obtenerValorDefecto());
        setCorreoAdministrativo(UtilTexto.getInstance().obtenerValorDefecto());
        setPaginaWeb(UtilTexto.getInstance().obtenerValorDefecto());
        setEncargados(List.of());
        setCanchas(List.of());
        setEstadoVerificacion(EstadoVerificacionDTO.obtenerValorDefecto());
    }

    public OrganizacionDeportivaDTO(
            final UUID id,
            final String nombre,
            final String username,
            final String contrasena,
            final String prefijoTelefono,
            final String telefono,
            final String documentoExistencia,
            final String correoAdministrativo,
            final String paginaWeb,
            final List<EncargadoDTO> encargados,
            final List<CanchaDTO> canchas,
            final EstadoVerificacionDTO estadoVerificacion
    ) {
        super(id, nombre, username, contrasena, prefijoTelefono, telefono);
        setDocumentoExistencia(documentoExistencia);
        setCorreoAdministrativo(correoAdministrativo);
        setPaginaWeb(paginaWeb);
        setEncargados(encargados);
        setCanchas(canchas);
        setEstadoVerificacion(estadoVerificacion);
    }

    public static OrganizacionDeportivaDTO obtenerValorDefecto() {
        return new OrganizacionDeportivaDTO();
    }

    public static OrganizacionDeportivaDTO obtenerValorDefecto(final OrganizacionDeportivaDTO dto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(dto, obtenerValorDefecto());
    }

    public String getDocumentoExistencia() {
        return documentoExistencia;
    }

    public String getCorreoAdministrativo() {
        return correoAdministrativo;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public List<EncargadoDTO> getEncargados() {
        return encargados;
    }

    public List<CanchaDTO> getCanchas() {
        return canchas;
    }

    public EstadoVerificacionDTO getEstadoVerificacion() {
        return estadoVerificacion;
    }

    public OrganizacionDeportivaDTO setDocumentoExistencia(final String documentoExistencia) {
        this.documentoExistencia = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(documentoExistencia);
        return this;
    }

    public OrganizacionDeportivaDTO setCorreoAdministrativo(final String correoAdministrativo) {
        this.correoAdministrativo = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(correoAdministrativo);
        return this;
    }

    public OrganizacionDeportivaDTO setPaginaWeb(final String paginaWeb) {
        this.paginaWeb = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(paginaWeb);
        return this;
    }

    public OrganizacionDeportivaDTO setEncargados(final List<EncargadoDTO> encargados) {
        this.encargados = UtilObjeto.getInstance().obtenerValorDefecto(encargados, List.of());
        return this;
    }

    public OrganizacionDeportivaDTO setCanchas(final List<CanchaDTO> canchas) {
        this.canchas = UtilObjeto.getInstance().obtenerValorDefecto(canchas, List.of());
        return this;
    }

    public OrganizacionDeportivaDTO setEstadoVerificacion(final EstadoVerificacionDTO estadoVerificacion) {
        this.estadoVerificacion = EstadoVerificacionDTO.obtenerValorDefecto(estadoVerificacion);
        return this;
    }
}
