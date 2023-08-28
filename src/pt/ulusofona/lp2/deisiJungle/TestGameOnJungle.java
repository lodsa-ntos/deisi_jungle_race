package pt.ulusofona.lp2.deisiJungle;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.*;

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

        List<Jogador> jogadores = gameOnJungle.getJogadores();

        // Jogador 1
        Jogador jogador1 = jogadores.get(0);
        jogador1.getEspecie().setEnergiaAtual(76);
        jogador1.alterarPosicaoAtual(6);

        // Jogador 2
        Jogador jogador2 = jogadores.get(1);
        jogador2.getEspecie().setEnergiaAtual(30);
        jogador2.alterarPosicaoAtual(1);

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

        List<Jogador> jogadores = gameOnJungle.getJogadores();

        // Jogador 1
        Jogador jogador1 = jogadores.get(0);
        jogador1.getEspecie().setEnergiaAtual(0);
        jogador1.alterarPosicaoAtual(1);

        // Jogador 2
        Jogador jogador2 = jogadores.get(1);
        jogador2.getEspecie().setEnergiaAtual(76);
        jogador2.alterarPosicaoAtual(6);

        // Jogador 3
        Jogador jogador3 = jogadores.get(2);
        jogador3.getEspecie().setEnergiaAtual(50);
        jogador3.alterarPosicaoAtual(3);

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

        List<Jogador> jogadores = gameOnJungle.getJogadores();

        // Jogador 1
        Jogador jogador1 = jogadores.get(0);
        jogador1.getEspecie().setEnergiaAtual(0);
        jogador1.alterarPosicaoAtual(1);

        // Jogador 2
        Jogador jogador2 = jogadores.get(1);
        jogador2.getEspecie().setEnergiaAtual(0);
        jogador2.alterarPosicaoAtual(4);

        // Jogador 3
        Jogador jogador3 = jogadores.get(2);
        jogador3.getEspecie().setEnergiaAtual(0);
        jogador3.alterarPosicaoAtual(3);

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

        List<Jogador> jogadores = gameOnJungle.getJogadores();

        // Jogador 1
        Jogador jogador1 = jogadores.get(0);
        jogador1.getEspecie().setEnergiaAtual(0);
        jogador1.alterarPosicaoAtual(1);

        // Jogador 2
        Jogador jogador2 = jogadores.get(1);
        jogador2.getEspecie().setEnergiaAtual(0);
        jogador2.alterarPosicaoAtual(4);

        // Jogador 3
        Jogador jogador3 = jogadores.get(2);
        jogador3.getEspecie().setEnergiaAtual(0);
        jogador3.alterarPosicaoAtual(3);

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

        List<Jogador> jogadores = gameOnJungle.getJogadores();

        // Jogador 1
        Jogador jogador1 = jogadores.get(0);
        jogador1.getEspecie().setEnergiaAtual(0);
        jogador1.alterarPosicaoAtual(1);

        // Jogador 2
        Jogador jogador2 = jogadores.get(1);
        jogador2.getEspecie().setEnergiaAtual(0);
        jogador2.alterarPosicaoAtual(4);

        // Jogador 3
        Jogador jogador3 = jogadores.get(2);
        jogador3.getEspecie().setEnergiaAtual(14);
        jogador3.alterarPosicaoAtual(3);

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

        List<Jogador> jogadores = gameOnJungle.getJogadores();

        // Jogador 1
        Jogador jogador1 = jogadores.get(0);
        jogador1.getEspecie().setEnergiaAtual(74);
        jogador1.alterarPosicaoAtual(1);

        // Jogador 2
        Jogador jogador2 = jogadores.get(1);
        jogador2.getEspecie().setEnergiaAtual(80);
        jogador2.alterarPosicaoAtual(1);

        // Fazer um movimento com o jogador Leão
        MovementResult resultPedro = gameOnJungle.moveCurrentPlayer(4, false);
        assertEquals(MovementResultCode.CAUGHT_FOOD, resultPedro.code());

        // Fazer um movimento com o jogador Mowgli
        MovementResult resultSara = gameOnJungle.moveCurrentPlayer(5, false);
        assertEquals(MovementResultCode.CAUGHT_FOOD, resultSara.code());

        // Verificar se a energia de Leão está correta
        int energiaEsperadaJogadorLeao1 = 116;
        int energiaAtual = jogador1.getEspecie().getEnergiaAtual();
        int energiaEsperadaJogadorLeao2 = 120;
        int energiaAtual2 = jogador2.getEspecie().getEnergiaAtual();

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

    @Test
    public void testConsumoCarne() throws InvalidInitialJungleException {
        /*
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
        Alimento carne = gameOnJungle.alimentos.get(0);
        //carne.setNumroJogadasCarne(gameOnJungle.turnoAtual);
        String mensagem = carne.toolTip();
        assertEquals("Carne : + 50 energia : 0 jogadas", mensagem);

        // Verificar se a energia foi atualizada corretamente
        infoEnergia = gameOnJungle.getCurrentPlayerEnergyInfo(24);
        assertEquals("[96, 50]", Arrays.toString(infoEnergia));
         */
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
        Alimento carne = gameOnJungle.getAlimentos().get(0);
        //carne.setNumroJogadasCarne(gameOnJungle.turnoAtual);
        String mensagem = carne.toolTip();
        assertEquals("Carne : + 50 energia : 1 jogadas", mensagem);

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

        String expectedToolTip = "Carne : + 50 energia : 2 jogadas";
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

        List<Jogador> jogadores = gameOnJungle.getJogadores();

        // Jogador 1
        Jogador jogador1 = jogadores.get(0);
        jogador1.getEspecie().setEnergiaAtual(50);
        jogador1.alterarPosicaoAtual(1);

        // Jogador 2
        Jogador jogador2 = jogadores.get(1);
        jogador2.getEspecie().setEnergiaAtual(70);
        jogador2.alterarPosicaoAtual(3);

        // Jogador herbívoro calha na carne
        // Tentar fazer um movimento com o jogador com espécie "herbívoro" para cima da carne
        MovementResult resultRanjan = gameOnJungle.moveCurrentPlayer(6, false);
        assertEquals(MovementResultCode.VALID_MOVEMENT, resultRanjan.code());
    }

    @Test
    public void testConsumir1CachoBanana() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimento (banana)
        foodInfo[0][0] = "b";
        foodInfo[0][1] = "3";

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "E";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Jogador> jogadores = gameOnJungle.getJogadores();

        // Jogador 1
        Jogador jogador1 = jogadores.get(0);
        jogador1.getEspecie().setEnergiaAtual(100);
        jogador1.alterarPosicaoAtual(1);

        // Jogador 2
        Jogador jogador2 = jogadores.get(1);
        jogador2.getEspecie().setEnergiaAtual(200);
        jogador2.alterarPosicaoAtual(1);

        Alimento alimento = gameOnJungle.getAlimentos().get(0);

        // HashMap para ficar a saber os jogadores que consumiram bananas
        Map<Integer, Integer> jogadoresQueConsumiramBanana = new HashMap<>();

        // Consumir banana pela priemeira vez
        int energiaConsumida = alimento.consumir("herbívoro", jogador1, gameOnJungle.getAlimentos().get(0), jogadoresQueConsumiramBanana);
        assertEquals(40, energiaConsumida);

        // Consumir banana pela priemeira vez
        energiaConsumida = alimento.consumir("carnívoro", jogador2, gameOnJungle.getAlimentos().get(0), jogadoresQueConsumiramBanana);
        assertEquals(40, energiaConsumida);

        // Consumir banana pela segunda vez
        energiaConsumida = alimento.consumir("herbívoro", jogador1, gameOnJungle.getAlimentos().get(0), jogadoresQueConsumiramBanana);
        assertEquals(-40, energiaConsumida);

        // Consumir quando o cacho estiver vazio
        energiaConsumida = alimento.consumir("herbívoro", jogador1, gameOnJungle.getAlimentos().get(0), jogadoresQueConsumiramBanana);
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

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "E";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";

        // Jogador 3
        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Lod";
        playerInfo[2][2] = "T";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Jogador> jogadores = gameOnJungle.getJogadores();

        // Jogador 1
        Jogador jogador1 = jogadores.get(0);
        jogador1.getEspecie().setEnergiaAtual(20);

        // Jogador 2
        Jogador jogador2 = jogadores.get(1);
        jogador2.getEspecie().setEnergiaAtual(30);

        // Jogador 3
        Jogador jogador3 = jogadores.get(2);
        jogador3.getEspecie().setEnergiaAtual(60);

        Alimento alimento = gameOnJungle.getAlimentos().get(0);

        // HashMap para ficar a saber os jogadores que consumiram bananas
        Map<Integer, Integer> jogadoresQueConsumiramBanana = new HashMap<>();

        // Jogador1 consumir banana pela primeira vez
        int energiaConsumida = alimento.consumir("herbívoro", jogador1, gameOnJungle.getAlimentos().get(0), jogadoresQueConsumiramBanana);
        int energiaEsperada1 = jogador1.getEspecie().getEnergiaAtual() + energiaConsumida;
        assertEquals(40, energiaConsumida);
        assertEquals(60, energiaEsperada1);

        // Jogador2 consumir banana pela primeira vez
        energiaConsumida = alimento.consumir("carnívoro", jogador2, gameOnJungle.getAlimentos().get(0), jogadoresQueConsumiramBanana);
        int energiaEsperada2 = jogador2.getEspecie().getEnergiaAtual() + energiaConsumida;
        assertEquals(40, energiaConsumida);
        assertEquals(70, energiaEsperada2);

        // Jogador1 consumir banana pela segunda vez
        energiaConsumida = alimento.consumir("herbívoro", jogador1, gameOnJungle.getAlimentos().get(1), jogadoresQueConsumiramBanana);
        int novaEnergia = energiaEsperada1 + energiaConsumida;
        assertEquals(-40, energiaConsumida);
        assertEquals(20, novaEnergia);

        // Jogador2 consumir banana pela segunda vez
        energiaConsumida = alimento.consumir("carnívoro", jogador2, gameOnJungle.getAlimentos().get(0), jogadoresQueConsumiramBanana);
        novaEnergia = energiaEsperada2 + energiaConsumida;
        assertEquals(-40, energiaConsumida);
        assertEquals(30, novaEnergia);

        // Jogador3 consumir banana pela primeira vez
        energiaConsumida = alimento.consumir("omnívoro", jogador3, gameOnJungle.getAlimentos().get(1), jogadoresQueConsumiramBanana);
        int energiaEsperada3 = jogador3.getEspecie().getEnergiaAtual() + energiaConsumida;
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

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";


        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Jogador> jogadores = gameOnJungle.getJogadores();

        // Jogador 1
        Jogador jogadorUnicornio = jogadores.get(0);
        jogadorUnicornio.getEspecie().setEnergiaAtual(50);
        jogadorUnicornio.alterarPosicaoAtual(1);

        MovementResult result = gameOnJungle.moveCurrentPlayer(3, false);

        // Verificar se o resultado é uma movimentação válida
        assertEquals(MovementResultCode.VALID_MOVEMENT, result.code());

        // Jogador1 ignora alimento
        assertEquals(0, jogadorUnicornio.getNumeroAlimento());
    }

    @Test
    public void testUnicornioIgnoraCogumelo() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (cogumelo)
        foodInfo[0][0] = "m";
        foodInfo[0][1] = "4";

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";


        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Jogador> jogadores = gameOnJungle.getJogadores();

        // Jogador 1
        Jogador jogadorUnicornio = jogadores.get(0);
        jogadorUnicornio.getEspecie().setEnergiaAtual(50);
        jogadorUnicornio.alterarPosicaoAtual(1);

        MovementResult result = gameOnJungle.moveCurrentPlayer(3, false);

        // Verificar se o resultado é uma movimentação válida
        assertEquals(MovementResultCode.VALID_MOVEMENT, result.code());

        // Jogador1 ignora alimento
        assertEquals(0, jogadorUnicornio.getNumeroAlimento());
    }

    @Test
    public void testUnicornioIgnoraAgua() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (água)
        foodInfo[0][0] = "a";
        foodInfo[0][1] = "6";

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";


        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Jogador> jogadores = gameOnJungle.getJogadores();

        // Jogador 1
        Jogador jogadorUnicornio = jogadores.get(0);
        jogadorUnicornio.getEspecie().setEnergiaAtual(50);
        jogadorUnicornio.alterarPosicaoAtual(1);

        MovementResult result = gameOnJungle.moveCurrentPlayer(3, false);

        // Verificar se o resultado é uma movimentação válida
        assertEquals(MovementResultCode.VALID_MOVEMENT, result.code());

        // Jogador1 ignora alimento
        assertEquals(0, jogadorUnicornio.getNumeroAlimento());
    }

    @Test
    public void testUnicornioIgnoraBanana() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (bananas)
        foodInfo[0][0] = "b";
        foodInfo[0][1] = "8";

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";


        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Jogador> jogadores = gameOnJungle.getJogadores();

        // Jogador 1
        Jogador jogadorUnicornio = jogadores.get(0);
        jogadorUnicornio.getEspecie().setEnergiaAtual(50);
        jogadorUnicornio.alterarPosicaoAtual(1);

        MovementResult result = gameOnJungle.moveCurrentPlayer(3, false);

        // Verificar se o resultado é uma movimentação válida
        assertEquals(MovementResultCode.VALID_MOVEMENT, result.code());

        // Jogador1 ignora alimento
        assertEquals(0, jogadorUnicornio.getNumeroAlimento());
    }

    @Test
    public void testUnicornioIgnoraErva() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (erva)
        foodInfo[0][0] = "e";
        foodInfo[0][1] = "10";

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";


        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Jogador> jogadores = gameOnJungle.getJogadores();

        // Jogador 1
        Jogador jogadorUnicornio = jogadores.get(0);
        jogadorUnicornio.getEspecie().setEnergiaAtual(50);
        jogadorUnicornio.alterarPosicaoAtual(1);

        MovementResult result = gameOnJungle.moveCurrentPlayer(3, false);

        // Verificar se o resultado é uma movimentação válida
        assertEquals(MovementResultCode.VALID_MOVEMENT, result.code());

        // Jogador1 ignora alimento
        assertEquals(0, jogadorUnicornio.getNumeroAlimento());
    }

    @Test
    public void testUnicornioIgnoraEnergiaAgua() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (água)
        foodInfo[0][0] = "a";
        foodInfo[0][1] = "6";

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Jogador> jogadores = gameOnJungle.getJogadores();
        List<Alimento> alimentos = gameOnJungle.getAlimentos();

        // Jogador 1
        Jogador jogadorUnicornio = jogadores.get(0);
        jogadorUnicornio.getEspecie().setEnergiaAtual(200);
        Alimento agua = alimentos.get(0);

        jogadorUnicornio.alterarPosicaoAtual(1);

        gameOnJungle.setEnergyOfNumberOfSquare(5, jogadorUnicornio.getEspecie().getEnergiaAtual(), 8, true);

        assertEquals("Jogador Unicórnio deve ignorar a água\"",
                160, jogadorUnicornio.getEspecie().getEnergiaAtual());

        assertEquals("Jogador Unicórnio deve ignorar a água\"",
                "40", gameOnJungle.getCurrentPlayerEnergyInfo(5)[0]);

        // Jogador1 ignora alimento, não atualiza o historico
        assertEquals(0, jogadorUnicornio.getNumeroAlimento());
    }

    @Test
    public void testUnicornioIgnoraEnergiaCogumelo() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (erva)
        foodInfo[0][0] = "e";
        foodInfo[0][1] = "10";

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Jogador> jogadores = gameOnJungle.getJogadores();

        // Jogador 1
        Jogador jogadorUnicornio = jogadores.get(0);
        jogadorUnicornio.getEspecie().setEnergiaAtual(200);

        jogadorUnicornio.alterarPosicaoAtual(1);
        gameOnJungle.setEnergyOfNumberOfSquare(5, jogadorUnicornio.getEspecie().getEnergiaAtual(), 8, false);

        assertEquals("Jogador Unicórnio deve ignorar Cogumelo Mágico",
                162, jogadorUnicornio.getEspecie().getEnergiaAtual());

        // Jogador1 ignora alimento, não atualiza o historico
        assertEquals(0, jogadorUnicornio.getNumeroAlimento());
    }

    @Test
    public void testUnicornio_CasaSemAlimento() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (cogumelo)
        foodInfo[0][0] = "m";
        foodInfo[0][1] = "10";

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Jogador> jogadores = gameOnJungle.getJogadores();

        // Jogador 1
        Jogador jogadorUnicornio = jogadores.get(0);
        jogadorUnicornio.getEspecie().setEnergiaAtual(200);
        jogadorUnicornio.alterarPosicaoAtual(1);

        gameOnJungle.setEnergyOfNumberOfSquare(5, jogadorUnicornio.getEspecie().getEnergiaAtual(), 8, false);

        assertEquals("Jogador Unicórnio deve ignorar a água\"",
                "42", gameOnJungle.getCurrentPlayerEnergyInfo(5)[0]);

        // Jogador1 ignora alimento, não atualiza o historico
        assertEquals(0, jogadorUnicornio.getNumeroAlimento());
    }

    @Test
    public void testUnicornio_CasaComAlimento() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (cogumelo)
        foodInfo[0][0] = "m";
        foodInfo[0][1] = "6";

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(20, playerInfo, foodInfo);

        List<Jogador> jogadores = gameOnJungle.getJogadores();

        // Jogador 1
        Jogador jogadorUnicornio = jogadores.get(0);
        jogadorUnicornio.getEspecie().setEnergiaAtual(200);
        jogadorUnicornio.alterarPosicaoAtual(1);

        gameOnJungle.setEnergyOfNumberOfSquare(5, jogadorUnicornio.getEspecie().getEnergiaAtual(), 8, true);

        assertEquals("Jogador Unicórnio deve ignorar a água\"",
                "40", gameOnJungle.getCurrentPlayerEnergyInfo(5)[0]);

        // Jogador1 ignora alimento, não atualiza o historico
        assertEquals(0, jogadorUnicornio.getNumeroAlimento());
    }

    @Test
    public void testJogadasExemploDoEnunciado() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[1][2];

        // Alimentos (agua)
        foodInfo[0][0] = "a";
        foodInfo[0][1] = "4";

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(10, playerInfo, foodInfo);

        List<Jogador> jogadores = gameOnJungle.getJogadores();

        // Jogador 1
        Jogador jogadorTarzan = jogadores.get(0);
        jogadorTarzan.getEspecie().setEnergiaAtual(70);

        // Jogador 2
        Jogador jogadorLeao = jogadores.get(1);
        jogadorLeao.getEspecie().setEnergiaAtual(80);

        /**
         * Tarzan avança
         */
        // Lança-se os dados e sai 3. O tarzan avança para a casa com a água.
        MovementResult resultTarzanAcao1 = gameOnJungle.moveCurrentPlayer(3, true);

        // Consome 6 unidades com o movimento, ficando com 64 unidades.
        // Mas como calha numa casa com água e é um omnívoro, recupera 20% de energia. Fica, portanto, com 76 unidades de energia.
        assertEquals("O Tarzan deveria ter ficado com 76 unidades de energia.",
                76, jogadorTarzan.getEspecie().getEnergiaAtual());

        // Jogador1 atualiza o historico ao consumir a água
        assertEquals("O historico do Tarzan deveria ter sido atualizado", 1, jogadorTarzan.getNumeroAlimento());

        // Verificar se o resultado é uma movimentação válida
        assertEquals(MovementResultCode.CAUGHT_FOOD, resultTarzanAcao1.code());

        /**
         * Leão avança
         */
        // Lança-se os dados e sai 5. O leão avança para a casa 6.
        MovementResult resultLeaoAcao1 = gameOnJungle.moveCurrentPlayer(5, true);

        // No movimento consome 10 unidades de energia, ficando com 70.
        assertEquals("O leão deveria ter ficado com 70 unidades de energia.",
                70, jogadorLeao.getEspecie().getEnergiaAtual());

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
                115, jogadorTarzan.getEspecie().getEnergiaAtual());

        // Jogador1 atualiza o historico ao consumir a água
        assertEquals("O historico do Tarzan deveria ter sido atualizado", 2, jogadorTarzan.getNumeroAlimento());

        // Verificar se o resultado é uma movimentação válida
        assertEquals(MovementResultCode.CAUGHT_FOOD, resultTarzan.code());


        /**
         * Leão avança
         */
        // Lança-se os dados e sai 4. O leão avança para a casa 10 (fim).
        MovementResult resultLeaoAcao2 = gameOnJungle.moveCurrentPlayer(4, true);

        // No movimento consome 8 unidades de energia, ficando com 62.
        assertEquals("O leão deveria ter ficado com 62 unidades de energia.",
                62, jogadorLeao.getEspecie().getEnergiaAtual());

        // Verificar se o resultado é uma movimentação válida
        assertEquals(MovementResultCode.VALID_MOVEMENT, resultLeaoAcao2.code());

        // Termina o jogo, o leão é vencedor.
        String[] infoJogadorVencedor = gameOnJungle.getWinnerInfo();

        assertNotNull(infoJogadorVencedor);
        assertEquals("[#1 Bagheera, Leão, 10, 9, 0, #2 Mogli, Tarzan, 4, 3, 2]",
                Arrays.toString(gameOnJungle.getGameResults().toArray()));

    }

    @Test
    public void testExisteGrandeDistanciaEntreJogadores4() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];
        String[][] foodInfo = new String[0][0];

        // Jogador 1
        playerInfo[0][0] = "2";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Jogador 2
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

        // Jogador 1
        playerInfo[0][0] = "2";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Jogador 2
        playerInfo[1][0] = "4";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Jogador 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(7, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Jogador 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(5, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        // Jogador 1 joga
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

        // Jogador 1
        playerInfo[0][0] = "2";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Jogador 2
        playerInfo[1][0] = "4";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "E";

        // Jogador 3
        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Akela";
        playerInfo[2][2] = "U";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Jogador 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Jogador 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(6, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        // Jogador 3 joga
        MovementResult movementResult3 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult3.code());

        String[] infoJogadorVencedor = gameOnJungle.getWinnerInfo();

        assertNotNull(infoJogadorVencedor);
        assertEquals("A vitória deveria ser do jogador Bagheera","4", gameOnJungle.getWinnerInfo()[0]);

    }

    @Test
    public void testGetWinnerInfo_NovaCondicaoVencedor2() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[3][3];

        // Jogador 1
        playerInfo[0][0] = "2";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Jogador 2
        playerInfo[1][0] = "4";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "P";

        // Jogador 3
        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Akela";
        playerInfo[2][2] = "U";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Jogador 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Jogador 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        // Jogador 3 joga
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

        // Jogador 1
        playerInfo[0][0] = "2";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Jogador 2
        playerInfo[1][0] = "4";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "P";

        gameOnJungle.createInitialJungle(11, playerInfo);

        // Jogador 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Jogador 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        String[] infoJogadorVencedor = gameOnJungle.getWinnerInfo();

        assertNull("getWinnerInfo() devia ter dado null porque o jogo ainda não tem vencedor", infoJogadorVencedor);
    }

    @Test
    public void testGetWinnerInfo_IndexOutOfBoundsException() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[] infoJogadorVencedor = gameOnJungle.getWinnerInfo();

        assertNull("getWinnerInfo() devia ter dado null porque não há jogadores", infoJogadorVencedor);
    }

    @Test
    public void testgetGameResults_NovaCondicaoVencedor() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[3][3];

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "P";

        // Jogador 3
        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Akela";
        playerInfo[2][2] = "U";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Jogador 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Jogador 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(6, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        // Jogador 3 joga
        MovementResult movementResult3 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult3.code());

        assertEquals("[#1 Akela, Unicórnio, 5, 4, 0, #2 Mogli, Tarzan, 5, 4, 0, #3 Bagheera, Pássaro, 7, 6, 0]",
                Arrays.toString(gameOnJungle.getGameResults().toArray()));
    }

    @Test
    public void testgetGameResults_NovaCondicaoVencedor2() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Goiaba";
        playerInfo[0][2] = "E";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Pato Donald";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Jogador 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Jogador 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        assertEquals("[#1 Goiaba, Elefante, 5, 4, 0, #2 Pato Donald, Leão, 5, 4, 0]",
                Arrays.toString(gameOnJungle.getGameResults().toArray()));
    }

    @Test
    public void testgetGameResults_IndexOutOfBoundsException() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];

        // Jogador 1
        playerInfo[0][0] = "2";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Jogador 2
        playerInfo[1][0] = "4";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "P";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Jogador 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Jogador 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());


        gameOnJungle.getGameResults().toArray();
    }

    @Test
    public void testSaveAndLoadGame_WithoutFood() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Jogador 2
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

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Jogador 2
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

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Ranjan";
        playerInfo[0][2] = "U";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Balu";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(10, playerInfo, foodInfo);

        Jogador jogadorUnicornio = gameOnJungle.getJogadores().get(0);
        jogadorUnicornio.getEspecie().setEnergiaAtual(200);
        jogadorUnicornio.alterarPosicaoAtual(1);

        int energiaAtual = jogadorUnicornio.getEspecie().getEnergiaAtual();

        gameOnJungle.setEnergyOfNumberOfSquare(4, energiaAtual, 8, true);

        assertEquals("42", gameOnJungle.getCurrentPlayerEnergyInfo(5)[0]);
    }

    @Test
    public void testGetGameResults_NovaCondicaoVencedor4() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[4][3];

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "Z";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "L";

        // Jogador 3
        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Akela";
        playerInfo[2][2] = "T";

        // Jogador 4
        playerInfo[3][0] = "4";
        playerInfo[3][1] = "Baghu";
        playerInfo[3][2] = "E";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Jogador 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Jogador 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        // Jogador 3 joga
        MovementResult movementResult3 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult3.code());

        // Jogador 4 joga
        MovementResult movementResult4 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult4.code());

        assertEquals("[#1 Baghu, Elefante, 5, 4, 0, #2 Akela, Tartaruga, 5, 4, 0, #3 Bagheera, Leão, 5, 4, 0, " +
                        "#4 Mogli, Tarzan, 5, 4, 0]",  Arrays.toString(gameOnJungle.getGameResults().toArray()));
    }


    @Test
    public void testGetGameResults_ClassificacaoNovaCondicao() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "L";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Jogador 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Jogador 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(8, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        assertEquals("[#1 Mogli, Leão, 5, 4, 0, #2 Bagheera, Leão, 9, 8, 0]",  Arrays.toString(gameOnJungle.getGameResults().toArray()));
    }

    @Test
    public void testGetGameResults_ClassificacaoNovaCondicao2() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Mogli";
        playerInfo[0][2] = "L";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Bagheera";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(11, playerInfo);

        // Jogador 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(1, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Jogador 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(2, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        // Jogador 1 joga
        MovementResult movementResult3 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult3.code());

        // Jogador 2 joga
        MovementResult movementResult4 = gameOnJungle.moveCurrentPlayer(6, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult4.code());

        assertEquals("[#1 Mogli, Leão, 6, 5, 0, #2 Bagheera, Leão, 9, 8, 0]", Arrays.toString(gameOnJungle.getGameResults().toArray()));
    }

    @Test
    public void testgetGameResults_NovaCondicaoVencedor3() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[2][3];

        // Jogador 1
        playerInfo[0][0] = "2";
        playerInfo[0][1] = "Bruninho";
        playerInfo[0][2] = "L";

        // Jogador 2
        playerInfo[1][0] = "1";
        playerInfo[1][1] = "Pato Donald";
        playerInfo[1][2] = "L";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Jogador 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Jogador 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(8, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        assertEquals("#2 Bruninho, Leão, 9, 8, 0", gameOnJungle.getGameResults().get(1));

        // Chamada da função para obter o vencedor
        Jogador vencedor = gameOnJungle.verificarJogadorComMaisEnergiaNovaCondicao(gameOnJungle.getJogadores());

        // Verificação
        assertEquals(gameOnJungle.getJogadores().get(0), vencedor);
    }

    @Test
    public void testgetGameResults_NovaLogicaCondicaoVencedor() throws InvalidInitialJungleException {
        GameManager gameOnJungle = new GameManager();

        String[][] playerInfo = new String[3][3];

        // Jogador 1
        playerInfo[0][0] = "1";
        playerInfo[0][1] = "Pato Donald";
        playerInfo[0][2] = "L";

        // Jogador 2
        playerInfo[1][0] = "2";
        playerInfo[1][1] = "Kelly";
        playerInfo[1][2] = "Z";

        // Jogador 3
        playerInfo[2][0] = "3";
        playerInfo[2][1] = "Bruninho";
        playerInfo[2][2] = "L";

        gameOnJungle.createInitialJungle(10, playerInfo);

        // Jogador 1 joga
        MovementResult movementResult1 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult1.code());

        // Jogador 2 joga
        MovementResult movementResult2 = gameOnJungle.moveCurrentPlayer(4, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult2.code());

        // Jogador 3 joga
        MovementResult movementResult3 = gameOnJungle.moveCurrentPlayer(8, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult3.code());

        // Jogador 1 joga e fica na mesma casa
        MovementResult movementResult4 = gameOnJungle.moveCurrentPlayer(0, true);
        assertEquals(MovementResultCode.VALID_MOVEMENT, movementResult4.code());

        assertEquals("#2 Bruninho, Leão, 9, 8, 0", gameOnJungle.getGameResults().get(1));
    }


}
