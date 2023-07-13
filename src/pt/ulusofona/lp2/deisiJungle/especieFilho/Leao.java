package pt.ulusofona.lp2.deisiJungle.especieFilho;

import pt.ulusofona.lp2.deisiJungle.Especie;

public class Leao extends Especie {

    public Leao() {
        this.id = "L";
        this.nome = "Leão";
        this.imagem = "lion.png";
        this.tipoAlimentacaoDaEspecie = "carnívoro";
        this.energiaInicial = 80;
        this.consumoEnergia = 2;
        this.ganhoEnergiaDescanso = 10;
        this.velocidadeMinima = 4;
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
    public String getTipoAlimentacaoDaEspecie() {
        return tipoAlimentacaoDaEspecie;
    }

    @Override
    public void setTipoAlimentacaoDaEspecie(String tipoAlimentacaoDaEspecie) {
        this.tipoAlimentacaoDaEspecie = tipoAlimentacaoDaEspecie;
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