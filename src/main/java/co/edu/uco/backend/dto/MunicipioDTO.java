package co.edu.uco.backend.dto;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class MunicipioDTO {

    private UUID id;
    private String nombre;
    private DepartamentoDTO departamento;

    public MunicipioDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setDepartamento(DepartamentoDTO.obtenerValorDefecto());
    }

    public MunicipioDTO(final UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setDepartamento(DepartamentoDTO.obtenerValorDefecto());
    }

    public MunicipioDTO(final UUID id, final String nombre, final DepartamentoDTO departamento) {
        setId(id);
        setNombre(nombre);
        setDepartamento(departamento);
    }

    public static MunicipioDTO obtenerValorDefecto() {
        return new MunicipioDTO();
    }

    public static MunicipioDTO obtenerValorDefecto(final MunicipioDTO dto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(dto, obtenerValorDefecto());
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

    public MunicipioDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public MunicipioDTO setNombre(final String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(nombre);
        return this;
    }

    public MunicipioDTO setDepartamento(final DepartamentoDTO departamento) {
        this.departamento = DepartamentoDTO.obtenerValorDefecto(departamento);
        return this;
    }
}
