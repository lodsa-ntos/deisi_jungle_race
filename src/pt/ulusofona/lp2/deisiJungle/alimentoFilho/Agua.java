package pt.ulusofona.lp2.deisiJungle.alimentoFilho;

import pt.ulusofona.lp2.deisiJungle.Alimento;

public class Agua extends Alimento {

    public Agua(String id, int posicaoAlimento) {
        super(id, posicaoAlimento);

        this.id = id;
        this.nome = "Água";
        this.imagem = "water.png";
        this.posicaoAlimento = posicaoAlimento;
    }

    /**
     * Efeitos ao consumir Água
     */
    @Override
    public int obterEfeitosConsumo(String tipoAlimentacaoEspecie, int energiaEspecie) {

        /*
        ● Agua (identificador: ‘a’, imagem: ‘water.png’)
            ○ Se ingerido por carnívoros ou herbívoros, aumenta a energia em 15 unidades
            ○ Se ingerido por omnívoros, aumenta a energia em 20%
         */

        int aumentarEnergia = 15;
        double  aumentarEnergiaPercentagem =  0.20;

        return switch (tipoAlimentacaoEspecie) {
            case "carnívoro", "herbívoro" -> (energiaEspecie + aumentarEnergia);
            case "omnívoro" -> (int) (energiaEspecie + aumentarEnergiaPercentagem);
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
            ○ A tooltip deve mostrar “Agua : + 15U|20% energia”.
         */
        return "Agua : + 15U|20% energia";
    }
}
