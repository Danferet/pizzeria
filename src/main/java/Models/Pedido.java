package Models;

public class Pedido {

    private Cliente cliente;
    private float total;
    private String fecha;

    public Pedido(Cliente cliente, String fecha, float total) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String verDetalle() {

        StringBuilder sb = new StringBuilder(cliente.verDetalle()).
                append("\n\n");

        sb.append("\n")
                .append("Total factura: ")
                .append(total)
                .append("€");

        sb.append("\n").append("Hora del pedido: ")
                .append(fecha);

        return sb.toString();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "cliente=" + cliente +
                ", total=" + total +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}