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

fun TODO(): Unit? {
    //TODO falta implementar alguma coisa aqui;
    return null;
}




