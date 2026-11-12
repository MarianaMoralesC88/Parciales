package co.edu.uniquindio.Parcial3;

public class PapelCarton extends Lote {
    private boolean clasificado;

    public PapelCarton(String codigo, String descripcion, int pesoKg, boolean clasificado) {
        super(codigo, descripcion, pesoKg);
        this.clasificado = clasificado;
    }

    @Override
    public double calcularValor() {
        double valor = pesoKg * 500;

        if (clasificado) {
            valor += pesoKg * 100;
        }

        return valor;
    }

    public boolean isClasificado() {
        return clasificado;
    }

    @Override
    public String toString() {
        return "Papel y carton - " + super.toString() + " - Clasificado: " + clasificado;
    }
}
