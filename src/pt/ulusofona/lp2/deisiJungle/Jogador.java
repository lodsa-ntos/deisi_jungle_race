package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.alimentoFilho.Carne;
import pt.ulusofona.lp2.deisiJungle.alimentoFilho.CogumeloMagico;
import pt.ulusofona.lp2.deisiJungle.especieFilho.*;

import java.util.HashMap;

public class Jogador {
    private  int id;
    private  String nome;
    private  String idEspecie;
    private int posicaoAtual;
    private int energiaAtual;
    private Especie especie;
    private int turnoAtual;
    private int aumentarEnergia;
    private int diminuirEnergia;
    private int manterEnergia;
    private boolean consumiuCachoDeBanana;

    public Jogador(int id, String nome, String idEspecie, int posicaoAtual, Especie especie) {
        this.id = id;
        this.nome = nome;
        this.idEspecie = idEspecie;
        this.posicaoAtual = posicaoAtual;
        this.especie = especie;
    }

    public Jogador() {
    }

    /**
     * Caracterizar a Especie do Jogador
     */
    public void caracterizarEspecieJogador(Jogador jogadorAtual) {

        // HashMap para mapear os IDs das espécie e às instâncias da classe Especie.
        HashMap<String, Especie> especies = new HashMap<>();

        especies.put("E", new Elefante());
        especies.put("L", new Leao());
        especies.put("T", new Tartaruga());
        especies.put("P", new Passaro());
        especies.put("Z", new Tarzan());
        especies.put("U", new Unicornio());

        //  obter a instância que correspondente à idEspecie
        Especie especieJogador = especies.get(idEspecie);

        if (especieJogador != null) {
            jogadorAtual.setEspecie(especieJogador);
            jogadorAtual.setEnergiaAtual(especieJogador.getEnergiaInicial());
            jogadorAtual.getEspecie().setConsumoEnergia(especieJogador.getConsumoEnergia());
            jogadorAtual.getEspecie().setGanhoEnergiaDescanso(especieJogador.getGanhoEnergiaDescanso());
            jogadorAtual.getEspecie().setVelocidadeMinima(especieJogador.getVelocidadeMinima());
            jogadorAtual.getEspecie().setVelocidadeMaxima(especieJogador.getVelocidadeMaxima());
        }
    }

    /**
     * Efeitos ao consumir Erva
     */
    public void consumirErva(String tipoAlimentacaoEspecie, Jogador jogador, Alimento alimento) {
        switch (tipoAlimentacaoEspecie) {
            case "herbívoro", "omnívoro" -> jogador.setEnergiaAtual(jogador.getEspecie().getEnergiaInicial() + 40);
            case "carnívoro" -> jogador.setEnergiaAtual(jogador.getEspecie().getEnergiaInicial() - 40);
        }
    }

    /**
     * Efeitos ao consumir Água
     */
    public void consumirAgua(String tipoAlimentacaoEspecie, Jogador jogador, Alimento alimento) {
        switch (tipoAlimentacaoEspecie) {
            case "carnívoros", "herbívoros" -> jogador.setEnergiaAtual(jogador.getEspecie().getEnergiaInicial() + 15);
            case "omnívoros" -> jogador.setEnergiaAtual((int) (jogador.getEspecie().getEnergiaInicial() - 0.20));
        }
    }

    /**
     * Efeitos ao consumir bananas
     */
    public void consumirBanana(String tipoAlimentacaoEspecie, Jogador jogador, Alimento alimento) {

        if (tipoAlimentacaoEspecie.equals("carnívoro") || tipoAlimentacaoEspecie.equals("herbívoro") ||
                tipoAlimentacaoEspecie.equals("omnívoro") ) {

            if (alimento.getNumeroBananasON() > 0 && !jogador.isConsumiuCachoDeBanana()) {

                if (alimento.getNumeroBananasON() <= 1) {

                    jogador.setEnergiaAtual(jogador.getEnergiaAtual() + 40);

                } else {
                    jogador.setEnergiaAtual(jogador.getEnergiaAtual() - 40 * (alimento.getNumeroBananasON()  - 1));
                }
                alimento.setNumeroBananasON( - 1);

            }
        }

    }

    /**
     * Efeitos ao consumir carne
     */
    public void consumirCarne(String tipoAlimentacaoEspecie, Jogador jogador, int turnosRestantes, Alimento alimento) {

        // Só é comestível nas primeiras 12 jogadas
        if (turnosRestantes <= 12) {

            alimento.setNumroJogadasCarne(turnosRestantes);

            // Se ingerido por carnívoros (ex: Leão) ou omnívoros (ex: Tarzan)...
            if (tipoAlimentacaoEspecie.equals("carnívoro") || tipoAlimentacaoEspecie.equals("omnívoro")) {

                // aumenta a energia em 50 unidades
                jogador.setEnergiaAtual(jogador.getEspecie().getEnergiaInicial() + 50);

            } else {
                // Os herbívoros ignoram esta comida, por isso não lhes acontece nada.
                jogador.manterEnergia(0);
            }

        } else {

            alimento.setNumroJogadasCarne(turnosRestantes);

            // A partir daí é tóxica — se fôr ingerida, reduz para metade a energia do animal
            alimento.setCarneToxica(true);

            if (tipoAlimentacaoEspecie.equals("carnívoro") || tipoAlimentacaoEspecie.equals("omnívoro")) {

                int energia = (jogador.getEnergiaAtual() / 2);
                jogador.setEnergiaAtual(jogador.getEspecie().getEnergiaInicial()- energia);

            } else {
                // Os herbívoros ignoram esta comida, por isso não lhes acontece nada.
                jogador.manterEnergia(0);
            }
        }
    }

    /**
     * Efeitos ao consumir cogumelos magicos
     */
    public void consumirCogumeloMagico(String tipoAlimentacaoEspecie, Jogador jogador, int turnoCogumelo, Alimento alimento) {

        // Todos os animais podem ingerir -> "carnívoro", "herbívoro", "omnívoro"
        if (tipoAlimentacaoEspecie.equals("carnívoro") || tipoAlimentacaoEspecie.equals("herbívoro") ||
                tipoAlimentacaoEspecie.equals("omnívoro") ) {

            // Se comerem o cogumelo nas jogadas pares
            if (turnoCogumelo % 2 == 0) {

                // os animais aumentam em N% a sua energia
                jogador.setEnergiaAtual(jogador.getEspecie().getEnergiaInicial()  * alimento.getNumeroAleatorioCog() / 100);

                // Se comerem o cogumelo nas jogadas ímpares
            } else {

                // torna-se venenos
                alimento.setVenenoso(true);

                // reduzem em N% a sua energia
                int energia = jogador.getEspecie().getEnergiaInicial() * (alimento.getNumeroAleatorioCog() / 100);
                jogador.setEnergiaAtual(jogador.getEspecie().getEnergiaInicial() - energia);

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

    public boolean isConsumiuCachoDeBanana() {
        return consumiuCachoDeBanana;
    }

    public void setConsumiuCachoDeBanana(boolean consumiuCachoDeBanana) {
        this.consumiuCachoDeBanana = consumiuCachoDeBanana;
    }

    public int getAumentarEnergia() {
        return aumentarEnergia;
    }

    public void aumentarEnergia(int aumentarEnergia) {
        this.aumentarEnergia = aumentarEnergia;
    }

    public int getDiminuirEnergia() {
        return diminuirEnergia;
    }

    public void diminuirEnergia(int diminuirEnergia) {
        this.diminuirEnergia = diminuirEnergia;
    }

    public int getManterEnergia() {
        return manterEnergia;
    }

    public void manterEnergia(int manterEnergia) {
        this.manterEnergia = manterEnergia;
    }

    @Override
    public String toString() {
        return id + ":" + nome + ":" + idEspecie + ":" + energiaAtual + ":" + posicaoAtual;
    }
}