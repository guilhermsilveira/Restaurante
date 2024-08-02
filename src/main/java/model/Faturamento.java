package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Faturamento implements Serializable {
    private double valor;
    private LocalDate data;

    public Faturamento(double valor, LocalDate data) {
        this.valor = valor;
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return valor + "%" + data;
    }

}