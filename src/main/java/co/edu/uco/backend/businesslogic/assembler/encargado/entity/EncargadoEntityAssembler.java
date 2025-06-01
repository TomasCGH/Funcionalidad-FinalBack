package co.edu.uco.backend.businesslogic.assembler.encargado.entity;

import co.edu.uco.backend.businesslogic.assembler.*;
import co.edu.uco.backend.businesslogic.assembler.organizaciondeportiva.entity.OrganizacionDeportivaEntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.EncargadoDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.entity.EncargadoEntity;

import java.util.ArrayList;
import java.util.List;

public final class EncargadoEntityAssembler implements EntityAssembler<EncargadoEntity, EncargadoDomain> {

    private static final EncargadoEntityAssembler INSTANCE = new EncargadoEntityAssembler();

    private EncargadoEntityAssembler() {
        super();
    }

    public static EncargadoEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public EncargadoEntity toEntity(final EncargadoDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? EncargadoEntity.obtenerEncargadoDefecto()
                : new EncargadoEntity(
                domain.getId(),
                domain.getNombre(),
                domain.getUsername(),
                domain.getTelefono(),
                domain.getPrefijoTelefono(),
                domain.getContrasena(),
                domain.getCorreo(),
                domain.getTipoDocumento(),
                domain.getDocumento(),
                OrganizacionDeportivaEntityAssembler.getInstance().toEntity(domain.getOrganizacion())
        );
    }

    @Override
    public EncargadoDomain toDomain(final EncargadoEntity entity) {
        var entityAEnsamblar = EncargadoEntity.obtenerValorDefecto(entity);
        return new EncargadoDomain(
                entityAEnsamblar.getId(),
                entityAEnsamblar.getNombre(),
                entityAEnsamblar.getUsername(),
                entityAEnsamblar.getTelefono(),
                entityAEnsamblar.getPrefijoTelefono(),
                entityAEnsamblar.getContrasena(),
                entityAEnsamblar.getCorreo(),
                entityAEnsamblar.getTipoDocumento(),
                entityAEnsamblar.getDocumento(),
                OrganizacionDeportivaEntityAssembler.getInstance().toDomain(entityAEnsamblar.getOrganizacion())
        );
    }

    @Override
    public List<EncargadoDomain> toDomain(final List<EncargadoEntity> entityList) {
        var listaResultados = new ArrayList<EncargadoDomain>();
        for (EncargadoEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<EncargadoEntity> toEntity(final List<EncargadoDomain> domainList) {
        var listaResultados = new ArrayList<EncargadoEntity>();
        for (EncargadoDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }
        return listaResultados;
    }
}
