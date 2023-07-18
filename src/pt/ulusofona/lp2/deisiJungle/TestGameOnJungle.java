package pt.ulusofona.lp2.deisiJungle;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestGameOnJungle {

    @Test
    public void testGetSquareInfo_JogadoresNaCasa() throws InvalidInitialJungleException {

        GameManager jogo = new GameManager();

        String[][] playerInfo = new String[2][3];

        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Jogador 1";
        playerInfo[0][2] = "L";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Jogador 2";
        playerInfo[1][2] = "E";

        // Adicionar jogadores
        jogo.createInitialJungle(20, playerInfo);

        // Posicionar os jogadores numa determinada casa
        jogo.moveCurrentPlayer(1, true); // Mover Jogador 1 para a posição 1 + 1
        jogo.moveCurrentPlayer(5, true); // Mover Jogador 2 para a posição 1 + 5

        // Obter informações da posição 2
        String[] infoPosicao = jogo.getSquareInfo(2);

        // Verificar se o ‘id’ do jogador 1 é retornada corretamente
        assertEquals("1", infoPosicao[2]);
    }

    @Test
    public void testGetSquareInfo_JogadoresNaCasa2() throws InvalidInitialJungleException {

        GameManager jogo = new GameManager();

        String[][] playerInfo = new String[2][3];

        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Jogador 1";
        playerInfo[0][2] = "L";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Jogador 2";
        playerInfo[1][2] = "E";

        // Adicionar jogadores
        jogo.createInitialJungle(20, playerInfo);

        // Posicionar os jogadores na mesma casa
        jogo.moveCurrentPlayer(5, true); // Mover Jogador 1 para a posição 1 + 5
        jogo.moveCurrentPlayer(5, true); // Mover Jogador 2 para a posição 1 + 5

        // Obter informações da posição 6
        String[] infoPosicao = jogo.getSquareInfo(6);

        // Verificar se o 'id' dos jogadores é retornado corretamente
        assertEquals("1,2", infoPosicao[2]);
    }

    @Test
    public void testGetSquareInfo_JogadoresNaCasa3() throws InvalidInitialJungleException {

        GameManager jogo = new GameManager();

        String[][] playerInfo = new String[4][3];

        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Jogador 1";
        playerInfo[0][2] = "L";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Jogador 2";
        playerInfo[1][2] = "T";

        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Jogador 3";
        playerInfo[2][2] = "U";

        playerInfo[3][0] = "4";
        playerInfo[3][1] = "Jogador 4";
        playerInfo[3][2] = "E";

        // Adicionar jogadores
        jogo.createInitialJungle(20, playerInfo);

        // Posicionar os jogadores na mesma casa
        jogo.moveCurrentPlayer(5, true); // Mover Jogador 1 para a posição 1 + 5
        jogo.moveCurrentPlayer(5, true); // Mover Jogador 2 para a posição 1 + 5
        jogo.moveCurrentPlayer(8, true); // Mover Jogador 3 para a posição 1 + 8
        jogo.moveCurrentPlayer(5, true); // Mover Jogador 4 para a posição 1 + 5

        // Obter informações da posição 6
        String[] infoPosicao = jogo.getSquareInfo(6);

        // Verificar se o 'id' dos jogadores é retornado corretamente
        assertEquals("1,2,4", infoPosicao[2]);
    }

    @Test
    public void testGetPlayersInfo_JogadorID1Existe() throws InvalidInitialJungleException {

        GameManager jogo = new GameManager();

        String[][] playerInfo = new String[2][3];

        playerInfo[0][0] = "2";
        playerInfo[0][1] = "Jogador 1";
        playerInfo[0][2] = "L";

        playerInfo[1][0] = "1";
        playerInfo[1][1] = "Jogador 2";
        playerInfo[1][2] = "E";

        // Adicionar jogadores
        jogo.createInitialJungle(20, playerInfo);

        // Verificar se a função getPlayersInfo() retorna o jogador com ID=1
        String[][] jogadores = jogo.getPlayersInfo();

        boolean jogadorExiste = false;

        for (String[] jogador: jogadores) {
            // Se contem o jogador com 1
            if (jogador[0].contains("1")) {
                jogadorExiste = true;
                break;
            }

        }

        assertTrue("A função getPlayersInfo() não devolveu o jogador com ID=1", jogadorExiste);
    }

}
