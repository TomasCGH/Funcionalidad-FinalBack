package co.edu.uco.backend.api;

import co.edu.uco.backend.businesslogic.facade.ClienteFacade;
import co.edu.uco.backend.businesslogic.facade.impl.ClienteFacadeImpl;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.ClienteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteFacade clienteFacade;

    public ClienteController() throws BackEndException {
        clienteFacade = new ClienteFacadeImpl();
    }
    @GetMapping("/dummy")
    public ClienteDTO getDummy() {
        return new ClienteDTO();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> consultarPorId(@PathVariable("id") UUID id) throws BackEndException {
        var cliente = clienteFacade.consultarClientePorId(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> consultarTodos() throws BackEndException {
        var lista = clienteFacade.consultarClientes(getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody ClienteDTO cliente) throws BackEndException {
        clienteFacade.registrarNuevoCliente(cliente);
        var mensajeExito = "El  nuevo cliente " + cliente.getNombre()+ " se ha registrado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable("id") UUID id,@RequestBody ClienteDTO cliente) throws BackEndException {
        cliente.setId(id);
        clienteFacade.modificarClienteExistente(id, cliente);
        var mensajeExito = "El cliente " + cliente.getNombre()+ " se ha modificado exitosamente";
        return new ResponseEntity<>(mensajeExito,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") UUID id) throws BackEndException {
        var cliente = clienteFacade.consultarClientePorId(id);
        clienteFacade.darBajaDefinitivamenteClienteExistente(id);
        var mensajeExito = "El cliente " + cliente.getNombre()+ " se ha eliminado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.OK);
    }
}
