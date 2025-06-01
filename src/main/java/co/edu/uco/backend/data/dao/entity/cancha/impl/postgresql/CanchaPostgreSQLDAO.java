package co.edu.uco.backend.data.dao.entity.cancha.impl.postgresql;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.DataBackEndException;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.backend.data.dao.entity.cancha.CanchaDAO;
import co.edu.uco.backend.entity.CanchaEntity;
import co.edu.uco.backend.entity.TipoCanchaEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CanchaPostgreSQLDAO implements CanchaDAO {

    private final Connection connection;

    public CanchaPostgreSQLDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void eliminar(UUID codigocancha) throws BackEndException{
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("DELETE FROM cancha WHERE codigocancha = ?)");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,codigocancha);

            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de hacer un DELETE en la tabla de la cancha en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de eliminar definitivamente informacion de la cancha deseada de la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        }catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer un DELETE en la tabla cancha en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema INESPERADO tratando de borrar definitivamente la informacion de la cancha en la base de datos    ";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }






    @Override
    public List<CanchaEntity> consultar(CanchaEntity filtro) {
        return List.of();
    }

    @Override
    public CanchaEntity consultarPorId(UUID id) throws BackEndException {

        var CanchaEntityRetorno = new CanchaEntity();
        var sentenciaSQL = new StringBuilder();


        sentenciaSQL.append("SELECT codigocancha, nombreCancha, tipo, dimensiones, superficie, costoHora, ubicacion, organizacion, iluminacion, cubierta, HorariosDisponibles, HorariosEspeciales FROM cancha WHERE codigocancha = ?");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,id);

            try(var cursorResultados = sentenciaPreparada.executeQuery()){

                if(cursorResultados.next()){
                    CanchaEntityRetorno.setId(UtilUUID.convertirAUUID(cursorResultados.getString("codigocancha")));
                    CanchaEntityRetorno.setNombreCancha(cursorResultados.getString("nombreCancha"));
                    


                }
            }

        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de consultar la informacion de la cancha con el ID deseado , para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la informacion de la cancha con el ID deseado";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        }catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción de tipo SQLException NO CONTROLADA tratando de consultar la infromacion de la cancha con el ID deseado en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de consultar informacion de la cancha con el ID deseado";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
        return CanchaEntityRetorno;
    }

    @Override
    public void crear(CanchaEntity entity) throws BackEndException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("INSERT INTO cancha(codigocancha, nombreCancha, tipo, dimensiones, superficie, costoHora, ubicacion, organizacion, iluminacion, cubierta, HorariosDisponibles, HorariosEspeciales)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,entity.getId());
            sentenciaPreparada.setString(2,entity.getNombreCancha());
            sentenciaPreparada.setObject(3,entity.getTipo());
            sentenciaPreparada.setObject(4,entity.getSuperficie());
            sentenciaPreparada.setObject(5,entity.getDimensiones());
            sentenciaPreparada.setDouble(6,entity.getCostoHora());
            sentenciaPreparada.setObject(7,entity.getUbicacion());
            sentenciaPreparada.setObject(8,entity.getOrganizacion());
            sentenciaPreparada.setBoolean(9,entity.isIluminacion());
            sentenciaPreparada.setBoolean(10,entity.isCubierta());
            sentenciaPreparada.setObject(11,entity.getHorariosDisponibles());
            sentenciaPreparada.setObject(12,entity.getHorariosEspeciales());

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
    public void modificar(UUID codigocancha, CanchaEntity entity) throws BackEndException{
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("UPDATE cancha SET nombreCancha = ?, tipo = ?, dimensiones = ?, superficie = ?, costoHora = ?, ubicacion = ?, iluminacion = ?, cubierta = ?, HorariosDisponibles = ?, HorariosEspeciales = ? WHERE codigocancha = ?)");
        try (var sentenciaPreparada = connection.prepareStatement(sentenciaSQL.toString())){
            sentenciaPreparada.setObject(1,codigocancha);
            sentenciaPreparada.setString(2,entity.getNombreCancha());
            sentenciaPreparada.setObject(3,entity.getTipo());
            sentenciaPreparada.setObject(4,entity.getSuperficie());
            sentenciaPreparada.setObject(5,entity.getDimensiones());
            sentenciaPreparada.setDouble(6,entity.getCostoHora());
            sentenciaPreparada.setObject(7,entity.getUbicacion());
            sentenciaPreparada.setObject(8,entity.getOrganizacion());
            sentenciaPreparada.setBoolean(9,entity.isIluminacion());
            sentenciaPreparada.setBoolean(10,entity.isCubierta());
            sentenciaPreparada.setObject(11,entity.getHorariosDisponibles());
            sentenciaPreparada.setObject(12,entity.getHorariosEspeciales());

            sentenciaPreparada.executeUpdate();
        } catch (SQLException exception) {
            var mensajeTecnico = "Se presentó una SQLException tratando de modificar la nueva informacion de la cancha deseada en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema tratando de modificar la nueva informacion de la cancha deseada en la fuente de datos";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);

        }catch (Exception exception) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA tratando de hacer un UPDATE de la cancha deseada en la base de datos, para más detalles revise el log de errores";
            var mensajeUsuario = "Se ha presentado un problema inesperado tratando de modificar la nueva informacion de la cancha deseada en la base de datos    ";

            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, exception);
        }
    }
}





