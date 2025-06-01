package co.edu.uco.backend.businesslogic.businesslogic.domain;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class MunicipioDomain {

    private UUID id;
    private String nombre;
    private DepartamentoDomain departamento;

    MunicipioDomain() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setDepartamento(DepartamentoDomain.obtenerDepartamentoDefecto());
    }

    public MunicipioDomain(final UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setDepartamento(DepartamentoDomain.obtenerDepartamentoDefecto());
    }

    public MunicipioDomain(final UUID id, final String nombre, DepartamentoDomain departamento) {
        setId(id);
        setNombre(nombre);
        setDepartamento(departamento);
    }

    static MunicipioDomain obtenerMunicipioDefecto() {
        return new MunicipioDomain();
    }

    static MunicipioDomain obtenerValorDefecto(MunicipioDomain domain) {
        return UtilObjeto.getInstance().obtenerValorDefecto(domain, obtenerMunicipioDefecto());
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

    public DepartamentoDomain getDepartamento() {
        return departamento;
    }

    private void setDepartamento(final DepartamentoDomain departamento) {
        this.departamento = DepartamentoDomain.obtenerValorDefecto(departamento);
    }
}