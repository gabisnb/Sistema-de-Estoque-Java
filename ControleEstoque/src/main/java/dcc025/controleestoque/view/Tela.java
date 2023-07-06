
package dcc025.controleestoque.view;

//Gabriela Singulani Marques

import controller.GerenciaProdutos;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Tela {
    
    private JFrame tela;
    private final int WIDTH = 500;
    private final int HEIGHT = 200;
    private final int V_GAP = 10;
    private final int H_GAP = 5;
    
    private JTextField tfNome;
    private JTextField tfValor;
    private JTextField tfQuantidade;
    private JTextField tfdescricao;
    
    private JTable tabela;

    public Tela() {
        
    }
    
    public void desenha(){
        tela = new JFrame("Controle de estoque");
        tela.addWindowListener(new GerenciaProdutos(this));
        
    }
}
