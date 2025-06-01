package co.edu.uco.backend.businesslogic.assembler.dimension.entity;

import co.edu.uco.backend.businesslogic.assembler.EntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.DimensionDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.entity.DimensionEntity;

import java.util.ArrayList;
import java.util.List;

public final class DimensionEntityAssembler implements EntityAssembler<DimensionEntity, DimensionDomain> {

    private static final DimensionEntityAssembler INSTANCE = new DimensionEntityAssembler();

    private DimensionEntityAssembler() {
        super();
    }

    public static DimensionEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public DimensionEntity toEntity(final DimensionDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? DimensionEntity.obtenerDimensionDefecto()
                : new DimensionEntity(domain.getId(), domain.getLargo(), domain.getAncho());
    }

    @Override
    public DimensionDomain toDomain(final DimensionEntity entity) {
        var dimensionEntityAEnsamblar = DimensionEntity.obtenerValorDefecto(entity);
        return new DimensionDomain(
                dimensionEntityAEnsamblar.getId(),
                dimensionEntityAEnsamblar.getLargo(),
                dimensionEntityAEnsamblar.getAncho()
        );
    }

    @Override
    public List<DimensionDomain> toDomain(final List<DimensionEntity> entityList) {
        var listaResultados = new ArrayList<DimensionDomain>();
        for (DimensionEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<DimensionEntity> toEntity(final List<DimensionDomain> domainList) {
        var listaResultados = new ArrayList<DimensionEntity>();
        for (DimensionDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }
        return listaResultados;
    }
}
