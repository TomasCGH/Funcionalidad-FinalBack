package co.edu.uco.backend.data.dao.entity.organizaciondeportiva.impl.postgresql;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.DataBackEndException;
import co.edu.uco.backend.data.dao.entity.organizaciondeportiva.OrganizacionDeportivaDAO;
import co.edu.uco.backend.entity.OrganizacionDeportivaEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class OrganizacionDeportivaPostgreSQLDAO implements OrganizacionDeportivaDAO {

    private Connection connection;

    public OrganizacionDeportivaPostgreSQLDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(OrganizacionDeportivaEntity entity) throws BackEndException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("INSERT INTO organizaciondeportiva(codigoorganizacion, nombre, usuario, contrasena, prefijo, telefono, documentoexistencia, correo, paginaweb, codigoestadover)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,entity.getId());
            sentenciaPreparada.setString(2,entity.getNombre());
            sentenciaPreparada.setString(3,entity.getUsername());
            sentenciaPreparada.setString(4,entity.getContrasena());
            sentenciaPreparada.setString(5,entity.getPrefijoTelefono());
            sentenciaPreparada.setString(6,entity.getTelefono());
            sentenciaPreparada.setString(7,entity.getDocumentoExistencia());
            sentenciaPreparada.setString(8,entity.getCorreoAdministrativo());
            sentenciaPreparada.setString(9,entity.getPaginaWeb());
            sentenciaPreparada.setObject(10,entity.getEstadoVerificacion());


            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de registrar la nueva informacion de la organizacion deportiva en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de registrar la nueva informacion de la organizacion deportiva en la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        }catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de ingresar la nueva infromacion de la organizacion deportiva en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de ingresar la nueva informacion de la organizacion deportiva en la base de datos    ";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void eliminar(UUID organizacionDeportivaId) throws BackEndException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("DELETE FROM organizaciondeportiva WHERE codigoorganizacion = ?)");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,organizacionDeportivaId);

            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de hacer un DELETE en la tabla de organizacion deportiva en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar definitivamente informacion de la organizacion deportiva deseada de la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        }catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer un DELETE en la tabla de organizacion deportiva en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de borrar definitivamente la informacion de la organizacion deportiva en la base de datos    ";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public List<OrganizacionDeportivaEntity> consultar(OrganizacionDeportivaEntity entity) {
        return List.of();
    }

    @Override
    public OrganizacionDeportivaEntity consultarPorId(UUID id) {
        return null;
    }


    @Override
    public void modificar(UUID organizacionDeportivaId, OrganizacionDeportivaEntity entity) throws BackEndException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("UPDATE organizaciondeportiva SET nombre = ?, usuario = ?, contrasena = ?,prefijo = ?, telefono = ?, documentoexistencia = ?, correo = ?, paginaweb = ?, codigoestadover = ? WHERE codigoorganizacion = ?)");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,organizacionDeportivaId);
            sentenciaPreparada.setString(2,entity.getNombre());
            sentenciaPreparada.setString(3,entity.getUsername());
            sentenciaPreparada.setString(4,entity.getContrasena());
            sentenciaPreparada.setString(5,entity.getPrefijoTelefono());
            sentenciaPreparada.setString(6,entity.getTelefono());
            sentenciaPreparada.setString(7,entity.getDocumentoExistencia());
            sentenciaPreparada.setString(8,entity.getCorreoAdministrativo());
            sentenciaPreparada.setString(9,entity.getPaginaWeb());
            sentenciaPreparada.setObject(10,entity.getEstadoVerificacion());

            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de modificar la nueva informacion de la organizacion deportiva deseada en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la nueva informacion de la organizacion deportiva deseada en la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        }catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer un UPDATE de la organizacion deportiva deseada en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la nueva informacion de la organizacion deportiva deseada en la base de datos    ";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

    }
}
