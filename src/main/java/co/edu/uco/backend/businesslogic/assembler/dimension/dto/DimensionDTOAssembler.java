package co.edu.uco.backend.businesslogic.assembler.dimension.dto;

import co.edu.uco.backend.businesslogic.assembler.DTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.DimensionDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.dto.DimensionDTO;

import java.util.ArrayList;
import java.util.List;

public final class DimensionDTOAssembler implements DTOAssembler<DimensionDTO, DimensionDomain> {

    private static final DimensionDTOAssembler INSTANCE = new DimensionDTOAssembler();

    private DimensionDTOAssembler() {
        super();
    }

    public static DimensionDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public DimensionDTO toDTO(final DimensionDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? DimensionDTO.obtenerValorDefecto()
                : new DimensionDTO(
                domain.getId(),
                domain.getLargo(),
                domain.getAncho()
        );
    }

    @Override
    public DimensionDomain toDomain(final DimensionDTO dto) {
        var dtoAEnsamblar = DimensionDTO.obtenerValorDefecto(dto);
        return new DimensionDomain(
                dtoAEnsamblar.getId(),
                dtoAEnsamblar.getLargo(),
                dtoAEnsamblar.getAncho()
        );
    }

    @Override
    public List<DimensionDTO> toDTOs(final List<DimensionDomain> domainList) {
        final List<DimensionDTO> resultado = new ArrayList<>();
        for (DimensionDomain domain : domainList) {
            resultado.add(toDTO(domain));
        }
        return resultado;
    }

    @Override
    public List<DimensionDomain> toDomains(final List<DimensionDTO> dtoList) {
        final List<DimensionDomain> resultado = new ArrayList<>();
        for (DimensionDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
