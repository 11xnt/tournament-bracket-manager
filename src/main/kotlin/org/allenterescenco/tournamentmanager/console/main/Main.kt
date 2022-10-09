package org.allenterescenco.tournamentmanager.console.main

import mu.KotlinLogging
import org.allenterescenco.tournamentmanager.console.controllers.TournamentController
import org.allenterescenco.tournamentmanager.console.controllers.TeamController
private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    TeamController().start()
}