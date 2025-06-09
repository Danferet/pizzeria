package Models;

public class Cliente {

    //El cliente tendrá los siguientes atributos
    private String nombre;
    private String direccion;
    private String telefono;


    //Solo se necesitará un constructor completo, en principio.
    public Cliente(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    //Un metodo ver detalle para mostrar los datos.
    public String verDetalle() {

        return "Nombre: "
                + "."
                + nombre
                + "\nDirección: "
                + direccion
                + "."
                + "\nTeléfono: "
                + telefono;
    }
}
