package co.edu.uco.backend.api;

import co.edu.uco.backend.businesslogic.facade.EncargadoFacade;
import co.edu.uco.backend.businesslogic.facade.impl.EncargadoFacadeImpl;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.EncargadoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    /**
     * Captura el caso en que no venga un body válido en la petición (JSON ausente o mal formado).
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleMissingBody(HttpMessageNotReadableException ex) {
        String mensajeUsuario = "Se debe enviar un cuerpo (body) en formato JSON.";
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(mensajeUsuario);
    }

    /**
     * Punto de prueba: devuelve un DTO vacío de Encargado.
     */
    @GetMapping("/dummy")
    public EncargadoDTO getDummy() {
        return new EncargadoDTO();
    }

    /**
     * Consulta un encargado por su ID.
     * @param id UUID del encargado
     * @return ResponseEntity con el EncargadoDTO y estado 200 OK
     */
    @GetMapping("/{id}")
    public ResponseEntity<EncargadoDTO> consultarPorId(@PathVariable("id") UUID id) throws BackEndException {
        EncargadoDTO dto = encargadoFacade.consultarEncargadoPorId(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * Lista todos los encargados que cumplan el filtro (aquí se envía un DTO vacío para obtener todos).
     * @return ResponseEntity con la lista de EncargadoDTO y estado 200 OK
     */
    @GetMapping
    public ResponseEntity<List<EncargadoDTO>> consultarTodos() throws BackEndException {
        List<EncargadoDTO> lista = encargadoFacade.consultarEncargados(getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    /**
     * Crea un nuevo encargado a partir del JSON en el cuerpo de la petición.
     * @param encargadoDTO datos del nuevo encargado
     * @return ResponseEntity con mensaje de éxito y estado 200 OK
     */
    @PostMapping
    public ResponseEntity<String> crear(@RequestBody EncargadoDTO encargadoDTO) throws BackEndException {
        encargadoFacade.registrarNuevoEncargado(encargadoDTO);
        String mensajeExito = "El nuevo encargado '" + encargadoDTO.getNombre() +
                "' se ha registrado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.OK);
    }

    /**
     * Modifica un encargado existente. El ID va en la URL y el resto de campos en el JSON.
     * @param id UUID del encargado a modificar
     * @param encargadoDTO campos actualizados
     * @return ResponseEntity con mensaje de éxito y estado 200 OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> modificar(@PathVariable("id") UUID id,
                                            @RequestBody EncargadoDTO encargadoDTO) throws BackEndException {
        // Asegurarse de propagar el ID al DTO antes de invocar la capa de negocio
        encargadoDTO.setId(id);
        encargadoFacade.modificarEncargadoExistente(id, encargadoDTO);
        String mensajeExito = "El encargado '" + encargadoDTO.getNombre() +
                "' se ha modificado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.OK);
    }

    /**
     * Elimina definitivamente un encargado según el ID en la URL.
     * @param id UUID del encargado a eliminar
     * @return ResponseEntity con mensaje de éxito y estado 200 OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") UUID id) throws BackEndException {
        // Para devolver el nombre en el mensaje de éxito, primero lo consultamos
        EncargadoDTO dtoExistente = encargadoFacade.consultarEncargadoPorId(id);
        encargadoFacade.darBajaDefinitivamenteEncargadoExistente(id);
        String mensajeExito = "El encargado '" + dtoExistente.getNombre() +
                "' se ha eliminado exitosamente";
        return new ResponseEntity<>(mensajeExito, HttpStatus.OK);
    }
}
