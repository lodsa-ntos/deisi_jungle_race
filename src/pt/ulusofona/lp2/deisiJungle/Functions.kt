package pt.ulusofona.lp2.deisiJungle

enum class CommandType {
    GET,
    POST
}

fun router(): (CommandType) -> ((GameManager, List<String>) -> String?)? {
    return { commandType ->
        when (commandType) {
            CommandType.GET -> { manager, args ->
                // Verificar se o comando é "PLAYER_INFO" e se tem pelo menos um argumento
                if (args.size >= 2 && args[0] == "PLAYER_INFO") {
                    val nomeJogador = args[1]
                    getPlayerInfo(manager, nomeJogador)

                // Verificar se o comando é "PLAYERS_BY_SPECIE" e se tem pelo menos um argumento
                } else if (args.size >= 2 && args[0] == "PLAYERS_BY_SPECIE") {
                    val idEspecie = args[1]
                    getPlayersBySpecie(manager, idEspecie)

                } else {
                    "Invalid command"
                }

            }
            CommandType.POST -> { _, _ -> null }
        }
    }
}

/**
 * GET PLAYER_INFO
 */

// Obtém informações do jogador cujo nome é igual ao parâmetro.
// As informações devem ser obtidas no seguinte formato:
// <id> | <nome> | <nome_espécie> | <energia> | <posicao>

// Ex: GET PLAYER_INFO Sara
// 3 | Sara | Elefante | 130 | 12
fun getPlayerInfo (manager: GameManager, nomeJogador: String): String {
    for (jogador in manager.jogadores) {
        if (jogador.nome.equals(nomeJogador)) {
            return "${jogador.id} | ${jogador.nome} | ${jogador.especie.nome} | ${jogador.especie.energiaAtual} | ${jogador.posicaoAtual}"
        }
    }
    return "Inexistent player"
}


/**
 * GET PLAYERS_BY_SPECIE
 */
// Obtém a lista dos nomes dos jogadores de uma certa espécie, separados por vírgula.
// GET PLAYERS_BY_SPECIE E Pedro, Bruno

// Caso não exista nenhum jogador associado a essa espécie ou a espécie seja inválida, deve retornar uma String vazia
// A lista deve estar ordenada alfabeticamente de forma decrescente.
// GET PLAYERS_BY_SPECIE <specie_id>
fun getPlayersBySpecie (manager: GameManager,  idEspecie: String) : String {
    //  lista dos nomes dos jogadores de uma certa espécie
    val jogadoresComIdEspecie = mutableListOf<String>()

    for (jogador in manager.jogadores) {
        if (jogador.especie.getId().equals(idEspecie)) {
            jogadoresComIdEspecie.add(jogador.nome)
        }
    }

    // A lista deve estar ordenada alfabeticamente de forma decrescente.
    jogadoresComIdEspecie.sortDescending()

    // Retorna se existirem muitos com o mesmo ID especie, separados por vírgula
    return jogadoresComIdEspecie.joinToString(", ")
}


fun TODO(): (GameManager, List<String>) -> String? {
    return { _, _ ->
        // TODO: Implementar alguma coisa aqui
        "Falta implementar alguma coisa aqui."
    }
}




