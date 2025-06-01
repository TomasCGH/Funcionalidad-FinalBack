package co.edu.uco.backend.businesslogic.businesslogic.domain;

import co.edu.uco.backend.crosscutting.utilitarios.UtilBooleano;
import co.edu.uco.backend.crosscutting.utilitarios.UtilDouble;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;
import java.util.List;
import java.util.UUID;


public final class CanchaDomain {
    private UUID id;
    private String nombreCancha;
    private TipoCanchaDomain tipo;
    private DimensionDomain dimensiones;
    private SuperficieDomain superficie;
    private double costoHora;
    private UbicacionPrecisaDomain ubicacion;
    private OrganizacionDeportivaDomain organizacion;
    private boolean iluminacion;
    private boolean cubierta;
    private List<HorarioDisponibleDomain> horariosDisponibles;
    private List<HorarioEspecialDomain> horariosEspeciales;

    CanchaDomain() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombreCancha(UtilTexto.getInstance().obtenerValorDefecto());
        setTipo(TipoCanchaDomain.obtenerTipoCanchaDefecto());
        setDimensiones(DimensionDomain.obtenerDimensionDefecto());
        setSuperficie(SuperficieDomain.obtenerSuperficieDefecto());
        setCostoHora(UtilDouble.obtenerValorDefecto(0.0));
        setUbicacion(UbicacionPrecisaDomain.obtenerUbicacionPrecisaDefecto());
        setOrganizacion(OrganizacionDeportivaDomain.obtenerOrganizacionDeportivaDefecto());
        setIluminacion(UtilBooleano.obtenerValorDefecto(false));
        setCubierta(UtilBooleano.obtenerValorDefecto(false));
        setHorariosDisponibles(List.of());
        setHorariosEspeciales(List.of());
    }


    public CanchaDomain(final UUID id) {
        setId(id);
        setNombreCancha(UtilTexto.getInstance().obtenerValorDefecto());
        setTipo(TipoCanchaDomain.obtenerTipoCanchaDefecto());
        setDimensiones(DimensionDomain.obtenerDimensionDefecto());
        setSuperficie(SuperficieDomain.obtenerSuperficieDefecto());
        setCostoHora(UtilDouble.obtenerValorDefecto(0.0));
        setUbicacion(UbicacionPrecisaDomain.obtenerUbicacionPrecisaDefecto());
        setOrganizacion(OrganizacionDeportivaDomain.obtenerOrganizacionDeportivaDefecto());
        setIluminacion(UtilBooleano.obtenerValorDefecto(false));
        setCubierta(UtilBooleano.obtenerValorDefecto(false));
        setHorariosDisponibles(List.of());
        setHorariosEspeciales(List.of());
    }

    public CanchaDomain(
            final UUID id,
            final String nombreCancha,
            final TipoCanchaDomain tipo,
            final DimensionDomain dimensiones,
            final SuperficieDomain superficie,
            final double costoHora,
            final UbicacionPrecisaDomain ubicacion,
            final OrganizacionDeportivaDomain organizacion,
            final boolean iluminacion,
            final boolean cubierta,
            final List<HorarioDisponibleDomain> horariosDisponibles,
            final List<HorarioEspecialDomain> horariosEspeciales
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


    static CanchaDomain obtenerCanchaDefecto() {
        return new CanchaDomain();
    }

    static CanchaDomain obtenerValorDefecto(final CanchaDomain domain) {
        return UtilObjeto.getInstance().obtenerValorDefecto(domain, obtenerCanchaDefecto());
    }

    public UUID getId() {
        return id;
    }

    public String getNombreCancha() {
        return nombreCancha;
    }

    public TipoCanchaDomain getTipo() {
        return tipo;
    }

    public DimensionDomain getDimensiones() {
        return dimensiones;
    }

    public SuperficieDomain getSuperficie() {
        return superficie;
    }

    public double getCostoHora() {
        return costoHora;
    }

    public UbicacionPrecisaDomain getUbicacion() {
        return ubicacion;
    }

    public OrganizacionDeportivaDomain getOrganizacion() {
        return organizacion;
    }

    public boolean isIluminacion() {
        return iluminacion;
    }

    public boolean isCubierta() {
        return cubierta;
    }

    public List<HorarioDisponibleDomain> getHorariosDisponibles() {
        return horariosDisponibles;
    }

    public List<HorarioEspecialDomain> getHorariosEspeciales() {
        return horariosEspeciales;
    }

    private void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    private void setNombreCancha(final String nombreCancha) {
        this.nombreCancha = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(nombreCancha);
    }

    private void setTipo(final TipoCanchaDomain tipo) {
        this.tipo = TipoCanchaDomain.obtenerValorDefecto(tipo);
    }

    private void setDimensiones(final DimensionDomain dimensiones) {
        this.dimensiones = DimensionDomain.obtenerValorDefecto(dimensiones);
    }

    private void setSuperficie(final SuperficieDomain superficie) {
        this.superficie = SuperficieDomain.obtenerValorDefecto(superficie);
    }

    private void setCostoHora(final double costoHora) {
        this.costoHora = UtilDouble.obtenerValorDefecto(costoHora);
    }

    private void setUbicacion(final UbicacionPrecisaDomain ubicacion) {
        this.ubicacion = UbicacionPrecisaDomain.obtenerValorDefecto(ubicacion);
    }

    private void setOrganizacion(final OrganizacionDeportivaDomain organizacion) {
        this.organizacion = OrganizacionDeportivaDomain.obtenerValorDefecto(organizacion);
    }

    private void setIluminacion(final boolean iluminacion) {
        this.iluminacion = UtilBooleano.obtenerValorDefecto(iluminacion);
    }

    private void setCubierta(final boolean cubierta) {
        this.cubierta = UtilBooleano.obtenerValorDefecto(cubierta);
    }

    private void setHorariosDisponibles(final List<HorarioDisponibleDomain> horariosDisponibles) {
        this.horariosDisponibles = UtilObjeto.getInstance().obtenerValorDefecto(horariosDisponibles, List.of());
    }

    private void setHorariosEspeciales(final List<HorarioEspecialDomain> horariosEspeciales) {
        this.horariosEspeciales = UtilObjeto.getInstance().obtenerValorDefecto(horariosEspeciales, List.of());
    }
}
