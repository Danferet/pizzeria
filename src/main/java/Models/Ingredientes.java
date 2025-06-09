package Models;

public class Ingredientes {

    private String nombre;

    public Ingredientes(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
