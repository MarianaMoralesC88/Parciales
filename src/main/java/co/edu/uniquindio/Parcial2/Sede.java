package co.edu.uniquindio.Parcial2;

public class Sede {
    private String codigo;
    private String nombre;
    private String direccion;

    private EquipoAudiovisual[] listEquipos;

    public Sede(String codigo, String nombre, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        listEquipos = new EquipoAudiovisual[10];
    }

    public boolean agregarEquipo(EquipoAudiovisual equipo) {
        if (equipo == null) {
            return false;
        }

        for (int i = 0; i < listEquipos.length; i++) {
            if (listEquipos[i] == null) {
                listEquipos[i] = equipo;
                return true;
            }
        }

        return false;
    }

    public String getCodigo() {
        return codigo;
    }

    public EquipoAudiovisual[] getListEquipos() {
        return listEquipos;
    }
}
