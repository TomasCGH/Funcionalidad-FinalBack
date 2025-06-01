package co.edu.uco.backend.businesslogic.assembler.resena.entity;

import co.edu.uco.backend.businesslogic.assembler.EntityAssembler;
import co.edu.uco.backend.businesslogic.assembler.reserva.entity.ReservaEntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.ResenaDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.entity.ResenaEntity;

import java.util.ArrayList;
import java.util.List;

public final class ResenaEntityAssembler implements EntityAssembler<ResenaEntity, ResenaDomain> {

    private static final ResenaEntityAssembler INSTANCE = new ResenaEntityAssembler();

    private ResenaEntityAssembler() {
        super();
    }

    public static ResenaEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public ResenaEntity toEntity(final ResenaDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? ResenaEntity.obtenerResenaDefecto()
                : new ResenaEntity(
                domain.getId(),
                ReservaEntityAssembler.getInstance().toEntity(domain.getReserva()),
                domain.getCalificacion(),
                domain.getComentario(),
                domain.getFecha()
        );
    }

    @Override
    public ResenaDomain toDomain(final ResenaEntity entity) {
        var entityAEnsamblar = ResenaEntity.obtenerValorDefecto(entity);
        return new ResenaDomain(
                entityAEnsamblar.getId(),
                ReservaEntityAssembler.getInstance().toDomain(entityAEnsamblar.getReserva()),
                entityAEnsamblar.getCalificacion(),
                entityAEnsamblar.getComentario(),
                entityAEnsamblar.getFecha()
        );
    }

    @Override
    public List<ResenaDomain> toDomain(final List<ResenaEntity> entityList) {
        var listaResultados = new ArrayList<ResenaDomain>();
        for (ResenaEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<ResenaEntity> toEntity(final List<ResenaDomain> domainList) {
        var listaResultados = new ArrayList<ResenaEntity>();
        for (ResenaDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }
        return listaResultados;
    }
}
