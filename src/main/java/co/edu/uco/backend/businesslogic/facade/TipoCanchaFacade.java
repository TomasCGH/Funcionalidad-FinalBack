package co.edu.uco.backend.businesslogic.facade;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.dto.TipoCanchaDTO;

import java.util.List;
import java.util.UUID;

public interface TipoCanchaFacade {

    void registrarNuevoTipoCancha(TipoCanchaDTO tipoCancha) throws BackEndException;

    void modificarTipoCanchaExistente(UUID id, TipoCanchaDTO tipoCancha) throws BackEndException;

    void darBajaDefinitivamenteTipoCanchaExistente(UUID tipoCanchaId) throws BackEndException;

    TipoCanchaDTO consultarTipoCanchaPorId(UUID tipoCanchaId) throws BackEndException;

    List<TipoCanchaDTO> consultarTipoCanchas(TipoCanchaDTO filtro) throws BackEndException;

}
