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
                when {
                    // Verificar se o comando é "PLAYER_INFO" e se tem pelo menos um argumento
                    args.size >= 2 && args[0] == "PLAYER_INFO" -> {
                        val nomeJogador = args[1]
                        getPlayerInfo(manager, nomeJogador)

                    }
                    // Verificar se o comando é "PLAYERS_BY_SPECIE" e se tem pelo menos um argumento
                    args.size >= 2 && args[0] == "PLAYERS_BY_SPECIE" -> {
                        val idEspecie = args[1]
                        getPlayersBySpecie(manager, idEspecie)

                    }
                    // Verificar se o comando é "MOST_TRAVELED" e se tem pelo menos um argumento
                    args.isNotEmpty() && args[0] == "MOST_TRAVELED" -> {
                        getMostTraveled(manager)

                    }
                    // Verificar se o comando é "TOP_ENERGETIC_OMNIVORES" e se tem pelo menos um argumento
                    args.size >= 2 && args[0] == "TOP_ENERGETIC_OMNIVORES" -> {
                        val maxResults = args[1].toIntOrNull() ?: 0
                        getTopErnergeticOmnivores(manager, maxResults)

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

/**
 * GET TOP_ENERGETIC_OMNIVORES
 */
fun getTopErnergeticOmnivores (manager: GameManager, max_results: Int) : String {

    val listaJogadoresOmnivorosEmJogo = mutableListOf<String>()

    for (jogador in manager.jogadores) {
        if (jogador.especie.getTipoAlimentacaoDaEspecie().equals("omnívoro")) {
            val nomeJogador = jogador.nome
            val energia = jogador.especie.getEnergiaAtual()

            // NOME_JOGADOR:ENERGIA
            listaJogadoresOmnivorosEmJogo.addAll(listOf("$nomeJogador:$energia"))
        }
    }

    if (max_results >= 2) {
        //Obtém os jogadores omnívoros com mais energia, ordenados de forma decrescente. ([1] -> energia)
        listaJogadoresOmnivorosEmJogo.sortByDescending { it.split(":")[1].toInt() }

        // Limitar o número de elementos na lista...
        val listaLimitada = listaJogadoresOmnivorosEmJogo.take(max_results)

        // ... remover os elementos antigos (reset)...
        listaJogadoresOmnivorosEmJogo.clear()
        // ...para depois adicionar os novos elementos.
        listaJogadoresOmnivorosEmJogo.addAll(listaLimitada)
    }

    // Quebra de linha
    return listaJogadoresOmnivorosEmJogo.joinToString("\n")
}



fun TODO(): (GameManager, List<String>) -> String? {
    return { _, _ ->
        // TODO: Implementar alguma coisa aqui
        "Falta implementar alguma coisa aqui."
    }
}




