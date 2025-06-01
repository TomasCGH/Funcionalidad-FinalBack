package co.edu.uco.backend.businesslogic.businesslogic.domain;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class SuperficieDomain {

    private UUID id;
    private String nombre;

    SuperficieDomain() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public SuperficieDomain(final UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public SuperficieDomain(final UUID id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    static SuperficieDomain obtenerSuperficieDefecto() {
        return new SuperficieDomain();
    }

    static SuperficieDomain obtenerValorDefecto(SuperficieDomain domain) {
        return UtilObjeto.getInstance().obtenerValorDefecto(domain, obtenerSuperficieDefecto());
    }

    public UUID getId() {
        return id;
    }

    private void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(nombre);
    }
}