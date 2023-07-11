package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.especieFilho.*;

public class Jogador {
    private final int id;
    private final String nome;
    private final String idEspecie;
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

    /*
    public void definirEspecieJogador(String idEspecieJogador) {
        switch (idEspecieJogador) {
            case "E" -> this.especie = new Elefante(idEspecieJogador, "Elefante", "elephant.png");
            case "L" -> this.especie = new Leao(idEspecieJogador, "Leao", "leon.png");
            case "T" -> this.especie = new Tartaruga(idEspecieJogador, "Tartaruga", "turtle.png");
            case "P" -> this.especie = new Passaro(idEspecieJogador, "Pássaro", "bird.png");
            case "Z" -> this.especie = new Tarzan(idEspecieJogador, "Tarzan", "tarzan.png");
            case "U" -> this.especie = new Unicornio(idEspecieJogador, "Unicórnio", "unicorn.png");
            default -> throw new IllegalArgumentException("Espécie inválida: " + idEspecieJogador);
        }

        this.especie.caracterizarEspecie();
    }
     */




    @Override
    public String toString() {
        return id + ":" + nome + ":" + idEspecie + ":" + posicaoAtual;
    }

}
