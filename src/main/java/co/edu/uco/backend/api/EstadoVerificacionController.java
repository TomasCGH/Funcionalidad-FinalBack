package co.edu.uco.backend.api;

import co.edu.uco.backend.businesslogic.facade.EstadoVerificacionFacade;
import co.edu.uco.backend.businesslogic.facade.impl.EstadoVerificacionFacadeImpl;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.EstadoVerificacionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/estado-verificaciones")
public class EstadoVerificacionController {

    private final EstadoVerificacionFacade estadoVerificacionFacade;

    public EstadoVerificacionController() throws BackEndException {
        this.estadoVerificacionFacade = new EstadoVerificacionFacadeImpl();
    }

    @GetMapping("/dummy")
    public EstadoVerificacionDTO getDummy() {
        return new EstadoVerificacionDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoVerificacionDTO> consultarPorId(@PathVariable("id") UUID id) throws BackEndException {
        var estado = estadoVerificacionFacade.consultarEstadoVerificacionPorId(id);
        return new ResponseEntity<>(estado, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EstadoVerificacionDTO>> consultarTodos() throws BackEndException {
        var lista = estadoVerificacionFacade.consultarEstadoVerificacions(getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody EstadoVerificacionDTO estadoVerificacion) throws BackEndException {
        estadoVerificacionFacade.registrarNuevoEstadoVerificacion(estadoVerificacion);
        var mensaje = "El nuevo estado de verificación se ha registrado exitosamente.";
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable("id") UUID id, @RequestBody EstadoVerificacionDTO estadoVerificacion) throws BackEndException {
        estadoVerificacionFacade.modificarEstadoVerificacionExistente(id, estadoVerificacion);
        var mensaje = "El estado de verificación con ID " + id + " se ha modificado exitosamente.";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") UUID id) throws BackEndException {
        estadoVerificacionFacade.darBajaDefinitivamenteEstadoVerificacionExistente(id);
        var mensaje = "El estado de verificación con ID " + id + " ha sido eliminado correctamente.";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }
}
