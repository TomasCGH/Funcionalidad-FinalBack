package co.edu.uco.backend.businesslogic.facade;

import co.edu.uco.backend.dto.FacturaDTO;

import java.util.List;
import java.util.UUID;

public interface FacturaFacade {

    void registrarNuevaFactura(UUID reservaId, FacturaDTO factura);

    void modificarFacturaExistente(UUID reservaId, UUID facturaId, FacturaDTO factura);

    void darBajaDefinitivamenteFacturaExistente(UUID reservaId,UUID facturaId);

    FacturaDTO consultarFacturaPorId(UUID reservaId,UUID facturaid);

    List<FacturaDTO> consultarFacturasPorReserva(UUID reservaId, FacturaDTO filtro);

    byte[] generarPdfFactura(UUID reservaId, UUID facturaId);

}
