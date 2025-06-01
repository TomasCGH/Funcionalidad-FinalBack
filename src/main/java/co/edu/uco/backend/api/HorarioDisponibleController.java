package co.edu.uco.backend.api;

import co.edu.uco.backend.businesslogic.facade.HorarioDisponibleFacade;
import co.edu.uco.backend.businesslogic.facade.impl.HorarioDisponibleFacadeImpl;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.HorarioDisponibleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/horarios-disponibles")
public class HorarioDisponibleController {

    private final HorarioDisponibleFacade horarioDisponibleFacade;

    public HorarioDisponibleController() throws BackEndException {
        this.horarioDisponibleFacade = new HorarioDisponibleFacadeImpl();
    }

    @GetMapping("/dummy")
    public HorarioDisponibleDTO getDummy() {
        return new HorarioDisponibleDTO();
    }

    @GetMapping("/{canchaId}/{horarioDisponibleId}")
    public ResponseEntity<HorarioDisponibleDTO> consultarPorId(
            @PathVariable UUID canchaId,
            @PathVariable UUID horarioDisponibleId
    ) throws BackEndException {
        var horario = horarioDisponibleFacade.consultarHorarioDisponiblePorId(canchaId, horarioDisponibleId);
        return new ResponseEntity<>(horario, HttpStatus.OK);
    }

    @GetMapping("/{canchaId}")
    public ResponseEntity<List<HorarioDisponibleDTO>> consultarTodosPorCancha(
            @PathVariable UUID canchaId
    ) throws BackEndException {
        var lista = horarioDisponibleFacade.consultarHorariosDisponiblesPorCancha(canchaId, getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("/{canchaId}")
    public ResponseEntity<String> crear(
            @PathVariable UUID canchaId,
            @RequestBody HorarioDisponibleDTO horarioDisponible
    ) throws BackEndException {
        horarioDisponibleFacade.registrarNuevoHorarioDisponible(canchaId, horarioDisponible);
        return new ResponseEntity<>("Horario disponible registrado correctamente.", HttpStatus.CREATED);
    }

    @PutMapping("/{canchaId}/{horarioDisponibleId}")
    public ResponseEntity<String> modificar(
            @PathVariable UUID canchaId,
            @PathVariable UUID horarioDisponibleId,
            @RequestBody HorarioDisponibleDTO horarioDisponible
    ) throws BackEndException {
        horarioDisponibleFacade.modificarHorarioDisponibleExistente(canchaId, horarioDisponibleId, horarioDisponible);
        return new ResponseEntity<>("Horario disponible modificado correctamente.", HttpStatus.OK);
    }

    @DeleteMapping("/{canchaId}/{horarioDisponibleId}")
    public ResponseEntity<String> eliminar(
            @PathVariable UUID canchaId,
            @PathVariable UUID horarioDisponibleId
    ) throws BackEndException {
        horarioDisponibleFacade.darBajaDefinitivamenteHorarioDisponibleExistente(canchaId, horarioDisponibleId);
        return new ResponseEntity<>("Horario disponible eliminado correctamente.", HttpStatus.OK);
    }
}
