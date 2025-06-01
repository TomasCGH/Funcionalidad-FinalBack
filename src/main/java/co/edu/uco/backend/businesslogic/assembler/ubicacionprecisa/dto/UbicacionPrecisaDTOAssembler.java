package co.edu.uco.backend.businesslogic.assembler.ubicacionprecisa.dto;

import co.edu.uco.backend.businesslogic.assembler.DTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.municipio.dto.MunicipioDTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.UbicacionPrecisaDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.dto.UbicacionPrecisaDTO;

import java.util.ArrayList;
import java.util.List;

public final class UbicacionPrecisaDTOAssembler implements DTOAssembler<UbicacionPrecisaDTO, UbicacionPrecisaDomain> {

    private static final UbicacionPrecisaDTOAssembler INSTANCE = new UbicacionPrecisaDTOAssembler();

    private UbicacionPrecisaDTOAssembler() {
        super();
    }

    public static UbicacionPrecisaDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public UbicacionPrecisaDTO toDTO(final UbicacionPrecisaDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? UbicacionPrecisaDTO.obtenerValorDefecto()
                : new UbicacionPrecisaDTO(
                domain.getId(),
                domain.getDireccion(),
                domain.getLatitud(),
                domain.getLongitud(),
                MunicipioDTOAssembler.getInstance().toDTO(domain.getMunicipio()),
                domain.getInformacionAdicional()
        );
    }

    @Override
    public UbicacionPrecisaDomain toDomain(final UbicacionPrecisaDTO dto) {
        var dtoAEnsamblar = UbicacionPrecisaDTO.obtenerValorDefecto(dto);
        return new UbicacionPrecisaDomain(
                dtoAEnsamblar.getId(),
                dtoAEnsamblar.getDireccion(),
                dtoAEnsamblar.getLatitud(),
                dtoAEnsamblar.getLongitud(),
                MunicipioDTOAssembler.getInstance().toDomain(dtoAEnsamblar.getMunicipio()),
                dtoAEnsamblar.getInformacionAdicional()
        );
    }

    @Override
    public List<UbicacionPrecisaDTO> toDTOs(final List<UbicacionPrecisaDomain> domainList) {
        final List<UbicacionPrecisaDTO> resultado = new ArrayList<>();
        for (UbicacionPrecisaDomain domain : domainList) {
            resultado.add(toDTO(domain));
        }
        return resultado;
    }

    @Override
    public List<UbicacionPrecisaDomain> toDomains(final List<UbicacionPrecisaDTO> dtoList) {
        final List<UbicacionPrecisaDomain> resultado = new ArrayList<>();
        for (UbicacionPrecisaDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
