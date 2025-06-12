package Models;

public class Item {

    //Los pedidos estarán compuestos por los items, que a su vez estarán compuestas por:
    //Un producto, un tamaño si procede, una cantidad, un precio, y un total (cantidad * precio)
    private Producto producto;
    private TAMANIO tamanio;
    private byte cantidad;
    private float precio;
    private float total;

    //Las pizzas tendraán el nombre del producto, un tamanio(nomral o familiar) una cantidad y un precio.
    public Item(Producto producto, TAMANIO tamanio, byte cantidad, float precio) {

        this.producto = producto;
        this.tamanio = tamanio;
        this.cantidad = cantidad;
        this.precio = precio;
        total = cantidad * precio;

    }

    //Todos los demás productos tendrán los mismos atributos menos el tamanio
    public Item(Producto producto, byte cantidad, float precio) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        total = cantidad * precio;

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

    public byte getCantidad() {
        return cantidad;
    }

    public void setCantidad(byte cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getTotal() {
        return total;
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
                    + precio
                    + " "
                    + total;

        }else{

            detalle = producto.getNombre()
                    + " "
                    + cantidad
                    + " "
                    + precio
                    + " "
                    + total;

        }

        return detalle;
    }
}
