package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.especieFilho.*;

public class Jogador {
    private int id;
    private String nome;
    private String idEspecie;
    private int posicaoAtual;
    private int energiaAtual;
    private Especie especie;
    private int turnoAtual;

    public Jogador(int id, String nome, String idEspecie, int posicaoAtual) {
        this.id = id;
        this.nome = nome;
        this.idEspecie = idEspecie;
        this.posicaoAtual = posicaoAtual;
    }

    public void caracterizarEspecieJogador(Jogador jogadorAtual) {

        switch (idEspecie) {
            case "E" -> {
                this.especie = new Elefante(idEspecie, "Elefante", "elephant.png",
                        180, 4, 10, 1, 6);
                jogadorAtual.setEspecie(this.especie);
                jogadorAtual.setEnergiaAtual(this.especie.getEnergiaInicial());
                jogadorAtual.getEspecie().setConsumoEnergia(4);
                jogadorAtual.getEspecie().setVelocidadeMinima(1);
                jogadorAtual.getEspecie().setVelocidadeMaxima(6);
            }
            case "L" -> {
                this.especie = new Leao(idEspecie, "Leão", "lion.png",
                        80, 2, 10, 4, 6);
                jogadorAtual.setEspecie(this.especie);
                jogadorAtual.setEnergiaAtual(this.especie.getEnergiaInicial());
                jogadorAtual.getEspecie().setConsumoEnergia(2);
                jogadorAtual.getEspecie().setVelocidadeMinima(4);
                jogadorAtual.getEspecie().setVelocidadeMaxima(6);
            }
            case "P" -> {
                this.especie = new Passaro(idEspecie, "Pássaro", "bird.png",
                        70, 4, 50, 5, 6);
                jogadorAtual.setEspecie(this.especie);
                jogadorAtual.setEnergiaAtual(this.especie.getEnergiaInicial());
                jogadorAtual.getEspecie().setConsumoEnergia(4);
                jogadorAtual.getEspecie().setVelocidadeMinima(5);
                jogadorAtual.getEspecie().setVelocidadeMaxima(6);
            }
            case "T" -> {
                this.especie = new Tartaruga(idEspecie, "Tartaruga", "turtle.png",
                        150, 1, 5, 1, 3);
                jogadorAtual.setEspecie(this.especie);
                jogadorAtual.setEnergiaAtual(this.especie.getEnergiaInicial());
                jogadorAtual.getEspecie().setConsumoEnergia(1);
                jogadorAtual.getEspecie().setVelocidadeMinima(1);
                jogadorAtual.getEspecie().setVelocidadeMaxima(3);
            }
            case "Z" -> {
                this.especie = new Tarzan(idEspecie, "Tarzan", "tarzan.png",
                        70, 2, 20, 1, 6);
                jogadorAtual.setEspecie(this.especie);
                jogadorAtual.setEnergiaAtual(this.especie.getEnergiaInicial());
                jogadorAtual.getEspecie().setConsumoEnergia(2);
                jogadorAtual.getEspecie().setVelocidadeMinima(1);
                jogadorAtual.getEspecie().setVelocidadeMaxima(6);
            }
            case "U" -> {
                this.especie = new Unicornio(idEspecie, "Unicórnio", "unicorn.png",
                        200, 8, 20, 3, 6);
                jogadorAtual.setEspecie(this.especie);
                jogadorAtual.setEnergiaAtual(this.especie.getEnergiaInicial());
                jogadorAtual.getEspecie().setConsumoEnergia(8);
                jogadorAtual.getEspecie().setVelocidadeMinima(3);
                jogadorAtual.getEspecie().setVelocidadeMaxima(6);
            }
        }

    }

    public final int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIdEspecie() {
        return idEspecie;
    }

    public int getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(int posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public int getEnergiaAtual() {
        return energiaAtual;
    }

    public void setEnergiaAtual(int energiaAtual) {
        this.energiaAtual = energiaAtual;
    }

    public int getTurnoAtual() {
        return turnoAtual;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    @Override
    public String toString() {
        return id + ":" + nome + ":" + idEspecie + ":" + posicaoAtual;
    }

}
