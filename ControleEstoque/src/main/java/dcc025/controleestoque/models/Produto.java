
package dcc025.controleestoque.models;

//Gabriela Singulani Marques

import dcc025.controleestoque.exceptions.NomeException;
import dcc025.controleestoque.exceptions.ProdutoException;
import dcc025.controleestoque.exceptions.QuantidadeException;
import dcc025.controleestoque.exceptions.ValorException;

public class Produto {
    
    private String nome;
    private double valor;
    private int quantidade;
    private String descricao;

    public Produto(String nome, double valor, int quantidade, String descricao) throws ProdutoException{
        if(nome.isBlank())
            throw new NomeException();
        this.nome = nome;
        if(valor<0)
            throw new ValorException();
        this.valor = valor;
        if(quantidade<=0)
            throw new QuantidadeException();
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Produto duplica(){
        try{
            return new Produto(nome, valor, quantidade, descricao);
        }
        catch(Exception e){}
        return null;
    }
    
}
