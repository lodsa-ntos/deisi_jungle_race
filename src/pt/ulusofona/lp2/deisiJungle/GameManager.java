package pt.ulusofona.lp2.deisiJungle;

import pt.ulusofona.lp2.deisiJungle.Records.MovementResult;
import pt.ulusofona.lp2.deisiJungle.Records.MovementResultCode;
import pt.ulusofona.lp2.deisiJungle.Validar.ValidadorJogador;

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
    int posicaoFinalJogo;
    int casaPartida = 1;
    int turnoAtual = 0;

    public GameManager() {}

    public String[][] getSpecies() {

        /* estrutura:
            ● [0] => id da espécie
            ● [1] => nome da espécie
            ● [2] → nome do ficheiro com a imagem da espécie (ex: “turtle.png”)
        */
        String [][] especies = new String[5][3];

        especies[0][0] = "E";
        especies[0][1] = "Elefante";
        especies[0][2] = "elephant.png";
        especies[1][0] = "L";
        especies[1][1] = "Leão";
        especies[1][2] = "lion.png";
        especies[2][0] = "T";
        especies[2][1] = "Tartaruga";
        especies[2][2] = "turtle.png";
        especies[3][0] = "P";
        especies[3][1] = "Pássaro";
        especies[3][2] = "bird.png";
        especies[4][0] = "Z";
        especies[4][1] = "Tarzan";
        especies[4][2] = "tarzan.png";

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

        // TODO MAPA — duas posições por cada jogador
        ValidadorJogador.validarDimensaoMapa(posicaoFinalJogo, playersInfo.length);

        // TODO JOGADORES — O jogo terá entre 2 e 4 jogadores
        ValidadorJogador.validarNumJogadorEmJogo(playersInfo.length);

        // ‘loop’ foreach para guardar informação do playersInfo
        for (String[] infoJogador: playersInfo) {

            int idJogador = Integer.parseInt(infoJogador[0]);
            String nomeJogador = infoJogador[1];
            String especieJogador = infoJogador[2];

            Jogador jogadorAtual = new Jogador(idJogador, nomeJogador, especieJogador, casaPartida);
            jogadores.add(jogadorAtual);
            System.out.println(jogadorAtual);

            // TODO IDs - é um valor numérico?
            ValidadorJogador.validarIDJogadores(idJogador);

            // TODO IDs - não podem haver dois jogadores com o mesmo id
            ValidadorJogador.validarNumeroIDs(idJogadoresEmJogo, idJogador);

            // TODO NOMES - não podem ser null ou vazios
            ValidadorJogador.validarNomeJogadores(nomeJogador);

            // TODO TARZAN - Apenas poderá existir um jogador da espécie Tarzan a competir
            ValidadorJogador.validarEspecieTarzan(especieJogador);

            // TODO ESPÉCIES - A espécie tem que ser uma das que foi retornada da função getSpecies()
            ValidadorJogador.validarEspecieJogador(especieJogador, getSpecies());
        }

    }

    public void createInitialJungle(int jungleSize, String[][] playersInfo) throws InvalidInitialJungleException {

        HashMap<Integer,Integer> idJogadoresEmJogo = new HashMap<>();
        posicaoFinalJogo = jungleSize;

        // TODO MAPA — duas posições por cada jogador
        ValidadorJogador.validarDimensaoMapa(posicaoFinalJogo, playersInfo.length);

        // TODO JOGADORES — O jogo terá entre 2 e 4 jogadores
        ValidadorJogador.validarNumJogadorEmJogo(playersInfo.length);

        // ‘loop’ foreach para guardar informação do playersInfo
        for (String[] infoJogador: playersInfo) {

            int idJogador = Integer.parseInt(infoJogador[0]);
            String nomeJogador = infoJogador[1];
            String especieJogador = infoJogador[2];

            Jogador jogadorAtual = new Jogador(idJogador, nomeJogador, especieJogador, casaPartida);
            jogadores.add(jogadorAtual);
            System.out.println(jogadorAtual);

            // TODO IDs - é um valor numérico?
            ValidadorJogador.validarIDJogadores(idJogador);

            // TODO IDs - não podem haver dois jogadores com o mesmo id
            ValidadorJogador.validarNumeroIDs(idJogadoresEmJogo, idJogador);

            // TODO NOMES - não podem ser null ou vazios
            ValidadorJogador.validarNomeJogadores(nomeJogador);

            // TODO TARZAN - Apenas poderá existir um jogador da espécie Tarzan a competir
            ValidadorJogador.validarEspecieTarzan(especieJogador);

            // TODO ESPÉCIES - A espécie tem que ser uma das que foi retornada da função getSpecies()
            ValidadorJogador.validarEspecieJogador(especieJogador, getSpecies());
        }

        // System.out.println(Arrays.toString(getPlayerIds(1)));
        //System.out.println(Arrays.toString(getPlayerInfo(2)));
        //System.out.println(Arrays.toString(getCurrentPlayerInfo()));
        //System.out.println(moveCurrentPlayer(1, true));

    }

    public int[] getPlayerIds(int squareNr) {

        ArrayList<Integer> listaIdsJogadores = new ArrayList<>();

        /// RESTRIÇÕES:
        // Retornar um array vazio se o squareNr for inválido;
        // Retornar um array vazio se não houver jogadorers na posição indicada;
        // squareNr — números de quadrados do mapa (posições do mapa)
        if (squareNr < casaPartida || squareNr > posicaoFinalJogo) {
            return new int[0];
        }

        /// REQUISITOS:
        // Obter os ‘ids’ dos jogadores de uma determinada posição do mapa
        for (Jogador emJogo: jogadores) {
            if (emJogo.getPosicaoAtual() == squareNr) {
                listaIdsJogadores.add(emJogo.getId());
            }
        }

        // Retornar um array com ‘ids’ dos jogadores de uma determinada posição do mapa
        int [] idsInJungle = new int[listaIdsJogadores.size()];
        for (int i = 0; i < listaIdsJogadores.size(); i++) {
            // passar os valores da lista para o array
            idsInJungle[i] = listaIdsJogadores.get(i);
        }

        return idsInJungle;
    }

    public String[] getSquareInfo(int squareNr) {

        /// RESTRIÇÕES
        // Caso o nrSquare seja inválido, a função deve retornar null.
        // squareNr — números de quadrados do mapa (posições do mapa)
        if (squareNr < 1 || squareNr > posicaoFinalJogo) {
            return null;
        }

        /*
        Cada elemento do array deve ter a seguinte informação:
            - [0] => Nome do ficheiro com a imagem a colocar nesse posição
            - [1] => Uma descrição textual do que existe nessa posição (nesta fase pode ser apenas “Vazio” ou “Meta”)
            - [2] => Uma String contendo os identificadores dos jogadores que
            estão nessa posição, separados por
            vírgula (ex: “3,5” — estão lá os
            jogadores 3 e 5).
         */

        String[] infoPosCaixasNoMapa = new String[3];

        // [0] => Nome do ficheiro com a imagem a colocar nessa posicao
        // [1] => Uma descrição textual do que existe nessa posição (nesta fase pode ser apenas “Vazio” ou “Meta”)
        // Se squareNr for a última posição a descrição textual será Meta senão será Vazio
        if (squareNr == posicaoFinalJogo) {
            infoPosCaixasNoMapa[0] = "finish.png";
            infoPosCaixasNoMapa[1] = "Meta";
        } else {
            infoPosCaixasNoMapa[0] = "blank.png";
            infoPosCaixasNoMapa[1] = "Vazio";
        }

        /*
        [2] => Uma String contendo os ‘ids’ dos jogadores que estão nessa posição, separados por
            vírgula (ex: “3,5” — estão lá os jogadores 3 e 5).
         */
        //TODO a pensar acerca deste ponto...

        return infoPosCaixasNoMapa;
    }

    public String[] getPlayerInfo(int playerId) {

        /*
        - [0] => O ID do jogador
        - [1] => O Nome do jogador
        - [2] => O ID da espécie associada ao jogador.
        - [3] => A energia atual do jogador, medida em unidades de energia
         */
        String[] infoJogador = new String[4];

        for (Jogador info : jogadores) {
            if (info.getId() == playerId) {
                infoJogador[0] = String.valueOf(info.getId());
                infoJogador[1] = info.getNome();
                infoJogador[2] = String.valueOf(info.getIdEspecie());
                infoJogador[3] = String.valueOf(info.getEnergiaAtual());
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

        String[] jogadorAtual = new String[4];

        for (Jogador jogadorAtualEmJogo: jogadores) {
            if (jogadorAtualEmJogo.getTurnoAtual() == 0) {
                jogadorAtual[0] = String.valueOf(jogadorAtualEmJogo.getId());
                jogadorAtual[1] = jogadorAtualEmJogo.getNome();
                jogadorAtual[2] = jogadorAtualEmJogo.getIdEspecie();
                jogadorAtual[3] = String.valueOf(jogadorAtualEmJogo.getEnergiaAtual());
                return jogadorAtual;
            }
        }
        return null;
    }

    public String[] getCurrentPlayerEnergyInfo(int nrPositions) {

        return new String[0];
    }

    public String[][] getPlayersInfo() {

        /*
        Retorna informação de todos os jogadores,
        no mesmo formato que o retornado pelas
        funções getPlayerInfo e
        getCurrentPlayerInfo.
         */

        String [][] infoGeralJogadores = new String[jogadores.size()][4];

        for (int i = 0; i < jogadores.size(); i++) {
            infoGeralJogadores[i][0] = String.valueOf(jogadores.get(i).getId());
            infoGeralJogadores[i][1] = jogadores.get(i).getNome();
            infoGeralJogadores[i][2] = String.valueOf(jogadores.get(i).getIdEspecie());
            infoGeralJogadores[i][3] = String.valueOf(jogadores.get(i).getEnergiaAtual());
        }
        return infoGeralJogadores;
    }

    public MovementResult moveCurrentPlayer(int nrSquares, boolean bypassValidations) {

        // A cada turno alterno o jogador atual de acordo a quantidade dos jogadores em jogo
        // Quando chega a casa A + M alterna o jogador
        Jogador jogadorAtual = jogadores.get(turnoAtual % jogadores.size());
        System.out.println(jogadorAtual);

        int casaAtual = jogadorAtual.getPosicaoAtual(); // CASA DE PARTIDA = 1
        int novaCasaAtual = casaAtual + nrSquares; // A + M
        int energiaAtual = jogadorAtual.getEnergiaAtual(); // 22

        // O argumento nrSquares não pode ser menor que 1 ou maior do que 6, porque o dado tem 6 lados.
        // No entanto, se o parâmetro bypassValidations tiver o valor true, a regra anterior não é aplicada.
        if (!bypassValidations && (nrSquares < -6 || nrSquares > 6)) {
            return new MovementResult(MovementResultCode.INVALID_MOVEMENT," ");
        }

        // Se o jogador tentar ultrapassar a acasa final do jogo, deve ficar na posição final do jogo
        if (novaCasaAtual > posicaoFinalJogo) {
            novaCasaAtual = posicaoFinalJogo;
            //System.out.println("Vencedor");
        }

        // Se não tiver energia suficiente para fazer o movimento, fica na mesma casa
        if (energiaAtual == 0) {
            return new MovementResult(MovementResultCode.NO_ENERGY," ");
        }

        // Movimento do jogador para a casa A + M
        jogadorAtual.setPosicaoAtual(novaCasaAtual);

        // Durante o movimento, o jogador consome 2 unidades de energia
        jogadorAtual.setEnergiaAtual(energiaAtual - 2);

        incrementarTurno();
        return new MovementResult(MovementResultCode.VALID_MOVEMENT, " ");
    }

    public String[] getWinnerInfo() {
        return null;
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
        return "";
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

}
