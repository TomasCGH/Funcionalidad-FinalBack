package co.edu.uco.backend.data.dao.entity.horariodisponible.impl.postgresql;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.DataBackEndException;
import co.edu.uco.backend.data.dao.entity.horariodisponible.HorarioDisponibleDAO;
import co.edu.uco.backend.entity.HorarioDisponibleEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class HorarioDisponiblePostgreSQLDAO implements HorarioDisponibleDAO {

    private final Connection connection;

    public HorarioDisponiblePostgreSQLDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(HorarioDisponibleEntity entity) throws BackEndException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("INSERT INTO cancha(id, cancha, dia, horaApertura, horaCierre)" +
                " VALUES (?, ?, ?, ?, ?)");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,entity.getId());
            sentenciaPreparada.setObject(2,entity.getCancha());
            sentenciaPreparada.setObject(3,entity.getDia());
            sentenciaPreparada.setObject(4,entity.getHoraApertura());
            sentenciaPreparada.setObject(5,entity.getHoraCierre());
            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de registrar la nueva informacion de la cancha en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de registrar la nueva informacion de la cancha en la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        }catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de ingresar la nueva infromacion de la cancha en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de ingresar la nueva informacion de la cancha en la base de datos    ";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void eliminar(UUID id) throws BackEndException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("DELETE FROM HorarioDisponible WHERE id = ?)");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,id);

            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de hacer un DELETE en la tabla del HorarioDisponible en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar definitivamente informacion del HorarioDisponible deseada de la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        }catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer un DELETE en la tabla HorarioDisponible en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de borrar definitivamente la informacion del HorarioDisponible en la base de datos    ";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public List<HorarioDisponibleEntity> consultar(HorarioDisponibleEntity entity) {
        return List.of();
    }

    @Override
    public HorarioDisponibleEntity consultarPorId(UUID id) {
        return null;
    }


    @Override
    public void modificar(UUID id, HorarioDisponibleEntity entity) throws BackEndException{
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("UPDATE HorarioDisponible SET cancha = ?, dia = ?, horaApertura = ?, horaCierre = ? WHERE id = ?)");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,id);
            sentenciaPreparada.setObject(2,entity.getCancha());
            sentenciaPreparada.setObject(3,entity.getDia());
            sentenciaPreparada.setObject(4,entity.getHoraApertura());
            sentenciaPreparada.setObject(5,entity.getHoraCierre());


            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de modificar la nueva informacion del HorarioDisponible deseada en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la nueva informacion del HorarioDisponible deseada en la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        }catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer un UPDATE del HorarioDisponible deseada en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la nueva informacion del HorarioDisponible deseada en la base de datos    ";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }
}
