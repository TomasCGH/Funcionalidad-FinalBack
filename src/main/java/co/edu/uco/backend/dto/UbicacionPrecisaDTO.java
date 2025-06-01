package co.edu.uco.backend.dto;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.backend.crosscutting.utilitarios.UtilDouble;

import java.util.UUID;

public final class UbicacionPrecisaDTO {

    private UUID id;
    private String direccion;
    private double latitud;
    private double longitud;
    private MunicipioDTO municipio;
    private String informacionAdicional;


    public UbicacionPrecisaDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setLatitud(UtilDouble.obtenerValorDefecto(0.0));
        setLongitud(UtilDouble.obtenerValorDefecto(0.0));
        setMunicipio(MunicipioDTO.obtenerValorDefecto());
        setInformacionAdicional(UtilTexto.getInstance().obtenerValorDefecto());

    }

    public UbicacionPrecisaDTO(final UUID id) {
        setId(id);
        setDireccion(UtilTexto.getInstance().obtenerValorDefecto());
        setLatitud(UtilDouble.obtenerValorDefecto(0.0));
        setLongitud(UtilDouble.obtenerValorDefecto(0.0));
        setMunicipio(MunicipioDTO.obtenerValorDefecto());
        setInformacionAdicional(UtilTexto.getInstance().obtenerValorDefecto());

    }

    public UbicacionPrecisaDTO(
            final UUID id,
            final String direccion,
            final double latitud,
            final double longitud,
            final MunicipioDTO municipio,
            final String informacionAdicional

    ) {
        setId(id);
        setDireccion(direccion);
        setLatitud(latitud);
        setLongitud(longitud);
        setMunicipio(municipio);
        setInformacionAdicional(informacionAdicional);

    }

    public static UbicacionPrecisaDTO obtenerValorDefecto() {
        return new UbicacionPrecisaDTO();
    }

    public static UbicacionPrecisaDTO obtenerValorDefecto(final UbicacionPrecisaDTO dto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(dto, obtenerValorDefecto());
    }

    public UUID getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public MunicipioDTO getMunicipio() {
        return municipio;
    }

    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    public UbicacionPrecisaDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public UbicacionPrecisaDTO setDireccion(final String direccion) {
        this.direccion = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(direccion);
        return this;
    }

    public UbicacionPrecisaDTO setLatitud(final double latitud) {
        this.latitud = UtilDouble.obtenerValorDefecto(latitud);
        return this;
    }

    public UbicacionPrecisaDTO setLongitud(final double longitud) {
        this.longitud = UtilDouble.obtenerValorDefecto(longitud);
        return this;
    }

    public UbicacionPrecisaDTO setMunicipio(final MunicipioDTO municipio) {
        this.municipio = MunicipioDTO.obtenerValorDefecto(municipio);
        return this;
    }

    public UbicacionPrecisaDTO setInformacionAdicional(final String informacionAdicional) {
        this.informacionAdicional = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(informacionAdicional);
        return this;
    }


}
