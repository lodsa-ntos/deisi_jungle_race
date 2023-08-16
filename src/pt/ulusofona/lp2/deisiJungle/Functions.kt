package pt.ulusofona.lp2.deisiJungle

enum class CommandType {
    GET,
    POST
}

fun router(): (CommandType) -> Unit {
    return { commandType ->
        when (commandType) {
            CommandType.GET -> TODO()
            CommandType.POST -> TODO()
        }
    }
}

fun TODO(): String {
    // TODO: Implementar alguma coisa aqui
    return "A implementação está pendente"
}



