package co.edu.uco.backend.businesslogic.businesslogic;

import co.edu.uco.backend.businesslogic.businesslogic.domain.OrganizacionDeportivaDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

import java.util.List;
import java.util.UUID;

public interface OrganizacionDeportivaBusinessLogic extends UsuarioBusinessLogic {

    void registrarNuevaOrganizacionDeportiva(OrganizacionDeportivaDomain organizacionDeportiva) throws BackEndException;

    void modificarOrganizacionDeportivaExistente(UUID orgId, OrganizacionDeportivaDomain organizacionDeportiva) throws BackEndException;

    void darBajaDefinitivamenteOrganizacionDeportivaExistente(UUID orgId) throws BackEndException;

    OrganizacionDeportivaDomain consultarOrganizacionDeportivaPorId(UUID orgId) throws BackEndException;

    List<OrganizacionDeportivaDomain> consultarOrganizacionesDeportivas(OrganizacionDeportivaDomain filtro) throws BackEndException;

    void aceptarOrganizacion(UUID orgId);

    void rechazarOrganizacion(UUID orgId);


}
