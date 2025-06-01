package co.edu.uco.backend.businesslogic.assembler.estadoverificacion.dto;

import co.edu.uco.backend.businesslogic.assembler.DTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.EstadoVerificacionDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.dto.EstadoVerificacionDTO;

import java.util.ArrayList;
import java.util.List;

public final class EstadoVerificacionDTOAssembler implements DTOAssembler<EstadoVerificacionDTO, EstadoVerificacionDomain> {

    private static final EstadoVerificacionDTOAssembler INSTANCE = new EstadoVerificacionDTOAssembler();

    private EstadoVerificacionDTOAssembler() {
        super();
    }

    public static EstadoVerificacionDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public EstadoVerificacionDTO toDTO(final EstadoVerificacionDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? EstadoVerificacionDTO.obtenerValorDefecto()
                : new EstadoVerificacionDTO(
                domain.getId(),
                domain.getNombre()
        );
    }

    @Override
    public EstadoVerificacionDomain toDomain(final EstadoVerificacionDTO dto) {
        var dtoAEnsamblar = EstadoVerificacionDTO.obtenerValorDefecto(dto);
        return new EstadoVerificacionDomain(
                dtoAEnsamblar.getId(),
                dtoAEnsamblar.getNombre()
        );
    }

    @Override
    public List<EstadoVerificacionDTO> toDTOs(final List<EstadoVerificacionDomain> domainList) {
        final List<EstadoVerificacionDTO> resultado = new ArrayList<>();
        for (EstadoVerificacionDomain domain : domainList) {
            resultado.add(toDTO(domain));
        }
        return resultado;
    }

    @Override
    public List<EstadoVerificacionDomain> toDomains(final List<EstadoVerificacionDTO> dtoList) {
        final List<EstadoVerificacionDomain> resultado = new ArrayList<>();
        for (EstadoVerificacionDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
