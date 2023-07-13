package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.alimentoFilho.*;
import pt.ulusofona.lp2.deisiJungle.especieFilho.*;

public abstract class Alimento {

    protected String id;
    protected String nome;
    protected String imagem;
    protected int posicaoAlimento;

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
     * Efeitos ao consumir Erva
     */
    protected abstract int obterEfeitosConsumo (String tipoAlimentacaoEspecie);

    public abstract String getId();

    public abstract String getNome();

    public abstract String getImagem();

    public abstract int getPosicaoAlimento();

    public abstract String toolTip();
}
