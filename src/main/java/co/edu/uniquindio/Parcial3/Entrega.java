package co.edu.uniquindio.Parcial3;

import java.time.LocalDate;
import java.util.LinkedList;

public class Entrega {
    private String codigo;
    private LocalDate fecha;
    private EstadoEntrega estado;

    private Aportante theAportante;
    private LinkedList<Lote> listLotes;
    private Comprobante theComprobante;

    public Entrega(String codigo, LocalDate fecha, Aportante theAportante) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.theAportante = theAportante;
        estado = EstadoEntrega.ABIERTA;
        listLotes = new LinkedList<>();
        theComprobante = null;
    }


    public boolean agregarLote(Lote lote) {
        boolean agregado = false;

        if (estado == EstadoEntrega.ABIERTA && lote != null && lote.esValido()
                && buscarLote(lote.getCodigo()) == null) {
            listLotes.add(lote);
            agregado = true;
        }

        return agregado;
    }

    public boolean aceptar() {
        boolean aceptada = false;

        if (estado == EstadoEntrega.ABIERTA && !listLotes.isEmpty()) {
            estado = EstadoEntrega.ACEPTADA;
            theComprobante = new Comprobante(codigo, theAportante.identificacion(),
                    calcularPesoTotal(), calcularValorTotal());
            aceptada = true;
        }

        return aceptada;
    }

    public Lote buscarLote(String codigoLote) {
        Lote encontrado = null;

        for (Lote lote : listLotes) {
            if (lote.getCodigo().equals(codigoLote)) {
                encontrado = lote;
            }
        }

        return encontrado;
    }

    public int calcularPesoTotal() {
        int pesoTotal = 0;

        for (Lote lote : listLotes) {
            pesoTotal += lote.getPesoKg();
        }

        return pesoTotal;
    }

    public double calcularValorTotal() {
        double valorTotal = 0;

        for (Lote lote : listLotes) {
            valorTotal += lote.calcularValor();
        }

        return valorTotal;
    }

    public LinkedList<String> obtenerEtiquetas() {
        LinkedList<String> etiquetas = new LinkedList<>();

        for (Lote lote : listLotes) {
            if (lote instanceof IEtiquetable) {
                IEtiquetable loteEtiquetable = (IEtiquetable) lote;
                etiquetas.add(loteEtiquetable.generarEtiqueta());
            }
        }

        return etiquetas;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public EstadoEntrega getEstado() {
        return estado;
    }

    public Aportante getTheAportante() {
        return theAportante;
    }

    public LinkedList<Lote> getListLotes() {
        return listLotes;
    }

    public Comprobante getTheComprobante() {
        return theComprobante;
    }

    @Override
    public String toString() {
        return "Entrega: " + codigo + " - " + fecha + " - " + estado
                + " - Aportante: " + theAportante.identificacion()
                + " - Lotes: " + listLotes.size();
    }
}
