package co.edu.uco.backend.businesslogic.assembler.departamento.entity;

import co.edu.uco.backend.businesslogic.assembler.EntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.DepartamentoDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.entity.DepartamentoEntity;

import java.util.ArrayList;
import java.util.List;

public final class DepartamentoEntityAssembler implements EntityAssembler<DepartamentoEntity, DepartamentoDomain> {

    private static final DepartamentoEntityAssembler INSTANCE = new DepartamentoEntityAssembler();

    private DepartamentoEntityAssembler() {
        super();
    }

    public static DepartamentoEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public DepartamentoEntity toEntity(final DepartamentoDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? DepartamentoEntity.obtenerDepartamentoDefecto()
                : new DepartamentoEntity(domain.getId(), domain.getNombre());
    }

    @Override
    public DepartamentoDomain toDomain(final DepartamentoEntity entity) {
        var entityAEnsamblar = DepartamentoEntity.obtenerValorDefecto(entity);
        return new DepartamentoDomain(
                entityAEnsamblar.getId(),
                entityAEnsamblar.getNombre()
        );
    }

    @Override
    public List<DepartamentoDomain> toDomain(final List<DepartamentoEntity> entityList) {
        var listaResultados = new ArrayList<DepartamentoDomain>();
        for (DepartamentoEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<DepartamentoEntity> toEntity(final List<DepartamentoDomain> domainList) {
        var listaResultados = new ArrayList<DepartamentoEntity>();
        for (DepartamentoDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }
        return listaResultados;
    }
}

