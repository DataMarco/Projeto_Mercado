package model;

public class Venda {
    private int quantidade;
    private Produto produto;
    private double valorDado;
    private double troco;
    private double valorTotal;
    private int idvenda;

    public Venda(int quantidade, Produto produto, double valorDado, double troco, double valorTotal, int idvenda){
        this.quantidade = quantidade;
        this.produto = produto;
        this.valorDado = valorDado;
        this.troco = troco;
        this.valorTotal = valorTotal;
        this.idvenda = idvenda;
    }

    public String toString(){
        return "Quantidade: "+quantidade+
                " Produto: "+produto+
                " Total pago: "+valorDado+
                " Troco: "+troco+
                " Valor: "+valorTotal+
                " ID: "+idvenda;
    }
}
