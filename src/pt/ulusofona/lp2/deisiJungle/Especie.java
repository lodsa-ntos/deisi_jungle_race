package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.especieFilho.*;

public abstract class Especie {

    protected int energiaInicial;
    protected int consumoEnergia;
    protected int ganhoEnergiaDescanso;
    protected int velocidadeMaxima;
    protected int velocidadeMinima;
    protected String id;
    protected String nome;
    protected String imagem;


    public static Especie identificarEspecie(String id) {
        switch (id) {
            case "E" -> new Elefante();
            case "L" -> new Leao();
            case "T" -> new Tartaruga();
            case "P" -> new Passaro();
            case "Z" -> new Tarzan();
            case "U" -> new Unicornio();
            default -> {
            }
        }
        return null;
    }

    public abstract String getId();

    public abstract void setId(String id);

    public abstract String getNome();

    public abstract void setNome(String nome);

    public abstract String getImagem();

    public abstract void setImagem(String imagem);

    public abstract int getEnergiaInicial();

    public abstract void setEnergiaInicial(int energiaInicial);

    public abstract int getConsumoEnergia();

    public abstract void setConsumoEnergia(int consumoEnergia);

    public abstract int getGanhoEnergiaDescanso();

    public abstract void setGanhoEnergiaDescanso(int ganhoEnergiaDescanso);

    public abstract int getVelocidadeMaxima();

    public abstract void setVelocidadeMaxima(int velocidadeMaxima);

    public abstract int getVelocidadeMinima();

    public abstract void setVelocidadeMinima(int velocidadeMinima);

    public abstract String toString();
}