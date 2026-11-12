package co.edu.uniquindio.Parcial3;

public abstract class Lote {
    protected String codigo;
    protected String descripcion;
    protected int pesoKg;

    public Lote(String codigo, String descripcion, int pesoKg) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.pesoKg = pesoKg;
    }

    public abstract double calcularValor();

    public boolean esValido() {
        boolean valido = false;

        if (codigo != null && !codigo.isBlank() && pesoKg > 0) {
            valido = true;
        }

        return valido;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPesoKg() {
        return pesoKg;
    }

    @Override
    public String toString() {
        return "Lote: " + codigo + " - " + descripcion + " - " + pesoKg + " kg";
    }
}