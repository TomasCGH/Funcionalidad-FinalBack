package co.edu.uco.backend.api;

import co.edu.uco.backend.businesslogic.facade.SuperficieFacade;
import co.edu.uco.backend.businesslogic.facade.impl.SuperficieFacadeImpl;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.SuperficieDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/superficies")
public class SuperficieController {

    private final SuperficieFacade superficieFacade;

    public SuperficieController() throws BackEndException {
        this.superficieFacade = new SuperficieFacadeImpl();
    }

    @GetMapping("/dummy")
    public SuperficieDTO dummy() {
        return new SuperficieDTO();
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody SuperficieDTO superficie) throws BackEndException {
        superficieFacade.registrarNuevoSuperficie(superficie);
        return new ResponseEntity<>("Superficie registrada exitosamente", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable UUID id, @RequestBody SuperficieDTO superficie) throws BackEndException {
        superficieFacade.modificarSuperficieExistente(id, superficie);
        return new ResponseEntity<>("Superficie modificada exitosamente", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable UUID id) throws BackEndException {
        superficieFacade.darBajaDefinitivamenteSuperficieExistente(id);
        return new ResponseEntity<>("Superficie eliminada exitosamente", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuperficieDTO> consultarPorId(@PathVariable UUID id) throws BackEndException {
        SuperficieDTO superficie = superficieFacade.consultarSuperficiePorId(id);
        return new ResponseEntity<>(superficie, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SuperficieDTO>> listar(@RequestBody(required = false) SuperficieDTO filtro) throws BackEndException {
        List<SuperficieDTO> superficies = superficieFacade.consultarSuperficies(filtro != null ? filtro : new SuperficieDTO());
        return new ResponseEntity<>(superficies, HttpStatus.OK);
    }
}
