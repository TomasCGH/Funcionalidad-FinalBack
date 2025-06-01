package co.edu.uco.backend.api;

import co.edu.uco.backend.businesslogic.facade.DepartamentoFacade;
import co.edu.uco.backend.businesslogic.facade.impl.DepartamentoFacadeImpl;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.DepartamentoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/departamentos")
public class DepartamentoController {

    private final DepartamentoFacade departamentoFacade;

    public DepartamentoController() throws BackEndException {
        departamentoFacade = new DepartamentoFacadeImpl();
    }

    @GetMapping("/dummy")
    public DepartamentoDTO getDummy() {
        return new DepartamentoDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> consultarPorId(@PathVariable("id") UUID id) throws BackEndException {
        var departamento = departamentoFacade.consultarDepartamentoPorId(id);
        return new ResponseEntity<>(departamento, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DepartamentoDTO>> consultarTodos() throws BackEndException {
        var lista = departamentoFacade.consultarDepartamentos(getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody DepartamentoDTO departamento) throws BackEndException {
        departamentoFacade.registrarNuevoDepartamento(departamento);
        var mensajeExito = "El nuevo departamento " + departamento.getNombre() + " se ha registrado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable("id") UUID id, @RequestBody DepartamentoDTO departamento) throws BackEndException {
        departamento.setId(id);
        departamentoFacade.modificarDepartamentoExistente(id, departamento);
        var mensajeExito = "El departamento " + departamento.getNombre() + " se ha modificado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") UUID id) throws BackEndException {
        var departamento = departamentoFacade.consultarDepartamentoPorId(id);
        departamentoFacade.darBajaDefinitivamenteDepartamentoExistente(id);
        var mensajeExito = "El departamento " + departamento.getNombre() + " se ha eliminado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.OK);
    }
}
