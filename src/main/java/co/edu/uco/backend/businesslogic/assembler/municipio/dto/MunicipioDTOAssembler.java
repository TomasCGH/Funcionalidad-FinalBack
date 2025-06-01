package co.edu.uco.backend.businesslogic.assembler.municipio.dto;

import co.edu.uco.backend.businesslogic.assembler.DTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.departamento.dto.DepartamentoDTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.MunicipioDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.dto.MunicipioDTO;

import java.util.ArrayList;
import java.util.List;

public final class MunicipioDTOAssembler implements DTOAssembler<MunicipioDTO, MunicipioDomain> {

    private static final MunicipioDTOAssembler INSTANCE = new MunicipioDTOAssembler();

    private MunicipioDTOAssembler() {
        super();
    }

    public static MunicipioDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public MunicipioDTO toDTO(final MunicipioDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? MunicipioDTO.obtenerValorDefecto()
                : new MunicipioDTO(
                domain.getId(),
                domain.getNombre(),
                DepartamentoDTOAssembler.getInstance().toDTO(domain.getDepartamento())
        );
    }

    @Override
    public MunicipioDomain toDomain(final MunicipioDTO dto) {
        var dtoAEnsamblar = MunicipioDTO.obtenerValorDefecto(dto);
        return new MunicipioDomain(
                dtoAEnsamblar.getId(),
                dtoAEnsamblar.getNombre(),
                DepartamentoDTOAssembler.getInstance().toDomain(dtoAEnsamblar.getDepartamento())
        );
    }

    @Override
    public List<MunicipioDTO> toDTOs(final List<MunicipioDomain> domainList) {
        final List<MunicipioDTO> resultado = new ArrayList<>();
        for (MunicipioDomain domain : domainList) {
            resultado.add(toDTO(domain));
        }
        return resultado;
    }

    @Override
    public List<MunicipioDomain> toDomains(final List<MunicipioDTO> dtoList) {
        final List<MunicipioDomain> resultado = new ArrayList<>();
        for (MunicipioDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
