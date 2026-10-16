package co.edu.uniquindio.Parcial2;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main {


    static Empresa miEmpresa = new Empresa("PixelRent");


    static Cliente[] listClientes = new Cliente[10];

    public static void main(String[] args) {
        int option = 0;
        do {
            try {
                option = Integer.parseInt(JOptionPane.showInputDialog("=== SISTEMA PIXELRENT ===\n" +
                        "Seleccione una opcion:\n" +
                        "1. Cargar datos de prueba (Recomendado iniciar aquí)\n" +
                        "2. Registrar Sede\n" +
                        "3. Registrar Cliente\n" +
                        "4. Registrar Operador\n" +
                        "5. Registrar Equipo Audiovisual (Cámara/Dron/Kit)\n" +
                        "6. Crear nueva Reserva (Inicia SOLICITADA)\n" +
                        "7. Agregar Equipo a una Reserva\n" +
                        "8. Asignar Operador a una Reserva\n" +
                        "9. Confirmar/Cambiar estado de Reserva\n" +
                        "10. Consultar Valor Total de una Reserva\n" +
                        "11. Consultar Equipos Disponibles (Fecha/Turno)\n" +
                        "12. Consultar Reservas Confirmadas de un Cliente\n" +
                        "0. Salir del sistema"));

                switch (option) {
                    case 1:
                        cargarDatosDePrueba();
                        break;
                    case 2:
                        solicitarInformacionSede();
                        break;
                    case 3:
                        solicitarInformacionCliente();
                        break;
                    case 4:
                        solicitarInformacionOperador();
                        break;
                    case 5:
                        solicitarInformacionEquipo();
                        break;
                    case 6:
                        solicitarInformacionReserva();
                        break;
                    case 7:
                        solicitarAgregarEquipoReserva();
                        break;
                    case 8:
                        solicitarAsignarOperador();
                        break;
                    case 9:
                        solicitarCambiarEstadoReserva();
                        break;
                    case 10:
                        solicitarValorReserva();
                        break;
                    case 11:
                        solicitarEquiposDisponibles();
                        break;
                    case 12:
                        solicitarReservasCliente();
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null, "El programa finalizó");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "La opción no es válida");
                        break;
                }
            } catch (NumberFormatException | DateTimeParseException e) {

                if (option != 0) {
                    JOptionPane.showMessageDialog(null, "Entrada inválida (revise números y fecha AAAA-MM-DD). Intente de nuevo.");
                }
            }

        } while (option != 0);
    }



    private static void solicitarInformacionSede() {
        String codigo = JOptionPane.showInputDialog("Ingrese el código de la sede (ej. S1):");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la sede:");
        String direccion = JOptionPane.showInputDialog("Ingrese la dirección de la sede:");

        boolean resultado = miEmpresa.registrarSede(new Sede(codigo, nombre, direccion));
        mostrarResultado(resultado, "Sede registrada exitosamente.", "Error al registrar la sede (código duplicado).");
    }

    private static void solicitarInformacionCliente() {
        String id = JOptionPane.showInputDialog("Ingrese la identificación del cliente:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        String telefono = JOptionPane.showInputDialog("Ingrese el teléfono del cliente:");
        String correo = JOptionPane.showInputDialog("Ingrese el correo del cliente:");

        Cliente cliente = new Cliente(id, nombre, telefono, correo);
        boolean resultado = miEmpresa.registrarCliente(cliente);

        if (resultado) {
            guardarCliente(cliente);
        }

        mostrarResultado(resultado, "Cliente registrado exitosamente.", "Error: Ya existe un cliente con esa identificación.");
    }

    private static void solicitarInformacionOperador() {
        String id = JOptionPane.showInputDialog("Ingrese la identificación del operador:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del operador:");
        String certificacion = JOptionPane.showInputDialog("Ingrese la certificación:");
        int experiencia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese los años de experiencia:"));

        boolean resultado = miEmpresa.registrarOperador(new Operador(id, nombre, certificacion, experiencia));
        mostrarResultado(resultado, "Operador registrado exitosamente.", "Error: Operador duplicado.");
    }

    private static void solicitarInformacionEquipo() {
        String codigoSede = JOptionPane.showInputDialog("Ingrese el código de la Sede donde estará el equipo:");
        int tipo = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el tipo de equipo:\n1. Cámara\n2. Dron\n3. Kit de Iluminación"));

        String codigo = JOptionPane.showInputDialog("Ingrese el código único del equipo:");
        String marca = JOptionPane.showInputDialog("Ingrese la marca:");
        String modelo = JOptionPane.showInputDialog("Ingrese el modelo:");

        boolean resultado = false;

        if (tipo == 1) {
            String resolucion = JOptionPane.showInputDialog("Resolución máxima (ej. 4K):");
            int respEstabilizador = JOptionPane.showConfirmDialog(null, "¿Posee estabilizador?", "Estabilizador", JOptionPane.YES_NO_OPTION);
            boolean estabilizador = (respEstabilizador == JOptionPane.YES_OPTION);

            resultado = miEmpresa.registrarEquipo(codigoSede, new Camara(codigo, marca, modelo, resolucion, estabilizador));
        } else if (tipo == 2) {
            int autonomia = Integer.parseInt(JOptionPane.showInputDialog("Autonomía de vuelo en minutos:"));
            int respBateria = JOptionPane.showConfirmDialog(null, "¿Posee batería adicional?", "Batería", JOptionPane.YES_NO_OPTION);
            boolean bateriaAdicional = (respBateria == JOptionPane.YES_OPTION);

            resultado = miEmpresa.registrarEquipo(codigoSede, new Dron(codigo, marca, modelo, autonomia, bateriaAdicional));
        } else if (tipo == 3) {
            int cantidadLuces = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de luces:"));
            int respControl = JOptionPane.showConfirmDialog(null, "¿Posee control inalámbrico?", "Control", JOptionPane.YES_NO_OPTION);
            boolean control = (respControl == JOptionPane.YES_OPTION);

            resultado = miEmpresa.registrarEquipo(codigoSede, new KitIluminacion(codigo, marca, modelo, cantidadLuces, control));
        }

        mostrarResultado(resultado, "Equipo registrado correctamente en la sede.", "Error: El código de equipo ya existe o la sede no fue encontrada.");
    }

    private static void solicitarInformacionReserva() {
        String codigo = JOptionPane.showInputDialog("Ingrese el código único de la Reserva:");
        String idCliente = JOptionPane.showInputDialog("Ingrese la identificación del Cliente:");

        String fechaStr = JOptionPane.showInputDialog("Ingrese la fecha (AAAA-MM-DD):");
        LocalDate fecha = LocalDate.parse(fechaStr);

        int opcTurno = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el turno:\n1. Mañana\n2. Tarde\n3. Noche"));
        Turno turno = (opcTurno == 1) ? Turno.MANANA : (opcTurno == 2) ? Turno.TARDE : Turno.NOCHE;

        Cliente cliente = buscarCliente(idCliente);

        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Error: El cliente no está registrado.");
            return;
        }

        boolean resultado = miEmpresa.registrarReserva(new Reserva(codigo, fecha, turno, cliente));
        mostrarResultado(resultado, "Reserva SOLICITADA creada con éxito.", "Error: Ya existe una reserva con ese código.");
    }

    private static void solicitarAgregarEquipoReserva() {
        String codigoReserva = JOptionPane.showInputDialog("Ingrese el código de la Reserva:");
        String codigoEquipo = JOptionPane.showInputDialog("Ingrese el código del Equipo a agregar:");
        int horas = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de horas de alquiler para este equipo:"));

        boolean resultado = miEmpresa.agregarEquipoToReserva(codigoReserva, codigoEquipo, horas);
        mostrarResultado(resultado, "Equipo agregado a la reserva exitosamente.", "Error: El equipo no existe, no está disponible o ya fue agregado a esta reserva.");
    }

    private static void solicitarAsignarOperador() {
        String codigoReserva = JOptionPane.showInputDialog("Ingrese el código de la Reserva:");
        String idOperador = JOptionPane.showInputDialog("Ingrese la identificación del Operador:");

        boolean resultado = miEmpresa.asignarOperadorToReserva(codigoReserva, idOperador);
        mostrarResultado(resultado, "Operador asignado a la reserva.", "Error: La reserva o el operador no existen.");
    }

    private static void solicitarCambiarEstadoReserva() {
        String codigoReserva = JOptionPane.showInputDialog("Ingrese el código de la Reserva:");
        int opcEstado = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el nuevo estado:\n1. CONFIRMADA\n2. CANCELADA\n3. FINALIZADA"));

        EstadoReserva estado = (opcEstado == 1) ? EstadoReserva.CONFIRMADA :
                (opcEstado == 2) ? EstadoReserva.CANCELADA : EstadoReserva.FINALIZADA;

        boolean resultado = miEmpresa.cambiarEstadoReserva(codigoReserva, estado);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "El estado de la reserva cambió a: " + estado);
        } else {
            String motivo = "Error de validación al cambiar estado.\n" +
                    "Si intentó CONFIRMAR, verifique:\n" +
                    "- Que la reserva tenga al menos un equipo.\n" +
                    "- Que no incluya un dron sin operador asignado.\n" +
                    "- Que los equipos no estén en otra reserva confirmada en la misma fecha y turno.\n" +
                    "- Que la reserva no estuviese previamente cancelada/finalizada.";
            JOptionPane.showMessageDialog(null, motivo);
        }
    }

    private static void solicitarValorReserva() {
        String codigoReserva = JOptionPane.showInputDialog("Ingrese el código de la Reserva:");
        double valor = miEmpresa.obtenerValorReserva(codigoReserva);

        if (valor != -1) {
            JOptionPane.showMessageDialog(null, "El valor total de la reserva " + codigoReserva + " es: $" + valor);
        } else {
            JOptionPane.showMessageDialog(null, "Error: Reserva no encontrada.");
        }
    }

    private static void solicitarEquiposDisponibles() {
        String fechaStr = JOptionPane.showInputDialog("Ingrese la fecha a consultar (AAAA-MM-DD):");
        LocalDate fecha = LocalDate.parse(fechaStr);

        int opcTurno = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el turno:\n1. Mañana\n2. Tarde\n3. Noche"));
        Turno turno = (opcTurno == 1) ? Turno.MANANA : (opcTurno == 2) ? Turno.TARDE : Turno.NOCHE;

        EquipoAudiovisual[] disponibles = miEmpresa.consultarEquiposDisponibles(fecha, turno);

        String mensaje = "Equipos disponibles (" + disponibles.length + "):\n";
        for (int i = 0; i < disponibles.length; i++) {
            mensaje += "- " + disponibles[i].getCodigo() + " (" + disponibles[i].getClass().getSimpleName() + ")\n";
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private static void solicitarReservasCliente() {
        String idCliente = JOptionPane.showInputDialog("Ingrese la identificación del Cliente:");
        Reserva[] reservas = miEmpresa.consultarReservasConfirmadas(idCliente);

        String mensaje = "Reservas CONFIRMADAS del cliente (" + reservas.length + "):\n";
        for (int i = 0; i < reservas.length; i++) {
            mensaje += "- Reserva: " + reservas[i].getCodigo() + " | Fecha: " + reservas[i].getFecha() + "\n";
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }



    private static void guardarCliente(Cliente cliente) {
        for (int i = 0; i < listClientes.length; i++) {
            if (listClientes[i] == null) {
                listClientes[i] = cliente;
                return;
            }
        }
    }

    private static Cliente buscarCliente(String identificacion) {
        for (int i = 0; i < listClientes.length; i++) {
            if (listClientes[i] != null && listClientes[i].getIdentificacion().equals(identificacion)) {
                return listClientes[i];
            }
        }

        return null;
    }

    private static void mostrarResultado(boolean exito, String mensajeExito, String mensajeError) {
        if (exito) {
            JOptionPane.showMessageDialog(null, mensajeExito);
        } else {
            JOptionPane.showMessageDialog(null, mensajeError);
        }
    }

    private static void cargarDatosDePrueba() {
        miEmpresa.registrarSede(new Sede("S1", "Sede Centro", "Calle 10"));
        miEmpresa.registrarEquipo("S1", new Camara("C1", "Sony", "A7", "4K", true));
        miEmpresa.registrarEquipo("S1", new Camara("C2", "Canon", "R5", "8K", false));

        Camara camaraEnMantenimiento = new Camara("C3", "Nikon", "Z6", "4K", false);
        camaraEnMantenimiento.setCondicion(CondicionEquipo.EN_MANTENIMIENTO);
        miEmpresa.registrarEquipo("S1", camaraEnMantenimiento);

        miEmpresa.registrarEquipo("S1", new Dron("D1", "DJI", "Mavic", 30, true));
        miEmpresa.registrarEquipo("S1", new KitIluminacion("K1", "Godox", "SL60", 3, false));
        Cliente clienteBase = new Cliente("1001", "Ana Perez", "310", "ana@correo.com");
        if (miEmpresa.registrarCliente(clienteBase)) {
            guardarCliente(clienteBase);
        }
        miEmpresa.registrarOperador(new Operador("2001", "Luis Gomez", "Piloto", 5));

        JOptionPane.showMessageDialog(null, "Datos de prueba cargados:\nSede: S1\nCliente: 1001\nOperador: 2001\nEquipos: C1, C2, C3 (en mantenimiento), D1, K1");
    }
}