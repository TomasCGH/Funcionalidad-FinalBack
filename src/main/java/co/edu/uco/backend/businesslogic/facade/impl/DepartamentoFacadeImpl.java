package co.edu.uco.backend.businesslogic.facade.impl;

import co.edu.uco.backend.businesslogic.businesslogic.domain.DepartamentoDomain;
import co.edu.uco.backend.businesslogic.businesslogic.impl.DepartamentoBusinessLogicImpl;
import co.edu.uco.backend.businesslogic.businesslogic.DepartamentoBusinessLogic;
import co.edu.uco.backend.businesslogic.facade.DepartamentoFacade;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.data.dao.factory.Factory;
import co.edu.uco.backend.dto.DepartamentoDTO;

import java.util.List;
import java.util.UUID;

public class DepartamentoFacadeImpl implements DepartamentoFacade {

    private DAOFactory daoFactory;
    private DepartamentoBusinessLogic departamentoBusinessLogic;

    public DepartamentoFacadeImpl() throws BackEndException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        departamentoBusinessLogic = new DepartamentoBusinessLogicImpl(daoFactory);
    }

    @Override
    public void registrarNuevoDepartamento(DepartamentoDTO departamento) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            DepartamentoDomain departamentoDomain = null; //TODO: magia de convertir de DTO a Domain
            departamentoBusinessLogic.registrarNuevoDepartamento(departamentoDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de registrar la informacion del nuevo departamento, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registrar la informacion del nuevo departamento";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void modificarDepartamentoExistente(UUID departamentoId, DepartamentoDTO departamento) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            DepartamentoDomain departamentoDomain = null; //TODO: magia de convertir de DTO a Domain
            departamentoBusinessLogic.modificarDepartamentoExistente(departamentoId, departamentoDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de modificar la informacion del departamento, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la informacion del departamento";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void darBajaDefinitivamenteDepartamentoExistente(UUID departamentoId) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            DepartamentoDomain departamentoDomain = null; //TODO: magia de convertir de DTO a Domain
            departamentoBusinessLogic.darBajaDefinitivamenteDepartamentoExistente(departamentoId);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de eliminar la informacion del departamento, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de eliminar la informacion del departamento";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public DepartamentoDTO consultarDepartamentoPorId(UUID departamentoId) throws BackEndException {
        try {
            var departamentoDomainResultado = departamentoBusinessLogic.consultarDepartamentoPorId(departamentoId);;
            //TODO: Magia de convertir de domain a DTO de respuesta
            return null;
        } catch (BackEndException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de consultar la informacion del departamento con el identificador deseado, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de consultar la informacion del departamento con el identificador deseado...";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<DepartamentoDTO> consultarDepartamentos(DepartamentoDTO filtro) throws BackEndException {
        try {
            // 1. TODO: convertir DTO(filtro) -> Domain
            // CanchaDomain filtroDomain = CanchaAssembler.toDomain(filtro);
            DepartamentoDomain filtroDomain = null;
            // 2. Consultar lista global de canchas
            List<DepartamentoDomain> dominios = departamentoBusinessLogic.consultarDepartamentos(filtroDomain);
            // 3. TODO: convertir cada Domain -> DTO
            // return dominios.stream().map(CanchaAssembler::toDto).collect(Collectors.toList());
            return List.of();
        } catch (BackEndException ex) {
            throw ex;
        } catch (Exception ex) {
            var mensajeUsuario = "Se ha presentado un problema inesperado al consultar todos los clientes";
            var mensajeTecnico = "Excepción inesperada listando todas los clientes";
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
