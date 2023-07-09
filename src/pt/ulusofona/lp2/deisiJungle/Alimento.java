package pt.ulusofona.lp2.deisiJungle;

public class Alimento {

    String id;
    String nome;
    String imagem;
    int posicaoAlimento;

    public Alimento(String id, int posicaoAlimento) {
        this.id = id;
        this.posicaoAlimento = posicaoAlimento;
    }

    public Alimento() {
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }

    public int getPosicaoAlimento() {
        return posicaoAlimento;
    }

    @Override
    public String toString() {

        return id + ':' + posicaoAlimento;
    }
}
