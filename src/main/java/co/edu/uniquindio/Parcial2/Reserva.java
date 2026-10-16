package co.edu.uniquindio.Parcial2;

import java.time.LocalDate;

public class Reserva {
    private String codigo;
    private LocalDate fecha;

    private Turno turno;
    private EstadoReserva estado;
    private Cliente theCliente;
    private Operador theOperador;
    private DetalleReserva[] listDetalles;

    public Reserva(String codigo, LocalDate fecha, Turno turno, Cliente cliente) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.turno = turno;
        this.theCliente = cliente;
        estado = EstadoReserva.SOLICITADA;
        theOperador = null;
        listDetalles = new DetalleReserva[10];
    }


    public boolean agregarEquipo(EquipoAudiovisual equipo, int cantidadHoras) {
        if (equipo == null || cantidadHoras <= 0) {
            return false;
        }


        if (equipo.getCondicion() != CondicionEquipo.DISPONIBLE) {
            return false;
        }


        for (int i = 0; i < listDetalles.length; i++) {
            if (listDetalles[i] != null
                    && listDetalles[i].getEquipo().getCodigo().equals(equipo.getCodigo())) {
                return false;
            }
        }

        for (int i = 0; i < listDetalles.length; i++) {
            if (listDetalles[i] == null) {
                listDetalles[i] = new DetalleReserva(equipo, cantidadHoras);
                return true;
            }
        }

        return false;
    }

    public boolean asignarOperador(Operador operador) {
        if (operador == null) {
            return false;
        }

        theOperador = operador;
        return true;
    }


    public boolean cambiarEstado(EstadoReserva nuevoEstado) {
        if (nuevoEstado == null) {
            return false;
        }

        if (nuevoEstado == EstadoReserva.CONFIRMADA) {

            if (estado == EstadoReserva.CANCELADA || estado == EstadoReserva.FINALIZADA) {
                return false;
            }

            int contador = 0;
            boolean equiposUtilizables = true;

            for (int i = 0; i < listDetalles.length; i++) {
                if (listDetalles[i] != null) {
                    contador++;
                    if (listDetalles[i].getEquipo().getCondicion() != CondicionEquipo.DISPONIBLE) {
                        equiposUtilizables = false;
                    }
                }
            }

            if (contador == 0 || !equiposUtilizables) {
                return false;
            }

            // Una reserva con un dron necesita un operador asignado
            if (contieneDron() && theOperador == null) {
                return false;
            }
        }

        estado = nuevoEstado;
        return true;
    }

    public boolean contieneDron() {
        boolean encontrado = false;

        for (int i = 0; i < listDetalles.length && !encontrado; i++) {
            if (listDetalles[i] != null && listDetalles[i].getEquipo() instanceof Dron) {
                encontrado = true;
            }
        }

        return encontrado;
    }


    public double calcularValorTotal() {
        double total = 0;

        for (int i = 0; i < listDetalles.length; i++) {
            if (listDetalles[i] != null) {
                total += listDetalles[i].calcularSubTotal();
            }
        }

        return total;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Turno getTurno() {
        return turno;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public Cliente getTheCliente() {
        return theCliente;
    }

    public DetalleReserva[] getListDetalles() {
        return listDetalles;
    }
}