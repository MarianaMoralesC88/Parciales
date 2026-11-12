package co.edu.uniquindio.Parcial3;

public record Comprobante(String codigoEntrega, String identificacionAportante,
                          int pesoTotal, double valorTotal) {

    @Override
    public String toString() {
        return "Comprobante: " + codigoEntrega + " - Aportante: " + identificacionAportante
                + " - Peso total: " + pesoTotal + " kg - Valor total: $" + valorTotal;
    }
}