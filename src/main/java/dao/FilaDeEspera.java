/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Requisicao;

/**
 *
 * @author Guilh
 */
public class FilaDeEspera extends AbstractDAO implements Serializable{

    private List<Requisicao> filaDeEspera;
    private static FilaDeEspera instance;

    private final String localArquivo = "./src/main/java/data/filaDeEspera.txt";

    private FilaDeEspera() {
        this.filaDeEspera = new ArrayList<>();
        carregaFilaDeEspera();
    }

    public static FilaDeEspera getInstance() {
        if (instance == null) {
            instance = new FilaDeEspera();
        }
        return instance;
    }
    
    private void carregaFilaDeEspera(){
        this.filaDeEspera = super.leitura(localArquivo);
    }
    
    public void grava() {
         super.grava(localArquivo, filaDeEspera);
    }
    
    public void addAFila(Requisicao requisicao){
        this.filaDeEspera.add(requisicao);
    }
    
        public List<Requisicao> getFila() {
        return filaDeEspera;
    }
}
