package co.edu.uco.backend.businesslogic.businesslogic.impl;

import co.edu.uco.backend.businesslogic.businesslogic.UbicacionPrecisaBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.UbicacionPrecisaDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import co.edu.uco.backend.crosscutting.utilitarios.UtilDouble;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.entity.CanchaEntity;
import co.edu.uco.backend.entity.MunicipioEntity;
import co.edu.uco.backend.entity.UbicacionPrecisaEntity;

import java.util.List;
import java.util.UUID;

public class UbicacionPrecisaBusinessLogicImpl implements UbicacionPrecisaBusinessLogic {

    private DAOFactory factory;
    public UbicacionPrecisaBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevaUbicacionPrecisa(UUID canchaId,  UbicacionPrecisaDomain ubicacionPrecisa) throws BackEndException {
        //  1. Verificar que la cancha exista
        CanchaEntity cancha = factory.getCanchaDAO().consultarPorId(canchaId);
        if (cancha == null) {
            throw BusinessLogicBackEndException.reportar(
                    "La cancha no existe: " + canchaId
            );
        }

        //  2. Validar datos de la ubicacion precisa
        validarIntegridadUbicacionPrecisa(ubicacionPrecisa);


        //  3. Verificar que el municipio (y por tanto su departamento) existen
        UUID municipioId = ubicacionPrecisa.getMunicipio().getId();
        MunicipioEntity municipio = factory.getMunicipioDAO().consultarPorId(municipioId);
        if (municipio == null) {
            throw BusinessLogicBackEndException.reportar(
                    "El municipio no existe: " + municipioId
            );
        }
        if (municipio.getDepartamento() == null) {
            throw BusinessLogicBackEndException.reportar(
                    "El municipio no está vinculado a ningún departamento: " + municipioId
            );
        }

        //  4. Generar nuevo identificador
        var id = generarIdentificadorNuevaUbicacion();

        //  5. Recrear el domain con el id generado
        var ubicacionDomainACrear = new UbicacionPrecisaDomain(id,ubicacionPrecisa.getDireccion(),ubicacionPrecisa.getLatitud(),ubicacionPrecisa.getLongitud(),ubicacionPrecisa.getMunicipio(),ubicacionPrecisa.getInformacionAdicional());

        //  6. Crear la ubicacion
        UbicacionPrecisaEntity ubicacionPrecisaEntity = null; //ubicacionPrecisaEntityAssembler.getInstance().toEntity(ubicacionPrecisaDomainACrear)
        ubicacionPrecisaEntity.setMunicipio(municipio);
        factory.getUbicacionPrecisaDAO().crear(ubicacionPrecisaEntity);
    }

    private void validarIntegridadUbicacionPrecisa(UbicacionPrecisaDomain ubicacionPrecisa) throws BackEndException {
        if (ubicacionPrecisa == null) {
            throw BusinessLogicBackEndException.reportar("La ubicación precisa es obligatoria");
        }
        validarDireccion(ubicacionPrecisa.getDireccion());
        validarLatitud(ubicacionPrecisa.getLatitud());
        validarLongitud(ubicacionPrecisa.getLongitud());
        // El municipio se valida en el método principal
        validarInformacionAdicional(ubicacionPrecisa.getInformacionAdicional());
    }

    private void validarInformacionAdicional(String informacionAdicional) throws BackEndException {
        if (informacionAdicional != null && UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(informacionAdicional).length() > 500) {
            throw BusinessLogicBackEndException.reportar(
                    "La información adicional no puede superar 500 caracteres"
            );
        }
    }

    private void validarLongitud(double longitud) throws BackEndException {
        if (!UtilDouble.estaEnRango(longitud, -180.0, 180.0)) {
            throw BusinessLogicBackEndException.reportar(
                    "La longitud debe estar entre -180.0 y 180.0"
            );
        }
    }

    private void validarLatitud(double latitud) throws BackEndException {
        if (!UtilDouble.estaEnRango(latitud, -90.0, 90.0)) {
            throw BusinessLogicBackEndException.reportar(
                    "La latitud debe estar entre -90.0 y 90.0"
            );
        }
    }

    private void validarDireccion(String direccion) throws BackEndException {
        if (UtilTexto.getInstance().estaVacia(direccion)) {
            throw BusinessLogicBackEndException.reportar("La dirección es obligatoria");
        }
        if (direccion.length() > 200) {
            throw BusinessLogicBackEndException.reportar("La dirección no puede superar 200 caracteres");
        }
    }

    private UUID generarIdentificadorNuevaUbicacion() throws BackEndException {
        UUID nuevoId;
        var existeId = false;
        do {
            nuevoId = UtilUUID.generarNuevoUUID();
            var ubicacion = factory.getUbicacionPrecisaDAO().consultarPorId(nuevoId);
            existeId = !UtilUUID.esValorDefecto(ubicacion.getId());


        }while (existeId);
        return nuevoId;
    }

    @Override
    public void modificarUbicacionPrecisaExistente(UUID canchaId, UUID ubicacionPrecisaId, UbicacionPrecisaDomain ubicacionPrecisa) throws BackEndException {
        UbicacionPrecisaEntity ubicacionPrecisaEntity = null;
        factory.getUbicacionPrecisaDAO().modificar(ubicacionPrecisaId,ubicacionPrecisaEntity);
    }

    @Override
    public void eliminarUbicacionPrecisa(UUID canchaId, UUID ubicacionPrecisaId) throws BackEndException {
        //TODO: Validar cancha
        factory.getUbicacionPrecisaDAO().eliminar(ubicacionPrecisaId);
    }

    @Override
    public UbicacionPrecisaDomain consultarUbicacionPrecisaPorId(UUID canchaId, UUID ubicacionPrecisaId) throws BackEndException {
        UbicacionPrecisaEntity ubicacionPrecisaEntity = null;
        //TODO: mapear de Entity -> Domain y validar cancha
        factory.getUbicacionPrecisaDAO().consultarPorId(ubicacionPrecisaId);
        return null;
    }

}
