package Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Pedido {

    private Pizzeria pizzeria;
    private Cliente cliente;

    /*
    Creo que el producto debería estar relacionado con el item,
    que tiene un producto, una cantidad, precio del producto y precio total (precio * cantidad)
    Seguidamente el "Pedido" estaría relacionado con los items, teniendo una lista de estos últimos.
    También tendrá relación con el cliente, apareciendo el cliente en la factura o ticket
     */

    private List<Item> listaItems;
    private float total;
    private LocalDateTime ldt = LocalDateTime.now();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.listaItems = new ArrayList<>();
        this.ldt = LocalDateTime.now();
        total = 0.0f;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pizzeria getPizzeria() {
        return pizzeria;
    }

    public void setPizzeria(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    public List<Item> getListaItems() {
        return listaItems;
    }

    public void setListaItems(List<Item> listaItems) {
        this.listaItems = listaItems;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public LocalDateTime getLdt() {
        return ldt;
    }

    public void setLdt(LocalDateTime ldt) {
        this.ldt = ldt;
    }

    public void addItem(Item item){

        listaItems.add(item);

    }

    public String verDetalle(){



        StringBuilder sb = new StringBuilder(pizzeria.verDetalle()).
                append("\n").
                append(cliente.verDetalle()).
                append("\n\n");

        for(int i = 0; i<listaItems.size();i++){

            sb.append(listaItems.get(i).verDetalle())
                    .append("\n");

            total += listaItems.get(i).getTotal();
        }

        sb.append("\n")
                .append("Total factura: ")
                .append(total)
                .append("€");

        sb.append("\n").append("Hora del pedido: ")
                .append(ldt.format(formatter));

        return sb.toString();
    }
}