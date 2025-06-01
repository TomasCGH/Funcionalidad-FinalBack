package co.edu.uco.backend.businesslogic.assembler.municipio.entity;

import co.edu.uco.backend.businesslogic.assembler.*;
import co.edu.uco.backend.businesslogic.assembler.departamento.entity.DepartamentoEntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.MunicipioDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.entity.MunicipioEntity;

import java.util.ArrayList;
import java.util.List;

public final class MunicipioEntityAssembler implements EntityAssembler<MunicipioEntity, MunicipioDomain> {

    private static final MunicipioEntityAssembler INSTANCE = new MunicipioEntityAssembler();

    private MunicipioEntityAssembler() {
        super();
    }

    public static MunicipioEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public MunicipioEntity toEntity(final MunicipioDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? MunicipioEntity.obtenerMunicipioDefecto()
                : new MunicipioEntity(
                domain.getId(),
                domain.getNombre(),
                DepartamentoEntityAssembler.getInstance().toEntity(domain.getDepartamento())
        );
    }

    @Override
    public MunicipioDomain toDomain(final MunicipioEntity entity) {
        var entityAEnsamblar = MunicipioEntity.obtenerValorDefecto(entity);
        return new MunicipioDomain(
                entityAEnsamblar.getId(),
                entityAEnsamblar.getNombre(),
                DepartamentoEntityAssembler.getInstance().toDomain(entityAEnsamblar.getDepartamento())
        );
    }

    @Override
    public List<MunicipioDomain> toDomain(final List<MunicipioEntity> entityList) {
        var listaResultados = new ArrayList<MunicipioDomain>();
        for (MunicipioEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<MunicipioEntity> toEntity(final List<MunicipioDomain> domainList) {
        var listaResultados = new ArrayList<MunicipioEntity>();
        for (MunicipioDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }
        return listaResultados;
    }
}

