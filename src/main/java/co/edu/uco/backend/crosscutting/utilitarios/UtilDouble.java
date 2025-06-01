package co.edu.uco.backend.crosscutting.utilitarios;

public final class UtilDouble {

    private static final double VALOR_DEFECTO = 0.0;

    private UtilDouble() {
        super();
    }

    public static double obtenerValorDefecto(final Double valor) {
        return UtilObjeto.getInstance().obtenerValorDefecto(valor, VALOR_DEFECTO);
    }

    /** Devuelve true si el valor es estrictamente mayor que 0. */
    public static boolean esPositivo(final Double valor) {
        return obtenerValorDefecto(valor) > 0.0;
    }

    /**
     * Devuelve true si valor está en [minimo, maximo].
     */
    public static boolean estaEnRango(final Double valor, final double minimo, final double maximo) {
        double v = obtenerValorDefecto(valor);
        return v >= minimo && v <= maximo;
    }
}
