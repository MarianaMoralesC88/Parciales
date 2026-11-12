package co.edu.uniquindio.Parcial3;

public class ResiduoElectronico extends Lote implements IEtiquetable {
    private int equiposCompletos;

    public ResiduoElectronico(String codigo, String descripcion, int pesoKg, int equiposCompletos) {
        super(codigo, descripcion, pesoKg);
        this.equiposCompletos = equiposCompletos;
    }

    @Override
    public double calcularValor() {
        double valor = pesoKg * 1400 + equiposCompletos * 800;

        return valor;
    }

    @Override
    public boolean esValido() {
        return super.esValido() && equiposCompletos >= 0;
    }

    @Override
    public String generarEtiqueta() {
        return "Etiqueta - Lote: " + codigo + " - Equipos completos: " + equiposCompletos;
    }

    public int getEquiposCompletos() {
        return equiposCompletos;
    }

    @Override
    public String toString() {
        return "Residuo electronico - " + super.toString() + " - Equipos completos: " + equiposCompletos;
    }
}
