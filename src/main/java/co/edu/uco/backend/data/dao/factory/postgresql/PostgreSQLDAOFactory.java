package co.edu.uco.backend.data.dao.factory.postgresql;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.DataBackEndException;
import co.edu.uco.backend.data.dao.entity.cancha.impl.postgresql.CanchaPostgreSQLDAO;
import co.edu.uco.backend.data.dao.entity.cliente.impl.postgresql.ClientePostgreSQLDAO;
import co.edu.uco.backend.data.dao.entity.departamento.impl.postgresql.DepartamentoPostgreSQLDAO;
import co.edu.uco.backend.data.dao.entity.dimension.impl.postgresql.DimensionPostgreSQLDAO;
import co.edu.uco.backend.data.dao.entity.encargado.impl.postgresql.EncargadoPostgreSQLDAO;
import co.edu.uco.backend.data.dao.entity.estadoreserva.impl.postgresql.EstadoReservaPostgreSQLDAO;
import co.edu.uco.backend.data.dao.entity.estadoverificacion.impl.postgresql.EstadoVerificacionPostgreSQLDAO;
import co.edu.uco.backend.data.dao.entity.factura.impl.postgresql.FacturaPostgreSQLDAO;
import co.edu.uco.backend.data.dao.entity.horariodisponible.impl.postgresql.HorarioDisponiblePostgreSQLDAO;
import co.edu.uco.backend.data.dao.entity.horarioespecial.impl.postgresql.HorarioEspecialPostgreSQLDAO;
import co.edu.uco.backend.data.dao.entity.municipio.impl.postgresql.MunicipioPostgreSQLDAO;
import co.edu.uco.backend.data.dao.entity.organizaciondeportiva.impl.postgresql.OrganizacionDeportivaPostgreSQLDAO;
import co.edu.uco.backend.data.dao.entity.resena.impl.postgresql.ResenaPostgreSQLDAO;
import co.edu.uco.backend.data.dao.entity.reserva.impl.postgresql.ReservaPostgreSQLDAO;
import co.edu.uco.backend.data.dao.entity.superficie.impl.postgresql.SuperficiePostgreSQLDAO;
import co.edu.uco.backend.data.dao.entity.tipocancha.impl.postgresql.TipoCanchaPostgreSQLDAO;
import co.edu.uco.backend.data.dao.entity.ubicacionprecisa.impl.postgresql.UbicacionPrecisaPostgreSQLDAO;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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



public class PostgreSQLDAOFactory extends DAOFactory {

    private Connection conexion;
    private boolean transaccionEstaIniciada;
    private boolean connexionEstaAbierta;

    public PostgreSQLDAOFactory() throws BackEndException {
        transaccionEstaIniciada = false;
        connexionEstaAbierta = false;
    }

    @Override
    public void abrirConexion() throws BackEndException {
        var baseDatos = "DOODB";
        var servidor = "localhost:5432";

        try {
            conexion=DriverManager.getConnection("jdbc:postgresql://" + servidor + "/" + baseDatos, "postgres", "3104136215apa");
            connexionEstaAbierta = true;
        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de obtener la conexión con la base de datos "
                    + baseDatos + " en el servidor " + servidor + ", para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de obtener la conexión con la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de obtener la conexión con la base de datos "
                    + baseDatos + " en el servidor " + servidor + ", para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de obtener la conexión con la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void iniciarTransaccion() throws BackEndException {
        try {
            asegurarConexionAbierta();
            conexion.setAutoCommit(false);
            transaccionEstaIniciada =true;

        } catch (BackEndException exception) {
            throw exception;

        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de iniciar la transacción sobre la conexión con la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de iniciar la transacción con la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de iniciar la transacción sobre la conexión con la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de iniciar la transacción con la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void confirmarTransaccion() throws BackEndException {
        try {

            asegurarConexionAbierta();
            asegurarTransaccionIniciada();
            conexion.commit();
            conexion.setAutoCommit(true);
            transaccionEstaIniciada = false;

        } catch (BackEndException exception) {
            throw exception;

        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de CONFIRMAR la transacción sobre la conexión con la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de confirmar la transacción con la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de CONFIRMAR la transacción sobre la conexión con la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de confirmar la transacción con la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void cancelarTransaccion() throws BackEndException {
        try {
            asegurarConexionAbierta();
            asegurarTransaccionIniciada();
            conexion.rollback();
            conexion.setAutoCommit(true);
            transaccionEstaIniciada = false;

        } catch (BackEndException exception) {
            throw exception;

        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de CANCELAR la transacción sobre la conexión con la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de cancelar la transacción con la fuente de datos para revertir la operación realizada";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de CANCELAR la transacción sobre la conexión con la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de cancelar la transacción con la fuente de datos para revertir la operación realizada";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void cerrarConexion() throws BackEndException {
        try {
            asegurarConexionAbierta();
            conexion.close();
            connexionEstaAbierta = false;

        } catch (BackEndException exception) {
            throw exception;

        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de CERRAR la conexión con la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de cerrar la conexión con la fuente de datos luego de realizar la operación";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        } catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de CERRAR la conexión con la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de cerrar la conexión con la fuente de datos luego de realizar la operación";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    private void asegurarTransaccionIniciada() throws BackEndException {
        if (!transaccionEstaIniciada) {
            var mensajeTecnico = "Se presentó una excepción tratando de gestionar(COMMIT,ROLLBACK) la conexión con la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de gestionar la conexión con la fuente de datos luego de realizar la operación";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico);
        }
    }

    private void asegurarConexionAbierta() throws BackEndException {
        if (!connexionEstaAbierta) {
            var mensajeTecnico = "Se intentó llevar a cabo una operación que requería una conexión abierta, pero al momento de validarla esta cerrada";
            var mensajeUsuario = "Se presentó una excepción tratando de llevar a cabo operación deseada con la conexión cerrada";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico);
        }
    }

    @Override
    public DepartamentoDAO getDepartamentoDAO() throws BackEndException{
        asegurarConexionAbierta();
        return new DepartamentoPostgreSQLDAO(conexion);
    }

    @Override
    public MunicipioDAO getMunicipioDAO() throws BackEndException{
        asegurarConexionAbierta();
        return new MunicipioPostgreSQLDAO(conexion);
    }

    @Override
    public UbicacionPrecisaDAO getUbicacionPrecisaDAO() throws BackEndException {
        asegurarConexionAbierta();
        return new UbicacionPrecisaPostgreSQLDAO(conexion);
    }

    @Override
    public TipoCanchaDAO getTipoCanchaDAO() throws BackEndException {
        asegurarConexionAbierta();
        return new TipoCanchaPostgreSQLDAO(conexion);
    }


    @Override
    public HorarioDisponibleDAO getHorarioDisponibleDAO() throws BackEndException {
        asegurarConexionAbierta();
        return new HorarioDisponiblePostgreSQLDAO(conexion);
    }

    @Override
    public HorarioEspecialDAO getHorarioEspecialDAO() throws BackEndException {
        asegurarConexionAbierta();
        return new HorarioEspecialPostgreSQLDAO(conexion);
    }

    @Override
    public EstadoVerificacionDAO getEstadoVerificacionDAO() throws BackEndException {
        asegurarConexionAbierta();
        return new EstadoVerificacionPostgreSQLDAO(conexion);
    }

    @Override
    public OrganizacionDeportivaDAO getOrganizacionDeportivaDAO() throws BackEndException {
        asegurarConexionAbierta();
        return new OrganizacionDeportivaPostgreSQLDAO(conexion);
    }

    @Override
    public EncargadoDAO getEncargadoDAO() throws BackEndException {
        asegurarConexionAbierta();
        return new EncargadoPostgreSQLDAO(conexion);
    }

    @Override
    public CanchaDAO getCanchaDAO() throws BackEndException {
        asegurarConexionAbierta();
        return new CanchaPostgreSQLDAO(conexion);
    }

    @Override
    public EstadoReservaDAO getEstadoReservaDAO() throws BackEndException {
        asegurarConexionAbierta();
        return new EstadoReservaPostgreSQLDAO(conexion);
    }

    @Override
    public ClienteDAO getClienteDAO() throws BackEndException {
        asegurarConexionAbierta();
        return new ClientePostgreSQLDAO(conexion);
    }

    @Override
    public ReservaDAO getReservaDAO() throws BackEndException {
        asegurarConexionAbierta();
        return new ReservaPostgreSQLDAO(conexion);
    }

    @Override
    public ResenaDAO getResenaDAO() throws BackEndException {
        asegurarConexionAbierta();
        return new ResenaPostgreSQLDAO(conexion);
    }

    @Override
    public FacturaDAO getFacturaDAO() throws BackEndException {
        asegurarConexionAbierta();
        return new FacturaPostgreSQLDAO(conexion);
    }

    @Override
    public SuperficieDAO getSuperficieDAO() throws BackEndException {
        asegurarConexionAbierta();
        return new SuperficiePostgreSQLDAO(conexion);
    }

    @Override
    public DimensionDAO getDimensionDAO() throws BackEndException {
        asegurarConexionAbierta();
        return new DimensionPostgreSQLDAO(conexion);
    }
}

