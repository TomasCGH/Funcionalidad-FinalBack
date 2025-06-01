package co.edu.uco.backend.businesslogic.businesslogic.impl;

import co.edu.uco.backend.businesslogic.assembler.cancha.entity.CanchaEntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.CanchaBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.*;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import co.edu.uco.backend.crosscutting.utilitarios.UtilDouble;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.entity.CanchaEntity;

import java.util.List;
import java.util.UUID;


public class CanchaBusinessLogicImpl implements CanchaBusinessLogic {

    private final DAOFactory factory;

    public CanchaBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevaCancha(UUID orgId, CanchaDomain cancha) throws BackEndException {
        //  1. POL-0001. Se debe asegurar que los datos sean válidos a nivel de tipo de dato,
        //  longitud , obligatoriedad, formato y rango
        validarIntegridadInformacionRegistrarNuevaCancha(cancha);

        //  2. POL-0002. Solo una Organización deportiva verificada podrá registrar una nueva cancha
        //  que automáticamente será asociada a ellos

        //  3. POL-0003. Asegurar que el nombre de la cancha no exista ya
        validarNoExistaCanchaConMismoNombre(cancha.getNombreCancha());

        //  4. Generar identificador nueva cancha
        var id = generarIdentificadorNuevaCancha();

        //  5. Recrear el domain con el id generado
        var canchaDomainACrear = new CanchaDomain(id, cancha.getNombreCancha(),cancha.getTipo(),cancha.getDimensiones(),cancha.getSuperficie(),cancha.getCostoHora(),cancha.getUbicacion(),cancha.getOrganizacion(),cancha.isIluminacion(),cancha.isCubierta(),cancha.getHorariosDisponibles(),cancha.getHorariosEspeciales());

        //  6. Creamos la cancha siempre y cuando se cumplan todas las reglas
        CanchaEntity canchaEntity = CanchaEntityAssembler.getInstance().toEntity(canchaDomainACrear);
        factory.getCanchaDAO().crear(canchaEntity);
    }



    private void validarIntegridadInformacionRegistrarNuevaCancha(CanchaDomain cancha) throws BackEndException {
        validarIntegridadNombreCancha(cancha.getNombreCancha());
        validarIntegridadTipoCancha(cancha.getTipo());
        validarIntegridadDimensiones(cancha.getDimensiones());
        validarIntegridadSuperficie(cancha.getSuperficie());
        validarIntegridadCostoHora(cancha.getCostoHora());
        validarIntegridadUbicacion(cancha.getUbicacion());
        validarIntegridadOrganizacion(cancha.getOrganizacion());
        validarIntegridadIluminacion(cancha.isIluminacion());
        validarIntegridadCubierta(cancha.isCubierta());
        validarIntegridadHorariosDisponibles(cancha.getHorariosDisponibles());
        validarIntegridadHorariosEspeciales(cancha.getHorariosEspeciales());
    }

    private void validarIntegridadNombreCancha(String nombreCancha) throws BackEndException {
        //  1. Nombre cancha obligatorio
        if(UtilTexto.getInstance().estaVacia(nombreCancha)) {
            throw BusinessLogicBackEndException.reportar("El nombre de la cancha es un dato obligatorio");
        }
        //  2. Nombre cancha longitud valida(menor que 60 caracteres)
        if(UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(nombreCancha).length()>60){
            throw BusinessLogicBackEndException.reportar("El nombre de la cancha supera los 60 caracteres");
        }
        //  3.  Validar que no contenga caracteres especiales, solo letras o números
        if (!UtilTexto.getInstance().contieneSoloLetrasNumerosEspacios(nombreCancha)) {
            throw BusinessLogicBackEndException.reportar(
                    "El nombre de la cancha solo puede contener letras, números y espacios"
            );
        }
    }

    private void validarIntegridadTipoCancha(TipoCanchaDomain tipo)  {
    }
    private void validarIntegridadHorariosEspeciales(List<HorarioEspecialDomain> horariosEspeciales) {
    }

    private void validarIntegridadHorariosDisponibles(List<HorarioDisponibleDomain> horariosDisponibles) {
    }

    private void validarIntegridadCubierta(boolean cubierta) {
        //  1. Longitud: solo 2 caracteres, puede ser "SI" o "NO" donde SI = true, NO = false

        //  2. Obligatoriedad: No es obligatorio, si no se indica, el valor por defecto será "NO"

        //  3. Formato: Valores permitidos --> (SI,NO)
    }

    private void validarIntegridadIluminacion(boolean iluminacion) {
        //  1. Longitud: solo 2 caracteres, puede ser "SI" o "NO" donde SI = true, NO = false

        //  2. Obligatoriedad: No es obligatorio, si no se indica, el valor por defecto será "NO"

        //  3. Formato: Valores permitidos --> (SI,NO)
    }

    private void validarIntegridadOrganizacion(OrganizacionDeportivaDomain organizacion) {
    }

    private void validarIntegridadUbicacion(UbicacionPrecisaDomain ubicacion) {
    }

    private void validarIntegridadCostoHora(double costoHora) throws BackEndException {
        //  1. Obligatorio y positivo
        if (!UtilDouble.esPositivo(costoHora)) {
            throw BusinessLogicBackEndException.reportar(
                    "El costo por hora es obligatorio y debe ser mayor que cero"
            );
        }
        //  2.  Rango permitido entre $30.000 y $1.000.000
        if (!UtilDouble.estaEnRango(costoHora, 30_000.0, 1_000_000.0)) {
            throw BusinessLogicBackEndException.reportar(
                    "El costo por hora debe estar entre $30.000 y $1.000.000"
            );
        }
    }

    private void validarIntegridadSuperficie(SuperficieDomain superficie) {
    }

    private void validarIntegridadDimensiones(DimensionDomain dimensiones) {
    }

    private void validarNoExistaCanchaConMismoNombre(String nombreCancha) throws BackEndException {
        var filtro = new CanchaEntity();
        filtro.setNombreCancha(nombreCancha);

        var listaResultados = factory.getCanchaDAO().consultar(filtro);
        //Si la lista devolvió resultados entonces si existe una cancha con el mismo nombre
        if(!listaResultados.isEmpty()){
            throw BusinessLogicBackEndException.reportar("Ya existe una cancha con ese nombre");
        }
    }

    private UUID generarIdentificadorNuevaCancha() throws BackEndException {
        UUID nuevoId;
        var existeId = false;
        do {
            nuevoId = UtilUUID.generarNuevoUUID();
            var cancha = factory.getCanchaDAO().consultarPorId(nuevoId);
            existeId = !UtilUUID.esValorDefecto(cancha.getId());


        }while (existeId);
        return nuevoId;
    }


    @Override
    public void modificarCanchaExistente(UUID orgId, UUID canchaId, CanchaDomain cancha) throws BackEndException {
        CanchaEntity canchaEntity = null; //CanchaEntityAssembler.getInstance().toEntity(cancha)
        //TODO: Validar organizacion con id
        factory.getCanchaDAO().modificar(canchaId,canchaEntity);

    }

    @Override
    public void darBajaDefinitivamenteCanchaExistente(UUID orgId, UUID canchaId) throws BackEndException {
        //TODO: Validar organizacion con id
        factory.getCanchaDAO().eliminar(canchaId);
    }

    @Override
    public CanchaDomain consultarCanchaPorOrganizacion(UUID orgId, UUID canchaId) throws BackEndException {
        //TODO: Validar organizacion con id
        var canchaEntity = factory.getCanchaDAO().consultarPorId(canchaId);
        return null; //CanchaEntityAssembler.getInstance().toDomain(canchaEntity)
    }

    @Override
    public List<CanchaDomain> listarCanchasPorOrganizacion(UUID orgId, CanchaDomain filtro) throws BackEndException {
        CanchaEntity canchaFilter = null;//CanchaEntityAssembler.getInstance().toEntity(filtro)
        //TODO: Validar organizacion con id
        List<CanchaEntity> canchaEntities = factory.getCanchaDAO().consultar(canchaFilter);
        return null;//CanchaEntityAssembler.getInstance().toDomain(canchaEntities)
    }

    @Override
    public CanchaDomain consultarCanchaPorId(UUID canchaId) throws BackEndException {
        var canchaEntity = factory.getCanchaDAO().consultarPorId(canchaId);
        return null; //CanchaEntityAssembler.getInstance().toDomain(canchaEntity)
    }

    @Override
    public List<CanchaDomain> consultarTodasCanchas(CanchaDomain filtro) throws BackEndException {
        CanchaEntity canchaFilter = null;//CanchaEntityAssembler.getInstance().toEntity(filtro)
        List<CanchaEntity> canchaEntities = factory.getCanchaDAO().consultar(canchaFilter);
        return null; //CanchaEntityAssembler.getInstance().toDomain(canchaEntities)
    }

}
