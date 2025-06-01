package co.edu.uco.backend.businesslogic.assembler.usuario.dto;

import co.edu.uco.backend.businesslogic.assembler.DTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.UsuarioDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.dto.UsuarioDTO;

import java.util.ArrayList;
import java.util.List;

public final class UsuarioDTOAssembler implements DTOAssembler<UsuarioDTO, UsuarioDomain> {

    private static final UsuarioDTOAssembler INSTANCE = new UsuarioDTOAssembler();

    private UsuarioDTOAssembler() {
        super();
    }

    public static UsuarioDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public UsuarioDTO toDTO(final UsuarioDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? UsuarioDTO.obtenerUsuarioDefecto()
                : new UsuarioDTO(
                domain.getId(),
                domain.getNombre(),
                domain.getUsername(),
                domain.getContrasena(),
                domain.getPrefijoTelefono(),
                domain.getTelefono()
        );
    }

    @Override
    public UsuarioDomain toDomain(final UsuarioDTO dto) {
        var dtoAEnsamblar = UsuarioDTO.obtenerValorDefecto(dto);
        return new UsuarioDomain(
                dtoAEnsamblar.getId(),
                dtoAEnsamblar.getNombre(),
                dtoAEnsamblar.getUsername(),
                dtoAEnsamblar.getContrasena(),
                dtoAEnsamblar.getPrefijoTelefono(),
                dtoAEnsamblar.getTelefono()
        ) {
            // Implementación anónima para clase abstracta (por si se quiere usar directamente)
        };
    }

    @Override
    public List<UsuarioDTO> toDTOs(final List<UsuarioDomain> domainList) {
        final List<UsuarioDTO> resultado = new ArrayList<>();
        for (UsuarioDomain domain : domainList) {
            resultado.add(toDTO(domain));
        }
        return resultado;
    }

    @Override
    public List<UsuarioDomain> toDomains(final List<UsuarioDTO> dtoList) {
        final List<UsuarioDomain> resultado = new ArrayList<>();
        for (UsuarioDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
