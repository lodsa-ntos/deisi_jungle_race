package pt.ulusofona.lp2.deisiJungle

enum class CommandType {
    GET,
    POST
}

/**
 * router()
 */
fun router(): (CommandType) -> ((GameManager, List<String>) -> String?)? {
    return { commandType ->
        when (commandType) {
            CommandType.GET -> { manager, args ->
                // Check that the command is "PLAYER_INFO" and that it has at least one argument
                when {
                    // Check that the command is "PLAYER_INFO" and that it has at least one argument
                    args.size >= 2 && args[0] == "PLAYER_INFO" -> {
                        val nomeJogador = args[1]
                        getPlayerInfo(manager, nomeJogador)

                    }
                    // Verify that the command is "PLAYERS_BY_SPECIE" and that it has at least one argument
                    args.size >= 2 && args[0] == "PLAYERS_BY_SPECIE" -> {
                        val idEspecie = args[1]
                        getPlayersBySpecie(manager, idEspecie)

                    }
                    // Check that the command is "MOST_TRAVELED" and that it has at least one argument
                    args.isNotEmpty() && args[0] == "MOST_TRAVELED" -> {
                        getMostTraveled(manager)

                    }
                    // Check that the command is "TOP_ENERGETIC_OMNIVORES" and that it has at least one argument
                    args.size >= 2 && args[0] == "TOP_ENERGETIC_OMNIVORES" -> {
                        val maxResults = args[1].toIntOrNull() ?: 0
                        getTopErnergeticOmnivores(manager, maxResults)

                    }
                    // Check that the command is "CONSUMED_FOODS" and that it has at least one argument
                    args.isNotEmpty() && args[0] == "CONSUMED_FOODS" -> {
                        getConsumedFoods(manager)
                    }
                    else -> {
                        "Invalid command"
                    }
                }
            }
            CommandType.POST -> { _, _ -> null }
        }
    }
}

/**
 * GET PLAYER_INFO
 */
fun getPlayerInfo (manager: GameManager, nomeJogador: String) : String {
    for (jogador in manager.players) {
        if (jogador.nome.equals(nomeJogador)) {
            return "${jogador.id} | ${jogador.nome} | ${jogador.especie.name} | ${jogador.especie.currentEnergy} | ${jogador.currentPosition}"
        }
    }
    return "Inexistent player"
}

/**
 * GET PLAYERS_BY_SPECIE
 */
fun getPlayersBySpecie (manager: GameManager,  idEspecie: String) : String {
    //  List of the names of players of a certain species
    val jogadoresComIdEspecie = mutableListOf<String>()

    for (jogador in manager.players) {
        if (jogador.especie.getId().equals(idEspecie)) {
            jogadoresComIdEspecie.add(jogador.nome)
        }
    }

    // The list should be sorted alphabetically in descending order.
    jogadoresComIdEspecie.sortDescending()

    // Returns if there are many with the same species ID, separated by a comma
    return jogadoresComIdEspecie.joinToString(",")
}

/**
 * GET MOST_TRAVELED
 */
fun getMostTraveled (manager: GameManager) : String {

    val listOfPlayersInPlay = mutableListOf<String>()

    for (jogador in manager.players) {
        val nomeJogador = jogador.nome
        val idEspecie = jogador.especie.getId()
        val distanciaPercorrida = jogador.numeroPosicoesPercorridas

        // NOME_JOGADOR:ID_ESPÉCIE:DISTÂNCIA_PERCORRIDA
        listOfPlayersInPlay.addAll(listOf("$nomeJogador:$idEspecie:$distanciaPercorrida"))
    }

    // Get the list of players sorted, in descending order, by the distance traveled by each one. ([2] -> $distancia)
    listOfPlayersInPlay.sortWith(compareByDescending { it.split(":")[2].toInt() })

    // The distance travelled is the sum of the movements made so far.
    val totalDistanciaPercorridas = manager.players.sumOf { it.numeroPosicoesPercorridas }
    // Total:<DISTÂNCIA_PERCORRIDA>
    listOfPlayersInPlay.add("Total:$totalDistanciaPercorridas")

    // Line Break
    return listOfPlayersInPlay.joinToString("\n")
}

/**
 * GET TOP_ENERGETIC_OMNIVORES
 */
fun getTopErnergeticOmnivores (manager: GameManager, max_results: Int) : String {

    val listaJogadoresOmnivorosEmJogos = mutableListOf<Player>()

    /*
    for (jogador in manager.jogadores) {
        if (jogador.especie.getTipoAlimentacaoDaEspecie().equals("omnívoro")) {
            val nomeJogador = jogador.nome
            val energia = jogador.especie.getEnergiaAtual()

            // NOME_JOGADOR:ENERGIA
            listaJogadoresOmnivorosEmJogo.addAll(listOf("$nomeJogador:$energia"))
        }
    }
     */

    for (jogador in manager.players) {
        if (jogador.especie.getTypeFeedSpecies().equals("omnívoro")) {
            listaJogadoresOmnivorosEmJogos.add(jogador)
        }
    }

    // sort in descending order (first the one with the most energy).
    listaJogadoresOmnivorosEmJogos.sortByDescending { jogador ->
        jogador.especie.getCurrentEnergy()
    }

    val topJogadoresOmnivoros = listaJogadoresOmnivorosEmJogos
        .take(max_results) // Limit the number of items in the list...
            // transformar cada jogador no formato "NOME_JOGADOR:ENERGIA".
        .map { jogador -> "${jogador.nome}:${jogador.especie.getCurrentEnergy()}" }

    // Line Break
    return topJogadoresOmnivoros.joinToString("\n")
}

/**
 * GET CONSUMED_FOODS
 */
fun getConsumedFoods(manager: GameManager): String {
    val alimentosConsumidos = manager.foodsConsumed

    // Sort alphabetically and break the line after each word
    return alimentosConsumidos.sorted().joinToString("\n")
}

/*
fun TODO(): (GameManager, List<String>) -> String? {
    return { _, _ ->
        // TODO: Implementar alguma coisa aqui
        "Something needs to be implemented here."
    }
}
 */




