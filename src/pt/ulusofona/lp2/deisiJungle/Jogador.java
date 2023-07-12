package pt.ulusofona.lp2.deisiJungle;


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

    public void caracterizarEspecieJogador(Jogador jogadorAtual) {

        switch (idEspecie) {
            case "E" -> {
                Especie jogadorElefante = new Especie(idEspecie, jogadorAtual.getEspecie().getNome(), jogadorAtual.getEspecie().getImagem(),
                        180, 4, 10, 1, 6);
                jogadorAtual.setEspecie(jogadorElefante);
                jogadorAtual.setEnergiaAtual(jogadorElefante.getEnergiaInicial());
                jogadorAtual.getEspecie().setConsumoEnergia(jogadorElefante.getConsumoEnergia());
                jogadorAtual.getEspecie().setGanhoEnergiaDescanso(jogadorElefante.getGanhoEnergiaDescanso());
                jogadorAtual.getEspecie().setVelocidadeMinima(jogadorElefante.getVelocidadeMinima());
                jogadorAtual.getEspecie().setVelocidadeMaxima(jogadorElefante.getVelocidadeMaxima());
            }
            case "L" -> {
                Especie jogadorLeao = new Especie(idEspecie, jogadorAtual.getEspecie().getNome(), jogadorAtual.getEspecie().getImagem(),
                        80, 2, 10, 4, 6);
                jogadorAtual.setEspecie(jogadorLeao);
                jogadorAtual.setEnergiaAtual(jogadorLeao.getEnergiaInicial());
                jogadorAtual.getEspecie().setConsumoEnergia(jogadorLeao.getConsumoEnergia());
                jogadorAtual.getEspecie().setGanhoEnergiaDescanso(jogadorLeao.getGanhoEnergiaDescanso());
                jogadorAtual.getEspecie().setVelocidadeMinima(jogadorLeao.getVelocidadeMinima());
                jogadorAtual.getEspecie().setVelocidadeMaxima(jogadorLeao.getVelocidadeMaxima());
            }
            case "P" -> {
                Especie jogadorPassaro = new Especie(idEspecie, jogadorAtual.getEspecie().getNome(), jogadorAtual.getEspecie().getImagem(),
                        70, 4, 50, 5, 6);
                jogadorAtual.setEspecie(jogadorPassaro);
                jogadorAtual.setEnergiaAtual(jogadorPassaro.getEnergiaInicial());
                jogadorAtual.getEspecie().setConsumoEnergia(jogadorPassaro.getConsumoEnergia());
                jogadorAtual.getEspecie().setGanhoEnergiaDescanso(jogadorPassaro.getGanhoEnergiaDescanso());
                jogadorAtual.getEspecie().setVelocidadeMinima(jogadorPassaro.getVelocidadeMinima());
                jogadorAtual.getEspecie().setVelocidadeMaxima(jogadorPassaro.getVelocidadeMaxima());
            }
            case "T" -> {
                Especie jogadorTartaruga = new Especie(idEspecie, jogadorAtual.getEspecie().getNome(), jogadorAtual.getEspecie().getImagem(),
                        150, 1, 5, 1, 3);
                jogadorAtual.setEspecie(jogadorTartaruga);
                jogadorAtual.setEnergiaAtual(jogadorTartaruga.getEnergiaInicial());
                jogadorAtual.getEspecie().setConsumoEnergia(jogadorTartaruga.getConsumoEnergia());
                jogadorAtual.getEspecie().setGanhoEnergiaDescanso(jogadorTartaruga.getGanhoEnergiaDescanso());
                jogadorAtual.getEspecie().setVelocidadeMinima(jogadorTartaruga.getVelocidadeMinima());
                jogadorAtual.getEspecie().setVelocidadeMaxima(jogadorTartaruga.getVelocidadeMaxima());
            }
            case "Z" -> {
                Especie jogadorTarzan = new Especie(idEspecie, jogadorAtual.getEspecie().getNome(), jogadorAtual.getEspecie().getImagem(),
                        70, 2, 20, 1, 6);
                jogadorAtual.setEspecie(jogadorTarzan);
                jogadorAtual.setEnergiaAtual(jogadorTarzan.getEnergiaInicial());
                jogadorAtual.getEspecie().setConsumoEnergia(jogadorTarzan.getConsumoEnergia());
                jogadorAtual.getEspecie().setGanhoEnergiaDescanso(jogadorTarzan.getGanhoEnergiaDescanso());
                jogadorAtual.getEspecie().setVelocidadeMinima(jogadorTarzan.getVelocidadeMinima());
                jogadorAtual.getEspecie().setVelocidadeMaxima(jogadorTarzan.getVelocidadeMaxima());
            }
            case "U" -> {
                Especie jogadorUnicornio = new Especie(idEspecie, jogadorAtual.getEspecie().getNome(), jogadorAtual.getEspecie().getImagem(),
                        200, 8, 20, 3, 6);
                jogadorAtual.setEspecie(jogadorUnicornio);
                jogadorAtual.setEnergiaAtual(jogadorUnicornio.getEnergiaInicial());
                jogadorAtual.getEspecie().setConsumoEnergia(jogadorUnicornio.getConsumoEnergia());
                jogadorAtual.getEspecie().setGanhoEnergiaDescanso(jogadorUnicornio.getGanhoEnergiaDescanso());
                jogadorAtual.getEspecie().setVelocidadeMinima(jogadorUnicornio.getVelocidadeMinima());
                jogadorAtual.getEspecie().setVelocidadeMaxima(jogadorUnicornio.getVelocidadeMaxima());
            }
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