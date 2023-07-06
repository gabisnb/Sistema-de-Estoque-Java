
package dcc025.controleestoque.controller;

import dcc025.controleestoque.view.Tela;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoverProduto implements ActionListener {
    
    private Tela tela;

    public RemoverProduto(Tela tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.tela.removerProduto();
    }
}
