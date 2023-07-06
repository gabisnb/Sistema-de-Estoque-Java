
package dcc025.controleestoque.view;

//Gabriela Singulani Marques

import dcc025.controleestoque.controller.AdicionarProduto;
import dcc025.controleestoque.controller.DuplicarProduto;
import dcc025.controleestoque.controller.GerenciaProdutos;
import dcc025.controleestoque.controller.RemoverProduto;
import dcc025.controleestoque.exceptions.NomeException;
import dcc025.controleestoque.exceptions.ProdutoException;
import dcc025.controleestoque.exceptions.QuantidadeException;
import dcc025.controleestoque.exceptions.ValorException;
import dcc025.controleestoque.models.Produto;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class Tela {
    
    private JFrame tela;
    private final int WIDTH = 750;
    private final int HEIGHT = 300;
    private final int V_GAP = 10;
    private final int H_GAP = 5;
    
    private JTextField tfNome;
    private JTextField tfValor;
    private JTextField tfQuantidade;
    private JTextField tfDescricao;
    
    private JTable tabela;
    private List<Produto> produtos;
    
    private String[] colunas = {
        "Nome",
        "Valor",
        "Quantidade",
        "Descrição"
    };

    public Tela() {
        this.produtos = new ArrayList();
        this.tabela = new JTable();
    }
    
    public void desenha(){
        tela = new JFrame("Controle de estoque");
        tela.addWindowListener(new GerenciaProdutos(this));
        
        tela.setSize(WIDTH, HEIGHT);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setVisible(true);
        tela.setLayout(new BorderLayout());
        tela.setLocationRelativeTo(null);

        desenhaFormulario();
        desenhaTabela();

        tela.pack();
    }
    
    public void desenhaFormulario(){
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Produto"));
        painel.setPreferredSize(new Dimension(WIDTH/3, HEIGHT));
        painel.setLayout(new BorderLayout());
        
        JPanel painelLabel = new JPanel();
        painelLabel.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
        painelLabel.add(new JLabel("Nome"));
        painelLabel.add(new JLabel("Valor"));
        painelLabel.add(new JLabel("Quantidade"));
        painelLabel.add(new JLabel("Descrição"));
        
        JPanel painelField = new JPanel();
        painelField.setLayout(new GridLayout(0,1, H_GAP,V_GAP));
        tfNome = new JTextField(20);
        tfValor = new JTextField(20);
        tfQuantidade = new JTextField(20);
        tfDescricao = new JTextField(20);

        painelField.add(tfNome);
        painelField.add(tfValor);
        painelField.add(tfQuantidade);
        painelField.add(tfDescricao);

        JPanel formulario = new JPanel();
        formulario.setLayout(new GridLayout(0, 2, 4, 2));
        formulario.add(painelLabel, BorderLayout.WEST);
        formulario.add(painelField);
        painel.add(formulario, BorderLayout.NORTH);
        
        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new AdicionarProduto(this));
        JButton btnRemover = new JButton("Remover");
        btnRemover.addActionListener(new RemoverProduto(this));
        
        JPanel botoes = new JPanel();
        botoes.setLayout(new GridLayout(0, 2, 1, 5));
        botoes.add(btnAdicionar);
        botoes.add(btnRemover);
        
        painel.add(botoes, BorderLayout.SOUTH);
        
        tela.getContentPane().add(painel, BorderLayout.CENTER);
    }
    
    public void desenhaTabela(){
        
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Estoque"));
        painel.setPreferredSize(new Dimension(WIDTH*2/3, HEIGHT));
        painel.setLayout(new BorderLayout());
        
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        this.tabela = new JTable(model);
        this.tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);
        
        JButton btnDuplicar = new JButton("Duplicar Produto");
        btnDuplicar.addActionListener(new DuplicarProduto(this));
        painel.add(btnDuplicar, BorderLayout.SOUTH);

        tela.getContentPane().add(painel, BorderLayout.EAST);
    }
    
    public void adicionarProduto(){
        try{
            String nome = tfNome.getText();
            double valor = Double.parseDouble(tfValor.getText());
            int quantidade = Integer.parseInt(tfQuantidade.getText());
            String descricao = tfDescricao.getText();

            produtos.add(new Produto(nome, valor, quantidade, descricao));
            tfNome.setText("");
            tfValor.setText("");
            tfQuantidade.setText("");
            tfDescricao.setText("");
            
            carregaProdutos();
        }
        catch(NomeException e){
            JOptionPane.showMessageDialog(null, "Insira o nome do produto!");
        }
        catch(ValorException e){
            JOptionPane.showMessageDialog(null, "Valor do produto inválido!");
        }
        catch(QuantidadeException e){
            JOptionPane.showMessageDialog(null, "Quantidade de produto inválido!");
        }
        catch(NumberFormatException | ProdutoException e){
            JOptionPane.showMessageDialog(null, "Formato de valor inválido!");
        }
    }
    
    public void removerProduto(){
        try{
            int index = tabela.getSelectedRow();
            
            produtos.remove(index);
            carregaProdutos();
        }
        catch(IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Selecione o produto que deseja remover!");
        }
    }
    
    public void duplicarProduto(){
        try{
            int index = tabela.getSelectedRow();
            
            Produto copia = produtos.get(index).duplica();
            produtos.add(index, copia);
            carregaProdutos();
        }
        catch(IndexOutOfBoundsException | NullPointerException e){
            JOptionPane.showMessageDialog(null, "Selecione o produto que deseja duplicar!");
        }
    }
    
    public void carregaProdutos(){
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        for(Produto p: produtos){
            Object[] linha = {p.getNome(), p.getValor(), p.getQuantidade(), p.getDescricao()};
            model.addRow(linha);
        }
        tabela.setModel(model);
    }
}
