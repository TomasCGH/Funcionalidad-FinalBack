package co.edu.uco.backend.businesslogic.businesslogic;

import co.edu.uco.backend.businesslogic.businesslogic.domain.FacturaDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

import java.util.List;
import java.util.UUID;

public interface FacturaBusinessLogic {

    void registrarNuevaFactura(UUID reservaId, FacturaDomain factura) throws BackEndException;

    void modificarFacturaExistente(UUID reservaId, UUID facturaId, FacturaDomain factura) throws BackEndException;

    void darBajaDefinitivamenteFacturaExistente(UUID reservaId,UUID facturaId) throws BackEndException;

    FacturaDomain consultarFacturaPorId(UUID reservaId,UUID facturaid) throws BackEndException;

    List<FacturaDomain> consultarFacturasPorReserva(UUID reservaId, FacturaDomain filtro) throws BackEndException;

    byte[] generarPdfFactura(UUID reservaId, UUID facturaId);

}
