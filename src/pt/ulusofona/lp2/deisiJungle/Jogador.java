package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.especieFilho.*;

import java.util.HashMap;

public class Jogador {
    private  int id;
    private  String nome;
    private  String idEspecie;
    private int posicaoAtual;
    private int numeroPosicoesPercorridas;
    private Especie especie;
    private int manterEnergia;
    private boolean consumiuCachoDeBanana;

    public Jogador(int id, String nome, String idEspecie, int posicaoAtual, Especie especie) {
        this.id = id;
        this.nome = nome;
        this.idEspecie = idEspecie;
        this.posicaoAtual = posicaoAtual;
        this.especie = especie;
        this.numeroPosicoesPercorridas = 0;
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
            jogadorAtual.getEspecie().setEnergiaInicial(especieJogador.getEnergiaInicial());
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

        if (alimento.getId().equals("e")) {
            switch (tipoAlimentacaoEspecie) {
                case "herbívoro", "omnívoro" -> {
                    int aumentaEnergia = (jogador.getEspecie().getEnergiaInicial() + 40);
                    jogador.getEspecie().setEnergiaInicial(aumentaEnergia);
                    System.out.println(aumentaEnergia);
                }
                case "carnívoro" -> {
                    int diminuiEnergia = (jogador.getEspecie().getEnergiaInicial() - 40);
                    jogador.getEspecie().setEnergiaInicial(diminuiEnergia);
                }
            }
        }
    }

    /**
     * Efeitos ao consumir Água
     */
    public void consumirAgua(String tipoAlimentacaoEspecie, Jogador jogador, Alimento alimento) {
        if (alimento.getId().equals("a")) {
            switch (tipoAlimentacaoEspecie) {
                case "carnívoro", "herbívoro" -> {
                    int aumentaEnergia = (jogador.getEspecie().getEnergiaInicial() + 15);
                    jogador.getEspecie().setEnergiaInicial(aumentaEnergia);
                }
                case "omnívoro" -> {
                    int diminuiEnergia = ((int) (jogador.getEspecie().getEnergiaInicial() * 0.20));
                    jogador.getEspecie().setEnergiaInicial(diminuiEnergia);
                }
            }
        }
    }

    /**
     * Efeitos ao consumir bananas
     */
    public void consumirBanana(String tipoAlimentacaoEspecie, Jogador jogador, Alimento alimento) {

        if (alimento.getId().equals("b")) {
            switch (tipoAlimentacaoEspecie) {
                case "carnívoro":
                case "herbívoro":
                case "omnívoro":

                    if (alimento.getNumeroBananasON() > 0 && !jogador.isConsumiuCachoDeBanana()) {

                        if (alimento.getNumeroBananasON() <= 1) {
                            int aumentaEnergia = (jogador.getEspecie().getEnergiaInicial() + 40);
                            jogador.getEspecie().setEnergiaInicial(aumentaEnergia);

                        } else {
                            int diminuiEnergia = (jogador.getEspecie().getEnergiaInicial() - 40);
                            jogador.getEspecie().setEnergiaInicial(diminuiEnergia);
                        }
                        alimento.setNumeroBananasON(alimento.getNumeroBananasON()-1);

                    }
                    break;
            }
        }

    }

    /**
     * Efeitos ao consumir carne
     */
    public void consumirCarne(String tipoAlimentacaoEspecie, Jogador jogador, int turnosRestantes, Alimento alimento) {

        if (alimento.getId().equals("c")) {
            // Só é comestível nas primeiras 12 jogadas
            if (turnosRestantes <= 12) {

                alimento.setNumroJogadasCarne(turnosRestantes);

                // Se ingerido por carnívoros (ex: Leão) ou omnívoros (ex: Tarzan)...
                switch (tipoAlimentacaoEspecie) {
                    case "carnívoro", "omnívoro" -> {
                        int aumentaEnergia = (jogador.getEspecie().getEnergiaInicial() + 50);
                        // aumenta a energia em 50 unidades
                        jogador.getEspecie().setEnergiaInicial(aumentaEnergia);
                    }
                    default ->
                        // Os herbívoros ignoram esta comida, por isso não lhes acontece nada.
                            jogador.manterEnergia(0);
                }

            } else {

                alimento.setNumroJogadasCarne(turnosRestantes);

                // A partir daí é tóxica — se fôr ingerida, reduz para metade a energia do animal
                alimento.setCarneToxica(true);

                switch (tipoAlimentacaoEspecie) {
                    case "carnívoro", "omnívoro" -> {
                        int diminuiEnergia = (jogador.getEspecie().getEnergiaInicial() / 2);
                        jogador.getEspecie().setEnergiaInicial(jogador.getEspecie().getEnergiaInicial() - diminuiEnergia);
                    }
                    default ->
                        // Os herbívoros ignoram esta comida, por isso não lhes acontece nada.
                            jogador.manterEnergia(0);
                }
            }
        }
    }

    /**
     * Efeitos ao consumir cogumelos magicos
     */
    public void consumirCogumeloMagico(String tipoAlimentacaoEspecie, Jogador jogador, int turnoCogumelo, Alimento alimento) {

        if (alimento.getId().equals("m")) {
            // Todos os animais podem ingerir -> "carnívoro", "herbívoro", "omnívoro"
            switch (tipoAlimentacaoEspecie) {
                case "carnívoro":
                case "herbívoro":
                case "omnívoro":

                    // Se comerem o cogumelo nas jogadas pares
                    if (turnoCogumelo % 2 == 0) {

                        int energiaJogador = jogador.getEspecie().getEnergiaInicial();
                        float aumentaEnergia = (float) alimento.getNumeroAleatorioCog() / 100;

                        // os animais aumentam em N% a sua energia
                        jogador.getEspecie().setEnergiaInicial((int) (energiaJogador * aumentaEnergia));

                        // Se comerem o cogumelo nas jogadas ímpares
                    } else {

                        // torna-se venenos
                        alimento.setVenenoso(true);

                        int energiaJogador = jogador.getEspecie().getEnergiaInicial();
                        float diminuiEnergia = (float) alimento.getNumeroAleatorioCog() / 100;

                        // reduzem em N% a sua energia
                        jogador.getEspecie().setEnergiaInicial((int) -(energiaJogador * diminuiEnergia));

                    }

                    break;
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

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public boolean isConsumiuCachoDeBanana() {
        return consumiuCachoDeBanana;
    }

    public void manterEnergia(int manterEnergia) {
        this.manterEnergia = manterEnergia;
    }

    public int getNumeroPosicoesPercorridas() {
        return numeroPosicoesPercorridas;
    }

    public void setNumeroPosicoesPercorridas(int numeroPosicoesMovimentadas) {
        this.numeroPosicoesPercorridas = numeroPosicoesMovimentadas;
    }

    @Override
    public String toString() {
        return id + ":" + nome + ":" + idEspecie + ":" + especie.getEnergiaInicial() + ":" + posicaoAtual;
    }
}