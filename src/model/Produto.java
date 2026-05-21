package model;

public class Produto {
    private String nome;
    private double preco;
    private int estoque;
    private int id;

    public Produto(String nome, double preco, int estoque, int id){
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.id=id;
    }

    public String toString(){
        return "Nome: "+nome+
                " Preço: "+preco+
                " Estoque: "+estoque+
                " Id: "+id;
    }

    public String getNome(){
        return nome;
    }
    public double getPreco(){
        return preco;
    }
    public int getEstoque(){
        return estoque;
    }
    public int getId(){
        return id;
    }

    public void setNome(String novonome){
        nome = novonome;
    }
    public void setPreco(double novopreco){
        preco = novopreco;
    }
    public void setEstoque(int novoestoque){
        estoque = novoestoque;
    }
    public void setId(int novoid){
        id = novoid;
    }
}
