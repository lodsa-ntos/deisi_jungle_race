package pt.ulusofona.lp2.deisiJungle

enum class CommandType {
    GET,
    POST
}

fun router(): (CommandType) -> ((manager: GameManager, args: List<String>) -> String?) {
    return { commandType ->
        when (commandType) {
            CommandType.GET -> TODO()
            CommandType.POST -> TODO()
        }
    }
}

// Obtém informações do jogador cujo nome é igual ao parâmetro.
// As informações devem ser obtidas no seguinte formato:
// <id> | <nome> | <nome_espécie> | <energia> | <posicao>

// Ex: GET PLAYER_INFO Sara
// 3 | Sara | Elefante | 130 | 12



fun TODO(): (GameManager, List<String>) -> String? {
    return { _, _ ->
        // TODO: Implementar alguma coisa aqui
        "Falta implementar alguma coisa aqui."
    }
}




