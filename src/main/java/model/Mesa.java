package model;

import java.io.Serializable;

public class Mesa implements Serializable {

    private int id;
    private boolean ocupada;
    private int capacidade;

    public Mesa(int id, boolean ocupada, int capacidade) {
        this.id = id;
        this.ocupada = false;
        this.capacidade = capacidade;
    }

    public Mesa(int id, int capacidade) {
        this.id = id;
        this.capacidade = capacidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    @Override
    public String toString() {
        return id + "%" + capacidade + "%" + ocupada + "%";
    }

}
