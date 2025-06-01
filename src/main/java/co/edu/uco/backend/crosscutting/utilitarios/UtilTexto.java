package co.edu.uco.backend.crosscutting.utilitarios;

public final class UtilTexto {

    private static final UtilTexto instancia = new UtilTexto();

    private static final String PATRON_SOLO_LETRAS_ESPACIOS             = "^[a-zA-ZáÁéÉíÍóÓúÚñÑ ]+$";
    private static final String PATRON_ALFANUMERICO_ESPACIOS             = "^[a-zA-ZáÁéÉíÍóÓúÚñÑ0-9 ]+$";
    private static final String PATRON_ALFANUMERICO_ESPACIOS_ESPECIALES  = "^[a-zA-ZáÁéÉíÍóÓúÚñÑ0-9 \\-\\_\\.\\,\\@]+$";
    private static final String PATRON_SOLO_NUMEROS = "^[0-9]+$";
    private static final String PATRON_PREFIJO_TELEFONO                  = "^\\+?[0-9]+$";
    public final static String VACIO = "";

    private UtilTexto() {}

    public static UtilTexto getInstance() {
        return instancia;
    }

    public boolean patronEsValido(final String valor, final String patron) {
        return obtenerValorDefecto(valor).matches(obtenerValorDefecto(patron));
    }

    public boolean contieneSoloLetrasEspacios(final String valor) {
        return patronEsValido(quitarEspaciosEnBlancoInicioFin(valor), PATRON_SOLO_LETRAS_ESPACIOS);
    }

    public boolean contieneSoloLetrasNumerosEspacios(final String valor) {
        return patronEsValido(quitarEspaciosEnBlancoInicioFin(valor), PATRON_ALFANUMERICO_ESPACIOS);
    }

    public boolean contieneSoloAlfanumericoEspaciosEspeciales(final String valor) {
        return patronEsValido(quitarEspaciosEnBlancoInicioFin(valor), PATRON_ALFANUMERICO_ESPACIOS_ESPECIALES);
    }

    public boolean contieneSoloNumeros(final String valor) {
        return patronEsValido(quitarEspaciosEnBlancoInicioFin(valor), PATRON_SOLO_NUMEROS);
    }

    /**
     * Valida prefijo telefónico: sólo acepta dígitos y opcionalmente un '+' al inicio.
     * Ejemplos válidos: "+57", "1", "12345"
     */
    public boolean contieneSoloNumerosYMas(final String valor) {
        return patronEsValido(quitarEspaciosEnBlancoInicioFin(valor), PATRON_PREFIJO_TELEFONO);
    }

    public Boolean esNula(final String valor) {
        return UtilObjeto.getInstance().esNulo(valor);
    }

    public boolean estaVacia(final String valor) {
        return VACIO.equals(quitarEspaciosEnBlancoInicioFin(valor));
    }

    public String obtenerValorDefecto() {
        return VACIO;
    }

    public String obtenerValorDefecto(final String valor) {
        return obtenerValorDefecto(valor, VACIO);
    }

    public String obtenerValorDefecto(final String valorOriginal, final String valorDefecto) {
        return UtilObjeto.getInstance().obtenerValorDefecto(valorOriginal, valorDefecto);
    }

    public String quitarEspaciosEnBlancoInicioFin(final String valor) {
        return obtenerValorDefecto(valor).trim();
    }
}
