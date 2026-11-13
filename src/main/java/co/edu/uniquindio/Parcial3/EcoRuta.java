package co.edu.uniquindio.Parcial3;

import java.time.LocalDate;
import java.util.LinkedList;

public class EcoRuta {
    private String nombre;

    private LinkedList<Aportante> listAportantes;
    private LinkedList<Entrega> listEntregas;

    public EcoRuta(String nombre) {
        this.nombre = nombre;
        listAportantes = new LinkedList<>();
        listEntregas = new LinkedList<>();
    }


    public boolean registrarAportante(Aportante aportante) {
        boolean registrado = false;

        if (aportante != null && buscarAportante(aportante.identificacion()) == null) {
            listAportantes.add(aportante);
            registrado = true;
        }

        return registrado;
    }


    public boolean registrarEntrega(String codigo, LocalDate fecha, String identificacionAportante) {
        boolean registrada = false;
        Aportante aportante = buscarAportante(identificacionAportante);

        if (codigo != null && !codigo.isBlank() && aportante != null
                && buscarEntrega(codigo) == null) {
            Entrega nuevaEntrega = new Entrega(codigo, fecha, aportante);
            listEntregas.add(nuevaEntrega);
            registrada = true;
        }

        return registrada;
    }

    public boolean agregarLoteToEntrega(String codigoEntrega, Lote lote) {
        boolean agregado = false;
        Entrega entrega = buscarEntrega(codigoEntrega);

        if (entrega != null) {
            agregado = entrega.agregarLote(lote);
        }

        return agregado;
    }

    public boolean aceptarEntrega(String codigoEntrega) {
        boolean aceptada = false;
        Entrega entrega = buscarEntrega(codigoEntrega);

        if (entrega != null) {
            aceptada = entrega.aceptar();
        }

        return aceptada;
    }


    public Comprobante consultarComprobante(String codigoEntrega) {
        Comprobante comprobante = null;
        Entrega entrega = buscarEntrega(codigoEntrega);

        if (entrega != null) {
            comprobante = entrega.getTheComprobante();
        }

        return comprobante;
    }

    public LinkedList<String> consultarEtiquetas(String codigoEntrega) {
        LinkedList<String> etiquetas = new LinkedList<>();
        Entrega entrega = buscarEntrega(codigoEntrega);

        if (entrega != null) {
            etiquetas = entrega.obtenerEtiquetas();
        }

        return etiquetas;
    }

    public double calcularValorAcumuladoAportante(String identificacionAportante) {
        double valorAcumulado = 0;

        for (Entrega entrega : listEntregas) {
            Comprobante comprobante = entrega.getTheComprobante();

            if (comprobante != null
                    && comprobante.identificacionAportante().equals(identificacionAportante)) {
                valorAcumulado += comprobante.valorTotal();
            }
        }

        return valorAcumulado;
    }

    public Aportante buscarAportante(String identificacion) {
        Aportante encontrado = null;

        for (Aportante aportante : listAportantes) {
            if (aportante.identificacion().equals(identificacion)) {
                encontrado = aportante;
            }
        }

        return encontrado;
    }

    public Entrega buscarEntrega(String codigo) {
        Entrega encontrada = null;

        for (Entrega entrega : listEntregas) {
            if (entrega.getCodigo().equals(codigo)) {
                encontrada = entrega;
            }
        }

        return encontrada;
    }


    public String getNombre() {
        return nombre;
    }

    public LinkedList<Aportante> getListAportantes() {
        return listAportantes;
    }

    public LinkedList<Entrega> getListEntregas() {
        return listEntregas;
    }
}
