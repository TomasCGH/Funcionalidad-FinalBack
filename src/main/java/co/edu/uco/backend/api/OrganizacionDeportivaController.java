package co.edu.uco.backend.api;

import co.edu.uco.backend.businesslogic.facade.OrganizacionDeportivaFacade;
import co.edu.uco.backend.businesslogic.facade.impl.OrganizacionDeportivaFacadeImpl;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.OrganizacionDeportivaDTO;
import co.edu.uco.backend.dto.UsuarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/organizaciones")
public class OrganizacionDeportivaController {

    private final OrganizacionDeportivaFacade organizacionDeportivaFacade;

    public OrganizacionDeportivaController() throws BackEndException {
        this.organizacionDeportivaFacade = new OrganizacionDeportivaFacadeImpl();
    }

    @GetMapping("/dummy")
    public OrganizacionDeportivaDTO dummy() {
        return new OrganizacionDeportivaDTO();
    }

    @PostMapping
    public ResponseEntity<String> registrar(@RequestBody OrganizacionDeportivaDTO dto) throws BackEndException {
        organizacionDeportivaFacade.registrarNuevaOrganizacionDeportiva(dto);
        return new ResponseEntity<>("Organización deportiva registrada exitosamente.", HttpStatus.CREATED);
    }

    @PutMapping("/{orgId}")
    public ResponseEntity<String> modificar(@PathVariable UUID orgId, @RequestBody OrganizacionDeportivaDTO dto) throws BackEndException {
        organizacionDeportivaFacade.modificarOrganizacionDeportivaExistente(orgId, dto);
        return new ResponseEntity<>("Organización deportiva modificada exitosamente.", HttpStatus.OK);
    }

    @DeleteMapping("/{orgId}")
    public ResponseEntity<String> eliminar(@PathVariable UUID orgId) throws BackEndException {
        organizacionDeportivaFacade.darBajaDefinitivamenteOrganizacionDeportivaExistente(orgId);
        return new ResponseEntity<>("Organización deportiva eliminada exitosamente.", HttpStatus.OK);
    }

    @GetMapping("/{orgId}")
    public ResponseEntity<OrganizacionDeportivaDTO> consultarPorId(@PathVariable UUID orgId) throws BackEndException {
        var dto = organizacionDeportivaFacade.consultarOrganizacionDeportivaPorId(orgId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrganizacionDeportivaDTO>> listar() throws BackEndException {
        var lista = organizacionDeportivaFacade.consultarOrganizacionesDeportivas(dummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PutMapping("/{orgId}/aceptar")
    public ResponseEntity<String> aceptar(@PathVariable UUID orgId) {
        organizacionDeportivaFacade.aceptarOrganizacion(orgId);
        return new ResponseEntity<>("Organización aceptada correctamente.", HttpStatus.OK);
    }

    @PutMapping("/{orgId}/rechazar")
    public ResponseEntity<String> rechazar(@PathVariable UUID orgId) {
        organizacionDeportivaFacade.rechazarOrganizacion(orgId);
        return new ResponseEntity<>("Organización rechazada correctamente.", HttpStatus.OK);
    }

    // Métodos relacionados a Usuario
    @PostMapping("/iniciar-sesion")
    public ResponseEntity<UsuarioDTO> iniciarSesion(@RequestParam String username,
                                                    @RequestParam String password,
                                                    @RequestParam String ip,
                                                    @RequestParam String userAgent) {
        var usuario = organizacionDeportivaFacade.iniciarSesion(username, password, ip, userAgent);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping("/cerrar-sesion/{usuarioId}")
    public ResponseEntity<String> cerrarSesion(@PathVariable UUID usuarioId) {
        organizacionDeportivaFacade.cerrarSesion(usuarioId);
        return new ResponseEntity<>("Sesión cerrada correctamente.", HttpStatus.OK);
    }

    @PostMapping("/recuperar-contrasena")
    public ResponseEntity<String> recuperarContrasena(@RequestParam String username) {
        organizacionDeportivaFacade.recuperarContrasena(username);
        return new ResponseEntity<>("Correo de recuperación enviado.", HttpStatus.OK);
    }

    @PutMapping("/cambiar-contrasena/{usuarioId}")
    public ResponseEntity<String> cambiarContrasena(@PathVariable UUID usuarioId,
                                                    @RequestParam String actual,
                                                    @RequestParam String nueva) {
        organizacionDeportivaFacade.cambiarContrasena(usuarioId, actual, nueva);
        return new ResponseEntity<>("Contraseña cambiada correctamente.", HttpStatus.OK);
    }

    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<UsuarioDTO> consultarUsuario(@PathVariable UUID usuarioId) {
        var usuario = organizacionDeportivaFacade.consultarUsuarioPorId(usuarioId);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        var usuarios = organizacionDeportivaFacade.listarUsuarios(new UsuarioDTO());
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
}
