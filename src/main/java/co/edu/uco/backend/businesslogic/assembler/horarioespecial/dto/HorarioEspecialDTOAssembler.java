package co.edu.uco.backend.businesslogic.assembler.horarioespecial.dto;

import co.edu.uco.backend.businesslogic.assembler.cancha.dto.CanchaDTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.DTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.HorarioEspecialDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.dto.HorarioEspecialDTO;

import java.util.ArrayList;
import java.util.List;

public final class HorarioEspecialDTOAssembler implements DTOAssembler<HorarioEspecialDTO, HorarioEspecialDomain> {

    private static final HorarioEspecialDTOAssembler INSTANCE = new HorarioEspecialDTOAssembler();

    private HorarioEspecialDTOAssembler() {
        super();
    }

    public static HorarioEspecialDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public HorarioEspecialDTO toDTO(final HorarioEspecialDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? HorarioEspecialDTO.obtenerValorDefecto()
                : new HorarioEspecialDTO(
                domain.getId(),
                CanchaDTOAssembler.getInstance().toDTO(domain.getCancha()),
                domain.getFechaInicio(),
                domain.getFechaFin(),
                domain.getHoraInicio(),
                domain.getHoraFin(),
                domain.getMotivo()
        );
    }

    @Override
    public HorarioEspecialDomain toDomain(final HorarioEspecialDTO dto) {
        var dtoAEnsamblar = HorarioEspecialDTO.obtenerValorDefecto(dto);
        return new HorarioEspecialDomain(
                dtoAEnsamblar.getId(),
                CanchaDTOAssembler.getInstance().toDomain(dtoAEnsamblar.getCancha()),
                dtoAEnsamblar.getFechaInicio(),
                dtoAEnsamblar.getFechaFin(),
                dtoAEnsamblar.getHoraInicio(),
                dtoAEnsamblar.getHoraFin(),
                dtoAEnsamblar.getMotivo()
        );
    }

    @Override
    public List<HorarioEspecialDTO> toDTOs(final List<HorarioEspecialDomain> domainList) {
        final List<HorarioEspecialDTO> resultado = new ArrayList<>();
        for (HorarioEspecialDomain domain : domainList) {
            resultado.add(toDTO(domain));
        }
        return resultado;
    }

    @Override
    public List<HorarioEspecialDomain> toDomains(final List<HorarioEspecialDTO> dtoList) {
        final List<HorarioEspecialDomain> resultado = new ArrayList<>();
        for (HorarioEspecialDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
