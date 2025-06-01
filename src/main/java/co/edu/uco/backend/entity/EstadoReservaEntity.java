package co.edu.uco.backend.entity;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class EstadoReservaEntity {

    private UUID id;
    private String nombre;

    public EstadoReservaEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public EstadoReservaEntity(final UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public EstadoReservaEntity(final UUID id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static EstadoReservaEntity obtenerEstadoReservaDefecto() {
        return new EstadoReservaEntity();
    }

    public static EstadoReservaEntity obtenerValorDefecto(final EstadoReservaEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, obtenerEstadoReservaDefecto());
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

    public void setNombre(final String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(nombre);
    }
}

