package co.edu.uco.backend.businesslogic.assembler.estadoreserva.dto;

import co.edu.uco.backend.businesslogic.assembler.DTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.EstadoReservaDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.dto.EstadoReservaDTO;

import java.util.ArrayList;
import java.util.List;

public final class EstadoReservaDTOAssembler implements DTOAssembler<EstadoReservaDTO, EstadoReservaDomain> {

    private static final EstadoReservaDTOAssembler INSTANCE = new EstadoReservaDTOAssembler();

    private EstadoReservaDTOAssembler() {
        super();
    }

    public static EstadoReservaDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public EstadoReservaDTO toDTO(final EstadoReservaDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? EstadoReservaDTO.obtenerValorDefecto()
                : new EstadoReservaDTO(
                domain.getId(),
                domain.getNombre()
        );
    }

    @Override
    public EstadoReservaDomain toDomain(final EstadoReservaDTO dto) {
        var dtoAEnsamblar = EstadoReservaDTO.obtenerValorDefecto(dto);
        return new EstadoReservaDomain(
                dtoAEnsamblar.getId(),
                dtoAEnsamblar.getNombre()
        );
    }

    @Override
    public List<EstadoReservaDTO> toDTOs(final List<EstadoReservaDomain> domainList) {
        final List<EstadoReservaDTO> resultado = new ArrayList<>();
        for (EstadoReservaDomain domain : domainList) {
            resultado.add(toDTO(domain));
        }
        return resultado;
    }

    @Override
    public List<EstadoReservaDomain> toDomains(final List<EstadoReservaDTO> dtoList) {
        final List<EstadoReservaDomain> resultado = new ArrayList<>();
        for (EstadoReservaDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
