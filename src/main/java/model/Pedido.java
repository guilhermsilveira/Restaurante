package model;

import java.io.Serializable;

public class Pedido implements Serializable {
    private ItemMenu itemMenu;
    private int quantidade;
    private double valorTotal;

    public Pedido(ItemMenu itemMenu, int quantidade) {
        this.itemMenu = itemMenu;
        this.quantidade = quantidade;
    }

    public Pedido(ItemMenu itemMenu, int quantidade, double valorTotal) {
        this.itemMenu = itemMenu;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    public ItemMenu getItem() {
        return itemMenu;
    }

    public void setItem(ItemMenu itemMenu) {
        this.itemMenu = itemMenu;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        valorTotal = itemMenu.getPreco()* quantidade;
        return valorTotal;
    }

    @Override
    public String toString() {
        return itemMenu + "%" + quantidade + " %" + valorTotal;
    }
}