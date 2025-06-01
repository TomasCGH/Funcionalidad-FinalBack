package co.edu.uco.backend.crosscutting.exceptions;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;

import java.io.Serial;


public class BackEndException extends Exception{

    @Serial
    private static final long serialVersionUID = 1L;
    private String mensajeUsuario;
    private LayerException capa;

    protected BackEndException(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz, LayerException capa) {
        super(mensajeTecnico, excepcionRaiz);
        setMensajeUsuario(mensajeUsuario);
        setCapa(capa);
    }

    public String getMensajeUsuario() {
        return mensajeUsuario;
    }

    private void setMensajeUsuario(String mensajeUsuario) {
        this.mensajeUsuario = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(mensajeUsuario);
    }

    public String getMensajeTecnico() {
        return UtilTexto.getInstance().obtenerValorDefecto(getMessage());
    }

    public Throwable getExcepcionRaiz() {
        return UtilObjeto.getInstance().obtenerValorDefecto(getCause(), new Exception(getMensajeUsuario()));
    }


    public LayerException getCapa() {
        return capa;
    }

    private void setCapa(LayerException capa) {
        this.capa = UtilObjeto.getInstance().obtenerValorDefecto(capa, LayerException.GENERAL);

    }
}
