package org.allenterescenco.tournamentmanager.console.main

import mu.KotlinLogging
import org.allenterescenco.tournamentmanager.console.models.TournamentModel

private val logger = KotlinLogging.logger {}

val tournaments = ArrayList<TournamentModel>()

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
    println(" 2. Create a Tournament")
    println(" 3. Delete a Tournament")
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
    var tempTournament = TournamentModel()
    println("You Chose To Create a Tournament")


    print("Tournament Name: ")
    tempTournament.name = readLine()!!
    print("Tournament Organiser: ")
    tempTournament.org = readLine()!!
    print("${tempTournament.name}'s Starting Date: ")
    tempTournament.startDate = readLine()!!
    print("${tempTournament.name}'s Ending Date: ")
    tempTournament.endDate = readLine()!!
    print("Max Teams: ")
    tempTournament.maxTeams = readLine()!!.toInt()

    println("Tournament: ${tempTournament.name} has been added")
    tempTournament.id = tournaments.size.toLong()
    tournaments.add(tempTournament.copy())
}

fun listTournaments() {
    println("You Chose To View Tournaments")
    tournaments.forEach { logger.info("${it}") }
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

fun searchTournaments() {

    var searchId = getId()
    val tempTournament = search(searchId)

    if(tempTournament != null)
        println("Placemark Details [ $tempTournament ]")
    else
        println("Placemark Not Found...")
}

fun getId() : Long {
    var strId : String? // String to hold user input
    var searchId : Long // Long to hold converted id
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : TournamentModel? {
    var foundTournament: TournamentModel? = tournaments.find { p -> p.id == id }
    return foundTournament
}