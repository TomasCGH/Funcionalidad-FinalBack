package co.edu.uco.backend.businesslogic.assembler.horarioespecial.entity;

import co.edu.uco.backend.businesslogic.assembler.cancha.entity.CanchaEntityAssembler;
import co.edu.uco.backend.businesslogic.assembler.EntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.HorarioEspecialDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.entity.HorarioEspecialEntity;

import java.util.ArrayList;
import java.util.List;

public final class HorarioEspecialEntityAssembler implements EntityAssembler<HorarioEspecialEntity, HorarioEspecialDomain> {

    private static final HorarioEspecialEntityAssembler INSTANCE = new HorarioEspecialEntityAssembler();

    private HorarioEspecialEntityAssembler() {
        super();
    }

    public static HorarioEspecialEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public HorarioEspecialEntity toEntity(final HorarioEspecialDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? HorarioEspecialEntity.obtenerHorarioEspecialDefecto()
                : new HorarioEspecialEntity(
                domain.getId(),
                CanchaEntityAssembler.getInstance().toEntity(domain.getCancha()),
                domain.getFechaInicio(),
                domain.getFechaFin(),
                domain.getHoraInicio(),
                domain.getHoraFin(),
                domain.getMotivo()
        );
    }

    @Override
    public HorarioEspecialDomain toDomain(final HorarioEspecialEntity entity) {
        var entityAEnsamblar = HorarioEspecialEntity.obtenerValorDefecto(entity);
        return new HorarioEspecialDomain(
                entityAEnsamblar.getId(),
                CanchaEntityAssembler.getInstance().toDomain(entityAEnsamblar.getCancha()),
                entityAEnsamblar.getFechaInicio(),
                entityAEnsamblar.getFechaFin(),
                entityAEnsamblar.getHoraInicio(),
                entityAEnsamblar.getHoraFin(),
                entityAEnsamblar.getMotivo()
        );
    }

    @Override
    public List<HorarioEspecialDomain> toDomain(final List<HorarioEspecialEntity> entityList) {
        var listaResultados = new ArrayList<HorarioEspecialDomain>();
        for (HorarioEspecialEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<HorarioEspecialEntity> toEntity(final List<HorarioEspecialDomain> domainList) {
        var listaResultados = new ArrayList<HorarioEspecialEntity>();
        for (HorarioEspecialDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }
        return listaResultados;
    }
}
