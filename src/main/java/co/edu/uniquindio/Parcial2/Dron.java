package co.edu.uniquindio.Parcial2;

public class Dron extends EquipoAudiovisual {
    private int autonomiaVuelo;
    private boolean tieneBateriaAdicional;

    public Dron(String codigo, String marca, String modelo,
                int autonomiaVuelo, boolean tieneBateriaAdicional) {
        super(codigo, marca, modelo);
        this.autonomiaVuelo = autonomiaVuelo;
        this.tieneBateriaAdicional = tieneBateriaAdicional;
    }

    @Override
    public double calcularCostoTotal(int horas) {
        final double TARIFA_HORA = 18000;
        final double ADICIONAL_BATERIA = 20000;


        double costo = TARIFA_HORA * horas;

        if (tieneBateriaAdicional) {
            costo += ADICIONAL_BATERIA;
        }

        return costo;
    }
}
