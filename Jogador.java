import java.util.ArrayList;
import java.util.List;

public class Jogador {
  public String nome;
  public double saldo;
  public int posicao;
  public int rodadasPreso; // Contador de rodadas que o jogador está preso
  public List<Casa> propriedades;

  public Jogador(String nome, double saldoInicial) {
    this.nome = nome;
    this.saldo = saldoInicial;
    this.posicao = 0;
    this.rodadasPreso = 0;
    this.propriedades = new ArrayList<>();
  }

  public void mover(int passos, int totalCasas) {
    posicao = (posicao + passos) % totalCasas;
  }

  @Override
  public String toString() {
    return String.format("Jogador: %s | Saldo: R$%.2f | Posição: %d | Rodadas Preso: %d | Propriedades: %d",
            nome, saldo, posicao, rodadasPreso, propriedades.size());
  }
}
