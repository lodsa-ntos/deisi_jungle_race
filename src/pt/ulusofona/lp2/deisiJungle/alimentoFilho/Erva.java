package pt.ulusofona.lp2.deisiJungle.alimentoFilho;

import pt.ulusofona.lp2.deisiJungle.Alimento;
import pt.ulusofona.lp2.deisiJungle.Jogador;

public class Erva extends Alimento {

    public Erva(String id, int posicaoAlimento) {

        super(id, posicaoAlimento);

        this.id = id;
        this.nome = "Erva";
        this.imagem = "grass.png";
        this.posicaoAlimento = posicaoAlimento;
    }

    /**
     * Efeitos ao consumir Erva
     */

    /*
     @Override
    public int obterEfeitosConsumo(String tipoAlimentacaoEspecie, int energiaEspecie) {

        /*
        ● Erva (identificador: ‘e’, imagem: ‘grass.png’)
            ○ Se ingerido por herbívoros ou omnívoros, aumenta a energia em 20 unidades
            ○ Se ingerido por carnívoros, reduz a energia em 20 unidades
            ○ A tooltip deve mostrar “Erva : +- 20 energia”.

    int restricaoEnergia = 20;

        return switch (tipoAlimentacaoEspecie) {
        case "herbívoro", "omnívoro" -> (energiaEspecie + restricaoEnergia);
        case "carnívoro" -> (energiaEspecie - restricaoEnergia);
        default -> throw new IllegalArgumentException("");
    };

    }
     */

    public void consumirErva(String tipoAlimentacaoEspecie, Jogador jogador) {
        switch (tipoAlimentacaoEspecie) {
            case "herbívoro", "omnívoro" -> jogador.aumentarEnergia(40);
            case "carnívoro" -> jogador.diminuirEnergia(40);
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
    public String toolTip() {
        /*
            ○ A tooltip deve mostrar “Agua : + 15U|20% energia”.
         */
        return "Erva : +- 20 energia";
    }
}
