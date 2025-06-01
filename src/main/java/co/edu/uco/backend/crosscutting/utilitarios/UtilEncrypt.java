package co.edu.uco.backend.crosscutting.utilitarios;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.CrossCuttingBackEndException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class UtilEncrypt {
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;

    /**
     * Genera un hash PBKDF2 del password dado, con salt aleatorio.
     * @param password claro
     * @return salt:hash en Base64
     * @throws BackEndException si falla el algoritmo
     */
    public static String hash(String password) throws BackEndException {
        try {
            // 1. Generar salt
            byte[] salt = new byte[16];
            new SecureRandom().nextBytes(salt);

            // 2. Derivar la clave
            PBEKeySpec spec = new PBEKeySpec(
                    password.toCharArray(), salt, ITERATIONS, KEY_LENGTH
            );
            byte[] hash = SecretKeyFactory
                    .getInstance("PBKDF2WithHmacSHA256")
                    .generateSecret(spec)
                    .getEncoded();

            // 3. Devolver salt y hash en un único string
            String saltB64 = Base64.getEncoder().encodeToString(salt);
            String hashB64 = Base64.getEncoder().encodeToString(hash);
            return saltB64 + ":" + hashB64;

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            // Usamos tu excepción de crosscutting
            throw CrossCuttingBackEndException.reportar(
                    "Error al encriptar la contraseña","Error generando hash PBKDF2", ex
            );
        }
    }

    /**
     * Comprueba si un password en claro coincide con el hash almacenado.
     * @param password claro
     * @param stored salt:hash en Base64
     * @return true si coincide
     * @throws BackEndException si falla la comprobación
     */
    public static boolean matches(String password, String stored) throws BackEndException {
        try {
            String[] parts = stored.split(":");
            if (parts.length != 2) {
                throw CrossCuttingBackEndException.reportar("Formato de hash inválido: " + stored);
            }

            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] hash = Base64.getDecoder().decode(parts[1]);

            PBEKeySpec spec = new PBEKeySpec(
                    password.toCharArray(), salt, ITERATIONS, KEY_LENGTH
            );
            byte[] testHash = SecretKeyFactory
                    .getInstance("PBKDF2WithHmacSHA256")
                    .generateSecret(spec)
                    .getEncoded();

            if (testHash.length != hash.length) {
                return false;
            }
            for (int i = 0; i < hash.length; i++) {
                if (testHash[i] != hash[i]) {
                    return false;
                }
            }
            return true;

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw CrossCuttingBackEndException.reportar(
                    "Error al comprobar la encriptacion","Error comprobando hash PBKDF2" ,ex
            );
        }
    }

}
