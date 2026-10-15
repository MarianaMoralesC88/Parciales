package co.edu.uniquindio.parcial1;

import java.util.Date;


public class Proyecto {

    private String codigo;
    private Date fechaSolicitud;
    private Date fechaInicio;
    private Date fechaEntrega;
    private String estado;
    private String metodoPago;
    private double valorTotal;

    private Desarrollador[] listDesarrolladores;
    private ServicioAdicional[] listServicios;

    public Proyecto(String codigo, Date fechaSolicitud, Date fechaInicio, Date fechaEntrega, String metodoPago) {
        this.codigo = codigo;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaInicio = fechaInicio;
        this.fechaEntrega = fechaEntrega;
        this.metodoPago = metodoPago;
        this.estado = "Pendiente";
        this.valorTotal = 0.0;

        this.listDesarrolladores = new Desarrollador[10];
        this.listServicios = new ServicioAdicional[10];
    }

    public double calcularValorTotal(double descuento) {
        double subtotal = 0;
        this.valorTotal = subtotal - (subtotal * (descuento / 100));
        return this.valorTotal;
    }

    public boolean cambiarEstado(String nuevoEstado) {
        setEstado(nuevoEstado);
        return true;
    }

    public boolean confirmarProyecto() {
        this.estado = "Confirmado";
        return true;
    }

    public boolean agregarDesarrollador(Desarrollador desarrollador) {
        for (int i = 0; i < listDesarrolladores.length; i++) {
            if (listDesarrolladores[i] == null) {
                listDesarrolladores[i] = desarrollador;
                return true;
            }
        }
        return false;
    }

    public boolean agregarServicio(ServicioAdicional servicio) {
        for (int i = 0; i < listServicios.length; i++) {
            if (listServicios[i] == null) {
                listServicios[i] = servicio;
                return true;
            }
        }
        return false;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado.equalsIgnoreCase("Pendiente") ||
                estado.equalsIgnoreCase("Confirmado") ||
                estado.equalsIgnoreCase("En curso") ||
                estado.equalsIgnoreCase("Finalizado") ||
                estado.equalsIgnoreCase("Cancelado")) {
            this.estado = estado;
        }
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        if (metodoPago.equalsIgnoreCase("Tarjeta de credito") ||
                metodoPago.equalsIgnoreCase("Transferencia") ||
                metodoPago.equalsIgnoreCase("Efectivo")) {
            this.metodoPago = metodoPago;
        }
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Desarrollador[] getListDesarrolladores() {
        return listDesarrolladores;
    }

    public void setListDesarrolladores(Desarrollador[] listDesarrolladores) {
        this.listDesarrolladores = listDesarrolladores;
    }

    public ServicioAdicional[] getListServicios() {
        return listServicios;
    }

    public void setListServicios(ServicioAdicional[] listServicios) {
        this.listServicios = listServicios;
    }
}