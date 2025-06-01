package co.edu.uco.backend.api;

import co.edu.uco.backend.businesslogic.facade.FacturaFacade;
import co.edu.uco.backend.businesslogic.facade.impl.FacturaFacadeImpl;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.FacturaDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/facturas")
public class FacturaController {

    private final FacturaFacade facturaFacade;

    public FacturaController() throws BackEndException {
        this.facturaFacade = new FacturaFacadeImpl();
    }

    @GetMapping("/dummy")
    public FacturaDTO getDummy() {
        return new FacturaDTO();
    }

    @GetMapping("/{reservaId}/{facturaId}")
    public ResponseEntity<FacturaDTO> consultarPorId(
            @PathVariable UUID reservaId,
            @PathVariable UUID facturaId
    ) throws BackEndException {
        var factura = facturaFacade.consultarFacturaPorId(reservaId, facturaId);
        return new ResponseEntity<>(factura, HttpStatus.OK);
    }

    @GetMapping("/{reservaId}")
    public ResponseEntity<List<FacturaDTO>> consultarTodasPorReserva(
            @PathVariable UUID reservaId
    ) throws BackEndException {
        var lista = facturaFacade.consultarFacturasPorReserva(reservaId, getDummy());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("/{reservaId}")
    public ResponseEntity<String> crear(
            @PathVariable UUID reservaId,
            @RequestBody FacturaDTO factura
    ) throws BackEndException {
        facturaFacade.registrarNuevaFactura(reservaId, factura);
        return new ResponseEntity<>("Factura registrada correctamente.", HttpStatus.CREATED);
    }

    @PutMapping("/{reservaId}/{facturaId}")
    public ResponseEntity<String> modificar(
            @PathVariable UUID reservaId,
            @PathVariable UUID facturaId,
            @RequestBody FacturaDTO factura
    ) throws BackEndException {
        facturaFacade.modificarFacturaExistente(reservaId, facturaId, factura);
        return new ResponseEntity<>("Factura modificada correctamente.", HttpStatus.OK);
    }

    @DeleteMapping("/{reservaId}/{facturaId}")
    public ResponseEntity<String> eliminar(
            @PathVariable UUID reservaId,
            @PathVariable UUID facturaId
    ) throws BackEndException {
        facturaFacade.darBajaDefinitivamenteFacturaExistente(reservaId, facturaId);
        return new ResponseEntity<>("Factura eliminada correctamente.", HttpStatus.OK);
    }

    @GetMapping("/{reservaId}/{facturaId}/pdf")
    public ResponseEntity<byte[]> descargarPdf(
            @PathVariable UUID reservaId,
            @PathVariable UUID facturaId
    ) throws BackEndException {
        byte[] pdfBytes = facturaFacade.generarPdfFactura(reservaId, facturaId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "factura_" + facturaId + ".pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
