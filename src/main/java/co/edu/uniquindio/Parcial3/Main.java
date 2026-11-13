package co.edu.uniquindio.Parcial3;

import javax.swing.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class Main {
    private static EcoRuta ecoRuta;

    public static void main(String[] args) {

        ecoRuta = new EcoRuta("EcoRuta");
        inicializarDatos();

        int option = 0;
        do {
            option = Integer.parseInt(JOptionPane.showInputDialog("Este es el menu de EcoRuta:" +
                    "\n Seleccione una opcion:" +
                    "\n 1. Registrar aportante" +
                    "\n 2. Mostrar aportantes" +
                    "\n 3. Registrar entrega" +
                    "\n 4. Agregar lote a una entrega" +
                    "\n 5. Aceptar entrega" +
                    "\n 6. Mostrar entrega" +
                    "\n 7. Consultar comprobante" +
                    "\n 8. Consultar etiquetas" +
                    "\n 9. Consultar valor acumulado de un aportante" +
                    "\n 0. Salir del sistema"));

            switch (option) {

                case 1:
                    solicitarInformacionAportante();
                    break;

                case 2:
                    mostrarAportantes();
                    break;

                case 3:
                    solicitarInformacionEntrega();
                    break;

                case 4:
                    solicitarInformacionLote();
                    break;

                case 5:
                    solicitarAceptarEntrega();
                    break;

                case 6:
                    mostrarEntrega();
                    break;

                case 7:
                    mostrarComprobante();
                    break;

                case 8:
                    mostrarEtiquetas();
                    break;

                case 9:
                    mostrarValorAcumulado();
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "El programa finalizo");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "La opcion no es valida");
                    break;
            }

        } while (option != 0);

    }

    private static void inicializarDatos() {
        ecoRuta.registrarAportante(new Aportante("1094", "Laura Gomez", "laura@correo.com"));
        ecoRuta.registrarAportante(new Aportante("7550", "Tienda La Esquina", "tienda@correo.com"));
    }

    private static void solicitarInformacionAportante() {
        String identificacion = JOptionPane.showInputDialog("Ingrese la identificacion del aportante:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del aportante:");
        String correo = JOptionPane.showInputDialog("Ingrese el correo del aportante:");

        boolean resultado = ecoRuta.registrarAportante(new Aportante(identificacion, nombre, correo));

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Registro exitoso");
        } else {
            JOptionPane.showMessageDialog(null, "No se hizo el registro, la identificacion ya existe");
        }
    }

    private static void mostrarAportantes() {
        String mensaje = "Lista de aportantes:\n";

        for (Aportante aportante : ecoRuta.getListAportantes()) {
            mensaje += "\n" + aportante;
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }

    private static void solicitarInformacionEntrega() {
        String codigo = JOptionPane.showInputDialog("Ingrese el codigo de la entrega:");
        String identificacion = JOptionPane.showInputDialog("Ingrese la identificacion del aportante:");

        boolean resultado = ecoRuta.registrarEntrega(codigo, LocalDate.now(), identificacion);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Entrega registrada en estado ABIERTA");
        } else {
            JOptionPane.showMessageDialog(null, "No se hizo el registro, el codigo esta repetido o el aportante no existe");
        }
    }

    private static void solicitarInformacionLote() {
        String codigoEntrega = JOptionPane.showInputDialog("Ingrese el codigo de la entrega:");

        int tipo = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el tipo de lote:" +
                "\n 1. Papel y carton" +
                "\n 2. Metal" +
                "\n 3. Residuo electronico"));

        String codigoLote = JOptionPane.showInputDialog("Ingrese el codigo del lote:");
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripcion del lote:");
        int pesoKg = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el peso en kilogramos:"));

        Lote nuevoLote = null;

        switch (tipo) {

            case 1:
                int clasificado = Integer.parseInt(JOptionPane.showInputDialog("El material esta clasificado?" +
                        "\n 1. Si" +
                        "\n 2. No"));
                nuevoLote = new PapelCarton(codigoLote, descripcion, pesoKg, clasificado == 1);
                break;

            case 2:
                int metal = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el tipo de metal:" +
                        "\n 1. Aluminio" +
                        "\n 2. Acero"));
                TipoMetal tipoMetal = TipoMetal.ACERO;
                if (metal == 1) {
                    tipoMetal = TipoMetal.ALUMINIO;
                }
                nuevoLote = new Metal(codigoLote, descripcion, pesoKg, tipoMetal);
                break;

            case 3:
                int equipos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de equipos completos:"));
                nuevoLote = new ResiduoElectronico(codigoLote, descripcion, pesoKg, equipos);
                break;

            default:
                JOptionPane.showMessageDialog(null, "El tipo de lote no es valido");
                break;
        }

        if (nuevoLote != null) {
            boolean resultado = ecoRuta.agregarLoteToEntrega(codigoEntrega, nuevoLote);

            if (resultado) {
                JOptionPane.showMessageDialog(null, "Lote agregado. Valor del lote: $" + nuevoLote.calcularValor());
            } else {
                JOptionPane.showMessageDialog(null, "No se agrego el lote. Revise que la entrega exista y este ABIERTA, " +
                        "\nque el peso sea valido y que el codigo no este repetido");
            }
        }
    }

    private static void solicitarAceptarEntrega() {
        String codigoEntrega = JOptionPane.showInputDialog("Ingrese el codigo de la entrega que quiere aceptar:");

        boolean resultado = ecoRuta.aceptarEntrega(codigoEntrega);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Entrega ACEPTADA\n" + ecoRuta.consultarComprobante(codigoEntrega));
        } else {
            JOptionPane.showMessageDialog(null, "No se acepto la entrega. No existe, esta vacia o ya fue aceptada");
        }
    }

    private static void mostrarEntrega() {
        String codigoEntrega = JOptionPane.showInputDialog("Ingrese el codigo de la entrega que quiere ver:");

        Entrega entrega = ecoRuta.buscarEntrega(codigoEntrega);
        String mensaje = "";

        if (entrega != null) {
            mensaje = entrega + "\n\nLotes:";

            for (Lote lote : entrega.getListLotes()) {
                mensaje += "\n" + lote + " - Valor: $" + lote.calcularValor();
            }

            mensaje += "\n\nPeso total: " + entrega.calcularPesoTotal() + " kg" +
                    "\nValor total: $" + entrega.calcularValorTotal();
        } else {
            mensaje = "La entrega no se encuentra en la lista.";
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }

    private static void mostrarComprobante() {
        String codigoEntrega = JOptionPane.showInputDialog("Ingrese el codigo de la entrega:");

        Comprobante comprobante = ecoRuta.consultarComprobante(codigoEntrega);

        if (comprobante != null) {
            JOptionPane.showMessageDialog(null, "Codigo de entrega: " + comprobante.codigoEntrega() +
                    "\nAportante: " + comprobante.identificacionAportante() +
                    "\nPeso total: " + comprobante.pesoTotal() + " kg" +
                    "\nValor total: $" + comprobante.valorTotal());
        } else {
            JOptionPane.showMessageDialog(null, "La entrega no existe o no tiene comprobante");
        }
    }

    private static void mostrarEtiquetas() {
        String codigoEntrega = JOptionPane.showInputDialog("Ingrese el codigo de la entrega:");

        LinkedList<String> etiquetas = ecoRuta.consultarEtiquetas(codigoEntrega);
        String mensaje = "Etiquetas de la entrega " + codigoEntrega + ": " + etiquetas.size() + "\n";

        for (String etiqueta : etiquetas) {
            mensaje += "\n" + etiqueta;
        }

        JOptionPane.showMessageDialog(null, mensaje);
    }

    private static void mostrarValorAcumulado() {
        String identificacion = JOptionPane.showInputDialog("Ingrese la identificacion del aportante:");

        double valorAcumulado = ecoRuta.calcularValorAcumuladoAportante(identificacion);

        JOptionPane.showMessageDialog(null, "Valor acumulado del aportante " + identificacion + ": $" + valorAcumulado);
    }

}