package co.edu.uniquindio.Parcial2;

import java.time.LocalDate;

public class Empresa {
    private String nombre;

    private Sede[] listSedes;
    private Cliente[] listClientes;
    private Operador[] listOperadores;
    private Reserva[] listReservas;

    public Empresa(String nombre) {
        this.nombre = nombre;
        listSedes = new Sede[10];
        listClientes = new Cliente[10];
        listOperadores = new Operador[10];
        listReservas = new Reserva[10];
    }


    public boolean registrarSede(Sede sede) {
        if (sede == null || buscarSede(sede.getCodigo()) != null) {
            return false;
        }

        for (int i = 0; i < listSedes.length; i++) {
            if (listSedes[i] == null) {
                listSedes[i] = sede;
                return true;
            }
        }

        return false;
    }

    public boolean registrarEquipo(String codigoSede, EquipoAudiovisual equipo) {
        if (equipo == null) {
            return false;
        }

        Sede sede = buscarSede(codigoSede);


        if (sede == null || buscarEquipo(equipo.getCodigo()) != null) {
            return false;
        }

        return sede.agregarEquipo(equipo);
    }

    public boolean registrarCliente(Cliente cliente) {
        if (cliente == null) {
            return false;
        }

        for (int i = 0; i < listClientes.length; i++) {
            if (listClientes[i] != null
                    && listClientes[i].getIdentificacion().equals(cliente.getIdentificacion())) {
                return false;
            }
        }

        for (int i = 0; i < listClientes.length; i++) {
            if (listClientes[i] == null) {
                listClientes[i] = cliente;
                return true;
            }
        }

        return false;
    }

    public boolean registrarOperador(Operador operador) {
        if (operador == null || buscarOperador(operador.getIdentificacion()) != null) {
            return false;
        }

        for (int i = 0; i < listOperadores.length; i++) {
            if (listOperadores[i] == null) {
                listOperadores[i] = operador;
                return true;
            }
        }

        return false;
    }

    public boolean registrarReserva(Reserva reserva) {

        if (reserva == null || buscarReserva(reserva.getCodigo()) != null) {
            return false;
        }

        for (int i = 0; i < listReservas.length; i++) {
            if (listReservas[i] == null) {
                listReservas[i] = reserva;
                return true;
            }
        }

        return false;
    }


    public boolean agregarEquipoToReserva(String codigoReserva, String codigoEquipo, int horas) {
        Reserva reserva = buscarReserva(codigoReserva);
        EquipoAudiovisual equipo = buscarEquipo(codigoEquipo);

        if (reserva == null || equipo == null) {
            return false;
        }

        return reserva.agregarEquipo(equipo, horas);
    }

    public boolean asignarOperadorToReserva(String codigoReserva, String identificacionOperador) {
        Reserva reserva = buscarReserva(codigoReserva);
        Operador operador = buscarOperador(identificacionOperador);

        if (reserva == null || operador == null) {
            return false;
        }

        return reserva.asignarOperador(operador);
    }

    public boolean cambiarEstadoReserva(String codigoReserva, EstadoReserva nuevoEstado) {
        Reserva reserva = buscarReserva(codigoReserva);

        if (reserva == null || nuevoEstado == null) {
            return false;
        }

        if (nuevoEstado == EstadoReserva.CONFIRMADA) {

            DetalleReserva[] detalles = reserva.getListDetalles();

            for (int i = 0; i < detalles.length; i++) {
                if (detalles[i] != null
                        && equipoReservado(detalles[i].getEquipo().getCodigo(),
                        reserva.getFecha(), reserva.getTurno(), reserva)) {
                    return false;
                }
            }
        }

        return reserva.cambiarEstado(nuevoEstado);
    }

    public EquipoAudiovisual[] consultarEquiposDisponibles(LocalDate fecha, Turno turno) {
        int contador = 0;

        for (int i = 0; i < listSedes.length; i++) {
            if (listSedes[i] != null) {
                EquipoAudiovisual[] equipos = listSedes[i].getListEquipos();
                for (int j = 0; j < equipos.length; j++) {
                    if (esDisponible(equipos[j], fecha, turno)) {
                        contador++;
                    }
                }
            }
        }

        EquipoAudiovisual[] disponibles = new EquipoAudiovisual[contador];
        int posicion = 0;


        for (int i = 0; i < listSedes.length; i++) {
            if (listSedes[i] != null) {
                EquipoAudiovisual[] equipos = listSedes[i].getListEquipos();
                for (int j = 0; j < equipos.length; j++) {
                    if (esDisponible(equipos[j], fecha, turno)) {
                        disponibles[posicion] = equipos[j];
                        posicion++;
                    }
                }
            }
        }

        return disponibles;
    }

    public Reserva[] consultarReservasConfirmadas(String identificacionCliente) {
        int contador = 0;

        for (int i = 0; i < listReservas.length; i++) {
            if (esConfirmadaDelCliente(listReservas[i], identificacionCliente)) {
                contador++;
            }
        }

        Reserva[] confirmadas = new Reserva[contador];
        int posicion = 0;

        for (int i = 0; i < listReservas.length; i++) {
            if (esConfirmadaDelCliente(listReservas[i], identificacionCliente)) {
                confirmadas[posicion] = listReservas[i];
                posicion++;
            }
        }

        return confirmadas;
    }


    public double obtenerValorReserva(String codigoReserva) {
        Reserva reserva = buscarReserva(codigoReserva);

        if (reserva == null) {
            return -1;
        }

        return reserva.calcularValorTotal();
    }


    private Sede buscarSede(String codigoSede) {
        for (int i = 0; i < listSedes.length; i++) {
            if (listSedes[i] != null && listSedes[i].getCodigo().equals(codigoSede)) {
                return listSedes[i];
            }
        }

        return null;
    }

    private EquipoAudiovisual buscarEquipo(String codigoEquipo) {
        for (int i = 0; i < listSedes.length; i++) {
            if (listSedes[i] != null) {
                EquipoAudiovisual[] equipos = listSedes[i].getListEquipos();
                for (int j = 0; j < equipos.length; j++) {
                    if (equipos[j] != null && equipos[j].getCodigo().equals(codigoEquipo)) {
                        return equipos[j];
                    }
                }
            }
        }

        return null;
    }

    private Operador buscarOperador(String identificacionOperador) {
        for (int i = 0; i < listOperadores.length; i++) {
            if (listOperadores[i] != null
                    && listOperadores[i].getIdentificacion().equals(identificacionOperador)) {
                return listOperadores[i];
            }
        }

        return null;
    }

    private Reserva buscarReserva(String codigoReserva) {
        for (int i = 0; i < listReservas.length; i++) {
            if (listReservas[i] != null && listReservas[i].getCodigo().equals(codigoReserva)) {
                return listReservas[i];
            }
        }

        return null;
    }


    private boolean equipoReservado(String codigoEquipo, LocalDate fecha, Turno turno, Reserva reservaExcluida) {
        for (int i = 0; i < listReservas.length; i++) {
            Reserva reserva = listReservas[i];

            if (reserva != null && reserva != reservaExcluida
                    && reserva.getEstado() == EstadoReserva.CONFIRMADA
                    && reserva.getFecha().equals(fecha)
                    && reserva.getTurno() == turno) {

                DetalleReserva[] detalles = reserva.getListDetalles();
                for (int j = 0; j < detalles.length; j++) {
                    if (detalles[j] != null && detalles[j].getEquipo().getCodigo().equals(codigoEquipo)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean esDisponible(EquipoAudiovisual equipo, LocalDate fecha, Turno turno) {
        return equipo != null
                && fecha != null && turno != null
                && equipo.getCondicion() == CondicionEquipo.DISPONIBLE
                && !equipoReservado(equipo.getCodigo(), fecha, turno, null);
    }

    private boolean esConfirmadaDelCliente (Reserva reserva, String identificacionCliente) {
        return reserva != null
                && reserva.getEstado() == EstadoReserva.CONFIRMADA
                && reserva.getTheCliente().getIdentificacion().equals(identificacionCliente);
    }
}
