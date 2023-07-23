package pt.ulusofona.lp2.deisiJungle;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class TestGameOnJungle {

    @Test
    public void testGetSquareInfo_JogadoresNaCasa() throws InvalidInitialJungleException {

        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];

        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Jogador 1";
        playerInfo[0][2] = "L";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Jogador 2";
        playerInfo[1][2] = "E";

        // Adicionar jogadores
        gameOnJungle.createInitialJungle(20, playerInfo);

        // Posicionar os jogadores numa determinada casa
        gameOnJungle.moveCurrentPlayer(1, true); // Mover Jogador 1 para a posição 1 + 1
        gameOnJungle.moveCurrentPlayer(5, true); // Mover Jogador 2 para a posição 1 + 5

        // Obter informações da posição 2
        String[] infoPosicao = gameOnJungle.getSquareInfo(2);

        // Verificar se o ‘id’ do jogador 1 é retornada corretamente
        assertEquals("1", infoPosicao[2]);
    }

    @Test
    public void testGetSquareInfo_JogadoresNaCasa2() throws InvalidInitialJungleException {

        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];

        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Jogador 1";
        playerInfo[0][2] = "L";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Jogador 2";
        playerInfo[1][2] = "E";

        // Adicionar jogadores
        gameOnJungle.createInitialJungle(20, playerInfo);

        // Posicionar os jogadores na mesma casa
        gameOnJungle.moveCurrentPlayer(5, true); // Mover Jogador 1 para a posição 1 + 5
        gameOnJungle.moveCurrentPlayer(5, true); // Mover Jogador 2 para a posição 1 + 5

        // Obter informações da posição 6
        String[] infoPosicao = gameOnJungle.getSquareInfo(6);

        // Verificar se o 'id' dos jogadores é retornado corretamente
        assertEquals("1,2", infoPosicao[2]);
    }

    @Test
    public void testGetSquareInfo_JogadoresNaCasa3() throws InvalidInitialJungleException {

        GameManager gameOnJungle = new GameManager();

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
        gameOnJungle.createInitialJungle(20, playerInfo);

        // Posicionar os jogadores na mesma casa
        gameOnJungle.moveCurrentPlayer(5, true); // Mover Jogador 1 para a posição 1 + 5
        gameOnJungle.moveCurrentPlayer(5, true); // Mover Jogador 2 para a posição 1 + 5
        gameOnJungle.moveCurrentPlayer(8, true); // Mover Jogador 3 para a posição 1 + 8
        gameOnJungle.moveCurrentPlayer(5, true); // Mover Jogador 4 para a posição 1 + 5

        // Obter informações da posição 6
        String[] infoPosicao = gameOnJungle.getSquareInfo(6);

        // Verificar se o 'id' dos jogadores é retornado corretamente
        assertEquals("1,2,4", infoPosicao[2]);
    }

    @Test
    public void testGetPlayersInfo_JogadorID1Existe() throws InvalidInitialJungleException {

        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];

        playerInfo[0][0] = "2";
        playerInfo[0][1] = "Jogador 1";
        playerInfo[0][2] = "L";

        playerInfo[1][0] = "1";
        playerInfo[1][1] = "Jogador 2";
        playerInfo[1][2] = "E";

        // Adicionar jogadores
        gameOnJungle.createInitialJungle(20, playerInfo);

        // Verificar se a função getPlayersInfo() retorna o jogador com ID=1
        String[][] jogadores = gameOnJungle.getPlayersInfo();

        boolean jogadorExiste = false;

        for (String[] jogador : jogadores) {
            // Se contem o jogador com 1
            if (jogador[0].contains("1")) {
                jogadorExiste = true;
                break;
            }

        }

        assertTrue("A função getPlayersInfo() não devolveu o jogador com ID=1", jogadorExiste);
    }

    @Test
    public void testGetWinnerInfo_PrimeiroChegarNaMeta() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Pedro";
        playerInfo[0][2] = "E";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Sara";
        playerInfo[1][2] = "T";

        gameOnJungle.createInitialJungle(6, playerInfo);

        List<Jogador> jogadores = gameOnJungle.jogadores;

        // Jogador 1
        Jogador jogador1 = jogadores.get(0);
        jogador1.getEspecie().setEnergiaInicial(76);
        jogador1.setPosicaoAtual(6);

        // Jogador 2
        Jogador jogador2 = jogadores.get(1);
        jogador2.getEspecie().setEnergiaInicial(30);
        jogador2.setPosicaoAtual(1);

        String[] infoJogadorVencedor = gameOnJungle.getWinnerInfo();

        assertNotNull(infoJogadorVencedor);
        assertEquals("[#1 Pedro, Elefante, 6, 0, 0, #2 Sara, Tartaruga, 1, 0, 0]",
                Arrays.toString(gameOnJungle.getGameResults().toArray()));

    }

    @Test
    public void testGetWinnerInfo_ApenasUmJogadorSemEnergia() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[3][3];
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Pedro";
        playerInfo[0][2] = "E";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Sara";
        playerInfo[1][2] = "T";

        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Joaquin";
        playerInfo[2][2] = "Z";

        gameOnJungle.createInitialJungle(6, playerInfo);

        List<Jogador> jogadores = gameOnJungle.jogadores;

        // Jogador 1
        Jogador jogador1 = jogadores.get(0);
        jogador1.getEspecie().setEnergiaInicial(0);
        jogador1.setPosicaoAtual(1);

        // Jogador 2
        Jogador jogador2 = jogadores.get(1);
        jogador2.getEspecie().setEnergiaInicial(76);
        jogador2.setPosicaoAtual(6);

        // Jogador 3
        Jogador jogador3 = jogadores.get(2);
        jogador3.getEspecie().setEnergiaInicial(50);
        jogador3.setPosicaoAtual(3);

        String[] infoJogadorVencedor = gameOnJungle.getWinnerInfo();

        assertNotNull(infoJogadorVencedor);
        assertEquals("[#1 Sara, Tartaruga, 6, 0, 0, #2 Joaquin, Tarzan, 3, 0, 0, #3 Pedro, Elefante, 1, 0, 0]",
                gameOnJungle.getGameResults().toString());
    }

    @Test
    public void testGetWinnerInfo_NoEnergy_NoLonger_A_Tie() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[3][3];
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Pedro";
        playerInfo[0][2] = "E";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Sara";
        playerInfo[1][2] = "T";

        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Joaquin";
        playerInfo[2][2] = "Z";

        gameOnJungle.createInitialJungle(6, playerInfo);

        List<Jogador> jogadores = gameOnJungle.jogadores;

        // Jogador 1
        Jogador jogador1 = jogadores.get(0);
        jogador1.getEspecie().setEnergiaInicial(0);
        jogador1.setPosicaoAtual(1);

        // Jogador 2
        Jogador jogador2 = jogadores.get(1);
        jogador2.getEspecie().setEnergiaInicial(0);
        jogador2.setPosicaoAtual(4);

        // Jogador 3
        Jogador jogador3 = jogadores.get(2);
        jogador3.getEspecie().setEnergiaInicial(0);
        jogador3.setPosicaoAtual(3);

        String[] infoJogadorVencedor = gameOnJungle.getWinnerInfo();

        assertNull(infoJogadorVencedor);
    }

    @Test
    public void testMoveCurrentPlayer_NoEnergy() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[3][3];
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Pedro";
        playerInfo[0][2] = "E";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Sara";
        playerInfo[1][2] = "T";

        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Joaquin";
        playerInfo[2][2] = "Z";

        gameOnJungle.createInitialJungle(12, playerInfo);

        List<Jogador> jogadores = gameOnJungle.jogadores;

        // Jogador 1
        Jogador jogador1 = jogadores.get(0);
        jogador1.getEspecie().setEnergiaInicial(0);
        jogador1.setPosicaoAtual(1);

        // Jogador 2
        Jogador jogador2 = jogadores.get(1);
        jogador2.getEspecie().setEnergiaInicial(0);
        jogador2.setPosicaoAtual(4);

        // Jogador 3
        Jogador jogador3 = jogadores.get(2);
        jogador3.getEspecie().setEnergiaInicial(0);
        jogador3.setPosicaoAtual(3);

        // Tentar mover o jogador para 6 casas à frente, mas ele não tem energia suficiente
        MovementResult resultadoMovimento = gameOnJungle.moveCurrentPlayer(6, false);

        // Verificar se o resultado é NO_ENERGY
        assertEquals(MovementResultCode.NO_ENERGY, resultadoMovimento.code());
    }

    @Test
    public void test_011_NoEnergy_NoLonger_Terminates_Game() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[3][3];
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Pedro";
        playerInfo[0][2] = "E";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Sara";
        playerInfo[1][2] = "T";

        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Joaquin";
        playerInfo[2][2] = "Z";

        gameOnJungle.createInitialJungle(6, playerInfo);

        List<Jogador> jogadores = gameOnJungle.jogadores;

        // Jogador 1
        Jogador jogador1 = jogadores.get(0);
        jogador1.getEspecie().setEnergiaInicial(0);
        jogador1.setPosicaoAtual(1);

        // Jogador 2
        Jogador jogador2 = jogadores.get(1);
        jogador2.getEspecie().setEnergiaInicial(0);
        jogador2.setPosicaoAtual(4);

        // Jogador 3
        Jogador jogador3 = jogadores.get(2);
        jogador3.getEspecie().setEnergiaInicial(14);
        jogador3.setPosicaoAtual(3);

        // Tentar fazer um movimento com o jogador Pedro, que não tem energia suficiente
        MovementResult resultPedro = gameOnJungle.moveCurrentPlayer(2, false);
        assertEquals(MovementResultCode.NO_ENERGY, resultPedro.code());

        // Tentar fazer um movimento com a jogadora Sara, que não tem energia suficiente
        MovementResult resultSara = gameOnJungle.moveCurrentPlayer(1, false);
        assertEquals(MovementResultCode.NO_ENERGY, resultSara.code());

        // Tentar fazer um movimento com o jogador Joaquin, que tem energia suficiente
        MovementResult resultJoaquin = gameOnJungle.moveCurrentPlayer(3, false);
        assertEquals(MovementResultCode.VALID_MOVEMENT, resultJoaquin.code());
    }

    @Test
    public void testConsumo_AlimentacaoDoLeao1() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[2][3];

        // Alimentos
        foodInfo[0][0] = "c";
        foodInfo[0][1] = "5";

        foodInfo[1][0] = "c";
        foodInfo[1][1] = "6";

        // Jogadores
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Leão";
        playerInfo[0][2] = "L";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Mowgli";
        playerInfo[1][2] = "L";

        // Adicionar jogadores
        gameOnJungle.createInitialJungle(10, playerInfo, foodInfo);

        List<Jogador> jogadores = gameOnJungle.jogadores;

        // Jogador 1
        Jogador jogador1 = jogadores.get(0);
        jogador1.getEspecie().setEnergiaInicial(74);
        jogador1.setPosicaoAtual(1);

        // Jogador 2
        Jogador jogador2 = jogadores.get(1);
        jogador2.getEspecie().setEnergiaInicial(80);
        jogador2.setPosicaoAtual(1);

        // Fazer um movimento com o jogador Leão
        MovementResult resultPedro = gameOnJungle.moveCurrentPlayer(4, false);
        assertEquals(MovementResultCode.CAUGHT_FOOD, resultPedro.code());

        // Fazer um movimento com o jogador Mowgli
        MovementResult resultSara = gameOnJungle.moveCurrentPlayer(5, false);
        assertEquals(MovementResultCode.CAUGHT_FOOD, resultSara.code());

        // Verificar se a energia de Leão está correta
        int energiaEsperadaJogadorLeao1 = 116;
        int energiaAtual = jogador1.getEspecie().getEnergiaInicial();
        int energiaEsperadaJogadorLeao2 = 120;
        int energiaAtual2 = jogador2.getEspecie().getEnergiaInicial();

        assertEquals(energiaEsperadaJogadorLeao1, energiaAtual);
        assertEquals(energiaEsperadaJogadorLeao2, energiaAtual2);
    }

    @Test
    public void testConsumoEnergia1() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[2][3];

        // Alimentos
        foodInfo[0][0] = "e";
        foodInfo[0][1] = "5";

        foodInfo[1][0] = "e";
        foodInfo[1][1] = "6";

        // Jogadores
        playerInfo[0][0] = "3";
        playerInfo[0][1] = "Balu";
        playerInfo[0][2] = "Z";

        // Jogador turno atual = 0
        playerInfo[1][0] = "1";
        playerInfo[1][1] = "Mowgli";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        String[] infoEnergia = gameOnJungle.getCurrentPlayerEnergyInfo(20);
        Assert.assertEquals("[40, 10]", Arrays.toString(infoEnergia));

        infoEnergia = gameOnJungle.getCurrentPlayerEnergyInfo(12);
        Assert.assertEquals("[24, 10]", Arrays.toString(infoEnergia));
    }

    @Test
    public void testConsumoEnergia2() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[2][3];

        // Alimentos
        foodInfo[0][0] = "c";
        foodInfo[0][1] = "5";

        foodInfo[1][0] = "c";
        foodInfo[1][1] = "6";

        // Jogadores
        // Jogador turno atual = 0
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "P";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Mowgli";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        String[] infoEnergia = gameOnJungle.getCurrentPlayerEnergyInfo(20);
        Assert.assertEquals("[80, 50]", Arrays.toString(infoEnergia));

        infoEnergia = gameOnJungle.getCurrentPlayerEnergyInfo(14);
        Assert.assertEquals("[56, 50]", Arrays.toString(infoEnergia));

        infoEnergia = gameOnJungle.getCurrentPlayerEnergyInfo(4);
        Assert.assertEquals("[16, 50]", Arrays.toString(infoEnergia));
    }
}
