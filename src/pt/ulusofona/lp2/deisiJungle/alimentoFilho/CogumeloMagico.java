package pt.ulusofona.lp2.deisiJungle.alimentoFilho;

import pt.ulusofona.lp2.deisiJungle.Alimento;

public class CogumeloMagico extends Alimento {

    public CogumeloMagico(String id, int posicaoAlimento) {
        super(id, posicaoAlimento);

        this.id = id;
        this.nome = "Cogumelos magicos";
        this.imagem = "mushroom.png";
        this.posicaoAlimento = posicaoAlimento;
    }

    @Override
    public int obterEfeitosConsumoErva(String tipoAlimentacaoEspecie) {
        return 0;
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
        int numeroAleatorio = 0;
        return "Cogumelo Magico: +- " + numeroAleatorio + "% energia";
    }
}
