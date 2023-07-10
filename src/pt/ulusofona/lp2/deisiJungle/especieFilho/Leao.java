package pt.ulusofona.lp2.deisiJungle.especieFilho;

import pt.ulusofona.lp2.deisiJungle.Especie;

public class Leao extends Especie {

    public Leao(String id, String nome, String imagem) {
        super(id, nome, imagem);
    }

    @Override
    public void caracterizarEspecie() {
        this.energiaInicial = 80;
        this.consumoEnergia = 2;
        this.ganhoEnergiaDescanso = 10;
        this.velocidadeMinima = 4;
        this.velocidadeMaxima = 6;
    }

    @Override
    public int getEnergiaInicial() {
        return energiaInicial;
    }

    @Override
    public void setEnergiaInicial(int energiaInicial) {
        this.energiaInicial = energiaInicial;
    }

    @Override
    public int getConsumoEnergia() {
        return consumoEnergia;
    }

    @Override
    public void setConsumoEnergia(int consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
    }

    @Override
    public int getGanhoEnergiaDescanso() {
        return ganhoEnergiaDescanso;
    }

    @Override
    public void setGanhoEnergiaDescanso(int ganhoEnergiaDescanso) {
        this.ganhoEnergiaDescanso = ganhoEnergiaDescanso;
    }

    @Override
    public int getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    @Override
    public void setVelocidadeMaxima(int velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }

    @Override
    public int getVelocidadeMinima() {
        return velocidadeMinima;
    }

    @Override
    public void setVelocidadeMinima(int velocidadeMinima) {
        this.velocidadeMinima = velocidadeMinima;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getImagem() {
        return imagem;
    }

    @Override
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return  "Especie: " + nome + "\n" +
                "Energia Inicial: " + energiaInicial + "\n" +
                "Consumo de Energia: " + consumoEnergia + "\n" +
                "Ganho Energia Descanso: " + ganhoEnergiaDescanso + "\n" +
                "Velocidade minima: " + velocidadeMinima + "\n" +
                "Velocidade maxima: " + velocidadeMaxima + "\n";
    }
}
