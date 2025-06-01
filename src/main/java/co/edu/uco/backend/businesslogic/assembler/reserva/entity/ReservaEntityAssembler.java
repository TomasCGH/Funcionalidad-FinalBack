package co.edu.uco.backend.businesslogic.assembler.reserva.entity;

import co.edu.uco.backend.businesslogic.assembler.*;
import co.edu.uco.backend.businesslogic.assembler.cancha.entity.CanchaEntityAssembler;
import co.edu.uco.backend.businesslogic.assembler.cliente.entity.ClienteEntityAssembler;
import co.edu.uco.backend.businesslogic.assembler.estadoreserva.entity.EstadoReservaEntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.ReservaDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.entity.ReservaEntity;

import java.util.ArrayList;
import java.util.List;

public final class ReservaEntityAssembler implements EntityAssembler<ReservaEntity, ReservaDomain> {

    private static final ReservaEntityAssembler INSTANCE = new ReservaEntityAssembler();

    private ReservaEntityAssembler() {
        super();
    }

    public static ReservaEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public ReservaEntity toEntity(final ReservaDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? ReservaEntity.obtenerReservaDefecto()
                : new ReservaEntity(
                domain.getId(),
                ClienteEntityAssembler.getInstance().toEntity(domain.getCliente()),
                CanchaEntityAssembler.getInstance().toEntity(domain.getCancha()),
                domain.getFechaReserva(),
                domain.getFechaUsoCancha(),
                domain.getHoraInicio(),
                domain.getHoraFin(),
                EstadoReservaEntityAssembler.getInstance().toEntity(domain.getEstado())
        );
    }

    @Override
    public ReservaDomain toDomain(final ReservaEntity entity) {
        var entityAEnsamblar = ReservaEntity.obtenerValorDefecto(entity);
        return new ReservaDomain(
                entityAEnsamblar.getId(),
                ClienteEntityAssembler.getInstance().toDomain(entityAEnsamblar.getCliente()),
                CanchaEntityAssembler.getInstance().toDomain(entityAEnsamblar.getCancha()),
                entityAEnsamblar.getFechaReserva(),
                entityAEnsamblar.getFechaUsoCancha(),
                entityAEnsamblar.getHoraInicio(),
                entityAEnsamblar.getHoraFin(),
                EstadoReservaEntityAssembler.getInstance().toDomain(entityAEnsamblar.getEstado())
        );
    }

    @Override
    public List<ReservaDomain> toDomain(final List<ReservaEntity> entityList) {
        var listaResultados = new ArrayList<ReservaDomain>();
        for (ReservaEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<ReservaEntity> toEntity(final List<ReservaDomain> domainList) {
        var listaResultados = new ArrayList<ReservaEntity>();
        for (ReservaDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }
        return listaResultados;
    }
}
