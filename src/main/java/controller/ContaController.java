/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Clientes;
import dao.Itens;
import dao.Mesas;
import dao.Requisicoes;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import model.Mesa;
import model.Requisicao;
import view.TelaFinalizarMesa;

/**
 *
 * @author Guilh
 */
public class ContaController {

    private TelaFinalizarMesa view;
    private Requisicao requisicao;
    private Requisicoes requisicoes;
    private final Clientes clientes;
    private final Mesas mesas;
    
    public ContaController(Requisicao requisicao) {
        this.requisicao = requisicao;
        this.view = new TelaFinalizarMesa();
        this.mesas = Mesas.getInstance();
        this.clientes = Clientes.getInstance();
        this.requisicoes = Requisicoes.getInstance();


        FinalizarMesa();
    }
        private void FinalizarMesa() {
        String nome = requisicao.getCliente().getNome();
        clientes.excluirCliente(clientes.buscarClientePorNome(nome));

        int numMesa = requisicao.getMesa().getId();
        Mesa mesa = mesas.buscarMesaPorId(numMesa);
        mesa.setOcupada(false);
        mesas.altera(mesa, requisicao.getMesa().getId());

        requisicoes.excluirRequisicao(requisicao);

        requisicao.setHoraSaida(LocalTime.now());


        double valorFinal = 25; //requisicao.getConta().calculaValor();

        JOptionPane.showMessageDialog(view, "Mesa finalizada, total a pagar: "+valorFinal );
    }
}
