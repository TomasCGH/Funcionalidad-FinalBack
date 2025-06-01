package co.edu.uco.backend.data.dao.entity.ubicacionprecisa.impl.postgresql;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.DataBackEndException;
import co.edu.uco.backend.data.dao.entity.ubicacionprecisa.UbicacionPrecisaDAO;
import co.edu.uco.backend.entity.UbicacionPrecisaEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class UbicacionPrecisaPostgreSQLDAO implements UbicacionPrecisaDAO {

    private final Connection connection;

    public UbicacionPrecisaPostgreSQLDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(UbicacionPrecisaEntity entity) throws BackEndException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("INSERT INTO ubicacionprecisa(codigoubicacion, direccion, latitud, longitud, codigomunicipio,informacion)" +
                " VALUES (?, ?, ?, ?, ?, ?,?");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,entity.getId());
            sentenciaPreparada.setString(2,entity.getDireccion());
            sentenciaPreparada.setDouble(3,entity.getLatitud());
            sentenciaPreparada.setDouble(4,entity.getLongitud());
            sentenciaPreparada.setObject(5,entity.getMunicipio());
            sentenciaPreparada.setString(6,entity.getInformacionAdicional());

            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de registrar la nueva informacion de la ubicacion de la cancha en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de registrar la nueva informacion de la ubicacion de la cancha en la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        }catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de ingresar la nueva infromacion de la ubicacion de la cancha en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de ingresar la nueva informacion de la ubicacion de la cancha en la base de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public void eliminar(UUID ubicacionPrecisaId) throws BackEndException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("DELETE FROM ubicacionprecisa WHERE codigoubicacion = ?)");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,ubicacionPrecisaId);

            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de hacer un DELETE en la tabla ubicacionprecisa de la cancha en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar definitivamente informacion de la cancha deseada de la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        }catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer un DELETE en la tabla ubicacionprecisa en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de borrar definitivamente la informacion de la ubicacionprecisa en la base de datos    ";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }

    @Override
    public List<UbicacionPrecisaEntity> consultar(UbicacionPrecisaEntity entity) {
        return List.of();
    }

    @Override
    public UbicacionPrecisaEntity consultarPorId(UUID id) {
        return null;
    }


    @Override
    public void modificar(UUID ubicacionPrecisaId, UbicacionPrecisaEntity entity) throws BackEndException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("UPDATE ubicacionprecisa SET direccion = ?, latitud = ?, longitud = ?, codigomunicipio = ?, informacion = ? WHERE codigoubicacion = ?)");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,ubicacionPrecisaId);
            sentenciaPreparada.setString(2,entity.getDireccion());
            sentenciaPreparada.setDouble(3,entity.getLatitud());
            sentenciaPreparada.setDouble(4,entity.getLongitud());
            sentenciaPreparada.setObject(5,entity.getMunicipio());
            sentenciaPreparada.setString(6,entity.getInformacionAdicional());



            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de modificar la nueva informacion de la ubicacionprecisa deseada en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la nueva informacion de la ubicacionprecisa deseada en la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        }catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer un UPDATE de la ubicacionprecisa deseada en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la nueva informacion de la ubicacionprecisa deseada en la base de datos    ";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }

    }
}
