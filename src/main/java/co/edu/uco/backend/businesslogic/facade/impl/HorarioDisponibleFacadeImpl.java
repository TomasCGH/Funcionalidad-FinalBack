package co.edu.uco.backend.businesslogic.facade.impl;

import co.edu.uco.backend.businesslogic.businesslogic.domain.HorarioDisponibleDomain;
import co.edu.uco.backend.businesslogic.businesslogic.impl.HorarioDisponibleBusinessLogicImpl;
import co.edu.uco.backend.businesslogic.businesslogic.HorarioDisponibleBusinessLogic;
import co.edu.uco.backend.businesslogic.facade.HorarioDisponibleFacade;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.data.dao.factory.Factory;
import co.edu.uco.backend.dto.HorarioDisponibleDTO;

import java.util.List;
import java.util.UUID;

public class HorarioDisponibleFacadeImpl implements HorarioDisponibleFacade {

    private DAOFactory daoFactory;
    private HorarioDisponibleBusinessLogic horarioDisponibleBusinessLogic;

    public HorarioDisponibleFacadeImpl() throws BackEndException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        horarioDisponibleBusinessLogic = new HorarioDisponibleBusinessLogicImpl(daoFactory);
    }


    @Override
    public void registrarNuevoHorarioDisponible(UUID canchaId, HorarioDisponibleDTO horarioDisponible) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            HorarioDisponibleDomain horarioDisponibleDomain= null; //TODO: magia de convertir de DTO a Domain
            horarioDisponibleBusinessLogic.registrarNuevoHorarioDisponible(canchaId,horarioDisponibleDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de registrar la informacion del nuevo horario disponible, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registrar la informacion del nuevo horario disponible";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void modificarHorarioDisponibleExistente(UUID canchaId, UUID horarioDisponinbleID, HorarioDisponibleDTO horarioDisponible) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            HorarioDisponibleDomain horarioDisponibleDomain= null; //TODO: magia de convertir de DTO a Domain
            horarioDisponibleBusinessLogic.modificarHorarioDisponibleExistente(canchaId,horarioDisponinbleID,horarioDisponibleDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de modificar la informacion del horario disponible, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la informacion del horario disponible";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void darBajaDefinitivamenteHorarioDisponibleExistente(UUID canchaId, UUID horarioDisponibleId) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            HorarioDisponibleDomain horarioDisponibleDomain= null; //TODO: magia de convertir de DTO a Domain
            horarioDisponibleBusinessLogic.darBajaDefinitivamenteHorarioDisponibleExistente(canchaId,horarioDisponibleId);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de eliminar la informacion del horario disponible, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de eliminar la informacion del horario disponible";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public HorarioDisponibleDTO consultarHorarioDisponiblePorId(UUID canchaId, UUID horarioDisponibleId) throws BackEndException {
        try {
            var horarioDisponibleDomainResultado = horarioDisponibleBusinessLogic.consultarHorarioDisponiblePorId(canchaId,horarioDisponibleId);;
            //TODO: Magia de convertir de domain a DTO de respuesta
            return null;
        } catch (BackEndException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de consultar la informacion del horario disponible con el identificador deseado, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de consultar la informacion del horario disponible con el identificador deseado...";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<HorarioDisponibleDTO> consultarHorariosDisponiblesPorCancha(UUID canchaId, HorarioDisponibleDTO filtro) throws BackEndException {
        try {
            // 1. TODO: convertir DTO(filtro) -> Domain
            // CanchaDomain filtroDomain = CanchaAssembler.toDomain(filtro);
            HorarioDisponibleDomain filtroDomain = null;
            // 2. Consultar lista de domains
            List<HorarioDisponibleDomain> dominios = horarioDisponibleBusinessLogic.consultarHorariosDisponiblesPorCancha(canchaId, filtroDomain);
            // 3. TODO: convertir cada Domain -> DTO
            // return dominios.stream().map(CanchaAssembler::toDto).collect(Collectors.toList());
            return List.of();
        } catch (BackEndException ex) {
            throw ex;
        } catch (Exception ex) {
            var mensajeUsuario = "Se ha presentado un problema inesperado al listar horarios disponibles de la cancha";
            var mensajeTecnico = "Excepción inesperada listando horarios disponibles de la cancha " + canchaId;
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
