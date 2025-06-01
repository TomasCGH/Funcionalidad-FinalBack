package co.edu.uco.backend.businesslogic.assembler.tipocancha.entity;

import co.edu.uco.backend.businesslogic.assembler.EntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.TipoCanchaDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.entity.TipoCanchaEntity;

import java.util.ArrayList;
import java.util.List;

public final class TipoCanchaEntityAssembler implements EntityAssembler<TipoCanchaEntity, TipoCanchaDomain> {

    private static final TipoCanchaEntityAssembler INSTANCE = new TipoCanchaEntityAssembler();

    private TipoCanchaEntityAssembler() {
        super();
    }

    public static TipoCanchaEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public TipoCanchaEntity toEntity(final TipoCanchaDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? TipoCanchaEntity.obtenerTipoCanchaDefecto()
                : new TipoCanchaEntity(domain.getId(), domain.getNombre(), domain.getJugadoresRecomendados());
    }

    @Override
    public TipoCanchaDomain toDomain(final TipoCanchaEntity entity) {
        var tipoCanchaEntityAEnsamblar = TipoCanchaEntity.obtenerValorDefecto(entity);
        return new TipoCanchaDomain(
                tipoCanchaEntityAEnsamblar.getId(),
                tipoCanchaEntityAEnsamblar.getNombre(),
                tipoCanchaEntityAEnsamblar.getJugadoresRecomendados()
        );
    }

    @Override
    public List<TipoCanchaDomain> toDomain(final List<TipoCanchaEntity> entityList) {
        var listaResultados = new ArrayList<TipoCanchaDomain>();
        for (TipoCanchaEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<TipoCanchaEntity> toEntity(final List<TipoCanchaDomain> domainList) {
        var listaResultados = new ArrayList<TipoCanchaEntity>();
        for (TipoCanchaDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }
        return listaResultados;
    }
}

