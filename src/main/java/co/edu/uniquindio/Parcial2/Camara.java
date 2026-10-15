package co.edu.uniquindio.Parcial2;

public class Camara extends EquipoAudiovisual {
    private String resolucionMaxima;
    private boolean tieneEstabilizador;

    public Camara(String codigo, String marca, String modelo,
                  String resolucionMaxima, boolean tieneEstabilizador) {
        super(codigo, marca, modelo);
        this.resolucionMaxima = resolucionMaxima;
        this.tieneEstabilizador = tieneEstabilizador;
    }

    @Override
    public double calcularCostoTotal(int horas) {
        final double TARIFA_HORA = 12000;
        final double ADICIONAL_ESTABILIZADOR = 15000;


        double costo = TARIFA_HORA * horas;

        if (tieneEstabilizador) {
            costo += ADICIONAL_ESTABILIZADOR;
        }

        return costo;
    }
}
