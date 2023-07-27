package pt.ulusofona.lp2.deisiJungle.especieFilho;

import pt.ulusofona.lp2.deisiJungle.Especie;

public class Unicornio extends Especie {

    public Unicornio() {
        this.id = "U";
        this.nome = "Unicórnio";
        this.imagem = "unicorn.png";
        this.tipoAlimentacaoDaEspecie = "";
        this.energiaInicial = 200;
        this.consumoEnergia = 22;
        this.ganhoEnergiaDescanso = 20;
        this.velocidadeMinima = 3;
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
