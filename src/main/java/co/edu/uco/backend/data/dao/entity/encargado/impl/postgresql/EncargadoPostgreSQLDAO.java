package co.edu.uco.backend.data.dao.entity.encargado.impl.postgresql;

import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.DataBackEndException;
import co.edu.uco.backend.crosscutting.utilitarios.UtilEncrypt;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.backend.data.dao.entity.encargado.EncargadoDAO;
import co.edu.uco.backend.entity.EncargadoEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Implementación PostgreSQL de EncargadoDAO.
 * Sigue la misma estructura que ClientePostgreSQLDAO, pero adaptada
 * a la entidad Encargado con campos adicionales:
 *  - tipoDocumento
 *  - numeroDocumento
 *  - correo
 *  - organizacionId
 */
public class EncargadoPostgreSQLDAO implements EncargadoDAO {

    private final Connection connection;

    public EncargadoPostgreSQLDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(EncargadoEntity entity) throws BackEndException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append(
                "INSERT INTO doodb.encargado(" +
                        " codigoencargado," +
                        " nombre," +
                        " username," +
                        " contrasena," +
                        " prefijotelefono," +
                        " telefono," +
                        " tipodocumento," +
                        " numerodocumento," +
                        " correo," +
                        " organizacionid" +
                        ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        );

        try (var ps = connection.prepareStatement(sentenciaSQL.toString())) {
            ps.setObject(1, entity.getId());
            ps.setString(2, entity.getNombre());
            ps.setString(3, entity.getUsername());
            String hashed = UtilEncrypt.hash(entity.getContrasena());
            ps.setString(4, hashed);
            ps.setString(5, entity.getPrefijoTelefono());
            ps.setString(6, entity.getTelefono());
            ps.setString(7, entity.getTipoDocumento());
            ps.setString(8, entity.getNumeroDocumento());
            ps.setString(9, entity.getCorreo());
            ps.setObject(10, entity.getOrganizacionId());

            ps.executeUpdate();
        } catch (SQLException ex) {
            var mensajeTecnico = "Se presentó una SQLException tratando de registrar un nuevo Encargado en la base de datos.";
            var mensajeUsuario = "No se pudo registrar el Encargado en el sistema.";
            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        } catch (Exception ex) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA al registrar un nuevo Encargado.";
            var mensajeUsuario = "Ocurrió un error inesperado registrando el Encargado.";
            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        }
    }

    @Override
    public void eliminar(UUID codigoencargado) throws BackEndException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append("DELETE FROM doodb.encargado WHERE codigoencargado = ?");
        try (var ps = connection.prepareStatement(sentenciaSQL.toString())) {
            ps.setObject(1, codigoencargado);
            ps.executeUpdate();
        } catch (SQLException ex) {
            var mensajeTecnico = "Se presentó una SQLException tratando de eliminar un Encargado en la base de datos.";
            var mensajeUsuario = "No se pudo eliminar el Encargado del sistema.";
            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        } catch (Exception ex) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA al eliminar un Encargado.";
            var mensajeUsuario = "Ocurrió un error inesperado eliminando el Encargado.";
            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        }
    }

    @Override
    public List<EncargadoEntity> consultar(EncargadoEntity entity) throws BackEndException {
        var lista = new ArrayList<EncargadoEntity>();
        var sql = new StringBuilder("""
            SELECT codigoencargado
                 , nombre
                 , username
                 , contrasena
                 , prefijotelefono
                 , telefono
                 , tipodocumento
                 , numerodocumento
                 , correo
                 , organizacionid
            FROM doodb.encargado
            WHERE 1=1
            """);

        boolean filtrarId              = !UtilUUID.esValorDefecto(entity.getId());
        boolean filtrarNombre          = !UtilTexto.getInstance().estaVacia(entity.getNombre());
        boolean filtrarUsername        = !UtilTexto.getInstance().estaVacia(entity.getUsername());
        boolean filtrarPrefijoTelefono = !UtilTexto.getInstance().estaVacia(entity.getPrefijoTelefono());
        boolean filtrarTelefono        = !UtilTexto.getInstance().estaVacia(entity.getTelefono());
        boolean filtrarTipoDocumento   = !UtilTexto.getInstance().estaVacia(entity.getTipoDocumento());
        boolean filtrarNumeroDocumento = !UtilTexto.getInstance().estaVacia(entity.getNumeroDocumento());
        boolean filtrarOrganizacionId  = !UtilUUID.esValorDefecto(entity.getOrganizacionId());

        if (filtrarId) {
            sql.append(" AND codigoencargado = ?");
        }
        if (filtrarNombre) {
            sql.append(" AND nombre ILIKE ?");
        }
        if (filtrarUsername) {
            sql.append(" AND username = ?");
        }
        if (filtrarPrefijoTelefono) {
            sql.append(" AND prefijotelefono = ?");
        }
        if (filtrarTelefono) {
            sql.append(" AND telefono = ?");
        }
        if (filtrarTipoDocumento) {
            sql.append(" AND tipodocumento = ?");
        }
        if (filtrarNumeroDocumento) {
            sql.append(" AND numerodocumento = ?");
        }
        if (filtrarOrganizacionId) {
            sql.append(" AND organizacionid = ?");
        }

        try (var ps = connection.prepareStatement(sql.toString())) {
            int idx = 1;
            if (filtrarId) {
                ps.setObject(idx++, entity.getId());
            }
            if (filtrarNombre) {
                ps.setString(idx++, "%" + entity.getNombre().trim() + "%");
            }
            if (filtrarUsername) {
                ps.setString(idx++, entity.getUsername().trim());
            }
            if (filtrarPrefijoTelefono) {
                ps.setString(idx++, entity.getPrefijoTelefono().trim());
            }
            if (filtrarTelefono) {
                ps.setString(idx++, entity.getTelefono().trim());
            }
            if (filtrarTipoDocumento) {
                ps.setString(idx++, entity.getTipoDocumento().trim());
            }
            if (filtrarNumeroDocumento) {
                ps.setString(idx++, entity.getNumeroDocumento().trim());
            }
            if (filtrarOrganizacionId) {
                ps.setObject(idx++, entity.getOrganizacionId());
            }

            try (var rs = ps.executeQuery()) {
                while (rs.next()) {
                    var e = new EncargadoEntity();
                    e.setId(UtilUUID.convertirAUUID(rs.getString("codigoencargado")));
                    e.setNombre(rs.getString("nombre"));
                    e.setUsername(rs.getString("username"));
                    e.setContrasena(rs.getString("contrasena"));
                    e.setPrefijoTelefono(rs.getString("prefijotelefono"));
                    e.setTelefono(rs.getString("telefono"));
                    e.setTipoDocumento(rs.getString("tipodocumento"));
                    e.setNumeroDocumento(rs.getString("numerodocumento"));
                    e.setCorreo(rs.getString("correo"));
                    e.setOrganizacionId(UtilUUID.convertirAUUID(rs.getString("organizacionid")));
                    lista.add(e);
                }
            }
        } catch (SQLException ex) {
            var mensajeTecnico = "Se presentó una SQLException al consultar Encargados en la base de datos.";
            var mensajeUsuario = "No se pudo obtener la información de Encargados.";
            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        } catch (Exception ex) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA al consultar Encargados.";
            var mensajeUsuario = "Ocurrió un error inesperado consultando Encargados.";
            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        }

        return lista;
    }

    @Override
    public List<EncargadoEntity> listAll() throws BackEndException {
        var lista = new ArrayList<EncargadoEntity>();
        var sql = new StringBuilder();
        sql.append("""
            SELECT codigoencargado
                 , nombre
                 , username
                 , contrasena
                 , prefijotelefono
                 , telefono
                 , tipodocumento
                 , numerodocumento
                 , correo
                 , organizacionid
            FROM doodb.encargado
            """);

        try (var ps = connection.prepareStatement(sql.toString());
             var rs = ps.executeQuery()) {
            while (rs.next()) {
                var e = new EncargadoEntity();
                e.setId(UtilUUID.convertirAUUID(rs.getString("codigoencargado")));
                e.setNombre(rs.getString("nombre"));
                e.setUsername(rs.getString("username"));
                e.setContrasena(rs.getString("contrasena"));
                e.setPrefijoTelefono(rs.getString("prefijotelefono"));
                e.setTelefono(rs.getString("telefono"));
                e.setTipoDocumento(rs.getString("tipodocumento"));
                e.setNumeroDocumento(rs.getString("numerodocumento"));
                e.setCorreo(rs.getString("correo"));
                e.setOrganizacionId(UtilUUID.convertirAUUID(rs.getString("organizacionid")));
                lista.add(e);
            }
        } catch (SQLException ex) {
            var mensajeTecnico = "Se presentó una SQLException al listar todos los Encargados.";
            var mensajeUsuario = "No se pudo obtener la lista de Encargados.";
            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        } catch (Exception ex) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA al listar Encargados.";
            var mensajeUsuario = "Ocurrió un error inesperado al listar Encargados.";
            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        }
        return lista;
    }

    @Override
    public EncargadoEntity consultarPorId(UUID codigoencargado) throws BackEndException {
        var eRetorno = new EncargadoEntity();
        var sql = new StringBuilder();
        sql.append("""
            SELECT codigoencargado
                 , nombre
                 , username
                 , contrasena
                 , prefijotelefono
                 , telefono
                 , tipodocumento
                 , numerodocumento
                 , correo
                 , organizacionid
            FROM doodb.encargado
            WHERE codigoencargado = ?
            """);

        try (var ps = connection.prepareStatement(sql.toString())) {
            ps.setObject(1, codigoencargado);
            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    eRetorno.setId(UtilUUID.convertirAUUID(rs.getString("codigoencargado")));
                    eRetorno.setNombre(rs.getString("nombre"));
                    eRetorno.setUsername(rs.getString("username"));
                    eRetorno.setContrasena(rs.getString("contrasena"));
                    eRetorno.setPrefijoTelefono(rs.getString("prefijotelefono"));
                    eRetorno.setTelefono(rs.getString("telefono"));
                    eRetorno.setTipoDocumento(rs.getString("tipodocumento"));
                    eRetorno.setNumeroDocumento(rs.getString("numerodocumento"));
                    eRetorno.setCorreo(rs.getString("correo"));
                    eRetorno.setOrganizacionId(UtilUUID.convertirAUUID(rs.getString("organizacionid")));
                }
            }
        } catch (SQLException ex) {
            var mensajeTecnico = "Se presentó una SQLException al consultar un Encargado por ID.";
            var mensajeUsuario = "No se pudo obtener la información del Encargado solicitado.";
            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        } catch (Exception ex) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA al consultar un Encargado por ID.";
            var mensajeUsuario = "Ocurrió un error inesperado al consultar el Encargado.";
            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        }

        return eRetorno;
    }

    @Override
    public void modificar(UUID codigoencargado, EncargadoEntity entity) throws BackEndException {
        var sentenciaSQL = new StringBuilder();
        sentenciaSQL.append(
                "UPDATE doodb.encargado SET " +
                        " nombre = ?," +
                        " username = ?," +
                        " contrasena = ?," +
                        " prefijotelefono = ?," +
                        " telefono = ?," +
                        " tipodocumento = ?," +
                        " numerodocumento = ?," +
                        " correo = ?," +
                        " organizacionid = ? " +
                        "WHERE codigoencargado = ?"
        );

        try (var ps = connection.prepareStatement(sentenciaSQL.toString())) {
            ps.setString(1, entity.getNombre());
            ps.setString(2, entity.getUsername());
            String hashed = UtilEncrypt.hash(entity.getContrasena());
            ps.setString(3, hashed);
            ps.setString(4, entity.getPrefijoTelefono());
            ps.setString(5, entity.getTelefono());
            ps.setString(6, entity.getTipoDocumento());
            ps.setString(7, entity.getNumeroDocumento());
            ps.setString(8, entity.getCorreo());
            ps.setObject(9, entity.getOrganizacionId());
            ps.setObject(10, codigoencargado);

            ps.executeUpdate();
        } catch (SQLException ex) {
            var mensajeTecnico = "Se presentó una SQLException al modificar un Encargado en la base de datos.";
            var mensajeUsuario = "No se pudo actualizar la información del Encargado.";
            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        } catch (Exception ex) {
            var mensajeTecnico = "Se presentó una excepción NO CONTROLADA al modificar un Encargado.";
            var mensajeUsuario = "Ocurrió un error inesperado actualizando el Encargado.";
            throw DataBackEndException.reportar(mensajeUsuario, mensajeTecnico, ex);
        }
    }
}
