/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Guilh
 */
public class Conta implements Serializable {

    private static double taxa = 10;
    private ArrayList<Pedido> pedidos;

    public Conta() {
        this.pedidos = new ArrayList<>();
    }

    public Conta(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public static double getTaxa() {
        return taxa;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void addPedidos(Pedido pedido) {
        this.pedidos.add(pedido);

    }

    @Override
    public String toString() {
        return "" + pedidos;
    }

    public double calculaValor() {
        double valor = 0;
        valor = pedidos.stream()
                .mapToDouble(Pedido::getValorTotal)
                .sum();
        valor += valor * taxa / 100;
        return Math.round(valor * 100.0) / 100.0;
    }
}
