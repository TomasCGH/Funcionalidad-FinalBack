package co.edu.uco.backend.businesslogic.assembler.factura.entity;

import co.edu.uco.backend.businesslogic.assembler.EntityAssembler;
import co.edu.uco.backend.businesslogic.assembler.reserva.entity.ReservaEntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.FacturaDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.entity.FacturaEntity;

import java.util.ArrayList;
import java.util.List;

public final class FacturaEntityAssembler implements EntityAssembler<FacturaEntity, FacturaDomain> {

    private static final FacturaEntityAssembler INSTANCE = new FacturaEntityAssembler();

    private FacturaEntityAssembler() {
        super();
    }

    public static FacturaEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public FacturaEntity toEntity(final FacturaDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? FacturaEntity.obtenerFacturaDefecto()
                : new FacturaEntity(
                domain.getId(),
                domain.getIdentificador(),
                ReservaEntityAssembler.getInstance().toEntity(domain.getReserva()),
                domain.getFechaGeneracion(),
                domain.getTotal()
        );
    }

    @Override
    public FacturaDomain toDomain(final FacturaEntity entity) {
        var entityAEnsamblar = FacturaEntity.obtenerValorDefecto(entity);
        return new FacturaDomain(
                entityAEnsamblar.getId(),
                entityAEnsamblar.getIdentificador(),
                ReservaEntityAssembler.getInstance().toDomain(entityAEnsamblar.getReserva()),
                entityAEnsamblar.getFechaGeneracion(),
                entityAEnsamblar.getTotal()
        );
    }

    @Override
    public List<FacturaDomain> toDomain(final List<FacturaEntity> entityList) {
        var listaResultados = new ArrayList<FacturaDomain>();
        for (FacturaEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<FacturaEntity> toEntity(final List<FacturaDomain> domainList) {
        var listaResultados = new ArrayList<FacturaEntity>();
        for (FacturaDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }
        return listaResultados;
    }
}
