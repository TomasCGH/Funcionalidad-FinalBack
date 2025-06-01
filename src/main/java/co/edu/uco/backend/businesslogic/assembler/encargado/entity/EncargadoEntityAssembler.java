package co.edu.uco.backend.businesslogic.assembler.encargado.entity;

import co.edu.uco.backend.businesslogic.assembler.EntityAssembler;
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
                domain.getContrasena(),
                domain.getPrefijoTelefono(),
                domain.getTelefono(),
                domain.getTipoDocumento(),
                domain.getNumeroDocumento(),
                domain.getCorreo(),
                domain.getOrganizacionId()
        );
    }

    @Override
    public EncargadoDomain toDomain(final EncargadoEntity entity) {
        var entitySafe = EncargadoEntity.obtenerValorDefecto(entity);
        return new EncargadoDomain(
                entitySafe.getId(),
                entitySafe.getNombre(),
                entitySafe.getUsername(),
                entitySafe.getContrasena(),
                entitySafe.getPrefijoTelefono(),
                entitySafe.getTelefono(),
                entitySafe.getTipoDocumento(),
                entitySafe.getNumeroDocumento(),
                entitySafe.getCorreo(),
                entitySafe.getOrganizacionId()
        );
    }

    @Override
    public List<EncargadoDomain> toDomain(final List<EncargadoEntity> entityList) {
        var resultado = new ArrayList<EncargadoDomain>();
        for (EncargadoEntity entity : entityList) {
            resultado.add(toDomain(entity));
        }
        return resultado;
    }

    @Override
    public List<EncargadoEntity> toEntity(final List<EncargadoDomain> domainList) {
        var resultado = new ArrayList<EncargadoEntity>();
        for (EncargadoDomain domain : domainList) {
            resultado.add(toEntity(domain));
        }
        return resultado;
    }
}
