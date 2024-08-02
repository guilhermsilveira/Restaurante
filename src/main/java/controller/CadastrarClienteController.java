package controller;

import dao.Clientes;
import dao.FilaDeEspera;
import dao.Mesas;
import exception.NumeroInvalidoException;
import exception.CapacidadeInsuficienteException;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import model.Cliente;
import model.Mesa;
import model.Requisicao;
import view.TelaCadastrarCliente;

/**
 *
 * @author Guilh
 */
public class CadastrarClienteController {

    private TelaCadastrarCliente view;
    private Clientes clientes;
    private FilaDeEspera filaDeEspera;
    private Mesas mesas;

    public CadastrarClienteController() {
        this.view = new TelaCadastrarCliente();
        this.clientes = Clientes.getInstance();
        this.filaDeEspera = FilaDeEspera.getInstance();
        this.mesas = Mesas.getInstance();

        this.view.getBtnSalvarCliente().addActionListener((e) -> {
            try {
                addCliente();
            } catch (NumeroInvalidoException | CapacidadeInsuficienteException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.view.setVisible(true);
        view.setLocationRelativeTo(null);
    }

    public void addCliente() throws NumeroInvalidoException, CapacidadeInsuficienteException {
        String nome = view.getTxtNomeCliente().getText();
        int capacidade;
        try {
            capacidade = Integer.parseInt(view.getTxtQntCliente().getText().trim());
        } catch (NumberFormatException ex) {
            throw new NumeroInvalidoException("A quantidade de pessoas deve ser um número válido.");
        }

        Cliente cliente = new Cliente(nome, capacidade);

        gerarRequisicao(cliente);
    }

    private List<Mesa> buscarMesaDisponivel(int quantidade) throws CapacidadeInsuficienteException {
        List<Mesa> mesasDisponiveis = mesas.getMesas().stream()
                .filter(mesa -> !mesa.isOcupada() && mesa.getCapacidade() >= quantidade)
                .collect(Collectors.toList());
        
        return mesasDisponiveis;
    }

    public void gerarRequisicao(Cliente cliente) throws CapacidadeInsuficienteException {
        int numPessoas = cliente.getQuantidade();
        Requisicao requisicao = new Requisicao(cliente, numPessoas);

        List<Mesa> mesaDisponivel = buscarMesaDisponivel(numPessoas);

        if (!mesaDisponivel.isEmpty()) {
            RequisicaoController requisicaoController = new RequisicaoController(requisicao, mesaDisponivel);
        } else {
            clientes.addCliente(cliente);
            filaDeEspera.addAFila(requisicao);
            JOptionPane.showMessageDialog(view, "Cliente adicionado à fila de espera");
        }
    }
}
