package co.edu.uniquindio.parcial1;

import java.util.Date;

public class Empresa {

    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private String paginaWeb;


    private Cliente[] listClientes;
    private Proyecto[] listProyectos;
    private Desarrollador[] listDesarrolladores;
    private ServicioAdicional[] listServicios;

    public Empresa(String nombreComercial, String nit, String direccion,
                   String telefono, String paginaWeb) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.paginaWeb = paginaWeb;


        this.listClientes = new Cliente[10];
        this.listProyectos = new Proyecto[10];
        this.listDesarrolladores = new Desarrollador[10];
        this.listServicios = new ServicioAdicional[10];
    }


    public Cliente buscarClientePorTelefono(long telefonoBusqueda) {
        for (int i = 0; i < listClientes.length; i++) {

            if (listClientes[i] != null && listClientes[i].getTelefono() == telefonoBusqueda) {
                return listClientes[i];
            }
        }
        return null;
    }

    public boolean esNumeroPerfecto(long numero) {
        if (numero <= 1) {
            return false;
        }

        long sumaDivisores = 0;
        for (long i = 1; i <= numero / 2; i++) {
            if (numero % i == 0) {
                sumaDivisores += i;
            }
        }

        return sumaDivisores == numero;
    }


    public double calcularIngresosPorFecha(Date fechaConsulta) {
        double totalIngresos = 0.0;

        for (int i = 0; i < listProyectos.length; i++) {
            if (listProyectos[i] != null && listProyectos[i].getFechaSolicitud().equals(fechaConsulta)) {
                totalIngresos += listProyectos[i].getValorTotal();
            }
        }

        return totalIngresos;
    }

    public boolean registrarCliente(Cliente nuevoCliente) {
        for (int i = 0; i < listClientes.length; i++) {
            if (listClientes[i] == null) {
                listClientes[i] = nuevoCliente;
                return true;
            }
        }
        return false;
    }



    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public Cliente[] getListClientes() {
        return listClientes;
    }

    public void setListClientes(Cliente[] listClientes) {
        this.listClientes = listClientes;
    }

    public Proyecto[] getListProyectos() {
        return listProyectos;
    }

    public void setListProyectos(Proyecto[] listProyectos) {
        this.listProyectos = listProyectos;
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