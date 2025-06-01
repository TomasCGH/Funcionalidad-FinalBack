package co.edu.uco.backend.api;

import co.edu.uco.backend.businesslogic.facade.TipoCanchaFacade;
import co.edu.uco.backend.businesslogic.facade.impl.TipoCanchaFacadeImpl;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.TipoCanchaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tiposcanchas")
public class TipoCanchaController {

    private final TipoCanchaFacade tipoCanchaFacade;

    public TipoCanchaController() throws BackEndException {
        this.tipoCanchaFacade = new TipoCanchaFacadeImpl();
    }

    @GetMapping("/dummy")
    public TipoCanchaDTO dummy() {
        return new TipoCanchaDTO();
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody TipoCanchaDTO tipoCancha) throws BackEndException {
        tipoCanchaFacade.registrarNuevoTipoCancha(tipoCancha);
        return new ResponseEntity<>("Tipo de cancha registrado exitosamente", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable UUID id, @RequestBody TipoCanchaDTO tipoCancha) throws BackEndException {
        tipoCanchaFacade.modificarTipoCanchaExistente(id, tipoCancha);
        return new ResponseEntity<>("Tipo de cancha modificado exitosamente", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable UUID id) throws BackEndException {
        tipoCanchaFacade.darBajaDefinitivamenteTipoCanchaExistente(id);
        return new ResponseEntity<>("Tipo de cancha eliminado exitosamente", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoCanchaDTO> consultarPorId(@PathVariable UUID id) throws BackEndException {
        TipoCanchaDTO tipoCancha = tipoCanchaFacade.consultarTipoCanchaPorId(id);
        return new ResponseEntity<>(tipoCancha, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TipoCanchaDTO>> listar(@RequestBody(required = false) TipoCanchaDTO filtro) throws BackEndException {
        List<TipoCanchaDTO> tiposCanchas = tipoCanchaFacade.consultarTipoCanchas(filtro != null ? filtro : new TipoCanchaDTO());
        return new ResponseEntity<>(tiposCanchas, HttpStatus.OK);
    }
}
