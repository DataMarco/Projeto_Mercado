package ui;
import java.util.*;
import model.Produto;
import model.Venda;
import service.ProdutoService;
public class Menu{ 
    public static void main(String[] args){
        Scanner leitor = new Scanner(System.in);
        ProdutoService service = new ProdutoService();
        boolean menu = true;
            while (menu) {
                ArrayList<Produto> lista = service.listarProdutos();
                System.out.println("Bem-vindo! \n1 - listar produtos \n2 - criar produto \n3 - Buscar por ID \n4 - Vender Produto \n5 - Histórico de Vendas \n6 -Sair Digite a sua escolha: ");
                int escolha = leitor.nextInt();
                leitor.nextLine();
                if (escolha == 1) {
                    for (Produto produto : lista) {
                        System.out.println(produto.toString());
                    }
                    System.out.println("Ordenar a lista como: \n1 - Preço (crescente) \n2 - Preço (Decrescente) \n3 - Estoque \n4 - Nome \n5 - ID");
                    int escolha4 = leitor.nextInt();
                    if (escolha4 == 1) {
                        service.precoMaiorMenor();
                        for (Produto produto : lista) {
                            System.out.println(produto.toString());
                        }
                    }
                    else if (escolha4 == 2) {
                        service.precoMenorMaior();
                        for (Produto produto : lista) {
                            System.out.println(produto.toString());
                        }
                    }
                    else if (escolha4 == 3) {
                        service.compEstoque();
                        for (Produto produto : lista) {
                            System.out.println(produto.toString());
                        }
                    }
                    else if (escolha4 == 4) {
                        service.compNome();
                        for (Produto produto : lista) {
                            System.out.println(produto.toString());
                        }
                    }
                    else if (escolha4 == 5) {
                        service.compId();
                        for (Produto produto : lista) {
                            System.out.println(produto.toString());
                        }
                    } else {
                        System.out.println("Digite uma opção válida.");
                    }
                    System.out.println("Deseja gerar um relatório?");
                    String alternativa1 = leitor.nextLine();
                    if ("s".equalsIgnoreCase(alternativa1)) {
                        service.gerarRelatorio();
                    }
                }
                else if (escolha == 2){
                    System.out.println("Nome do prduto: ");
                    String nome = leitor.nextLine();
                    if (nome.length()==0) {
                        System.out.println("O nome nao pode estar vazio.");
                    }
                    System.out.println("Preço: ");
                    double preco = leitor.nextDouble();
                    if (preco < 0) {
                        System.out.println("O preço não pode ter valor negativo.");
                    }
                    leitor.nextLine();
                    System.out.println("Estoque: ");
                    int estoque = leitor.nextInt();
                    if (estoque <= 0) {
                        System.out.println("O estoque não pode ser 0.");
                    }
                    leitor.nextLine();
                    service.cadastrar(nome, preco, estoque);
                }
                else if (escolha == 3) {
                    System.out.println("Digite o id: ");
                    int id = leitor.nextInt();
                    Produto produto = service.buscarPorId(id);
                    System.out.println("1 - Deletar \n2 - Atualizar estoque \n3 - Atualizar preço");
                    int escolha2 = leitor.nextInt();
                    if (escolha2 == 1) {
                        service.delete(produto.getId());
                    }
                    else if (escolha2 == 2) {
                        System.out.println("Estoque atual: "+produto.getEstoque());
                        System.out.println("Novo estoque: ");
                        int novoEstoque = leitor.nextInt();
                        service.attEstoque(produto, novoEstoque);
                    }
                    else if (escolha2 == 3) {
                        System.out.println("Preço atual: "+produto.getPreco());
                        System.out.println("Novo preço: ");
                        double novopreco = leitor.nextDouble();
                        service.attPreco(produto, novopreco);
                    }
                }
                else if (escolha == 4) {
                    System.out.println("buscar por: 1 - nome \n2 - ID");
                    int escolha3 = leitor.nextInt();
                    leitor.nextLine();
                    if (escolha3 == 1) {
                        System.out.println("Digite o nome: ");
                        String nome = leitor.nextLine();
                        Produto produto = service.buscarPorNome(nome);
                        if (produto != null) {
                            System.out.println("Digite a quantidade: ");
                            int quantidade = leitor.nextInt();
                            System.out.println("Valor pago: ");
                            double pagar = leitor.nextDouble();
                            leitor.nextLine();
                            double troco = service.vender(produto, quantidade, pagar);
                            System.out.println(troco);
                        }
                    }
                    else if (escolha3 == 2) {
                        System.out.println("Digite o Id: ");
                        int id = leitor.nextInt();
                        Produto produto = service.buscarPorId(id);
                        if (produto != null) {
                            System.out.println("Digite a quantidade: ");
                            int quantidade = leitor.nextInt();
                            System.out.println("Valor pago: ");
                            double pagar = leitor.nextDouble();
                            leitor.nextLine();
                            double troco = service.vender(produto, quantidade, pagar);
                            System.out.println(troco);
                        }
                    }
                }
                else if (escolha == 5) {
                    System.out.println("Histórico de vendas: ");
                    ArrayList<Venda> vendas = service.listarvendas();
                    for (Venda venda : vendas) {
                        System.out.println(venda.toString());
                    }
                    leitor.nextLine();
                    System.out.println("Gerar relatório? Y/N");
                    String alternativa = leitor.nextLine();
                    if ("y".equalsIgnoreCase(alternativa)) {
                        System.out.println("Gerando relatório.");
                        service.gerarRelatorio();
                    } else if ("n".equalsIgnoreCase(alternativa)) {
                        System.out.println("Voltando...");
                    }
                }
                else if (escolha == 6) {
                    menu = false;
                    leitor.close();
                }
                else {
                    System.out.println("Opção inválida.");
                }
            }
        }
    }
