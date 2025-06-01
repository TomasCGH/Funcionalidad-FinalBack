package co.edu.uco.backend.businesslogic.assembler.usuario.entity;

import co.edu.uco.backend.businesslogic.assembler.EntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.UsuarioDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.entity.UsuarioEntity;

import java.util.ArrayList;
import java.util.List;

public final class UsuarioEntityAssembler implements EntityAssembler<UsuarioEntity, UsuarioDomain> {

    private static final UsuarioEntityAssembler INSTANCE = new UsuarioEntityAssembler();

    private UsuarioEntityAssembler() {
        super();
    }

    public static UsuarioEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public UsuarioEntity toEntity(final UsuarioDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? UsuarioEntity.obtenerUsuarioDefecto()
                : new UsuarioEntity(
                domain.getId(),
                domain.getNombre(),
                domain.getUsername(),
                domain.getContrasena(),
                domain.getPrefijoTelefono(),
                domain.getTelefono()
        );
    }

    @Override
    public UsuarioDomain toDomain(final UsuarioEntity entity) {
        var entityAEnsamblar = UsuarioEntity.obtenerValorDefecto(entity);
        return new UsuarioDomain(
                entityAEnsamblar.getId(),
                entityAEnsamblar.getNombre(),
                entityAEnsamblar.getUsername(),
                entityAEnsamblar.getContrasena(),
                entityAEnsamblar.getPrefijoTelefono(),
                entityAEnsamblar.getTelefono()
        ) {
            // Clase anónima concreta porque UsuarioDomain es abstracta
        };
    }

    @Override
    public List<UsuarioDomain> toDomain(final List<UsuarioEntity> entityList) {
        var listaResultados = new ArrayList<UsuarioDomain>();
        for (UsuarioEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<UsuarioEntity> toEntity(final List<UsuarioDomain> domainList) {
        var listaResultados = new ArrayList<UsuarioEntity>();
        for (UsuarioDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }
        return listaResultados;
    }
}
