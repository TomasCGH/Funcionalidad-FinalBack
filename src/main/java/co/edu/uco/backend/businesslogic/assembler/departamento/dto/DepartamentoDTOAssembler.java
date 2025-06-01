package co.edu.uco.backend.businesslogic.assembler.departamento.dto;

import co.edu.uco.backend.businesslogic.assembler.DTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.DepartamentoDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.dto.DepartamentoDTO;

import java.util.ArrayList;
import java.util.List;

public final class DepartamentoDTOAssembler implements DTOAssembler<DepartamentoDTO, DepartamentoDomain> {

    private static final DepartamentoDTOAssembler INSTANCE = new DepartamentoDTOAssembler();

    private DepartamentoDTOAssembler() {
        super();
    }

    public static DepartamentoDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public DepartamentoDTO toDTO(final DepartamentoDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? DepartamentoDTO.obtenerValorDefecto()
                : new DepartamentoDTO(
                domain.getId(),
                domain.getNombre()
        );
    }

    @Override
    public DepartamentoDomain toDomain(final DepartamentoDTO dto) {
        var dtoAEnsamblar = DepartamentoDTO.obtenerValorDefecto(dto);
        return new DepartamentoDomain(
                dtoAEnsamblar.getId(),
                dtoAEnsamblar.getNombre()
        );
    }

    @Override
    public List<DepartamentoDTO> toDTOs(final List<DepartamentoDomain> domainList) {
        final List<DepartamentoDTO> resultado = new ArrayList<>();
        for (DepartamentoDomain domain : domainList) {
            resultado.add(toDTO(domain));
        }
        return resultado;
    }

    @Override
    public List<DepartamentoDomain> toDomains(final List<DepartamentoDTO> dtoList) {
        final List<DepartamentoDomain> resultado = new ArrayList<>();
        for (DepartamentoDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
