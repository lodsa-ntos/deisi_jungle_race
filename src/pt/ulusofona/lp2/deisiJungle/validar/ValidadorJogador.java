package pt.ulusofona.lp2.deisiJungle.validar;

import pt.ulusofona.lp2.deisiJungle.InvalidInitialJungleException;

import java.util.Map;

public class ValidadorJogador {

    public static void validarDimensaoMapa(int jungleSize, int numJogadores) throws InvalidInitialJungleException {
        if (jungleSize < 2 * numJogadores) {
            throw new InvalidInitialJungleException("O mapa tem de ter, pelo menos, duas posições por cada jogador.", true, false);
        }
    }

    public static void validarNumJogadorEmJogo(int numJogadorEmJogo) throws InvalidInitialJungleException {
        if (numJogadorEmJogo < 2 || numJogadorEmJogo > 4) {
            throw new InvalidInitialJungleException("Número de jogadores inválido.", true, false);
        }
    }

    public static void validarIDJogadores(int idJogador) throws InvalidInitialJungleException {
        String newID = String.valueOf(idJogador);
        boolean isNumericValue = newID.matches("-?\\d+(\\.\\d+)?");

        if (!isNumericValue) {
            throw new InvalidInitialJungleException("O ID do jogador não é válido.", true, false);
        }

        if (newID == null || newID.isEmpty()) {
            throw new InvalidInitialJungleException("O ID do jogador não existe.", true, false);
        }
    }

    public static void validarNumeroIDs(Map<Integer, Integer> idJogadoresEmJogo, int idJogador) throws InvalidInitialJungleException {
        Integer countIDJogadores = idJogadoresEmJogo.get(idJogador);

        if (countIDJogadores != null && countIDJogadores > 0) {
            throw new InvalidInitialJungleException("Não podem haver dois jogadores com o mesmo id.", true, false);
        }

        idJogadoresEmJogo.put(idJogador, 1);
    }

    public static void validarNomeJogadores(String nomeJogador) throws InvalidInitialJungleException {
        if (nomeJogador == null || nomeJogador.isEmpty()) {
            throw new InvalidInitialJungleException("Os nomes dos jogadores não podem ser null ou vazios.", true, false);
        }
    }

    public static void validarEspecieTarzan(String playerSpecies) throws InvalidInitialJungleException {
        int countTarzan = 0;

        for (int i = 0; i < playerSpecies.length(); i++) {
            if (playerSpecies.charAt(i) == 'Z') {
                countTarzan++;
            }

            if (countTarzan > 1) {
                throw new InvalidInitialJungleException("Existe mais do que 1 jogador da espécie Tarzan a competir.", true, false);
            }
        }
    }

    public static void validarEspecieJogador(String especieJogador, String[][] especies) throws InvalidInitialJungleException {

        int countIDEspecie = 0;

        for (String[] especie : especies) {
            String obterEspecies = especie[0];

            // Se o ‘id’ da espécie pertence ao que foi retornado pela função getSpecies()
            if (especieJogador.contains(obterEspecies)) {
                // E se contém dentro do playersInfo a especieJogador com retornado pela função getSpecies()
                // o count = 1, contem ‘id’ da espécie
                countIDEspecie++;
                break;
            }
        }

        // Se não tiver nenhum ‘id’ de acordo aos que são retornados pela função getSpecies(),
        if (countIDEspecie == 0) {
            // é lançado uma exception.
            throw new InvalidInitialJungleException("A espécie do jogador não é válida.", true, false);
        }

    }

}

