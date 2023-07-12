package pt.ulusofona.lp2.deisiJungle;

public class Especie {

    private int energiaInicial;
    private int consumoEnergia;
    private int ganhoEnergiaDescanso;
    private int velocidadeMaxima;
    private int velocidadeMinima;
    private String id;
    private String nome;
    private String imagem;

    public Especie(String id, String nome, String imagem, int energiaInicial, int consumoEnergia,
                   int ganhoEnergiaDescanso, int velocidadeMinima, int velocidadeMaxima) {

        this.id = id;
        this.nome = nome;
        this.imagem = imagem;
        this.energiaInicial = energiaInicial;
        this.consumoEnergia = consumoEnergia;
        this.ganhoEnergiaDescanso = ganhoEnergiaDescanso;
        this.velocidadeMinima = velocidadeMinima;
        this.velocidadeMaxima = velocidadeMaxima;
    }

    public Especie() {
    }

    public void identificarEspecie(String id) {
        switch (id) {
            case "E" -> {
                this.id = id;
                this.nome = "Elefante";
                this.imagem = "elephant.png";
                this.energiaInicial = 180;
                this.consumoEnergia = 4;
                this.ganhoEnergiaDescanso = 10;
                this.velocidadeMinima = 1;
                this.velocidadeMaxima = 6;
            }
            case "L" -> {
                this.id = id;
                this.nome = "Leão";
                this.imagem = "ion.png";
                this.energiaInicial = 80;
                this.consumoEnergia = 2;
                this.ganhoEnergiaDescanso = 10;
                this.velocidadeMinima = 4;
                this.velocidadeMaxima = 6;
            }
            case "T" -> {
                this.id = id;
                this.nome = "Tartaruga";
                this.imagem = "turtle.png";
                this.energiaInicial = 150;
                this.consumoEnergia = 1;
                this.ganhoEnergiaDescanso = 5;
                this.velocidadeMinima = 1;
                this.velocidadeMaxima = 3;
            }
            case "P" -> {
                this.id = id;
                this.nome = "Pássaro";
                this.imagem = "bird.png";
                this.energiaInicial = 70;
                this.consumoEnergia = 4;
                this.ganhoEnergiaDescanso = 50;
                this.velocidadeMinima = 5;
                this.velocidadeMaxima = 6;
            }
            case "Z" -> {
                this.id = id;
                this.nome = "Tarzan";
                this.imagem = "tarzan.png";
                this.energiaInicial = 70;
                this.consumoEnergia = 2;
                this.ganhoEnergiaDescanso = 20;
                this.velocidadeMinima = 1;
                this.velocidadeMaxima = 6;
            }
            case "U" -> {
                this.id = id;
                this.nome = "Unicórnio";
                this.imagem = "unicorn.png";
                this.energiaInicial = 200;
                this.consumoEnergia = 8;
                this.ganhoEnergiaDescanso = 20;
                this.velocidadeMinima = 3;
                this.velocidadeMaxima = 6;
            }
        }
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getEnergiaInicial() {
        return energiaInicial;
    }

    public void setEnergiaInicial(int energiaInicial) {
        this.energiaInicial = energiaInicial;
    }

    public int getConsumoEnergia() {
        return consumoEnergia;
    }

    public void setConsumoEnergia(int consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
    }

    public int getGanhoEnergiaDescanso() {
        return ganhoEnergiaDescanso;
    }

    public void setGanhoEnergiaDescanso(int ganhoEnergiaDescanso) {
        this.ganhoEnergiaDescanso = ganhoEnergiaDescanso;
    }

    public int getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    public void setVelocidadeMaxima(int velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }

    public int getVelocidadeMinima() {
        return velocidadeMinima;
    }

    public void setVelocidadeMinima(int velocidadeMinima) {
        this.velocidadeMinima = velocidadeMinima;
    }

    @Override
    public String toString() {
        return "Especie: " + nome + "\n" +
                "id: " + id + "\n" +
                "Energia: " + energiaInicial + "\n" +
                "Consumo de energia: " + consumoEnergia + "\n" +
                "Ganho de Energia Descanso: " + ganhoEnergiaDescanso + "\n" +
                "Velocidade mínima: " + velocidadeMinima + "\n" +
                "Velocidade máxima: " + velocidadeMaxima + "\n" ;
    }
}