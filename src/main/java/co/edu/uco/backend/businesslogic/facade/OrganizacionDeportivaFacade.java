package co.edu.uco.backend.businesslogic.facade;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.OrganizacionDeportivaDTO;

import java.util.List;
import java.util.UUID;

public interface OrganizacionDeportivaFacade extends UsuarioFacade {

    void registrarNuevaOrganizacionDeportiva(OrganizacionDeportivaDTO organizacionDeportiva) throws BackEndException;

    void modificarOrganizacionDeportivaExistente(UUID orgId, OrganizacionDeportivaDTO organizacionDeportiva) throws BackEndException;

    void darBajaDefinitivamenteOrganizacionDeportivaExistente(UUID orgId) throws BackEndException;

    OrganizacionDeportivaDTO consultarOrganizacionDeportivaPorId(UUID orgId) throws BackEndException;

    List<OrganizacionDeportivaDTO> consultarOrganizacionesDeportivas(OrganizacionDeportivaDTO filtro) throws BackEndException;

    void aceptarOrganizacion(UUID orgId);

    void rechazarOrganizacion(UUID orgId);

}
