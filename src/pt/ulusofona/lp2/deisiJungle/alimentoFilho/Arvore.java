package pt.ulusofona.lp2.deisiJungle.alimentoFilho;

import pt.ulusofona.lp2.deisiJungle.Alimento;
import pt.ulusofona.lp2.deisiJungle.Jogador;

import java.util.Map;
import java.util.Random;

public class Arvore extends Alimento {

    public Arvore(String id, int posicaoAlimento) {

        super(id, posicaoAlimento);

        this.id = id;
        this.nome = "Arvore";
        this.imagem = "trees.png";
        this.posicaoAlimento = posicaoAlimento;
    }

    /**
     * Efeitos ao consumir folhas das árvores
     */
    @Override
    public int consumir(String tipoAlimentacaoEspecie, Jogador jogador) {
        int alteracaoEnergia = 0;

        if (getId().equals("t")) {
            switch (tipoAlimentacaoEspecie) {
                case "herbívoro" -> {
                    alteracaoEnergia = 50;
                }
                case "omnívoro" -> {
                    alteracaoEnergia = ((int) (jogador.getEspecie().getEnergiaAtual() * 0.30));
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
    public void setVenenoso(boolean venenoso) {
        this.isVenenoso = venenoso;
    }

    @Override
    public String toolTip() {
        /*
            ○ A tooltip deve mostrar “Arvore : + 50U|30% energia”.
         */
        return "Arvore : + 50U|30% energia";
    }

    // COPORTAMENTO PARA CARNE / COGUMELO / BANANA
    @Override
    public int consumir(String tipoAlimentacaoEspecie, Jogador jogador, int turnoAtual, Alimento alimento) {
        return 0;
    }

    @Override
    public int consumir(String tipoAlimentacaoEspecie, Jogador jogador, Alimento alimento, Map<Integer, Integer> bananasConsumidasPorJogador) {
        return 0;
    }
}
