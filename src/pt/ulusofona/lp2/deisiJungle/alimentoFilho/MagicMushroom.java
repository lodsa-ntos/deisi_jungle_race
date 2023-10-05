package pt.ulusofona.lp2.deisiJungle.alimentoFilho;

import pt.ulusofona.lp2.deisiJungle.Food;
import pt.ulusofona.lp2.deisiJungle.Player;

import java.util.Map;
import java.util.Random;

public class MagicMushroom extends Food {

    public MagicMushroom(String id, int posicaoAlimento) {
        super(id, posicaoAlimento);

        this.id = id;
        this.nome = "Cogumelo Magico";
        this.imagem = "mushroom.png";
        this.posicaoAlimento = posicaoAlimento;
        random = new Random();
        numeroAleatorioCog = random.nextInt(41) + 10; // Gerar um número aleatório entre 10 e 50
    }

    /**
     * Efeitos ao consumir cogumelos magicos
     */
    @Override
    public int consumir(String tipoAlimentacaoEspecie, Player player, int turnoAtual, Food food) {
        int alteracaoEnergia = 0;

        if (food.getId().equals("m")) {
            // Todos os animais podem ingerir -> "carnívoro", "herbívoro", "omnívoro"
            switch (tipoAlimentacaoEspecie) {
                case "carnívoro", "herbívoro", "omnívoro" -> {
                    int energiaJogador = player.getEspecie().getCurrentEnergy();
                    float valorAlteracaoEnergia = (float) food.getNumeroAleatorioCog() / 100;

                    // Se comerem o cogumelo nas jogadas pares, aumentam em N% a energia
                    if (turnoAtual % 2 == 0) {
                        alteracaoEnergia = (int) (energiaJogador * valorAlteracaoEnergia);

                        // Se comerem o cogumelo nas jogadas ímpares, reduzem em N% a energia
                    } else {
                        // Torna-se venenoso e reduzem em N% a sua energia
                        food.setVenenoso(true);
                        alteracaoEnergia = -(int) (energiaJogador * valorAlteracaoEnergia);
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
            ○ A tooltip deve mostrar “Cogumelo Magico: +- <N>% energia”, em que N é o número gerado aleatoriamente.
         */

        //int numeroAleatorio = 0;
        return "Cogumelo Magico : +- " + numeroAleatorioCog + "% energia";
    }


    // COPORTAMENTO PARA AGUA / ERVA / BANANA
    @Override
    public int consumir(String tipoAlimentacaoEspecie, Player player, Food food, Map<Integer, Integer> bananasConsumidasPorJogador) {
        return 0;
    }

    @Override
    public int consumir(String tipoAlimentacaoEspecie, Player player) {
        return 0;
    }
}
