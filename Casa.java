public class Casa {
  protected String nomeImovel;
  protected String tipo;
  protected double preco;
  protected double aluguel;

  public Casa(String nomeImovel, String tipo, double preco, double aluguel) {
    this.nomeImovel = nomeImovel;
    this.tipo = tipo;
    this.preco = preco;
    this.aluguel = aluguel;

  }

  public String getNomeImovel() {

    return nomeImovel;
  }

  public String getTipo() {
    return tipo;
  }

  public void setNomeImovel(String nomeImovel) {
    this.nomeImovel = nomeImovel;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public void setPreco(double preco) {
    this.preco = preco;
  }

  public void setAluguel(double aluguel) {
    this.aluguel = aluguel;
  }


  @Override
  public String toString() {
    return String.format("Casa: %s | Tipo: %s", nomeImovel, tipo);
  }
}
