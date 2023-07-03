package pt.ulusofona.lp2.deisiJungle;

public class Jogador {
    int id;
    String nome;
    String especie;

    public Jogador(int id, String nome, String especie) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    @Override
    public String toString() {
        return id + ":" + nome + ":" + especie;
    }

}
