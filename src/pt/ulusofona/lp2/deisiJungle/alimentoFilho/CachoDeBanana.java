package pt.ulusofona.lp2.deisiJungle.alimentoFilho;

import pt.ulusofona.lp2.deisiJungle.Alimento;

public class CachoDeBanana extends Alimento {

    public CachoDeBanana(String id, int posicaoAlimento) {
        super(id, posicaoAlimento);

        this.id = id;
        this.nome = "Cacho de bananas";
        this.imagem = "bananas.png";
        this.posicaoAlimento = posicaoAlimento;
        this.numeroBananasON = 3;
    }


    /**
     * Efeitos ao consumir bananas
     */
    @Override
    protected int obterEfeitosConsumo(String tipoAlimentacaoEspecie, int energiaEspecie) {

        /*
        ● Cacho de bananas (identificador: ‘b’, imagem: ‘bananas.png’)
            ○ Pode ser ingerido por qualquer animal -> "carnívoro", "herbívoro", "omnívoro"
            ○ Aumenta a energia em 40 unidades
            ○ Só existem 3 bananas no cacho. Os animais que calham numa casa com este
            alimento consomem uma dessas bananas. Quando as bananas acabarem, o
            alimento deixa de produzir efeito. Ou seja, a partir da 4.ª vez que algum animal
            chega a uma casa contendo este alimento, ele já não é afetado.
            ○ Como comer muitas bananas causa dificuldades gástricas, se o mesmo jogador
            consumir mais do que uma banana, a 2.ª e 3.ª bananas retiram energia em vez de
            dar, na mesma proporção (40U).
            ○ Note-se que pode haver mais do que um cacho de bananas no terreno. Cada
            cacho é independente dos outros.
         */

        int aumentarEnergia = 40;

        return switch (tipoAlimentacaoEspecie) {
            case "carnívoro", "herbívoro", "omnívoro" -> (energiaEspecie + aumentarEnergia);
            default -> throw new IllegalArgumentException("");
        };
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
    public String toolTip() {
        /*
            A tooltip deve mostrar “Bananas : <N> : + 40 energia”, em que N é o número de bananas disponíveis
         */

        //int numBananas = 0;
        return "Bananas : " + numeroBananasON + " : + 40 energia" ;
    }
}
