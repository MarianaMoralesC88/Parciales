package co.edu.uniquindio.Parcial2;

public class KitIluminacion extends EquipoAudiovisual {
    private int cantidadLuces;
    private boolean tieneControlInalambrico;

    public KitIluminacion(String codigo, String marca, String modelo,
                          int cantidadLuces, boolean tieneControlInalambrico) {
        super(codigo, marca, modelo);
        this.cantidadLuces = cantidadLuces;
        this.tieneControlInalambrico = tieneControlInalambrico;
    }

    @Override
    public double calcularCostoTotal(int horas) {
        final double TARIFA_HORA = 10000;
        final double ADICIONAL_CONTROL = 12000;


        double costo = TARIFA_HORA * horas;

        if (tieneControlInalambrico) {
            costo += ADICIONAL_CONTROL;
        }

        return costo;
    }
}