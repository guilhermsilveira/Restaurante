package controller;

import dao.Mesas;
import exception.MesaJaExisteException;
import exception.CapacidadeInvalidaException;

import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Mesa;
import view.TelaExibirMesa;

/**
 *
 * @author Guilh
 */
public class TelaMesaController {

    private TelaExibirMesa view;
    private Mesas mesas;

    public TelaMesaController() {
        this.view = new TelaExibirMesa();
        view.setLocationRelativeTo(null); 
        this.mesas = Mesas.getInstance();

        carregaTabela();

        this.view.getBtnSalvarMesa().addActionListener((e) -> {
            try {
                addMesa();
            } catch (MesaJaExisteException | CapacidadeInvalidaException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.view.setVisible(true);
    }

    public void addMesa() throws MesaJaExisteException, CapacidadeInvalidaException {
        int id;
        int capacidade;

        try {
            id = Integer.parseInt(view.getTxtId().getText());
        } catch (NumberFormatException ex) {
            throw new CapacidadeInvalidaException("O ID da mesa deve ser um número válido.");
        }

        try {
            capacidade = Integer.parseInt(view.getTxtCapacidade().getText());
        } catch (NumberFormatException ex) {
            throw new CapacidadeInvalidaException("A capacidade da mesa deve ser um número válido.");
        }

        if (mesas.getMesas().stream().anyMatch(mesa -> mesa.getId() == id)) {
            throw new MesaJaExisteException("Uma mesa com este ID já existe.");
        }

        Mesa m = new Mesa(id, capacidade);
        mesas.addMesa(m);
        limparTela();
        carregaTabela();
    }

    private void limparTela() {
        this.view.getTxtId().setText("");
        this.view.getTxtCapacidade().setText("");
    }

    private void carregaTabela() {
        Object colunas[] = {"ID", "Capacidade", "Ocupada"};
        DefaultTableModel tm = new DefaultTableModel(colunas, 0);

        tm.setNumRows(0);
        Iterator<Mesa> it = mesas.getMesas().iterator();
        while (it.hasNext()) {
            Mesa m = it.next();
            String mesa = m.toString();
            String linha[] = mesa.split("%");
            tm.addRow(new Object[]{linha[0], linha[1], linha[2]});
        }
        view.getTbMesa().setModel(tm);
    }
}
