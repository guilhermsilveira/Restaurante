/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.Itens;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;
import model.ItemMenu;
import view.TelaExibirCardapio;

/**
 *
 * @author Guilh
 */
public class TelaCardapioContoller {

    private TelaExibirCardapio view;
    private Itens itens;

    public TelaCardapioContoller() {
        this.view = new TelaExibirCardapio();
        view.setLocationRelativeTo(null); 
        this.itens = Itens.getInstance();

        carregaTabela();

        this.view.getBtnSalvarItem().addActionListener((e) -> {

            addItem();
        });

        this.view.setVisible(true);
    }

    public void addItem() {

        String nome = view.getTxtNome().getText();
        double preco = Double.parseDouble(view.getTxtPreco().getText().trim());

        ItemMenu i = new ItemMenu(nome, preco);

        itens.addItem(i);

        limparTela();

    }

    private void limparTela() {

        this.view.getTxtNome().setText("");
        this.view.getTxtPreco().setText("");
    }

    private void carregaTabela() {
        Object colunas[] = {"Nome", "Preço"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);

        tm.setNumRows(0);
        Iterator<ItemMenu> it = itens.getItens().iterator();
        while (it.hasNext()) {
            ItemMenu i = it.next();
            String item = i.toString();
            String linha[] = item.split("%");
            tm.addRow(new Object[]{linha[0], linha[1]});
        }
        view.getTblCardapio().setModel(tm);
    }
}
