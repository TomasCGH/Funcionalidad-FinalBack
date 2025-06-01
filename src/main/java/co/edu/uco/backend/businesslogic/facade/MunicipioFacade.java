package co.edu.uco.backend.businesslogic.facade;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.MunicipioDTO;

import java.util.List;
import java.util.UUID;

public interface MunicipioFacade {

    void registrarNuevoMunicipio(UUID departamentoId, MunicipioDTO municipio) throws BackEndException;

    void modificarMunicipioExistente(UUID departamentoId, UUID municipioId, MunicipioDTO municipio) throws BackEndException;

    void darBajaDefinitivamenteMunicipioExistente(UUID departamentoId, UUID municipioId) throws BackEndException;

    MunicipioDTO consultarMunicipioPorId(UUID departamentoId, UUID municipioId) throws BackEndException;

    List<MunicipioDTO> consultarMunicipios(UUID departamentoID, MunicipioDTO filtro) throws BackEndException;

}
