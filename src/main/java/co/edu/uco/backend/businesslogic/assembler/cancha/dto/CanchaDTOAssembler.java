package co.edu.uco.backend.businesslogic.assembler.cancha.dto;

import co.edu.uco.backend.businesslogic.assembler.DTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.dimension.dto.DimensionDTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.horariodisponible.dto.HorarioDisponibleDTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.horarioespecial.dto.HorarioEspecialDTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.organizaciondeportiva.dto.OrganizacionDeportivaDTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.superficie.dto.SuperficieDTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.tipocancha.dto.TipoCanchaDTOAssembler;
import co.edu.uco.backend.businesslogic.assembler.ubicacionprecisa.dto.UbicacionPrecisaDTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.CanchaDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.dto.CanchaDTO;

import java.util.ArrayList;
import java.util.List;

public final class CanchaDTOAssembler implements DTOAssembler<CanchaDTO, CanchaDomain> {

    private static final CanchaDTOAssembler INSTANCE = new CanchaDTOAssembler();

    private CanchaDTOAssembler() {
        super();
    }

    public static CanchaDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public CanchaDTO toDTO(final CanchaDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? CanchaDTO.obtenerValorDefecto()
                : new CanchaDTO(
                domain.getId(),
                domain.getNombreCancha(),
                TipoCanchaDTOAssembler.getInstance().toDTO(domain.getTipo()),
                DimensionDTOAssembler.getInstance().toDTO(domain.getDimensiones()),
                SuperficieDTOAssembler.getInstance().toDTO(domain.getSuperficie()),
                domain.getCostoHora(),
                UbicacionPrecisaDTOAssembler.getInstance().toDTO(domain.getUbicacion()),
                OrganizacionDeportivaDTOAssembler.getInstance().toDTO(domain.getOrganizacion()),
                domain.isIluminacion(),
                domain.isCubierta(),
                HorarioDisponibleDTOAssembler.getInstance().toDTOs(domain.getHorariosDisponibles()),
                HorarioEspecialDTOAssembler.getInstance().toDTOs(domain.getHorariosEspeciales())
        );
    }

    @Override
    public CanchaDomain toDomain(final CanchaDTO dto) {
        var dtoAEnsamblar = CanchaDTO.obtenerValorDefecto(dto);
        return new CanchaDomain(
                dtoAEnsamblar.getId(),
                dtoAEnsamblar.getNombreCancha(),
                TipoCanchaDTOAssembler.getInstance().toDomain(dtoAEnsamblar.getTipo()),
                DimensionDTOAssembler.getInstance().toDomain(dtoAEnsamblar.getDimensiones()),
                SuperficieDTOAssembler.getInstance().toDomain(dtoAEnsamblar.getSuperficie()),
                dtoAEnsamblar.getCostoHora(),
                UbicacionPrecisaDTOAssembler.getInstance().toDomain(dtoAEnsamblar.getUbicacion()),
                OrganizacionDeportivaDTOAssembler.getInstance().toDomain(dtoAEnsamblar.getOrganizacion()),
                dtoAEnsamblar.isIluminacion(),
                dtoAEnsamblar.isCubierta(),
                HorarioDisponibleDTOAssembler.getInstance().toDomains(dtoAEnsamblar.getHorariosDisponibles()),
                HorarioEspecialDTOAssembler.getInstance().toDomains(dtoAEnsamblar.getHorariosEspeciales())
        );
    }

    @Override
    public List<CanchaDTO> toDTOs(final List<CanchaDomain> domainList) {
        final List<CanchaDTO> resultado = new ArrayList<>();
        for (CanchaDomain domain : domainList) {
            resultado.add(toDTO(domain));
        }
        return resultado;
    }

    @Override
    public List<CanchaDomain> toDomains(final List<CanchaDTO> dtoList) {
        final List<CanchaDomain> resultado = new ArrayList<>();
        for (CanchaDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
