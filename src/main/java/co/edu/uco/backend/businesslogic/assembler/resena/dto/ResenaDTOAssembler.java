package co.edu.uco.backend.businesslogic.assembler.resena.dto;

import co.edu.uco.backend.businesslogic.assembler.DTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.reserva.dto.ReservaDTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.ResenaDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.dto.ResenaDTO;

import java.util.ArrayList;
import java.util.List;

public final class ResenaDTOAssembler implements DTOAssembler<ResenaDTO, ResenaDomain> {

    private static final ResenaDTOAssembler INSTANCE = new ResenaDTOAssembler();

    private ResenaDTOAssembler() {
        super();
    }

    public static ResenaDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public ResenaDTO toDTO(final ResenaDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? ResenaDTO.obtenerValorDefecto()
                : new ResenaDTO(
                domain.getId(),
                ReservaDTOAssembler.getInstance().toDTO(domain.getReserva()),
                domain.getCalificacion(),
                domain.getComentario(),
                domain.getFecha()
        );
    }

    @Override
    public ResenaDomain toDomain(final ResenaDTO dto) {
        var dtoAEnsamblar = ResenaDTO.obtenerValorDefecto(dto);
        return new ResenaDomain(
                dtoAEnsamblar.getId(),
                ReservaDTOAssembler.getInstance().toDomain(dtoAEnsamblar.getReserva()),
                dtoAEnsamblar.getCalificacion(),
                dtoAEnsamblar.getComentario(),
                dtoAEnsamblar.getFecha()
        );
    }

    @Override
    public List<ResenaDTO> toDTOs(final List<ResenaDomain> domainList) {
        final List<ResenaDTO> resultado = new ArrayList<>();
        for (ResenaDomain domain : domainList) {
            resultado.add(toDTO(domain));
        }
        return resultado;
    }

    @Override
    public List<ResenaDomain> toDomains(final List<ResenaDTO> dtoList) {
        final List<ResenaDomain> resultado = new ArrayList<>();
        for (ResenaDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
