package co.edu.uco.backend.businesslogic.facade;

import co.edu.uco.backend.dto.UsuarioDTO;

import java.util.List;
import java.util.UUID;

public interface UsuarioFacade {
    UsuarioDTO iniciarSesion(String username, String rawPassword, String ipAdress, String userAgent);
    void cerrarSesion(UUID usuarioId);
    void recuperarContrasena(String username);
    void cambiarContrasena(UUID usuarioId, String rawPasswordActual, String rawPasswordNueva);
    UsuarioDTO consultarUsuarioPorId(UUID usuarioId);
    List<UsuarioDTO> listarUsuarios(UsuarioDTO filtro);
}
