package pt.ulusofona.lp2.deisiJungle;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class TestGameOnJungle {

    @Test
    public void testGetSquareInfo_JogadoresNaCasa() throws InvalidInitialJungleException {

        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];

        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Player 1";
        playerInfo[0][2] = "L";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Player 2";
        playerInfo[1][2] = "E";

        // Adicionar jogadores
        gameOnJungle.createInitialJungle(20, playerInfo);

        // Posicionar os jogadores numa determinada casa
        gameOnJungle.moveCurrentPlayer(1, true); // Mover Player 1 para a posição 1 + 1
        gameOnJungle.moveCurrentPlayer(5, true); // Mover Player 2 para a posição 1 + 5

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
        playerInfo[0][1] = "Player 1";
        playerInfo[0][2] = "L";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Player 2";
        playerInfo[1][2] = "E";

        // Adicionar jogadores
        gameOnJungle.createInitialJungle(20, playerInfo);

        // Posicionar os jogadores na mesma casa
        gameOnJungle.moveCurrentPlayer(5, true); // Mover Player 1 para a posição 1 + 5
        gameOnJungle.moveCurrentPlayer(5, true); // Mover Player 2 para a posição 1 + 5

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
        playerInfo[0][1] = "Player 1";
        playerInfo[0][2] = "L";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Player 2";
        playerInfo[1][2] = "T";

        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Player 3";
        playerInfo[2][2] = "U";

        playerInfo[3][0] = "4";
        playerInfo[3][1] = "Player 4";
        playerInfo[3][2] = "E";

        // Adicionar jogadores
        gameOnJungle.createInitialJungle(20, playerInfo);

        // Posicionar os jogadores na mesma casa
        gameOnJungle.moveCurrentPlayer(5, true); // Mover Player 1 para a posição 1 + 5
        gameOnJungle.moveCurrentPlayer(5, true); // Mover Player 2 para a posição 1 + 5
        gameOnJungle.moveCurrentPlayer(8, true); // Mover Player 3 para a posição 1 + 8
        gameOnJungle.moveCurrentPlayer(5, true); // Mover Player 4 para a posição 1 + 5

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
        playerInfo[0][1] = "Player 1";
        playerInfo[0][2] = "L";

        playerInfo[1][0] = "1";
        playerInfo[1][1] = "Player 2";
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

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player player1 = jogadores.get(0);
        player1.getEspecie().definirEnergiaAtual(76);
        player1.alterarPosicaoAtual(6);

        // Player 2
        Player player2 = jogadores.get(1);
        player2.getEspecie().definirEnergiaAtual(30);
        player2.alterarPosicaoAtual(1);

        String[] infoJogadorVencedor = gameOnJungle.getWinnerInfo();

        assertNotNull(infoJogadorVencedor);
        assertEquals("[#1 Pedro, Elephant, 6, 0, 0, #2 Sara, Turtle, 1, 0, 0]",
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

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player player1 = jogadores.get(0);
        player1.getEspecie().definirEnergiaAtual(0);
        player1.alterarPosicaoAtual(1);

        // Player 2
        Player player2 = jogadores.get(1);
        player2.getEspecie().definirEnergiaAtual(76);
        player2.alterarPosicaoAtual(6);

        // Player 3
        Player player3 = jogadores.get(2);
        player3.getEspecie().definirEnergiaAtual(50);
        player3.alterarPosicaoAtual(3);

        String[] infoJogadorVencedor = gameOnJungle.getWinnerInfo();

        assertNotNull(infoJogadorVencedor);
        assertEquals("[#1 Sara, Turtle, 6, 0, 0, #2 Joaquin, Tarzan, 3, 0, 0, #3 Pedro, Elephant, 1, 0, 0]",
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

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player player1 = jogadores.get(0);
        player1.getEspecie().definirEnergiaAtual(0);
        player1.alterarPosicaoAtual(1);

        // Player 2
        Player player2 = jogadores.get(1);
        player2.getEspecie().definirEnergiaAtual(0);
        player2.alterarPosicaoAtual(4);

        // Player 3
        Player player3 = jogadores.get(2);
        player3.getEspecie().definirEnergiaAtual(0);
        player3.alterarPosicaoAtual(3);

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

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player player1 = jogadores.get(0);
        player1.getEspecie().definirEnergiaAtual(0);
        player1.alterarPosicaoAtual(1);

        // Player 2
        Player player2 = jogadores.get(1);
        player2.getEspecie().definirEnergiaAtual(0);
        player2.alterarPosicaoAtual(4);

        // Player 3
        Player player3 = jogadores.get(2);
        player3.getEspecie().definirEnergiaAtual(0);
        player3.alterarPosicaoAtual(3);

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

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player player1 = jogadores.get(0);
        player1.getEspecie().definirEnergiaAtual(0);
        player1.alterarPosicaoAtual(1);

        // Player 2
        Player player2 = jogadores.get(1);
        player2.getEspecie().definirEnergiaAtual(0);
        player2.alterarPosicaoAtual(4);

        // Player 3
        Player player3 = jogadores.get(2);
        player3.getEspecie().definirEnergiaAtual(14);
        player3.alterarPosicaoAtual(3);

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

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player player1 = jogadores.get(0);
        player1.getEspecie().definirEnergiaAtual(74);
        player1.alterarPosicaoAtual(1);

        // Player 2
        Player player2 = jogadores.get(1);
        player2.getEspecie().definirEnergiaAtual(80);
        player2.alterarPosicaoAtual(1);

        // Fazer um movimento com o jogador Leão
        MovementResult resultPedro = gameOnJungle.moveCurrentPlayer(4, false);
        assertEquals(MovementResultCode.CAUGHT_FOOD, resultPedro.code());

        // Fazer um movimento com o jogador Mowgli
        MovementResult resultSara = gameOnJungle.moveCurrentPlayer(5, false);
        assertEquals(MovementResultCode.CAUGHT_FOOD, resultSara.code());

        // Verificar se a energia de Leão está correta
        int energiaEsperadaJogadorLeao1 = 116;
        int energiaAtual = player1.getEspecie().getCurrentEnergy();
        int energiaEsperadaJogadorLeao2 = 120;
        int energiaAtual2 = player2.getEspecie().getCurrentEnergy();

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

        // Player turno atual = 0
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
        // Player turno atual = 0
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

    @Test
    public void testConsumoCarne() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[2][2];

        // Alimentos
        foodInfo[0][0] = "c";
        foodInfo[0][1] = "5";

        foodInfo[1][0] = "m";
        foodInfo[1][1] = "6";

        // Jogadores
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "P";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Mowgli";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        String[] infoEnergia = gameOnJungle.getCurrentPlayerEnergyInfo(20);
        Assert.assertEquals("[80, 50]", Arrays.toString(infoEnergia));

        // Movimentar o jogador 4 posições para que o alimento seja consumido
        MovementResult res1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals("CAUGHT_FOOD", res1.code().toString());

        // Consumir a carne
        Food carne = gameOnJungle.getFoods().get(0);
        //carne.setNumroJogadasCarne(gameOnJungle.turnoAtual);
        String mensagem = carne.toolTip();
        assertEquals("Meat : + 50 energia : 1 jogadas", mensagem);

        // Verificar se a energia foi atualizada corretamente
        infoEnergia = gameOnJungle.getCurrentPlayerEnergyInfo(24);
        assertEquals("[96, 50]", Arrays.toString(infoEnergia));
    }

    @Test
    public void testConsumoCarne1() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[2][2];

        // Alimentos
        foodInfo[0][0] = "c";
        foodInfo[0][1] = "5";

        foodInfo[1][0] = "m";
        foodInfo[1][1] = "6";

        // Jogadores
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "P";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Mowgli";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        String[] infoEnergia = gameOnJungle.getCurrentPlayerEnergyInfo(20);
        Assert.assertEquals("[80, 50]", Arrays.toString(infoEnergia));

        MovementResult res2 = gameOnJungle.moveCurrentPlayer(8, true);
        assertEquals("VALID_MOVEMENT", res2.code().toString());

        //gameOnJungle.turnoAtual = 1;

        // Consumir a carne
        Food carne = gameOnJungle.getFoods().get(0);
        //carne.setNumroJogadasCarne(gameOnJungle.turnoAtual);
        String mensagem = carne.toolTip();
        assertEquals("Meat : + 50 energia : 1 jogadas", mensagem);

        // Verificar se a energia foi atualizada corretamente
        infoEnergia = gameOnJungle.getCurrentPlayerEnergyInfo(24);
        assertEquals("[96, 50]", Arrays.toString(infoEnergia));
    }

    @Test
    public void testJogadasCarneCount() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[2][2];

        // Alimentos
        foodInfo[0][0] = "c";
        foodInfo[0][1] = "2";

        foodInfo[1][0] = "m";
        foodInfo[1][1] = "6";

        // Jogadores
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "P";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Mowgli";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        gameOnJungle.moveCurrentPlayer(2, true);
        gameOnJungle.moveCurrentPlayer(-1, true);

        String[] squareInfo = gameOnJungle.getSquareInfo(2);

        String expectedToolTip = "Meat : + 50 energia : 2 jogadas";
        String actualToolTip = squareInfo[1];
        Assert.assertEquals("O número de jogadas da carne não está a ser atualizado corretamente.", expectedToolTip, actualToolTip);
    }

    @Test
    public void testMoveCurrentPlayer_CalhaNaCarneHerbivoro() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[5][2];

        // Alimentos
        foodInfo[0][0] = "e";
        foodInfo[0][1] = "2";

        foodInfo[1][0] = "a";
        foodInfo[1][1] = "5";

        foodInfo[2][0] = "c";
        foodInfo[2][1] = "7";

        foodInfo[3][0] = "m";
        foodInfo[3][1] = "9";

        foodInfo[4][0] = "b";
        foodInfo[4][1] = "12";

        // Jogadores
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "E";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Mowgli";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player player1 = jogadores.get(0);
        player1.getEspecie().definirEnergiaAtual(50);
        player1.alterarPosicaoAtual(1);

        // Player 2
        Player player2 = jogadores.get(1);
        player2.getEspecie().definirEnergiaAtual(70);
        player2.alterarPosicaoAtual(3);

        // Player herbívoro calha na carne
        // Tentar fazer um movimento com o jogador com espécie "herbívoro" para cima da carne
        MovementResult resultRanjan = gameOnJungle.moveCurrentPlayer(6, false);
        assertEquals(MovementResultCode.VALID_MOVEMENT, resultRanjan.code());
    }

    @Test
    public void testConsumir1CachoBanana() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Food (banana)
        foodInfo[0][0] = "b";
        foodInfo[0][1] = "3";

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "E";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player player1 = jogadores.get(0);
        player1.getEspecie().definirEnergiaAtual(100);
        player1.alterarPosicaoAtual(1);

        // Player 2
        Player player2 = jogadores.get(1);
        player2.getEspecie().definirEnergiaAtual(200);
        player2.alterarPosicaoAtual(1);

        Food food = gameOnJungle.getFoods().get(0);

        // HashMap para ficar a saber os jogadores que consumiram bananas
        Map<Integer, Integer> jogadoresQueConsumiramBanana = new HashMap<>();

        // Consumir banana pela priemeira vez
        int energiaConsumida = food.consumir("herbívoro", player1, gameOnJungle.getFoods().get(0), jogadoresQueConsumiramBanana);
        assertEquals(40, energiaConsumida);

        // Consumir banana pela priemeira vez
        energiaConsumida = food.consumir("carnívoro", player2, gameOnJungle.getFoods().get(0), jogadoresQueConsumiramBanana);
        assertEquals(40, energiaConsumida);

        // Consumir banana pela segunda vez
        energiaConsumida = food.consumir("herbívoro", player1, gameOnJungle.getFoods().get(0), jogadoresQueConsumiramBanana);
        assertEquals(-40, energiaConsumida);

        // Consumir quando o cacho estiver vazio
        energiaConsumida = food.consumir("herbívoro", player1, gameOnJungle.getFoods().get(0), jogadoresQueConsumiramBanana);
        assertEquals(0, energiaConsumida);
    }

    @Test
    public void testConsumir2CachoBanana() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[3][3];
        String[][] foodInfo = new String[2][2];

        // Alimentos (bananas)
        foodInfo[0][0] = "b";
        foodInfo[0][1] = "3";

        foodInfo[1][0] = "b";
        foodInfo[1][1] = "5";

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "E";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";

        // Player 3
        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Lod";
        playerInfo[2][2] = "T";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player player1 = jogadores.get(0);
        player1.getEspecie().definirEnergiaAtual(20);

        // Player 2
        Player player2 = jogadores.get(1);
        player2.getEspecie().definirEnergiaAtual(30);

        // Player 3
        Player player3 = jogadores.get(2);
        player3.getEspecie().definirEnergiaAtual(60);

        Food food = gameOnJungle.getFoods().get(0);

        // HashMap para ficar a saber os jogadores que consumiram bananas
        Map<Integer, Integer> jogadoresQueConsumiramBanana = new HashMap<>();

        // Jogador1 consumir banana pela primeira vez
        int energiaConsumida = food.consumir("herbívoro", player1, gameOnJungle.getFoods().get(0), jogadoresQueConsumiramBanana);
        int energiaEsperada1 = player1.getEspecie().getCurrentEnergy() + energiaConsumida;
        assertEquals(40, energiaConsumida);
        assertEquals(60, energiaEsperada1);

        // Jogador2 consumir banana pela primeira vez
        energiaConsumida = food.consumir("carnívoro", player2, gameOnJungle.getFoods().get(0), jogadoresQueConsumiramBanana);
        int energiaEsperada2 = player2.getEspecie().getCurrentEnergy() + energiaConsumida;
        assertEquals(40, energiaConsumida);
        assertEquals(70, energiaEsperada2);

        // Jogador1 consumir banana pela segunda vez
        energiaConsumida = food.consumir("herbívoro", player1, gameOnJungle.getFoods().get(1), jogadoresQueConsumiramBanana);
        int novaEnergia = energiaEsperada1 + energiaConsumida;
        assertEquals(-40, energiaConsumida);
        assertEquals(20, novaEnergia);

        // Jogador2 consumir banana pela segunda vez
        energiaConsumida = food.consumir("carnívoro", player2, gameOnJungle.getFoods().get(0), jogadoresQueConsumiramBanana);
        novaEnergia = energiaEsperada2 + energiaConsumida;
        assertEquals(-40, energiaConsumida);
        assertEquals(30, novaEnergia);

        // Jogador3 consumir banana pela primeira vez
        energiaConsumida = food.consumir("omnívoro", player3, gameOnJungle.getFoods().get(1), jogadoresQueConsumiramBanana);
        int energiaEsperada3 = player3.getEspecie().getCurrentEnergy() + energiaConsumida;
        assertEquals(40, energiaConsumida);
        assertEquals(100, energiaEsperada3);
    }

    @Test
    public void testUnicornioIgnoraCarne() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (carne)
        foodInfo[0][0] = "c";
        foodInfo[0][1] = "4";

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";


        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player playerUnicornio = jogadores.get(0);
        playerUnicornio.getEspecie().definirEnergiaAtual(50);
        playerUnicornio.alterarPosicaoAtual(1);

        MovementResult result = gameOnJungle.moveCurrentPlayer(3, false);

        // Verificar se o resultado é uma movimentação válida
        assertEquals(MovementResultCode.VALID_MOVEMENT, result.code());

        // Jogador1 ignora alimento
        assertEquals(0, playerUnicornio.getNumeroAlimento());
    }

    @Test
    public void testUnicornioIgnoraCogumelo() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (cogumelo)
        foodInfo[0][0] = "m";
        foodInfo[0][1] = "4";

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";


        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player playerUnicornio = jogadores.get(0);
        playerUnicornio.getEspecie().definirEnergiaAtual(50);
        playerUnicornio.alterarPosicaoAtual(1);

        MovementResult result = gameOnJungle.moveCurrentPlayer(3, false);

        // Verificar se o resultado é uma movimentação válida
        assertEquals(MovementResultCode.VALID_MOVEMENT, result.code());

        // Jogador1 ignora alimento
        assertEquals(0, playerUnicornio.getNumeroAlimento());
    }

    @Test
    public void testUnicornioIgnoraAgua() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (água)
        foodInfo[0][0] = "a";
        foodInfo[0][1] = "6";

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";


        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player playerUnicornio = jogadores.get(0);
        playerUnicornio.getEspecie().definirEnergiaAtual(50);
        playerUnicornio.alterarPosicaoAtual(1);

        MovementResult result = gameOnJungle.moveCurrentPlayer(3, false);

        // Verificar se o resultado é uma movimentação válida
        assertEquals(MovementResultCode.VALID_MOVEMENT, result.code());

        // Jogador1 ignora alimento
        assertEquals(0, playerUnicornio.getNumeroAlimento());
    }

    @Test
    public void testUnicornioIgnoraBanana() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (bananas)
        foodInfo[0][0] = "b";
        foodInfo[0][1] = "8";

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";


        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player playerUnicornio = jogadores.get(0);
        playerUnicornio.getEspecie().definirEnergiaAtual(50);
        playerUnicornio.alterarPosicaoAtual(1);

        MovementResult result = gameOnJungle.moveCurrentPlayer(3, false);

        // Verificar se o resultado é uma movimentação válida
        assertEquals(MovementResultCode.VALID_MOVEMENT, result.code());

        // Jogador1 ignora alimento
        assertEquals(0, playerUnicornio.getNumeroAlimento());
    }

    @Test
    public void testUnicornioIgnoraErva() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (erva)
        foodInfo[0][0] = "e";
        foodInfo[0][1] = "10";

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";


        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player playerUnicornio = jogadores.get(0);
        playerUnicornio.getEspecie().definirEnergiaAtual(50);
        playerUnicornio.alterarPosicaoAtual(1);

        MovementResult result = gameOnJungle.moveCurrentPlayer(3, false);

        // Verificar se o resultado é uma movimentação válida
        assertEquals(MovementResultCode.VALID_MOVEMENT, result.code());

        // Jogador1 ignora alimento
        assertEquals(0, playerUnicornio.getNumeroAlimento());
    }

    @Test
    public void testUnicornioIgnoraEnergiaAgua() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (água)
        foodInfo[0][0] = "a";
        foodInfo[0][1] = "6";

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Player> jogadores = gameOnJungle.getPlayers();
        List<Food> foods = gameOnJungle.getFoods();

        // Player 1
        Player playerUnicornio = jogadores.get(0);
        playerUnicornio.getEspecie().definirEnergiaAtual(200);
        Food agua = foods.get(0);

        playerUnicornio.alterarPosicaoAtual(1);

        gameOnJungle.setEnergyOfNumberOfSquare(5, playerUnicornio.getEspecie().getCurrentEnergy(), 8, true);

        assertEquals("Player Unicórnio deve ignorar a água\"",
                160, playerUnicornio.getEspecie().getCurrentEnergy());

        assertEquals("Player Unicórnio deve ignorar a água\"",
                "40", gameOnJungle.getCurrentPlayerEnergyInfo(5)[0]);

        // Jogador1 ignora alimento, não atualiza o historico
        assertEquals(0, playerUnicornio.getNumeroAlimento());
    }

    @Test
    public void testUnicornioIgnoraEnergiaCogumelo() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (erva)
        foodInfo[0][0] = "e";
        foodInfo[0][1] = "10";

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player playerUnicornio = jogadores.get(0);
        playerUnicornio.getEspecie().definirEnergiaAtual(200);

        playerUnicornio.alterarPosicaoAtual(1);
        gameOnJungle.setEnergyOfNumberOfSquare(5, playerUnicornio.getEspecie().getCurrentEnergy(), 8, false);

        assertEquals("Player Unicórnio deve ignorar Cogumelo Mágico",
                162, playerUnicornio.getEspecie().getCurrentEnergy());

        // Jogador1 ignora alimento, não atualiza o historico
        assertEquals(0, playerUnicornio.getNumeroAlimento());
    }

    @Test
    public void testUnicornio_CasaSemAlimento() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (cogumelo)
        foodInfo[0][0] = "m";
        foodInfo[0][1] = "10";

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player playerUnicornio = jogadores.get(0);
        playerUnicornio.getEspecie().definirEnergiaAtual(200);
        playerUnicornio.alterarPosicaoAtual(1);

        gameOnJungle.setEnergyOfNumberOfSquare(5, playerUnicornio.getEspecie().getCurrentEnergy(), 8, false);

        assertEquals("Player Unicórnio deve ignorar a água\"",
                "42", gameOnJungle.getCurrentPlayerEnergyInfo(5)[0]);

        // Jogador1 ignora alimento, não atualiza o historico
        assertEquals(0, playerUnicornio.getNumeroAlimento());
    }

    @Test
    public void testUnicornio_CasaComAlimento() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (cogumelo)
        foodInfo[0][0] = "m";
        foodInfo[0][1] = "6";

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player playerUnicornio = jogadores.get(0);
        playerUnicornio.getEspecie().definirEnergiaAtual(200);
        playerUnicornio.alterarPosicaoAtual(1);

        gameOnJungle.setEnergyOfNumberOfSquare(5, playerUnicornio.getEspecie().getCurrentEnergy(), 8, true);

        assertEquals("Player Unicórnio deve ignorar a água\"",
                "40", gameOnJungle.getCurrentPlayerEnergyInfo(5)[0]);

        // Jogador1 ignora alimento, não atualiza o historico
        assertEquals(0, playerUnicornio.getNumeroAlimento());
    }

    @Test
    public void testJogadasExemploDoEnunciado() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (agua)
        foodInfo[0][0] = "a";
        foodInfo[0][1] = "4";

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(10, playerInfo, foodInfo);

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player playerTarzan = jogadores.get(0);
        playerTarzan.getEspecie().definirEnergiaAtual(70);

        // Player 2
        Player playerLeao = jogadores.get(1);
        playerLeao.getEspecie().definirEnergiaAtual(80);

        /**
         * Tarzan avança
         */
        // Lança-se os dados e sai 3. O tarzan avança para a casa com a água.
        MovementResult resultTarzanAcao1 = gameOnJungle.moveCurrentPlayer(3, true);

        // Consome 6 unidades com o movimento, ficando com 64 unidades.
        // Mas como calha numa casa com água e é um omnívoro, recupera 20% de energia. Fica, portanto, com 76 unidades de energia.
        assertEquals("O Tarzan deveria ter ficado com 76 unidades de energia.",
                76, playerTarzan.getEspecie().getCurrentEnergy());

        // Jogador1 atualiza o historico ao consumir a água
        assertEquals("O historico do Tarzan deveria ter sido atualizado", 1, playerTarzan.getNumeroAlimento());

        // Verificar se o resultado é uma movimentação válida
        assertEquals(MovementResultCode.CAUGHT_FOOD, resultTarzanAcao1.code());

        /**
         * Leão avança
         */
        // Lança-se os dados e sai 5. O leão avança para a casa 6.
        MovementResult resultLeaoAcao1 = gameOnJungle.moveCurrentPlayer(5, true);

        // No movimento consome 10 unidades de energia, ficando com 70.
        assertEquals("O leão deveria ter ficado com 70 unidades de energia.",
                70, playerLeao.getEspecie().getCurrentEnergy());

        // Verificar se o resultado é uma movimentação válida
        assertEquals(MovementResultCode.VALID_MOVEMENT, resultLeaoAcao1.code());


        /**
         * Tarzan fica no mesmo lugar (nrSquare = 0)
         */
        // Lança-se os dados e sai 1.
        MovementResult resultTarzan = gameOnJungle.moveCurrentPlayer(0, true);

        // O tarzan decide ficar no mesmo sítio. (nrSquare = 0)
        // Por descansar, ganha 20 unidades de energia e depois ganha mais 20% por estar numa casa com água.
        // Ou seja, termina com 115 unidades de energia.
        assertEquals("O Tarzan deveria ter ficado com 115 unidades de energia.",
                115, playerTarzan.getEspecie().getCurrentEnergy());

        // Jogador1 atualiza o historico ao consumir a água
        assertEquals("O historico do Tarzan deveria ter sido atualizado", 2, playerTarzan.getNumeroAlimento());

        // Verificar se o resultado é uma movimentação válida
        assertEquals(MovementResultCode.CAUGHT_FOOD, resultTarzan.code());


        /**
         * Leão avança
         */
        // Lança-se os dados e sai 4. O leão avança para a casa 10 (fim).
        MovementResult resultLeaoAcao2 = gameOnJungle.moveCurrentPlayer(4, true);

        // No movimento consome 8 unidades de energia, ficando com 62.
        assertEquals("O leão deveria ter ficado com 62 unidades de energia.",
                62, playerLeao.getEspecie().getCurrentEnergy());

        // Verificar se o resultado é uma movimentação válida
        assertEquals(MovementResultCode.VALID_MOVEMENT, resultLeaoAcao2.code());

        // Termina o jogo, o leão é vencedor.
        String[] infoJogadorVencedor = gameOnJungle.getWinnerInfo();

        assertNotNull(infoJogadorVencedor);
        assertEquals("[#1 Bagheera, Lion, 10, 9, 0, #2 Mogli, Tarzan, 4, 3, 2]",
                Arrays.toString(gameOnJungle.getGameResults().toArray()));

    }

    @Test
    public void testExisteGrandeDistanciaEntreJogadores4() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[0][0];

        // Player 1
        playerInfo[0][0] = "2";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Player 2
        playerInfo[1][0] = "4";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(10, playerInfo, foodInfo);

        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(7, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        String[] infoJogadorVencedor = gameOnJungle.getWinnerInfo();

        assertNotNull(infoJogadorVencedor);
        assertEquals("getWinnerInfo()[0] regra da distância","4", gameOnJungle.getWinnerInfo()[0]);

    }

    @Test
    public void testGetWinnerInfo_VencedorAposAlcancarMeta() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];

        // Player 1
        playerInfo[0][0] = "2";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Player 2
        playerInfo[1][0] = "4";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Player 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(7, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Player 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(5, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        // Player 1 joga
        MovementResult movementResult3 = gameOnJungle.moveCurrentPlayer(10, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult3.code());

        String[] infoJogadorVencedor = gameOnJungle.getWinnerInfo();

        assertNotNull(infoJogadorVencedor);
        assertEquals("A vitória deveria ser do jogador Mogli","2", gameOnJungle.getWinnerInfo()[0]);
    }

    @Test
    public void testGetWinnerInfo_NovaCondicaoVencedor() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[3][3];

        // Player 1
        playerInfo[0][0] = "2";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Player 2
        playerInfo[1][0] = "4";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "E";

        // Player 3
        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Akela";
        playerInfo[2][2] = "U";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Player 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Player 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(6, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        // Player 3 joga
        MovementResult movementResult3 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult3.code());

        String[] infoJogadorVencedor = gameOnJungle.getWinnerInfo();

        assertNotNull(gameOnJungle.getPlayers());
        Player vencedor = gameOnJungle.obterVencedorDaCasaDoMeioNovaCondicao(gameOnJungle.getPlayers());

         assertNotNull(vencedor);

        assertNotNull(infoJogadorVencedor);
        assertEquals("A vitória deveria ser do jogador Bagheera","4", gameOnJungle.getWinnerInfo()[0]);

    }

    @Test
    public void testGetWinnerInfo_NovaCondicaoVencedor2() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[3][3];

        // Player 1
        playerInfo[0][0] = "2";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Player 2
        playerInfo[1][0] = "4";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "P";

        // Player 3
        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Akela";
        playerInfo[2][2] = "U";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Player 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Player 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        // Player 3 joga
        MovementResult movementResult3 = gameOnJungle.moveCurrentPlayer(6, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult3.code());

        String[] infoJogadorVencedor = gameOnJungle.getWinnerInfo();

        assertNotNull(infoJogadorVencedor);
        assertEquals("A vitória deveria ser do jogador Akela","3", gameOnJungle.getWinnerInfo()[0]);
    }

    @Test
    public void testGetWinnerInfo_AindaNaoExisteUmVencedor() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];

        // Player 1
        playerInfo[0][0] = "2";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Player 2
        playerInfo[1][0] = "4";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "P";

        gameOnJungle.createInitialJungle(11, playerInfo);

        // Player 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Player 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        String[] infoJogadorVencedor = gameOnJungle.getWinnerInfo();

        assertNull("getWinnerInfo() devia ter dado null porque o jogo ainda não tem vencedor", infoJogadorVencedor);
    }

    @Test
    public void testGetWinnerInfo_IndexOutOfBoundsException() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[] infoJogadorVencedor = gameOnJungle.getWinnerInfo();

        assertNotNull(gameOnJungle.getPlayers());

        assertNull("getWinnerInfo() devia ter dado null porque não há jogadores", infoJogadorVencedor);
    }

    @Test
    public void testGetGameResults_NovaCondicaoVencedor() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[3][3];

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "L";

        // Player 3
        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Akela";
        playerInfo[2][2] = "U";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Player 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Player 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(6, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        // Player 3 joga
        MovementResult movementResult3 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult3.code());

        assertEquals("[#1 Akela, Unicórnio, 5, 4, 0, #2 Bagheera, Lion, 7, 6, 0, #3 Mogli, Tarzan, 5, 4, 0]",
                Arrays.toString(gameOnJungle.getGameResults().toArray()));
    }

    @Test
    public void testGetGameResults_NovaCondicaoVencedor2() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Goiaba";
        playerInfo[0][2] = "E";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Pato Donald";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Player 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Player 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        assertEquals("[#1 Goiaba, Elephant, 5, 4, 0, #2 Pato Donald, Lion, 5, 4, 0]",
                Arrays.toString(gameOnJungle.getGameResults().toArray()));
    }

    @Test
    public void testGetGameResults_IndexOutOfBoundsException() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];

        // Player 1
        playerInfo[0][0] = "2";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Player 2
        playerInfo[1][0] = "4";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "P";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Player 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Player 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());


        gameOnJungle.getGameResults();

    }

    @Test
    public void testSaveAndLoadGame_WithoutFood() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Player 2
        playerInfo[1][0] = "4";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "P";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Joga jogador 1
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Joga jogador 2
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(5, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Joga jogador 1
        MovementResult movementResult3 = gameOnJungle.moveCurrentPlayer(2, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult3.code());

        // Guardar o jogo
        File ficheiroTeste = new File("jogoGuardadoTest.txt");

        assertTrue(gameOnJungle.saveGame(ficheiroTeste));
        assertTrue(gameOnJungle.loadGame(ficheiroTeste));

        assertEquals("4", gameOnJungle.getCurrentPlayerInfo()[0]);
    }

    @Test
    public void testSaveAndLoadGame_WithoutFood2() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Player 2
        playerInfo[1][0] = "3";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "P";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Joga jogador 1
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Guardar o jogo
        File ficheiroTeste = new File("jogoGuardadoTest.txt");

        assertTrue(gameOnJungle.saveGame(ficheiroTeste));
        assertTrue(gameOnJungle.loadGame(ficheiroTeste));

        assertEquals("3", gameOnJungle.getCurrentPlayerInfo()[0]);

    }

    @Test
    public void testUnicornio_Move_Eat_OBG() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (cogumelo)
        foodInfo[0][0] = "m";
        foodInfo[0][1] = "5";

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(10, playerInfo, foodInfo);

        Player playerUnicornio = gameOnJungle.getPlayers().get(0);
        playerUnicornio.getEspecie().definirEnergiaAtual(200);
        playerUnicornio.alterarPosicaoAtual(1);

        int energiaAtual = playerUnicornio.getEspecie().getCurrentEnergy();

        gameOnJungle.setEnergyOfNumberOfSquare(4, energiaAtual, 8, true);

        assertEquals("42", gameOnJungle.getCurrentPlayerEnergyInfo(5)[0]);
    }

    @Test
    public void testGetGameResults_NovaCondicaoVencedor4() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[4][3];

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "L";

        // Player 3
        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Akela";
        playerInfo[2][2] = "T";

        // Player 4
        playerInfo[3][0] = "4";
        playerInfo[3][1] = "Baghu";
        playerInfo[3][2] = "E";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Player 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Player 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        // Player 3 joga
        MovementResult movementResult3 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult3.code());

        // Player 4 joga
        MovementResult movementResult4 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult4.code());

        assertEquals("[#1 Baghu, Elephant, 5, 4, 0, #2 Akela, Turtle, 5, 4, 0, #3 Bagheera, Lion, 5, 4, 0, " +
                "#4 Mogli, Tarzan, 5, 4, 0]",  Arrays.toString(gameOnJungle.getGameResults().toArray()));
    }

    @Test
    public void testGetGameResults_NovaLogicaCondicaoVencedor() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[3][3];

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Lod";
        playerInfo[0][2] = "E";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Pato Donald";
        playerInfo[1][2] = "L";

        // Player 3
        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Bruninho";
        playerInfo[2][2] = "L";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Player 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Player 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        // Player 3 joga
        MovementResult movementResult3 = gameOnJungle.moveCurrentPlayer(8, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult3.code());

        String[] infoJogadorVencedor = gameOnJungle.getWinnerInfo();

        assertNotNull(infoJogadorVencedor);
        assertEquals("A vitória deveria ser do jogador Lod","1", gameOnJungle.getWinnerInfo()[0]);

        assertEquals("[#1 Lod, Elephant, 5, 4, 0, #2 Bruninho, Lion, 9, 8, 0, #3 Pato Donald, Lion, 5, 4, 0]",
                Arrays.toString(gameOnJungle.getGameResults().toArray()));
    }

    @Test
    public void testGetWinnerInfo_VencedorNovaCondicao() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Pedro";
        playerInfo[0][2] = "E";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Sara";
        playerInfo[1][2] = "T";

        gameOnJungle.createInitialJungle(10, playerInfo);

        List<Player> jogadores = gameOnJungle.getPlayers();

        // Player 1
        Player player1 = jogadores.get(0);
        player1.getEspecie().definirEnergiaAtual(32);
        player1.alterarPosicaoAtual(5);

        // Player 2
        Player player2 = jogadores.get(1);
        player2.getEspecie().definirEnergiaAtual(76);
        player2.alterarPosicaoAtual(5);

        String[] infoJogadorVencedor = gameOnJungle.getWinnerInfo();

        assertNotNull(infoJogadorVencedor);
        assertEquals("[#1 Sara, Turtle, 5, 0, 0, #2 Pedro, Elephant, 5, 0, 0]",
                Arrays.toString(gameOnJungle.getGameResults().toArray()));

    }

    @Test
    public void testGetGameResults_VitoriaPelaEnergiaCasaDoMeio() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[3][3];

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Pato Donald";
        playerInfo[0][2] = "E";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Bruninho";
        playerInfo[1][2] = "L";

        // Player 3
        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Goiaba";
        playerInfo[2][2] = "P";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Player 1 joga (vencedor)
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Player 2 joga (com menos energia na casa do meio)
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        // Player 3 joga (adiantado)
        MovementResult movementResult3 = gameOnJungle.moveCurrentPlayer(5, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult3.code());

        assertEquals("[#1 Pato Donald, Elephant, 5, 4, 0, #2 Goiaba, Bird, 6, 5, 0, #3 Bruninho, Lion, 5, 4, 0]",
                Arrays.toString(gameOnJungle.getGameResults().toArray()));
    }

    @Test
    public void testGetGameResults_SegundoClassificado() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[3][3];

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Pato Donald";
        playerInfo[0][2] = "E";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Bruninho";
        playerInfo[1][2] = "L";

        // Player 3
        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Goiaba";
        playerInfo[2][2] = "P";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Player 1 joga (vencedor)
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Player 2 joga (com menos energia na casa do meio)
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        // Player 3 joga (adiantado)
        MovementResult movementResult3 = gameOnJungle.moveCurrentPlayer(5, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult3.code());

        assertEquals( "A fn getGameResults está a retornar o 1º classificado incorreto.",
                "#1 Pato Donald, Elephant, 5, 4, 0",
                gameOnJungle.getGameResults().get(0));

        assertEquals( "A fn getGameResults está a retornar o 2º classificado incorreto.",
                "#2 Goiaba, Bird, 6, 5, 0",
                gameOnJungle.getGameResults().get(1));

        assertEquals( "A fn getGameResults está a retornar o 3º classificado incorreto.",
                "#3 Bruninho, Lion, 5, 4, 0",
                gameOnJungle.getGameResults().get(2));

        assertEquals("[#1 Pato Donald, Elephant, 5, 4, 0, #2 Goiaba, Bird, 6, 5, 0, #3 Bruninho, Lion, 5, 4, 0]",
                Arrays.toString(gameOnJungle.getGameResults().toArray()));
    }

    @Test
    public void testGetGameResults_ClassificacaoGeral() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[4][3];

        // Player 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Pato Donald";
        playerInfo[0][2] = "E";

        // Player 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Bruninho";
        playerInfo[1][2] = "L";

        // Player 3
        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Goiaba";
        playerInfo[2][2] = "P";

        // Player 3
        playerInfo[3][0] = "4";
        playerInfo[3][1] = "Akela";
        playerInfo[3][2] = "Z";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Player 1 joga (vencedor)
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Player 2 joga (com menos energia na casa do meio)
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        // Player 3 joga (adiantado)
        MovementResult movementResult3 = gameOnJungle.moveCurrentPlayer(5, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult3.code());

        // Player 4 joga (adiantado)
        MovementResult movementResult4 = gameOnJungle.moveCurrentPlayer(8, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult4.code());

        assertEquals( "A fn getGameResults está a retornar o 1º classificado incorreto.",
                "#1 Pato Donald, Elephant, 5, 4, 0",
                gameOnJungle.getGameResults().get(0));

        assertEquals( "A fn getGameResults está a retornar o 2º classificado incorreto.",
                "#2 Akela, Tarzan, 9, 8, 0",
                gameOnJungle.getGameResults().get(1));

        assertEquals( "A fn getGameResults está a retornar o 3º classificado incorreto.",
                "#3 Goiaba, Bird, 6, 5, 0",
                gameOnJungle.getGameResults().get(2));

        assertEquals( "A fn getGameResults está a retornar o 4º classificado incorreto.",
                "#4 Bruninho, Lion, 5, 4, 0",
                gameOnJungle.getGameResults().get(3));

        assertEquals("[#1 Pato Donald, Elephant, 5, 4, 0, #2 Akela, Tarzan, 9, 8, 0, #3 Goiaba, Bird, 6, 5, 0, #4 Bruninho, Lion, 5, 4, 0]",
                Arrays.toString(gameOnJungle.getGameResults().toArray()));
    }

    @Test
    public void testGirafaIgnoraConsumoDoCogumelo() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Cogumelo Magico
        foodInfo[0][0] = "m";
        foodInfo[0][1] = "3";

        // Jogadores
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Melvin";
        playerInfo[0][2] = "G";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Mowgli";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        String[] infoEnergia = gameOnJungle.getCurrentPlayerEnergyInfo(20);
        Assert.assertEquals("[80, 3]", Arrays.toString(infoEnergia));

        // Movimentar o jogador 4 posições para que o alimento seja consumido
        MovementResult res1 = gameOnJungle.moveCurrentPlayer(2, true);
        assertEquals("VALID_MOVEMENT", res1.code().toString());

        assertEquals(0, gameOnJungle.getPlayers().get(0).getNumeroAlimento());
    }

    @Test
    public void testGirafaAdoraConsumirErva() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[2][2];

        // Cogumelo Magico
        foodInfo[0][0] = "e";
        foodInfo[0][1] = "3";

        // Cogumelo Magico
        foodInfo[1][0] = "e";
        foodInfo[1][1] = "6";

        // Jogadores
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Melvin";
        playerInfo[0][2] = "G";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Mowgli";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        String[] infoEnergia = gameOnJungle.getCurrentPlayerEnergyInfo(20);
        Assert.assertEquals("[80, 3]", Arrays.toString(infoEnergia));

        // Movimentar o jogador 3 posições para que o alimento seja consumido
        MovementResult res1 = gameOnJungle.moveCurrentPlayer(2, true);
        assertEquals("CAUGHT_FOOD", res1.code().toString());

        assertEquals(1, gameOnJungle.getPlayers().get(0).getNumeroAlimento());

        // Verificar se a energia foi atualizada corretamente
        infoEnergia = gameOnJungle.getCurrentPlayerEnergyInfo(3);
        assertEquals("[12, 3]", Arrays.toString(infoEnergia));

        // Movimentar o jogador 9 posições para que o alimento seja consumido
        MovementResult res2 = gameOnJungle.moveCurrentPlayer(8, true);
        assertEquals("VALID_MOVEMENT", res2.code().toString());

        // Movimentar o jogador 4 posições para que o alimento seja consumido
        MovementResult res3 = gameOnJungle.moveCurrentPlayer(3, true);
        assertEquals("CAUGHT_FOOD", res3.code().toString());

        assertEquals(200, gameOnJungle.getPlayers().get(0).getEspecie().getCurrentEnergy());

        // Movimentar o jogador 7 posições para que o alimento seja consumido
        MovementResult res4 = gameOnJungle.moveCurrentPlayer(6, true);
        assertEquals("VALID_MOVEMENT", res4.code().toString());

        // Movimentar o jogador para trás (recuar)
        MovementResult res5 = gameOnJungle.moveCurrentPlayer(-2, true);
        assertEquals("VALID_MOVEMENT", res5.code().toString());

        assertEquals(192, gameOnJungle.getPlayers().get(0).getEspecie().getCurrentEnergy());
    }

    @Test
    public void testGetPlayerIds_And_GetPlayerInfo() throws InvalidInitialJungleException {

        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[2][2];

        // Cogumelo Magico
        foodInfo[0][0] = "a";
        foodInfo[0][1] = "3";

        // Cogumelo Magico
        foodInfo[1][0] = "b";
        foodInfo[1][1] = "6";

        // Jogadores
        playerInfo[0][0] = "4";
        playerInfo[0][1] = "Melvin";
        playerInfo[0][2] = "G";

        playerInfo[1][0] = "3";
        playerInfo[1][1] = "Mowgli";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(15, playerInfo, foodInfo);

        assertEquals("[3, 4]", Arrays.toString(gameOnJungle.getPlayerIds(1)));

        MovementResult res1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals("VALID_MOVEMENT", res1.code().toString());

        assertEquals("[4]", Arrays.toString(gameOnJungle.getPlayerIds(1)));

        assertEquals("[3]", Arrays.toString(gameOnJungle.getPlayerIds(5)));

        MovementResult res2 = gameOnJungle.moveCurrentPlayer(2, true);
        assertEquals("CAUGHT_FOOD", res2.code().toString());

        assertEquals("[4]", Arrays.toString(gameOnJungle.getPlayerIds(3)));

        assertEquals("[3]", Arrays.toString(gameOnJungle.getPlayerIds(5)));

        // Informação dos jogadores em jogo
        assertEquals("[3, Mowgli, L, 72, 4..6]", Arrays.toString(gameOnJungle.getPlayerInfo(3)));
        assertEquals("[4, Melvin, G, 157, 2..3]", Arrays.toString(gameOnJungle.getPlayerInfo(4)));

    }

    @Test
    public void testGetAuthorsPanel() {
        GameManager gameOnJungle = new GameManager();

        JPanel autor = gameOnJungle.getAuthorsPanel();

        assertNotNull(autor);

        JLabel authorLabel = (JLabel) autor.getComponents()[0];

        // Verificar se o texto é "Lodney Santos - a21505293"
        assertEquals("Lodney Santos - a21505293", authorLabel.getText());

        // Verificar se a cor do texto é azul
        assertEquals(Color.BLUE, authorLabel.getForeground());

        // Verificar se a fonte é Arial em negrito com tamanho 18
        Font expectedFont = new Font("Arial", Font.BOLD, 18);
        assertEquals(expectedFont, authorLabel.getFont());
    }

    @Test
    public void testWhoIsTaborda() {
        GameManager gameOnJungle = new GameManager();

        assertEquals("Wrestling", gameOnJungle.whoIsTaborda());
    }

    @Test
    public void testGirafaDetestaCarne() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Cogumelo Magico
        foodInfo[0][0] = "c";
        foodInfo[0][1] = "3";

        // Jogadores
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Melvin";
        playerInfo[0][2] = "G";

        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Mowgli";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        // Giraffe avança para casa com carne
        MovementResult res1 = gameOnJungle.moveCurrentPlayer(2, true);
        assertEquals("VALID_MOVEMENT", res1.code().toString());;
    }

}
