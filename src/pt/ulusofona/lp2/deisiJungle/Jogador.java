package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.especieFilho.*;

import java.util.HashMap;
import java.util.Map;

public class Jogador {
    private  int id;
    private  String nome;
    private  String idEspecie;
    private int posicaoAtual;
    private int numeroPosicoesPercorridas;
    private int numeroAlimento;
    private int numJogadoresEmJogo;
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
    public int consumirErva(String tipoAlimentacaoEspecie, Jogador jogador, Alimento alimento) {
        int alteracaoEnergia = 0;

        if (alimento.getId().equals("e")) {
            switch (tipoAlimentacaoEspecie) {
                case "herbívoro", "omnívoro" -> {
                    alteracaoEnergia = 20;
                }
                case "carnívoro" -> {
                    alteracaoEnergia = -20;
                }
            }
        }
        return alteracaoEnergia;
    }

    /**
     * Efeitos ao consumir Água
     */
    public int consumirAgua(String tipoAlimentacaoEspecie, Jogador jogador, Alimento alimento) {
        int alteracaoEnergia = 0;

        if (alimento.getId().equals("a")) {
            switch (tipoAlimentacaoEspecie) {
                case "carnívoro", "herbívoro" -> {
                    alteracaoEnergia = 15;
                }
                case "omnívoro" -> {
                    alteracaoEnergia = ((int) (jogador.getEspecie().getEnergiaInicial() * 0.20));
                }
            }
        }
        return alteracaoEnergia;
    }

    /**
     * Efeitos ao consumir bananas
     */
    public int consumirBanana(String tipoAlimentacaoEspecie, Jogador jogador, Alimento alimento, Map<Integer, Integer> bananasConsumidasPorJogador) {
        int alteracaoEnergia = 0;

        if (alimento.getId().equals("b")) {
            switch (tipoAlimentacaoEspecie) {
                case "carnívoro":
                case "herbívoro":
                case "omnívoro":

                    if (alimento.getNumeroBananasON() > 0) {
                        int bananasConsumidas = bananasConsumidasPorJogador.getOrDefault(jogador.getId(), 0);

                        if (bananasConsumidas == 0) {
                            alteracaoEnergia = 40;
                            bananasConsumidasPorJogador.put(jogador.getId(), bananasConsumidas + 1);

                        } else if (bananasConsumidas == 1) {
                            alteracaoEnergia = -40;
                        }

                        alimento.setNumeroBananasON(alimento.getNumeroBananasON() - 1);
                    }
                    break;
            }
        }

        return alteracaoEnergia;
    }

    /**
     * Efeitos ao consumir carne
     */
    public int consumirCarne(String tipoAlimentacaoEspecie, Jogador jogador, int turnosRestantes, Alimento alimento) {
        int alteracaoEnergia = 0;
        if (alimento.getId().equals("c")) {
            // Só é comestível nas primeiras 12 jogadas
            if (turnosRestantes <= 12) {
                // Se ingerido por carnívoros (ex: Leão) ou omnívoros (ex: Tarzan)...
                switch (tipoAlimentacaoEspecie) {
                    case "carnívoro", "omnívoro" -> {
                        // aumenta a energia em 50 unidades
                        alteracaoEnergia = 50;
                    }
                }
            } else {
                // A partir daí é tóxica. — se for ingerida, reduz para metade a energia do animal
                alimento.setCarneToxica(true);

                switch (tipoAlimentacaoEspecie) {
                    case "carnívoro", "omnívoro" -> {
                        int diminuiEnergia = jogador.getEspecie().getEnergiaInicial() / 2;
                        alteracaoEnergia = -diminuiEnergia;
                    }
                }
            }
        }
        return alteracaoEnergia;
    }

    /**
     * Efeitos ao consumir cogumelos magicos
     */
    public int consumirCogumeloMagico(String tipoAlimentacaoEspecie, Jogador jogador, int turnoCogumelo, Alimento alimento) {
        int alteracaoEnergia = 0;

        if (alimento.getId().equals("m")) {
            // Todos os animais podem ingerir -> "carnívoro", "herbívoro", "omnívoro"
            switch (tipoAlimentacaoEspecie) {
                case "carnívoro", "herbívoro", "omnívoro" -> {
                    int energiaJogador = jogador.getEspecie().getEnergiaInicial();
                    float valorAlteracaoEnergia = (float) alimento.getNumeroAleatorioCog() / 100;

                    // Se comerem o cogumelo nas jogadas pares, aumentam em N% a energia
                    if (turnoCogumelo % 2 == 0) {
                        alteracaoEnergia = (int) (energiaJogador * valorAlteracaoEnergia);

                    // Se comerem o cogumelo nas jogadas ímpares, reduzem em N% a energia
                    } else {
                        // Torna-se venenoso e reduzem em N% a sua energia
                        alimento.setVenenoso(true);
                        alteracaoEnergia = -(int) (energiaJogador * valorAlteracaoEnergia);
                    }
                }
            }
        }
        return alteracaoEnergia;
    }

    public final int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(String idEspecie) {
        this.idEspecie = idEspecie;
    }

    public int getPosicaoAtual() {
        return posicaoAtual;
    }

    public void alterarPosicaoAtual(int posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    // TIRAR
    public void novaPosicao(int posicao) {
        this.posicaoAtual += posicao;
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
        this.numeroPosicoesPercorridas += numeroPosicoesMovimentadas;
    }

    public int getNumeroAlimento() {
        return numeroAlimento;
    }

    public void contarNumAlimentoApanhado(int numeroAlimento) {
        this.numeroAlimento += numeroAlimento;
    }

    public int getNumJogadoresEmJogo() {
        return numJogadoresEmJogo;
    }

    public void saberNumJogadoresEmJogo(int numJogadoresEmJogo) {
        this.numJogadoresEmJogo = numJogadoresEmJogo;
    }

    @Override
    public String toString() {
        return id + ":" + nome + ":" + idEspecie + ":" + especie.getEnergiaInicial()
                + ":" + posicaoAtual + ":" + numeroPosicoesPercorridas + ":" + numeroAlimento;
    }
}