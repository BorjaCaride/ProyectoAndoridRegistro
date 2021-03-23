package com.example.proyectofinal.pojos;

public class Pedidos {
    private String IdPedido;
    private int Mesa;
    private String Pedido;
    private String Estado;

    public Pedidos() {
    }

    public Pedidos(String idPedido, int mesa, String pedido, String estado) {
        IdPedido = idPedido;
        Mesa = mesa;
        Pedido = pedido;
        Estado = estado;
    }

    public String getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(String idPedido) {
        IdPedido = idPedido;
    }

    public int getMesa() {
        return Mesa;
    }

    public void setMesa(int mesa) {
        Mesa = mesa;
    }

    public String getPedido() {
        return Pedido;
    }

    public void setPedido(String pedido) {
        Pedido = pedido;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
