package co.edu.uco.backend.businesslogic.businesslogic.domain;

import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;

import java.util.UUID;

public final class TipoCanchaDomain {

    private UUID id;
    private String nombre;
    private String jugadoresRecomendados;

    TipoCanchaDomain() {
        setId(UtilUUID.obtenerValorDefecto());
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setJugadoresRecomendados(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public TipoCanchaDomain(final UUID id) {
        setId(id);
        setNombre(UtilTexto.getInstance().obtenerValorDefecto());
        setJugadoresRecomendados(UtilTexto.getInstance().obtenerValorDefecto());
    }

    public TipoCanchaDomain(final UUID id, final String nombre, final String jugadoresRecomendados) {
        setId(id);
        setNombre(nombre);
        setJugadoresRecomendados(jugadoresRecomendados);
    }

    static TipoCanchaDomain obtenerTipoCanchaDefecto() {
        return new TipoCanchaDomain();
    }

    static TipoCanchaDomain obtenerValorDefecto(TipoCanchaDomain domain) {
        return UtilObjeto.getInstance().obtenerValorDefecto(domain, obtenerTipoCanchaDefecto());
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
    public String getJugadoresRecomendados() { return jugadoresRecomendados; }
    private void setJugadoresRecomendados(String jugadoresRecomendados) {
        this.jugadoresRecomendados = UtilTexto.getInstance().obtenerValorDefecto(jugadoresRecomendados);
    }
}