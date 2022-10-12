package org.allenterescenco.tournamentmanager.console.main

import mu.KotlinLogging
import org.allenterescenco.tournamentmanager.console.controllers.PlayerController
import org.allenterescenco.tournamentmanager.console.controllers.TeamController
import org.allenterescenco.tournamentmanager.console.controllers.TournamentController

private val logger = KotlinLogging.logger {}

/** Colour ANSI escape codes found from here:
 * https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
 */
const val ANSI_RESET = "\u001B[0m"
const val ANSI_RED = "\u001B[31m"
const val ANSI_GREEN = "\u001B[32m"
const val ANSI_YELLOW = "\u001B[33m"
const val ANSI_BLUE = "\u001B[34m"


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
            -1 -> println( ANSI_RED + "Exiting App")
            else -> println( ANSI_RED + "Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { ANSI_RED + "Shutting Down Tournament Manager Console App" }
}

/**
 * Main menu for user input to start and exit different controllers
 */
fun menu() : Int {

    var option : Int
    var input : String?

    println( ANSI_GREEN + "MAIN MENU")
    println( ANSI_BLUE + " 1. " + ANSI_YELLOW + "Enter Tournament Menu")
    println( ANSI_BLUE + " 2. " + ANSI_YELLOW +"Enter Team Menu")
    println( ANSI_BLUE + " 3. " + ANSI_YELLOW + "Enter Player Menu")
    println( ANSI_RED + "-1. Exit")
    println()
    print( ANSI_GREEN + "Enter an option: " + ANSI_RESET)
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}
