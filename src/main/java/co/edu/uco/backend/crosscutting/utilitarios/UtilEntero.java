package co.edu.uco.backend.crosscutting.utilitarios;

public final class UtilEntero {

    private static final int VALOR_DEFECTO = 0;

    private UtilEntero() {
        super();
    }

    public static int obtenerValorDefecto(final Integer valor) {
        return UtilObjeto.getInstance().obtenerValorDefecto(valor, VALOR_DEFECTO);
    }

    public static boolean esPositivo(final Integer valor) {
        return obtenerValorDefecto(valor) > 0;
    }

    public static boolean esNegativo(final Integer valor) {
        return obtenerValorDefecto(valor) < 0;
    }

    public static boolean esCero(final Integer valor) {
        return obtenerValorDefecto(valor) == 0;
    }
}
