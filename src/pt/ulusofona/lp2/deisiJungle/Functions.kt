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

                // Verificar se o comando é "MOST_TRAVELED" e se tem pelo menos um argumento
                } else if (args.isNotEmpty() && args[0] == "MOST_TRAVELED") {
                    getMostTraveled(manager)

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
fun getPlayerInfo (manager: GameManager, nomeJogador: String) : String {
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
    return jogadoresComIdEspecie.joinToString(",")
}


/**
 * GET MOST_TRAVELED
 */

// Obtém a lista dos jogadores ordenada, de forma decrescente, pela distância percorrida por cada um.
// A distância percorrida é a soma dos movimentos efetuados até ao momento.
// Note-se que recuos também são movimentos positivos (a distância percorrida num recuo é positiva).

// O resultado deve ser uma String com várias linhas em que cada linha tem o seguinte formato:
// NOME_JOGADOR:ID_ESPÉCIE:DISTÂNCIA_PERCORRIDA
// Após estas linhas, deve incluir uma última linha com a soma dessas distâncias neste formato:
// Total:<DISTÂNCIA_PERCORRIDA>

    /*
    Exemplo:
    Duarte:E:27
    Bruno:L:24
    Pedro:Z:17
    Total:68
    Caso hajam empates, a ordem é indiferente.
     */

fun getMostTraveled (manager: GameManager) : String {

    val listaJogadoresEmJogo = mutableListOf<String>()

    for (jogador in manager.jogadores) {
        val nomeJogador = jogador.nome
        val idEspecie = jogador.especie.getId()
        val distanciaPercorrida = jogador.numeroPosicoesPercorridas

        // NOME_JOGADOR:ID_ESPÉCIE:DISTÂNCIA_PERCORRIDA
        listaJogadoresEmJogo.addAll(listOf("$nomeJogador:$idEspecie:$distanciaPercorrida"))
    }

    // Obter a lista dos jogadores ordenada, de forma decrescente, pela distância percorrida por cada um. ([2] -> $distancia)
    listaJogadoresEmJogo.sortWith(compareByDescending { it.split(":")[2].toInt() })

    // A distância percorrida é a soma dos movimentos efetuados até ao momento.
    val totalDistanciaPercorridas = manager.jogadores.sumOf { it.numeroPosicoesPercorridas }
    // Total:<DISTÂNCIA_PERCORRIDA>
    listaJogadoresEmJogo.add("Total:$totalDistanciaPercorridas")

    // Quebra de linha
    return listaJogadoresEmJogo.joinToString("\n")
}


fun TODO(): (GameManager, List<String>) -> String? {
    return { _, _ ->
        // TODO: Implementar alguma coisa aqui
        "Falta implementar alguma coisa aqui."
    }
}




