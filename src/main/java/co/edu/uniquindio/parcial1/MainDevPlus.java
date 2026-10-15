
package co.edu.uniquindio.parcial1;

import javax.swing.JOptionPane;



public class MainDevPlus {

    private static Empresa miEmpresa;

    public static void main(String[] args) {

        miEmpresa = new Empresa("DevPlus", "900123456-1", "Calle 123", "3001234567", "www.devplus.com");

        int opcion = 0;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Este es el menu de la empresa DevPlus:" +
                    "\n Seleccione una opcion:" +
                    "\n 1. Registrar cliente." +
                    "\n 2. Buscar cliente por telefono." +
                    "\n 3. Verificar numero perfecto." +
                    "\n 0. Salir del sistema"));

            switch (opcion) {

                case 1:
                    solicitarInformacionCliente();
                    break;

                case 2:
                    solicitarBusquedaCliente();
                    break;

                case 3:
                    solicitarVerificacionNumero();
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "El programa finalizo");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "La opcion no es valida");
                    break;
            }

        } while (opcion != 0);
    }

    // Registrar la informacion de los clientes
    private static void solicitarInformacionCliente() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre o razon social del cliente:");
        String nit = JOptionPane.showInputDialog("Ingrese el NIT o documento:");
        long telefono = Long.parseLong(JOptionPane.showInputDialog("Ingrese el numero de telefono:"));
        String correo = JOptionPane.showInputDialog("Ingrese el correo electronico:");
        String pais = JOptionPane.showInputDialog("Ingrese el pais de procedencia:");

        Cliente nuevoCliente = new Cliente(nombre, nit, telefono, correo, pais);
        boolean resultado = miEmpresa.registrarCliente(nuevoCliente);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Registro exitoso");
        } else {
            JOptionPane.showMessageDialog(null, "No se hizo el registro");
        }
    }

    // Buscar la informacion de los clientes
    private static void solicitarBusquedaCliente() {
        long telefonoBusqueda = Long.parseLong(JOptionPane.showInputDialog("Ingrese el telefono del cliente que quiere ver:"));

        Cliente clienteEncontrado = miEmpresa.buscarClientePorTelefono(telefonoBusqueda);

        if (clienteEncontrado != null) {
            String mensaje = "co.edu.uniquindio.devplus.Cliente encontrado:\n" +
                    "\nNombre: " + clienteEncontrado.getNombreRazonSocial() +
                    "\nNIT: " + clienteEncontrado.getDocumentoNit() +
                    "\nCorreo: " + clienteEncontrado.getCorreo() +
                    "\nPais: " + clienteEncontrado.getPaisProcedencia();
            JOptionPane.showMessageDialog(null, mensaje);
        } else {
            JOptionPane.showMessageDialog(null, "El cliente no se encuentra en la lista.");
        }
    }

    // Verificar el numero perfecto
    private static void solicitarVerificacionNumero() {
        long numero = Long.parseLong(JOptionPane.showInputDialog("Ingrese el numero que quiere verificar:"));

        boolean esPerfecto = miEmpresa.esNumeroPerfecto(numero);

        if (esPerfecto) {
            JOptionPane.showMessageDialog(null, "El numero SI es perfecto.");
        } else {
            JOptionPane.showMessageDialog(null, "El numero NO es perfecto.");
        }
    }
}