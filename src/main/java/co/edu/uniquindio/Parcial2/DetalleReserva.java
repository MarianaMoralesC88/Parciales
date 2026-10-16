package co.edu.uniquindio.Parcial2;

public class DetalleReserva {
    private int cantidadHoras;

    private EquipoAudiovisual theEquipo;

    public DetalleReserva(EquipoAudiovisual equipo, int cantidadHoras) {
        this.theEquipo = equipo;
        this.cantidadHoras = cantidadHoras;
    }


    public double calcularSubTotal() {
        return theEquipo.calcularCostoTotal(cantidadHoras);
    }

    public EquipoAudiovisual getEquipo() {
        return theEquipo;
    }

    public int getCantidadHoras() {
        return cantidadHoras;
    }
}
