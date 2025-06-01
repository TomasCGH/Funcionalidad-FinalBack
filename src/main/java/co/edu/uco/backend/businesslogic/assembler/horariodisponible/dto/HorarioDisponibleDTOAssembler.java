package co.edu.uco.backend.businesslogic.assembler.horariodisponible.dto;

import co.edu.uco.backend.businesslogic.assembler.cancha.dto.CanchaDTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.DTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.HorarioDisponibleDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.dto.HorarioDisponibleDTO;

import java.util.ArrayList;
import java.util.List;

public final class HorarioDisponibleDTOAssembler implements DTOAssembler<HorarioDisponibleDTO, HorarioDisponibleDomain> {

    private static final HorarioDisponibleDTOAssembler INSTANCE = new HorarioDisponibleDTOAssembler();

    private HorarioDisponibleDTOAssembler() {
        super();
    }

    public static HorarioDisponibleDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public HorarioDisponibleDTO toDTO(final HorarioDisponibleDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? HorarioDisponibleDTO.obtenerValorDefecto()
                : new HorarioDisponibleDTO(
                domain.getId(),
                CanchaDTOAssembler.getInstance().toDTO(domain.getCancha()),
                domain.getDia(),
                domain.getHoraApertura(),
                domain.getHoraCierre()
        );
    }

    @Override
    public HorarioDisponibleDomain toDomain(final HorarioDisponibleDTO dto) {
        var dtoAEnsamblar = HorarioDisponibleDTO.obtenerValorDefecto(dto);
        return new HorarioDisponibleDomain(
                dtoAEnsamblar.getId(),
                CanchaDTOAssembler.getInstance().toDomain(dtoAEnsamblar.getCancha()),
                dtoAEnsamblar.getDia(),
                dtoAEnsamblar.getHoraApertura(),
                dtoAEnsamblar.getHoraCierre()
        );
    }

    @Override
    public List<HorarioDisponibleDTO> toDTOs(final List<HorarioDisponibleDomain> domainList) {
        final List<HorarioDisponibleDTO> resultado = new ArrayList<>();
        for (HorarioDisponibleDomain domain : domainList) {
            resultado.add(toDTO(domain));
        }
        return resultado;
    }

    @Override
    public List<HorarioDisponibleDomain> toDomains(final List<HorarioDisponibleDTO> dtoList) {
        final List<HorarioDisponibleDomain> resultado = new ArrayList<>();
        for (HorarioDisponibleDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
