package controller;

import dao.Clientes;
import dao.Mesas;
import dao.Requisicoes;
import javax.swing.*;
import java.util.Optional;
import model.Requisicao;
import view.TelaFinalizarMesa;

public class FinalizarMesaController {

    private TelaFinalizarMesa view;
    private Requisicao requisicao;
    private Requisicoes requisicoes;
    private final Clientes clientes;
    private final Mesas mesas;

    public FinalizarMesaController() {
        this.requisicao = requisicao;
        this.view = new TelaFinalizarMesa();
        this.clientes = Clientes.getInstance();
        this.requisicoes = Requisicoes.getInstance();
        view.setLocationRelativeTo(null);
        this.mesas = Mesas.getInstance();

        this.view.getBtnEncerrar().addActionListener(e
                -> fecharMesa()
        );

        this.view.setVisible(true);
    }

    private void fecharMesa() {

        String nome = (String) this.view.getTxtNumMesa().getText();
 
        Optional<Requisicao> requisicaoOpt = requisicoes.buscarRequisicao(nome);
        if (requisicaoOpt.isPresent()) {
            new ContaController(requisicaoOpt.get());
        } else {
            // Tratar a ausência da requisição, por exemplo, mostrando uma mensagem de erro
            JOptionPane.showMessageDialog(view, "Não há clientes com esse nome.");
        }

    }
}
