package service;
import model.Venda;
import model.Produto;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ProdutoService{
    private int id = 1;
    private int idvenda = 1;
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ArrayList<Venda> vendas = new ArrayList<>();
    public void cadastrar(String nome, double preco, int estoque){
        Produto produto = new Produto(nome, preco, estoque, id);
        produtos.add(produto);
        id++;
    }

    public void precoMaiorMenor(){
        Collections.sort(produtos, Comparator.comparingDouble(Produto::getPreco));
    }
    public void precoMenorMaior(){
        Collections.sort(produtos, Comparator.comparingDouble(Produto::getPreco).reversed());
    }
    public void compNome(){
        Collections.sort(produtos, Comparator.comparing(Produto::getNome));
    }
    public void compEstoque(){
        Collections.sort(produtos, Comparator.comparing(Produto::getEstoque));
    }
    public void compId(){
        Collections.sort(produtos, Comparator.comparing(Produto::getId));
    }

    public ArrayList<Venda> listarvendas(){
        return vendas;
    }
    public ArrayList<Produto> listarProdutos(){
        return produtos;
    }
    public void delete(int id){
        Produto produto = buscarPorId(id);
        if (produto!=null) {
            produtos.remove(produto);
        }
    }
    public Produto buscarPorId(int id){
        for (Produto produto : produtos) {
            if (produto.getId()==id) {
                return produto;
            }
        }
        return null;
    }

    public Produto buscarPorNome(String nome){
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        return null;
    }

    public double vender(Produto produto, int quantidade, double pagar){
        if (produto != null) {
            boolean validar = validar(produto, quantidade);
            if (validar) {
                double total = total(quantidade, produto);
                boolean validarPagamento = validarPagamento(total, pagar);
                if (validarPagamento) {
                    alterarestoque(produto, quantidade);
                    double troco = troco(total, pagar);
                    double totalPago = pagar - total;
                    Venda venda = new Venda(quantidade, produto, pagar, troco, totalPago, idvenda);
                    vendas.add(venda);
                    idvenda++;
                    return troco;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
    
    public boolean validar(Produto produto, int quantidade){
        if (produto.getEstoque()<quantidade) {
            return false;
        }
        else{
            return true;
        }
    }

    public void alterarestoque(Produto produto, int quantidade){
        int alterado = produto.getEstoque() - quantidade;
        attEstoque(produto, alterado);
    }

    public boolean validarPagamento(double total, double pagar){
        if (total < pagar) {
            return false;
        } else {
            return true;
        }
    }

    public double total(int quantidade, Produto produto){
        double total = quantidade*produto.getPreco(); 
        return total;
    }

    public double troco(double total, double pagar){
        double troco = pagar - total;
        return troco;
    }

    public void attEstoque(Produto produto, int novoEstoque){
        produto.setEstoque(novoEstoque);
    }
    public void attPreco(Produto produto, double novopreco){
        produto.setPreco(novopreco);
    }
    public void gerarLista(){
        try {
            FileWriter arq = new FileWriter("listaProdutos.txt");
            PrintWriter gerar = new PrintWriter(arq);
            for (Produto produto : produtos) {
                gerar.println(produto);
                gerar.println("\n============");
            }
            System.out.println("Relatório gerado com sucesso!");
            gerar.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void gerarRelatorio(){
        try {
            FileWriter arq = new FileWriter("relatorio.txt");
            PrintWriter gravar = new PrintWriter(arq);
            gravar.println("Relatório de Vendas: ");
            for (Venda produto : vendas) {
                gravar.println(produto);
                gravar.println("\n=====================");
            }
            System.out.println("Relatório gerado com sucesso!");
            gravar.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
