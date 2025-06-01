package co.edu.uco.backend.api;

import co.edu.uco.backend.businesslogic.facade.EncargadoFacade;
import co.edu.uco.backend.businesslogic.facade.impl.EncargadoFacadeImpl;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.EncargadoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/encargados")
public class EncargadoController {

    private final EncargadoFacade encargadoFacade;

    public EncargadoController() throws BackEndException {
        this.encargadoFacade = new EncargadoFacadeImpl();
    }

    @GetMapping("/dummy")
    public EncargadoDTO getDummy() {
        return new EncargadoDTO();
    }

    @GetMapping("/{orgId}/{encargadoId}")
    public ResponseEntity<EncargadoDTO> consultarPorId(
            @PathVariable("orgId") UUID orgId,
            @PathVariable("encargadoId") UUID encargadoId) throws BackEndException {
        var encargado = encargadoFacade.consultarEncargadoPorId(orgId, encargadoId);
        return new ResponseEntity<>(encargado, HttpStatus.OK);
    }

    @GetMapping("/{orgId}")
    public ResponseEntity<List<EncargadoDTO>> consultarTodosPorOrganizacion(
            @PathVariable("orgId") UUID orgId) throws BackEndException {
        var lista = encargadoFacade.consultarEncargadosPorOrganizacion(orgId, getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("/{orgId}")
    public ResponseEntity<String> crear(
            @PathVariable("orgId") UUID orgId,
            @RequestBody EncargadoDTO encargado) throws BackEndException {
        encargadoFacade.registrarNuevoEncargado(orgId, encargado);
        var mensaje = "El nuevo encargado ha sido registrado exitosamente en la organización con ID " + orgId;
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    @PutMapping("/{orgId}/{encargadoId}")
    public ResponseEntity<String> modificar(
            @PathVariable("orgId") UUID orgId,
            @PathVariable("encargadoId") UUID encargadoId,
            @RequestBody EncargadoDTO encargado) throws BackEndException {
        encargadoFacade.modificarEncargadoExistente(orgId, encargadoId, encargado);
        var mensaje = "El encargado con ID " + encargadoId + " se ha modificado exitosamente.";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @DeleteMapping("/{orgId}/{encargadoId}")
    public ResponseEntity<String> eliminar(
            @PathVariable("orgId") UUID orgId,
            @PathVariable("encargadoId") UUID encargadoId) throws BackEndException {
        encargadoFacade.darBajaDefinitivamenteEncargadoExistente(orgId, encargadoId);
        var mensaje = "El encargado con ID " + encargadoId + " ha sido eliminado correctamente.";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }
}
