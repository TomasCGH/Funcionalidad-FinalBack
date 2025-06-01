package co.edu.uco.backend.businesslogic.assembler.ubicacionprecisa.entity;

import co.edu.uco.backend.businesslogic.assembler.EntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.UbicacionPrecisaDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.entity.UbicacionPrecisaEntity;
import co.edu.uco.backend.businesslogic.assembler.municipio.entity.MunicipioEntityAssembler;

import java.util.ArrayList;
import java.util.List;

public final class UbicacionPrecisaEntityAssembler implements EntityAssembler<UbicacionPrecisaEntity, UbicacionPrecisaDomain> {

    private static final UbicacionPrecisaEntityAssembler INSTANCE = new UbicacionPrecisaEntityAssembler();

    private UbicacionPrecisaEntityAssembler() {
        super();
    }

    public static UbicacionPrecisaEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public UbicacionPrecisaEntity toEntity(final UbicacionPrecisaDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? UbicacionPrecisaEntity.obtenerUbicacionPrecisaDefecto()
                : new UbicacionPrecisaEntity(
                domain.getId(),
                domain.getDireccion(),
                domain.getLatitud(),
                domain.getLongitud(),
                MunicipioEntityAssembler.getInstance().toEntity(domain.getMunicipio()),
                domain.getInformacionAdicional()
        );
    }

    @Override
    public UbicacionPrecisaDomain toDomain(final UbicacionPrecisaEntity entity) {
        var ubicacionEntityAEnsamblar = UbicacionPrecisaEntity.obtenerValorDefecto(entity);
        return new UbicacionPrecisaDomain(
                ubicacionEntityAEnsamblar.getId(),
                ubicacionEntityAEnsamblar.getDireccion(),
                ubicacionEntityAEnsamblar.getLatitud(),
                ubicacionEntityAEnsamblar.getLongitud(),
                MunicipioEntityAssembler.getInstance().toDomain(ubicacionEntityAEnsamblar.getMunicipio()),
                ubicacionEntityAEnsamblar.getInformacionAdicional()
        );
    }

    @Override
    public List<UbicacionPrecisaDomain> toDomain(final List<UbicacionPrecisaEntity> entityList) {
        var listaResultados = new ArrayList<UbicacionPrecisaDomain>();
        for (UbicacionPrecisaEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<UbicacionPrecisaEntity> toEntity(final List<UbicacionPrecisaDomain> domainList) {
        var listaResultados = new ArrayList<UbicacionPrecisaEntity>();
        for (UbicacionPrecisaDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }
        return listaResultados;
    }
}

