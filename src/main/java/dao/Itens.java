/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.ItemMenu;

/**
 *
 * @author Guilh
 */
public class Itens extends AbstractDAO implements Serializable {

    private List<ItemMenu> itens;

    private static Itens instance;

    // Endereço do arquivo serializado que contém a coleção de mesas
    private final String localArquivo = "src/main/java/data/itens.txt";

    // Singleton
    private Itens() {
        this.itens = new ArrayList<>();
        carregaMesas();
    }

    // Método para recuperar a única instância de mesas
    public static Itens getInstance() {
        if (instance == null) {
            instance = new Itens();
        }
        return instance;
    }

    public void addItem(ItemMenu itemMenu) {
        this.itens.add(itemMenu);
        grava();
    }

    private void carregaMesas() {

        this.itens = super.leitura(localArquivo);
    }

    private void grava() {
        super.grava(localArquivo, itens);
    }

    public List<ItemMenu> getItens() {
        return itens;
    }

    public void excluirItem(ItemMenu itemMenu) {

        itens.remove(itemMenu);
        grava();
    }

    public ItemMenu buscarPorNome(String nome) {
        return itens.stream()
                .filter(itemMenu -> itemMenu.getNome().equals(nome))
                .findFirst()
                .orElse(null);
    }
}
