package co.edu.uco.backend.data.dao.entity.encargado.impl.postgresql;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.DataBackEndException;
import co.edu.uco.backend.data.dao.entity.encargado.EncargadoDAO;
import co.edu.uco.backend.entity.EncargadoEntity;
import co.edu.uco.backend.entity.OrganizacionDeportivaEntity;
import co.edu.uco.backend.entity.UsuarioEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class EncargadoPostgreSQLDAO implements EncargadoDAO {

    private final Connection connection;

    public EncargadoPostgreSQLDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(EncargadoEntity entity) throws BackEndException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("INSERT INTO encargado(encargadoId, nombre, usuario, contrasena, prefijo, telefono, correo, tipodocumento, documento, codigoorganizacion)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,entity.getId());
            sentenciaPreparada.setString(2,entity.getNombre());
            sentenciaPreparada.setString(3,entity.getUsername());
            sentenciaPreparada.setString(4,entity.getContrasena());
            sentenciaPreparada.setString(5,entity.getPrefijoTelefono());
            sentenciaPreparada.setString(6,entity.getTelefono());
            sentenciaPreparada.setString(7,entity.getCorreo());
            sentenciaPreparada.setString(8,entity.getTipoDocumento());
            sentenciaPreparada.setString(9,entity.getDocumento());
            sentenciaPreparada.setObject(10,entity.getOrganizacion());


            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de registrar la nueva informacion del Encargado en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de registrar la nueva informacion del Encargado en la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        }catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de ingresar la nueva infromacion del Encargado en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de ingresar la nueva informacion del Encargado en la base de datos    ";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void eliminar(UUID encargadoId) throws BackEndException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("DELETE FROM encargado WHERE encargadoid = ?)");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,encargadoId);

            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de hacer un DELETE en la tabla del encargado en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar definitivamente informacion del encargado deseada de la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        }catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer un DELETE en la tabla del encargado en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de borrar definitivamente la informacion del encargado en la base de datos    ";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public List<EncargadoEntity> consultar(EncargadoEntity entity) {
        return List.of();
    }

    @Override
    public EncargadoEntity consultarPorId(UUID id) {
        return null;
    }



    @Override
    public void modificar(UUID encargadoId, EncargadoEntity entity) throws BackEndException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("UPDATE encargado SET nombre = ?, usuario = ?, contrasena = ?,prefijo = ?, telefono = ?, correo = ?, tipodocumento = ?, documento = ?, codigoorganizacion = ? WHERE encargadoId = ?)");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,encargadoId);
            sentenciaPreparada.setString(2,entity.getNombre());
            sentenciaPreparada.setString(3,entity.getUsername());
            sentenciaPreparada.setString(4,entity.getContrasena());
            sentenciaPreparada.setString(5,entity.getPrefijoTelefono());
            sentenciaPreparada.setString(6,entity.getTelefono());
            sentenciaPreparada.setString(7,entity.getCorreo());
            sentenciaPreparada.setString(8,entity.getTipoDocumento());
            sentenciaPreparada.setString(9,entity.getDocumento());
            sentenciaPreparada.setObject(10,entity.getOrganizacion());

            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de modificar la nueva informacion del encargado deseada en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la nueva informacion del encargado deseada en la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        }catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer un UPDATE del encargado deseada en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la nueva informacion del encargado deseada en la base de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

    }
}
