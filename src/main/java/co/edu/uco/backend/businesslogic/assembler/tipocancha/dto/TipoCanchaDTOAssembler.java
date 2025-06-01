package co.edu.uco.backend.businesslogic.assembler.tipocancha.dto;

import co.edu.uco.backend.businesslogic.assembler.DTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.TipoCanchaDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.dto.TipoCanchaDTO;

import java.util.ArrayList;
import java.util.List;

public final class TipoCanchaDTOAssembler implements DTOAssembler<TipoCanchaDTO, TipoCanchaDomain> {

    private static final TipoCanchaDTOAssembler INSTANCE = new TipoCanchaDTOAssembler();

    private TipoCanchaDTOAssembler() {
        super();
    }

    public static TipoCanchaDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public TipoCanchaDTO toDTO(final TipoCanchaDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? TipoCanchaDTO.obtenerValorDefecto()
                : new TipoCanchaDTO(
                domain.getId(),
                domain.getNombre(),
                domain.getJugadoresRecomendados()
        );
    }

    @Override
    public TipoCanchaDomain toDomain(final TipoCanchaDTO dto) {
        var dtoAEnsamblar = TipoCanchaDTO.obtenerValorDefecto(dto);
        return new TipoCanchaDomain(
                dtoAEnsamblar.getId(),
                dtoAEnsamblar.getNombre(),
                dtoAEnsamblar.getJugadoresRecomendados()
        );
    }

    @Override
    public List<TipoCanchaDTO> toDTOs(final List<TipoCanchaDomain> domainList) {
        final List<TipoCanchaDTO> resultado = new ArrayList<>();
        for (TipoCanchaDomain domain : domainList) {
            resultado.add(toDTO(domain));
        }
        return resultado;
    }

    @Override
    public List<TipoCanchaDomain> toDomains(final List<TipoCanchaDTO> dtoList) {
        final List<TipoCanchaDomain> resultado = new ArrayList<>();
        for (TipoCanchaDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
