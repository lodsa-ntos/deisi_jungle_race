package pt.ulusofona.lp2.deisiJungle.especieFilho;

import pt.ulusofona.lp2.deisiJungle.Especie;

public class Girafa extends Especie {

    public Girafa() {
        this.id = "G";
        this.nome = "Girafa";
        this.imagem = "giraffe.png";
        this.tipoAlimentacaoDaEspecie = "herbívoro";
        this.energiaAtual = 150;
        this.consumoEnergia = 4;
        this.ganhoEnergiaDescanso = 3;
        this.velocidadeMinima = 2;
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
    public int getEnergiaAtual() {
        return energiaAtual;
    }

    @Override
    public void definirEnergiaAtual(int energiaAtual) {
        this.energiaAtual = energiaAtual;
    }

    @Override
    public int getConsumoEnergia() {
        return consumoEnergia;
    }

    @Override
    public void definirConsumoEnergia(int consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
    }

    @Override
    public int getGanhoEnergiaDescanso() {
        return ganhoEnergiaDescanso;
    }

    @Override
    public void definirGanhoEnergiaDescanso(int ganhoEnergiaDescanso) {
        this.ganhoEnergiaDescanso = ganhoEnergiaDescanso;
    }

    @Override
    public int getVelocidadeMinima() {
        return velocidadeMinima;
    }

    @Override
    public void definirVelocidadeMinima(int velocidadeMinima) {
        this.velocidadeMinima = velocidadeMinima;
    }

    @Override
    public int getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    @Override
    public void definirVelocidadeMaxima(int velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }

    @Override
    public String getTipoAlimentacaoDaEspecie() {
        return tipoAlimentacaoDaEspecie;
    }

    @Override
    public String definirTipoAlimentacaoDaEspecie(String tipoAlimentacaoDaEspecie) {
        this.tipoAlimentacaoDaEspecie = tipoAlimentacaoDaEspecie;
        return tipoAlimentacaoDaEspecie;
    }

    @Override
    public String toString() {
        return "Especie: " + nome + "\n" +
                "Tipo de alimentação: " + tipoAlimentacaoDaEspecie + "\n" +
                "id: " + id + "\n" +
                "Energia: " + energiaAtual + "\n" +
                "Consumo de energia: " + consumoEnergia + "\n" +
                "Ganho de Energia Descanso: " + ganhoEnergiaDescanso + "\n" +
                "Velocidade mínima: " + velocidadeMinima + "\n" +
                "Velocidade máxima: " + velocidadeMaxima + "\n" ;
    }
}