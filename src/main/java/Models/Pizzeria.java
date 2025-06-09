package Models;

public class Pizzeria {

    private final String nombre = "Pizzería Góndola";
    private final String direccion = "Plaza Don Tomás Galindo, Nº 3, 45270, Mocejón, Toledo";
    private final String cif = "123456789";

    public Pizzeria() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getDirección() {
        return direccion;
    }

    public String getCif() {
        return cif;
    }

    public String verDetalle() {

        return nombre + "\nDirección: " + direccion + "\nCIF: " + cif + ".";

    }
}
