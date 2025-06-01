package co.edu.uco.backend.data.dao.factory;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.DataBackEndException;
import co.edu.uco.backend.data.dao.entity.departamento.DepartamentoDAO;
import co.edu.uco.backend.data.dao.entity.municipio.MunicipioDAO;
import co.edu.uco.backend.data.dao.entity.ubicacionprecisa.UbicacionPrecisaDAO;
import co.edu.uco.backend.data.dao.entity.tipocancha.TipoCanchaDAO;
import co.edu.uco.backend.data.dao.entity.horariodisponible.HorarioDisponibleDAO;
import co.edu.uco.backend.data.dao.entity.horarioespecial.HorarioEspecialDAO;
import co.edu.uco.backend.data.dao.entity.estadoverificacion.EstadoVerificacionDAO;
import co.edu.uco.backend.data.dao.entity.organizaciondeportiva.OrganizacionDeportivaDAO;
import co.edu.uco.backend.data.dao.entity.encargado.EncargadoDAO;
import co.edu.uco.backend.data.dao.entity.cancha.CanchaDAO;
import co.edu.uco.backend.data.dao.entity.estadoreserva.EstadoReservaDAO;
import co.edu.uco.backend.data.dao.entity.cliente.ClienteDAO;
import co.edu.uco.backend.data.dao.entity.reserva.ReservaDAO;
import co.edu.uco.backend.data.dao.entity.resena.ResenaDAO;
import co.edu.uco.backend.data.dao.entity.factura.FacturaDAO;
import co.edu.uco.backend.data.dao.entity.superficie.SuperficieDAO;
import co.edu.uco.backend.data.dao.entity.dimension.DimensionDAO;
import co.edu.uco.backend.data.dao.factory.postgresql.PostgreSQLDAOFactory;


public abstract class DAOFactory{


    public static DAOFactory getFactory(Factory factory) throws BackEndException {
        switch (factory) {
            case POSTGRE_SQL: {
                return new PostgreSQLDAOFactory();
            }
            default:
                var mensajeUsuario = "Se ha presentado un problema tratando de obtener la informacion de la fuente de datos contra la cual se llevaran a cabo las operaciones";
                var mensajeTecnico = "Se solicitó la factoría " + factory + " pero no está implementada...";
                throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico);
        }
    }

    public abstract void abrirConexion() throws BackEndException;

    public abstract void iniciarTransaccion() throws BackEndException;

    public abstract void confirmarTransaccion() throws BackEndException;

    public abstract void cancelarTransaccion() throws BackEndException;

    public abstract void cerrarConexion() throws BackEndException;




    public abstract DepartamentoDAO getDepartamentoDAO() throws BackEndException;
    public abstract MunicipioDAO getMunicipioDAO() throws BackEndException;
    public abstract UbicacionPrecisaDAO getUbicacionPrecisaDAO() throws BackEndException;
    public abstract TipoCanchaDAO getTipoCanchaDAO() throws BackEndException;
    public abstract HorarioDisponibleDAO getHorarioDisponibleDAO() throws BackEndException;
    public abstract HorarioEspecialDAO getHorarioEspecialDAO() throws BackEndException;
    public abstract EstadoVerificacionDAO getEstadoVerificacionDAO() throws BackEndException;
    public abstract OrganizacionDeportivaDAO getOrganizacionDeportivaDAO() throws BackEndException;
    public abstract EncargadoDAO getEncargadoDAO() throws BackEndException;
    public abstract CanchaDAO getCanchaDAO() throws BackEndException;
    public abstract EstadoReservaDAO getEstadoReservaDAO() throws BackEndException;
    public abstract ClienteDAO getClienteDAO() throws BackEndException;
    public abstract ReservaDAO getReservaDAO() throws BackEndException;
    public abstract ResenaDAO getResenaDAO() throws BackEndException;
    public abstract FacturaDAO getFacturaDAO() throws BackEndException;
    public abstract SuperficieDAO getSuperficieDAO() throws BackEndException;
    public abstract DimensionDAO getDimensionDAO() throws BackEndException;

}
