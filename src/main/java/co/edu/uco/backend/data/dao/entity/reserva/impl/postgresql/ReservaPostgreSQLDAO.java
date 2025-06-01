package co.edu.uco.backend.data.dao.entity.reserva.impl.postgresql;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.DataBackEndException;
import co.edu.uco.backend.data.dao.entity.reserva.ReservaDAO;
import co.edu.uco.backend.entity.ReservaEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ReservaPostgreSQLDAO implements ReservaDAO {

    private final Connection connection;

    public ReservaPostgreSQLDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(ReservaEntity entity) throws BackEndException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("INSERT INTO reserva(codigoreserva,codigocliente, codigocancha, fechareserva, fechausocancha, horainicio, horafin, codigoestadores)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,entity.getId());
            sentenciaPreparada.setObject(2,entity.getCliente());
            sentenciaPreparada.setObject(3,entity.getCancha());
            sentenciaPreparada.setObject(4,entity.getFechaReserva());
            sentenciaPreparada.setObject(5,entity.getFechaUsoCancha());
            sentenciaPreparada.setObject(6,entity.getHoraInicio());
            sentenciaPreparada.setObject(7,entity.getHoraFin());
            sentenciaPreparada.setObject(8,entity.getEstado());


            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de registrar la nueva informacion de la reserva en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de registrar la nueva informacion de la reserva en la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        }catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de ingresar la nueva infromacion de la reserva en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de ingresar la nueva informacion de la reserva en la base de datos    ";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void eliminar(UUID reservaId) throws BackEndException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("DELETE FROM reserva WHERE codigoreserva = ?)");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,reservaId);

            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de hacer un DELETE en la tabla reserva en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar definitivamente informacion de la reserva deseada de la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        }catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer un DELETE en la tabla reserva en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de borrar definitivamente la informacion de la reserva en la base de datos    ";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public List<ReservaEntity> consultar(ReservaEntity entity) {
        return List.of();
    }

    @Override
    public ReservaEntity consultarPorId(UUID id) {
        return null;
    }


    @Override
    public void modificar(UUID reservaId, ReservaEntity entity) throws BackEndException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("UPDATE reserva SET codigocliente = ?, codigocancha = ?, fechareserva = ?, fechausocancha = ?, horainicio = ?, horafin = ?, codigoestadores = ? WHERE codigoreserva = ?)");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,reservaId);
            sentenciaPreparada.setObject(2,entity.getCliente());
            sentenciaPreparada.setObject(3,entity.getCancha());
            sentenciaPreparada.setObject(4,entity.getFechaReserva());
            sentenciaPreparada.setObject(5,entity.getFechaUsoCancha());
            sentenciaPreparada.setObject(6,entity.getHoraInicio());
            sentenciaPreparada.setObject(7,entity.getHoraFin());
            sentenciaPreparada.setObject(8,entity.getEstado());

            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de modificar la nueva informacion de la reserva deseada en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la nueva informacion de la reserva deseada en la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        }catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer un UPDATE de la reserva deseada en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la nueva informacion de la reserva deseada en la base de datos    ";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

    }
}
