import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {
  private List<Casa> casas;

  public Tabuleiro() {
    this.casas = new ArrayList<>();
  }

  public void adicionarCasa(Casa casa) {
    if (casa == null) {
      throw new IllegalArgumentException("A casa não pode ser nula!");
    }
    casas.add(casa);
  }

  public Casa localizarCasa(int posicao) {
    return casas.get(posicao % casas.size());
  }

  public int totalCasas() {
    return casas.size();
  }

  public Casa removerCasa(int index) {
    if (index < 0 || index >= casas.size()) {
      throw new IndexOutOfBoundsException("Índice inválido para remoção de casa.");
    }
    return casas.remove(index);
  }
}
