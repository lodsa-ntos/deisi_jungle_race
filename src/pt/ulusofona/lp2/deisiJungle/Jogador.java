package pt.ulusofona.lp2.deisiJungle;

public class Jogador {
    int id;
    String nome;
    String idEspecie;
    int posicaoAtual;
    int energiaAtual;
    int turnoAtual;

    public Jogador(int id, String nome, String idEspecie, int posicaoAtual, int energiaAtual) {
        this.id = id;
        this.nome = nome;
        this.idEspecie = idEspecie;
        this.posicaoAtual = posicaoAtual;
        this.energiaAtual = energiaAtual;
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

    public void setTurnoAtual(int turnoAtual) {
        this.turnoAtual = turnoAtual;
    }

    @Override
    public String toString() {
        return id + ":" + nome + ":" + idEspecie + ":" + energiaAtual + ":" + posicaoAtual;
    }

}
