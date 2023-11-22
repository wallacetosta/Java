import java.util.ArrayList;
import java.util.Scanner;

// Classe que representa um item do menu
class ItemMenu {
    private String nome;
    private double preco;

    public ItemMenu(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}

// Classe que representa o menu do restaurante
class Menu {
    private ArrayList<ItemMenu> itens;

    public Menu() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(String nome, double preco) {
        ItemMenu item = new ItemMenu(nome, preco);
        itens.add(item);
    }

    public void exibirMenu() {
        System.out.println("Menu do Restaurante:");
        for (ItemMenu item : itens) {
            System.out.println(item.getNome() + " - R$ " + item.getPreco());
        }
        System.out.println();
    }

    public ItemMenu getItem(int indice) {
        return itens.get(indice);
    }
}

// Classe que representa o pedido do cliente
class Pedido {
    private ArrayList<ItemMenu> itensPedidos;

    public Pedido() {
        this.itensPedidos = new ArrayList<>();
    }

    public void adicionarItem(ItemMenu item) {
        itensPedidos.add(item);
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemMenu item : itensPedidos) {
            total += item.getPreco();
        }
        return total;
    }

    public void exibirRecibo() {
        System.out.println("Itens pedidos:");
        for (ItemMenu item : itensPedidos) {
            System.out.println(item.getNome() + " - R$ " + item.getPreco());
        }
        System.out.println("Total: R$ " + calcularTotal());
    }
}

// Classe principal
public class RestauranteApp {
    public static void main(String[] args) {
        // Criar o menu do restaurante
        Menu menu = new Menu();
        menu.adicionarItem("Hamburguer", 10.0);
        menu.adicionarItem("Pizza", 15.0);
        menu.adicionarItem("Salada", 8.0);

        // Exibir o menu
        menu.exibirMenu();

        // Fazer um pedido
        Pedido pedido = new Pedido();
        Scanner scanner = new Scanner(System.in);

        int escolha;
        do {
            System.out.println("Digite o número do item que deseja pedir (ou 0 para finalizar):");
            escolha = scanner.nextInt();

            if (escolha > 0 && escolha <= menu.getItemCount()) {
                ItemMenu itemEscolhido = menu.getItem(escolha - 1);
                pedido.adicionarItem(itemEscolhido);
                System.out.println(itemEscolhido.getNome() + " adicionado ao pedido.");
            } else if (escolha != 0) {
                System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != 0);

        // Exibir recibo
        System.out.println("\nRecibo do Pedido:");
        pedido.exibirRecibo();

        scanner.close();
    }
}
