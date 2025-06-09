package Models;

public class Ingredientes {

    //solo contará con el atributo nombre.
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
