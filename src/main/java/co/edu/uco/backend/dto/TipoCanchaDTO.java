package co.edu.uco.backend.dto;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class TipoCanchaDTO {

    private UUID id;
    private String nombre;
    private String jugadoresRecomendados;

    public TipoCanchaDTO() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setJugadoresRecomendados(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public TipoCanchaDTO(final UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setJugadoresRecomendados(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public TipoCanchaDTO(final UUID id, final String nombre, final String jugadoresRecomendados) {
        setId(id);
        setNombre(nombre);
        setJugadoresRecomendados(jugadoresRecomendados);
    }

    public static TipoCanchaDTO obtenerValorDefecto() {
        return new TipoCanchaDTO();
    }

    public static TipoCanchaDTO obtenerValorDefecto(final TipoCanchaDTO dto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(dto, obtenerValorDefecto());
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getJugadoresRecomendados() {
        return jugadoresRecomendados;
    }

    public TipoCanchaDTO setId(final UUID id) {
        this.id = UtilUUID.obtenerValorDefecto(id);
        return this;
    }

    public TipoCanchaDTO setNombre(final String nombre) {
        this.nombre = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(nombre);
        return this;
    }

    public TipoCanchaDTO setJugadoresRecomendados(final String jugadoresRecomendados) {
        this.jugadoresRecomendados = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(jugadoresRecomendados);
        return this;
    }
}
