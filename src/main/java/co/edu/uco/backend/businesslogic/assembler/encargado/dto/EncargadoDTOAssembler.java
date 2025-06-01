package co.edu.uco.backend.businesslogic.assembler.encargado.dto;

import co.edu.uco.backend.businesslogic.assembler.DTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.organizaciondeportiva.dto.OrganizacionDeportivaDTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.EncargadoDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.dto.EncargadoDTO;

import java.util.ArrayList;
import java.util.List;

public final class EncargadoDTOAssembler implements DTOAssembler<EncargadoDTO, EncargadoDomain> {

    private static final EncargadoDTOAssembler INSTANCE = new EncargadoDTOAssembler();

    private EncargadoDTOAssembler() {
        super();
    }

    public static EncargadoDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public EncargadoDTO toDTO(final EncargadoDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? EncargadoDTO.obtenerValorDefecto()
                : new EncargadoDTO(
                domain.getId(),
                domain.getNombre(),
                domain.getUsername(),
                domain.getContrasena(),
                domain.getPrefijoTelefono(),
                domain.getTelefono(),
                domain.getCorreo(),
                domain.getTipoDocumento(),
                domain.getDocumento(),
                OrganizacionDeportivaDTOAssembler.getInstance().toDTO(domain.getOrganizacion())
        );
    }

    @Override
    public EncargadoDomain toDomain(final EncargadoDTO dto) {
        var dtoAEnsamblar = EncargadoDTO.obtenerValorDefecto(dto);
        return new EncargadoDomain(
                dtoAEnsamblar.getId(),
                dtoAEnsamblar.getNombre(),
                dtoAEnsamblar.getUsername(),
                dtoAEnsamblar.getContrasena(),
                dtoAEnsamblar.getPrefijoTelefono(),
                dtoAEnsamblar.getTelefono(),
                dtoAEnsamblar.getCorreo(),
                dtoAEnsamblar.getTipoDocumento(),
                dtoAEnsamblar.getDocumento(),
                OrganizacionDeportivaDTOAssembler.getInstance().toDomain(dtoAEnsamblar.getOrganizacion())
        );
    }

    @Override
    public List<EncargadoDTO> toDTOs(final List<EncargadoDomain> domainList) {
        final List<EncargadoDTO> resultado = new ArrayList<>();
        for (EncargadoDomain domain : domainList) {
            resultado.add(toDTO(domain));
        }
        return resultado;
    }

    @Override
    public List<EncargadoDomain> toDomains(final List<EncargadoDTO> dtoList) {
        final List<EncargadoDomain> resultado = new ArrayList<>();
        for (EncargadoDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
