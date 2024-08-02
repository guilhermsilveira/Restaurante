package dao;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Faturamento;

public class Faturamentos extends AbstractDAO implements Serializable {

    private List<Faturamento> faturamentos;
    private static Faturamentos instance;

    private final String localArquivo = "./src/main/java/data/faturamentos.txt";

    private Faturamentos() {
        this.faturamentos = new ArrayList<>();
        carregaFaturamentos();
    }

    public static Faturamentos getInstance() {
        if (instance == null) {
            instance = new Faturamentos();
        }
        return instance;
    }

    public void addPagamento(Faturamento faturamento) {
        try {
            Faturamento pagamentoPorData = buscarFaturamento(faturamento.getData());
            if (pagamentoPorData != null) {
                double valorAtual = pagamentoPorData.getValor() + faturamento.getValor();
                pagamentoPorData.setValor(valorAtual);
            } else {
                faturamentos.add(faturamento);
            }
            grava();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void carregaFaturamentos() {

        this.faturamentos = super.leitura(localArquivo);

    }

    private void grava() {

        super.grava(localArquivo, faturamentos);

    }

    public List<Faturamento> getFaturamento() {
        return faturamentos;
    }

    public Faturamento buscarFaturamento(LocalDate data) {
        try {
            return faturamentos.stream()
                    .filter(pagamento -> pagamento.getData().equals(data))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
