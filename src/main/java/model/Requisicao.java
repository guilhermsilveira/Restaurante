/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Guilh
 */
public class Requisicao implements Serializable {

    private int quantidade;
    private Cliente cliente;
    private LocalDate data;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;
    private Conta conta;
    private Mesa mesa;

    public Requisicao(int quantidade, Mesa mesa, Cliente cliente, LocalDate data, Conta conta, LocalTime horaEntrada, LocalTime horaSaida) {
        this.quantidade = quantidade;
        this.cliente = cliente;
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.conta = conta;
        this.mesa = mesa;

    }

    public Requisicao(Cliente cliente, int quantidade) {
        this.quantidade = quantidade;
        this.cliente = cliente;
        this.data = LocalDate.now();
        this.horaEntrada = LocalTime.now();
        this.horaSaida = LocalTime.now();
        this.mesa = null;
        this.conta = new Conta();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Conta getConta() {
        return conta;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    @Override
    public String toString() {
        return cliente + "%" + quantidade + "%" + data + "%" + horaEntrada + "%" + horaSaida + "%" + mesa + "%" + conta;
    }

}
