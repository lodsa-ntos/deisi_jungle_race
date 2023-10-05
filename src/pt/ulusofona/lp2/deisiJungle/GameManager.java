package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.validar.ValidadorJogador;
import pt.ulusofona.lp2.deisiJungle.validar.ValidadorAlimento;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/*-------------------------------------------------DEISI JUNGLE-----------------------------------------------------
    One day, in a savannah far (far) from here, the animals decided to find out who would be the best athlete among them.
    To do so, they defined a set of sporting events that would compete with each other - whoever won more events would
    be considered the King of the Jungle. Soon the news reached Tarzan, who did not want to stop participating in the
    events, convinced that he would easily win them. The first race is the track race. The animals will gather
    on a track and compete to determine which athlete is the best.
 ------------------------------------------------------------------------------------------------------------------ */

// Class responsible for managing the game
public class GameManager {
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Food> foods = new ArrayList<>();
    private HashMap<Integer,Integer> idPlayersInGame = new HashMap<>();
    private HashMap<Integer,Integer> playersWhoConsumedBanana = new HashMap<>();
    private Set<String> foodsConsumed = new HashSet<>();
    private Player currentPlayer;
    private int finalGamePosition;
    private int initialHouse;
    private int currentShift;
    private int middleHouse;
    private int countTarzan;
    private boolean someoneReachedTheGoal;
    private boolean homeWithFood;

    public GameManager() {}

    public String[][] getSpecies() {
        String [][] especies = new String[7][7];
        especies[0][0] = "E"; // ⇒ species id
        especies[0][1] = "Elephant"; // ⇒ species name
        especies[0][2] = "elephant.png"; // ⇒ species image name
        especies[0][3] = "180"; // ⇒ initial power
        especies[0][4] = "4"; // ⇒ power consumption
        especies[0][5] = "10"; // ⇒ energy gain at rest
        especies[0][6] = "1..6"; // ⇒ speed in the format x y

        especies[1][0] = "L";
        especies[1][1] = "Leão";
        especies[1][2] = "lion.png";
        especies[1][3] = "80";
        especies[1][4] = "2";
        especies[1][5] = "10";
        especies[1][6] = "4..6";

        especies[2][0] = "T";
        especies[2][1] = "Turtle";
        especies[2][2] = "turtle.png";
        especies[2][3] = "150";
        especies[2][4] = "1";
        especies[2][5] = "5";
        especies[2][6] = "1..3";

        especies[3][0] = "P";
        especies[3][1] = "Pássaro";
        especies[3][2] = "bird.png";
        especies[3][3] = "70";
        especies[3][4] = "4";
        especies[3][5] = "50";
        especies[3][6] = "5..6";

        especies[4][0] = "Z";
        especies[4][1] = "Tarzan";
        especies[4][2] = "tarzan.png";
        especies[4][3] = "70";
        especies[4][4] = "2";
        especies[4][5] = "20";
        especies[4][6] = "1..6";

        especies[5][0] = "U";
        especies[5][1] = "Unicórnio";
        especies[5][2] = "unicorn.png";
        especies[5][3] = "200";
        especies[5][4] = "8";
        especies[5][5] = "20";
        especies[5][6] = "3..6";

        especies[6][0] = "G";
        especies[6][1] = "Giraffe";
        especies[6][2] = "giraffe.png";
        especies[6][3] = "150";
        especies[6][4] = "4";
        especies[6][5] = "3";
        especies[6][6] = "2..3";

        return especies;
    }

    public String[][] getFoodTypes() {

        String [][] alimentos = new String[6][3];

        alimentos[0][0] = "e";
        alimentos[0][1] = "Grass";
        alimentos[0][2] = "grass.png";
        alimentos[1][0] = "a";
        alimentos[1][1] = "Água";
        alimentos[1][2] = "water.png";
        alimentos[2][0] = "b";
        alimentos[2][1] = "Cacho de bananas";
        alimentos[2][2] = "bananas.png";
        alimentos[3][0] = "c";
        alimentos[3][1] = "Meat";
        alimentos[3][2] = "meat.png";
        alimentos[4][0] = "m";
        alimentos[4][1] = "Cogumelos magicos";
        alimentos[4][2] = "mushroom.png";
        alimentos[5][0] = "t";
        alimentos[5][1] = "Árvores";
        alimentos[5][2] = "trees.png";

        return alimentos;
    }

    public void createInitialJungle(int jungleSize, String[][] playersInfo, String[][] foodsInfo) throws InvalidInitialJungleException {
        updateMeatPlayCount(0);
        // Each time the game is created, the program will reset the variables to their initial value
        increaseReset();
        currentShift = 1;
        finalGamePosition = jungleSize;

        // TODO THE MAP — two positions for each player
        ValidadorJogador.validarDimensaoMapa(finalGamePosition, playersInfo.length);

        // TODO PLAYER — The game will have between 2 and 4 players
        ValidadorJogador.validarNumJogadorEmJogo(playersInfo.length);

        /**
         * PLAYERS
         * loop’ foreach to save playersInfo information
         */
        for (String[] infoJogador: playersInfo) {

            String oldIDJogador = infoJogador[0];
            String nomeJogador = infoJogador[1];
            String idEspecieJogador = infoJogador[2];

            // TODO IDs - is null or empty?
            if (oldIDJogador == null || oldIDJogador.isEmpty()) {
                throw new InvalidInitialJungleException("O ID do jogador é null ou vazio, logo, não é válido.", true, false);
            }

            // TODO IDs — is a numeric value?
            boolean isNumericValue = oldIDJogador.matches("-?\\d+(\\.\\d+)?");

            if (!isNumericValue) {
                throw new InvalidInitialJungleException("O ID do jogador não é válido.", true, false);
            }

            int idJogador = Integer.parseInt(oldIDJogador);

            // TODO IDs — there cannot be two players with the same id
            validarNumeroIDs(idPlayersInGame, idJogador);

            // TODO NOMES — cannot be null or empty
            ValidadorJogador.validarNomeJogadores(nomeJogador);

            // TODO TARZAN — There can only be one Tarzan player competing
            validarEspecieTarzan(idEspecieJogador);

            // TODO ESPÉCIES — The species must be one of those returned from the getSpecies() function
            ValidadorJogador.validarEspecieJogador(idEspecieJogador, getSpecies());


            Specie specieJogadorEmJogo = Specie.identifySpecie(idEspecieJogador);
            currentPlayer = new Player(idJogador, nomeJogador, idEspecieJogador, initialHouse, specieJogadorEmJogo);
            players.add(currentPlayer);
            players.sort(Comparator.comparing(Player::getId)); // Sort IDs in ascending order
            currentPlayer.caracterizarEspecieJogador(currentPlayer);

            //jogadorAtual = jogadores.get(turnoAtual % jogadores.size());
            // Set the current player as first in the list
            currentPlayer = players.get(0);

            if (players.size() >= 2) {
                currentPlayer.saberNumJogadoresEmJogo(players.size());
            }

        }

        /**
         * FOODS
         * ‘loop’ foreach to save information from foodsInfo
         */
        //System.out.println("ALIMENTOS");
        for (String[] infoAlimento: foodsInfo) {

            String idTipo = infoAlimento[0];
            String oldPosicaoAlimento = infoAlimento[1];
            //int posicaoAlimento = Integer.parseInt(infoAlimento[1]);

            // TODO IDs - is null or empty?
            if (oldPosicaoAlimento == null || oldPosicaoAlimento.isEmpty()) {
                throw new InvalidInitialJungleException("A posição do alimento é null ou vazio, logo, não é válida.", false, true);
            }

            // TODO IDs - is a numeric value?
            boolean isNumericValue = oldPosicaoAlimento.matches("-?\\d+(\\.\\d+)?");

            if (!isNumericValue) {
                throw new InvalidInitialJungleException("A posição do alimento não é válida.", false, true);
            }

            int posicaoAtualAlimento = Integer.parseInt(oldPosicaoAlimento);

            // TODO THE ID - it has to be one of those returned by the getFoodTypes() function
            ValidadorAlimento.validarIDAlimento(idTipo, getFoodTypes());

            // TODO POSITION - Food must be positioned within the boundaries of the land
            ValidadorAlimento.validarPosicaoAlimentos(posicaoAtualAlimento, initialHouse, finalGamePosition);

            Food tipoFood = Food.identificarAlimento(idTipo, posicaoAtualAlimento);

            foods.add(tipoFood);
        }
    }

    public void createInitialJungle(int jungleSize, String[][] playersInfo) throws InvalidInitialJungleException {
        String[][] foodsInfo = new String[0][2];
        createInitialJungle(jungleSize, playersInfo, foodsInfo);
    }

    public int[] getPlayerIds(int squareNr) {

        ArrayList<Player> listaIdsJogadores = new ArrayList<>();

        /// RESTRICTIONS:
        // Return an empty array if squareNr is invalid
        // Return an empty array if there are no players in the indicated position;
        // squareNr — numbers of map squares (map positions)
        if (squareNr < initialHouse || squareNr > finalGamePosition) {
            return new int[0];
        }

        /// REQUIREMENTS:
        // Get the 'ids' of the players of a certain position on the map
        for (Player playerEmJogo : players) {
            if (playerEmJogo.getPosicaoAtual() == squareNr) {
                listaIdsJogadores.add(playerEmJogo);
            }
        }

        // Return an array with player ids from a given position on the map
        int [] idsInJungle = new int[listaIdsJogadores.size()];
        for (int i = 0; i < listaIdsJogadores.size(); i++) {
            // Pass the values from the list to the array
            idsInJungle[i] = listaIdsJogadores.get(i).getId();
        }

        return idsInJungle;
    }

    public String[] getSquareInfo(int squareNr) {

        /// RESTRICTIONS
        // If nrSquare is invalid, the function should return null.
        // squareNr — Map Numbers of Squares (Map Positions)
        if (squareNr < initialHouse || squareNr > finalGamePosition) {
            return null;
        }

        /*
        Each element of the array must have the following information:
            - [0] => Name of the file with the image to be placed in that position
            - [1] => A textual description of what exists in that position (at this stage it can just be "Empty" or "Meta")
            - [2] => A String containing the identifiers of the players who are in that position, separated by
            comma (e.g. "3,5" — players 3 and 5 are there).
         */

        String[] infoPosCaixasNoMapa = new String[3];

        if (squareNr == finalGamePosition) {
            infoPosCaixasNoMapa[0] = "finish.png";
            infoPosCaixasNoMapa[1] = "Meta";
        } else {
            infoPosCaixasNoMapa[0] = "blank.png";
            infoPosCaixasNoMapa[1] = "Vazio";
        }

        infoPosCaixasNoMapa[2] = "";

        for (Food food : foods) {
            int posicaoAlimento = food.getPosicaoAlimento();
            String idAlimento = food.getId();
            String mostrarToolTip = food.toolTip();
            String imagemAlimento = food.getImagem();

            //incrementarTurno();
            //atualizarContagemJogadasCarne(0);

            //TODO should start to return food information, when there is some food in this slot.
            // Show a tooltip when hovering over a food item
            if (squareNr == posicaoAlimento) {
                switch (idAlimento) {
                    case "e", "a", "b", "m", "c", "t" -> infoPosCaixasNoMapa[1] = mostrarToolTip;
                }

                // Show food on the map
                infoPosCaixasNoMapa[0] = imagemAlimento;
                break;
            }
        }

        // [2] => A String containing the identifiers of the players who are in that position, separated by
        // comma (e.g. "3,5" — players 3 and 5 are there).

        String jogadoresNaCasa = "";

        for (Player player : players) {
            if (player.getPosicaoAtual() == squareNr) {
                // if the map position has more than one player,
                if (!jogadoresNaCasa.isEmpty()) {
                    jogadoresNaCasa += ","; // will be separated by comma "3,5"
                }
                // The first player found is added
                jogadoresNaCasa += player.getId();
            }
        }

        // If there is more than one player in a specific position, the comma will be kept.
        // Otherwise, the comma is removed from the last position.
        infoPosCaixasNoMapa[2] = jogadoresNaCasa;

        return infoPosCaixasNoMapa;
    }

    public String[] getPlayerInfo(int playerId) {

        /*
        - [0] => The Player ID
        - [1] => The player's name
        - [2] => The ID of the species associated with the player.
        - [3] => The player's current energy, measured in units of energy
        - [4] => The velocity, in the format "X.. Y" => Minimum Speed = 1..6 = Maximum Speed
         */

        String[] infoJogador = new String[5];

        for (Player player : players) {
            if (player.getId() == playerId) {
                infoJogador[0] = String.valueOf(player.getId());
                infoJogador[1] = player.getNome();
                infoJogador[2] = player.getIdEspecie();
                infoJogador[3] = String.valueOf(player.getEspecie().getCurrentEnergy());
                infoJogador[4] = player.getEspecie().getMinimumSpeed() + ".." + player.getEspecie().getMaximumSpeed();
                return infoJogador;
            }
        }
        return null;
    }

    public String[] getCurrentPlayerInfo() {

        /*
        Returns information about the player who is active in the current turn.
        The information returned is in the same format as the getPlayerInfo() function.
         */

        String[] infoJogadorAtual = new String[5];

        // Switch player
        Player playerAtual = players.get((currentShift - 1) % players.size());

        infoJogadorAtual[0] = Integer.toString(playerAtual.getId());
        infoJogadorAtual[1] = playerAtual.getNome();
        infoJogadorAtual[2] = playerAtual.getIdEspecie();
        infoJogadorAtual[3] = Integer.toString(playerAtual.getEspecie().getCurrentEnergy());
        infoJogadorAtual[4] = playerAtual.getEspecie().getMinimumSpeed() + ".." + playerAtual.getEspecie().getMaximumSpeed();

        return infoJogadorAtual;
    }

    public String[] getCurrentPlayerEnergyInfo(int nrPositions) {

        /*
        Returns energy information to the player who is active in the current turn.

        Energy consumption — the amount of energy units used for the
        animal moves one position. For example, if the player moves 3 positions, he will
        spend 3 * N, where N is the energy consumption of the respective species

        Resting energy gain — the amount of energy units the player
        He wins by not moving forward (either because he decided to do so, or because he no longer has the energy)

       - [0] => What will this player's energy consumption be if he moves <nrPositions>
       - [1] => What will be the energy gain if it stays in place

         */

        String[] infoEnergia = new String[2];

        // Math.abs(nrPositions) if a negative value is output, change it to positive

        // Update current player's turn and move
        // playerCurrent = players.get(turnoCurrent % players.size());

        int casaAtual = currentPlayer.getPosicaoAtual();
        int novaPosicaoJogador = casaAtual + nrPositions;
        int consumoEnergia = currentPlayer.getEspecie().getEnergyConsumption() * Math.abs(nrPositions);
        int ganhoEnergiaDescanso = currentPlayer.getEspecie().getGainEnergyRest();

        boolean casaComAlimento = verificaCasaComAlimentoUnicornio(novaPosicaoJogador);  // Check if the house has food

        if (currentPlayer.getEspecie().getId().equals("U")) {
            if (casaComAlimento) {
                infoEnergia[0] = String.valueOf(consumoEnergia);  // Normal consumption if there is food
            } else {
                int novoConsumoEnergia = consumoEnergia + 2;  // Increases consumption at home without food
                infoEnergia[0] = String.valueOf(novoConsumoEnergia);
            }
        } else {
            infoEnergia[0] = String.valueOf(consumoEnergia);  // For other species, normal consumption
        }

        infoEnergia[1] = String.valueOf(ganhoEnergiaDescanso);  // Energy gain at rest

        return infoEnergia;
    }

    public String[][] getPlayersInfo() {

        /*
        Returns information from all players, in the same format as that returned by the
        getPlayerInfo and getCurrentPlayerInfo functions.
         */

        String [][] infoGeralJogadores = new String[players.size()][5];

        for (int i = 0; i < players.size(); i++) {
            infoGeralJogadores[i][0] = String.valueOf(players.get(i).getId());
            infoGeralJogadores[i][1] = players.get(i).getNome();
            infoGeralJogadores[i][2] = String.valueOf(players.get(i).getIdEspecie());
            infoGeralJogadores[i][3] = String.valueOf(players.get(i).getEspecie().getCurrentEnergy());
            infoGeralJogadores[i][4] = players.get(i).getEspecie().getMinimumSpeed() + ".." + players.get(i).getEspecie().getMaximumSpeed();
        }

        return infoGeralJogadores;
    }

    public MovementResult moveCurrentPlayer(int nrSquares, boolean bypassValidations) {
        // Each turn I alternate the current player according to the number of players in the game
        currentPlayer = players.get((currentShift - 1) % players.size());
        updateMeatPlayCount(currentShift);

        int casaAtual = currentPlayer.getPosicaoAtual(); // CASA DE PARTIDA = 1
        int novaPosicaoJogador = casaAtual + nrSquares; // A + M
        int energiaAtual = currentPlayer.getEspecie().getCurrentEnergy();
        int consumoEnergia = currentPlayer.getEspecie().getEnergyConsumption();

        // If you decide to stay in the position
        if (nrSquares == 0) {
            // Increase the gainEnergy if you decide to stay in the position
            limitarEnergia(false, true, currentPlayer.getEspecie().getGainEnergyRest());
            // Update the shift
            increaseShift();
            // Check if the player has consumed any food
            String alimentoConsumido = verificarConsumoDeAlimento(novaPosicaoJogador);
            if (alimentoConsumido != null) {
                currentPlayer.aumentarNumAlimentoApanhado(1);
                return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou " + alimentoConsumido);
            }
            return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
        }

        // If you don't have enough energy to make the move, you stay in the same house
        MovementResult nivelDeEnergia = jogadorComEnergiaSuficienteParaSeMover(nrSquares, energiaAtual, consumoEnergia);

        // If the power level result is not null, return NO_ENERGY
        if (nivelDeEnergia != null) {
            return nivelDeEnergia;
        }

        // The nrSquares argument must be contained between -6 and 6
        // However, if the bypassValidations parameter has a value of true, the previous rule is not applied.
        if (!isMovimentoValido(nrSquares, novaPosicaoJogador, initialHouse, bypassValidations)) {
            increaseShift();
            return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
        }

        // If the player tries to go beyond the final square of the game, he must stay in the final position of the game
        if (novaPosicaoJogador >= finalGamePosition) {
            // ...the player must be in the final position of the game
            novaPosicaoJogador = finalGamePosition;
            // Player movement to house A + M
            currentPlayer.alterarPosicaoAtual(novaPosicaoJogador);
            someoneReachedTheGoal = true;
            //return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
        }

        // Player movement to house A + M
        currentPlayer.alterarPosicaoAtual(novaPosicaoJogador);

        // Increase the number of distance traveled
        currentPlayer.incrementarNumeroPosicoesPercorridas(Math.abs(nrSquares));

        // Check if the Unicorn player goes to a space with or without food in the new position
        homeWithFood = verificaCasaComAlimentoUnicornio(novaPosicaoJogador);

        // Check if there are players in the middle space for new victory condition
        verificaJogadorNaCasaDoMeio();

        // Set the player's energy when retreating or advancing
        setEnergyOfNumberOfSquare(nrSquares, energiaAtual, consumoEnergia, homeWithFood);

        // If you don't have enough energy to move, you stay in the same house
        MovementResult energyLevel2 = jogadorComEnergiaSuficienteParaSeMover(nrSquares, energiaAtual, consumoEnergia);

        // If the energy level result is not null, return NO_ENERGY
        if (energyLevel2 != null) {
            return energyLevel2;
        }

        // Check what food is consumed
        String alimentoConsumido = verificarConsumoDeAlimento(novaPosicaoJogador);
        MovementResult resultadoConsumo = processarMovimentoParaConsumoDeAlimento(alimentoConsumido, currentPlayer);

        // If the food consumption result is not null, return CAUGHT_FOOD
        if (resultadoConsumo != null) {
            return resultadoConsumo;
        }

        // Update shift
        increaseShift();
        return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
    }

    public String[] getWinnerInfo() {
        String[] infoJogadorVencedor = new String[4];

        calcularCasaDoMeio();
        verificarChegadaAMeta();

        // If a player reached the final position of the game, show the information of the winning player
        if (isSomeoneReachedTheGoal()) {
            players.sort(
                    Comparator.comparingInt(Player::getPosicaoAtual)
                            // flip based on players current position
                            .reversed()
                            // If the positions are the same, sort by ID
                            .thenComparingInt(Player::getId)
            );

            currentPlayer = players.get(0);
            infoJogadorVencedor[0] = String.valueOf(currentPlayer.getId());
            infoJogadorVencedor[1] = currentPlayer.getNome();
            infoJogadorVencedor[2] = currentPlayer.getIdEspecie();
            infoJogadorVencedor[3] = String.valueOf(currentPlayer.getEspecie().getCurrentEnergy());
            return infoJogadorVencedor;
        }

        // If no player reached the goal and there is a large distance between the players in play
        // The player furthest from the goal wins
        if (thereIsAPlayerVeryFarFromTheGoal()) {
            currentPlayer = obterJogadorMaisDistanteDaMeta(players);
            infoJogadorVencedor[0] = String.valueOf(currentPlayer.getId());
            infoJogadorVencedor[1] = currentPlayer.getNome();
            infoJogadorVencedor[2] = currentPlayer.getIdEspecie();
            infoJogadorVencedor[3] = String.valueOf(currentPlayer.getEspecie().getCurrentEnergy());
            return infoJogadorVencedor;
        }

        // TODO New Winner Condition:
        // If no player reached the goal and there is not a large distance between the players in play
        // When two players are present in the “middle square” and there is at least one
        // player between the “middle square” and the goal, the winner of the game is the player with more energy than
        // is located in the “middle house”.
        if (isNovaCondicaoVencedor(players)) {
            currentPlayer = obterVencedorDaCasaDoMeioNovaCondicao(players);
            infoJogadorVencedor[0] = String.valueOf(currentPlayer.getId());
            infoJogadorVencedor[1] = currentPlayer.getNome();
            infoJogadorVencedor[2] = currentPlayer.getIdEspecie();
            infoJogadorVencedor[3] = String.valueOf(currentPlayer.getEspecie().getCurrentEnergy());
            return infoJogadorVencedor;
        }

        return null;
    }

    public ArrayList<String> getGameResults() {
        ArrayList<String> resultados = new ArrayList<>();
        ArrayList<Player> playersInGames = new ArrayList<>();

        int posicaoDeChegada = 0;

        for (Player player : players) {
            if (player.getEspecie().getCurrentEnergy() >= 0) {
                playersInGames.add(player);
            }
        }

        if (isNovaCondicaoVencedor(playersInGames)) {
            // Find the player with the most energy in the "middle house"
            Player vencedorCasaDoMeio = obterVencedorDaCasaDoMeioNovaCondicao(playersInGames);
            processarResultadosNovaCondicaoVencedor(vencedorCasaDoMeio, playersInGames, posicaoDeChegada, resultados);
            return resultados;

        } else {

            if (isSomeoneReachedTheGoal()) {
                for (Player player : playersInGames) {
                    String nome = player.getNome();
                    String nomeEspecie = player.getEspecie().getName();
                    int posicaoAtual = player.getPosicaoAtual();
                    int distancia = player.getNumeroPosicoesPercorridas();
                    int numAlimento = player.getNumeroAlimento();

                    resultados.add("#" + (posicaoDeChegada + 1) + " " + nome + ", " + nomeEspecie + ", " + posicaoAtual
                            + ", " + distancia + ", " + numAlimento);
                    posicaoDeChegada++;
                }

            } else {

                if (thereIsAPlayerVeryFarFromTheGoal()) {
                    ArrayList<Player> jogadoresEmLongaDistancia = new ArrayList<>(playersInGames);
                    for (Player player : playersInGames) {
                        Player playerMaisDistante = obterJogadorMaisDistanteDaMeta(jogadoresEmLongaDistancia);
                        String nome = playerMaisDistante.getNome();
                        String nomeEspecie = playerMaisDistante.getEspecie().getName();
                        int posicaoAtual = playerMaisDistante.getPosicaoAtual();
                        int distancia = playerMaisDistante.getNumeroPosicoesPercorridas();
                        int numAlimento = playerMaisDistante.getNumeroAlimento();

                        resultados.add("#" + (posicaoDeChegada + 1) + " " + nome + ", " + nomeEspecie + ", " + posicaoAtual
                                + ", " + distancia + ", " + numAlimento);

                        // remove the Farthest player from the list, update the list (reset)
                        jogadoresEmLongaDistancia.remove(playerMaisDistante);
                        posicaoDeChegada++;
                    }
                }
            }
        }

        return resultados;
    }

    public JPanel getAuthorsPanel() {
        JLabel author = new JLabel();
        JPanel mostrarCredito = new JPanel();

        author.setText("Lodney Santos - a21505293");

        author.setForeground(Color.BLUE);
        author.setFont(new Font("Arial", Font.BOLD, 18));

        mostrarCredito.add(author);
        return mostrarCredito;
    }

    public String whoIsTaborda() {
        return "Wrestling";
    }

    public boolean saveGame(File file) {

        // Break the line
        String nextLine = System.lineSeparator();

        try {
            FileWriter guardarJogo = new FileWriter(file.getAbsoluteFile());
            Player playerAtual = players.get((currentShift - 1) % players.size());
            Player playerComMaisEnergia = null;

            for (Player player : players) {
                if (playerComMaisEnergia == null || player.getEspecie().getCurrentEnergy() >
                        playerComMaisEnergia.getEspecie().getCurrentEnergy()) {
                    playerComMaisEnergia = player;
                }
            }

            // Save general information
            guardarJogo.write("Turno atual: " + currentShift + nextLine);
            guardarJogo.write("Dimensão do mapa: " + finalGamePosition + nextLine);
            guardarJogo.write("Player atual: " + playerAtual.getId() + nextLine);
            guardarJogo.write("Player com mais energia: " + playerComMaisEnergia.getId() + nextLine);
            guardarJogo.write("Jogadores que consumiram bananas: " + playersWhoConsumedBanana + nextLine);
            guardarJogo.write("Casa do meio do mapa: " + middleHouse + nextLine);
            guardarJogo.write("Já existe vencedor: " + someoneReachedTheGoal + nextLine);

            // Save general player information
            guardarJogo.write(nextLine);
            guardarJogo.write("Informação geral dos jogadores em jogo: " + nextLine);
            guardarJogo.write("Quantidade de jogadores em jogo: " + players.size() + nextLine);

            for(Player player : players) {
                guardarJogo.write(player.getId() + " : " + player.getNome() + " : " + player.getPosicaoAtual() + " : "
                        + player.getIdEspecie() + " : " + player.getEspecie().getCurrentEnergy() + " : " +
                        player.getNumeroPosicoesPercorridas() + " : " + player.getNumeroAlimento() + " : " +
                        player.getEspecie().getTypeFeedSpecies() + " : " + player.getEspecie().getEnergyConsumption()
                        + " : " + player.getEspecie().getGainEnergyRest() + " : " + player.getEspecie().getMinimumSpeed()
                        + " : " + player.getEspecie().getMaximumSpeed());

                guardarJogo.write(nextLine);
            }

            // Save general food information
            guardarJogo.write(nextLine);
            guardarJogo.write("Informação geral dos alimentos em jogo: " + nextLine);
            guardarJogo.write("Quantidade de alimentos em jogo: " + foods.size() + nextLine);

            for(Food food : foods) {
                guardarJogo.write(food.getId() + " : " + food.getPosicaoAlimento() + " : "
                        + food.getNumeroBananasON() + " : " + food.getNumroJogadasCarne()
                        + " : " + food.getNumeroAleatorioCog() + " : " + food.toolTip());

                guardarJogo.write(nextLine);
            }

            guardarJogo.close();
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public boolean loadGame(File file) {
        try {
            BufferedReader carregarFicheiroGuardado = new BufferedReader(new FileReader(file));
            ArrayList<Player> jogadoresCarregados = new ArrayList<>();
            ArrayList<Food> alimentosCarregados = new ArrayList<>();
            HashMap<Integer, Integer> jogadoresQueConsumiramBananas = new HashMap<>();
            String linha;
            int jogadorAjogar = 0;
            int jogadorComMaisEnergia = 0;

            while ((linha = carregarFicheiroGuardado.readLine()) != null) {
                if (linha.startsWith("Turno atual: ")) {
                    currentShift = Integer.parseInt(linha.split(":")[1].trim());

                } else if (linha.startsWith("Dimensão do mapa: ")) {
                    finalGamePosition = Integer.parseInt(linha.split(":")[1].trim());

                } else if (linha.startsWith("Player atual: ")) {
                    jogadorAjogar = Integer.parseInt(linha.split(":")[1].trim());
                    setCurrentPlayerLoadGame(jogadorAjogar, jogadoresCarregados);

                } else if (linha.startsWith("Player com mais energia: ")) {
                    jogadorComMaisEnergia = Integer.parseInt(linha.split(":")[1].trim());

                } else if (linha.startsWith("Casa do meio do mapa: ")) {
                    middleHouse = Integer.parseInt(linha.split(":")[1].trim());

                } else if (linha.startsWith("Já existe vencedor: ")) {
                    someoneReachedTheGoal = Boolean.parseBoolean(linha.split(":")[1].trim());

                } else if (linha.startsWith("Jogadores que consumiram bananas: ")) {
                    carregarBananas(jogadoresQueConsumiramBananas, linha);

                } else if (linha.startsWith("Quantidade de jogadores em jogo: ")) {
                    int quantJogadoresEmJogo = Integer.parseInt(linha.split(":")[1].trim());
                    carregarJogadores(carregarFicheiroGuardado, quantJogadoresEmJogo, jogadoresCarregados);

                } else if (linha.startsWith("Quantidade de alimentos em jogo: ")) {
                    int quantAlimentosEmJogo = Integer.parseInt(linha.split(":")[1].trim());
                    carregarAlimentos(carregarFicheiroGuardado, quantAlimentosEmJogo, alimentosCarregados);
                }
            }

            carregarFicheiroGuardado.close();

            players.clear();
            players.addAll(jogadoresCarregados);

            foods.clear();
            foods.addAll(alimentosCarregados);

            return true;
        } catch (IOException e) {
            return false;
        }
    }



    /**
     * --------------------------------------AUXILIARY_FUNCTION_CREATEINITIALJUNGLE()-------------------------------------
     */

    // TODO TARZAN — Apenas poderá existir um jogador da espécie Tarzan a competir
    private void validarEspecieTarzan(String playerSpecies) throws InvalidInitialJungleException {
        for (int i = 0; i < playerSpecies.length(); i++) {
            if (playerSpecies.charAt(i) == 'Z') {
                countTarzan++;
            }

            if (countTarzan > 1) {
                throw new InvalidInitialJungleException("Existe mais do que 1 jogador da espécie Tarzan a competir.", true, false);
            }
        }
    }

    public static void validarNumeroIDs(Map<Integer, Integer> idJogadoresEmJogo, int idJogador) throws InvalidInitialJungleException {
        Integer countIDJogadores = idJogadoresEmJogo.get(idJogador);

        if (countIDJogadores != null && countIDJogadores > 0) {

            throw new InvalidInitialJungleException("Não podem haver dois jogadores com o mesmo id.", true, false, true);
        }

        idJogadoresEmJogo.put(idJogador, 1);

    }


    /**
     * --------------------------------------AUXILIARY_FUNCTIONS_MOVECURRENTPLAYER()-------------------------------------
     */

    private void updateMeatPlayCount(int turnoAtual) {
        for (Food food : foods) {
            food.setNumroJogadasCarne(turnoAtual);
        }
    }

    private void limitarEnergia(boolean avancouOuRecou, boolean ficou, int valorAlteracaoEnergia) {
        /*
        A energia de qualquer jogador nunca pode ultrapassar os 200, seja por descansar, seja
        por efeito de alimentos. Caso isso aconteça, a energia mantém-se nos 200.
         */
        int limiteEnergia = 200;

        int novaEnergia = currentPlayer.getEspecie().getCurrentEnergy();

        if (avancouOuRecou) {
            novaEnergia -= Math.abs(valorAlteracaoEnergia);
            novaEnergia = Math.max(novaEnergia, 0); // Garantir que a energia não fica negativa

        } else if (ficou) {
            novaEnergia += valorAlteracaoEnergia;
            novaEnergia = Math.min(novaEnergia, limiteEnergia); // Garantir que a energia não ultrapassa o limite
        }

        currentPlayer.getEspecie().definirEnergiaAtual(novaEnergia);
    }

    private MovementResult jogadorComEnergiaSuficienteParaSeMover(int nrSquares, int energiaAtual, int consumoEnergia) {
        if (energiaAtual < consumoEnergia * Math.abs(nrSquares)) {
            // Atualizar o turno
            increaseShift();
            return new MovementResult(MovementResultCode.NO_ENERGY, null);
        }
        return null;
    }

    private boolean isMovimentoValido(int nrSquares, int posicaoJogador, int casaPartida, boolean bypassValidations) {
        if (!bypassValidations) {
            if (nrSquares < -6 || nrSquares > 6) {
                return false;
            }
            if (posicaoJogador < casaPartida) {
                return false;
            }
            // Verificar se as espécies se movimentarem nas respetivas velocidades mínima e máxima
            return validarVelocidadeEspecie(Math.abs(nrSquares));
        }
        return true;
    }

    private boolean validarVelocidadeEspecie(int velocidade) {
        String especieID = currentPlayer.getEspecie().getId();

        return switch (especieID) {
            case "E", "Z" -> // Elephant ou Tarzan
                    velocidade >= 1 && velocidade <= 6;
            case "L" -> // Leão
                    velocidade >= 4 && velocidade <= 6;
            case "T" -> // Turtle
                    velocidade >= 1 && velocidade <= 3;
            case "P" -> // Pássaro
                    velocidade >= 5 && velocidade <= 6;
            case "U" -> // Unicórnio
                    velocidade >= 3 && velocidade <= 6;
            case "G" -> // Giraffe
                    velocidade >= 2 && velocidade <= 3;
            default -> false;
        };
    }

    private boolean verificaCasaComAlimentoUnicornio(int novaPosicaoJogador) {
        for (Food food : foods) {
            if (food.getPosicaoAlimento() == novaPosicaoJogador) {
                return true;
            }
        }
        return false;
    }

    private void verificaJogadorNaCasaDoMeio() {
        calcularCasaDoMeio();
        for (Player player : players) {
            if (player.getPosicaoAtual() == middleHouse) {
                return;
            }
        }
    }

    public void setEnergyOfNumberOfSquare(int nrSquares, int energiaAtual, int consumoEnergia, boolean casaComAlimento) {
        if (nrSquares != 0) {

            int energiaGasta = consumoEnergia * Math.abs(nrSquares);
            Specie specieJogador = currentPlayer.getEspecie();

            if (specieJogador.getId().equals("U")) {
                if (casaComAlimento) {
                    int novaEnergia = energiaAtual - energiaGasta;
                    specieJogador.definirEnergiaAtual(novaEnergia);
                } else {
                    int energiaAtualizada = energiaAtual + 2;
                    int novaEnergia = energiaAtualizada - energiaGasta;
                    specieJogador.definirEnergiaAtual(novaEnergia);
                }
            } else {
                int novaEnergia = energiaAtual - energiaGasta;
                specieJogador.definirEnergiaAtual(novaEnergia);
            }
        }
    }

    private String verificarConsumoDeAlimento(int posicao) {
        for (Food food : foods) {
            if (food.getPosicaoAlimento() == posicao) {
                String idAlimento = food.getId();
                String tipoDeAlimentacao = currentPlayer.getEspecie().getTypeFeedSpecies();

                int alteracaoEnergia;
                switch (idAlimento) {
                    // ERVA
                    case "e" -> alteracaoEnergia = food.consumir(tipoDeAlimentacao, currentPlayer);
                    // ÁGUA
                    case "a" -> alteracaoEnergia = food.consumir(tipoDeAlimentacao, currentPlayer);
                    // BANANA
                    case "b" -> alteracaoEnergia = food.consumir(tipoDeAlimentacao, currentPlayer, food, playersWhoConsumedBanana);
                    // CARNE
                    case "c" -> alteracaoEnergia = food.consumir(tipoDeAlimentacao, currentPlayer, currentShift, food);
                    // COGUMELO MÁGICO
                    case "m" -> alteracaoEnergia = food.consumir(tipoDeAlimentacao, currentPlayer, currentShift, food);
                    //FOLHAS DAS ARVORES
                    case "t" -> alteracaoEnergia = food.consumir(tipoDeAlimentacao, currentPlayer);

                    default -> alteracaoEnergia = 0;
                }
                // Verificar se o jogador é um unicórnio ignorar todos os alimentos
                if (currentPlayer.getEspecie().getId().equals("U")) {
                    if (alteracaoEnergia == 0) {
                        return null;
                    }
                    // Verificar se o jogador é uma girafa
                } else if (currentPlayer.getEspecie().getId().equals("G")) {
                    // ignorar COGUMELO MÁGICO
                    if (idAlimento.equals("m")) {
                        alteracaoEnergia = 0;
                    }
                }

                // Se o valor após consumir algum food for acima de zero ou igual (agua) aumenta...
                // ...o ganho de energia após consumir o food
                if (alteracaoEnergia > 0) {
                    limitarEnergia(false, true, alteracaoEnergia);
                    // Se não diminui o ganho de energia dependendo da espécie.
                } else {
                    limitarEnergia(true, false, alteracaoEnergia);
                }
                return food.getNome();
            }
        }
        return null;
    }

    private MovementResult processarMovimentoParaConsumoDeAlimento(String alimentoConsumido, Player playerAtual) {
        if (alimentoConsumido != null) {
            // GIRRAFA
            if (playerAtual.getEspecie().getId().equals("G")) {
                if (!alimentoConsumido.equals("Cogumelo Magico") && !alimentoConsumido.equals("Meat")) {
                    playerAtual.aumentarNumAlimentoApanhado(1);
                    // Registrar o alimento consumido
                    registrarAlimentoConsumido(alimentoConsumido);
                    // Atualizar o turno
                    increaseShift();
                    return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou " + alimentoConsumido);
                } else {
                    // Atualizar o turno e ignorar o consumo de carne por herbívoros
                    increaseShift();
                    return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
                }

                // Se for carnívoro, omnivoro ou se for herbivoro e o alimento consumido não é carne
                // contar o número de alimento apanhado.
            } else if (!playerAtual.getEspecie().getTypeFeedSpecies().equals("herbívoro") ||
                    (playerAtual.getEspecie().getTypeFeedSpecies().equals("herbívoro")
                            && !alimentoConsumido.equals("Meat")) && !alimentoConsumido.equals("Water")) {

                playerAtual.aumentarNumAlimentoApanhado(1);
                // Registrar o alimento consumido
                registrarAlimentoConsumido(alimentoConsumido);
                // senão se for herbívoro e o alimento consumido é carne...
            } else {
                // Atualizar o turno e ignorar o consumo de carne por herbívoros
                increaseShift();
                return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
            }
            // Registrar o alimento consumido
            registrarAlimentoConsumido(alimentoConsumido);
            // Atualizar o turno
            increaseShift();
            return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou " + alimentoConsumido);
        }
        return null;
    }

    private void registrarAlimentoConsumido(String alimento) {
        foodsConsumed.add(alimento);
    }


    /**
     * ---------------------------------AUXILIARY_FUNCTIONS_GETWINNERINFO()------------------------------------
     */

    private void calcularCasaDoMeio() {
        if (finalGamePosition % 2 != 0) {
            middleHouse = ((finalGamePosition / 2) + 1);
        } else {
            // Se o tabuleiro tem tamanho 10, logo, a casa do meio é a casa 5.
            middleHouse = (finalGamePosition / 2);
        }
    }

    private void verificarChegadaAMeta() {
        for (Player player : players) {
            if (player.getPosicaoAtual() == finalGamePosition) {
                someoneReachedTheGoal = true;
                break;
            }
        }
    }

    private boolean isSomeoneReachedTheGoal() {
        return someoneReachedTheGoal;
    }

    private boolean thereIsAPlayerVeryFarFromTheGoal() {
        int distanciaPrimeiroJogador = Integer.MAX_VALUE;
        int distanciaSegundoJogador = Integer.MAX_VALUE;

        // Verificar as duas menores distâncias entre os jogadores e a posição final do jogo
        for (Player player : players) {
            int distancia = Math.abs(finalGamePosition - player.getPosicaoAtual());

            if (distancia < distanciaPrimeiroJogador) {
                distanciaSegundoJogador = distanciaPrimeiroJogador;
                distanciaPrimeiroJogador = distancia;
            } else if (distancia < distanciaSegundoJogador) {
                distanciaSegundoJogador = distancia;
            }
        }

        int metadeDaMeta = finalGamePosition / 2;
        int distanciaEntreJogadores = (distanciaSegundoJogador - distanciaPrimeiroJogador);

        // Verificar se a distância entre os jogadores é maior que a metade do tamanho do mapa
        return distanciaEntreJogadores > metadeDaMeta;
    }

    private Player obterJogadorMaisDistanteDaMeta(List<Player> jogadores) {
        int maiorDistancia = Integer.MIN_VALUE;

        // Encontrar o jogador com a maior distância da meta
        for (Player player : jogadores) {
            int distancia = Math.abs(finalGamePosition - player.getPosicaoAtual());
            if (distancia > maiorDistancia) {
                maiorDistancia = distancia;
                currentPlayer = player;
            }
        }

        return currentPlayer;
    }

    private boolean isNovaCondicaoVencedor(List<Player> jogadoresEmJogos) {
        ArrayList<Player> jogadoresNaCasaDoMeio = new ArrayList<>();

        // Verificar se há pelo menos dois jogadores na "casa do meio"
        for (Player player : jogadoresEmJogos) {
            if (jogadorEstaNaCasaDoMeio(player)) {
                jogadoresNaCasaDoMeio.add(player);
                // Se existirem em jogo 3 jogadores (2 na casa do meio e 1 adiantado)
                if (jogadoresNaCasaDoMeio.size() >= 2 && jogadorEntreCasaDoMeioEAMeta(jogadoresEmJogos)) {
                    return true;

                    // Senão se existirem em jogo 2 jogadores na casa do meio
                } else if (jogadoresNaCasaDoMeio.size() == 2) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean jogadorEntreCasaDoMeioEAMeta(List<Player> jogadoresEmJogos) {
        // Verificar se há pelo menos um jogador entre a "casa do meio" e a meta
        calcularCasaDoMeio();
        for (Player player : jogadoresEmJogos) {
            int posicaoAtual = player.getPosicaoAtual();
            if (posicaoAtual > middleHouse && posicaoAtual < finalGamePosition) {
                return true;
            }
        }
        return false;
    }

    private boolean jogadorEstaNaCasaDoMeio(Player player) {
        int posicaoAtual = player.getPosicaoAtual();
        return posicaoAtual == middleHouse;
    }

    public Player obterVencedorDaCasaDoMeioNovaCondicao(ArrayList<Player> jogadoresEmJogos) {

        calcularCasaDoMeio();

        ArrayList<Player> jogadoresCasaDoMeio = new ArrayList<>();

        for (Player player : jogadoresEmJogos) {
            if (player.getPosicaoAtual() == middleHouse) {
                jogadoresCasaDoMeio.add(player);
            }
        }

        // Se existirem dois ou mais jogadores na casa do meio
        switch (jogadoresCasaDoMeio.size()) {
            case 2, 4 -> {
                // Verificar os jogadores na casa do meio com maior energia
                jogadoresCasaDoMeio.sort((j1, j2) -> j2.getEspecie().getCurrentEnergy() - j1.getEspecie().getCurrentEnergy());

                // O jogador com mais energia na casa do meio é o vencedor
                return jogadoresCasaDoMeio.get(0);
            }
        }

        return null;
    }


    /**
     * ---------------------------------AUXILIARY_FUNCTION_GETGAMERESULTS------------------------------------
     */

    private void processarResultadosNovaCondicaoVencedor(Player vencedor, List<Player> jogadoresEmJogos, int posicaoChegada,
                                                         List<String> resultados) {
        ArrayList<Player> jogadoresCasaDoMeio = new ArrayList<>();
        ArrayList<Player> jogadoresAdiantados = new ArrayList<>();

        if (vencedor != null) {
            // Colocar a parte o vencedor da casa do meio com maior energia (Reservar vencedor)
            jogadoresEmJogos.remove(vencedor);

            // Classificar o jogador vencedor da casa do meio como primeiro classificado
            String nomeVencedor = vencedor.getNome();
            String especieVencedor = vencedor.getEspecie().getName();
            int posicaoAtualVencedor = vencedor.getPosicaoAtual();
            int distanciaVencedor = vencedor.getNumeroPosicoesPercorridas();
            int numAlimentoVencedor = vencedor.getNumeroAlimento();

            resultados.add("#" + (posicaoChegada + 1) + " " + nomeVencedor + ", " + especieVencedor + ", " + posicaoAtualVencedor
                    + ", " + distanciaVencedor + ", " + numAlimentoVencedor);

            posicaoChegada++;

            for (Player player : jogadoresEmJogos) {
                if (player.getPosicaoAtual() == middleHouse) {
                    jogadoresCasaDoMeio.add(player);
                } else {
                    jogadoresAdiantados.add(player);
                }
            }

            // Se existirem 4 jogadores na casa do meio, classificar por energia
            if (jogadoresEmJogos.size() == 3) {

                if (jogadoresCasaDoMeio.size() == 1 && jogadoresAdiantados.size() >= 2) {
                    jogadoresEmJogos.sort(Collections.reverseOrder(Comparator.comparingInt(Player::getNumeroPosicoesPercorridas)));
                } else {
                    // Ordenar por ordem decrescente de energia (do maior ao menor)
                    jogadoresEmJogos.sort((jogador1, jogador2) -> jogador2.getEspecie().getCurrentEnergy() - jogador1.getEspecie().getCurrentEnergy());
                }

            } else {
                // Senão se estiverem 3 jogadores em jogo, dois na casa do meio e um adiantado e já existir um venvedor....
                // Disputa pelo segundo lugar,
                // A classificação restante deve corresponder à distância que cada jogador se encontra da meta.
                // Ordenar os jogadores restantes com base na distância até a meta
                jogadoresEmJogos.sort(Collections.reverseOrder(Comparator.comparingInt(Player::getNumeroPosicoesPercorridas)));
            }

            // Classificar os restantes com base na distância até a meta
            for (Player player : jogadoresEmJogos) {
                String nome = player.getNome();
                String especie = player.getEspecie().getName();
                int posicaoAtual = player.getPosicaoAtual();
                int distancia = player.getNumeroPosicoesPercorridas();
                int numAlimento = player.getNumeroAlimento();

                resultados.add("#" + (posicaoChegada + 1) + " " + nome + ", " + especie + ", " + posicaoAtual
                        + ", " + distancia + ", " + numAlimento);

                posicaoChegada++;
            }
        }
    }


    /**
     * --------------------------------------AUXILIARY_FUNCTIONS_LOADGAME--------------------------------------
     */

    private Player carregarDadosDoJogador(String[] playerInfo) {
        int novoIdJogador = Integer.parseInt(playerInfo[0]);
        String novoNomeJogador = playerInfo[1];
        int novaPosicaoJogador = Integer.parseInt(playerInfo[2]);
        String novoIdEspecie = playerInfo[3];
        int novaEnergiaAtual = Integer.parseInt(playerInfo[4]);
        int novaPosicaoPercorridas = Integer.parseInt(playerInfo[5]);
        int novoNumAlimento = Integer.parseInt(playerInfo[6]);
        String novoTipoAlimentacaoEspecie = playerInfo[7];
        int novoConsumoEnergia = Integer.parseInt(playerInfo[8]);
        int novoGanhoEnergia = Integer.parseInt(playerInfo[9]);
        int novaVelocidadeMinima = Integer.parseInt(playerInfo[10]);
        int novaVelocidadeMaxima = Integer.parseInt(playerInfo[11]);

        Specie specieJogadorCarregado = Specie.identifySpecie(novoIdEspecie);
        Player novoPlayer = new Player(novoIdJogador, novoNomeJogador, novoIdEspecie, novaPosicaoJogador, specieJogadorCarregado);

        novoPlayer.setId(novoIdJogador);
        novoPlayer.setNome(novoNomeJogador);
        novoPlayer.alterarPosicaoAtual(novaPosicaoJogador);
        novoPlayer.setIdEspecie(novoIdEspecie);
        novoPlayer.getEspecie().definirEnergiaAtual(novaEnergiaAtual);
        novoPlayer.incrementarNumeroPosicoesPercorridas(novaPosicaoPercorridas);
        novoPlayer.aumentarNumAlimentoApanhado(novoNumAlimento);
        novoPlayer.getEspecie().defineSpeciesFeedType(novoTipoAlimentacaoEspecie);
        novoPlayer.getEspecie().definirConsumoEnergia(novoConsumoEnergia);
        novoPlayer.getEspecie().definirGanhoEnergiaDescanso(novoGanhoEnergia);
        novoPlayer.getEspecie().definirVelocidadeMinima(novaVelocidadeMinima);
        novoPlayer.getEspecie().definirVelocidadeMaxima(novaVelocidadeMaxima);

        return novoPlayer;
    }

    private Food carregarDadosAlimento(String[] foodInfo) {
        String novoIdAlimento = foodInfo[0];
        int novaPosicaoAlimento = Integer.parseInt(foodInfo[1]);
        int novoNumeroBananasON = Integer.parseInt(foodInfo[2]);
        int novoNumroJogadasCarne= Integer.parseInt(foodInfo[3]);
        int novoNumeroAleatorioCog = Integer.parseInt(foodInfo[4]);

        Food carregarFood = Food.identificarAlimento(novoIdAlimento,novaPosicaoAlimento);

        carregarFood.setNumeroBananasON(novoNumeroBananasON);
        carregarFood.setNumroJogadasCarne(novoNumroJogadasCarne);
        carregarFood.setNumeroAleatorioCog(novoNumeroAleatorioCog);

        return carregarFood;
    }

    private void carregarBananas(HashMap<Integer, Integer> jogadoresBananas, String linha) {
        String hashMapBanana = linha.substring(linha.indexOf("{") + 1, linha.indexOf("}"));
        String[] chaveValorBananas = hashMapBanana.split(", ");
        for (String banana : chaveValorBananas) {
            String[] chaveValor = banana.split("=");
            if (chaveValor.length == 2) {
                int chave = Integer.parseInt(chaveValor[0].trim());
                int valor = Integer.parseInt(chaveValor[1].trim());
                jogadoresBananas.put(chave, valor);
            }
        }
    }

    private void carregarJogadores(BufferedReader reader, int quantidadeJogadoresEmJogo, ArrayList<Player> jogadores) throws IOException {
        for (int i = 0; i < quantidadeJogadoresEmJogo; i++) {
            String linha = reader.readLine();
            if (linha.trim().isEmpty()) {
                continue;
            }
            Player novoPlayer = carregarDadosDoJogador(linha.split(" : "));
            jogadores.add(novoPlayer);
        }
    }

    private void carregarAlimentos(BufferedReader reader, int quantidadeAlimentosEmJogo, ArrayList<Food> foods) throws IOException {
        for (int i = 0; i < quantidadeAlimentosEmJogo; i++) {
            String linha = reader.readLine();
            if (linha.trim().isEmpty()) {
                continue;
            }
            Food novoFood = carregarDadosAlimento(linha.split(" : "));
            foods.add(novoFood);
        }
    }

    private void setCurrentPlayerLoadGame(int idJogador, ArrayList<Player> players) {
        for (Player player : players) {
            if (player.getId() == idJogador) {
                currentPlayer = player;
                break;
            }
        }
    }

    /**
     * ----------------------Access methods (Facilitate access in Unit Tests and fn KOTLIN)()---------------------------
     */

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public Set<String> getFoodsConsumed() {
        return foodsConsumed;
    }


    /**
     * ------------------------------------------SHIFTS_AND_RESET()---------------------------------------------
     */

    private void increaseShift() {
        currentShift++;
    }

    private void increaseReset() {
        players = new ArrayList<>(); // reset the player list
        foods = new ArrayList<>(); // reset food list
        currentPlayer = null; // current player reset
        idPlayersInGame = new HashMap<>(); // reset the hashmap of player 'ids' at the beginning of the game
        playersWhoConsumedBanana = new HashMap<>(); // reset the hashmap of players’ ids consumed bananas
        foodsConsumed = new HashSet<>(); // reset the hashset of food consumed during the game

        someoneReachedTheGoal = false; // reset checks if there is already a winner
        homeWithFood = false; // reset checks if the next house contains food
        initialHouse = 1; // reset home match of all players
        currentShift = 0; // reset the current game turn
        finalGamePosition = 0; // reset final position of game map
        middleHouse = 0; // reset house in the middle of the game map
        countTarzan = 0;
        updateMeatPlayCount(0);
    }
}
