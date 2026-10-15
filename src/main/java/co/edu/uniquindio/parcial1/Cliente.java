package co.edu.uniquindio.parcial1;

public class Cliente {

    private String nombreRazonSocial;
    private String documentoNit;
    private long telefono;
    private String correo;
    private String paisProcedencia;

    public Cliente(String nombreRazonSocial, String documentoNit, long telefono, String correo, String paisProcedencia) {
        this.nombreRazonSocial = nombreRazonSocial;
        this.documentoNit = documentoNit;
        this.telefono = telefono;
        this.correo = correo;
        this.paisProcedencia = paisProcedencia;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public String getDocumentoNit() {
        return documentoNit;
    }

    public void setDocumentoNit(String documentoNit) {
        this.documentoNit = documentoNit;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPaisProcedencia() {
        return paisProcedencia;
    }

    public void setPaisProcedencia(String paisProcedencia) {
        this.paisProcedencia = paisProcedencia;
    }
}