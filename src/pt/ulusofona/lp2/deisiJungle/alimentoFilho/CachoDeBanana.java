package pt.ulusofona.lp2.deisiJungle.alimentoFilho;

import pt.ulusofona.lp2.deisiJungle.Alimento;
import pt.ulusofona.lp2.deisiJungle.Jogador;

import java.util.Map;
import java.util.Random;

public class CachoDeBanana extends Alimento {

    public CachoDeBanana(String id, int posicaoAlimento) {
        super(id, posicaoAlimento);

        this.id = id;
        this.nome = "Bananas";
        this.imagem = "bananas.png";
        this.posicaoAlimento = posicaoAlimento;
        numeroBananasON = 3;
    }

    /**
     * Efeitos ao consumir bananas
     */
    @Override
    public int consumir(String tipoAlimentacaoEspecie, Jogador jogador, Alimento alimento, Map<Integer, Integer> bananasConsumidasPorJogador) {
        int alteracaoEnergia = 0;

        if (alimento.getId().equals("b")) {
            switch (tipoAlimentacaoEspecie) {
                case "carnívoro":
                case "herbívoro":
                case "omnívoro":

                    if (alimento.getNumeroBananasON() > 0) {
                        int bananasConsumidas = bananasConsumidasPorJogador.getOrDefault(jogador.getId(), 0);

                        if (bananasConsumidas == 0) {
                            alteracaoEnergia = 40;
                            bananasConsumidasPorJogador.put(jogador.getId(), bananasConsumidas + 1);

                        } else if (bananasConsumidas == 1) {
                            alteracaoEnergia = -40;
                        }

                        alimento.setNumeroBananasON(alimento.getNumeroBananasON() - 1);
                    }
                    break;
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
            A tooltip deve mostrar “Bananas : <N> : + 40 energia”, em que N é o número de bananas disponíveis
         */

        //int numBananas = 0;
        return "Bananas : " + numeroBananasON + " : + 40 energia" ;
    }

    // COPORTAMENTO PARA AGUA / ERVA / CARNE / COGUMELO
    @Override
    public int consumir(String tipoAlimentacaoEspecie, Jogador jogador) {
        return 0;
    }

    @Override
    public int consumir(String tipoAlimentacaoEspecie, Jogador jogador, int turnoAtual, Alimento alimento) {
        return 0;
    }
}
