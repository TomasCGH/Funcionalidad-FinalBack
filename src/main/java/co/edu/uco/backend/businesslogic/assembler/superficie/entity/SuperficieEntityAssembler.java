package co.edu.uco.backend.businesslogic.assembler.superficie.entity;

import co.edu.uco.backend.businesslogic.assembler.EntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.SuperficieDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.entity.SuperficieEntity;

import java.util.ArrayList;
import java.util.List;

public final class SuperficieEntityAssembler implements EntityAssembler<SuperficieEntity, SuperficieDomain> {

    private static final SuperficieEntityAssembler INSTANCE = new SuperficieEntityAssembler();

    private SuperficieEntityAssembler() {
        super();
    }

    public static SuperficieEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public SuperficieEntity toEntity(final SuperficieDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? SuperficieEntity.obtenerSuperficieDefecto()
                : new SuperficieEntity(domain.getId(), domain.getNombre());
    }

    @Override
    public SuperficieDomain toDomain(final SuperficieEntity entity) {
        var superficieEntityAEnsamblar = SuperficieEntity.obtenerValorDefecto(entity);
        return new SuperficieDomain(
                superficieEntityAEnsamblar.getId(),
                superficieEntityAEnsamblar.getNombre()
        );
    }

    @Override
    public List<SuperficieDomain> toDomain(final List<SuperficieEntity> entityList) {
        var listaResultados = new ArrayList<SuperficieDomain>();
        for (SuperficieEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<SuperficieEntity> toEntity(final List<SuperficieDomain> domainList) {
        var listaResultados = new ArrayList<SuperficieEntity>();
        for (SuperficieDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }
        return listaResultados;
    }
}

