public class Prisao extends Casa {
  private int rodadasPreso;

  public Prisao(String nomeImovel, int rodadasPreso) {
	super(nomeImovel, "prisão", 0, 0);
	this.rodadasPreso = rodadasPreso;
  }

  public int getRodadasPreso() {
	return rodadasPreso;
  }

  @Override
  public String toString() {
	return String.format("Casa: %s | Tipo: %s | Rodadas Preso: %d", nomeImovel, tipo, rodadasPreso);
  }
}
