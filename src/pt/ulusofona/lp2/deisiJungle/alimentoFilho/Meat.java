package pt.ulusofona.lp2.deisiJungle.alimentoFilho;

import pt.ulusofona.lp2.deisiJungle.Food;
import pt.ulusofona.lp2.deisiJungle.Player;

import java.util.Map;

public class Meat extends Food {

    public Meat(String id, int posicaoAlimento) {
        super(id, posicaoAlimento);

        this.id = id;
        this.nome = "Meat";
        this.imagem = "meat.png";
        this.posicaoAlimento = posicaoAlimento;
        isCarneToxica = false;
    }

    /**
     * Efeitos ao consumir carne
     */
    @Override
    public int consumir(String tipoAlimentacaoEspecie, Player player, int turnoAtual, Food food) {
        int alteracaoEnergia = 0;
        if (getId().equals("c")) {
            // Só é comestível nas primeiras 12 jogadas
            if (turnoAtual <= 12) {
                // Se ingerido por carnívoros (ex: Leão) ou omnívoros (ex: Tarzan)...
                switch (tipoAlimentacaoEspecie) {
                    case "carnívoro", "omnívoro" -> {
                        // aumenta a energia em 50 unidades
                        alteracaoEnergia = 50;
                    }
                }
            } else {
                // A partir daí é tóxica. — se for ingerida, reduz para metade a energia do animal
                food.setCarneToxica(true);

                switch (tipoAlimentacaoEspecie) {
                    case "carnívoro", "omnívoro" -> {
                        int diminuiEnergia = player.getEspecie().getCurrentEnergy() / 2;
                        alteracaoEnergia = -diminuiEnergia;
                    }
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
            ○ A tooltip deve mostrar “Meat : +- 50 energia : <N> jogadas”, em que <N>
                representa o número de jogadas já efetuadas. Caso a carne já esteja tóxica,
                deve mostrar “Meat toxica”
         */

        if (numroJogadasCarne <= 12) {
            return "Meat : + 50 energia : " + numroJogadasCarne + " jogadas";
        } else {
            return "Meat toxica";
        }
    }


    // COPORTAMENTO PARA AGUA / ERVA / BANANA
    @Override
    public int consumir(String tipoAlimentacaoEspecie, Player player) {
        return 0;
    }

    @Override
    public int consumir(String tipoAlimentacaoEspecie, Player player, Food food, Map<Integer, Integer> bananasConsumidasPorJogador) {
        return 0;
    }

}
