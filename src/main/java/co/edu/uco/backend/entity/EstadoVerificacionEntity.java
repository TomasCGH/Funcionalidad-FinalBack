package co.edu.uco.backend.entity;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class EstadoVerificacionEntity {

    private UUID id;
    private String nombre;

    public EstadoVerificacionEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public EstadoVerificacionEntity(final UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public EstadoVerificacionEntity(final UUID id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static EstadoVerificacionEntity obtenerEstadoVerificacionDefecto() {
        return new EstadoVerificacionEntity();
    }

    public static EstadoVerificacionEntity obtenerValorDefecto(final EstadoVerificacionEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, obtenerEstadoVerificacionDefecto());
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

