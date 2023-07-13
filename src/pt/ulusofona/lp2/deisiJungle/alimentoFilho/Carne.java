package pt.ulusofona.lp2.deisiJungle.alimentoFilho;

import pt.ulusofona.lp2.deisiJungle.Alimento;
import pt.ulusofona.lp2.deisiJungle.Jogador;

import java.util.Random;

public class Carne extends Alimento {

    public Carne(String id, int posicaoAlimento) {
        super(id, posicaoAlimento);

        this.id = id;
        this.nome = "Carne";
        this.imagem = "meat.png";
        this.posicaoAlimento = posicaoAlimento;
        isCarneToxica = false;
    }

    /**
     * Efeitos ao consumir carne
     */

    /*
    @Override
    protected int obterEfeitosConsumo(String tipoAlimentacaoEspecie, int energiaEspecie) {

        /*
        ● Carne (identificador: ‘c’, imagem: ‘meat.png’)
            ○ Se ingerido por carnívoros (ex: Leão) ou omnívoros (ex: Tarzan), aumenta a energia em 50 unidades
            ○ Os herbívoros ignoram esta comida, por isso não lhes acontece nada.
            ○ Deteriora-se à medida que o tempo passa. Só é comestível nas primeiras 12
            jogadas. A partir daí é tóxica — se fôr ingerida, reduz para metade a energia do
            animal.


    int aumentarEnergia = 50;

        return switch (tipoAlimentacaoEspecie) {
        case "carnívoro", "omnívoro" -> (energiaEspecie + aumentarEnergia);
        default -> throw new IllegalArgumentException("");
    };
}
     */

    public void consumirCarne(Jogador jogador, int turnosRestantes, Carne carne) {

        // Só é comestível nas primeiras 12 jogadas
        if (turnosRestantes <= 12) {

            carne.setNumroJogadasCarne(turnosRestantes);

            // Se ingerido por carnívoros (ex: Leão) ou omnívoros (ex: Tarzan)...
            if (jogador.getEspecie().getTipoAlimentacaoDaEspecie().equals("carnívoro") ||
                    jogador.getEspecie().getTipoAlimentacaoDaEspecie().equals("omnívoro")) {

                // aumenta a energia em 50 unidades
                jogador.aumentarEnergia(50);

            } else {
                // Os herbívoros ignoram esta comida, por isso não lhes acontece nada.
                jogador.manterEnergia(0);
            }

        } else {

            carne.setNumroJogadasCarne(turnosRestantes);

            // A partir daí é tóxica — se fôr ingerida, reduz para metade a energia do animal
            isCarneToxica = true;
            carne.setCarneToxica(true);

            if (jogador.getEspecie().getTipoAlimentacaoDaEspecie().equals("carnívoro") ||
                    jogador.getEspecie().getTipoAlimentacaoDaEspecie().equals("omnívoro")) {

                int energia = (jogador.getEnergiaAtual() / 2);
                jogador.diminuirEnergia(--energia);

            } else {
                // Os herbívoros ignoram esta comida, por isso não lhes acontece nada.
                jogador.manterEnergia(0);
            }
        }
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
            ○ A tooltip deve mostrar “Carne : +- 50 energia : <N> jogadas”, em que <N>
                representa o número de jogadas já efetuadas. Caso a carne já esteja tóxica,
                deve mostrar “Carne toxica”
         */

        if (isCarneToxica()) {

            return "Carne toxica";

        } else {

            return "Carne : +- 50 energia : " + numroJogadasCarne + " jogadas";

        }
    }
}
