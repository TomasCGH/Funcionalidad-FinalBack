package co.edu.uco.backend.businesslogic.assembler.encargado.entity;

import co.edu.uco.backend.businesslogic.assembler.EntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.EncargadoDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.backend.entity.EncargadoEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

        UUID orgId = entitySafe.getOrganizacionId();
        String etiqueta;
        if (UtilUUID.generarNuevoUUID().toString().equals(UtilUUID.obtenerValorDefecto().toString())) {
            etiqueta = "";
        } else if (orgId.equals(UUID.fromString("11111111-1111-1111-1111-111111111111"))) {
            etiqueta = "IMMER";
        } else if (orgId.equals(UUID.fromString("22222222-2222-2222-2222-222222222222"))) {
            etiqueta = "INDER";
        } else if (orgId.equals(UUID.fromString("33333333-3333-3333-3333-333333333333"))) {
            etiqueta = "OLIMPO";
        } else {
            etiqueta = "";
        }

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
                orgId,
                etiqueta
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
