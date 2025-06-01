package co.edu.uco.backend.businesslogic.assembler.cliente.entity;

import co.edu.uco.backend.businesslogic.assembler.EntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.ClienteDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.entity.ClienteEntity;

import java.util.ArrayList;
import java.util.List;

public final class ClienteEntityAssembler implements EntityAssembler<ClienteEntity, ClienteDomain> {

    private static final ClienteEntityAssembler INSTANCE = new ClienteEntityAssembler();

    private ClienteEntityAssembler() {
        super();
    }

    public static ClienteEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public ClienteEntity toEntity(final ClienteDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? ClienteEntity.obtenerClienteDefecto()
                : new ClienteEntity(
                domain.getId(),
                domain.getNombre(),
                domain.getUsername(),
                domain.getContrasena(),
                domain.getPrefijoTelefono(),
                domain.getTelefono()
        );
    }

    @Override
    public ClienteDomain toDomain(final ClienteEntity entity) {
        var entityAEnsamblar = ClienteEntity.obtenerValorDefecto(entity);
        return new ClienteDomain(
                entityAEnsamblar.getId(),
                entityAEnsamblar.getNombre(),
                entityAEnsamblar.getUsername(),
                entityAEnsamblar.getContrasena(),
                entityAEnsamblar.getPrefijoTelefono(),
                entityAEnsamblar.getTelefono()
        );
    }

    @Override
    public List<ClienteDomain> toDomain(final List<ClienteEntity> entityList) {
        var listaResultados = new ArrayList<ClienteDomain>();
        for (ClienteEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<ClienteEntity> toEntity(final List<ClienteDomain> domainList) {
        var listaResultados = new ArrayList<ClienteEntity>();
        for (ClienteDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }
        return listaResultados;
    }
}

