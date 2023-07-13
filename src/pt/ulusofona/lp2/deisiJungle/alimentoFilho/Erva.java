package pt.ulusofona.lp2.deisiJungle.alimentoFilho;

import pt.ulusofona.lp2.deisiJungle.Alimento;

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
    @Override
    public int obterEfeitosConsumo(String tipoAlimentacaoEspecie) {

        /*
        ● Erva (identificador: ‘e’, imagem: ‘grass.png’)
            ○ Se ingerido por herbívoros ou omnívoros, aumenta a energia em 20 unidades
            ○ Se ingerido por carnívoros, reduz a energia em 20 unidades
            ○ A tooltip deve mostrar “Erva : +- 20 energia”.
         */

        int aumentarEnergia = 20;
        int reduzirEnergia = -20;

        return switch (tipoAlimentacaoEspecie) {
            case "herbívoro", "omnívoro" -> aumentarEnergia;
            case "carnívoro" -> reduzirEnergia;
            default -> throw new IllegalArgumentException();
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
    public String toolTip() {
        return "Erva : +- 20 energia";
    }
}
