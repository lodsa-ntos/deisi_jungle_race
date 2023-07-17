package pt.ulusofona.lp2.deisiJungle.especieFilho;

import pt.ulusofona.lp2.deisiJungle.Especie;

public class Tartaruga extends Especie {

    public Tartaruga() {
        this.id = "T";
        this.nome = "Tartaruga";
        this.imagem = "turtle.png";
        this.tipoAlimentacaoDaEspecie = "omnívoro";
        this.energiaInicial = 150;
        this.consumoEnergia = 1;
        this.ganhoEnergiaDescanso = 5;
        this.velocidadeMinima = 1;
        this.velocidadeMaxima = 3;
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
    public String getTipoAlimentacaoDaEspecie() {
        return tipoAlimentacaoDaEspecie;
    }

    @Override
    public String setTipoAlimentacaoDaEspecie(String tipoAlimentacaoDaEspecie) {
        this.tipoAlimentacaoDaEspecie = tipoAlimentacaoDaEspecie;
        return tipoAlimentacaoDaEspecie;
    }

    @Override
    public String toString() {
        return "Especie: " + nome + "\n" +
                "Tipo de alimentação: " + tipoAlimentacaoDaEspecie + "\n" +
                "id: " + id + "\n" +
                "Energia: " + energiaInicial + "\n" +
                "Consumo de energia: " + consumoEnergia + "\n" +
                "Ganho de Energia Descanso: " + ganhoEnergiaDescanso + "\n" +
                "Velocidade mínima: " + velocidadeMinima + "\n" +
                "Velocidade máxima: " + velocidadeMaxima + "\n" ;
    }
}
