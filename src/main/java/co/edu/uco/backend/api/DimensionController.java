package co.edu.uco.backend.api;

import co.edu.uco.backend.businesslogic.facade.DimensionFacade;
import co.edu.uco.backend.businesslogic.facade.impl.DimensionFacadeImpl;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.DimensionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dimensiones")
public class DimensionController {

    private final DimensionFacade dimensionFacade;

    public DimensionController() throws BackEndException {
        this.dimensionFacade = new DimensionFacadeImpl();
    }

    @GetMapping("/dummy")
    public DimensionDTO getDummy() {
        return new DimensionDTO();
    }

    @GetMapping("/{canchaId}/{dimensionId}")
    public ResponseEntity<DimensionDTO> consultarPorId(
            @PathVariable("canchaId") UUID canchaId,
            @PathVariable("dimensionId") UUID dimensionId) throws BackEndException {
        var dimension = dimensionFacade.consultarDimensionPorId(canchaId, dimensionId);
        return new ResponseEntity<>(dimension, HttpStatus.OK);
    }

    @PostMapping("/{canchaId}")
    public ResponseEntity<String> crear(
            @PathVariable("canchaId") UUID canchaId,
            @RequestBody DimensionDTO dimension) throws BackEndException {
        dimensionFacade.registrarNuevaDimension(canchaId, dimension);
        var mensaje = "La nueva dimensión ha sido registrada exitosamente para la cancha con ID " + canchaId;
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    @PutMapping("/{canchaId}/{dimensionId}")
    public ResponseEntity<String> modificar(
            @PathVariable("canchaId") UUID canchaId,
            @PathVariable("dimensionId") UUID dimensionId,
            @RequestBody DimensionDTO dimension) throws BackEndException {
        dimensionFacade.modificarDimensionExistente(canchaId, dimensionId, dimension);
        var mensaje = "La dimensión con ID " + dimensionId + " se ha modificado exitosamente.";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @DeleteMapping("/{canchaId}/{dimensionId}")
    public ResponseEntity<String> eliminar(
            @PathVariable("canchaId") UUID canchaId,
            @PathVariable("dimensionId") UUID dimensionId) throws BackEndException {
        dimensionFacade.eliminarDimension(canchaId, dimensionId);
        var mensaje = "La dimensión con ID " + dimensionId + " ha sido eliminada correctamente.";
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }
}
