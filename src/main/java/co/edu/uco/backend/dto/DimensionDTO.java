package co.edu.uco.backend.dto;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilDouble;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class DimensionDTO {

    private UUID id;
    private double largo;
    private double ancho;

    public DimensionDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setLargo(UtilDouble.obtenerValorDefecto(0.0));
        setAncho(UtilDouble.obtenerValorDefecto(0.0));
    }

    public DimensionDTO(final UUID id) {
        setId(id);
        setLargo(UtilDouble.obtenerValorDefecto(0.0));
        setAncho(UtilDouble.obtenerValorDefecto(0.0));
    }

    public DimensionDTO(final UUID id, final double largo, final double ancho) {
        setId(id);
        setLargo(largo);
        setAncho(ancho);
    }

    public static DimensionDTO obtenerValorDefecto() {
        return new DimensionDTO();
    }

    public static DimensionDTO obtenerValorDefecto(final DimensionDTO dto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(dto, obtenerValorDefecto());
    }

    public UUID getId() {
        return id;
    }

    public double getLargo() {
        return largo;
    }

    public double getAncho() {
        return ancho;
    }

    public DimensionDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public DimensionDTO setLargo(final double largo) {
        this.largo = UtilDouble.obtenerValorDefecto(largo);
        return this;
    }

    public DimensionDTO setAncho(final double ancho) {
        this.ancho = UtilDouble.obtenerValorDefecto(ancho);
        return this;
    }
}
