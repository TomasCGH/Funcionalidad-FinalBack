package co.edu.uco.backend.businesslogic.assembler.estadoverificacion.entity;

import co.edu.uco.backend.businesslogic.assembler.EntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.EstadoVerificacionDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.entity.EstadoVerificacionEntity;

import java.util.ArrayList;
import java.util.List;

public final class EstadoVerificacionEntityAssembler implements EntityAssembler<EstadoVerificacionEntity, EstadoVerificacionDomain> {

    private static final EstadoVerificacionEntityAssembler INSTANCE = new EstadoVerificacionEntityAssembler();

    private EstadoVerificacionEntityAssembler() {
        super();
    }

    public static EstadoVerificacionEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public EstadoVerificacionEntity toEntity(final EstadoVerificacionDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? EstadoVerificacionEntity.obtenerEstadoVerificacionDefecto()
                : new EstadoVerificacionEntity(domain.getId(), domain.getNombre());
    }

    @Override
    public EstadoVerificacionDomain toDomain(final EstadoVerificacionEntity entity) {
        var entityAEnsamblar = EstadoVerificacionEntity.obtenerValorDefecto(entity);
        return new EstadoVerificacionDomain(
                entityAEnsamblar.getId(),
                entityAEnsamblar.getNombre()
        );
    }

    @Override
    public List<EstadoVerificacionDomain> toDomain(final List<EstadoVerificacionEntity> entityList) {
        var listaResultados = new ArrayList<EstadoVerificacionDomain>();
        for (EstadoVerificacionEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<EstadoVerificacionEntity> toEntity(final List<EstadoVerificacionDomain> domainList) {
        var listaResultados = new ArrayList<EstadoVerificacionEntity>();
        for (EstadoVerificacionDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }
        return listaResultados;
    }
}
