package co.edu.uco.backend.api;

import co.edu.uco.backend.businesslogic.facade.HorarioEspecialFacade;
import co.edu.uco.backend.businesslogic.facade.impl.HorarioEspecialFacadeImpl;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.HorarioEspecialDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/horarios-especiales")
public class HorarioEspecialController {

    private final HorarioEspecialFacade horarioEspecialFacade;

    public HorarioEspecialController() throws BackEndException {
        this.horarioEspecialFacade = new HorarioEspecialFacadeImpl();
    }

    @GetMapping("/dummy")
    public HorarioEspecialDTO getDummy() {
        return new HorarioEspecialDTO();
    }

    @GetMapping("/{canchaId}/{horarioEspecialId}")
    public ResponseEntity<HorarioEspecialDTO> consultarPorId(
            @PathVariable UUID canchaId,
            @PathVariable UUID horarioEspecialId
    ) throws BackEndException {
        var dto = horarioEspecialFacade.consultarHorarioEspecialPorId(canchaId, horarioEspecialId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{canchaId}")
    public ResponseEntity<List<HorarioEspecialDTO>> consultarTodosPorCancha(
            @PathVariable UUID canchaId
    ) throws BackEndException {
        var lista = horarioEspecialFacade.consultarHorariosEspecialesPorCancha(canchaId, getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("/{canchaId}")
    public ResponseEntity<String> registrar(
            @PathVariable UUID canchaId,
            @RequestBody HorarioEspecialDTO horarioEspecial
    ) throws BackEndException {
        horarioEspecialFacade.registrarNuevoHorarioEspecial(canchaId, horarioEspecial);
        return new ResponseEntity<>("Horario especial registrado correctamente.", HttpStatus.CREATED);
    }

    @PutMapping("/{canchaId}/{horarioEspecialId}")
    public ResponseEntity<String> modificar(
            @PathVariable UUID canchaId,
            @PathVariable UUID horarioEspecialId,
            @RequestBody HorarioEspecialDTO horarioEspecial
    ) throws BackEndException {
        horarioEspecialFacade.modificarHorarioEspecialExistente(canchaId, horarioEspecialId, horarioEspecial);
        return new ResponseEntity<>("Horario especial modificado correctamente.", HttpStatus.OK);
    }

    @DeleteMapping("/{canchaId}/{horarioEspecialId}")
    public ResponseEntity<String> eliminar(
            @PathVariable UUID canchaId,
            @PathVariable UUID horarioEspecialId
    ) throws BackEndException {
        horarioEspecialFacade.darBajaDefinitivamenteHorarioEspecialExistente(canchaId, horarioEspecialId);
        return new ResponseEntity<>("Horario especial eliminado correctamente.", HttpStatus.OK);
    }
}
