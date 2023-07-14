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

    ArrayList<Jogador> jogadores = new ArrayList<>();
    ArrayList<Alimento> alimentos = new ArrayList<>();
    Jogador jogadorAtual = new Jogador();
    int posicaoFinalJogo;
    int casaPartida = 1;
    int turnoAtual;
    boolean existeVencedor;

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

        HashMap<Integer,Integer> idJogadoresEmJogo = new HashMap<>();
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

            jogadorAtual.caracterizarEspecieJogador(jogadorAtual);
            jogadores.add(jogadorAtual);
            jogadorAtual = jogadores.get(0); // Jogador atual = 1.ª posição da lista de jogadores = ‘id’ = 1
            jogadores.sort(Comparator.comparing(Jogador::getId)); // Ordenar IDs por ordem crescente


            System.out.println("Jogador ⇒ " + jogadorAtual);
            System.out.println(jogadorAtual.getEspecie().toString());
        }

        System.out.println("ALIMENTOS");

        /**
         * ALIMENTOS
         * ‘loop’ foreach para guardar informação do foodsInfo
         */
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
            System.out.println(tipoAlimento.toolTip());
        }
    }

    public void createInitialJungle(int jungleSize, String[][] playersInfo) throws InvalidInitialJungleException {

        HashMap<Integer,Integer> idJogadoresEmJogo = new HashMap<>();
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

            // TODO IDs - é um valor numérico?
            boolean isNumericValue = oldIDJogador.matches("-?\\d+(\\.\\d+)?");

            if (!isNumericValue) {
                throw new InvalidInitialJungleException("O ID do jogador não é válido.", true, false);
            }

            int idJogador = Integer.parseInt(oldIDJogador);

            // TODO IDs — não podem haver dois jogadores com o mesmo id
            ValidadorJogador.validarNumeroIDs(idJogadoresEmJogo, idJogador);

            // TODO O NOMES - não podem ser null ou vazios
            ValidadorJogador.validarNomeJogadores(nomeJogador);

            // TODO TARZAN — Apenas poderá existir um jogador da espécie Tarzan a competir
            ValidadorJogador.validarEspecieTarzan(idEspecieJogador);

            // TODO O ESPÉCIES - A espécie tem que ser uma das que foi retornada da função getSpecies()
            ValidadorJogador.validarEspecieJogador(idEspecieJogador, getSpecies());

            Especie especieJogadorEmJogo = Especie.identificarEspecie(idEspecieJogador);
            jogadorAtual = new Jogador(idJogador, nomeJogador, idEspecieJogador, casaPartida, especieJogadorEmJogo);

            jogadores.add(jogadorAtual);
            jogadorAtual = jogadores.get(0); // Jogador atual = 1.ª posição da lista de jogadores = ‘id’ = 1
            jogadores.sort(Comparator.comparing(Jogador::getId)); // Ordenar IDs por ordem crescente
            jogadorAtual.caracterizarEspecieJogador(jogadorAtual);

            System.out.println(jogadorAtual);
        }
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
        for (int i = 0; i < jogadores.size(); i++) {
            Jogador jogador = jogadores.get(i);
            if (jogador.getPosicaoAtual() == squareNr) {
                infoPosCaixasNoMapa[2] += jogador.getId();
                if (i < jogadores.size() - 1) {
                    infoPosCaixasNoMapa[2] += ",";
                }
            }
        }

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
        Devolve informação do jogador que se
        encontra ativo no turno atual.
        A informação retornada está no mesmo
        formato da função getPlayerInfo().
         */

        String[] InfoJogadorAtual = new String[5];

        for (Jogador jogadorAtualEmJogo: jogadores) {
            InfoJogadorAtual[0] = String.valueOf(jogadorAtualEmJogo.getId());
            InfoJogadorAtual[1] = jogadorAtualEmJogo.getNome();
            InfoJogadorAtual[2] = jogadorAtualEmJogo.getIdEspecie();
            InfoJogadorAtual[3] = String.valueOf(jogadorAtualEmJogo.getEspecie().getEnergiaInicial());
            InfoJogadorAtual[4] = jogadorAtualEmJogo.getEspecie().getVelocidadeMinima() + ".." + jogadorAtualEmJogo.getEspecie().getVelocidadeMaxima();
            return InfoJogadorAtual;
        }
        return null;
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

        int consumoEnergia = jogadorAtual.getEspecie().getConsumoEnergia() * nrPositions;
        int ganhoEnergiaDescanso = jogadorAtual.getEspecie().getGanhoEnergiaDescanso();

        infoEnergia[0] = String.valueOf(consumoEnergia);
        infoEnergia[1] = String.valueOf(ganhoEnergiaDescanso);

        System.out.println(Arrays.toString(infoEnergia));

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

        // null para movimentos inválidos e movimento válido

        // A cada turno alterno o jogador atual de acordo a quantidade dos jogadores em jogo
        // Quando chega a casa A + M alterna o jogador
        jogadorAtual = jogadores.get(turnoAtual % jogadores.size());
        //System.out.println(jogadorAtual);

        int velocidadeMinima = jogadorAtual.getEspecie().getVelocidadeMinima();
        int velocidadeMaxima = jogadorAtual.getEspecie().getVelocidadeMaxima();
        int casaAtual = jogadorAtual.getPosicaoAtual(); // CASA DE PARTIDA = 1
        int energiaAtual = jogadorAtual.getEspecie().getEnergiaInicial();
        int novaPosicaoJogador = casaAtual + nrSquares; // A + M
        //int recuar = casaAtual - nrSquares; // A — M

        // O argumento nrSquares não pode ser menor que 1 ou maior do que 6, porque o dado tem 6 lados.
        // No entanto, se o parâmetro bypassValidations tiver o valor true, a regra anterior não é aplicada.
        // Verificar se o movimento é válido
        if (!bypassValidations) {
            if (nrSquares < -6 || nrSquares > 6) {
                return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
            }

            // Se tentar recuar estando na primeira casa = INVALID_MOVEMENT
            if (nrSquares < 0 && novaPosicaoJogador < 1) {
                return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
            }

            // Se tentar avançar para além da posicao final jogo = INVALID_MOVEMENT
            if (nrSquares < 0 || novaPosicaoJogador > posicaoFinalJogo) {
                return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
            }

            // Se as espécies não se movimentarem conforme as suas velocidades = INVALID_MOVEMENT
            if (nrSquares < velocidadeMinima || nrSquares > velocidadeMaxima) {
                return new MovementResult(MovementResultCode.INVALID_MOVEMENT, null);
            }
        }

        // Se o jogador tentar ultrapassar a acasa final do jogo, deve ficar na posição final do jogo
        if (novaPosicaoJogador >= posicaoFinalJogo) {
            novaPosicaoJogador = posicaoFinalJogo;
            //System.out.println("Vencedor");
        }

        // Movimento do jogador para a casa A + M
        jogadorAtual.setPosicaoAtual(novaPosicaoJogador);

        String alimentoConsumido = verificarConsumoDeAlimento(novaPosicaoJogador);
        if (alimentoConsumido != null) {
            return new MovementResult(MovementResultCode.CAUGHT_FOOD, "Apanhou " + alimentoConsumido);
        }

        // Se não tiver energia suficiente para fazer o movimento, fica na mesma casa
        if (energiaAtual <= 0) {
            return new MovementResult(MovementResultCode.NO_ENERGY, null);
        }

        // Durante o movimento, o jogador consome 2 unidades de energia
        jogadorAtual.getEspecie().setEnergiaInicial(energiaAtual - 2);

        // Atualizar o turno
        incrementarTurno();

        return new MovementResult(MovementResultCode.VALID_MOVEMENT, null);
    }

    public String[] getWinnerInfo() {

        /*
        — O jogo termina quando for atingida uma das seguintes condições:
            ● Um dos jogadores chega à casa final do jogo. Nesse caso, esse jogador é o vencedor.
            ● A distância entre o jogador mais perto da meta e o segundo jogador mais perto da meta
            é superior à metade do tamanho do mapa. Neste caso, ganha o segundo jogador mais perto da meta.
         */

        // Verificar se algum jogador já chegou à posicão final do jogo
        for (Jogador jogadorVencedor : jogadores) {
            if (jogadorVencedor.getPosicaoAtual() == posicaoFinalJogo) {
                return getPlayerInfo(jogadorVencedor.getId());
            }
        }



        return null; // Nenhum jogador venceu ainda
    }

    public ArrayList<String> getGameResults() {
        return new ArrayList<>();
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

    public void incrementarTurno() {
        turnoAtual++;

        System.out.println();
        System.out.println("> turno atual: " + turnoAtual);
    }

    public String verificarConsumoDeAlimento(int posicao) {
        for (Alimento alimento : alimentos) {
            if (alimento.getPosicaoAlimento() == posicao) {

                boolean isHerbivoro = Boolean.parseBoolean(String.valueOf(jogadorAtual.getEspecie().getTipoAlimentacaoDaEspecie().equals("herbívoro")));
                boolean isOmnivoro = Boolean.parseBoolean(String.valueOf(jogadorAtual.getEspecie().getTipoAlimentacaoDaEspecie().equals("omnívoro")));
                boolean isCarnivoro = Boolean.parseBoolean(String.valueOf(jogadorAtual.getEspecie().getTipoAlimentacaoDaEspecie().equals("carnívoro")));
                String idAlimento = alimento.getId();
                String tipoAlimentacao = jogadorAtual.getEspecie().getTipoAlimentacaoDaEspecie();

                switch (idAlimento) {
                    case "e" -> { /** ERVA */
                        if (isHerbivoro || isOmnivoro) {
                            jogadorAtual.consumirErva(tipoAlimentacao, jogadorAtual, alimento);
                            return alimento.getNome();

                        } else if (isCarnivoro) {
                            jogadorAtual.consumirErva(tipoAlimentacao, jogadorAtual, alimento);
                            return alimento.getNome();
                        }
                    }
                    case "a" -> { /** ÁGUA */
                        if (isCarnivoro || isHerbivoro) {
                            // Se ingerido por carnívoros ou herbívoros, aumenta a energia em 15 unidades
                            jogadorAtual.consumirAgua(tipoAlimentacao, jogadorAtual, alimento);
                            return alimento.getNome();

                        } else if (isOmnivoro) {
                            // Se ingerido por omnívoros, aumenta a energia em 20%
                            jogadorAtual.consumirAgua(tipoAlimentacao, jogadorAtual, alimento);
                            return alimento.getNome();
                        }
                    }
                    case "b" -> { /** BANANA */
                        if (isCarnivoro || isHerbivoro || isOmnivoro) {
                            // Se ingerido por carnívoros ou herbívoros, aumenta a energia em 15 unidades
                            jogadorAtual.consumirBanana(tipoAlimentacao, jogadorAtual, alimento);
                        }
                        return alimento.getNome();
                    }
                    case "c" -> { /** CARNE */
                        if (isCarnivoro || isOmnivoro) {
                            // Se ingerido por carnívoros ou herbívoros, aumenta a energia em 15 unidades
                            jogadorAtual.consumirCarne(tipoAlimentacao, jogadorAtual, turnoAtual, alimento);
                            return alimento.getNome();
                        }
                    }
                    case "m" -> { /** COGUMELO MÁGICO */
                        if (isCarnivoro || isHerbivoro || isOmnivoro) {
                            // Se ingerido por carnívoros ou herbívoros, aumenta a energia em 15 unidades
                            jogadorAtual.consumirCogumeloMagico(tipoAlimentacao, jogadorAtual, turnoAtual, alimento);
                        }
                        return alimento.getNome();
                    }
                }
            }
        }
        return null;
    }
}
