import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JogoTabuleiro {
  static Scanner scanner = new Scanner(System.in);
  static Tabuleiro tabuleiro = new Tabuleiro();
  static List<Jogador> jogadores = new ArrayList<>();
  static double salario = 5000.0;
  static int maxRodadas = 20;


  public static void cadastrarImoveis() {
	System.out.println("Cadastro de Imóveis (Digite 'sair' para finalizar):");
	while (true) {
	  if (tabuleiro.totalCasas >= 40) {
		System.out.println("Máximo de 40 imóveis atingido.");
		break;
	  }
	  System.out.print("Nome do imóvel: ");
	  String nome = scanner.nextLine();
	  if (nome.equalsIgnoreCase("sair")) break;

	  System.out.print("Tipo (Imóvel/Imposto/Restituição/Início): ");
	  String tipo = scanner.nextLine();
	  System.out.print("Preço de compra: ");
	  double precoCompra = scanner.nextDouble();
	  System.out.print("Preço de aluguel: ");
	  double precoAluguel = scanner.nextDouble();
	  scanner.nextLine();

	  tabuleiro.adicionarCasa(Casa casa);
	  System.out.printf("Imóvel cadastrado com sucesso!\n", nome);

	  if (tabuleiro.totalCasas >= 10) {
		System.out.println("Já é possível começar o jogo! Digite 'sair' para encerrar o cadastro.");
	  }
	}
  }


  public static void cadastrarJogadores() {
	System.out.println("Cadastro de Jogadores (Digite 'sair' para finalizar):");
	while (true) {
	  if (jogadores.size() >= 6) {
		System.out.println("Máximo de 6 jogadores atingido.");
		break;
	  }
	  System.out.print("Nome do jogador: ");
	  String nome = scanner.nextLine();
	  if (nome.equalsIgnoreCase("sair")) break;

	  jogadores.add(new Jogador(nome, salario));
	  System.out.printf("Jogador '%s' cadastrado com sucesso!\n", nome);

	  if (jogadores.size() >= 2) {
		System.out.println("Já é possível começar o jogo! Digite 'sair' para encerrar o cadastro.");
	  }
	}
  }

  public static void main(String[] args) {
	System.out.println("Bem-vindo ao jogo de tabuleiro!");

	// Cadastro de imóveis
	cadastrarImoveis();
	if (tabuleiro.totalCasas < 10) {
	  System.out.println("Erro: O jogo precisa de pelo menos 10 imóveis.");
	  return;
	}

	// Cadastro de jogadores
	cadastrarJogadores();
	if (jogadores.size() < 2) {
	  System.out.println("Erro: O jogo precisa de pelo menos 2 jogadores.");
	  return;
	}

	// Exibir tabuleiro
	System.out.println("\nTabuleiro configurado:");
	tabuleiro.exibirTabuleiro();

	// Jogadores cadastrados
	System.out.println("\nJogadores cadastrados:");
	for (Jogador jogador : jogadores) {
	  System.out.printf("Nome: %s | Saldo: %.2f\n", jogador.nome, jogador.saldo);
	}


  }

}
