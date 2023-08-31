package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.validar.ValidadorJogador;
import pt.ulusofona.lp2.deisiJungle.validar.ValidadorAlimento;

import javax.swing.*;
import java.io.*;
import java.util.*;

/*-------------------------------------------------DEISI JUNGLE-----------------------------------------------------
    Certo dia, numa savana muito (muito) longe daqui, os animais decidiram descobrir quem seria o melhor atleta entre
    eles. Para o fazer, definiram um conjunto de provas desportivas que iriam disputar entre si — quem vencesse mais
    provas seria considerado o Rei da Selva. Rapidamente a notícia chegou ao Tarzan, que não quis deixar de participar
    nas provas, convencido de que facilmente as venceria. A primeira prova a disputar é a prova de atletismo.
    Os animais vão-se reunir numa pista e competir para determinar qual o melhor atleta.
 ------------------------------------------------------------------------------------------------------------------ */

// Classe responsável por gerir o jogo
public class GameManager {
    private ArrayList<Jogador> jogadores = new ArrayList<>();
    private ArrayList<Alimento> alimentos = new ArrayList<>();
    private HashMap<Integer,Integer> idJogadoresEmJogo = new HashMap<>();
    private HashMap<Integer, Integer> jogadoresQueConsumiramBanana = new HashMap<>();
    private Set<String> alimentosConsumidos = new HashSet<>();
    private Jogador jogadorAtual;
    private Jogador jogadorComMaisEnergia;
    private int posicaoFinalJogo;
    private int casaPartida;
    private int turnoAtual;
    private int casaDoMeio;
    private boolean alguemChegouNaMeta;
    private boolean casaComAlimento;
    private boolean existeJogadorNoMeio;

    public GameManager() {}

    public String[][] getSpecies() {

        /* estrutura:
            ● [0] ⇒ id da espécie
            ● [1] ⇒ nome da espécie
            ● [2] ⇒ nome do ficheiro com a imagem da espécie (ex: “turtle.png”)
            ● [3] ⇒ energia inicial
            ● [4] ⇒ consumo de energia
            ● [5] ⇒ ganho de energia em descanso
            ● [6] ⇒ velocidade, no formato “X..Y”
            // Total Especies = 6
        */
        String [][] especies = new String[6][7];

        especies[0][0] = "E"; // ⇒ id da espécie
        especies[0][1] = "Elefante"; // ⇒ nome da espécie
        especies[0][2] = "elephant.png"; // ⇒ nome da imagem da espécie
        especies[0][3] = "180"; // ⇒ energia inicial
        especies[0][4] = "4"; // ⇒ consumo de energia
        especies[0][5] = "10"; // ⇒ ganho de energia em descanso
        especies[0][6] = "1..6"; // ⇒ velocidade, no formato “X..Y”

        especies[1][0] = "L";
        especies[1][1] = "Leão";
        especies[1][2] = "lion.png";
        especies[1][3] = "80"; // ⇒ energia inicial
        especies[1][4] = "2"; // ⇒ consumo de energia
        especies[1][5] = "10"; // ⇒ ganho de energia em descanso
        especies[1][6] = "4..6"; // ⇒ velocidade, no formato “X..Y”

        especies[2][0] = "T";
        especies[2][1] = "Tartaruga";
        especies[2][2] = "turtle.png";
        especies[2][3] = "150"; // ⇒ energia inicial
        especies[2][4] = "1"; // ⇒ consumo de energia
        especies[2][5] = "5"; // ⇒ ganho de energia em descanso
        especies[2][6] = "1..3"; // ⇒ velocidade, no formato “X..Y”

        especies[3][0] = "P";
        especies[3][1] = "Pássaro";
        especies[3][2] = "bird.png";
        especies[3][3] = "70"; // ⇒ energia inicial
        especies[3][4] = "4"; // ⇒ consumo de energia
        especies[3][5] = "50"; // ⇒ ganho de energia em descanso
        especies[3][6] = "5..6"; // ⇒ velocidade, no formato “X..Y”

        especies[4][0] = "Z";
        especies[4][1] = "Tarzan";
        especies[4][2] = "tarzan.png";
        especies[4][3] = "70"; // ⇒ energia inicial
        especies[4][4] = "2"; // ⇒ consumo de energia
        especies[4][5] = "20"; // ⇒ ganho de energia em descanso
        especies[4][6] = "1..6"; // ⇒ velocidade, no formato “X..Y”

        especies[5][0] = "U";
        especies[5][1] = "Unicórnio";
        especies[5][2] = "unicorn.png";
        especies[5][3] = "200"; // ⇒ energia inicial
        especies[5][4] = "8"; // ⇒ consumo de energia
        especies[5][5] = "20"; // ⇒ ganho de energia em descanso
        especies[5][6] = "3..6"; // ⇒ velocidade, no formato “X..Y”

        return especies;
    }

    public String[][] getFoodTypes() {

        String [][] alimentos = new String[5][3];

        alimentos[0][0] = "e";
        alimentos[0][1] = "Erva";
        alimentos[0][2] = "grass.png";
        alimentos[1][0] = "a";
        alimentos[1][1] = "Água";
        alimentos[1][2] = "water.png";
        alimentos[2][0] = "b";
        alimentos[2][1] = "Cacho de bananas";
        alimentos[2][2] = "bananas.png";
        alimentos[3][0] = "c";
        alimentos[3][1] = "Carne";
        alimentos[3][2] = "meat.png";
        alimentos[4][0] = "m";
        alimentos[4][1] = "Cogumelos magicos";
        alimentos[4][2] = "mushroom.png";

        return alimentos;
    }

    public void createInitialJungle(int jungleSize, String[][] playersInfo, String[][] foodsInfo) throws InvalidInitialJungleException {
        atualizarContagemJogadasCarne(0);
        // Cada vez que o jogo é criado o programa vai fazer a reinicialização das variaveis para o valor inicial
        incrementarReset();
        turnoAtual = 1;
        posicaoFinalJogo = jungleSize;

        // TODO O MAPA — duas posições por cada jogador
        ValidadorJogador.validarDimensaoMapa(posicaoFinalJogo, playersInfo.length);

        // TODO JOGADOR — O jogo terá entre 2 e 4 jogadores
        ValidadorJogador.validarNumJogadorEmJogo(playersInfo.length);

        /**
         * JOGADORES
         * loop’ foreach para guardar informação do playersInfo
         */
        for (String[] infoJogador: playersInfo) {

            String oldIDJogador = infoJogador[0];
            String nomeJogador = infoJogador[1];
            String idEspecieJogador = infoJogador[2];

            // TODO IDs - é null ou vazio?
            if (oldIDJogador == null || oldIDJogador.isEmpty()) {
                throw new InvalidInitialJungleException("O ID do jogador é null ou vazio, logo, não é válido.", true, false);
            }

            // TODO IDs — é um valor numérico?
            boolean isNumericValue = oldIDJogador.matches("-?\\d+(\\.\\d+)?");

            if (!isNumericValue) {
                throw new InvalidInitialJungleException("O ID do jogador não é válido.", true, false);
            }

            int idJogador = Integer.parseInt(oldIDJogador);

            // TODO IDs — não pode haver dois jogadores com o mesmo id
            ValidadorJogador.validarNumeroIDs(idJogadoresEmJogo, idJogador);

            // TODO O NOME — não podem ser null ou vazios
            ValidadorJogador.validarNomeJogadores(nomeJogador);

            // TODO TARZAN — Apenas poderá existir um jogador da espécie Tarzan a competir
            ValidadorJogador.validarEspecieTarzan(idEspecieJogador);

            // TODO O ESPÉCIES — A espécie tem que ser uma das que foi retornada da função getSpecies()
            ValidadorJogador.validarEspecieJogador(idEspecieJogador, getSpecies());


            Especie especieJogadorEmJogo = Especie.identificarEspecie(idEspecieJogador);
            jogadorAtual = new Jogador(idJogador, nomeJogador, idEspecieJogador, casaPartida, especieJogadorEmJogo);

            jogadores.add(jogadorAtual);
            jogadores.sort(Comparator.comparing(Jogador::getId)); // Ordenar IDs por ordem crescente
            jogadorAtual.caracterizarEspecieJogador(jogadorAtual);
            //jogadorAtual = jogadores.get(turnoAtual % jogadores.size());

            // Definir o jogador atual como o primeiro da lista
            jogadorAtual = jogadores.get(0);


            if (jogadores.size() >= 2) {
                jogadorAtual.saberNumJogadoresEmJogo(jogadores.size());
            }

        }


        /**
         * ALIMENTOS
         * ‘loop’ foreach para guardar informação do foodsInfo
         */
        //System.out.println("ALIMENTOS");
        for (String[] infoAlimento: foodsInfo) {

            String idTipo = infoAlimento[0];
            String oldPosicaoAlimento = infoAlimento[1];
            //int posicaoAlimento = Integer.parseInt(infoAlimento[1]);

            // TODO IDs - é null ou vazio?
            if (oldPosicaoAlimento == null || oldPosicaoAlimento.isEmpty()) {
                throw new InvalidInitialJungleException("A posição do alimento é null ou vazio, logo, não é válida.", false, true);
            }

            // TODO IDs - é um valor numérico?
            boolean isNumericValue = oldPosicaoAlimento.matches("-?\\d+(\\.\\d+)?");

            if (!isNumericValue) {
                throw new InvalidInitialJungleException("A posição do alimento não é válida.", false, true);
            }

            int posicaoAtualAlimento = Integer.parseInt(oldPosicaoAlimento);

            // TODO O ID - tem que ser um dos que foi retornado pela função getFoodTypes()
            ValidadorAlimento.validarIDAlimento(idTipo, getFoodTypes());

            // TODO POSIÇÃO - Os alimentos têm que estar posicionados dentro dos limites do terreno.
            ValidadorAlimento.validarPosicaoAlimentos(posicaoAtualAlimento, casaPartida, posicaoFinalJogo);

            Alimento tipoAlimento = Alimento.identificarAlimento(idTipo, posicaoAtualAlimento);

            alimentos.add(tipoAlimento);
        }
    }

    public void createInitialJungle(int jungleSize, String[][] playersInfo) throws InvalidInitialJungleException {
        String[][] foodsInfo = new String[0][2];
        createInitialJungle(jungleSize, playersInfo, foodsInfo);
    }

    public int[] getPlayerIds(int squareNr) {

        ArrayList<Jogador> listaIdsJogadores = new ArrayList<>();

        /// RESTRIÇÕES:
        // Retornar um array vazio se o squareNr for inválido;
        // Retornar um array vazio se não houver jogadorers na posição indicada;
        // squareNr — números de quadrados do mapa (posições do mapa)
        if (squareNr < casaPartida || squareNr > posicaoFinalJogo) {
            return new int[0];
        }

        /// REQUISITOS:
        // Obter os ‘ids’ dos jogadores de uma determinada posição do mapa
        for (Jogador jogadorEmJogo: jogadores) {
            if (jogadorEmJogo.getPosicaoAtual() == squareNr) {
                listaIdsJogadores.add(jogadorEmJogo);
            }
        }

        // Retornar um array com ‘ids’ dos jogadores de uma determinada posição do mapa
        int [] idsInJungle = new int[listaIdsJogadores.size()];
        for (int i = 0; i < listaIdsJogadores.size(); i++) {
            // passar os valores da lista para o array
            idsInJungle[i] = listaIdsJogadores.get(i).getId();
        }

        return idsInJungle;
    }

    public String[] getSquareInfo(int squareNr) {

        /// RESTRIÇÕES
        // Caso o nrSquare seja inválido, a função deve retornar null.
        // squareNr — números de quadrados do mapa (posições do mapa)
        if (squareNr < casaPartida || squareNr > posicaoFinalJogo) {
            return null;
        }

        /*
        Cada elemento do array deve ter a seguinte informação:
            - [0] => Nome do ficheiro com a imagem a colocar nesse posição
            - [1] => Uma descrição textual do que existe nessa posição (nesta fase pode ser apenas “Vazio” ou “Meta”)
            - [2] => Uma String contendo os identificadores dos jogadores que estão nessa posição, separados por
            vírgula (ex: “3,5” — estão lá os jogadores 3 e 5).
         */

        String[] infoPosCaixasNoMapa = new String[3];

        if (squareNr == posicaoFinalJogo) {
            infoPosCaixasNoMapa[0] = "finish.png";
            infoPosCaixasNoMapa[1] = "Meta";
        } else {
            infoPosCaixasNoMapa[0] = "blank.png";
            infoPosCaixasNoMapa[1] = "Vazio";
        }
        infoPosCaixasNoMapa[2] = "";

        for (Alimento alimento : alimentos) {
            int posicaoAlimento = alimento.getPosicaoAlimento();
            String idAlimento = alimento.getId();
            String mostrarToolTip = alimento.toolTip();
            String imagemAlimento = alimento.getImagem();

            //incrementarTurno();
            //atualizarContagemJogadasCarne(0);



            //TODO deve passar a retornar informação do alimento, quando nesse slot esteja algum alimento.
            // Mostrar uma tooltip quando se passa o rato por cima de um alimento
            if (squareNr == posicaoAlimento) {
                switch (idAlimento) {
                    case "e", "a", "b", "m", "c" -> infoPosCaixasNoMapa[1] = mostrarToolTip;
                }

                // mostrar os alimentos no mapa
                infoPosCaixasNoMapa[0] = imagemAlimento;
                break;
            }
        }

        //
        // [2] => Uma String contendo os identificadores dos jogadores que estão nessa posição, separados por
        // vírgula (ex: “3,5” — estão lá os jogadores 3 e 5).

        String jogadoresNaCasa = "";

        for (Jogador jogador : jogadores) {
            if (jogador.getPosicaoAtual() == squareNr) {
                // se a posição do mapa tem mais do que um jogador,
                if (!jogadoresNaCasa.isEmpty()) {
                    jogadoresNaCasa += ","; // serão separados por vírgula “3,5”
                }
                // É adicionado o primeiro jogador encontrado
                jogadoresNaCasa += jogador.getId();
            }
        }

        // Se houver mais do que um jogador numa posição específica, a vírgula será mantida.
        // Senão a vírgula é retirada da última posição.
        infoPosCaixasNoMapa[2] = jogadoresNaCasa;

        /*
        // se no mapa...
        if (!jogadoresNaCasa.isEmpty()) {
            // ...houver apenas um jogador numa posição específica, a vírgula é retirada da última posição.
            infoPosCaixasNoMapa[2] = jogadoresNaCasa.substring(0, 1);
        }

        // se no mapa...
        if (!jogadoresNaCasa.isEmpty()) {
            // ...houver mais do que um jogador numa posição específica, a vírgula será mantida.
            infoPosCaixasNoMapa[2] = jogadoresNaCasa;
        }
         */
        return infoPosCaixasNoMapa;
    }

    public String[] getPlayerInfo(int playerId) {

        /*
        - [0] => O ID do jogador
        - [1] => O Nome do jogador
        - [2] => O ID da espécie associada ao jogador.
        - [3] => A energia atual do jogador, medida em unidades de energia
        - [4] => A velocidade, no formato “X..Y” => Velocidade Minima = 1..6 = Velocidade Maxima
         */
        String[] infoJogador = new String[5];

        for (Jogador jogador : jogadores) {
            if (jogador.getId() == playerId) {
                infoJogador[0] = String.valueOf(jogador.getId());
                infoJogador[1] = jogador.getNome();
                infoJogador[2] = jogador.getIdEspecie();
                infoJogador[3] = String.valueOf(jogador.getEspecie().getEnergiaAtual());
                infoJogador[4] = jogador.getEspecie().getVelocidadeMinima() + ".." + jogador.getEspecie().getVelocidadeMaxima();
                return infoJogador;
            }
        }
        return null;
    }

    public String[] getCurrentPlayerInfo() {

        /*
        Devolve informação do jogador que se encontra ativo no turno atual.
        A informação retornada está no mesmo formato da função getPlayerInfo().
         */

        String[] infoJogadorAtual = new String[5];
        // Alternar o jogador
        Jogador jogadorAtual = jogadores.get((turnoAtual - 1) % jogadores.size());

        infoJogadorAtual[0] = Integer.toString(jogadorAtual.getId());
        infoJogadorAtual[1] = jogadorAtual.getNome();
        infoJogadorAtual[2] = jogadorAtual.getIdEspecie();
        infoJogadorAtual[3] = Integer.toString(jogadorAtual.getEspecie().getEnergiaAtual());
        infoJogadorAtual[4] = jogadorAtual.getEspecie().getVelocidadeMinima() + ".." + jogadorAtual.getEspecie().getVelocidadeMaxima();

        return infoJogadorAtual;
    }

    public String[] getCurrentPlayerEnergyInfo(int nrPositions) {

        // Devolve informação de energia para o jogador que se encontra ativo no turno atual.

        /*
        - [0] => Qual será o consumo de energia deste jogador se se movimentar <nrPositions>
        - [1] => Qual será o ganho de energia se ficar no lugar
         */

        // Consumo de energia — a quantidade de unidades de energia gastas para o
        // animal se mover uma posição. Por exemplo, se o jogador se movimentar 3 posições, irá
        // gastar 3 * N, em que N é o consumo de energia da espécie respetiva

        // Ganho de energia em descanso — a quantidade de unidades de energia que o jogador
        // eganha por não avançar (seja porque assim o decidiu, seja porque já não tem energia)

        String[] infoEnergia = new String[2];


        /*
        for (int i = 0; i < jogadores.size(); i++) {
            Jogador jogadorAtual = jogadores.get(i);

            int consumoEnergia = jogadorAtual.getEspecie().getConsumoEnergia() * nrPositions;
            int ganhoEnergiaDescanso = jogadorAtual.getEspecie().getGanhoEnergiaDescanso();

            infoEnergia[0] = String.valueOf(consumoEnergia);
            infoEnergia[1] = String.valueOf(ganhoEnergiaDescanso);
        }
         */

        // Math.abs(nrPositions) se sair um valor negativo, modificar para positivo

        // Atualizar o turno e o jogador atual a se movimentar
        //jogadorAtual = jogadores.get(turnoAtual % jogadores.size());
        int casaAtual = jogadorAtual.getPosicaoAtual();
        int novaPosicaoJogador = casaAtual + nrPositions;
        int consumoEnergia = jogadorAtual.getEspecie().getConsumoEnergia() * Math.abs(nrPositions);
        int ganhoEnergiaDescanso = jogadorAtual.getEspecie().getGanhoEnergiaDescanso();

        boolean casaComAlimento = verificaCasaComAlimentoUnicornio(novaPosicaoJogador);  // Verifica se a casa tem alimento

        if (jogadorAtual.getEspecie().getId().equals("U")) {
            if (casaComAlimento) {
                infoEnergia[0] = String.valueOf(consumoEnergia);  // Consumo normal se houver alimento
            } else {
                int novoConsumoEnergia = consumoEnergia + 2;  // Aumenta o consumo em casa sem alimento
                infoEnergia[0] = String.valueOf(novoConsumoEnergia);
            }
        } else {
            infoEnergia[0] = String.valueOf(consumoEnergia);  // Para outras espécies, consumo normal
        }

        infoEnergia[1] = String.valueOf(ganhoEnergiaDescanso);  // Ganho de energia em descanso

        return infoEnergia;
    }

    public String[][] getPlayersInfo() {

        /*
        Retorna informação de todos os jogadores, no mesmo formato que o retornado pelas
        funções getPlayerInfo e getCurrentPlayerInfo.
         */

        String [][] infoGeralJogadores = new String[jogadores.size()][5];

        for (int i = 0; i < jogadores.size(); i++) {
            infoGeralJogadores[i][0] = String.valueOf(jogadores.get(i).getId());
            infoGeralJogadores[i][1] = jogadores.get(i).getNome();
            infoGeralJogadores[i][2] = String.valueOf(jogadores.get(i).getIdEspecie());
            infoGeralJogadores[i][3] = String.valueOf(jogadores.get(i).getEspecie().getEnergiaAtual());
            infoGeralJogadores[i][4] = jogadores.get(i).getEspecie().getVelocidadeMinima() + ".." + jogadores.get(i).getEspecie().getVelocidadeMaxima();
        }

        return infoGeralJogadores;
    }

    public MovementResult moveCurrentPlayer(int nrSquares, boolean bypassValidations) {
        // A cada turno alterno o jogador atual de acordo a quantidade dos jogadores em jogo
        jogadorAtual = jogadores.get((turnoAtual - 1) % jogadores.size());
        atualizarContagemJogadasCarne(turnoAtual);
        int casaAtual = jogadorAtual.getPosicaoAtual(); // CASA DE PARTIDA = 1
        int novaPosicaoJogador = casaAtual + nrSquares; // A + M
        int energiaAtual = jogadorAtual.getEspecie().getEnergiaAtual();
        int consumoEnergia = jogadorAtual.getEspecie().getConsumoEnergia();

        // Se decidir ficar na posição
        if (nrSquares == 0) {
            // Aumentar o ganhoEnergia se decidir ficar na posição
            limitarEnergia(false, true, jogadorAtual.getEspecie().getGanhoEnergiaDescanso());
            // Atualizar o turno
            incrementarTurno();
            // Verificar se o jogador consumiu algum alimento
            String alimentoConsumido = verificarConsumoDeAlimento(novaPosicaoJogador);
            if (alimentoConsumido != null) {
                jogadorAtual.aumentarNumAlimentoApanhado(1);
                return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou " + alimentoConsumido);
            }
            return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
        }

        // Se não tiver energia suficiente para fazer o movimento, fica na mesma casa
        if (energiaAtual < consumoEnergia * Math.abs(nrSquares)) {
            // Atualizar o turno
            incrementarTurno();
            return new MovementResult(MovementResultCode.NO_ENERGY, null);
        }

        // O argumento nrSquares tem que estar contido entre -6 e 6
        // No entanto, se o parâmetro bypassValidations tiver o valor true, a regra anterior não é aplicada.
        if (!isMovimentoValido(nrSquares, novaPosicaoJogador, casaPartida, bypassValidations)) {
            incrementarTurno();
            return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
        }

        // Se o jogador tentar ultrapassar a casa final do jogo, deve ficar na posição final do jogo
        if (novaPosicaoJogador >= posicaoFinalJogo) {
            // ...o jogador deve fica na posição final do jogo
            novaPosicaoJogador = posicaoFinalJogo;
            // Movimentar o jogador para a casa A + M
            jogadorAtual.alterarPosicaoAtual(novaPosicaoJogador);
            alguemChegouNaMeta = true;
            //return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
        }

        // Movimento do jogador para a casa A + M
        jogadorAtual.alterarPosicaoAtual(novaPosicaoJogador);

        // Incrementar o número de distância percorrida
        jogadorAtual.incrementarNumeroPosicoesPercorridas(Math.abs(nrSquares));

        // Verificar se o jogador Unicórnio vai para uma casa com ou sem alimento na nova posição
        casaComAlimento = verificaCasaComAlimentoUnicornio(novaPosicaoJogador);

        // Verificar se existem jogadores na casa do meio para nova condição de vitória
        existeJogadorNoMeio = verificaJogadorNaCasaDoMeio();

        // Definir a energia do jogador quando recua ou avança
        setEnergyOfNumberOfSquare(nrSquares, energiaAtual, consumoEnergia, casaComAlimento);

        // Se não tiver energia suficiente para fazer o movimento, fica na mesma casa
        if (energiaAtual < consumoEnergia * Math.abs(nrSquares)) {
            // Atualizar o turno
            incrementarTurno();
            return new MovementResult(MovementResultCode.NO_ENERGY, null);
        }

        // Verficar qual o alimento consumido
        String alimentoConsumido = verificarConsumoDeAlimento(novaPosicaoJogador);
        MovementResult resultadoConsumo = processarMovimentoParaConsumoAlimento(alimentoConsumido, jogadorAtual);

        // Se o resultado do consumo de alimento não for null, retornar VALID_MOVEMENT ou CAUGHT_FOOD
        if (resultadoConsumo != null) {
            return resultadoConsumo;
        }

        // Atualizar o turno
        incrementarTurno();
        return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
    }

    public String[] getWinnerInfo() {
        boolean jaExisteUmVencedor = false;
        String[] infoJogadorVencedor = new String[4];
        ArrayList<Jogador> vencedoresEmNovasCondicoes = new ArrayList<>();

        for (Jogador jogador : jogadores) {

            if (jogador.getPosicaoAtual() == posicaoFinalJogo) {
                alguemChegouNaMeta = true;
            }

            // Se algum jogador chegou à posição final do jogo mostrar a info do jogador vencedor
            if (alguemChegouNaMeta) {
                jogadores.sort(
                        Comparator.comparingInt(Jogador::getPosicaoAtual)
                                // inverter com base na posição atual dos jogadores
                                .reversed()
                                // Se as posições forem iguais, ordena por ID
                                .thenComparingInt(Jogador::getId)
                );

                jogador = jogadores.get(0);

                infoJogadorVencedor[0] = String.valueOf(jogador.getId());
                infoJogadorVencedor[1] = jogador.getNome();
                infoJogadorVencedor[2] = jogador.getIdEspecie();
                infoJogadorVencedor[3] = String.valueOf(jogador.getEspecie().getEnergiaAtual());
                return infoJogadorVencedor;
            }

            // Se nenhum jogador chegou à meta e existe uma grande distância entre os jogadores
            // Ganha o jogador mais distante da meta
            if (existeUmJogadorMuitoDistanteDaMeta()) {
                jogador = getJogadorMaisDistanteDaMeta(jogadores);
                infoJogadorVencedor[0] = String.valueOf(jogador.getId());
                infoJogadorVencedor[1] = jogador.getNome();
                infoJogadorVencedor[2] = jogador.getIdEspecie();
                infoJogadorVencedor[3] = String.valueOf(jogador.getEspecie().getEnergiaAtual());
                return infoJogadorVencedor;
            }

            // TODO Nova Condição Vencedor:
            calcularCasaDoMeio();
            jogadorComMaisEnergia = jogadores.get(0);

            // Quando estiverem presentes dois jogadores na “casa do meio”
            if (jogador.getPosicaoAtual() == casaDoMeio) {
                vencedoresEmNovasCondicoes.add(jogador);
                if (jogador.getEspecie().getEnergiaAtual() > jogadorComMaisEnergia.getEspecie().getEnergiaAtual()) {
                    jogadorComMaisEnergia = jogador;
                    jaExisteUmVencedor = true;
                }
            }

            // Se existir, pelo menos, um jogador entre a “casa do meio” e a meta (vencedoresEmNovasCondicoes)
            // O vencedor do jogo é o jogador com mais energia na “casa do meio”
            if (jaExisteUmVencedor && vencedoresEmNovasCondicoes.size() >= 2) {
                infoJogadorVencedor[0] = String.valueOf(jogadorComMaisEnergia.getId());
                infoJogadorVencedor[1] = jogadorComMaisEnergia.getNome();
                infoJogadorVencedor[2] = jogadorComMaisEnergia.getIdEspecie();
                infoJogadorVencedor[3] = String.valueOf(jogadorComMaisEnergia.getEspecie().getEnergiaAtual());
                return infoJogadorVencedor;
            }
        }

        return null; // Nenhum jogador venceu ainda
    }

    public ArrayList<String> getGameResults() {
        ArrayList<Jogador> jogadoresEmJogo = new ArrayList<>();
        for (Jogador jogador : jogadores) {
            if (jogador.getEspecie().getEnergiaAtual() >= 0) {
                jogadoresEmJogo.add(jogador);
            }
        }
        ArrayList<String> resultados = new ArrayList<>();
        int posicaoChegada = 0;
        if (alguemChegouNaMeta) {
            for (Jogador jogador : jogadoresEmJogo) {
                String nome = jogador.getNome();
                String nomeEspecie = jogador.getEspecie().getNome();
                int posicaoAtual = jogador.getPosicaoAtual();
                int distancia = jogador.getNumeroPosicoesPercorridas();
                int numAlimento = jogador.getNumeroAlimento();

                resultados.add("#" + (posicaoChegada + 1) + " " + nome + ", " + nomeEspecie + ", " + posicaoAtual
                        + ", " + distancia + ", " + numAlimento);
                posicaoChegada++;
            }
        } else {
            if (existeUmJogadorMuitoDistanteDaMeta()) {
                ArrayList<Jogador> jogadoresEmLongaDistancia = new ArrayList<>(jogadoresEmJogo);
                for (Jogador jogador : jogadoresEmJogo) {
                    Jogador jogadorMaisDistante = getJogadorMaisDistanteDaMeta(jogadoresEmLongaDistancia);
                    String nome = jogadorMaisDistante.getNome();
                    String nomeEspecie = jogadorMaisDistante.getEspecie().getNome();
                    int posicaoAtual = jogadorMaisDistante.getPosicaoAtual();
                    int distancia = jogadorMaisDistante.getNumeroPosicoesPercorridas();
                    int numAlimento = jogadorMaisDistante.getNumeroAlimento();

                    resultados.add("#" + (posicaoChegada + 1) + " " + nome + ", " + nomeEspecie + ", " + posicaoAtual
                            + ", " + distancia + ", " + numAlimento);

                    // remover o jogadorMaisDistante da lista, atualizar a lista (reset)
                    jogadoresEmLongaDistancia.remove(jogadorMaisDistante);
                    posicaoChegada++;
                }
            }

            // TODO Nova Condição Vencedor:
            obterVencedorNovaCondicao(jogadoresEmJogo, posicaoChegada, resultados);
        }

        return resultados;
    }

    public JPanel getAuthorsPanel() {
        JLabel author = new JLabel();
        JPanel mostrarCredito = new JPanel();

        author.setText("Lodney Santos");

        mostrarCredito.add(author);
        return mostrarCredito;
    }

    public String whoIsTaborda() {
        return "Wrestling";
    }

    public boolean saveGame(File file) {

        // Quebrar a linha
        String nextLine = System.lineSeparator();

        try {
            FileWriter guardarJogo = new FileWriter(file.getAbsoluteFile());
            Jogador jogadorAtual = jogadores.get((turnoAtual - 1) % jogadores.size());

            for (Jogador jogador : jogadores) {
                if (jogadorComMaisEnergia == null || jogador.getEspecie().getEnergiaAtual() >
                        jogadorComMaisEnergia.getEspecie().getEnergiaAtual()) {
                    jogadorComMaisEnergia = jogador;
                }
            }

            // Guardar a informação geral
            guardarJogo.write("Turno atual: " + turnoAtual + nextLine);
            guardarJogo.write("Dimensão do mapa: " + posicaoFinalJogo + nextLine);
            guardarJogo.write("Jogador atual: " + jogadorAtual.getId() + nextLine);
            guardarJogo.write("Jogador com mais energia: " + jogadorComMaisEnergia.getId() + nextLine);
            guardarJogo.write("Jogadores que consumiram bananas: " + jogadoresQueConsumiramBanana + nextLine);
            guardarJogo.write("Casa do meio do mapa: " + casaDoMeio + nextLine);
            guardarJogo.write("Já existe vencedor: " + alguemChegouNaMeta + nextLine);

            // Guardar a informação geral dos jogadores
            guardarJogo.write(nextLine);
            guardarJogo.write("Informação geral dos jogadores em jogo: " + nextLine);
            guardarJogo.write("Quantidade de jogadores em jogo: " + jogadores.size() + nextLine);

            for(Jogador jogador : jogadores) {
                guardarJogo.write(jogador.getId() + " : " + jogador.getNome() + " : " + jogador.getPosicaoAtual() + " : "
                        + jogador.getIdEspecie() + " : " + jogador.getEspecie().getEnergiaAtual() + " : " +
                        jogador.getNumeroPosicoesPercorridas() + " : " + jogador.getNumeroAlimento() + " : " +
                        jogador.getEspecie().getTipoAlimentacaoDaEspecie() + " : " + jogador.getEspecie().getConsumoEnergia()
                        + " : " + jogador.getEspecie().getGanhoEnergiaDescanso() + " : " + jogador.getEspecie().getVelocidadeMinima()
                        + " : " + jogador.getEspecie().getVelocidadeMaxima());

                guardarJogo.write(nextLine);
            }

            // Guardar a informação geral dos alimentos
            guardarJogo.write(nextLine);
            guardarJogo.write("Informação geral dos alimentos em jogo: " + nextLine);
            guardarJogo.write("Quantidade de alimentos em jogo: " + alimentos.size() + nextLine);

            for(Alimento alimento : alimentos) {
                guardarJogo.write(alimento.getId() + " : " + alimento.getPosicaoAlimento() + " : "
                        + alimento.getNumeroBananasON() + " : " + alimento.getNumroJogadasCarne()
                        + " : " + alimento.getNumeroAleatorioCog() + " : " + alimento.toolTip());

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
            ArrayList<Jogador> jogadoresCarregados = new ArrayList<>();
            ArrayList<Alimento> alimentosCarregados = new ArrayList<>();
            HashMap<Integer, Integer> jogadoresQueConsumiramBananas = new HashMap<>();
            String linha;
            int jogadorAjogar = 0;
            int jogadorComMaisEnergia = 0;

            while ((linha = carregarFicheiroGuardado.readLine()) != null) {
                if (linha.startsWith("Turno atual: ")) {
                    turnoAtual = Integer.parseInt(linha.split(":")[1].trim());

                } else if (linha.startsWith("Dimensão do mapa: ")) {
                    posicaoFinalJogo = Integer.parseInt(linha.split(":")[1].trim());

                } else if (linha.startsWith("Jogador atual: ")) {
                    jogadorAjogar = Integer.parseInt(linha.split(":")[1].trim());
                    definirJogadorAtualLoadGame(jogadorAjogar, jogadoresCarregados);

                } else if (linha.startsWith("Jogador com mais energia: ")) {
                    jogadorComMaisEnergia = Integer.parseInt(linha.split(":")[1].trim());

                } else if (linha.startsWith("Casa do meio do mapa: ")) {
                    casaDoMeio = Integer.parseInt(linha.split(":")[1].trim());

                } else if (linha.startsWith("Já existe vencedor: ")) {
                    alguemChegouNaMeta = Boolean.parseBoolean(linha.split(":")[1].trim());

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

            jogadores.clear();
            jogadores.addAll(jogadoresCarregados);

            alimentos.clear();
            alimentos.addAll(alimentosCarregados);

            return true;
        } catch (IOException e) {
            return false;
        }
    }




    /**
     * -----------------------------------------FUNÇÕES AUXILIARES---------------------------------------
     */

    /**
     * --------------------------------------moveCurrentPlayer()-------------------------------------
     */

    private MovementResult processarMovimentoParaConsumoAlimento(String alimentoConsumido, Jogador jogadorAtual) {
        if (alimentoConsumido != null) {
            // Se for carnívoro, omnivoro ou se for herbivoro e o alimento consumido não é carne
            // contar o número de alimento apanhado.
            if (!jogadorAtual.getEspecie().getTipoAlimentacaoDaEspecie().equals("herbívoro") ||
                    (jogadorAtual.getEspecie().getTipoAlimentacaoDaEspecie().equals("herbívoro")
                            && !alimentoConsumido.equals("Carne"))) {

                jogadorAtual.aumentarNumAlimentoApanhado(1);
                // Registrar o alimento consumido
                registrarAlimentoConsumido(alimentoConsumido);
                // senão se for herbívoro e o alimento consumido é carne...
            } else {
                // Atualizar o turno e ignorar o consumo de carne por herbívoros
                incrementarTurno();
                return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
            }
            // Registrar o alimento consumido
            registrarAlimentoConsumido(alimentoConsumido);
            // Atualizar o turno
            incrementarTurno();
            return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou " + alimentoConsumido);
        }
        return null;
    }

    private void registrarAlimentoConsumido(String alimento) {
        alimentosConsumidos.add(alimento);
    }

    private String verificarConsumoDeAlimento(int posicao) {
        for (Alimento alimento : alimentos) {
            if (alimento.getPosicaoAlimento() == posicao) {
                String idAlimento = alimento.getId();
                String tipoDeAlimentacao = jogadorAtual.getEspecie().getTipoAlimentacaoDaEspecie();

                int alteracaoEnergia;
                switch (idAlimento) {
                    // ERVA
                    case "e" -> alteracaoEnergia = alimento.consumir(tipoDeAlimentacao, jogadorAtual);
                    // ÁGUA
                    case "a" -> alteracaoEnergia = alimento.consumir(tipoDeAlimentacao, jogadorAtual);
                    // BANANA
                    case "b" -> alteracaoEnergia = alimento.consumir(tipoDeAlimentacao, jogadorAtual, alimento, jogadoresQueConsumiramBanana);
                    // CARNE
                    case "c" -> alteracaoEnergia = alimento.consumir(tipoDeAlimentacao, jogadorAtual, turnoAtual, alimento);
                    // COGUMELO MÁGICO
                    case "m" -> alteracaoEnergia = alimento.consumir(tipoDeAlimentacao, jogadorAtual, turnoAtual, alimento);

                    default -> alteracaoEnergia = 0;
                }
                // Verificar se o jogador é um unicórnio ignorar todos os alimentos
                if (jogadorAtual.getEspecie().getId().equals("U")) {
                    if (alteracaoEnergia == 0) {
                        return null;
                    }
                }

                // Se o valor após consumir algum alimento for acima de zero ou igual (agua) aumenta...
                // ...o ganho de energia após consumir o alimento
                if (alteracaoEnergia > 0) {
                    limitarEnergia(false, true, alteracaoEnergia);
                    // Se não diminui o ganho de energia dependendo da espécie.
                } else {
                    limitarEnergia(true, false, alteracaoEnergia);
                }
                return alimento.getNome();
            }
        }
        return null;
    }

    private boolean validarVelocidadeEspecie(int velocidade) {
        String especieID = jogadorAtual.getEspecie().getId();

        return switch (especieID) {
            case "E", "Z" -> // Elefante ou Tarzan
                    velocidade >= 1 && velocidade <= 6;
            case "L" -> // Leão
                    velocidade >= 4 && velocidade <= 6;
            case "T" -> // Tartaruga
                    velocidade >= 1 && velocidade <= 3;
            case "P" -> // Pássaro
                    velocidade >= 5 && velocidade <= 6;
            case "U" -> // Unicórnio
                    velocidade >= 3 && velocidade <= 6;
            default -> false;
        };
    }

    public void setEnergyOfNumberOfSquare(int nrSquares, int energiaAtual, int consumoEnergia, boolean casaComAlimento) {
        if (nrSquares != 0) {

            int energiaGasta = consumoEnergia * Math.abs(nrSquares);
            Especie especieJogador = jogadorAtual.getEspecie();

            if (especieJogador.getId().equals("U")) {
                if (casaComAlimento) {
                    int novaEnergia = energiaAtual - energiaGasta;
                    especieJogador.definirEnergiaAtual(novaEnergia);
                } else {
                    int energiaAtualizada = energiaAtual + 2;
                    int novaEnergia = energiaAtualizada - energiaGasta;
                    especieJogador.definirEnergiaAtual(novaEnergia);
                }
            } else {
                int novaEnergia = energiaAtual - energiaGasta;
                especieJogador.definirEnergiaAtual(novaEnergia);
            }
        }
    }

    private boolean verificaCasaComAlimentoUnicornio(int novaPosicaoJogador) {
        for (Alimento alimento : alimentos) {
            if (alimento.getPosicaoAlimento() == novaPosicaoJogador) {
                return true;
            }
        }
        return false;
    }

    private boolean verificaJogadorNaCasaDoMeio() {
        calcularCasaDoMeio();
        for (Jogador jogador : jogadores) {
            if (jogador.getPosicaoAtual() == casaDoMeio) {
                return true;
            }
        }
        return false;
    }

    private void limitarEnergia(boolean avancouOuRecou, boolean ficou, int valorAlteracaoEnergia) {
        /*
        A energia de qualquer jogador nunca pode ultrapassar os 200, seja por descansar, seja
        por efeito de alimentos. Caso isso aconteça, a energia mantém-se nos 200.
         */
        int limiteEnergia = 200;

        int novaEnergia = jogadorAtual.getEspecie().getEnergiaAtual();

        if (avancouOuRecou) {
            novaEnergia -= Math.abs(valorAlteracaoEnergia);
            novaEnergia = Math.max(novaEnergia, 0); // Garantir que a energia não fica negativa

        } else if (ficou) {
            novaEnergia += valorAlteracaoEnergia;
            novaEnergia = Math.min(novaEnergia, limiteEnergia); // Garantir que a energia não ultrapassa o limite
        }

        jogadorAtual.getEspecie().definirEnergiaAtual(novaEnergia);
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

    private void atualizarContagemJogadasCarne(int turnoAtual) {
        for (Alimento alimento : alimentos) {
            alimento.setNumroJogadasCarne(turnoAtual);
        }
    }


    /**
     * ----------------------getWinnerInfo() E getGameResults (DISTANCIA E NOVA CONDIÇÃO)------------------------
     */

    private void calcularCasaDoMeio() {
        if (posicaoFinalJogo % 2 != 0) {
            casaDoMeio = ((posicaoFinalJogo / 2) + 1);
        } else {
            // Se o tabuleiro tem tamanho 10, logo, a casa do meio é a casa 5.
            casaDoMeio = (posicaoFinalJogo / 2);
        }
    }

    private boolean existeUmJogadorMuitoDistanteDaMeta() {
        int distanciaPrimeiroJogador = Integer.MAX_VALUE;
        int distanciaSegundoJogador = Integer.MAX_VALUE;

        // Verificar as duas menores distâncias entre os jogadores e a posição final do jogo
        for (Jogador jogador : jogadores) {
            int distancia = Math.abs(posicaoFinalJogo - jogador.getPosicaoAtual());

            if (distancia < distanciaPrimeiroJogador) {
                distanciaSegundoJogador = distanciaPrimeiroJogador;
                distanciaPrimeiroJogador = distancia;
            } else if (distancia < distanciaSegundoJogador) {
                distanciaSegundoJogador = distancia;
            }
        }

        int metadeDaMeta = posicaoFinalJogo / 2;
        int distanciaEntreJogadores = (distanciaSegundoJogador - distanciaPrimeiroJogador);

        // Verificar se a distância entre os jogadores é maior que a metade do tamanho do mapa
        return distanciaEntreJogadores > metadeDaMeta;
    }


    private Jogador getJogadorMaisDistanteDaMeta(List<Jogador> jogadores) {
        int maiorDistancia = Integer.MIN_VALUE;
        Jogador jogadorMaisDistante = null;

        // Encontrar o jogador com a maior distância da meta
        for (Jogador jogador : jogadores) {
            int distancia = Math.abs(posicaoFinalJogo - jogador.getPosicaoAtual());
            if (distancia > maiorDistancia) {
                maiorDistancia = distancia;
                jogadorMaisDistante = jogador;
            }
        }

        return jogadorMaisDistante;
    }

    private void obterVencedorNovaCondicao(ArrayList<Jogador> jogadoresEmJogo, int posicaoChegada, ArrayList<String> resultados) {
        // TODO Lógica vencedor nova condição
        // Os jogadores na casa do meio competem pelo primeiro lugar (ganha o jogador com maior energia, 1.º lugar),
        // Verificar as posições dos proximos jogadores em jogo. (jogador adiantado vs jogador casa do meio com menos energia)
        // e se existir um jogador mais adiantado, deve ser o segundo (2.º lugar),
        // enquanto que o jogador na casa do meio com menos energia deve ser o terceiro.

        calcularCasaDoMeio();
        Jogador jogadorVencedorCasaDoMeio = null;
        Jogador segundoClassificado = null;
        Jogador terceiroClassificado = null;

        for (Jogador jogador : jogadoresEmJogo) {
            // Competição pelo primeiro lugar na casa do meio (maior energia)
            if (jogador.getPosicaoAtual() == casaDoMeio) {

                // Verificar se o jogador é o vencedor da casa do meio (maior energia)
                if (jogadorVencedorCasaDoMeio == null || jogador.getEspecie().getEnergiaAtual() > jogadorVencedorCasaDoMeio.getEspecie().getEnergiaAtual()) {

                    // testgetGameResults_NovaCondicaoVencedor e testGetGameResults_NovaCondicaoVencedor4 (Outros cenários)
                    // (manter ordem correta posições de chegada na classificação)
                    terceiroClassificado = segundoClassificado;
                    segundoClassificado = jogadorVencedorCasaDoMeio;

                    // Vencedor (1.º Lugar)
                    jogadorVencedorCasaDoMeio = jogador;

                // Jogador com menos energia na casa do meio (2.º Lugar)
                } else if (segundoClassificado == null || jogador.getEspecie().getEnergiaAtual() > segundoClassificado.getEspecie().getEnergiaAtual()) {

                    terceiroClassificado = segundoClassificado;
                    segundoClassificado = jogador;

                    // Se existem mais que 2 jogadores na casa do meio classificar como terceiro colocado (3.º Lugar)
                } else if (terceiroClassificado == null || jogador.getEspecie().getEnergiaAtual() > terceiroClassificado.getEspecie().getEnergiaAtual()) {
                    terceiroClassificado = jogador;
                }

            // Competição pelo segundo lugar (jogador mais adiantado)
            } else if (jogador.getPosicaoAtual() > casaDoMeio) {

                if (segundoClassificado == null || jogador.getNumeroPosicoesPercorridas() > segundoClassificado.getNumeroPosicoesPercorridas()) {
                    terceiroClassificado = segundoClassificado;

                    // Vencedor por distância (2.º Lugar jogador mais adiantado)
                    segundoClassificado = jogador;

                }

            } else if (terceiroClassificado == null || jogador.getEspecie().getEnergiaAtual() < terceiroClassificado.getEspecie().getEnergiaAtual()) {
                // Jogador com menos energia na casa do meio (3.º Lugar)
                terceiroClassificado = jogador;
            }
        }

        // Classificar jogadores
        classificarJogador(resultados, posicaoChegada, jogadorVencedorCasaDoMeio);
        jogadoresEmJogo.remove(jogadorVencedorCasaDoMeio);
        posicaoChegada++;

        classificarJogador(resultados, posicaoChegada, segundoClassificado);
        jogadoresEmJogo.remove(segundoClassificado);
        posicaoChegada++;

        classificarJogador(resultados, posicaoChegada, terceiroClassificado);
        jogadoresEmJogo.remove(terceiroClassificado);
        posicaoChegada++;

        // Se existirem mais jogadores, continuar a classificação
        for (Jogador emJogo : jogadoresEmJogo) {
            classificarJogador(resultados, posicaoChegada, emJogo);
            posicaoChegada++;
        }
    }

    private void classificarJogador(ArrayList<String> resultados, int posicaoChegada, Jogador jogador) {
        if (jogador != null) {
            String nome = jogador.getNome();
            String nomeEspecie = jogador.getEspecie().getNome();
            int posicaoAtual = jogador.getPosicaoAtual();
            int distancia = jogador.getNumeroPosicoesPercorridas();
            int numAlimento = jogador.getNumeroAlimento();

            resultados.add("#" + (posicaoChegada + 1)  + " " + nome + ", " + nomeEspecie + ", " + posicaoAtual
                    + ", " + distancia + ", " + numAlimento);
        }
    }



    /**
     * -----------------------------------------LOADGAME()-------------------------------------------
     */

    private Jogador carregarDadosDoJogador(String[] playerInfo) {
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

        Especie especieJogadorCarregado = Especie.identificarEspecie(novoIdEspecie);
        Jogador novoJogador = new Jogador(novoIdJogador, novoNomeJogador, novoIdEspecie, novaPosicaoJogador, especieJogadorCarregado);

        novoJogador.setId(novoIdJogador);
        novoJogador.setNome(novoNomeJogador);
        novoJogador.alterarPosicaoAtual(novaPosicaoJogador);
        novoJogador.setIdEspecie(novoIdEspecie);
        novoJogador.getEspecie().definirEnergiaAtual(novaEnergiaAtual);
        novoJogador.incrementarNumeroPosicoesPercorridas(novaPosicaoPercorridas);
        novoJogador.aumentarNumAlimentoApanhado(novoNumAlimento);
        novoJogador.getEspecie().setTipoAlimentacaoDaEspecie(novoTipoAlimentacaoEspecie);
        novoJogador.getEspecie().definirConsumoEnergia(novoConsumoEnergia);
        novoJogador.getEspecie().definirGanhoEnergiaDescanso(novoGanhoEnergia);
        novoJogador.getEspecie().definirVelocidadeMinima(novaVelocidadeMinima);
        novoJogador.getEspecie().definirVelocidadeMaxima(novaVelocidadeMaxima);

        return novoJogador;
    }

    private Alimento carregarDadosAlimento(String[] foodInfo) {
        String novoIdAlimento = foodInfo[0];
        int novaPosicaoAlimento = Integer.parseInt(foodInfo[1]);
        int novoNumeroBananasON = Integer.parseInt(foodInfo[2]);
        int novoNumroJogadasCarne= Integer.parseInt(foodInfo[3]);
        int novoNumeroAleatorioCog = Integer.parseInt(foodInfo[4]);

        Alimento carregarAlimento = Alimento.identificarAlimento(novoIdAlimento,novaPosicaoAlimento);

        carregarAlimento.setNumeroBananasON(novoNumeroBananasON);
        carregarAlimento.setNumroJogadasCarne(novoNumroJogadasCarne);
        carregarAlimento.setNumeroAleatorioCog(novoNumeroAleatorioCog);

        return carregarAlimento;
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

    private void carregarJogadores(BufferedReader reader, int quantidadeJogadoresEmJogo, ArrayList<Jogador> jogadores) throws IOException {
        for (int i = 0; i < quantidadeJogadoresEmJogo; i++) {
            String linha = reader.readLine();
            if (linha.trim().isEmpty()) {
                continue;
            }
            Jogador novoJogador = carregarDadosDoJogador(linha.split(" : "));
            jogadores.add(novoJogador);
        }
    }

    private void carregarAlimentos(BufferedReader reader, int quantidadeAlimentosEmJogo, ArrayList<Alimento> alimentos) throws IOException {
        for (int i = 0; i < quantidadeAlimentosEmJogo; i++) {
            String linha = reader.readLine();
            if (linha.trim().isEmpty()) {
                continue;
            }
            Alimento novoAlimento = carregarDadosAlimento(linha.split(" : "));
            alimentos.add(novoAlimento);
        }
    }

    private void definirJogadorAtualLoadGame(int idJogador, ArrayList<Jogador> jogadores) {
        for (Jogador jogador : jogadores) {
            if (jogador.getId() == idJogador) {
                jogadorAtual = jogador;
                break;
            }
        }
    }


    /**
     * ------------------------------------------TURNOS E RESET()---------------------------------------------
     */

    private void incrementarTurno() {
        turnoAtual++;
    }

    private void incrementarReset() {
        jogadores = new ArrayList<>(); // reset da lista de jogadores.
        alimentos = new ArrayList<>(); // reset da lista de alimentos
        jogadorAtual = null; // reset do jogadorAtual
        jogadorComMaisEnergia = null; // reset do jogadorComMaisEnergia
        idJogadoresEmJogo = new HashMap<>(); // reset do hashmap dos ‘ids’ dos jogadores no início do jogo
        jogadoresQueConsumiramBanana = new HashMap<>(); // reset do hashmap dos ‘ids’ dos jogadores consumiram bananas
        alimentosConsumidos = new HashSet<>(); // reset do hashset dos alimentos consumidos durante o jogo

        alguemChegouNaMeta = false;
        casaComAlimento = false;
        existeJogadorNoMeio = false;
        casaPartida = 1; // reset casa partida de todos os jogadores
        turnoAtual = 0; // reset do turno atual do jogo.
        posicaoFinalJogo = 0; // reset posicão final do mapa de jogo
        casaDoMeio = 0; // reset casa do meio do mapa de jogo
        atualizarContagemJogadasCarne(0);
    }


    /**
     * ----------------------Métodos de acesso(Facilitar acesso nos Testes Unitários e fn KOTLIN)()---------------------------
     */

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }

    public Set<String> getAlimentosConsumidos() {
        return alimentosConsumidos;
    }
}
