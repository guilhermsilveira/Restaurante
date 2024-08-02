package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.Requisicao;

public class Requisicoes extends AbstractDAO implements Serializable {

    private List<Requisicao> requisicoes;
    private static Requisicoes instance;

    private final String localArquivo = "./src/main/java/data/requisicoes.txt";

    private Requisicoes() {
        this.requisicoes = new ArrayList<>();
        carregaRequisicoes();
    }

    public static Requisicoes getInstance() {
        if (instance == null) {
            instance = new Requisicoes();
        }
        return instance;
    }

    public void addRequisicao(Requisicao requisicao) {
        this.requisicoes.add(requisicao);
        grava();
    }

    private void carregaRequisicoes() {
        this.requisicoes = super.leitura(localArquivo);
    }
    
    public Optional<Requisicao> buscarRequisicao(String nome) {
        return requisicoes.stream()
                           .filter(requisicao -> requisicao.getCliente().getNome().equals(nome))
                           .findFirst();
    }
    
    private void grava() {
        super.grava(localArquivo, requisicoes);
    }

    public List<Requisicao> getRequisicoes() {
        return requisicoes;
    }

    public void excluirRequisicao(Requisicao requisicao) {
        requisicoes.remove(requisicao);
        grava();
    }


    public boolean altera(Requisicao atendimentoExistente, String nome) {
        try {
            requisicoes = requisicoes.stream()
                                       .map(atendimento -> atendimento.getCliente().getNome().equals(nome) ? atendimentoExistente : atendimento)
                                       .collect(Collectors.toList());
            grava();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
