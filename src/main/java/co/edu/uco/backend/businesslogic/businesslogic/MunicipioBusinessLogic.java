package co.edu.uco.backend.businesslogic.businesslogic;

import co.edu.uco.backend.businesslogic.businesslogic.domain.MunicipioDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

import java.util.List;
import java.util.UUID;

public interface MunicipioBusinessLogic {

    void registrarNuevoMunicipio(UUID departamentoId, MunicipioDomain municipio) throws BackEndException;

    void modificarMunicipioExistente(UUID departamentoId, UUID municipioId, MunicipioDomain municipio) throws BackEndException;

    void darBajaDefinitivamenteMunicipioExistente(UUID departamentoId, UUID municipioId) throws BackEndException;

    MunicipioDomain consultarMunicipioPorId(UUID departamentoId, UUID municipioId) throws BackEndException;

    List<MunicipioDomain> consultarMunicipios(UUID departamentoID, MunicipioDomain filtro) throws BackEndException;

}
