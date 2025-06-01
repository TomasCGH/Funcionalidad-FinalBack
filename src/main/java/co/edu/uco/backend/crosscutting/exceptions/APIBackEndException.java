package co.edu.uco.backend.crosscutting.exceptions;

import java.io.Serial;

public class APIBackEndException extends BackEndException {

    @Serial
    private static final long serialVersionUID = 1L;

    private APIBackEndException(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        super(mensajeUsuario, mensajeTecnico, excepcionRaiz, LayerException.API);
    }
    public static BackEndException reportar(String mensajeUsuario) {
        return new APIBackEndException(mensajeUsuario, mensajeUsuario,new Exception());
    }

    public static BackEndException reportar(String mensajeUsuario, String mensajeTecnico) {
        return new APIBackEndException(mensajeUsuario, mensajeTecnico,new Exception());
    }

    public static BackEndException reportar(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        return new APIBackEndException(mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }




}
