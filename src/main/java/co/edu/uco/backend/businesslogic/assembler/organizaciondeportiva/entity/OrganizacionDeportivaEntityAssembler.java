package co.edu.uco.backend.businesslogic.assembler.organizaciondeportiva.entity;

import co.edu.uco.backend.businesslogic.assembler.*;
import co.edu.uco.backend.businesslogic.assembler.cancha.entity.CanchaEntityAssembler;
import co.edu.uco.backend.businesslogic.assembler.encargado.entity.EncargadoEntityAssembler;
import co.edu.uco.backend.businesslogic.assembler.estadoverificacion.entity.EstadoVerificacionEntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.OrganizacionDeportivaDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.entity.OrganizacionDeportivaEntity;

import java.util.ArrayList;
import java.util.List;

public final class OrganizacionDeportivaEntityAssembler implements EntityAssembler<OrganizacionDeportivaEntity, OrganizacionDeportivaDomain> {

    private static final OrganizacionDeportivaEntityAssembler INSTANCE = new OrganizacionDeportivaEntityAssembler();

    private OrganizacionDeportivaEntityAssembler() {
        super();
    }

    public static OrganizacionDeportivaEntityAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public OrganizacionDeportivaEntity toEntity(final OrganizacionDeportivaDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? OrganizacionDeportivaEntity.obtenerOrganizacionDeportivaDefecto()
                : new OrganizacionDeportivaEntity(
                domain.getId(),
                domain.getNombre(),
                domain.getUsername(),
                domain.getTelefono(),
                domain.getPrefijoTelefono(),
                domain.getContrasena(),
                domain.getDocumentoExistencia(),
                domain.getCorreoAdministrativo(),
                domain.getPaginaWeb(),
                EncargadoEntityAssembler.getInstance().toEntity(domain.getEncargados()),
                CanchaEntityAssembler.getInstance().toEntity(domain.getCanchas()),
                EstadoVerificacionEntityAssembler.getInstance().toEntity(domain.getEstadoVerificacion())
        );
    }

    @Override
    public OrganizacionDeportivaDomain toDomain(final OrganizacionDeportivaEntity entity) {
        var organizacionAEnsamblar = OrganizacionDeportivaEntity.obtenerValorDefecto(entity);
        return new OrganizacionDeportivaDomain(
                organizacionAEnsamblar.getId(),
                organizacionAEnsamblar.getNombre(),
                organizacionAEnsamblar.getUsername(),
                organizacionAEnsamblar.getTelefono(),
                organizacionAEnsamblar.getPrefijoTelefono(),
                organizacionAEnsamblar.getContrasena(),
                organizacionAEnsamblar.getDocumentoExistencia(),
                organizacionAEnsamblar.getCorreoAdministrativo(),
                organizacionAEnsamblar.getPaginaWeb(),
                EncargadoEntityAssembler.getInstance().toDomain(organizacionAEnsamblar.getEncargados()),
                CanchaEntityAssembler.getInstance().toDomain(organizacionAEnsamblar.getCanchas()),
                EstadoVerificacionEntityAssembler.getInstance().toDomain(organizacionAEnsamblar.getEstadoVerificacion())
        );
    }

    @Override
    public List<OrganizacionDeportivaDomain> toDomain(final List<OrganizacionDeportivaEntity> entityList) {
        var listaResultados = new ArrayList<OrganizacionDeportivaDomain>();
        for (OrganizacionDeportivaEntity entity : entityList) {
            listaResultados.add(toDomain(entity));
        }
        return listaResultados;
    }

    @Override
    public List<OrganizacionDeportivaEntity> toEntity(final List<OrganizacionDeportivaDomain> domainList) {
        var listaResultados = new ArrayList<OrganizacionDeportivaEntity>();
        for (OrganizacionDeportivaDomain domain : domainList) {
            listaResultados.add(toEntity(domain));
        }
        return listaResultados;
    }
}

