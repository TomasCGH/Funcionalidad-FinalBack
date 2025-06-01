package co.edu.uco.backend.businesslogic.assembler.estadoreserva.entity;

import co.edu.uco.backend.businesslogic.assembler.EntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.EstadoReservaDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.entity.EstadoReservaEntity;

import java.util.ArrayList;
import java.util.List;

public final class EstadoReservaEntityAssembler implements EntityAssembler<EstadoReservaEntity, EstadoReservaDomain> {

    private static final EstadoReservaEntityAssembler INSTANCE = new EstadoReservaEntityAssembler();

    private EstadoReservaEntityAssembler() {
        super();
    }

    public static EstadoReservaEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public EstadoReservaEntity toEntity(final EstadoReservaDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? EstadoReservaEntity.obtenerEstadoReservaDefecto()
                : new EstadoReservaEntity(domain.getId(), domain.getNombre());
    }

    @Override
    public EstadoReservaDomain toDomain(final EstadoReservaEntity entity) {
        var entityAEnsamblar = EstadoReservaEntity.obtenerValorDefecto(entity);
        return new EstadoReservaDomain(
                entityAEnsamblar.getId(),
                entityAEnsamblar.getNombre()
        );
    }

    @Override
    public List<EstadoReservaDomain> toDomain(final List<EstadoReservaEntity> entityList) {
        var listaResultados = new ArrayList<EstadoReservaDomain>();
        for (EstadoReservaEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<EstadoReservaEntity> toEntity(final List<EstadoReservaDomain> domainList) {
        var listaResultados = new ArrayList<EstadoReservaEntity>();
        for (EstadoReservaDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }
        return listaResultados;
    }
}
