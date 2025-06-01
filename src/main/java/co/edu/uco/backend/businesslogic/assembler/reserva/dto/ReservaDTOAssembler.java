package co.edu.uco.backend.businesslogic.assembler.reserva.dto;

import co.edu.uco.backend.businesslogic.assembler.cancha.dto.CanchaDTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.cliente.dto.ClienteDTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.DTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.estadoreserva.dto.EstadoReservaDTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.ReservaDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.dto.ReservaDTO;

import java.util.ArrayList;
import java.util.List;

public final class ReservaDTOAssembler implements DTOAssembler<ReservaDTO, ReservaDomain> {

    private static final ReservaDTOAssembler INSTANCE = new ReservaDTOAssembler();

    private ReservaDTOAssembler() {
        super();
    }

    public static ReservaDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public ReservaDTO toDTO(final ReservaDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? ReservaDTO.obtenerValorDefecto()
                : new ReservaDTO(
                domain.getId(),
                ClienteDTOAssembler.getInstance().toDTO(domain.getCliente()),
                CanchaDTOAssembler.getInstance().toDTO(domain.getCancha()),
                domain.getFechaReserva(),
                domain.getFechaUsoCancha(),
                domain.getHoraInicio(),
                domain.getHoraFin(),
                EstadoReservaDTOAssembler.getInstance().toDTO(domain.getEstado())
        );
    }

    @Override
    public ReservaDomain toDomain(final ReservaDTO dto) {
        var dtoAEnsamblar = ReservaDTO.obtenerValorDefecto(dto);
        return new ReservaDomain(
                dtoAEnsamblar.getId(),
                ClienteDTOAssembler.getInstance().toDomain(dtoAEnsamblar.getCliente()),
                CanchaDTOAssembler.getInstance().toDomain(dtoAEnsamblar.getCancha()),
                dtoAEnsamblar.getFechaReserva(),
                dtoAEnsamblar.getFechaUsoCancha(),
                dtoAEnsamblar.getHoraInicio(),
                dtoAEnsamblar.getHoraFin(),
                EstadoReservaDTOAssembler.getInstance().toDomain(dtoAEnsamblar.getEstado())
        );
    }

    @Override
    public List<ReservaDTO> toDTOs(final List<ReservaDomain> domainList) {
        final List<ReservaDTO> resultado = new ArrayList<>();
        for (ReservaDomain domain : domainList) {
            resultado.add(toDTO(domain));
        }
        return resultado;
    }

    @Override
    public List<ReservaDomain> toDomains(final List<ReservaDTO> dtoList) {
        final List<ReservaDomain> resultado = new ArrayList<>();
        for (ReservaDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
