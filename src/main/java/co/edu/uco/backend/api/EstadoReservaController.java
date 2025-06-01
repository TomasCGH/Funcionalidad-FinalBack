package co.edu.uco.backend.api;

import co.edu.uco.backend.businesslogic.facade.EstadoReservaFacade;
import co.edu.uco.backend.businesslogic.facade.impl.EstadoReservaFacadeImpl;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.EstadoReservaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/estado-reservas")
public class EstadoReservaController {

    private final EstadoReservaFacade estadoReservaFacade;

    public EstadoReservaController() throws BackEndException {
        this.estadoReservaFacade = new EstadoReservaFacadeImpl();
    }

    @GetMapping("/dummy")
    public EstadoReservaDTO getDummy() {
        return new EstadoReservaDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoReservaDTO> consultarPorId(@PathVariable("id") UUID id) throws BackEndException {
        var estadoReserva = estadoReservaFacade.consultarEstadoReservaPorId(id);
        return new ResponseEntity<>(estadoReserva, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EstadoReservaDTO>> consultarTodos() throws BackEndException {
        var lista = estadoReservaFacade.consultarEstadoReservas(getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody EstadoReservaDTO estadoReserva) throws BackEndException {
        estadoReservaFacade.registrarNuevoEstadoReserva(estadoReserva);
        var mensaje = "El nuevo estado de reserva se ha registrado exitosamente.";
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable("id") UUID id, @RequestBody EstadoReservaDTO estadoReserva) throws BackEndException {
        estadoReservaFacade.modificarEstadoReservaExistente(id, estadoReserva);
        var mensaje = "El estado de reserva con ID " + id + " se ha modificado exitosamente.";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") UUID id) throws BackEndException {
        estadoReservaFacade.darBajaDefinitivamenteEstadoReservaExistente(id);
        var mensaje = "El estado de reserva con ID " + id + " ha sido eliminado correctamente.";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }
}
