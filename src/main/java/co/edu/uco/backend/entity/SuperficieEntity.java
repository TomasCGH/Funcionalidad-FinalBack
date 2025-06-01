package co.edu.uco.backend.entity;

import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;

import java.util.UUID;

public final class SuperficieEntity {

    private UUID id;
    private String nombre;

    public SuperficieEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public SuperficieEntity(final UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public SuperficieEntity(final UUID id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static SuperficieEntity obtenerSuperficieDefecto() {
        return new SuperficieEntity();
    }

    public static SuperficieEntity obtenerValorDefecto(SuperficieEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, obtenerSuperficieDefecto());
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(nombre);
    }
}

