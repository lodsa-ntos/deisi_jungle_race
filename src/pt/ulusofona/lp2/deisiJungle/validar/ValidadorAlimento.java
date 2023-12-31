package pt.ulusofona.lp2.deisiJungle.validar;

import pt.ulusofona.lp2.deisiJungle.InvalidInitialJungleException;

public class ValidadorAlimento {

    public static void validarIDAlimento(String idTipo, String[][] foodsInfo) throws InvalidInitialJungleException {

        int countIDAlimento = 0;

        for (String[] food : foodsInfo) {
            String foodId = food[0];

            // Se o ‘id’ pertence ao que foi retornado pela função getFoodTypes()
            if (idTipo.contains(foodId)) {
                // E se contém dentro do foodsInfo o idTipo do alimento como o retornado pela função getFoodTypes()
                // o countIDAlimento = 1, contem ‘id’ no foodsInfo
                countIDAlimento++;
                break;
            }
        }

        // Se não tiver nenhum ‘id’ de acordo aos que são retornados pela função getFoodTypes(),
        if (countIDAlimento == 0) {
            // é lançado uma exception.
            throw new InvalidInitialJungleException("O id do alimento não é válido.", false, true);
        }

    }

    public static void validarPosicaoAlimentos(int posicaoAlimento, int posicaoInicialMapa, int posicaoFinalMapa) throws InvalidInitialJungleException {
        if (posicaoAlimento <= posicaoInicialMapa || posicaoAlimento >= posicaoFinalMapa) {
            throw new InvalidInitialJungleException("Os alimentos têm que estar posicionados dentro dos limites do terreno.", false, true);
        }
    }

}
