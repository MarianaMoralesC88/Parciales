package co.edu.uniquindio.Parcial2;

public abstract class EquipoAudiovisual {
    protected String codigo;
    protected String marca;
    protected String modelo;

    private CondicionEquipo condicion;

    public EquipoAudiovisual(String codigo, String marca, String modelo) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        condicion = CondicionEquipo.DISPONIBLE;
    }


    public abstract double calcularCostoTotal(int horas);

    public String getCodigo() {
        return codigo;
    }

    public String getMarca() {
        return marca;
    }

    public CondicionEquipo getCondicion() {
        return condicion;
    }

    public void setCondicion(CondicionEquipo condicion) {
        this.condicion = condicion;
    }
}
