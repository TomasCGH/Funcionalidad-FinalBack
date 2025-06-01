package co.edu.uco.backend.businesslogic.businesslogic.domain;

import co.edu.uco.backend.crosscutting.utilitarios.UtilDouble;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class DimensionDomain {

    private UUID id;
    private double largo;
    private double ancho;

    DimensionDomain() {
        setId(UtilUUID.obtenerValorDefecto());
        setLargo(UtilDouble.obtenerValorDefecto(0.0));
        setAncho(UtilDouble.obtenerValorDefecto(0.0));
    }

    public DimensionDomain(final UUID id) {
        setId(id);
        setLargo(UtilDouble.obtenerValorDefecto(0.0));
        setAncho(UtilDouble.obtenerValorDefecto(0.0));
    }

    public DimensionDomain(final UUID id, final double largo, final double ancho) {
        setId(id);
        setLargo(largo);
        setAncho(ancho);
    }

    static DimensionDomain obtenerDimensionDefecto() {
        return new DimensionDomain();
    }

    static DimensionDomain obtenerValorDefecto(DimensionDomain domain) {
        return UtilObjeto.getInstance().obtenerValorDefecto(domain, obtenerDimensionDefecto());
    }

    public UUID getId() {
        return id;
    }

    private void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public double getLargo() {return largo;}
    private void setLargo(final double largo) {
        this.largo = UtilDouble.obtenerValorDefecto(largo);
    }
    public double getAncho() {return ancho;}
    private void setAncho(final double ancho) {
        this.ancho = UtilDouble.obtenerValorDefecto(ancho);
    }
}