package co.edu.uco.backend.businesslogic.assembler.horariodisponible.entity;

import co.edu.uco.backend.businesslogic.assembler.*;
import co.edu.uco.backend.businesslogic.assembler.cancha.entity.CanchaEntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.HorarioDisponibleDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.entity.HorarioDisponibleEntity;

import java.util.ArrayList;
import java.util.List;

public final class HorarioDisponibleEntityAssembler implements EntityAssembler<HorarioDisponibleEntity, HorarioDisponibleDomain> {

    private static final HorarioDisponibleEntityAssembler INSTANCE = new HorarioDisponibleEntityAssembler();

    private HorarioDisponibleEntityAssembler() {
        super();
    }

    public static HorarioDisponibleEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public HorarioDisponibleEntity toEntity(final HorarioDisponibleDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? HorarioDisponibleEntity.obtenerHorarioDisponibleDefecto()
                : new HorarioDisponibleEntity(
                domain.getId(),
                CanchaEntityAssembler.getInstance().toEntity(domain.getCancha()),
                domain.getDia(),
                domain.getHoraApertura(),
                domain.getHoraCierre()
        );
    }

    @Override
    public HorarioDisponibleDomain toDomain(final HorarioDisponibleEntity entity) {
        var entityAEnsamblar = HorarioDisponibleEntity.obtenerValorDefecto(entity);
        return new HorarioDisponibleDomain(
                entityAEnsamblar.getId(),
                CanchaEntityAssembler.getInstance().toDomain(entityAEnsamblar.getCancha()),
                entityAEnsamblar.getDia(),
                entityAEnsamblar.getHoraApertura(),
                entityAEnsamblar.getHoraCierre()
        );
    }

    @Override
    public List<HorarioDisponibleDomain> toDomain(final List<HorarioDisponibleEntity> entityList) {
        var listaResultados = new ArrayList<HorarioDisponibleDomain>();
        for (HorarioDisponibleEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<HorarioDisponibleEntity> toEntity(final List<HorarioDisponibleDomain> domainList) {
        var listaResultados = new ArrayList<HorarioDisponibleEntity>();
        for (HorarioDisponibleDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }
        return listaResultados;
    }


}
