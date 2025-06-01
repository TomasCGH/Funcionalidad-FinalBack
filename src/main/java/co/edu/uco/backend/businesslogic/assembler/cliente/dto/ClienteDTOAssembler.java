package co.edu.uco.backend.businesslogic.assembler.cliente.dto;

import co.edu.uco.backend.businesslogic.assembler.DTOAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.domain.ClienteDomain;
import co.edu.uco.backend.crosscutting.utilitarios.UtilObjeto;
import co.edu.uco.backend.dto.ClienteDTO;

import java.util.ArrayList;
import java.util.List;

public final class ClienteDTOAssembler implements DTOAssembler<ClienteDTO, ClienteDomain> {

    private static final ClienteDTOAssembler INSTANCE = new ClienteDTOAssembler();

    private ClienteDTOAssembler() {
        super();
    }

    public static ClienteDTOAssembler getInstance() {
        return INSTANCE;
    }

    @Override
    public ClienteDTO toDTO(final ClienteDomain domain) {
        return UtilObjeto.getInstance().esNulo(domain)
                ? ClienteDTO.obtenerValorDefecto()
                : new ClienteDTO(
                domain.getId(),
                domain.getNombre(),
                domain.getUsername(),
                domain.getContrasena(),
                domain.getPrefijoTelefono(),
                domain.getTelefono()
        );
    }

    @Override
    public ClienteDomain toDomain(final ClienteDTO dto) {
        var dtoAEnsamblar = ClienteDTO.obtenerValorDefecto(dto);
        return new ClienteDomain(
                dtoAEnsamblar.getId(),
                dtoAEnsamblar.getNombre(),
                dtoAEnsamblar.getUsername(),
                dtoAEnsamblar.getContrasena(),
                dtoAEnsamblar.getPrefijoTelefono(),
                dtoAEnsamblar.getTelefono()
        );
    }

    @Override
    public List<ClienteDTO> toDTOs(final List<ClienteDomain> domainList) {
        final List<ClienteDTO> resultado = new ArrayList<>();
        for (ClienteDomain domain : domainList) {
            resultado.add(toDTO(domain));
        }
        return resultado;
    }

    @Override
    public List<ClienteDomain> toDomains(final List<ClienteDTO> dtoList) {
        final List<ClienteDomain> resultado = new ArrayList<>();
        for (ClienteDTO dto : dtoList) {
            resultado.add(toDomain(dto));
        }
        return resultado;
    }
}
