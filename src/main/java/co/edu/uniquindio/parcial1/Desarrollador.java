package co.edu.uniquindio.parcial1;

import java.util.Date;

public class Desarrollador {

    private String codigo;
    private String equipoTrabajo;
    private String nivel;
    private int maxProyectosSimultaneos;
    private double tarifaDia;
    private String estado;


    public Desarrollador(String codigo, String equipoTrabajo, String nivel, int maxProyectosSimultaneos, double tarifaDia) {
        this.codigo = codigo;
        this.equipoTrabajo = equipoTrabajo;
        this.nivel = nivel;
        this.maxProyectosSimultaneos = maxProyectosSimultaneos;
        this.tarifaDia = tarifaDia;
        this.estado = "Disponible";
    }


    public boolean validarDisponibilidad(Date fechaInicio, Date fechaEntrega) {
        if (this.estado.equalsIgnoreCase("Disponible")) {
            return true;
        }
        return false;
    }

    public boolean actualizarDisponibilidad(String nuevoEstado) {
        setEstado(nuevoEstado);
        return true;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEquipoTrabajo() {
        return equipoTrabajo;
    }

    public void setEquipoTrabajo(String equipoTrabajo) {
        this.equipoTrabajo = equipoTrabajo;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        if (nivel.equalsIgnoreCase("Junior") ||
                nivel.equalsIgnoreCase("Semisenior") ||
                nivel.equalsIgnoreCase("Senior")) {
            this.nivel = nivel;
        }
    }

    public int getMaxProyectosSimultaneos() {
        return maxProyectosSimultaneos;
    }

    public void setMaxProyectosSimultaneos(int maxProyectosSimultaneos) {
        this.maxProyectosSimultaneos = maxProyectosSimultaneos;
    }

    public double getTarifaDia() {
        return tarifaDia;
    }

    public void setTarifaDia(double tarifaDia) {
        this.tarifaDia = tarifaDia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado.equalsIgnoreCase("Disponible") ||
                estado.equalsIgnoreCase("Asignado") ||
                estado.equalsIgnoreCase("Ocupado") ||
                estado.equalsIgnoreCase("En capacitacion")) {
            this.estado = estado;
        }
    }
}


