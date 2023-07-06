
package dcc025.controleestoque.controller;

import dcc025.controleestoque.view.Tela;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DuplicarProduto implements ActionListener {
    
    private Tela tela;

    public DuplicarProduto(Tela tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.tela.duplicarProduto();
    }
}
