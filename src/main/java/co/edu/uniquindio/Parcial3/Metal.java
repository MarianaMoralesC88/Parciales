package co.edu.uniquindio.Parcial3;

public class Metal extends Lote implements IEtiquetable {
    private TipoMetal tipoMetal;

    public Metal(String codigo, String descripcion, int pesoKg, TipoMetal tipoMetal) {
        super(codigo, descripcion, pesoKg);
        this.tipoMetal = tipoMetal;
    }

    @Override
    public double calcularValor() {
        double valor = 0;

        if (tipoMetal == TipoMetal.ALUMINIO) {
            valor = pesoKg * 2200;
        } else if (tipoMetal == TipoMetal.ACERO) {
            valor = pesoKg * 900;
        }

        return valor;
    }

    @Override
    public boolean esValido() {
        return super.esValido() && tipoMetal != null;
    }

    @Override
    public String generarEtiqueta() {
        return "Etiqueta - Lote: " + codigo + " - Metal: " + tipoMetal;
    }

    public TipoMetal getTipoMetal() {
        return tipoMetal;
    }

    @Override
    public String toString() {
        return "Metal - " + super.toString() + " - Tipo: " + tipoMetal;
    }
}
