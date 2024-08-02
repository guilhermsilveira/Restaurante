/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Guilh
 */
public class Cliente implements Serializable{
    
    private String nome;
    private int quantidade;

    public Cliente(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    
    
    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }
    
    public String ToString(){
        return nome + "%" + quantidade;
    }
    
    
}
