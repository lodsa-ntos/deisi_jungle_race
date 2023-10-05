package pt.ulusofona.lp2.deisiJungle.alimentoFilho;

import pt.ulusofona.lp2.deisiJungle.Food;
import pt.ulusofona.lp2.deisiJungle.Player;

import java.util.Map;

public class Tree extends Food {

    public Tree(String id, int posicaoAlimento) {

        super(id, posicaoAlimento);

        this.id = id;
        this.nome = "Tree";
        this.imagem = "trees.png";
        this.posicaoAlimento = posicaoAlimento;
    }

    /**
     * Efeitos ao consumir folhas das árvores
     */
    @Override
    public int consumir(String tipoAlimentacaoEspecie, Player player) {
        int alteracaoEnergia = 0;

        if (getId().equals("t")) {
            switch (tipoAlimentacaoEspecie) {
                case "herbívoro" -> {
                    alteracaoEnergia = 50;
                }
                case "omnívoro" -> {
                    alteracaoEnergia = ((int) (player.getEspecie().getCurrentEnergy() * 0.30));
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
            ○ A tooltip deve mostrar “Tree : + 50U|30% energia”.
         */
        return "Tree : + 50U|30% energia";
    }

    // COPORTAMENTO PARA CARNE / COGUMELO / BANANA
    @Override
    public int consumir(String tipoAlimentacaoEspecie, Player player, int turnoAtual, Food food) {
        return 0;
    }

    @Override
    public int consumir(String tipoAlimentacaoEspecie, Player player, Food food, Map<Integer, Integer> bananasConsumidasPorJogador) {
        return 0;
    }
}
