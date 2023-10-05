package pt.ulusofona.lp2.deisiJungle.especieFilho;

import pt.ulusofona.lp2.deisiJungle.Specie;

public class Lion extends Specie {

    public Lion() {
        this.id = "L";
        this.name = "Lion";
        this.image = "lion.png";
        this.typeFeedSpecies = "carnívoro";
        this.currentEnergy = 80;
        this.energyConsumption = 2;
        this.gainEnergyRest = 10;
        this.minimumSpeed = 4;
        this.maximumSpeed = 6;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int getCurrentEnergy() {
        return currentEnergy;
    }

    @Override
    public void definirEnergiaAtual(int energiaAtual) {
        this.currentEnergy = energiaAtual;
    }

    @Override
    public int getEnergyConsumption() {
        return energyConsumption;
    }

    @Override
    public void definirConsumoEnergia(int consumoEnergia) {
        this.energyConsumption = consumoEnergia;
    }

    @Override
    public int getGainEnergyRest() {
        return gainEnergyRest;
    }

    @Override
    public void definirGanhoEnergiaDescanso(int ganhoEnergiaDescanso) {
        this.gainEnergyRest = ganhoEnergiaDescanso;
    }

    @Override
    public int getMinimumSpeed() {
        return minimumSpeed;
    }

    @Override
    public void definirVelocidadeMinima(int velocidadeMinima) {
        this.minimumSpeed = velocidadeMinima;
    }

    @Override
    public int getMaximumSpeed() {
        return maximumSpeed;
    }

    @Override
    public void definirVelocidadeMaxima(int velocidadeMaxima) {
        this.maximumSpeed = velocidadeMaxima;
    }

    @Override
    public String getTypeFeedSpecies() {
        return typeFeedSpecies;
    }

    @Override
    public String defineSpeciesFeedType(String tipoAlimentacaoDaEspecie) {
        this.typeFeedSpecies = tipoAlimentacaoDaEspecie;
        return tipoAlimentacaoDaEspecie;
    }

    @Override
    public String toString() {
        return "Specie: " + name + "\n" +
                "Tipo de alimentação: " + typeFeedSpecies + "\n" +
                "id: " + id + "\n" +
                "Energia: " + currentEnergy + "\n" +
                "Consumo de energia: " + energyConsumption + "\n" +
                "Ganho de Energia Descanso: " + gainEnergyRest + "\n" +
                "Velocidade mínima: " + minimumSpeed + "\n" +
                "Velocidade máxima: " + maximumSpeed + "\n" ;
    }
}
