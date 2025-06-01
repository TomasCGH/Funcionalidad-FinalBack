package co.edu.uco.backend.businesslogic.businesslogic.impl;

import co.edu.uco.backend.businesslogic.businesslogic.EncargadoBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.CanchaDomain;
import co.edu.uco.backend.businesslogic.businesslogic.domain.EncargadoDomain;
import co.edu.uco.backend.businesslogic.businesslogic.domain.UsuarioDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.entity.EncargadoEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EncargadoBusinessLogicImpl implements EncargadoBusinessLogic {

    private DAOFactory factory;
    public EncargadoBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoEncargado(UUID orgId, EncargadoDomain domain) throws BackEndException {
        EncargadoEntity encargadoEntity = null;
        //TODO: Validar que organizacion lo cree
        factory.getEncargadoDAO().crear(encargadoEntity);
    }

    @Override
    public void modificarEncargadoExistente(UUID orgId, UUID encargadoID, EncargadoDomain domain) throws BackEndException {
        EncargadoEntity encargadoEntity = null;
        //TODO: Validar organizacion que lo modifica
        factory.getEncargadoDAO().modificar(encargadoID,encargadoEntity);
    }


    @Override
    public void darBajaDefinitivamenteEncargadoExistente(UUID orgId, UUID encargadoId) throws BackEndException {
        EncargadoEntity encargadoEntity = null;
        //TODO: Validar organizacion que lo elimina
        factory.getEncargadoDAO().eliminar(encargadoId);
    }

    @Override
    public EncargadoDomain consultarEncargadoPorId(UUID orgId, UUID encargadoId) throws BackEndException {
        EncargadoEntity encargadoEntity = null;
        //TODO: Validar organizacion que lo consulta
        factory.getEncargadoDAO().consultarPorId(encargadoId);
        return null;
    }

    @Override
    public List<EncargadoDomain> consultarEncargadosPorOrganizacion(UUID orgId, EncargadoDomain filtro) throws BackEndException {
        EncargadoEntity encargadoFilter = null;
        List<EncargadoEntity> encargadoEntities = factory.getEncargadoDAO().consultar(encargadoFilter);
        List<EncargadoDomain> datosARetornar = null;
        //TODO: Validar organizacion que los consulta

        return datosARetornar;
    }


    @Override
    public String activarCuentaEncargado(String tokenDeActivacion, String rawPasswordNueva) {
        return "";
    }


    @Override
    public UsuarioDomain iniciarSesion(String username, String rawPassword, String ipAdress, String userAgent) {
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
