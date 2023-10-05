package pt.ulusofona.lp2.deisiJungle.alimentoFilho;

import pt.ulusofona.lp2.deisiJungle.Food;
import pt.ulusofona.lp2.deisiJungle.Player;

import java.util.Map;

public class BunchesOfBanana extends Food {

    public BunchesOfBanana(String id, int posicaoAlimento) {
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
    public int consumir(String tipoAlimentacaoEspecie, Player player, Food food, Map<Integer, Integer> bananasConsumidasPorJogador) {
        int alteracaoEnergia = 0;

        if (food.getId().equals("b")) {
            switch (tipoAlimentacaoEspecie) {
                case "carnívoro":
                case "herbívoro":
                case "omnívoro":

                    if (food.getNumeroBananasON() > 0) {
                        int bananasConsumidas = bananasConsumidasPorJogador.getOrDefault(player.getId(), 0);

                        if (bananasConsumidas == 0) {
                            alteracaoEnergia = 40;
                            bananasConsumidasPorJogador.put(player.getId(), bananasConsumidas + 1);

                        } else if (bananasConsumidas == 1) {
                            alteracaoEnergia = -40;
                        }

                        food.setNumeroBananasON(food.getNumeroBananasON() - 1);
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
            A tooltip deve mostrar “Bananas : <N> : + 40 energia”, em que N é o número de bananas disponíveis
         */

        //int numBananas = 0;
        return "Bananas : " + numeroBananasON + " : + 40 energia" ;
    }

    // COPORTAMENTO PARA AGUA / ERVA / CARNE / COGUMELO
    @Override
    public int consumir(String tipoAlimentacaoEspecie, Player player) {
        return 0;
    }

    @Override
    public int consumir(String tipoAlimentacaoEspecie, Player player, int turnoAtual, Food food) {
        return 0;
    }
}
