package co.edu.uco.backend.crosscutting.constants;

public enum DiaSemana {
    LUNES,
    MARTES,
    MIERCOLES,
    JUEVES,
    VIERNES,
    SABADO,
    DOMINGO;

    public static DiaSemana obtenerValorPorDefecto() {
        return LUNES;
    }
}

