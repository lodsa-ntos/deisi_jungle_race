package pt.ulusofona.lp2.deisiJungle.especieFilho;

import pt.ulusofona.lp2.deisiJungle.Especie;

public class Tarzan extends Especie {

    public Tarzan() {
        this.id = "Z";
        this.nome = "Tarzan";
        this.imagem = "tarzan.png";
        this.energiaInicial = 70;
        this.consumoEnergia = 2;
        this.ganhoEnergiaDescanso = 20;
        this.velocidadeMinima = 1;
        this.velocidadeMaxima = 6;
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
    public int getVelocidadeMinima() {
        return velocidadeMinima;
    }

    @Override
    public void setVelocidadeMinima(int velocidadeMinima) {
        this.velocidadeMinima = velocidadeMinima;
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
    public String toString() {
        return "Especie: " + nome + "\n" +
                "id: " + id + "\n" +
                "Energia: " + energiaInicial + "\n" +
                "Consumo de energia: " + consumoEnergia + "\n" +
                "Ganho de Energia Descanso: " + ganhoEnergiaDescanso + "\n" +
                "Velocidade mínima: " + velocidadeMinima + "\n" +
                "Velocidade máxima: " + velocidadeMaxima + "\n" ;
    }
}
