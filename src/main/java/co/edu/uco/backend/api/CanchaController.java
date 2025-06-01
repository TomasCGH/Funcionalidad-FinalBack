package co.edu.uco.backend.api;

import co.edu.uco.backend.businesslogic.facade.CanchaFacade;
import co.edu.uco.backend.businesslogic.facade.impl.CanchaFacadeImpl;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.CanchaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/canchas")
public class CanchaController {

    private final CanchaFacade canchaFacade;

    public CanchaController() throws BackEndException {
        canchaFacade = new CanchaFacadeImpl();
    }

    @GetMapping("/dummy")
    public CanchaDTO getDummy() {
        return new CanchaDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CanchaDTO> consultarPorId(@PathVariable("id") UUID id) throws BackEndException {
        var cancha = canchaFacade.consultarCanchaPorId(id);
        return new ResponseEntity<>(cancha, HttpStatus.OK);
    }

    @GetMapping("/organizacion/{orgId}/{canchaId}")
    public ResponseEntity<CanchaDTO> consultarPorOrganizacion(
            @PathVariable("orgId") UUID orgId,
            @PathVariable("canchaId") UUID canchaId) throws BackEndException {
        var cancha = canchaFacade.consultarCanchaPorOrganizacion(orgId, canchaId);
        return new ResponseEntity<>(cancha, HttpStatus.OK);
    }

    @GetMapping("/organizacion/{orgId}")
    public ResponseEntity<List<CanchaDTO>> listarPorOrganizacion(
            @PathVariable("orgId") UUID orgId) throws BackEndException {
        var lista = canchaFacade.listarCanchasPorOrganizacion(orgId, getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CanchaDTO>> consultarTodas() throws BackEndException {
        var lista = canchaFacade.consultarTodasCanchas(getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("/organizacion/{orgId}")
    public ResponseEntity<String> crear(
            @PathVariable("orgId") UUID orgId,
            @RequestBody CanchaDTO cancha) throws BackEndException {
        canchaFacade.registrarNuevaCancha(orgId, cancha);
        var mensaje = "La cancha " + cancha.getNombreCancha() + " fue registrada exitosamente.";
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    @PutMapping("/organizacion/{orgId}/{canchaId}")
    public ResponseEntity<String> modificar(
            @PathVariable("orgId") UUID orgId,
            @PathVariable("canchaId") UUID canchaId,
            @RequestBody CanchaDTO cancha) throws BackEndException {
        cancha.setId(canchaId);
        canchaFacade.modificarCanchaExistente(orgId, canchaId, cancha);
        var mensaje = "La cancha " + cancha.getNombreCancha() + " fue modificada exitosamente.";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @DeleteMapping("/organizacion/{orgId}/{canchaId}")
    public ResponseEntity<String> eliminar(
            @PathVariable("orgId") UUID orgId,
            @PathVariable("canchaId") UUID canchaId) throws BackEndException {
        var cancha = canchaFacade.consultarCanchaPorOrganizacion(orgId, canchaId);
        canchaFacade.darBajaDefinitivamenteCanchaExistente(orgId, canchaId);
        var mensaje = "La cancha " + cancha.getNombreCancha() + " fue eliminada exitosamente.";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }
}
