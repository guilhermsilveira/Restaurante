/*
  *Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Itens;
import dao.Mesas;
import dao.Requisicoes;
import java.util.Optional;
import javax.swing.JOptionPane;
import model.Requisicao;
import view.TelaAnotarPedido;

/**
 *
 * @author Guilh
 */
public class AnotarPedidoController {

    private TelaAnotarPedido view;
    private Itens itens;
    private Requisicao requisicao;
    private Requisicoes requisicoes;
    private Mesas mesas;

    public AnotarPedidoController() {
        this.view = new TelaAnotarPedido();
        this.requisicoes = Requisicoes.getInstance();
        this.itens = Itens.getInstance();
        this.mesas = Mesas.getInstance();
        this.requisicao = requisicao;

        view.setLocationRelativeTo(null);

        this.view.getBtnSalvarPedido().addActionListener(e
                -> fazerPedido()
        );

        this.view.setVisible(true);
    }

    private void fazerPedido() {

        String nome = view.getTxtNomeCliente().getText();

        Optional<Requisicao> requisicaoOpt = requisicoes.buscarRequisicao(nome);

        if (requisicaoOpt.isPresent()) {
          RequisicaoController requisicaoController =  new RequisicaoController(requisicaoOpt.get());
        } else {
            // Tratar a ausência da requisição, por exemplo, mostrando uma mensagem de erro
            JOptionPane.showMessageDialog(view, "Nenhuma requisição encontrada para o nome fornecido.");
        }
    }
}
