package co.edu.uco.backend.businesslogic.assembler.superficie.dto;

import co.edu.uco.backend.businesslogic.assembler.DTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.SuperficieDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.dto.SuperficieDTO;

import java.util.ArrayList;
import java.util.List;

public final class SuperficieDTOAssembler implements DTOAssembler<SuperficieDTO, SuperficieDomain> {

    private static final SuperficieDTOAssembler INSTANCE = new SuperficieDTOAssembler();

    private SuperficieDTOAssembler() {
        super();
    }

    public static SuperficieDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public SuperficieDTO toDTO(final SuperficieDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? SuperficieDTO.obtenerValorDefecto()
                : new SuperficieDTO(
                domain.getId(),
                domain.getNombre()
        );
    }

    @Override
    public SuperficieDomain toDomain(final SuperficieDTO dto) {
        var dtoAEnsamblar = SuperficieDTO.obtenerValorDefecto(dto);
        return new SuperficieDomain(
                dtoAEnsamblar.getId(),
                dtoAEnsamblar.getNombre()
        );
    }

    @Override
    public List<SuperficieDTO> toDTOs(final List<SuperficieDomain> domainList) {
        final List<SuperficieDTO> resultado = new ArrayList<>();
        for (SuperficieDomain domain : domainList) {
            resultado.add(toDTO(domain));
        }
        return resultado;
    }

    @Override
    public List<SuperficieDomain> toDomains(final List<SuperficieDTO> dtoList) {
        final List<SuperficieDomain> resultado = new ArrayList<>();
        for (SuperficieDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
