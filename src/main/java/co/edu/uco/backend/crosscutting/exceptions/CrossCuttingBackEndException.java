package co.edu.uco.backend.crosscutting.exceptions;

import java.io.Serial;

public class CrossCuttingBackEndException extends BackEndException {

    @Serial
    private static final long serialVersionUID = 1L;
    private CrossCuttingBackEndException(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        super(mensajeUsuario, mensajeTecnico, excepcionRaiz, LayerException.CROSSCUTTING);
    }
    public static BackEndException reportar(String mensajeUsuario) {
        return new CrossCuttingBackEndException(mensajeUsuario, mensajeUsuario,new Exception());
    }

    public static BackEndException reportar(String mensajeUsuario, String mensajeTecnico) {
        return new CrossCuttingBackEndException(mensajeUsuario, mensajeTecnico,new Exception());
    }

    public static BackEndException reportar(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        return new CrossCuttingBackEndException(mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }




}
