package pt.ulusofona.lp2.deisiJungle;

public abstract class Especie {

    protected int energiaInicial;
    protected int consumoEnergia;
    protected int ganhoEnergiaDescanso;
    protected int velocidadeMaxima;
    protected int velocidadeMinima;
    protected String id;
    protected String nome;
    protected String imagem;

    public Especie(String id, String nome, String imagem, int energiaInicial, int consumoEnergia,
                   int ganhoEnergiaDescanso, int velocidadeMinima, int velocidadeMaxima) {

        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
        this.energiaInicial = energiaInicial;
        this.consumoEnergia = consumoEnergia;
        this.ganhoEnergiaDescanso = ganhoEnergiaDescanso;
        this.velocidadeMinima = velocidadeMinima;
        this.velocidadeMaxima = velocidadeMaxima;
    }

    protected abstract void caracterizarEspecie();

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

    public abstract String getId();

    public abstract void setId(String id);

    public abstract String getNome();

    public abstract void setNome(String nome);

    public abstract String getImagem();

    public abstract void setImagem(String imagem);

    public abstract String toString();
}
