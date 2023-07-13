package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.especieFilho.*;

import java.util.HashMap;

public class Jogador {
    private final int id;
    private final String nome;
    private final String idEspecie;
    private int posicaoAtual;
    private int energiaAtual;
    private Especie especie;
    private int turnoAtual;

    public Jogador(int id, String nome, String idEspecie, int posicaoAtual, Especie especie) {
        this.id = id;
        this.nome = nome;
        this.idEspecie = idEspecie;
        this.posicaoAtual = posicaoAtual;
        this.especie = especie;
    }

    /**
     * Caracterizar a Especie do Jogador
     */
    public void caracterizarEspecieJogador(Jogador jogadorAtual) {

        // HashMap para mapear os IDs das espécie e às instâncias da classe Especie.
        HashMap<String, Especie> especies = new HashMap<>();

        especies.put("E", new Elefante());
        especies.put("L", new Leao());
        especies.put("T", new Tartaruga());
        especies.put("P", new Passaro());
        especies.put("Z", new Tarzan());
        especies.put("U", new Unicornio());

        //  obter a instância que correspondente à idEspecie
        Especie especieJogador = especies.get(idEspecie);

        if (especieJogador != null) {
            jogadorAtual.setEspecie(especieJogador);
            jogadorAtual.setEnergiaAtual(especieJogador.getEnergiaInicial());
            jogadorAtual.getEspecie().setConsumoEnergia(especieJogador.getConsumoEnergia());
            jogadorAtual.getEspecie().setGanhoEnergiaDescanso(especieJogador.getGanhoEnergiaDescanso());
            jogadorAtual.getEspecie().setVelocidadeMinima(especieJogador.getVelocidadeMinima());
            jogadorAtual.getEspecie().setVelocidadeMaxima(especieJogador.getVelocidadeMaxima());
        }
    }

    public final int getId() {
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

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    @Override
    public String toString() {
        return id + ":" + nome + ":" + idEspecie + ":" + energiaAtual + ":" + posicaoAtual;
    }

}