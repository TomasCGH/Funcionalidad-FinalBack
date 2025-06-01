package co.edu.uco.backend.businesslogic.businesslogic;

import co.edu.uco.backend.businesslogic.businesslogic.domain.TipoCanchaDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;

import java.util.List;
import java.util.UUID;

public interface TipoCanchaBusinessLogic {

    void registrarNuevoTipoCancha(TipoCanchaDomain tipoCancha) throws BackEndException;

    void modificarTipoCanchaExistente(UUID id, TipoCanchaDomain tipoCancha) throws BackEndException;

    void darBajaDefinitivamenteTipoCanchaExistente(UUID tipoCanchaId) throws BackEndException;

    TipoCanchaDomain consultarTipoCanchaPorId(UUID tipoCanchaId) throws BackEndException;

    List<TipoCanchaDomain> consultarTipoCanchas(TipoCanchaDomain filtro) throws BackEndException;

}
