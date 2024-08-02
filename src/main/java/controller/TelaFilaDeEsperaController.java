/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.FilaDeEspera;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;
import view.TelaExibirFila;

/**
 *
 * @author Guilh
 */
public class TelaFilaDeEsperaController {
    private TelaExibirFila view;
    private FilaDeEspera filaDeEspera;
    
    public TelaFilaDeEsperaController(){
        this.view = new TelaExibirFila();
        view.setLocationRelativeTo(null); 
        this.filaDeEspera = FilaDeEspera.getInstance();
        
        carregaTabela();
        
        this.view.setVisible(true);
    }
    
//        private void carregaTabela() {
//        Object colunas[] = {"ID", "Capacidade", "Ocupada"};
//        DefaultTableModel tm = new DefaultTableModel(colunas, 0);
//
//        tm.setNumRows(0);
//        Iterator<FilaDeEspera> it = FilaDeEspera.getFila().iterator();
//        while (it.hasNext()) {
//            FilaDeEspera l = it.next();
//            String fila = l.toString();
//            String linha[] = fila.split("%");
//            tm.addRow(new Object[]{linha[0], linha[1],});
//        }
//        view.getTblFilaDeEspera().setModel(tm);
//    }
    
       private void carregaTabela() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[] { "Nome",  "Quantidade" }, 0);

        filaDeEspera.getFila().stream().forEach(requicao -> {
            model.addRow(new Object[] {
                    requicao.getCliente().getNome(),
                    requicao.getQuantidade()
            });
        });
          view.getTblFilaDeEspera().setModel(model);
    }
}
