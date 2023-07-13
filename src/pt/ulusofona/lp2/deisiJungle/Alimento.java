package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.alimentoFilho.*;
import pt.ulusofona.lp2.deisiJungle.especieFilho.*;

public abstract class Alimento {

    protected String id;
    protected String nome;
    protected String imagem;
    protected int posicaoAlimento;
    protected int numeroBananasON;
    protected int numeroAleatorioCog;

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
    protected abstract int obterEfeitosConsumo (String tipoAlimentacaoEspecie, int energiaEspecie);

    public abstract String getId();

    public abstract String getNome();

    public abstract String getImagem();

    public abstract int getPosicaoAlimento();

    public abstract int getNumeroBananasON();

    public abstract void setNumeroBananasON(int numeroBananasON);

    public abstract int getNumeroAleatorioCog();

    public abstract void setNumeroAleatorioCog(int numeroAleatorioCog);

    public abstract String toolTip();
}
