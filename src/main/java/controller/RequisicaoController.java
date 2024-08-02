package controller;

import java.util.List;
import java.util.Optional;
import dao.Clientes;
import dao.Itens;
import dao.Mesas;
import dao.Requisicoes;
import java.time.LocalTime;
import javax.swing.JOptionPane;
import model.ItemMenu;
import model.Mesa;
import model.Pedido;
import model.Requisicao;
import view.TelaAnotarPedido;
import view.TelaCadastrarCliente;
import view.TelaFinalizarMesa;

public class RequisicaoController {

    private List<Mesa> mesaDisponivel;
    private TelaCadastrarCliente view;
    private TelaAnotarPedido view2;
    private Clientes clientes;
    private Mesas mesas;
    private final Requisicao requisicao;
    private Itens itens;
    private Requisicoes requisicoes;
    private TelaFinalizarMesa view3;

    public RequisicaoController(Requisicao requisicao, List<Mesa> mesaDisponivel) {
        this.requisicao = requisicao;
        this.mesaDisponivel = mesaDisponivel;
        this.mesas = Mesas.getInstance();
        this.clientes = Clientes.getInstance();
        this.requisicoes = Requisicoes.getInstance();

        alocarMesa();
    }

    public RequisicaoController(Requisicao requisicao) {
        this.requisicao = requisicao;
        this.view = new TelaCadastrarCliente();
        this.view2 = new TelaAnotarPedido();
    

        this.mesas = Mesas.getInstance();
        this.clientes = Clientes.getInstance();
        this.itens = Itens.getInstance();
        this.requisicoes = Requisicoes.getInstance();

       addPedido();
    }
    
    private void alocarMesa() {

        clientes.addCliente(requisicao.getCliente());
        int numPessoas = requisicao.getQuantidade();

        Optional<Mesa> mesaOpt = mesas.getMesas().stream()
                .filter(mesa -> !mesa.isOcupada() && mesa.getCapacidade() >= numPessoas)
                .findFirst();

        int id = mesaOpt.get().getId();
        Mesa mesa = mesas.buscarMesaPorId(id);
        mesa.setOcupada(true);
        mesas.altera(mesa, id);
        requisicao.setMesa(mesa);
        requisicoes.addRequisicao(requisicao);

        JOptionPane.showMessageDialog(view, "Mesa " + mesa.getId() + " alocada com sucesso para o cliente: " + requisicao.getCliente().getNome());
    }

    private void addPedido() {

        try {
            int quantidade = 2; //Integer.parseInt(view2.getTxtQntItens().getText());
            if (quantidade <= 0) {
                throw new NumberFormatException("A quantidade deve ser maior que zero.");
            }

            String nome = view2.getTxtNomeItem().getText();

            System.out.println(nome);
            ItemMenu item = itens.buscarPorNome(nome);
            
            System.out.println(item);

            Pedido pedido = new Pedido(item, quantidade);

            requisicao.getConta().addPedidos(pedido);
            
            requisicoes.altera(requisicao, requisicao.getCliente().getNome());
            JOptionPane.showMessageDialog(view2, "Pedido salvo com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view2, "Quantidade inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
