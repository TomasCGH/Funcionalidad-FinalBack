package co.edu.uco.backend.businesslogic.businesslogic.impl;

import co.edu.uco.backend.businesslogic.businesslogic.OrganizacionDeportivaBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.CanchaDomain;
import co.edu.uco.backend.businesslogic.businesslogic.domain.OrganizacionDeportivaDomain;
import co.edu.uco.backend.businesslogic.businesslogic.domain.UsuarioDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.entity.OrganizacionDeportivaEntity;

import java.util.List;
import java.util.UUID;

public class OrganizacionDeportivaBusinessLogicImpl implements OrganizacionDeportivaBusinessLogic {

    private DAOFactory factory;
    public OrganizacionDeportivaBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevaOrganizacionDeportiva(OrganizacionDeportivaDomain organizacionDeportiva) throws BackEndException {
        OrganizacionDeportivaEntity organizacionDeportivaEntity = null;
        factory.getOrganizacionDeportivaDAO().crear(organizacionDeportivaEntity);
    }

    @Override
    public void modificarOrganizacionDeportivaExistente(UUID organizacionDeportivaId, OrganizacionDeportivaDomain organizacionDeportiva) throws BackEndException {
        OrganizacionDeportivaEntity organizacionDeportivaEntity = null;
        factory.getOrganizacionDeportivaDAO().modificar(organizacionDeportivaId,organizacionDeportivaEntity);
    }

    @Override
    public void darBajaDefinitivamenteOrganizacionDeportivaExistente(UUID organizacionDeportivaId) throws BackEndException {
        OrganizacionDeportivaEntity organizacionDeportivaEntity = null;
        factory.getOrganizacionDeportivaDAO().eliminar(organizacionDeportivaId);
    }

    @Override
    public OrganizacionDeportivaDomain consultarOrganizacionDeportivaPorId(UUID organizacionDeportivaId) throws BackEndException {
        OrganizacionDeportivaEntity organizacionDeportivaEntity = null;
        factory.getOrganizacionDeportivaDAO().consultarPorId(organizacionDeportivaId);
        return null;
    }

    @Override
    public List<OrganizacionDeportivaDomain> consultarOrganizacionesDeportivas(OrganizacionDeportivaDomain filtro) throws BackEndException {
        OrganizacionDeportivaEntity organizacionDeportivaFilter = null;
        List<OrganizacionDeportivaEntity> organizacionDeportivaEntities = factory.getOrganizacionDeportivaDAO().consultar(organizacionDeportivaFilter);
        List<OrganizacionDeportivaDomain> datosARetortnar = null;
        return datosARetortnar;
    }

    @Override
    public void aceptarOrganizacion(UUID orgId) {

    }

    @Override
    public void rechazarOrganizacion(UUID orgId) {

    }

    @Override
    public UsuarioDomain iniciarSesion(String username, String rawPassword, String ipAdress, String agentUser) {
        return null;
    }

    @Override
    public void cerrarSesion(UUID usuarioId) {

    }

    @Override
    public void recuperarContrasena(String username) {

    }

    @Override
    public void cambiarContrasena(UUID usuarioId, String rawPasswordActual, String rawPasswordNueva) {

    }

    @Override
    public UsuarioDomain consultarUsuarioPorId(UUID usuarioId) {
        return null;
    }

    @Override
    public List<UsuarioDomain> listarUsuarios(UsuarioDomain filtro) {
        return List.of();
    }


}
