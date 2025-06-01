package co.edu.uco.backend.businesslogic.assembler.factura.dto;

import co.edu.uco.backend.businesslogic.assembler.DTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.reserva.dto.ReservaDTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.FacturaDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.dto.FacturaDTO;

import java.util.ArrayList;
import java.util.List;

public final class FacturaDTOAssembler implements DTOAssembler<FacturaDTO, FacturaDomain> {

    private static final FacturaDTOAssembler INSTANCE = new FacturaDTOAssembler();

    private FacturaDTOAssembler() {
        super();
    }

    public static FacturaDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public FacturaDTO toDTO(final FacturaDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? FacturaDTO.obtenerValorDefecto()
                : new FacturaDTO(
                domain.getId(),
                domain.getIdentificador(),
                ReservaDTOAssembler.getInstance().toDTO(domain.getReserva()),
                domain.getFechaGeneracion(),
                domain.getTotal()
        );
    }

    @Override
    public FacturaDomain toDomain(final FacturaDTO dto) {
        var dtoAEnsamblar = FacturaDTO.obtenerValorDefecto(dto);
        return new FacturaDomain(
                dtoAEnsamblar.getId(),
                dtoAEnsamblar.getIdentificador(),
                ReservaDTOAssembler.getInstance().toDomain(dtoAEnsamblar.getReserva()),
                dtoAEnsamblar.getFechaGeneracion(),
                dtoAEnsamblar.getTotal()
        );
    }

    @Override
    public List<FacturaDTO> toDTOs(final List<FacturaDomain> domainList) {
        final List<FacturaDTO> resultado = new ArrayList<>();
        for (FacturaDomain domain : domainList) {
            resultado.add(toDTO(domain));
        }
        return resultado;
    }

    @Override
    public List<FacturaDomain> toDomains(final List<FacturaDTO> dtoList) {
        final List<FacturaDomain> resultado = new ArrayList<>();
        for (FacturaDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
