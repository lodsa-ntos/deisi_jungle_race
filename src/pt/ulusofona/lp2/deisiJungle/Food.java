package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.alimentoFilho.*;

import java.util.Map;
import java.util.Random;

public abstract class Food {

    protected String id;
    protected String nome;
    protected String imagem;
    protected int posicaoAlimento;
    protected int numeroBananasON;
    protected int numeroAleatorioCog;
    protected int numroJogadasCarne;
    protected boolean isCarneToxica;
    protected boolean isVenenoso;
    protected Random random;

    public Food(String id, int posicaoAlimento) {
        this.id = id;
        this.posicaoAlimento = posicaoAlimento;
    }

    /**
     * Identificar Alimentos
     */
    protected static Food identificarAlimento(String id, int posicaoAlimento) {
        return switch (id) {
            case "e" -> new Grass(id, posicaoAlimento);
            case "a" -> new Water(id, posicaoAlimento);
            case "b" -> new BunchesOfBanana(id, posicaoAlimento);
            case "c" -> new Meat(id, posicaoAlimento);
            case "m" -> new MagicMushroom(id, posicaoAlimento);
            case "t" -> new Tree(id, posicaoAlimento);
            default -> null;
        };
    }

    /**
     * Efeitos ao consumir alimentos AGUA / ERVA
     */
    protected abstract int consumir(String tipoAlimentacaoEspecie, Player player);

    /**
     * Efeitos ao consumir alimentos CARNE / COGUMELO MÁGICO
     */
    protected abstract int consumir(String tipoAlimentacaoEspecie, Player player, int turnoAtual, Food food);

    /**
     * Efeitos ao consumir food BANANA
     */
    protected abstract int consumir(String tipoAlimentacaoEspecie, Player player, Food food, Map<Integer, Integer> bananasConsumidasPorJogador);

    public abstract String getId();

    public abstract String getNome();

    public abstract String getImagem();

    public abstract int getPosicaoAlimento();

    public abstract int getNumeroBananasON();

    public abstract void setNumeroBananasON(int numeroBananasON);

    public abstract int getNumeroAleatorioCog();

    public abstract void setNumeroAleatorioCog(int numeroAleatorioCog);

    public abstract void setCarneToxica(boolean carneToxica);

    public abstract int getNumroJogadasCarne();

    public abstract void setNumroJogadasCarne(int numroJogadasCarne);

    public abstract void setVenenoso(boolean venenoso);

    public abstract String toolTip();
}
