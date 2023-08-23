package pt.ulusofona.lp2.deisiJungle.alimentoFilho;

import pt.ulusofona.lp2.deisiJungle.Alimento;
import pt.ulusofona.lp2.deisiJungle.GameManager;
import pt.ulusofona.lp2.deisiJungle.Jogador;

import java.util.Map;
import java.util.Random;

public class Carne extends Alimento {

    public Carne(String id, int posicaoAlimento) {
        super(id, posicaoAlimento);

        this.id = id;
        this.nome = "Carne";
        this.imagem = "meat.png";
        this.posicaoAlimento = posicaoAlimento;
        isCarneToxica = false;
    }

    /**
     * Efeitos ao consumir carne
     */
    @Override
    public int consumir(String tipoAlimentacaoEspecie, Jogador jogador, int turnoAtual, Alimento alimento) {
        int alteracaoEnergia = 0;
        if (getId().equals("c")) {
            // Só é comestível nas primeiras 12 jogadas
            if (turnoAtual <= 12) {
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
                        int diminuiEnergia = jogador.getEspecie().getEnergiaAtual() / 2;
                        alteracaoEnergia = -diminuiEnergia;
                    }
                }
            }
        }
        return alteracaoEnergia;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getImagem() {
        return imagem;
    }

    @Override
    public int getPosicaoAlimento() {
        return posicaoAlimento;
    }

    @Override
    public int getNumeroBananasON() {
        return numeroBananasON;
    }

    @Override
    public void setNumeroBananasON(int numeroBananasON) {
        this.numeroBananasON = numeroBananasON;
    }

    @Override
    public int getNumeroAleatorioCog() {
        return numeroAleatorioCog;
    }

    @Override
    public void setNumeroAleatorioCog(int numeroAleatorioCog) {
        this.numeroAleatorioCog = numeroAleatorioCog;
    }

    @Override
    public boolean isCarneToxica() {
        return isCarneToxica;
    }

    @Override
    public void setCarneToxica(boolean carneToxica) {
        this.isCarneToxica = carneToxica;
    }

    @Override
    public int getNumroJogadasCarne() {
        return numroJogadasCarne;
    }

    @Override
    public void setNumroJogadasCarne(int numroJogadasCarne) {
        this.numroJogadasCarne = numroJogadasCarne;
    }

    @Override
    public boolean isVenenoso() {
        return isVenenoso;
    }

    @Override
    public void setVenenoso(boolean venenoso) {
        this.isVenenoso = venenoso;
    }

    @Override
    public Random getRandom() {
        return random;
    }

    @Override
    public void setRandom(Random random) {
        this.random = random;
    }

    @Override
    public String toolTip() {
        /*
            ○ A tooltip deve mostrar “Carne : +- 50 energia : <N> jogadas”, em que <N>
                representa o número de jogadas já efetuadas. Caso a carne já esteja tóxica,
                deve mostrar “Carne toxica”
         */

        if (numroJogadasCarne <= 12) {
            return "Carne : + 50 energia : " + numroJogadasCarne + " jogadas";
        } else {
            return "Carne toxica";
        }
    }


    // COPORTAMENTO PARA AGUA / ERVA / BANANA
    @Override
    public int consumir(String tipoAlimentacaoEspecie, Jogador jogador) {
        return 0;
    }

    @Override
    public int consumir(String tipoAlimentacaoEspecie, Jogador jogador, Alimento alimento, Map<Integer, Integer> bananasConsumidasPorJogador) {
        return 0;
    }
}
