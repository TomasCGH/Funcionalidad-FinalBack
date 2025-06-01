package co.edu.uco.backend.businesslogic.businesslogic.impl;

import co.edu.uco.backend.businesslogic.businesslogic.DimensionBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.DimensionDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import co.edu.uco.backend.crosscutting.utilitarios.UtilDouble;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.entity.CanchaEntity;
import co.edu.uco.backend.entity.DimensionEntity;

import java.util.UUID;

public class DimensionBusinessLogicImpl implements DimensionBusinessLogic {

    private DAOFactory factory;
    public DimensionBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevaDimension(UUID canchaId, DimensionDomain dimension) throws BackEndException {
        //  1. Verificar que la cancha exista
        CanchaEntity cancha = factory.getCanchaDAO().consultarPorId(canchaId);
        if (cancha == null) {
            throw BusinessLogicBackEndException.reportar(
                    "La cancha no existe: " + canchaId
            );
        }
        //  2. Validar datos de la dimension
        validarIntegridadDimensiones(dimension);

        //  3. Generar nuevo identificador
        var id = generarIdentificadorNuevaDimension();

        //  4. Recrear el domain con el id generado
        var dimensionDomainACrear = new DimensionDomain(id,dimension.getLargo(),dimension.getAncho());

        //  5. Crear la dimensión
        DimensionEntity dimensionEntity = null;//DimensionEntityAssembler.getInstance().toEntity(dimensionDomainACrear)
        factory.getDimensionDAO().crear(dimensionEntity);
    }

    private void validarIntegridadDimensiones(DimensionDomain dimensiones)
            throws BackEndException {
        if (dimensiones == null) {
            throw BusinessLogicBackEndException.reportar(
                    "Las dimensiones de la cancha son obligatorias"
            );
        }
        if (!UtilDouble.esPositivo(dimensiones.getLargo())) {
            throw BusinessLogicBackEndException.reportar(
                    "El largo de la cancha debe ser mayor que cero"
            );
        }
        if (!UtilDouble.esPositivo(dimensiones.getAncho())) {
            throw BusinessLogicBackEndException.reportar(
                    "El ancho de la cancha debe ser mayor que cero"
            );
        }
    }

    private UUID generarIdentificadorNuevaDimension() throws BackEndException {
        UUID nuevoId;
        var existeId = false;
        do {
            nuevoId = UtilUUID.generarNuevoUUID();
            var dimension = factory.getDimensionDAO().consultarPorId(nuevoId);
            existeId = !UtilUUID.esValorDefecto(dimension.getId());


        }while (existeId);
        return nuevoId;
    }

    public void modificarDimensionExistente(UUID canchaId, UUID dimensionId, DimensionDomain dimension) throws BackEndException {
        //TODO: Validar cancha
        DimensionEntity dimensionEntity = null;//CanchaEntityAssembler.getInstance().toEntity(canchaDomainACrear)
        factory.getDimensionDAO().modificar(dimensionId, dimensionEntity);
    }


    @Override
    public void eliminarDimension(UUID canchaId, UUID dimensionId) throws BackEndException {
        DimensionEntity dimensionEntity = null;
        //TODO: Validar cancha
        factory.getDimensionDAO().eliminar(dimensionId);

    }

    @Override
    public DimensionDomain consultarDimensionPorId(UUID canchaId, UUID dimensionId) throws BackEndException {
        DimensionEntity dimensionEntity = null;
        //TODO: mapear de Entity -> Domain y validar cancha
        factory.getDimensionDAO().consultarPorId(dimensionId);
        return null;
    }


}
