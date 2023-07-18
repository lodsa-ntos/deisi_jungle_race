package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.validar.ValidadorJogador;
import pt.ulusofona.lp2.deisiJungle.validar.ValidatorAlimento;

import javax.swing.*;
import java.io.File;
import java.util.*;
/*-------------------------------------------------DEISI JUNGLE----------------------------------------------------
    Certo dia, numa savana muito (muito) longe daqui, os animais decidiram descobrir quem seria o melhor atleta entre
    eles. Para o fazer, definiram um conjunto de provas desportivas que iriam disputar entre si — quem vencesse mais
    provas seria considerado o Rei da Selva. Rapidamente a notícia chegou ao Tarzan, que não quis deixar de participar
    nas provas, convencido de que facilmente as venceria. A primeira prova a disputar é a prova de atletismo.
    Os animais vão-se reunir numa pista e competir para determinar qual o melhor atleta.
 ----------------------------------------------------------------------------------------------------------------- */

// Classe responsável por gerir o jogo
public class GameManager {

    private ArrayList<Jogador> jogadores = new ArrayList<>();
    private ArrayList<Alimento> alimentos = new ArrayList<>();
    private HashMap<Integer,Integer> idJogadoresEmJogo = new HashMap<>();
    private Jogador jogadorAtual;
    private int posicaoFinalJogo;
    private int casaPartida;
    private int turnoAtual;
    boolean jogadorAvancou;
    boolean jogadorRecuou;
    boolean jogadorFicou;

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

        // Cada vez que o jogo é criado o programa vai fazer a reinicialização das variaveis para o valor inicial
        incrementarReset();
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


            //System.out.println("Jogador ⇒ " + jogadorAtual);
            //System.out.println(jogadorAtual.getEspecie().toString());
            //System.out.println(getPlayerIds(1));

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

            // TODO ID - tem que ser um dos que foi retornado pela função getFoodTypes()
            ValidatorAlimento.validarIDAlimento(idTipo, getFoodTypes());

            // TODO POSIÇÃO - Os alimentos têm que estar posicionados dentro dos limites do terreno.
            ValidatorAlimento.validarPosicaoAlimentos(posicaoAtualAlimento, casaPartida, posicaoFinalJogo);


            Alimento tipoAlimento = Alimento.identificarAlimento(idTipo, posicaoAtualAlimento);
            alimentos.add(tipoAlimento);
            //System.out.println(tipoAlimento.toolTip());
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

            //TODO deve passar a retornar informação do alimento, quando nesse slot esteja algum alimento.
            // Mostrar uma tooltip quando se passa o rato por cima de um alimento
            if (squareNr == posicaoAlimento) { // Até a posição dos alimentos

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
                infoJogador[3] = String.valueOf(jogador.getEspecie().getEnergiaInicial());
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
        Jogador jogadorAtual = jogadores.get(turnoAtual % jogadores.size());

        infoJogadorAtual[0] = Integer.toString(jogadorAtual.getId());
        infoJogadorAtual[1] = jogadorAtual.getNome();
        infoJogadorAtual[2] = jogadorAtual.getIdEspecie();
        infoJogadorAtual[3] = Integer.toString(jogadorAtual.getEspecie().getEnergiaInicial());
        infoJogadorAtual[4] = jogadorAtual.getEspecie().getVelocidadeMinima() + ".." + jogadorAtual.getEspecie().getVelocidadeMaxima();

        return infoJogadorAtual;
    }

    public String[] getCurrentPlayerEnergyInfo(int nrPositions) {

        //Devolve informação de energia para o jogador que se encontra ativo no turno atual.

        /*
        - [0] => Qual será o consumo de energia deste jogador se se movimentar <nrPositions>
        - [1] => Qual será o ganho de energia se ficar no lugar
         */

        //Consumo de energia — a quantidade de unidades de energia gastas para o
        //animal se mover uma posição. Por exemplo, se o jogador se movimentar 3 posições, irá
        //gastar 3 * N, em que N é o consumo de energia da espécie respetiva

        // Ganho de energia em descanso — a quantidade de unidades de energia que o jogador
        //ganha por não avançar (seja porque assim o decidiu, seja porque já não tem energia)

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

        int consumoEnergia = jogadorAtual.getEspecie().getConsumoEnergia() * Math.abs(nrPositions);
        int ganhoEnergiaDescanso = jogadorAtual.getEspecie().getGanhoEnergiaDescanso();

        infoEnergia[0] = String.valueOf(consumoEnergia);
        infoEnergia[1] = String.valueOf(ganhoEnergiaDescanso);

        //System.out.println(Arrays.toString(infoEnergia));

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
            infoGeralJogadores[i][3] = String.valueOf(jogadores.get(i).getEspecie().getEnergiaInicial());
            infoGeralJogadores[i][4] = jogadores.get(i).getEspecie().getVelocidadeMinima() + ".." + jogadores.get(i).getEspecie().getVelocidadeMaxima();
        }

        return infoGeralJogadores;
    }

    public MovementResult moveCurrentPlayer(int nrSquares, boolean bypassValidations) {
        // A cada turno alterno o jogador atual conforme a quantidade dos jogadores em jogo
        jogadorAtual = jogadores.get(turnoAtual % jogadores.size());
        int casaAtual = jogadorAtual.getPosicaoAtual(); // CASA DE PARTIDA = 1
        int novaPosicaoJogador = casaAtual + nrSquares; // A + M
        int energiaAtual = jogadorAtual.getEspecie().getEnergiaInicial(); // energiaAtual
        int consumoEnergia = jogadorAtual.getEspecie().getConsumoEnergia(); // consumoEnergia
        int ganhoEnergia = jogadorAtual.getEspecie().getGanhoEnergiaDescanso(); // ganhoEnergia

        // Se decidir ficar na posição...
        if (nrSquares == 0) {
            // ...o jogador irá descansar.
            jogadorAtual.getEspecie().setEnergiaInicial(energiaAtual + ganhoEnergia);
            // Atualizar o turno, troca o jogador.
            incrementarTurno();
            // Verificar se o jogador consumiu algum alimento
            String alimentoConsumido = verificarConsumoDeAlimento(novaPosicaoJogador);
            if (alimentoConsumido != null) {
                jogadorAtual.contarNumAlimentoApanhado(1);
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

        // Se tentar recuar estando na casa de partida = INVALID_MOVEMENT
        if (novaPosicaoJogador < casaPartida) {
            incrementarTurno();
            return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
        }

        // Se o jogador tentar ultrapassar a casa final do jogo
        if (novaPosicaoJogador > posicaoFinalJogo) {
            // Se não tiver energia suficiente para fazer o movimento, fica na mesma casa
            if (energiaAtual < consumoEnergia * Math.abs(nrSquares)) {
                // Atualizar o turno
                incrementarTurno();
                return new MovementResult(MovementResultCode.NO_ENERGY, null);
            }
        }

        // Se o parâmetro bypassValidations tiver o valor true.
        if (!bypassValidations) {
            // Se nrSquares é -6 ou 6 = INVALID_MOVEMENT || se as espécies não se movimentam nas velocidades mínima e máxima = INVALID_MOVEMENT
            if (nrSquares < -6 || nrSquares > 6 || !validarVelocidadeEspecie(Math.abs(nrSquares))) {
                // Atualizar o turno
                incrementarTurno();
                return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
            }

            // Se decidir avançar ou recuar
            if (novaPosicaoJogador > casaPartida && novaPosicaoJogador < posicaoFinalJogo) {
                // Movimentar o jogador para a casa A + M ou A-M
                jogadorAtual.setPosicaoAtual(novaPosicaoJogador);
                // o jogador irá consumir energia
                jogadorAtual.getEspecie().setEnergiaInicial(Math.abs(energiaAtual - consumoEnergia));
                // Calcular distancia percorrida
                jogadorAtual.setNumeroPosicoesPercorridas(Math.abs(nrSquares));
                // Verficar qual o alimento consumido
                String alimentoConsumido = verificarConsumoDeAlimento(novaPosicaoJogador);
                if (alimentoConsumido != null) {
                    jogadorAtual.contarNumAlimentoApanhado(1);
                    // Atualizar o turno
                    incrementarTurno();
                    return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou " + alimentoConsumido);
                }
                // Atualizar o turno
                incrementarTurno();
                return new MovementResult(MovementResultCode.VALID_MOVEMENT,null);

            } else {
                // Ao estar perto da meta, se A + M é maior que a posição final...
                if (novaPosicaoJogador >= posicaoFinalJogo) {
                    // ...o jogador deve fica na posição final do jogo
                    novaPosicaoJogador = posicaoFinalJogo;
                    // Movimentar o jogador para a casa A + M
                    jogadorAtual.setPosicaoAtual(novaPosicaoJogador);
                    return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
                }
            }
        }
        incrementarTurno();
        return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
    }

    public String[] getWinnerInfo() {
        /*
        Caso o jogo tenha terminado, deve devolver informação do jogador que ganhou o jogo, no
        mesmo formato devolvido pela função getPlayerInfo. Caso o jogo ainda não tenha terminado, deve retornar null.

        — O jogo termina quando for atingida uma das seguintes condições:
            ● Um dos jogadores chega à casa final do jogo. Nesse caso, esse jogador é o vencedor.
            ● A distância entre o jogador mais perto da meta e o segundo jogador mais perto da meta
              é superior à metade do tamanho do mapa. Neste caso, ganha o segundo jogador mais perto da meta.
         */

        // Verificar se algum jogador já chegou à posição final do jogo
        for (Jogador jogador : jogadores) {
            if (jogador.getPosicaoAtual() == posicaoFinalJogo) {
                return getPlayerInfo(jogador.getId()); // Retornar informação do jogador vencedor
            }
        }

        return null; // Nenhum jogador venceu ainda
    }

    public ArrayList<String> getGameResults() {

        ArrayList<String> resultados = new ArrayList<>();

        if (jogadorAtual.getPosicaoAtual() == posicaoFinalJogo) {
            for (Jogador jogador : jogadores) {
                String nome = jogador.getNome();
                String nomeEspecie = jogador.getEspecie().getNome();
                int posicaoDeChegada = jogador.getPosicaoAtual();
                int distancia = jogador.getNumeroPosicoesPercorridas();
                int numAlimento = jogador.getNumeroAlimento();

                resultados.add("#" + (jogadores.indexOf(jogador) + 1) + " " + nome + ", " + nomeEspecie
                        + ", " + posicaoDeChegada + ", " + distancia + ", " + numAlimento);
            }

        } else {

            for (Jogador jogador : jogadores) {
                String nome = jogador.getNome();
                String nomeEspecie = jogador.getEspecie().getNome();
                int posicaoDeChegada = jogador.getPosicaoAtual();
                int distancia = jogador.getNumeroPosicoesPercorridas();
                int numAlimento = jogador.getNumeroAlimento();

                resultados.add("#" + (jogadores.indexOf(jogador) + 1) + " " + nome + ", " + nomeEspecie
                        + ", " + posicaoDeChegada + ", " + distancia + ", " + numAlimento);
            }
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

        return false;
    }

    public boolean loadGame(File file) {
        return false;
    }

    public String verificarConsumoDeAlimento(int posicao) {
        for (Alimento alimento : alimentos) {
            if (alimento.getPosicaoAlimento() == posicao) {
                String idAlimento = alimento.getId();
                String tipoAlimentacao = jogadorAtual.getEspecie().getTipoAlimentacaoDaEspecie();

                // Verificar se o jogador é um unicórnio
                if (jogadorAtual.getEspecie().getId().equals("U")) {
                    // Se o jogador é um unicórnio e o alimento é água, ignorar o efeito da água
                    if (idAlimento.equals("a")) {
                        return null;
                    }
                }

                switch (idAlimento) {
                    case "e" -> { // ERVA
                        if (tipoAlimentacao.equals("herbívoro") || tipoAlimentacao.equals("omnívoro")) {
                            jogadorAtual.consumirErva(tipoAlimentacao, jogadorAtual, alimento);
                            return alimento.getNome();
                        } else if (tipoAlimentacao.equals("carnívoro")) {
                            jogadorAtual.consumirErva(tipoAlimentacao, jogadorAtual, alimento);
                            return alimento.getNome();
                        }
                    }
                    case "a" -> { // ÁGUA
                        if (tipoAlimentacao.equals("carnívoro") || tipoAlimentacao.equals("herbívoro")) {
                            jogadorAtual.consumirAgua(tipoAlimentacao, jogadorAtual, alimento);
                            return alimento.getNome();
                        } else if (tipoAlimentacao.equals("omnívoro")) {
                            jogadorAtual.consumirAgua(tipoAlimentacao, jogadorAtual, alimento);
                            return alimento.getNome();
                        }
                    }
                    case "b" -> { // BANANA
                        jogadorAtual.consumirBanana(tipoAlimentacao, jogadorAtual, alimento);
                        return alimento.getNome();
                    }
                    case "c" -> { // CARNE
                        if (tipoAlimentacao.equals("carnívoro") || tipoAlimentacao.equals("omnívoro")) {
                            jogadorAtual.consumirCarne(tipoAlimentacao, jogadorAtual, turnoAtual, alimento);
                            return alimento.getNome();
                        }
                    }
                    case "m" -> { // COGUMELO MÁGICO
                        jogadorAtual.consumirCogumeloMagico(tipoAlimentacao, jogadorAtual, turnoAtual, alimento);
                        return alimento.getNome();
                    }
                }
            }
        }
        return null;
    }

    public boolean validarVelocidadeEspecie(int velocidade) {
        String especieID = jogadorAtual.getEspecie().getId();

        return switch (especieID) { // Elefante
            case "E", "Z" -> // Tarzan
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

    private void verificarSeRecuouFicouAvancou(int nrSquares, int energiaAtual, int consumoEnergia, int ganhoEnergia) {
        String especieID = jogadorAtual.getEspecie().getId();

        switch (especieID) {
            case "E":
            case "L":
            case "T":
            case "P":
            case "Z":
            case "U":
                if (nrSquares != 0) {
                    // O jogador avançou ou recou
                    jogadorAtual.getEspecie().setEnergiaInicial(energiaAtual - consumoEnergia);
                    jogadorAvancou = true;
                    jogadorRecuou = true;
                } else {
                    // O jogador ficou no mesmo lugar
                    jogadorAtual.getEspecie().setEnergiaInicial(energiaAtual + ganhoEnergia);
                    jogadorFicou = true;
                }
                break;
            default:
                break;
        }
    }

    public void incrementarTurno() {
        turnoAtual++;

        //System.out.println();
        // System.out.println("> turno atual: " + turnoAtual);
    }

    public void incrementarReset() {

        jogadores = new ArrayList<>(); // reset da lista de jogadores.
        alimentos = new ArrayList<>(); // reset da lista de alimentos
        jogadorAtual = null; // reset do jogadorAtual
        idJogadoresEmJogo = new HashMap<>(); // reset do hashmap dos ‘ids’ dos jogadores no início do jogo

        jogadorRecuou = false;
        jogadorFicou = false;
        jogadorAvancou = false;

        casaPartida = 1; // reset casa partida de todos os jogadores
        turnoAtual = 0; // reset do turno atual do jogo.
        posicaoFinalJogo = 0; // reset posicão final do mapa de jogo
    }
}
