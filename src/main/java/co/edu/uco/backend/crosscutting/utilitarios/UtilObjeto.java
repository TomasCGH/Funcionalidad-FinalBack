package co.edu.uco.backend.crosscutting.utilitarios;

import java.util.Objects;

/**
 * UtilObjeto es un utilitario genérico para validar objetos y cadenas,
 * así como para proporcionar valores por defecto cuando el objeto original es nulo o inválido.
 */
public final class UtilObjeto {

    private static UtilObjeto instancia;

    private UtilObjeto() {
        super();
    }

    public static synchronized UtilObjeto getInstance() {
        if (instancia == null) {
            instancia = new UtilObjeto();
        }
        return instancia;
    }

    /**
     * Verifica si el objeto es nulo.
     *
     * @param <O>     Tipo genérico del objeto
     * @param objeto  El objeto a verificar
     * @return true si objeto es null; false en caso contrario
     */
    public <O> boolean esNulo(final O objeto) {
        return objeto == null;
    }

    /**
     * Retorna el valor original si no es nulo; de lo contrario, retorna el valorPorDefecto.
     *
     * @param <O>             Tipo genérico del objeto
     * @param valorOriginal   El objeto a validar
     * @param valorDefecto    El objeto que se retornará en caso de que valorOriginal sea null
     * @return valorOriginal si no es null; valorDefecto en caso contrario
     */
    public <O> O obtenerValorDefecto(final O valorOriginal, final O valorDefecto) {
        return esNulo(valorOriginal) ? valorDefecto : valorOriginal;
    }

    /**
     * Valida que la cadena no sea nula; si es nula, retorna cadena vacía.
     * En todos los casos, hace trim() para eliminar espacios en los extremos.
     *
     * @param valor La cadena a validar
     * @return valor.trim() si valor != null; de lo contrario, una cadena vacía ("")
     */
    public String validarCadena(final String valor) {
        if (valor == null) {
            return "";
        }
        return valor.trim();
    }

    /**
     * Valida que el objeto no sea nulo; en este caso lanza IllegalArgumentException
     * con el mensaje proporcionado si el objeto es null.
     *
     * @param <O>    Tipo genérico del objeto
     * @param valor  El objeto a validar
     * @param nombre Nombre descriptivo para el mensaje de error
     * @return valor si no es null
     * @throws IllegalArgumentException si valor es null
     */
    public <O> O validarObligatorio(final O valor, final String nombre) {
        if (valor == null) {
            throw new IllegalArgumentException(nombre + " no puede ser nulo.");
        }
        return valor;
    }
}
