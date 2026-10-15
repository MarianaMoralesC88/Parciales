package co.edu.uniquindio.Parcial2;

public class Operador {
    private String identificacion;
    private String nombreCompleto;
    private String certificacion;
    private int aniosExperiencia;

    public Operador(String identificacion, String nombreCompleto,
                    String certificacion, int aniosExperiencia) {
        this.identificacion = identificacion;
        this.nombreCompleto = nombreCompleto;
        this.certificacion = certificacion;
        this.aniosExperiencia = aniosExperiencia;
    }

    public String getIdentificacion() {
        return identificacion;
    }
}

