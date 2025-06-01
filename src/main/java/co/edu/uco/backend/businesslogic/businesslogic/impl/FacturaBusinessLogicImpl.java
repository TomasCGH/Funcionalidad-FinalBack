package co.edu.uco.backend.businesslogic.businesslogic.impl;

import co.edu.uco.backend.businesslogic.businesslogic.FacturaBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.FacturaDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.entity.FacturaEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FacturaBusinessLogicImpl implements FacturaBusinessLogic {

    private DAOFactory factory;
    public FacturaBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }


    @Override
    public void registrarNuevaFactura(UUID reservaId, FacturaDomain factura) throws BackEndException {
        FacturaEntity facturaEntity = null;
        //TODO: Validar reserva
        factory.getFacturaDAO().crear(facturaEntity);
    }

    @Override
    public void modificarFacturaExistente(UUID reservaId, UUID facturaId, FacturaDomain factura) throws BackEndException {
        FacturaEntity facturaEntity = null;
        //TODO: Validar reserva
        factory.getFacturaDAO().modificar(facturaId, facturaEntity);
    }

    @Override
    public void darBajaDefinitivamenteFacturaExistente(UUID reservaId, UUID facturaId) throws BackEndException {
        FacturaEntity facturaEntity = null;
        //TODO: Validar reserva
        factory.getFacturaDAO().eliminar(facturaId);
    }

    @Override
    public FacturaDomain consultarFacturaPorId(UUID reservaId, UUID facturaid) throws BackEndException {
        FacturaEntity facturaEntity = null;
        //TODO: Validar reserva y mapear entity to domain
        factory.getFacturaDAO().consultarPorId(facturaid);
        return null;
    }

    @Override
    public List<FacturaDomain> consultarFacturasPorReserva(UUID reservaId, FacturaDomain filtro) throws BackEndException {
        FacturaEntity facturaFilter = null;
        List<FacturaEntity> facturaEntities = factory.getFacturaDAO().consultar(facturaFilter);
        List<FacturaDomain> datosARetornar = null;
        return datosARetornar;
    }



    @Override
    public byte[] generarPdfFactura(UUID reservaId, UUID facturaId) {
        //TODO: Logica para generar pdf de factura
        return new byte[0];
    }
}
