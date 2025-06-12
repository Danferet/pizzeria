package Models;

public class ProductoIngrediente {

    private int id_producto;
    private int id_ingrediente;

    public ProductoIngrediente(int id_producto, int id_ingrediente) {
        this.id_producto = id_producto;
        this.id_ingrediente = id_ingrediente;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_ingrediente() {
        return id_ingrediente;
    }

    public void setId_ingrediente(int id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }
}
