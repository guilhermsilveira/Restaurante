/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author Guilh
 */
public class Clientes extends AbstractDAO implements Serializable {

    private List<Cliente> clientes;
    private static Clientes instance;

    private final String localArquivo = "./src/main/java/data/clientes.txt";

    private Clientes() {
        this.clientes = new ArrayList<>();
        carregaClientes();
    }

    public static Clientes getInstance() {
        if (instance == null) {
            instance = new Clientes();
        }
        return instance;
    }

    public void addCliente(Cliente cliente) {
        this.clientes.add(cliente);
        grava();
    }

    private void carregaClientes() {
        this.clientes = super.leitura(localArquivo);
    }

    private void grava() {
        super.grava(localArquivo, clientes);
    }

    public void excluirCliente(Cliente cliente) {
        clientes.remove(cliente);
        grava();
    }

public Cliente buscarClientePorNome(String nome) {
    return clientes.stream()
                   .filter(cliente -> cliente.getNome().equalsIgnoreCase(nome))
                   .findFirst()
                   .orElse(null);
}

}
