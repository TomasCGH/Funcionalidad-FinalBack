package co.edu.uco.backend.businesslogic.assembler.organizaciondeportiva.dto;

import co.edu.uco.backend.businesslogic.assembler.DTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.cancha.dto.CanchaDTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.encargado.dto.EncargadoDTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.estadoverificacion.dto.EstadoVerificacionDTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.OrganizacionDeportivaDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.dto.OrganizacionDeportivaDTO;

import java.util.ArrayList;
import java.util.List;

public final class OrganizacionDeportivaDTOAssembler implements DTOAssembler<OrganizacionDeportivaDTO, OrganizacionDeportivaDomain> {

    private static final OrganizacionDeportivaDTOAssembler INSTANCE = new OrganizacionDeportivaDTOAssembler();

    private OrganizacionDeportivaDTOAssembler() {
        super();
    }

    public static OrganizacionDeportivaDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public OrganizacionDeportivaDTO toDTO(final OrganizacionDeportivaDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? OrganizacionDeportivaDTO.obtenerValorDefecto()
                : new OrganizacionDeportivaDTO(
                domain.getId(),
                domain.getNombre(),
                domain.getUsername(),
                domain.getTelefono(),
                domain.getPrefijoTelefono(),
                domain.getContrasena(),
                domain.getDocumentoExistencia(),
                domain.getCorreoAdministrativo(),
                domain.getPaginaWeb(),
                EncargadoDTOAssembler.getInstance().toDTOs(domain.getEncargados()),
                CanchaDTOAssembler.getInstance().toDTOs(domain.getCanchas()),
                EstadoVerificacionDTOAssembler.getInstance().toDTO(domain.getEstadoVerificacion())
        );
    }

    @Override
    public OrganizacionDeportivaDomain toDomain(final OrganizacionDeportivaDTO dto) {
        var dtoAEnsamblar = OrganizacionDeportivaDTO.obtenerValorDefecto(dto);
        return new OrganizacionDeportivaDomain(
                dtoAEnsamblar.getId(),
                dtoAEnsamblar.getNombre(),
                dtoAEnsamblar.getUsername(),
                dtoAEnsamblar.getTelefono(),
                dtoAEnsamblar.getPrefijoTelefono(),
                dtoAEnsamblar.getContrasena(),
                dtoAEnsamblar.getDocumentoExistencia(),
                dtoAEnsamblar.getCorreoAdministrativo(),
                dtoAEnsamblar.getPaginaWeb(),
                EncargadoDTOAssembler.getInstance().toDomains(dtoAEnsamblar.getEncargados()),
                CanchaDTOAssembler.getInstance().toDomains(dtoAEnsamblar.getCanchas()),
                EstadoVerificacionDTOAssembler.getInstance().toDomain(dtoAEnsamblar.getEstadoVerificacion())
        );
    }

    @Override
    public List<OrganizacionDeportivaDTO> toDTOs(final List<OrganizacionDeportivaDomain> domainList) {
        final List<OrganizacionDeportivaDTO> resultado = new ArrayList<>();
        for (OrganizacionDeportivaDomain domain : domainList) {
            resultado.add(toDTO(domain));
        }
        return resultado;
    }

    @Override
    public List<OrganizacionDeportivaDomain> toDomains(final List<OrganizacionDeportivaDTO> dtoList) {
        final List<OrganizacionDeportivaDomain> resultado = new ArrayList<>();
        for (OrganizacionDeportivaDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
