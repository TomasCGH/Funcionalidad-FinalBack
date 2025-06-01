package co.edu.uco.backend.businesslogic.facade.impl;

import co.edu.uco.backend.businesslogic.businesslogic.impl.FacturaBusinessLogicImpl;
import co.edu.uco.backend.businesslogic.businesslogic.FacturaBusinessLogic;
import co.edu.uco.backend.businesslogic.facade.FacturaFacade;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.data.dao.factory.Factory;
import co.edu.uco.backend.dto.FacturaDTO;

import java.util.List;
import java.util.UUID;

public class FacturaFacadeImpl implements FacturaFacade {

    private DAOFactory daoFactory;
    private FacturaBusinessLogic facturaBusinessLogic;

    public FacturaFacadeImpl() throws BackEndException {
        daoFactory = DAOFactory.getFactory(Factory.POSTGRE_SQL);
        facturaBusinessLogic = new FacturaBusinessLogicImpl(daoFactory);
    }


    @Override
    public void registrarNuevaFactura(UUID reservaId, FacturaDTO factura) {

    }

    @Override
    public void modificarFacturaExistente(UUID reservaId, UUID facturaId, FacturaDTO factura) {

    }

    @Override
    public void darBajaDefinitivamenteFacturaExistente(UUID reservaId, UUID facturaId) {

    }

    @Override
    public FacturaDTO consultarFacturaPorId(UUID reservaId, UUID facturaid) {
        return null;
    }

    @Override
    public List<FacturaDTO> consultarFacturasPorReserva(UUID reservaId, FacturaDTO filtro) {
        return List.of();
    }

    @Override
    public byte[] generarPdfFactura(UUID reservaId, UUID facturaId) {
        return new byte[0];
    }
}
