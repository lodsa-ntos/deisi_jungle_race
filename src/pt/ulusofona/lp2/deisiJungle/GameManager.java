package pt.ulusofona.lp2.deisiJungle;

import javax.swing.*;
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

    Jogador[] todosJogadores;
    ArrayList<Jogador> jogadores = new ArrayList<>();
    int POSICAO_FINAL_JOGO;
    int CASA_PARTIDA = 1;

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

    public boolean createInitialJungle(int jungleSize, int initialEnergy, String[][] playersInfo) {

        todosJogadores = new Jogador[playersInfo.length];
        POSICAO_FINAL_JOGO = jungleSize;

        HashMap<Integer,Integer> idJogadoresEmJogo = new HashMap<>();
        int countTarzan = 0;

        // TODO POSIÇÃO - duas posições por cada jogador
        if (jungleSize < 2 * playersInfo.length) {
            System.out.println("ERRO.: O mapa tem de ter, pelo menos, duas posições por cada jogador");
            return false;
        }

        // TODO ENERGIA - todos os jogadores começam com energia inicial no maximo
        if (initialEnergy <= 0) {
            System.out.println("ERRO.: Energia inicial inválida.");
            return false;
        }

        // TODO JOGADORES — O jogo terá entre 2 e 4 jogadores
        if (playersInfo.length < 2 || playersInfo.length > 4) {
            System.out.println("ERRO.: Número de jogadores inválido");
            return false;
        }

        // ‘loop’ foreach para Guardar Informação dos Jogadores playersInfo
        for (String[] infoJogador: playersInfo) {

            int idJogador = Integer.parseInt(infoJogador[0]);
            String nomeJogador = infoJogador[1];
            String especieJogador = infoJogador[2];

            Jogador jogadorAtual = new Jogador(idJogador, nomeJogador, especieJogador, CASA_PARTIDA, initialEnergy);
            jogadores.add(jogadorAtual);

            // TODO IDs - não podem haver dois jogadores com o mesmo id
            Integer countIDJogadores = idJogadoresEmJogo.get(idJogador);

            // enquanto não existirem jogadores com o mesmo ID
            if (countIDJogadores == null) {
                // o id verificado fica anotado que só existe 1 vez...
                idJogadoresEmJogo.put(idJogador, 1);
            } else {
                // se não, se houver dois jogadores com o mesmo ID, retorna invalido.
                idJogadoresEmJogo.put(idJogador, ++countIDJogadores);
                return false;
            }

            // TODO NOMES - não podem ser null ou vazios
            if (nomeJogador == null || nomeJogador.isEmpty()) {
                return false;
            }

            // TODO TARZAN
            // Apenas poderá existir um jogador da espécie Tarzan a competir
            for (int i = 0; i < especieJogador.length(); i++) {
                if (especieJogador.charAt(i) == 'Z') {
                    countTarzan++;
                }

                if (countTarzan > 1) {
                    System.out.println();
                    System.out.println("Erro.: Existe mais do que 1 jogador da espécie Tarzan a competir.");
                    System.out.println();
                    return false;
                }
            }

            // TODO ESPÉCIES - A espécie tem que ser uma das que foi retornada da função getSpecies()
            for (int i = 0; i < getSpecies().length; i++) {
                String obterEspecies = getSpecies()[i][0];
                if ((especieJogador.contains(obterEspecies))) {
                    // System.out.println(obterEspecies);
                    //countEspeciesExistente++;
                    break;
                }
            }

        }
        // System.out.println(Arrays.toString(getPlayerIds(1)));
        return true;
    }

    public int[] getPlayerIds(int squareNr) {

        ArrayList<Integer> listaIdsJogadores = new ArrayList<>();

        /// RESTRIÇÕES:
        // Retornar um array vazio se o squareNr for inválido;
        // Retornar um array vazio se não houver jogadorers na posição indicada;
        // squareNr — números de quadrados do mapa (posições do mapa)
        if (squareNr < CASA_PARTIDA || squareNr > POSICAO_FINAL_JOGO) {
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
        if (squareNr < 1 || squareNr >  POSICAO_FINAL_JOGO) {
            return null;
        }

        /*
        Cada elemento do array deve ter a seguinte informação:
            - [0] => Nome do ficheiro com a imagem a colocar nesse posição
            - [1] => Uma descrição textual do que existe nessa posição (nesta fasebpode ser apenas “Vazio” ou “Meta”)
            - [2] => Uma String contendo os identificadores dos jogadores que
            estão nessa posição, separados por
            vírgula (ex: “3,5” — estão lá os
            jogadores 3 e 5).
         */

        String[] posicaoInfo = new String[3];


        return new String[0];
    }

    public String[] getPlayerInfo(int playerId) {

        return new String[0];
    }

    public String[] getCurrentPlayerInfo() {


        return new String[0];
    }

    public String[][] getPlayersInfo() {

        return new String[0][0];
    }

    public boolean moveCurrentPlayer(int nrSquares, boolean bypassValidations) {

        return true;
    }

    public String[] getWinnerInfo() {
        return new String[0];
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

}
