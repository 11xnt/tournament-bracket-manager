package org.allenterescenco.tournamentmanager.console.main

import mu.KotlinLogging
import org.allenterescenco.tournamentmanager.console.controllers.PlayerController
import org.allenterescenco.tournamentmanager.console.controllers.TournamentController
import org.allenterescenco.tournamentmanager.console.controllers.TeamController

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    start()
}

fun start() {
    var input: Int

    do {
        input = menu()
        when (input) {
            1 -> TournamentController().start()
            2 -> TeamController().start()
            3 -> PlayerController().start()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Tournament Manager Console App" }
}

fun menu() : Int {

    var option : Int
    var input : String?

    println("MAIN MENU")
    println(" 1. Enter Tournament Menu")
    println(" 2. Enter Team Menu")
    println(" 3. Enter Player Menu")
    println("-1. Exit")
    println()
    print("Enter Option : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}
