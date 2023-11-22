import java.util.Arrays;
import java.util.Scanner;

public class AgendaRestaurante {

    static final int MAX_TAREFAS = 50;

    static class Agenda {
        int chave;
        String dataCompromisso;
        String descricaoCompromisso;
    }

    public static void main(String[] args) {
        Agenda[] lista = new Agenda[MAX_TAREFAS];
        int chave = 1;
        int tamanho = 0;
        int escolha;
        int indice;
        int acao;
        int app = 1;
        int comprimento_agenda;
        String data;
        String novaData;
        String descricao;
        String novaDescricao;

        Scanner scanner = new Scanner(System.in);

        while (app == 1) {
            System.out.println("\nMenu:");
            System.out.println("1. Verificar Compromisso");
            System.out.println("2. Buscar Compromisso");
            System.out.println("3. Inserir Novo Compromisso");
            System.out.println("4. Mostrar Numero de compromissos");
            System.out.println("5. Editar/Remover Compromisso");
            System.out.println("6. Reinicializar Agenda");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    if (tamanho == 0) {
                        System.out.println("Você não possui compromissos na sua agenda.");
                    } else {
                        exibirElementos(lista, tamanho);
                    }
                    limparTela();
                    break;
                case 2:
                    if (tamanho == 0) {
                        System.out.println("Você não possui compromissos na sua agenda.");
                    } else {
                        System.out.print("Digite a chave a ser consultado: ");
                        indice = scanner.nextInt();
                        System.out.println("Elemento " + indice + ":");
                        consultarElemento(lista, indice);
                    }
                    limparTela();
                    break;
                case 3:
                    novaDescricao = obterDescricaoCompromisso();
                    novaData = criarTimestamp();
                    adicionarTarefa(lista, tamanho, chave, novaData, novaDescricao);
                    Arrays.sort(lista, 0, tamanho, (a, b) -> a.dataCompromisso.compareTo(b.dataCompromisso));
                    limparTela();
                    break;
                case 4:
                    comprimento_agenda = comprimentoAgenda(lista, tamanho);
                    System.out.println("Você tem " + comprimento_agenda + " compromissos em sua agenda.");
                    limparTela();
                    break;
                case 5:
                    exibirElementos(lista, tamanho);
                    System.out.print("Digite a chave do compromisso: ");
                    chave = scanner.nextInt();
                    System.out.println("1. Editar");
                    System.out.println("2. Remover");
                    acao = scanner.nextInt();
                    switch (acao) {
                        case 1:
                            novaDescricao = obterDescricaoCompromisso();
                            novaData = criarTimestamp();
                            editarCompromisso(lista, chave, novaDescricao, novaData);
                            Arrays.sort(lista, 0, tamanho, (a, b) -> a.dataCompromisso.compareTo(b.dataCompromisso));
                            System.out.println("Compromisso Editado.");
                            break;
                        case 2:
                            removerCompromisso(lista, tamanho, chave);
                            Arrays.sort(lista, 0, tamanho, (a, b) -> a.dataCompromisso.compareTo(b.dataCompromisso));
                            System.out.println("Compromisso Removido.");
                            break;
                    }
                    limparTela();
                    break;
                case 6:
                    if (tamanho == 0) {
                        System.out.println("A lista está vazia.");
                    } else {
                        limparAgenda(lista, tamanho);
                    }
                    limparTela();
                    break;
                case 0:
                    System.out.println("Encerrando o programa.");
                    app = 0;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    limparTela();
            }
        }
    }

    static void exibirElementos(Agenda[] lista, int tamanho) {
        for (int i = 0; i < tamanho; i++) {
            System.out.println("Chave: " + lista[i].chave +
                    ", Data: " + lista[i].dataCompromisso +
                    ", Descrição: " + lista[i].descricaoCompromisso);
        }
    }

    static void adicionarTarefa(Agenda[] lista, int tamanho, int chave, String novaData, String novaDescricao) {
        if (tamanho >= MAX_TAREFAS) {
            System.out.println("A lista de tarefas está cheia. Não é possível adicionar mais tarefas.");
            return;
        }

        lista[tamanho] = new Agenda();
        lista[tamanho].chave = chave;
        lista[tamanho].dataCompromisso = novaData;
        lista[tamanho].descricaoCompromisso = novaDescricao;
        tamanho++;
    }

    static void editarCompromisso(Agenda[] lista, int chave, String descricao, String data) {
        for (int i = 0; i < MAX_TAREFAS; i++) {
            if (lista[i].chave == chave) {
                lista[i].dataCompromisso = data;
                lista[i].descricaoCompromisso = descricao;
                break;
            }
        }
    }

    static void removerCompromisso(Agenda[] lista, int tamanho, int chave) {
        int encontrado = -1;
        for (int i = 0; i < tamanho; i++) {
            if (lista[i].chave == chave) {
                encontrado = i;
                break;
            }
        }

        if (encontrado != -1) {
            for (int j = encontrado; j < tamanho - 1; j++) {
                lista[j] = lista[j + 1];
            }
            tamanho--;
        } else {
            System.out.println("Chave não encontrada. Nenhum compromisso foi removido.");
        }
    }

    static void consultarElemento(Agenda[] lista, int chave) {
        for (int i = 0; i < MAX_TAREFAS; i++) {
            if (lista[i].chave == chave) {
                System.out.println("Chave: " + lista[i].chave +
                        ", Data: " + lista[i].dataCompromisso +
                        ", Descrição: " + lista[i].descricaoCompromisso);
                return;
            }
        }
        System.out.println("Chave não encontrada. Nenhum compromisso foi encontrado.");
    }

    static int comprimentoAgenda(Agenda[] lista, int tamanho) {
        int contador = 0;
        for (int i = 0; i < tamanho; i++) {
            if (lista[i].chave != -1) {
                contador++;
            }
