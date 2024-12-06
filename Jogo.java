import java.util.*;

public class Jogo {
  private static final Scanner scanner = new Scanner(System.in);
  private static final Random dado = new Random();
  private Tabuleiro tabuleiro;
  private List<Jogador> jogadores;
  private double salario;
  private int maxRodadas;

  public Jogo() {
	this.tabuleiro = new Tabuleiro();
	this.jogadores = new ArrayList<>();
  }

  public void configurarJogo() {
	configurarImoveis();
	configurarJogadores();
	configurarRegras();
  }

  private void configurarImoveis() {
	tabuleiro.adicionarCasa(new Casa("Início", "início", 0, 0));
	tabuleiro.adicionarCasa(new Casa("Casa do Bosque", "imóvel", 200000, 1100));
	tabuleiro.adicionarCasa(new Casa("Apartamento Central", "imóvel", 350000, 1800));
	tabuleiro.adicionarCasa(new Casa("Imposto", "imposto", 0, 0));
	tabuleiro.adicionarCasa(new Prisao("Prisão", 2)); // Prisão com 2 rodadas
  }

  private void configurarJogadores() {
	System.out.print("Quantos jogadores irão participar (mínimo 2, máximo 6)? ");
	int qtd = Integer.parseInt(scanner.nextLine());
	while (qtd < 2 || qtd > 6) {
	  System.out.print("Número inválido. Digite entre 2 e 6: ");
	  qtd = Integer.parseInt(scanner.nextLine());
	}
	for (int i = 0; i < qtd; i++) {
	  System.out.printf("Nome do jogador %d: ", i + 1);
	  String nome = scanner.nextLine();
	  jogadores.add(new Jogador(nome, 5000.00));
	}
  }

  public void editarCasa() {
	System.out.println("Editar Casa: ");
	listarCasas();
	System.out.print("Digite o número da casa que deseja editar: ");
	int index = Integer.parseInt(scanner.nextLine()) - 1;

	if (index < 0 || index >= tabuleiro.totalCasas()) {
	  System.out.println("Número inválido!");
	  return;
	}

	Casa casa = tabuleiro.localizarCasa(index);
	System.out.printf("Editando casa: %s\n", casa.getNomeImovel());
	System.out.print("Novo nome: ");
	String novoNome = scanner.nextLine();
	System.out.print("Novo tipo (início, imóvel, imposto, prisão): ");
	String novoTipo = scanner.nextLine();
	System.out.print("Novo preço: ");
	double novoPreco = Double.parseDouble(scanner.nextLine());
	System.out.print("Novo aluguel: ");
	double novoAluguel = Double.parseDouble(scanner.nextLine());

	casa.nomeImovel = novoNome;
	casa.tipo = novoTipo;
	casa.preco = novoPreco;
	casa.aluguel = novoAluguel;

	System.out.println("Casa atualizada com sucesso!");
  }

  
  public void excluirCasa() {
	System.out.println("Excluir Casa: ");
	listarCasas();
	System.out.print("Digite o número da casa que deseja excluir: ");
	int index = Integer.parseInt(scanner.nextLine()) - 1;

	if (index < 0 || index >= tabuleiro.totalCasas()) {
	  System.out.println("Número inválido!");
	  return;
	}

	Casa removida = tabuleiro.removerCasa(index);
	System.out.printf("Casa %s foi removida com sucesso!\n", removida.getNomeImovel());
  }


  public void editarJogador() {
	System.out.println("Editar Jogador: ");
	listarJogadores();
	System.out.print("Digite o número do jogador que deseja editar: ");
	int index = Integer.parseInt(scanner.nextLine()) - 1;

	if (index < 0 || index >= jogadores.size()) {
	  System.out.println("Número inválido!");
	  return;
	}

	Jogador jogador = jogadores.get(index);
	System.out.printf("Editando jogador: %s\n", jogador.nome);
	System.out.print("Novo nome: ");
	String novoNome = scanner.nextLine();
	System.out.print("Novo saldo: ");
	double novoSaldo = Double.parseDouble(scanner.nextLine());

	jogador.nome = novoNome;
	jogador.saldo = novoSaldo;

	System.out.println("Jogador atualizado com sucesso!");
  }


  public void excluirJogador() {
	System.out.println("Excluir Jogador: ");
	listarJogadores();
	System.out.print("Digite o número do jogador que deseja excluir: ");
	int index = Integer.parseInt(scanner.nextLine()) - 1;

	if (index < 0 || index >= jogadores.size()) {
	  System.out.println("Número inválido!");
	  return;
	}

	Jogador removido = jogadores.remove(index);
	System.out.printf("Jogador %s foi removido com sucesso!\n", removido.nome);
  }

  // Listar jogadores
  private void listarJogadores() {
	System.out.println("\nJogadores cadastrados:");
	for (int i = 0; i < jogadores.size(); i++) {
	  System.out.printf("%d. %s\n", i + 1, jogadores.get(i).nome);
	}
  }

  // Listar casas
  private void listarCasas() {
	System.out.println("\nCasas cadastradas no tabuleiro:");
	for (int i = 0; i < tabuleiro.totalCasas(); i++) {
	  System.out.printf("%d. %s\n", i + 1, tabuleiro.localizarCasa(i));
	}
  }

  private void configurarRegras() {
	System.out.print("Defina o salário que os jogadores receberão ao passar pelo início: ");
	salario = Double.parseDouble(scanner.nextLine());

	System.out.print("Defina o número máximo de rodadas: ");
	maxRodadas = Integer.parseInt(scanner.nextLine());
  }

  public void iniciarJogo() {
	int rodada = 1;

	while (rodada <= maxRodadas) {
	  System.out.printf("\n--- Rodada %d ---\n", rodada);

	  for (Jogador jogador : jogadores) {
		if (jogador.rodadasPreso > 0) {
		  jogador.rodadasPreso--;
		  System.out.printf("%s está preso e não pode jogar nesta rodada. Rodadas restantes: %d.\n",
				  jogador.nome, jogador.rodadasPreso);
		  continue;
		}

		int movimento = dado.nextInt(6) + 1;
		jogador.mover(movimento, tabuleiro.totalCasas());
		Casa casaAtual = tabuleiro.localizarCasa(jogador.posicao);
		System.out.printf("%s parou na casa: %s (%s)\n", jogador.nome, casaAtual.getNomeImovel(), casaAtual.getTipo());

		processarInteracao(jogador, casaAtual);
	  }
	  rodada++;
	}
	encerrarJogo();
  }

  private void processarInteracao(Jogador jogador, Casa casa) {
	switch (casa.getTipo()) {
	  case "início":
		jogador.saldo += salario;
		System.out.printf("Você passou pelo início e recebeu R$%.2f!\n", salario);
		break;
	  case "prisão":
		if (casa instanceof Prisao) {
		  Prisao prisao = (Prisao) casa;
		  jogador.rodadasPreso = prisao.getRodadasPreso();
		  System.out.printf("%s foi preso e ficará %d rodadas sem jogar.\n", jogador.nome, prisao.getRodadasPreso());
		}
		break;
	}
  }

  private void encerrarJogo() {
	System.out.println("\n--- Fim do Jogo ---");
	jogadores.sort(Comparator.comparingDouble(j -> -j.saldo));
	for (Jogador jogador : jogadores) {
	  System.out.println(jogador);
	}
  }
}
