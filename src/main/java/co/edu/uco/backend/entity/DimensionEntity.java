package co.edu.uco.backend.entity;


import co.edu.uco.backend.crosscutting.utilitarios.UtilDouble;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;


import java.util.UUID;

public final class DimensionEntity {

    private UUID id;
    private double largo;
    private double ancho;

    public DimensionEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setLargo(UtilDouble.obtenerValorDefecto(0.0));
        setAncho(UtilDouble.obtenerValorDefecto(0.0));
    }

    public DimensionEntity(final UUID id) {
        setId(id);
        setLargo(UtilDouble.obtenerValorDefecto(0.0));
        setAncho(UtilDouble.obtenerValorDefecto(0.0));
    }

    public DimensionEntity(final UUID id, final double largo, final double ancho) {
        setId(id);
        setLargo(largo);
        setAncho(ancho);
    }

    public static DimensionEntity obtenerDimensionDefecto() {
        return new DimensionEntity();
    }

    public static DimensionEntity obtenerValorDefecto(final DimensionEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, obtenerDimensionDefecto());
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(final double largo) {
        this.largo = UtilDouble.obtenerValorDefecto(largo);
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(final double ancho) {
        this.ancho = UtilDouble.obtenerValorDefecto(ancho);
    }
}

