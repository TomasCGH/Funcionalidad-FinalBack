package co.edu.uco.backend.entity;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class TipoCanchaEntity {

    public UUID id;
    public String nombre;
    public String jugadoresRecomendados;

    public TipoCanchaEntity() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setJugadoresRecomendados(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public TipoCanchaEntity(final UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setJugadoresRecomendados(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public TipoCanchaEntity(final UUID id, final String nombre, final String jugadoresRecomendados) {
        setId(id);
        setNombre(nombre);
        setJugadoresRecomendados(jugadoresRecomendados);
    }

    public static TipoCanchaEntity obtenerTipoCanchaDefecto() {
        return new TipoCanchaEntity();
    }

    public static TipoCanchaEntity obtenerValorDefecto(TipoCanchaEntity entity) {
        return UtilObjeto.getInstance().obtenerValorDefecto(entity, obtenerTipoCanchaDefecto());
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

    public String getJugadoresRecomendados() {
        return jugadoresRecomendados;
    }

    public void setJugadoresRecomendados(String jugadoresRecomendados) {
        this.jugadoresRecomendados = UtilTexto.getInstance().obtenerValorDefecto(jugadoresRecomendados);
    }
}

