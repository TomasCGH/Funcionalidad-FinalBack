package co.edu.uco.backend.api;

import co.edu.uco.backend.businesslogic.facade.ResenaFacade;
import co.edu.uco.backend.businesslogic.facade.impl.ResenaFacadeImpl;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.ResenaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reservas/{reservaId}/resenas")
public class ResenaController {

    private final ResenaFacade resenaFacade;

    public ResenaController() throws BackEndException {
        this.resenaFacade = new ResenaFacadeImpl();
    }

    @GetMapping("/dummy")
    public ResenaDTO dummy() {
        return new ResenaDTO();
    }

    @PostMapping
    public ResponseEntity<String> registrar(
            @PathVariable UUID reservaId,
            @RequestBody ResenaDTO resena) throws BackEndException {
        resenaFacade.registrarNuevaResena(reservaId, resena);
        return new ResponseEntity<>("Reseña registrada exitosamente.", HttpStatus.CREATED);
    }

    @PutMapping("/{resenaId}")
    public ResponseEntity<String> modificar(
            @PathVariable UUID reservaId,
            @PathVariable UUID resenaId,
            @RequestBody ResenaDTO resena) throws BackEndException {
        resenaFacade.modificarResenaExistente(reservaId, resenaId, resena);
        return new ResponseEntity<>("Reseña modificada exitosamente.", HttpStatus.OK);
    }

    @DeleteMapping("/{resenaId}")
    public ResponseEntity<String> eliminar(
            @PathVariable UUID reservaId,
            @PathVariable UUID resenaId) throws BackEndException {
        resenaFacade.darBajaDefinitivamenteResenaExistente(reservaId, resenaId);
        return new ResponseEntity<>("Reseña eliminada exitosamente.", HttpStatus.OK);
    }

    @GetMapping("/{resenaId}")
    public ResponseEntity<ResenaDTO> consultarPorId(
            @PathVariable UUID reservaId,
            @PathVariable UUID resenaId) throws BackEndException {
        var resena = resenaFacade.consultarResenaPorReserva(reservaId, resenaId);
        return new ResponseEntity<>(resena, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResenaDTO>> listar(
            @PathVariable UUID reservaId,
            @RequestBody(required = false) ResenaDTO filtro) throws BackEndException {
        var lista = resenaFacade.consultarResenas(reservaId, filtro != null ? filtro : new ResenaDTO());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
