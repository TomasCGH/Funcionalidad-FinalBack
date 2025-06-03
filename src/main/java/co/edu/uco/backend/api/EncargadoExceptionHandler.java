package co.edu.uco.backend.api;

import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackages = "co.edu.uco.backend.api")
public class EncargadoExceptionHandler {

    @ExceptionHandler(BusinessLogicBackEndException.class)
    public ResponseEntity<Map<String, String>> manejarExcepcionNegocio(BusinessLogicBackEndException ex) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", ex.getMessage()); // ✅ Angular esperará esto
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> manejarExcepcionGeneral(Exception ex) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Error inesperado del servidor.");
        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
