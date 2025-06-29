package Models;

import java.text.DecimalFormat;
import java.util.Objects;

public class Item {

    //Los pedidos estarán compuestos por los items, que a su vez estarán compuestas por:
    //Un producto, un tamaño si procede, una cantidad, un precio, y un total (cantidad * precio)
    private Producto producto;
    private TAMANIO tamanio;
    private int cantidad;
    private float precio;
    private float total;
    DecimalFormat df = new DecimalFormat("#.##");

    //Las pizzas tendraán el nombre del producto, un tamaño(normal o familiar) una cantidad y un precio.
    public Item(Producto producto, TAMANIO tamanio, int cantidad, float precio) {

        this.producto = producto;
        this.tamanio = tamanio;
        this.cantidad = cantidad;
        this.precio = precio;
        //total = cantidad * precio;
    }

    //Todos los demás productos tendrán los mismos atributos menos el tamanio
    public Item(Producto producto, int cantidad, float precio) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        //total = cantidad * precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public TAMANIO getTamanio() {
        return tamanio;
    }

    public void setTamanio(TAMANIO tamanio) {
        this.tamanio = tamanio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getTotal() {
        return this.cantidad * this.precio;
    }

    public void setTotal(float total) {
        this.total = total;
    }



    public String verDetalle() {

        String detalle;

        if(producto.getTipo().equals("pizza")){

            detalle = producto.getNombre()
                    + " "
                    + tamanio
                    + " "
                    + cantidad
                    + " "
                    + df.format(precio)
                    + " € "
                    + df.format(getTotal()) + " €.";
        }else{

            detalle = producto.getNombre()
                    + " "
                    + cantidad
                    + " "
                    + precio
                    + " € "
                    + getTotal() + " € ";

        }
        return detalle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto, tamanio, cantidad, precio, total);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item other = (Item) obj;
        return this.getProducto().equals(other.getProducto())
                && this.getTamanio() == other.getTamanio();
    }
}
