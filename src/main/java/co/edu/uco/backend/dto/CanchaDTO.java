package co.edu.uco.backend.dto;

import co.edu.uco.backend.crosscutting.utilitarios.UtilBooleano;
import co.edu.uco.backend.crosscutting.utilitarios.UtilDouble;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.List;
import java.util.UUID;

public final class CanchaDTO {

    private UUID id;
    private String nombreCancha;
    private TipoCanchaDTO tipo;
    private DimensionDTO dimensiones;
    private SuperficieDTO superficie;
    private double costoHora;
    private UbicacionPrecisaDTO ubicacion;
    private OrganizacionDeportivaDTO organizacion;
    private boolean iluminacion;
    private boolean cubierta;
    private List<HorarioDisponibleDTO> horariosDisponibles;
    private List<HorarioEspecialDTO> horariosEspeciales;

    public CanchaDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombreCancha(UtilTexto.getInstance().obtenerValorDefecto());
        setTipo(TipoCanchaDTO.obtenerValorDefecto());
        setDimensiones(DimensionDTO.obtenerValorDefecto());
        setSuperficie(SuperficieDTO.obtenerValorDefecto());
        setCostoHora(UtilDouble.obtenerValorDefecto(0.0));
        setUbicacion(UbicacionPrecisaDTO.obtenerValorDefecto());
        setOrganizacion(OrganizacionDeportivaDTO.obtenerValorDefecto());
        setIluminacion(UtilBooleano.obtenerValorDefecto(false));
        setCubierta(UtilBooleano.obtenerValorDefecto(false));
        setHorariosDisponibles(List.of());
        setHorariosEspeciales(List.of());
    }

    public CanchaDTO(UUID id) {
        setId(id);
        setNombreCancha(UtilTexto.getInstance().obtenerValorDefecto());
        setTipo(TipoCanchaDTO.obtenerValorDefecto());
        setDimensiones(DimensionDTO.obtenerValorDefecto());
        setSuperficie(SuperficieDTO.obtenerValorDefecto());
        setCostoHora(UtilDouble.obtenerValorDefecto(0.0));
        setUbicacion(UbicacionPrecisaDTO.obtenerValorDefecto());
        setOrganizacion(OrganizacionDeportivaDTO.obtenerValorDefecto());
        setIluminacion(UtilBooleano.obtenerValorDefecto(false));
        setCubierta(UtilBooleano.obtenerValorDefecto(false));
        setHorariosDisponibles(List.of());
        setHorariosEspeciales(List.of());
    }

    public CanchaDTO(
            UUID id,
            String nombreCancha,
            TipoCanchaDTO tipo,
            DimensionDTO dimensiones,
            SuperficieDTO superficie,
            double costoHora,
            UbicacionPrecisaDTO ubicacion,
            OrganizacionDeportivaDTO organizacion,
            boolean iluminacion,
            boolean cubierta,
            List<HorarioDisponibleDTO> horariosDisponibles,
            List<HorarioEspecialDTO> horariosEspeciales
    ) {
        setId(id);
        setNombreCancha(nombreCancha);
        setTipo(tipo);
        setDimensiones(dimensiones);
        setSuperficie(superficie);
        setCostoHora(costoHora);
        setUbicacion(ubicacion);
        setOrganizacion(organizacion);
        setIluminacion(iluminacion);
        setCubierta(cubierta);
        setHorariosDisponibles(horariosDisponibles);
        setHorariosEspeciales(horariosEspeciales);
    }

    public static CanchaDTO obtenerValorDefecto() {
        return new CanchaDTO();
    }

    public static CanchaDTO obtenerValorDefecto(final CanchaDTO dto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(dto, obtenerValorDefecto());
    }

    public UUID getId() {
        return id;
    }

    public String getNombreCancha() {
        return nombreCancha;
    }

    public TipoCanchaDTO getTipo() {
        return tipo;
    }

    public DimensionDTO getDimensiones() {
        return dimensiones;
    }

    public SuperficieDTO getSuperficie() {
        return superficie;
    }

    public double getCostoHora() {
        return costoHora;
    }

    public UbicacionPrecisaDTO getUbicacion() {
        return ubicacion;
    }

    public OrganizacionDeportivaDTO getOrganizacion() {
        return organizacion;
    }

    public boolean isIluminacion() {
        return iluminacion;
    }

    public boolean isCubierta() {
        return cubierta;
    }

    public List<HorarioDisponibleDTO> getHorariosDisponibles() {
        return horariosDisponibles;
    }

    public List<HorarioEspecialDTO> getHorariosEspeciales() {
        return horariosEspeciales;
    }

    public CanchaDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public CanchaDTO setNombreCancha(final String nombreCancha) {
        this.nombreCancha = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(nombreCancha);
        return this;
    }

    public CanchaDTO setTipo(final TipoCanchaDTO tipo) {
        this.tipo = TipoCanchaDTO.obtenerValorDefecto(tipo);
        return this;
    }

    public CanchaDTO setDimensiones(final DimensionDTO dimensiones) {
        this.dimensiones = DimensionDTO.obtenerValorDefecto(dimensiones);
        return this;
    }

    public CanchaDTO setSuperficie(final SuperficieDTO superficie) {
        this.superficie = SuperficieDTO.obtenerValorDefecto(superficie);
        return this;
    }

    public CanchaDTO setCostoHora(final double costoHora) {
        this.costoHora = UtilDouble.obtenerValorDefecto(costoHora);
        return this;
    }

    public CanchaDTO setUbicacion(final UbicacionPrecisaDTO ubicacion) {
        this.ubicacion = UbicacionPrecisaDTO.obtenerValorDefecto(ubicacion);
        return this;
    }

    public CanchaDTO setOrganizacion(final OrganizacionDeportivaDTO organizacion) {
        this.organizacion = OrganizacionDeportivaDTO.obtenerValorDefecto(organizacion);
        return this;
    }

    public CanchaDTO setIluminacion(final boolean iluminacion) {
        this.iluminacion = UtilBooleano.obtenerValorDefecto(iluminacion);
        return this;
    }

    public CanchaDTO setCubierta(final boolean cubierta) {
        this.cubierta = UtilBooleano.obtenerValorDefecto(cubierta);
        return this;
    }

    public CanchaDTO setHorariosDisponibles(final List<HorarioDisponibleDTO> horariosDisponibles) {
        this.horariosDisponibles = UtilObjeto.getInstance().obtenerValorDefecto(horariosDisponibles, List.of());
        return this;
    }

    public CanchaDTO setHorariosEspeciales(final List<HorarioEspecialDTO> horariosEspeciales) {
        this.horariosEspeciales = UtilObjeto.getInstance().obtenerValorDefecto(horariosEspeciales, List.of());
        return this;
    }
}
