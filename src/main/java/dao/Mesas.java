package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Mesa;

public class Mesas extends AbstractDAO implements Serializable {

    private List<Mesa> mesas;
    private static Mesas instance;

    private final String localArquivo = "./src/main/java/data/mesas.txt";

    private Mesas() {
        this.mesas = new ArrayList<>();
        carregaMesas();
    }

    public static Mesas getInstance() {
        if (instance == null) {
            instance = new Mesas();
        }
        return instance;
    }

    public void addMesa(Mesa mesa) {
        this.mesas.add(mesa);
        grava();
    }

    public Mesa buscarMesaPorId(int id) {
        return mesas.stream()
                .filter(mesa -> mesa.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private void carregaMesas() {
        this.mesas = super.leitura(localArquivo);
    }

    private void grava() {
        super.grava(localArquivo, mesas);
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void excluirMesa(Mesa mesa) {
        mesas.remove(mesa);
        grava();

    }

    public boolean altera(Mesa mesaExistente, int id) {
        try {
            ArrayList<Mesa> listaTemp = new ArrayList<Mesa>();

            for (Iterator<Mesa> it = mesas.iterator(); it.hasNext();) {
                Mesa mesa = it.next();
                if (mesa.getId()!= id)
                    listaTemp.add(mesa);
                else
                    listaTemp.add(mesaExistente);
            }

            mesas.removeAll(mesas);
            mesas.addAll(listaTemp);
            grava();

            return true;

        } catch (Exception e) {
            return false;
        }
    }
        

 }
