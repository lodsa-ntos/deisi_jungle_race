package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.especieFilho.*;

import java.util.HashMap;

public class Jogador {
    private int id;
    private String nome;
    private String idEspecie;
    private int posicaoAtual;
    private int numeroPosicoesPercorridas;
    private int numeroAlimento;
    private int numJogadoresEmJogo;
    private Especie especie;

    public Jogador() {
    }

    public Jogador(int id, String nome, String idEspecie, int posicaoAtual, Especie especie) {
        this.id = id;
        this.nome = nome;
        this.idEspecie = idEspecie;
        this.posicaoAtual = posicaoAtual;
        this.especie = especie;
        this.numeroPosicoesPercorridas = 0;
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
        especies.put("G", new Girafa());

        //  obter a instância que correspondente à idEspecie
        Especie especieJogador = especies.get(idEspecie);

        if (especieJogador != null) {
            jogadorAtual.setEspecie(especieJogador);
            jogadorAtual.getEspecie().definirEnergiaAtual(especieJogador.getEnergiaAtual());
            jogadorAtual.getEspecie().definirConsumoEnergia(especieJogador.getConsumoEnergia());
            jogadorAtual.getEspecie().definirGanhoEnergiaDescanso(especieJogador.getGanhoEnergiaDescanso());
            jogadorAtual.getEspecie().definirVelocidadeMinima(especieJogador.getVelocidadeMinima());
            jogadorAtual.getEspecie().definirVelocidadeMaxima(especieJogador.getVelocidadeMaxima());
        }
    }

    public final int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(String idEspecie) {
        this.idEspecie = idEspecie;
    }

    public int getPosicaoAtual() {
        return posicaoAtual;
    }

    public void alterarPosicaoAtual(int posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public int getNumeroPosicoesPercorridas() {
        return numeroPosicoesPercorridas;
    }

    public void incrementarNumeroPosicoesPercorridas(int numeroPosicoesMovimentadas) {
        this.numeroPosicoesPercorridas += numeroPosicoesMovimentadas;
    }

    public int getNumeroAlimento() {
        return numeroAlimento;
    }

    public void aumentarNumAlimentoApanhado(int numeroAlimento) {
        this.numeroAlimento += numeroAlimento;
    }

    public int getNumJogadoresEmJogo() {
        return numJogadoresEmJogo;
    }

    public void saberNumJogadoresEmJogo(int numJogadoresEmJogo) {
        this.numJogadoresEmJogo = numJogadoresEmJogo;
    }

    @Override
    public String toString() {
        return id + ":" + nome + ":" + idEspecie + ":" + especie.getEnergiaAtual()
                + ":" + posicaoAtual + ":" + numeroPosicoesPercorridas + ":" + numeroAlimento;
    }
}