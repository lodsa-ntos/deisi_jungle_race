package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.especieFilho.*;

public abstract class Especie {

    protected String id;
    protected String nome;
    protected String imagem;
    protected String tipoAlimentacaoDaEspecie;
    protected int energiaAtual;
    protected int consumoEnergia;
    protected int ganhoEnergiaDescanso;
    protected int velocidadeMinima;
    protected int velocidadeMaxima;

    public Especie() {
    }

    /**
     * Identificar Especies
     */
    protected static Especie identificarEspecie(String id) {
        switch (id) {
            case "E" -> {
                return new Elefante();
            }
            case "L" -> {
                return new Leao();
            }
            case "T" -> {
                return new Tartaruga();
            }
            case "P" -> {
                return new Passaro();
            }
            case "Z" -> {
                return new Tarzan();
            }
            case "U" -> {
                return new Unicornio();
            }
            case "G" -> {
                return new Girafa();
            }
            default -> {
                return null;
            }
        }
    }

    public abstract String getId();

    public abstract void setId(String id);

    public abstract String getNome();

    public abstract void setNome(String nome);

    public abstract String getImagem();

    public abstract void setImagem(String imagem);

    public abstract int getEnergiaAtual();

    public abstract void definirEnergiaAtual(int energiaAtual);

    public abstract int getConsumoEnergia();

    public abstract void definirConsumoEnergia(int consumoEnergia);

    public abstract int getGanhoEnergiaDescanso();

    public abstract void definirGanhoEnergiaDescanso(int ganhoEnergiaDescanso);

    public abstract int getVelocidadeMaxima();

    public abstract void definirVelocidadeMaxima(int velocidadeMaxima);

    public abstract int getVelocidadeMinima();

    public abstract void definirVelocidadeMinima(int velocidadeMinima);

    public abstract String getTipoAlimentacaoDaEspecie();

    public abstract String definirTipoAlimentacaoDaEspecie(String tipoAlimentacaoDaEspecie);

    public abstract String toString();
}