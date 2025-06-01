package co.edu.uco.backend.api;

import co.edu.uco.backend.businesslogic.facade.MunicipioFacade;
import co.edu.uco.backend.businesslogic.facade.impl.MunicipioFacadeImpl;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.MunicipioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/municipios")
public class MunicipioController {

    private final MunicipioFacade municipioFacade;

    public MunicipioController() throws BackEndException {
        this.municipioFacade = new MunicipioFacadeImpl();
    }

    @GetMapping("/dummy")
    public MunicipioDTO getDummy() {
        return new MunicipioDTO();
    }

    @GetMapping("/{departamentoId}/{municipioId}")
    public ResponseEntity<MunicipioDTO> consultarPorId(
            @PathVariable UUID departamentoId,
            @PathVariable UUID municipioId
    ) throws BackEndException {
        var dto = municipioFacade.consultarMunicipioPorId(departamentoId, municipioId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{departamentoId}")
    public ResponseEntity<List<MunicipioDTO>> consultarTodos(
            @PathVariable UUID departamentoId
    ) throws BackEndException {
        var lista = municipioFacade.consultarMunicipios(departamentoId, getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("/{departamentoId}")
    public ResponseEntity<String> registrar(
            @PathVariable UUID departamentoId,
            @RequestBody MunicipioDTO municipio
    ) throws BackEndException {
        municipioFacade.registrarNuevoMunicipio(departamentoId, municipio);
        return new ResponseEntity<>("Municipio registrado correctamente.", HttpStatus.CREATED);
    }

    @PutMapping("/{departamentoId}/{municipioId}")
    public ResponseEntity<String> modificar(
            @PathVariable UUID departamentoId,
            @PathVariable UUID municipioId,
            @RequestBody MunicipioDTO municipio
    ) throws BackEndException {
        municipioFacade.modificarMunicipioExistente(departamentoId, municipioId, municipio);
        return new ResponseEntity<>("Municipio modificado correctamente.", HttpStatus.OK);
    }

    @DeleteMapping("/{departamentoId}/{municipioId}")
    public ResponseEntity<String> eliminar(
            @PathVariable UUID departamentoId,
            @PathVariable UUID municipioId
    ) throws BackEndException {
        municipioFacade.darBajaDefinitivamenteMunicipioExistente(departamentoId, municipioId);
        return new ResponseEntity<>("Municipio eliminado correctamente.", HttpStatus.OK);
    }
}
