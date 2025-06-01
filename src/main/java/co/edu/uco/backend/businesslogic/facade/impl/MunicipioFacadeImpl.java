package co.edu.uco.backend.businesslogic.facade.impl;

import co.edu.uco.backend.businesslogic.businesslogic.domain.MunicipioDomain;
import co.edu.uco.backend.businesslogic.businesslogic.impl.MunicipioBusinessLogicImpl;
import co.edu.uco.backend.businesslogic.businesslogic.MunicipioBusinessLogic;
import co.edu.uco.backend.businesslogic.facade.MunicipioFacade;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.data.dao.factory.Factory;
import co.edu.uco.backend.dto.MunicipioDTO;

import java.util.List;
import java.util.UUID;

public class MunicipioFacadeImpl implements MunicipioFacade {

    private DAOFactory daoFactory;
    private MunicipioBusinessLogic municipioBusinessLogic;

    public MunicipioFacadeImpl() throws BackEndException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        municipioBusinessLogic = new MunicipioBusinessLogicImpl(daoFactory);
    }


    @Override
    public void registrarNuevoMunicipio(UUID departamentoId, MunicipioDTO municipio) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            MunicipioDomain municipioDomain= null; //TODO: magia de convertir de DTO a Domain
            municipioBusinessLogic.registrarNuevoMunicipio(departamentoId,municipioDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de registrar la informacion del nuevo municipio, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de registrar la informacion del nuevo municipio";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void modificarMunicipioExistente(UUID departamentoId, UUID municipioId, MunicipioDTO municipio) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            MunicipioDomain municipioDomain= null; //TODO: magia de convertir de DTO a Domain
            municipioBusinessLogic.modificarMunicipioExistente(departamentoId,municipioId,municipioDomain);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de modificar la informacion del municipio, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la informacion del municipio";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public void darBajaDefinitivamenteMunicipioExistente(UUID departamentoId, UUID municipioId) throws BackEndException {
        try {
            daoFactory.iniciarTransaccion();

            MunicipioDomain municipioDomain= null; //TODO: magia de convertir de DTO a Domain
            municipioBusinessLogic.darBajaDefinitivamenteMunicipioExistente(departamentoId,municipioId);

            daoFactory.confirmarTransaccion();
        } catch (BackEndException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de eliminar la informacion del municipio, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de eliminar la informacion del municipio";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public MunicipioDTO consultarMunicipioPorId(UUID departamentoId, UUID municipioId) throws BackEndException {
        try {
            var municipioDomainResultado = municipioBusinessLogic.consultarMunicipioPorId(departamentoId,municipioId);
            //TODO: Magia de convertir de domain a DTO de respuesta
            return null;
        } catch (BackEndException exception) {
            throw exception;
        } catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción inesperada de tipo Exception tratando de consultar la informacion del municipio con el identificador deseado, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de consultar la informacion del municipio con el identificador deseado...";

            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }

    @Override
    public List<MunicipioDTO> consultarMunicipios(UUID departamentoID, MunicipioDTO filtro) throws BackEndException {
        try {
            // 1. TODO: convertir DTO(filtro) -> Domain
            // CanchaDomain filtroDomain = CanchaAssembler.toDomain(filtro);
            MunicipioDomain filtroDomain = null;
            // 2. Consultar lista global de canchas
            List<MunicipioDomain> dominios = municipioBusinessLogic.consultarMunicipios(departamentoID,filtroDomain);
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

