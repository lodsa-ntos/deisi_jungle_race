package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.alimentoFilho.*;

import java.util.Random;

public abstract class Alimento {

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

    public Alimento(String id, int posicaoAlimento) {
        this.id = id;
        this.posicaoAlimento = posicaoAlimento;
    }

    /**
     * Identificar Alimentos
     */
    public static Alimento identificarAlimento(String id, int posicaoAlimento) {
        return switch (id) {
            case "e" -> new Erva(id, posicaoAlimento);
            case "a" -> new Agua(id, posicaoAlimento);
            case "b" -> new CachoDeBanana(id, posicaoAlimento);
            case "c" -> new Carne(id, posicaoAlimento);
            case "m" -> new CogumeloMagico(id, posicaoAlimento);
            default -> null;
        };
    }

    /**
     * Efeitos ao consumir alimentos
     */
    //protected abstract int obterEfeitosConsumo (String tipoAlimentacaoEspecie, int energiaEspecie);

    public void diminuirNumBananas() {
        this.numeroBananasON -= 1;
    }

    public abstract String getId();

    public abstract String getNome();

    public abstract String getImagem();

    public abstract int getPosicaoAlimento();

    public abstract int getNumeroBananasON();

    public abstract void setNumeroBananasON(int numeroBananasON);

    public abstract int getNumeroAleatorioCog();

    public abstract void setNumeroAleatorioCog(int numeroAleatorioCog);

    public abstract boolean isCarneToxica();

    public abstract void setCarneToxica(boolean carneToxica);

    public abstract int getNumroJogadasCarne();

    public abstract void setNumroJogadasCarne(int numroJogadasCarne);

    public abstract boolean isVenenoso();

    public abstract void setVenenoso(boolean venenoso);

    public abstract Random getRandom();

    public abstract void setRandom(Random random);

    public abstract String toolTip();
}
