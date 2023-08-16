package pt.ulusofona.lp2.deisiJungle

enum class CommandType {
    GET,
    POST
}

fun router(): (CommandType) -> Unit {
    return { commandType ->
        when (commandType) {
            CommandType.GET -> null
            CommandType.POST -> null
        }
    }
}





