package co.edu.uco.backend.api;

import co.edu.uco.backend.businesslogic.facade.UbicacionPrecisaFacade;
import co.edu.uco.backend.businesslogic.facade.impl.UbicacionPrecisaFacadeImpl;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.UbicacionPrecisaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/canchas/{canchaId}/ubicacion-precisa")
public class UbicacionPrecisaController {

    private final UbicacionPrecisaFacade ubicacionPrecisaFacade;

    public UbicacionPrecisaController() throws BackEndException {
        this.ubicacionPrecisaFacade = new UbicacionPrecisaFacadeImpl();
    }

    @PostMapping
    public ResponseEntity<String> registrar(
            @PathVariable UUID canchaId,
            @RequestBody UbicacionPrecisaDTO ubicacionPrecisa) throws BackEndException {
        ubicacionPrecisaFacade.registrarNuevaUbicacionPrecisa(canchaId, ubicacionPrecisa);
        return new ResponseEntity<>("Ubicación precisa registrada exitosamente", HttpStatus.CREATED);
    }

    @PutMapping("/{ubicacionPrecisaId}")
    public ResponseEntity<String> modificar(
            @PathVariable UUID canchaId,
            @PathVariable UUID ubicacionPrecisaId,
            @RequestBody UbicacionPrecisaDTO ubicacionPrecisa) throws BackEndException {
        ubicacionPrecisaFacade.modificarUbicacionPrecisaExistente(canchaId, ubicacionPrecisaId, ubicacionPrecisa);
        return new ResponseEntity<>("Ubicación precisa modificada exitosamente", HttpStatus.OK);
    }

    @DeleteMapping("/{ubicacionPrecisaId}")
    public ResponseEntity<String> eliminar(
            @PathVariable UUID canchaId,
            @PathVariable UUID ubicacionPrecisaId) throws BackEndException {
        ubicacionPrecisaFacade.eliminarUbicacionPrecisa(canchaId, ubicacionPrecisaId);
        return new ResponseEntity<>("Ubicación precisa eliminada exitosamente", HttpStatus.OK);
    }

    @GetMapping("/{ubicacionPrecisaId}")
    public ResponseEntity<UbicacionPrecisaDTO> consultarPorId(
            @PathVariable UUID canchaId,
            @PathVariable UUID ubicacionPrecisaId) throws BackEndException {
        var dto = ubicacionPrecisaFacade.consultarUbicacionPrecisaPorId(canchaId, ubicacionPrecisaId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/dummy")
    public UbicacionPrecisaDTO dummy() {
        return new UbicacionPrecisaDTO();
    }
}
