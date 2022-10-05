import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    logger.info { "Launching Tournament Bracket Manager Console App" }
    println("Tournament Bracket Manager Kotlin App Version 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> listTournaments()
            2 -> addTournament()
            3 -> deleteTournament()
            4 -> listTeams()
            5 -> addTeam()
            6 -> updateTeam()
            7 -> deleteTeam()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Tournament Bracket Manager Console App" }
}

fun menu() : Int {

    var option : Int
    var input: String? = null

    println("Main Menu")
    println(" 1. View Tournaments")
    println(" 2. Create Tournaments")
    println(" 3. Delete Tournaments")
    println(" ----------------------- ")
    println(" 4. View Teams")
    println(" 5. Add Teams")
    println(" 6. Update Teams")
    println(" 7. Delete Teams")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option

}

fun addTournament() {
    println("You Chose To Create a Tournament")
}

fun listTournaments() {
    println("You Chose To View Tournaments")
}

fun deleteTournament() {
    println("You Chose To Delete a Tournament")
}

fun listTeams() {
    println("You Chose To View Teams")
}

fun addTeam() {
    println("You Chose To Add a Team")
}

fun updateTeam() {
    println("You Chose To Update a Team")
}

fun deleteTeam() {
    println("You Chose To Delete a Team")
}