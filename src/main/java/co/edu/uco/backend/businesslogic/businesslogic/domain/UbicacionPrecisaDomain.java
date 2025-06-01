package co.edu.uco.backend.businesslogic.businesslogic.domain;

import co.edu.uco.backend.crosscutting.utilitarios.*;

import java.util.UUID;

public final class UbicacionPrecisaDomain {

    private UUID id;
    private String direccion;
    private double latitud;
    private double longitud;
    private MunicipioDomain municipio;
    private String informacionAdicional;


    UbicacionPrecisaDomain() {
        setId(UtilUUID.obtenerValorDefecto());
        setDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setLatitud(UtilDouble.obtenerValorDefecto(0.0));
        setLongitud(UtilDouble.obtenerValorDefecto(0.0));
        setMunicipio(MunicipioDomain.obtenerMunicipioDefecto());
        setInformacionAdicional(UtilTexto.getInstance().obtenerValorDefecto());

    }

    public UbicacionPrecisaDomain(final UUID id) {
        setId(id);
        setDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setLatitud(UtilDouble.obtenerValorDefecto(0.0));
        setLongitud(UtilDouble.obtenerValorDefecto(0.0));
        setMunicipio(MunicipioDomain.obtenerMunicipioDefecto());
        setInformacionAdicional(UtilTexto.getInstance().obtenerValorDefecto());

    }

    public UbicacionPrecisaDomain(final UUID id, final String direccion, final double latitud, final double longitud,
                                  final MunicipioDomain municipio, final String informacionAdicional) {
        setId(id);
        setDireccion(direccion);
        setLatitud(latitud);
        setLongitud(longitud);
        setMunicipio(municipio);
        setInformacionAdicional(informacionAdicional);

    }

    static UbicacionPrecisaDomain obtenerUbicacionPrecisaDefecto() {
        return new UbicacionPrecisaDomain();
    }

    static UbicacionPrecisaDomain obtenerValorDefecto(final UbicacionPrecisaDomain domain) {
        return UtilObjeto.getInstance().obtenerValorDefecto(domain, obtenerUbicacionPrecisaDefecto());
    }

    public UUID getId() {
        return id;
    }

    private void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public String getDireccion() {
        return direccion;
    }

    private void setDireccion(final String direccion) {
        this.direccion = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(direccion);
    }

    public double getLatitud() {
        return latitud;
    }

    private void setLatitud(final double latitud) {
        this.latitud = UtilDouble.obtenerValorDefecto(latitud);
    }

    public double getLongitud() {
        return longitud;
    }

    private void setLongitud(final double longitud) {
        this.longitud = UtilDouble.obtenerValorDefecto(longitud);
    }

    public MunicipioDomain getMunicipio() {
        return municipio;
    }

    private void setMunicipio(final MunicipioDomain municipio) {
        this.municipio = MunicipioDomain.obtenerValorDefecto(municipio);
    }

    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    private void setInformacionAdicional(final String informacionAdicional) {
        this.informacionAdicional = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(informacionAdicional);
    }


}
