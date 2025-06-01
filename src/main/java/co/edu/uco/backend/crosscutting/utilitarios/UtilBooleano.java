package co.edu.uco.backend.crosscutting.utilitarios;

public final class UtilBooleano {

    private static final boolean VALOR_DEFECTO = false;

    private UtilBooleano() {
        super();
    }

    public static boolean obtenerValorDefecto(final Boolean valor) {
        return UtilObjeto.getInstance().obtenerValorDefecto(valor, VALOR_DEFECTO);
    }

    public static boolean esVerdadero(final Boolean valor) {
        return obtenerValorDefecto(valor);
    }

    public static boolean esFalso(final Boolean valor) {
        return !esVerdadero(valor);
    }
}
