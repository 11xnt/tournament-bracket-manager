package org.allenterescenco.tournamentmanager.console.main

import mu.KotlinLogging
import org.allenterescenco.tournamentmanager.console.controllers.TournamentController

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    TournamentController().start()
}