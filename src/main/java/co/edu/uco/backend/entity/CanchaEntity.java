package co.edu.uco.backend.entity;

import co.edu.uco.backend.crosscutting.utilitarios.*;


import java.util.List;
import java.util.UUID;

public final class CanchaEntity {

    private UUID id;
    private String nombreCancha;
    private TipoCanchaEntity tipo;
    private DimensionEntity dimensiones;
    private SuperficieEntity superficie;
    private double costoHora;
    private UbicacionPrecisaEntity ubicacion;
    private OrganizacionDeportivaEntity organizacion;
    private boolean iluminacion;
    private boolean cubierta;
    private List<HorarioDisponibleEntity> horariosDisponibles;
    private List<HorarioEspecialEntity> horariosEspeciales;

    public CanchaEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombreCancha(UtilTexto.getInstance().obtenerValorDefecto());
        setTipo(TipoCanchaEntity.obtenerTipoCanchaDefecto());
        setDimensiones(DimensionEntity.obtenerDimensionDefecto());
        setSuperficie(SuperficieEntity.obtenerSuperficieDefecto());
        setCostoHora(UtilDouble.obtenerValorDefecto(0.0));
        setUbicacion(UbicacionPrecisaEntity.obtenerUbicacionPrecisaDefecto());
        setOrganizacion(OrganizacionDeportivaEntity.obtenerOrganizacionDeportivaDefecto());
        setIluminacion(UtilBooleano.obtenerValorDefecto(false));
        setCubierta(UtilBooleano.obtenerValorDefecto(false));
        setHorariosDisponibles(List.of());
        setHorariosEspeciales(List.of());
    }

    public CanchaEntity (UUID id) {
        setId(id);
        setNombreCancha(UtilTexto.getInstance().obtenerValorDefecto());
        setTipo(TipoCanchaEntity.obtenerTipoCanchaDefecto());
        setDimensiones(DimensionEntity.obtenerDimensionDefecto());
        setSuperficie(SuperficieEntity.obtenerSuperficieDefecto());
        setCostoHora(UtilDouble.obtenerValorDefecto(0.0));
        setUbicacion(UbicacionPrecisaEntity.obtenerUbicacionPrecisaDefecto());
        setOrganizacion(OrganizacionDeportivaEntity.obtenerOrganizacionDeportivaDefecto());
        setIluminacion(UtilBooleano.obtenerValorDefecto(false));
        setCubierta(UtilBooleano.obtenerValorDefecto(false));
        setHorariosDisponibles(List.of());
        setHorariosEspeciales(List.of());
    }

    public CanchaEntity(
            final UUID id,
            final String nombreCancha,
            final TipoCanchaEntity tipo,
            final DimensionEntity dimensiones,
            final SuperficieEntity superficie,
            final double costoHora,
            final UbicacionPrecisaEntity ubicacion,
            final OrganizacionDeportivaEntity organizacion,
            final boolean iluminacion,
            final boolean cubierta,
            final List<HorarioDisponibleEntity> horariosDisponibles,
            final List<HorarioEspecialEntity> horariosEspeciales
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


    public static CanchaEntity obtenerCanchaDefecto() {
        return new CanchaEntity();
    }

    public static CanchaEntity obtenerValorDefecto(final CanchaEntity cancha) {
        return UtilObjeto.getInstance().obtenerValorDefecto(cancha, obtenerCanchaDefecto());
    }

    public UUID getId() {
        return id;
    }

    public String getNombreCancha() {
        return nombreCancha;
    }

    public TipoCanchaEntity getTipo() {
        return tipo;
    }

    public DimensionEntity getDimensiones() {
        return dimensiones;
    }

    public SuperficieEntity getSuperficie() {
        return superficie;
    }

    public double getCostoHora() {
        return costoHora;
    }

    public UbicacionPrecisaEntity getUbicacion() {
        return ubicacion;
    }

    public OrganizacionDeportivaEntity getOrganizacion() {
        return organizacion;
    }

    public boolean isIluminacion() {
        return iluminacion;
    }

    public boolean isCubierta() {
        return cubierta;
    }

    public List<HorarioDisponibleEntity> getHorariosDisponibles() {
        return horariosDisponibles;
    }

    public List<HorarioEspecialEntity> getHorariosEspeciales() {
        return horariosEspeciales;
    }

    public void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public void setNombreCancha(final String nombreCancha) {
        this.nombreCancha = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(nombreCancha);
    }

    public void setTipo(final TipoCanchaEntity tipo) {
        this.tipo = TipoCanchaEntity.obtenerValorDefecto(tipo);
    }

    public void setDimensiones(final DimensionEntity dimensiones) {
        this.dimensiones = DimensionEntity.obtenerValorDefecto(dimensiones);
    }

    public void setSuperficie(final SuperficieEntity superficie) {
        this.superficie = SuperficieEntity.obtenerValorDefecto(superficie);
    }

    public void setCostoHora(final double costoHora) {
        this.costoHora = UtilDouble.obtenerValorDefecto(costoHora);
    }

    public void setUbicacion(final UbicacionPrecisaEntity ubicacion) {
        this.ubicacion = UbicacionPrecisaEntity.obtenerValorDefecto(ubicacion);
    }

    public void setOrganizacion(final OrganizacionDeportivaEntity organizacion) {
        this.organizacion = OrganizacionDeportivaEntity.obtenerValorDefecto(organizacion);
    }

    public void setIluminacion(final boolean iluminacion) {
        this.iluminacion = UtilBooleano.obtenerValorDefecto(iluminacion);
    }

    public void setCubierta(final boolean cubierta) {
        this.cubierta = UtilBooleano.obtenerValorDefecto(cubierta);
    }

    public void setHorariosDisponibles(final List<HorarioDisponibleEntity> horariosDisponibles) {
        this.horariosDisponibles = UtilObjeto.getInstance().obtenerValorDefecto(horariosDisponibles, List.of());
    }

    public void setHorariosEspeciales(final List<HorarioEspecialEntity> horariosEspeciales) {
        this.horariosEspeciales = UtilObjeto.getInstance().obtenerValorDefecto(horariosEspeciales, List.of());
    }

}


