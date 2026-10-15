package co.edu.uniquindio.Parcial2;

public record Cliente (String identificacion, String nombreCompleto, String telefono, String correo) {

    public String getIdentificacion() {
        return identificacion;
    }
}