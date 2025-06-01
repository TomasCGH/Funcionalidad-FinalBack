package co.edu.uco.backend.businesslogic.assembler.cancha.entity;

import co.edu.uco.backend.businesslogic.assembler.dimension.entity.DimensionEntityAssembler;
import co.edu.uco.backend.businesslogic.assembler.EntityAssembler;
import co.edu.uco.backend.businesslogic.assembler.horariodisponible.entity.HorarioDisponibleEntityAssembler;
import co.edu.uco.backend.businesslogic.assembler.horarioespecial.entity.HorarioEspecialEntityAssembler;
import co.edu.uco.backend.businesslogic.assembler.organizaciondeportiva.entity.OrganizacionDeportivaEntityAssembler;
import co.edu.uco.backend.businesslogic.assembler.superficie.entity.SuperficieEntityAssembler;
import co.edu.uco.backend.businesslogic.assembler.tipocancha.entity.TipoCanchaEntityAssembler;
import co.edu.uco.backend.businesslogic.assembler.ubicacionprecisa.entity.UbicacionPrecisaEntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.CanchaDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.entity.CanchaEntity;

import java.util.ArrayList;
import java.util.List;

public final class CanchaEntityAssembler implements EntityAssembler<CanchaEntity, CanchaDomain> {

    private static final CanchaEntityAssembler INSTANCE = new CanchaEntityAssembler();

    private CanchaEntityAssembler() {
        super();
    }

    public static CanchaEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public CanchaEntity toEntity(final CanchaDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? CanchaEntity.obtenerCanchaDefecto()
                : new CanchaEntity(
                domain.getId(),
                domain.getNombreCancha(),
                TipoCanchaEntityAssembler.getInstance().toEntity(domain.getTipo()),
                DimensionEntityAssembler.getInstance().toEntity(domain.getDimensiones()),
                SuperficieEntityAssembler.getInstance().toEntity(domain.getSuperficie()),
                domain.getCostoHora(),
                UbicacionPrecisaEntityAssembler.getInstance().toEntity(domain.getUbicacion()),
                OrganizacionDeportivaEntityAssembler.getInstance().toEntity(domain.getOrganizacion()),
                domain.isIluminacion(),
                domain.isCubierta(),
                HorarioDisponibleEntityAssembler.getInstance().toEntity(domain.getHorariosDisponibles()),
                HorarioEspecialEntityAssembler.getInstance().toEntity(domain.getHorariosEspeciales())
        );
    }

    @Override
    public CanchaDomain toDomain(final CanchaEntity entity) {
        var canchaEntityAEnsamblar = CanchaEntity.obtenerValorDefecto(entity);
        return new CanchaDomain(
                canchaEntityAEnsamblar.getId(),
                canchaEntityAEnsamblar.getNombreCancha(),
                TipoCanchaEntityAssembler.getInstance().toDomain(canchaEntityAEnsamblar.getTipo()),
                DimensionEntityAssembler.getInstance().toDomain(canchaEntityAEnsamblar.getDimensiones()),
                SuperficieEntityAssembler.getInstance().toDomain(canchaEntityAEnsamblar.getSuperficie()),
                canchaEntityAEnsamblar.getCostoHora(),
                UbicacionPrecisaEntityAssembler.getInstance().toDomain(canchaEntityAEnsamblar.getUbicacion()),
                OrganizacionDeportivaEntityAssembler.getInstance().toDomain(canchaEntityAEnsamblar.getOrganizacion()),
                canchaEntityAEnsamblar.isIluminacion(),
                canchaEntityAEnsamblar.isCubierta(),
                HorarioDisponibleEntityAssembler.getInstance().toDomain(canchaEntityAEnsamblar.getHorariosDisponibles()),
                HorarioEspecialEntityAssembler.getInstance().toDomain(canchaEntityAEnsamblar.getHorariosEspeciales())
        );
    }

    @Override
    public List<CanchaDomain> toDomain(List<CanchaEntity> entityList) {
        var listaResultados = new ArrayList<CanchaDomain>();
        for (CanchaEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return List.of();
    }

    @Override
    public List<CanchaEntity> toEntity(List<CanchaDomain> domainList) {
        var listaResultados = new ArrayList<CanchaEntity>();
        for (CanchaDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }
        return List.of();
    }


}
