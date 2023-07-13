package pt.ulusofona.lp2.deisiJungle.alimentoFilho;

import pt.ulusofona.lp2.deisiJungle.Alimento;

import java.util.Random;

public class CogumeloMagico extends Alimento {

    public CogumeloMagico(String id, int posicaoAlimento) {
        super(id, posicaoAlimento);

        this.id = id;
        this.nome = "Cogumelos magicos";
        this.imagem = "mushroom.png";
        this.posicaoAlimento = posicaoAlimento;
    }

    /**
     * Efeitos ao consumir cogumelos magicos
     */
    @Override
    protected int obterEfeitosConsumo(String tipoAlimentacaoEspecie, int energiaEspecie) {

        /*
        ● Cogumelos magicos (identificador: ‘m’, imagem: ‘mushroom.png’)
            ○ Todos os animais podem ingerir -> "carnívoro", "herbívoro", "omnívoro"
            ○ Como são mágicos, o seu comportamento varia de cogumelo para cogumelo e de jogada para jogada.
            ○ Cada cogumelo associa um número (N) entre 10 e 50, gerado aleatoriamente na sua criação
            ○ Se comerem o cogumelo nas jogadas pares, os animais aumentam em N% a sua energia
            ○ Se comerem o cogumelo nas jogadas ímpares, ele torna-se venenoso e reduzem em N% a sua energia
            ○ A tooltip deve mostrar “Cogumelo Magico: +- <N>% energia”, em que N é o número gerado aleatoriamente.
         */

        int aumentarEnergia = 50;


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
            ○ A tooltip deve mostrar “Cogumelo Magico: +- <N>% energia”, em que N é o número gerado aleatoriamente.
         */

        //int numeroAleatorio = 0;
        return "Cogumelo Magico: +- " + numeroAleatorioCog + "% energia";
    }
}
