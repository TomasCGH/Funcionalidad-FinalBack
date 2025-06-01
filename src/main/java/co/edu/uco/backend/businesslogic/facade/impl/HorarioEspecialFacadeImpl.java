package co.edu.uco.backend.businesslogic.facade.impl;

import co.edu.uco.backend.businesslogic.businesslogic.domain.HorarioEspecialDomain;
import co.edu.uco.backend.businesslogic.businesslogic.impl.HorarioEspecialBusinessLogicImpl;
import co.edu.uco.backend.businesslogic.businesslogic.HorarioEspecialBusinessLogic;
import co.edu.uco.backend.businesslogic.facade.HorarioEspecialFacade;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.data.dao.factory.Factory;
import co.edu.uco.backend.dto.HorarioEspecialDTO;

import java.util.List;
import java.util.UUID;

public class HorarioEspecialFacadeImpl implements HorarioEspecialFacade {

    private DAOFactory daoFactory;
    private HorarioEspecialBusinessLogic horarioEspecialBusinessLogic;

    public HorarioEspecialFacadeImpl() throws BackEndException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        horarioEspecialBusinessLogic = new HorarioEspecialBusinessLogicImpl(daoFactory);
    }


    @Override
    public void registrarNuevoHorarioEspecial(UUID canchaId, HorarioEspecialDTO horarioEspecial) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            HorarioEspecialDomain horarioEspecialDomain= null; //TODO: magia de convertir de DTO a Domain
            horarioEspecialBusinessLogic.registrarNuevoHorarioEspecial(canchaId,horarioEspecialDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de registrar la informacion del nuevo horario especial, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registrar la informacion del nuevo horario especial";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void modificarHorarioEspecialExistente(UUID canchaId, UUID horarioEspecialId, HorarioEspecialDTO horarioEspecial) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            HorarioEspecialDomain horarioEspecialDomain= null; //TODO: magia de convertir de DTO a Domain
            horarioEspecialBusinessLogic.modificarHorarioEspecialExistente(canchaId,horarioEspecialId,horarioEspecialDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de registrar la informacion del nuevo horario especial, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registrar la informacion del nuevo horario especial";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void darBajaDefinitivamenteHorarioEspecialExistente(UUID canchaId, UUID horarioEspecialId) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            HorarioEspecialDomain horarioEspecialDomain= null; //TODO: magia de convertir de DTO a Domain
            horarioEspecialBusinessLogic.darBajaDefinitivamenteHorarioEspecialExistente(canchaId,horarioEspecialId);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de registrar la informacion del nuevo horario especial, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registrar la informacion del nuevo horario especial";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public HorarioEspecialDTO consultarHorarioEspecialPorId(UUID canchaId, UUID horarioEspecialId) throws BackEndException {
        try {
            var horarioEspecialDomainResultado = horarioEspecialBusinessLogic.consultarHorarioEspecialPorId(canchaId,horarioEspecialId);
            //TODO: Magia de convertir de domain a DTO de respuesta
            return null;
        } catch (BackEndException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de consultar la informacion del horario especial con el identificador deseado, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de consultar la informacion del horario especial con el identificador deseado...";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<HorarioEspecialDTO> consultarHorariosEspecialesPorCancha(UUID canchaId, HorarioEspecialDTO horarioEspecial) throws BackEndException {
        try {
            // 1. TODO: convertir DTO(filtro) -> Domain
            // CanchaDomain filtroDomain = CanchaAssembler.toDomain(filtro);
            HorarioEspecialDomain filtroDomain = null;
            // 2. Consultar lista de domains
            List<HorarioEspecialDomain> dominios = horarioEspecialBusinessLogic.consultarHorariosEspecialesPorCancha(canchaId, filtroDomain);
            // 3. TODO: convertir cada Domain -> DTO
            // return dominios.stream().map(CanchaAssembler::toDto).collect(Collectors.toList());
            return List.of();
        } catch (BackEndException ex) {
            throw ex;
        } catch (Exception ex) {
            var mensajeUsuario = "Se ha presentado un problema inesperado al listar horarios especiales de la cancha";
            var mensajeTecnico = "Excepción inesperada listando horarios especiales de la cancha " + canchaId;
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}

