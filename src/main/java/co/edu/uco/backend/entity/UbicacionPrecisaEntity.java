package co.edu.uco.backend.entity;

import co.edu.uco.backend.crosscutting.utilitarios.UtilDouble;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class UbicacionPrecisaEntity {

    private UUID id;
    private String direccion;
    private double latitud;
    private double longitud;
    private MunicipioEntity municipio;
    private String informacionAdicional;

    public UbicacionPrecisaEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setLatitud(UtilDouble.obtenerValorDefecto(0.0));
        setLongitud(UtilDouble.obtenerValorDefecto(0.0));
        setMunicipio(MunicipioEntity.obtenerMunicipioDefecto());
        setInformacionAdicional(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public UbicacionPrecisaEntity(final UUID id) {
        setId(id);
        setDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setLatitud(UtilDouble.obtenerValorDefecto(0.0));
        setLongitud(UtilDouble.obtenerValorDefecto(0.0));
        setMunicipio(MunicipioEntity.obtenerMunicipioDefecto());
        setInformacionAdicional(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public UbicacionPrecisaEntity(final UUID id, final String direccion, final double latitud, final double longitud,
                                  final MunicipioEntity municipio, final String informacionAdicional) {
        setId(id);
        setDireccion(direccion);
        setLatitud(latitud);
        setLongitud(longitud);
        setMunicipio(municipio);
        setInformacionAdicional(informacionAdicional);
    }

    public static UbicacionPrecisaEntity obtenerUbicacionPrecisaDefecto() {
        return new UbicacionPrecisaEntity();
    }

    public static UbicacionPrecisaEntity obtenerValorDefecto(final UbicacionPrecisaEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, obtenerUbicacionPrecisaDefecto());
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(final String direccion) {
        this.direccion = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(direccion);
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(final double latitud) {
        this.latitud = UtilDouble.obtenerValorDefecto(latitud);
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(final double longitud) {
        this.longitud = UtilDouble.obtenerValorDefecto(longitud);
    }

    public MunicipioEntity getMunicipio() {
        return municipio;
    }

    public void setMunicipio(final MunicipioEntity municipio) {
        this.municipio = MunicipioEntity.obtenerValorDefecto(municipio);
    }

    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    public void setInformacionAdicional(final String informacionAdicional) {
        this.informacionAdicional = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(informacionAdicional);
    }

}



