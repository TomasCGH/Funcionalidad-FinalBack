package co.edu.uco.backend.businesslogic.businesslogic.impl;

import co.edu.uco.backend.businesslogic.assembler.encargado.entity.EncargadoEntityAssembler;
import co.edu.uco.backend.businesslogic.businesslogic.EncargadoBusinessLogic;
import co.edu.uco.backend.businesslogic.businesslogic.domain.EncargadoDomain;
import co.edu.uco.backend.businesslogic.businesslogic.domain.UsuarioDomain;
import co.edu.uco.backend.crosscutting.exceptions.BackEndException;
import co.edu.uco.backend.crosscutting.exceptions.BusinessLogicBackEndException;
import co.edu.uco.backend.crosscutting.utilitarios.UtilTexto;
import co.edu.uco.backend.crosscutting.utilitarios.UtilUUID;
import co.edu.uco.backend.data.dao.factory.DAOFactory;
import co.edu.uco.backend.entity.EncargadoEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementación de la lógica de negocio para Encargado.
 * Sigue la misma estructura y convenciones de ClienteBusinessLogicImpl,
 * pero adaptada a los campos y políticas específicas de Encargado.
 */
public class EncargadoBusinessLogicImpl implements EncargadoBusinessLogic {

    // UUID fijos para cada organización. Ajustar estos valores según los UUID reales en base de datos:
    private static final UUID IMMER_ID  = UUID.fromString("11111111-1111-1111-1111-111111111111");
    private static final UUID INDER_ID  = UUID.fromString("22222222-2222-2222-2222-222222222222");
    private static final UUID OLIMPO_ID = UUID.fromString("33333333-3333-3333-3333-333333333333");

    private static final String ORG_IMMER  = "IMMER";
    private static final String ORG_INDER  = "INDER";
    private static final String ORG_OLIMPO = "OLIMPO";

    private final DAOFactory factory;

    public EncargadoBusinessLogicImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registrarNuevoEncargado(EncargadoDomain encargado) throws BackEndException {
        // 1. Validar integridad de datos básicos (nombre, username, contraseña, prefijo, teléfono, tipo/numero documento)
        validarIntegridadInformacion(encargado);

        // 2. Validar unicidad de teléfono
        validarNoExistaEncargadoConMismoTelefono(encargado.getTelefono());

        // 3. Validar unicidad de username
        validarNoExistaEncargadoConMismoUsername(encargado.getUsername());

        // 4. Validar unicidad de combinación tipoDocumento + numeroDocumento
        validarNoExistaEncargadoConMismoTipoYNumeroDocumento(
                encargado.getTipoDocumento(),
                encargado.getNumeroDocumento()
        );

        // 5. Validar etiqueta de organización y traducirla a UUID
        String orgEtiqueta = encargado.getOrganizacion().trim().toUpperCase();
        UUID orgId = traducirEtiquetaAUUID(orgEtiqueta);
        if (UtilUUID.esValorDefecto(orgId)) {
            String mensajeUsuario  = "Etiqueta de organización inválida. Solo se permiten \"IMMER\", \"INDER\" u \"OLIMPO\".";
            String mensajeTecnico  = "Etiqueta recibida: \"" + encargado.getOrganizacion() + "\"";
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico);
        }

        // 6. Generar nuevo UUID único para el Encargado
        UUID nuevoId = generarIdentificadorNuevoEncargado();

        // 7. Generar correo automático: IMMER + username.toLowerCase() + "@immer.co", etc.
        String prefijoUpper = orgEtiqueta;
        String correoGenerado = prefijoUpper
                + encargado.getUsername().trim().toLowerCase()
                + "@"
                + prefijoUpper.toLowerCase()
                + ".co";

        // 8. Crear un nuevo dominio con todos los datos definitivos (incluyendo etiqueta y UUID de organizacion)
        EncargadoDomain toCreate = new EncargadoDomain(
                nuevoId,
                encargado.getNombre().trim(),
                encargado.getUsername().trim().toLowerCase(),
                encargado.getContrasena(),
                encargado.getPrefijoTelefono().trim(),
                encargado.getTelefono().trim(),
                encargado.getTipoDocumento().trim(),
                encargado.getNumeroDocumento().trim(),
                correoGenerado,
                orgId,
                orgEtiqueta
        );

        // 9. Convertir a entidad y persistir en DAO
        EncargadoEntity entity = EncargadoEntityAssembler.getInstance().toEntity(toCreate);
        factory.getEncargadoDAO().crear(entity);
    }

    @Override
    public void modificarEncargadoExistente(UUID encargadoId, EncargadoDomain datosActualizados) throws BackEndException {
        // 1. Verificar que el Encargado exista (si no, arrojar excepción)
        EncargadoDomain existente = cargarEncargadoExistente(encargadoId);

        // 2. Mezclar campos: si en datosActualizados algún campo está vacío/nulo, conservar el original
        EncargadoDomain merged = mezclarCamposEncargado(existente, datosActualizados);

        // 3. Validar integridad del objeto fusionado
        validarIntegridadInformacion(merged);

        // 4. Validar unicidad de teléfono al actualizar (excluyendo al propio ID)
        validarNoExistaEncargadoConMismoTelefonoActualizar(encargadoId, merged.getTelefono());

        // 5. Validar unicidad de username al actualizar (excluyendo al propio ID)
        validarNoExistaEncargadoConMismoUsernameActualizar(encargadoId, merged.getUsername());

        // 6. Validar combinación tipoDocumento + numeroDocumento al actualizar (excluyendo al propio ID)
        validarNoExistaEncargadoConMismoTipoYNumeroDocumentoActualizar(
                encargadoId,
                merged.getTipoDocumento(),
                merged.getNumeroDocumento()
        );

        // 7. Verificar que la etiqueta de organización no cambie
        if (!existente.getOrganizacion().equals(merged.getOrganizacion())) {
            String mensajeUsuario  = "No está permitido cambiar de organización una vez asignado.";
            String mensajeTecnico  = "Intento de modificar organizacion de \"" +
                    existente.getOrganizacion() + "\" a \"" + merged.getOrganizacion() + "\"";
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico);
        }

        // 8. El correo no se modifica: conservar el original
        merged.setCorreo(existente.getCorreo());

        // 9. Convertir a entidad y llamar al DAO para actualizar
        EncargadoEntity entity = EncargadoEntityAssembler.getInstance().toEntity(merged);
        factory.getEncargadoDAO().modificar(encargadoId, entity);
    }

    @Override
    public void darBajaDefinitivamenteEncargadoExistente(UUID encargadoId) throws BackEndException {
        // 1. Verificar existencia
        cargarEncargadoExistente(encargadoId);
        // 2. Llamar al DAO para eliminar
        factory.getEncargadoDAO().eliminar(encargadoId);
    }

    @Override
    public EncargadoDomain consultarEncargadoPorId(UUID encargadoId) throws BackEndException {
        EncargadoEntity entity = factory.getEncargadoDAO().consultarPorId(encargadoId);
        if (UtilUUID.esValorDefecto(entity.getId())) {
            String mensajeUsuario = "Encargado no encontrado con id: " + encargadoId;
            String mensajeTecnico = "DAO devolvió entidad con ID por defecto al buscar " + encargadoId;
            throw BusinessLogicBackEndException.reportar(mensajeUsuario, mensajeTecnico);
        }
        return EncargadoEntityAssembler.getInstance().toDomain(entity);
    }

    @Override
    public List<EncargadoDomain> consultarEncargados(EncargadoDomain filtro) throws BackEndException {
        // 1. Convertir filtro domain a entidad
        EncargadoEntity filtroEntity = EncargadoEntityAssembler.getInstance().toEntity(filtro);
        // 2. Consultar DAO
        List<EncargadoEntity> entities = factory.getEncargadoDAO().consultar(filtroEntity);
        // 3. Mapear lista de entidades a dominios
        return entities.stream()
                .map(EncargadoEntityAssembler.getInstance()::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDomain iniciarSesion(String username, String rawPassword, String ipAddress, String userAgent) {
        // No implementado para el módulo Encargado
        return null;
    }

    @Override
    public void cerrarSesion(UUID usuarioId) {
        // No implementado para el módulo Encargado
    }

    @Override
    public void recuperarContrasena(String username) {
        // No implementado para el módulo Encargado
    }

    @Override
    public void cambiarContrasena(UUID usuarioId, String rawPasswordActual, String rawPasswordNueva) {
        // No implementado para el módulo Encargado
    }

    @Override
    public UsuarioDomain consultarUsuarioPorId(UUID usuarioId) {
        // No aplica para Encargado en este contexto
        return null;
    }

    @Override
    public List<UsuarioDomain> listarUsuarios(UsuarioDomain filtro) {
        // No aplica para Encargado; devolver lista vacía
        return List.of();
    }

    // ———————————————————————————————————————————
    // Métodos privados auxiliares
    // ———————————————————————————————————————————

    /**
     * Valida que los campos obligatorios y formatos básicos de EncargadoDomain sean correctos.
     * Lanza BusinessLogicBackEndException con mensajes adecuados si falla alguna regla.
     */
    private void validarIntegridadInformacion(EncargadoDomain e) throws BackEndException {
        validarIntegridadNombre(e.getNombre());
        validarIntegridadUsername(e.getUsername());
        validarIntegridadContrasena(e.getContrasena());
        validarIntegridadTipoDocumento(e.getTipoDocumento());
        validarIntegridadNumeroDocumento(e.getTipoDocumento(), e.getNumeroDocumento());
        validarIntegridadPrefijo(e.getPrefijoTelefono());
        validarIntegridadTelefono(e.getTelefono());

    }

    private void validarIntegridadNombre(String nombre) throws BackEndException {
        if (UtilTexto.getInstance().estaVacia(nombre)) {
            throw BusinessLogicBackEndException.reportar(
                    "Error de obligatoriedad: el nombre del Encargado es obligatorio.",
                    "nombre nulo o vacío en EncargadoDomain"
            );
        }
        String val = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(nombre);
        if (val.length() < 2 || val.length() > 50) {
            throw BusinessLogicBackEndException.reportar(
                    "Longitud inválida para el nombre. Debe tener entre 2 y 50 caracteres.",
                    "nombre '" + val + "' con longitud " + val.length()
            );
        }
        if (!UtilTexto.getInstance().contieneSoloLetrasEspacios(nombre)) {
            throw BusinessLogicBackEndException.reportar(
                    "Formato inválido para el nombre. Solo se permiten letras y espacios.",
                    "nombre '" + nombre + "' contiene caracteres inválidos"
            );
        }
    }

    private void validarIntegridadUsername(String username) throws BackEndException {
        // 1. Obligatoriedad
        if (UtilTexto.getInstance().estaVacia(username)) {
            throw BusinessLogicBackEndException.reportar(
                    "Error de obligatoriedad: el username es obligatorio.",
                    "username nulo o vacío en EncargadoDomain"
            );
        }

        // 2. Quitamos espacios al inicio/fin para medir longitud
        String val = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(username);

        // 3. Longitud: entre 5 y 30 caracteres
        if (val.length() < 5 || val.length() > 30) {
            throw BusinessLogicBackEndException.reportar(
                    "Longitud inválida para el username. Debe tener entre 5 y 30 caracteres.",
                    "username '" + val + "' con longitud " + val.length()
            );
        }

        // 4. Formato:
        //    - No puede contener '@' ni espacios en ninguna posición
        //    - Sólo letras, números, punto (.) o guión bajo (_) en medio
        //    - No puede empezar o terminar con '.' ni '_'
        //    - No se permiten dos puntos consecutivos ni dos guiones bajos consecutivos
        String patron = "^[A-Za-z0-9](?:[A-Za-z0-9]|[._](?=[A-Za-z0-9]))*[A-Za-z0-9]$";
        if (!val.matches(patron)) {
            throw BusinessLogicBackEndException.reportar(
                    "Formato inválido para el username. Debe iniciar y terminar con letra o número, "
                            + "puede contener ‘.’ o ‘_’ en medio, pero sin espacios, sin ‘@’ y sin puntos/guiones bajos consecutivos.",
                    "username '" + val + "' no cumple el patrón"
            );
        }
    }



    private void validarIntegridadContrasena(String contrasena) throws BackEndException {
        // 1. Sólo consideramos "obligatoria" si llega null o "" (longitud 0).
        //    NO usamos trim() ni UtilTexto.estaVacia() aquí, para que "   " (sólo espacios)
        //    NO se considere vacío en esta primera fase.
        if (contrasena == null || contrasena.isEmpty()) {
            throw BusinessLogicBackEndException.reportar(
                    "Error de obligatoriedad: la contraseña es obligatoria.",
                    "contrasena es null o vacía en EncargadoDomain"
            );
        }

        // 2. Ahora validamos la longitud TOTAL (incluyendo espacios).
        //    Como queremos que los espacios cuenten como caracteres, NO hacemos trim().
        int length = contrasena.length();
        if (length < 8 || length > 24) {
            throw BusinessLogicBackEndException.reportar(
                    "Longitud inválida para la contraseña. Debe tener entre 8 y 24 caracteres (los espacios cuentan).",
                    "contrasena con longitud " + length
            );
        }

        // 3. Contamos cuántos dígitos y cuántas letras hay dentro.
        //    Los espacios (u otros símbolos) ocupan “sitio” en el total de caracteres,
        //    pero no suman ni como dígitos ni como letras.
        int digitCount = 0;
        int letterCount = 0;
        for (char c : contrasena.toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
            } else if (Character.isLetter(c)) {
                letterCount++;
            }
            // Si es espacio u otro carácter, no incrementamos nada.
        }
        if (digitCount < 4 || letterCount < 3) {
            throw BusinessLogicBackEndException.reportar(
                    "La contraseña debe contener al menos 4 dígitos y 3 letras (los espacios y símbolos no cuentan como dígitos o letras).",
                    "contrasena no cumple mínimos de dígitos (" + digitCount + ") o letras (" + letterCount + ")"
            );
        }

        // 4. Ya no hace falta más chequear. Si llega hasta aquí, la contraseña es “válida”.
    }



    private void validarIntegridadPrefijo(String prefijo) throws BackEndException {
        // 1. Obligatoriedad
        if (UtilTexto.getInstance().estaVacia(prefijo)) {
            throw BusinessLogicBackEndException.reportar(
                    "Error de obligatoriedad: el prefijo telefónico es obligatorio.",
                    "prefijo nulo o vacío en EncargadoDomain"
            );
        }

        // 2. Quitamos espacios en blanco al inicio/fin
        String val = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(prefijo);

        // 3. Verificar que sea exactamente "+57"
        if (!val.equals("+57")) {
            throw BusinessLogicBackEndException.reportar(
                    "Prefijo inválido: sólo se permite \"+57\" como código de país.(sin espacios)",
                    "prefijo '" + val + "' no coincide con +57"
            );
        }
    }


    private void validarIntegridadTelefono(String telefono) throws BackEndException {
        if (UtilTexto.getInstance().estaVacia(telefono)) {
            throw BusinessLogicBackEndException.reportar(
                    "Error de obligatoriedad: el teléfono es obligatorio.",
                    "telefono nulo o vacío en EncargadoDomain"
            );
        }
        String val = UtilTexto.getInstance().quitarEspaciosEnBlancoInicioFin(telefono);
        if (val.length() != 10 || !val.startsWith("3")) {
            throw BusinessLogicBackEndException.reportar(
                    "Formato inválido para el teléfono. Debe tener 10 dígitos y comenzar con '3'.(sin espacios)",
                    "telefono '" + val + "' no cumple formato colombiano"
            );
        }
        if (!UtilTexto.getInstance().contieneSoloNumeros(val)) {
            throw BusinessLogicBackEndException.reportar(
                    "Formato inválido para el teléfono. Solo se permiten dígitos.",
                    "telefono '" + val + "' contiene caracteres no numéricos"
            );
        }
    }

    private void validarIntegridadTipoDocumento(String tipo) throws BackEndException {
        if (UtilTexto.getInstance().estaVacia(tipo)) {
            throw BusinessLogicBackEndException.reportar(
                    "Error de obligatoriedad: el tipo de documento es obligatorio.",
                    "tipoDocumento nulo o vacío en EncargadoDomain"
            );
        }
        if (!("CC".equals(tipo) || "CE".equals(tipo))) {
            throw BusinessLogicBackEndException.reportar(
                    "Tipo de documento inválido. Solo se permiten 'CC' o 'CE'.",
                    "tipoDocumento '" + tipo + "' no es ni 'CC' ni 'CE'"
            );
        }
    }

    private void validarIntegridadNumeroDocumento(String tipo, String numero) throws BackEndException {
        if (UtilTexto.getInstance().estaVacia(numero)) {
            throw BusinessLogicBackEndException.reportar(
                    "Error de obligatoriedad: el número de documento es obligatorio.",
                    "numeroDocumento nulo o vacío en EncargadoDomain"
            );
        }
        if (!UtilTexto.getInstance().contieneSoloNumeros(numero) || numero.startsWith("0")) {
            throw BusinessLogicBackEndException.reportar(
                    "Formato inválido para el número de documento. Solo dígitos y no puede iniciar en 0.",
                    "numeroDocumento '" + numero + "' no cumple formato"
            );
        }
        int len = numero.length();
        if ("CC".equals(tipo)) {
            if (len < 6 || len > 10) {
                throw BusinessLogicBackEndException.reportar(
                        "La cédula de ciudadanía debe tener entre 6 y 10 dígitos.",
                        "numeroDocumento '" + numero + "' longitud inválida para CD"
                );
            }
        } else { // "CE"
            if (len < 6 || len > 8) {
                throw BusinessLogicBackEndException.reportar(
                        "La cédula de extranjería debe tener entre 6 y 8 dígitos.",
                        "numeroDocumento '" + numero + "' longitud inválida para CE"
                );
            }
        }
    }

    private void validarNoExistaEncargadoConMismoTelefono(String telefono) throws BackEndException {
        EncargadoEntity filtro = new EncargadoEntity();
        filtro.setTelefono(telefono.trim());
        if (!factory.getEncargadoDAO().consultar(filtro).isEmpty()) {
            throw BusinessLogicBackEndException.reportar(
                    "Ya existe un Encargado con ese teléfono. Por favor ingrese un teléfono diferente.",
                    "Teléfono '" + telefono + "' ya registrado"
            );
        }
    }

    private void validarNoExistaEncargadoConMismoUsername(String username) throws BackEndException {
        EncargadoEntity filtro = new EncargadoEntity();
        filtro.setUsername(username.trim().toLowerCase());
        if (!factory.getEncargadoDAO().consultar(filtro).isEmpty()) {
            throw BusinessLogicBackEndException.reportar(
                    "Ya existe un Encargado con ese username. Por favor ingrese un username diferente.",
                    "Username '" + username + "' ya registrado"
            );
        }
    }

    private void validarNoExistaEncargadoConMismoTipoYNumeroDocumento(String tipo, String numero) throws BackEndException {
        EncargadoEntity filtro = new EncargadoEntity();
        filtro.setTipoDocumento(tipo.trim());
        filtro.setNumeroDocumento(numero.trim());
        if (!factory.getEncargadoDAO().consultar(filtro).isEmpty()) {
            throw BusinessLogicBackEndException.reportar(
                    "Ya existe un Encargado con ese tipo y número de documento.",
                    "Combinación tipoDocumento='" + tipo + "', numeroDocumento='" + numero + "' ya registrada"
            );
        }
    }

    private void validarNoExistaEncargadoConMismoTelefonoActualizar(UUID idActual, String telefono) throws BackEndException {
        EncargadoEntity filtro = new EncargadoEntity();
        filtro.setTelefono(telefono.trim());
        for (EncargadoEntity e : factory.getEncargadoDAO().consultar(filtro)) {
            if (!e.getId().equals(idActual)) {
                throw BusinessLogicBackEndException.reportar(
                        "Ya existe un Encargado con ese teléfono. Por favor ingrese un teléfono diferente.",
                        "Teléfono '" + telefono + "' ya registrado en otro ID"
                );
            }
        }
    }

    private void validarNoExistaEncargadoConMismoUsernameActualizar(UUID idActual, String username) throws BackEndException {
        EncargadoEntity filtro = new EncargadoEntity();
        filtro.setUsername(username.trim());
        for (EncargadoEntity e : factory.getEncargadoDAO().consultar(filtro)) {
            if (!e.getId().equals(idActual)) {
                throw BusinessLogicBackEndException.reportar(
                        "Ya existe un Encargado con ese username. Por favor ingrese un username diferente.",
                        "Username '" + username + "' ya registrado en otro ID"
                );
            }
        }
    }

    private void validarNoExistaEncargadoConMismoTipoYNumeroDocumentoActualizar(
            UUID idActual, String tipo, String numero) throws BackEndException {
        EncargadoEntity filtro = new EncargadoEntity();
        filtro.setTipoDocumento(tipo.trim());
        filtro.setNumeroDocumento(numero.trim());
        for (EncargadoEntity e : factory.getEncargadoDAO().consultar(filtro)) {
            if (!e.getId().equals(idActual)) {
                throw BusinessLogicBackEndException.reportar(
                        "Ya existe un Encargado con ese tipo y número de documento.",
                        "Combinación tipoDocumento='" + tipo + "', numeroDocumento='" + numero +
                                "' ya registrada en otro ID"
                );
            }
        }
    }

    private UUID generarIdentificadorNuevoEncargado() throws BackEndException {
        UUID nuevoId;
        do {
            nuevoId = UtilUUID.generarNuevoUUID();
        } while (!UtilUUID.esValorDefecto(factory.getEncargadoDAO().consultarPorId(nuevoId).getId()));
        return nuevoId;
    }

    private EncargadoDomain cargarEncargadoExistente(UUID encargadoId) throws BackEndException {
        EncargadoEntity entity = factory.getEncargadoDAO().consultarPorId(encargadoId);
        if (UtilUUID.esValorDefecto(entity.getId())) {
            throw BusinessLogicBackEndException.reportar(
                    "No existe Encargado con id: " + encargadoId,
                    "DAO devolvió entidad con ID por defecto al buscar " + encargadoId
            );
        }
        return EncargadoEntityAssembler.getInstance().toDomain(entity);
    }

    private UUID traducirEtiquetaAUUID(String etiqueta) {
        switch (etiqueta) {
            case ORG_IMMER:
                return IMMER_ID;
            case ORG_INDER:
                return INDER_ID;
            case ORG_OLIMPO:
                return OLIMPO_ID;
            default:
                return UtilUUID.obtenerValorDefecto();
        }
    }

    private EncargadoDomain mezclarCamposEncargado(EncargadoDomain original, EncargadoDomain actualizaciones) {
        String nombre   = UtilTexto.getInstance().estaVacia(actualizaciones.getNombre())
                ? original.getNombre()
                : actualizaciones.getNombre().trim();
        String username = UtilTexto.getInstance().estaVacia(actualizaciones.getUsername())
                ? original.getUsername()
                : actualizaciones.getUsername().trim().toLowerCase();
        String contra   = UtilTexto.getInstance().estaVacia(actualizaciones.getContrasena())
                ? original.getContrasena()
                : actualizaciones.getContrasena();
        String prefijo  = UtilTexto.getInstance().estaVacia(actualizaciones.getPrefijoTelefono())
                ? original.getPrefijoTelefono()
                : actualizaciones.getPrefijoTelefono().trim();
        String telefono = UtilTexto.getInstance().estaVacia(actualizaciones.getTelefono())
                ? original.getTelefono()
                : actualizaciones.getTelefono().trim();
        String tipo     = UtilTexto.getInstance().estaVacia(actualizaciones.getTipoDocumento())
                ? original.getTipoDocumento()
                : actualizaciones.getTipoDocumento().trim();
        String numero   = UtilTexto.getInstance().estaVacia(actualizaciones.getNumeroDocumento())
                ? original.getNumeroDocumento()
                : actualizaciones.getNumeroDocumento().trim();
        String etiqueta = UtilTexto.getInstance().estaVacia(actualizaciones.getOrganizacion())
                ? original.getOrganizacion()
                : actualizaciones.getOrganizacion().trim().toUpperCase();
        // Traducción de la etiqueta a UUID de organización:
        UUID orgId = traducirEtiquetaAUUID(etiqueta);

        // El correo original nunca cambia
        String correoOriginal = original.getCorreo();

        return new EncargadoDomain(
                original.getId(),
                nombre,
                username,
                contra,
                prefijo,
                telefono,
                tipo,
                numero,
                correoOriginal,
                original.getOrganizacionId(),
                etiqueta
        );
    }
}
