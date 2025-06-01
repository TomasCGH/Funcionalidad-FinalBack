package co.edu.uco.backend.entity;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class DepartamentoEntity {

    private UUID id;
    private String nombre;

    public DepartamentoEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public DepartamentoEntity(final UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public DepartamentoEntity(final UUID id, final String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public static DepartamentoEntity obtenerDepartamentoDefecto() {
        return new DepartamentoEntity();
    }

    public static DepartamentoEntity obtenerValorDefecto(final DepartamentoEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, obtenerDepartamentoDefecto());
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

    public void setNombre(final String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(nombre);
    }
}
