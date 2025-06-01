package co.edu.uco.backend.entity;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class MunicipioEntity {

    private UUID id;
    private String nombre;
    private DepartamentoEntity departamento;

    public MunicipioEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setDepartamento(DepartamentoEntity.obtenerDepartamentoDefecto());
    }

    public MunicipioEntity(final UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setDepartamento(DepartamentoEntity.obtenerDepartamentoDefecto());
    }

    public MunicipioEntity(final UUID id, final String nombre, final DepartamentoEntity departamento) {
        setId(id);
        setNombre(nombre);
        setDepartamento(departamento);
    }

    public static MunicipioEntity obtenerMunicipioDefecto() {
        return new MunicipioEntity();
    }

    public static MunicipioEntity obtenerValorDefecto(final MunicipioEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, obtenerMunicipioDefecto());
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

    public DepartamentoEntity getDepartamento() {
        return departamento;
    }

    public void setDepartamento(final DepartamentoEntity departamento) {
        this.departamento = DepartamentoEntity.obtenerValorDefecto(departamento);
    }
}
