package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.especieFilho.*;

import java.util.HashMap;

public class Player {
    private int id;
    private String nome;
    private String idEspecie;
    private int posicaoAtual;
    private int numeroPosicoesPercorridas;
    private int numeroAlimento;
    private int numJogadoresEmJogo;
    private Specie specie;

    public Player() {
    }

    public Player(int id, String nome, String idEspecie, int posicaoAtual, Specie specie) {
        this.id = id;
        this.nome = nome;
        this.idEspecie = idEspecie;
        this.posicaoAtual = posicaoAtual;
        this.specie = specie;
        this.numeroPosicoesPercorridas = 0;
    }

    /**
     * Caracterizar a Specie do Player
     */
    public void caracterizarEspecieJogador(Player playerAtual) {

        // HashMap to map species IDs and instances of the Specie class.
        HashMap<String, Specie> especies = new HashMap<>();

        especies.put("E", new Elephant());
        especies.put("L", new Lion());
        especies.put("T", new Turtle());
        especies.put("P", new Bird());
        especies.put("Z", new Tarzan());
        especies.put("U", new Unicorn());
        especies.put("G", new Giraffe());

        // get the instance that corresponds to the speciesid
        Specie specieJogador = especies.get(idEspecie);

        if (specieJogador != null) {
            playerAtual.setEspecie(specieJogador);
            playerAtual.getEspecie().definirEnergiaAtual(specieJogador.getCurrentEnergy());
            playerAtual.getEspecie().definirConsumoEnergia(specieJogador.getEnergyConsumption());
            playerAtual.getEspecie().definirGanhoEnergiaDescanso(specieJogador.getGainEnergyRest());
            playerAtual.getEspecie().definirVelocidadeMinima(specieJogador.getMinimumSpeed());
            playerAtual.getEspecie().definirVelocidadeMaxima(specieJogador.getMaximumSpeed());
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

    public Specie getEspecie() {
        return specie;
    }

    public void setEspecie(Specie specie) {
        this.specie = specie;
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
        return id + ":" + nome + ":" + idEspecie + ":" + specie.getCurrentEnergy()
                + ":" + posicaoAtual + ":" + numeroPosicoesPercorridas + ":" + numeroAlimento;
    }
}