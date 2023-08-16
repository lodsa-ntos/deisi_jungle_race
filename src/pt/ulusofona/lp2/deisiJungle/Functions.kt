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

fun TODO(): (GameManager, List<String>) -> String? {
    return { _, _ ->
        // TODO: Implementar alguma coisa aqui
        "Falta implementar alguma coisa aqui."
    }
}




