package org.allenterescenco.tournamentmanager.console.main

import mu.KotlinLogging
import org.allenterescenco.tournamentmanager.console.controllers.TournamentController
import org.allenterescenco.tournamentmanager.console.models.TournamentMemStore
import org.allenterescenco.tournamentmanager.console.models.TournamentModel
import org.allenterescenco.tournamentmanager.console.views.TournamentView

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    TournamentController().start()
}