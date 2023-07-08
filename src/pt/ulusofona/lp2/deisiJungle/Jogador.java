package pt.ulusofona.lp2.deisiJungle;

public class Jogador {
    private final int id;
    private final String nome;
    private final String idEspecie;
    private int posicaoAtual;
    private int energiaAtual;
    private int turnoAtual;

    public Jogador(int id, String nome, String idEspecie, int posicaoAtual) {
        this.id = id;
        this.nome = nome;
        this.idEspecie = idEspecie;
        this.posicaoAtual = posicaoAtual;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIdEspecie() {
        return idEspecie;
    }

    public int getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(int posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public int getEnergiaAtual() {
        return energiaAtual;
    }

    public void setEnergiaAtual(int energiaAtual) {
        this.energiaAtual = energiaAtual;
    }

    public int getTurnoAtual() {
        return turnoAtual;
    }

    @Override
    public String toString() {
        return id + ":" + nome + ":" + idEspecie + ":" + energiaAtual + ":" + posicaoAtual;
    }

}
