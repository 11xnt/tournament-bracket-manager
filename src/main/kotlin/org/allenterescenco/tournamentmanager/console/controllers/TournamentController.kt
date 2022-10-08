package org.allenterescenco.tournamentmanager.console.controllers

import mu.KotlinLogging

import org.allenterescenco.tournamentmanager.console.models.TournamentMemStore
import org.allenterescenco.tournamentmanager.console.models.TournamentModel
import org.allenterescenco.tournamentmanager.console.views.TournamentView

class TournamentController {

    val tournaments = TournamentMemStore()
    val tournamentView = TournamentView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Placemark Console App" }
        println("Placemark Kotlin App Version 1.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Placemark Console App" }
    }

    fun menu() :Int { return tournamentView.menu() }

    fun add(){
        val tempTournament = TournamentModel()

        if (tournamentView.addTournamentData(tempTournament))
            tournaments.create(tempTournament)
        else
            logger.info("Placemark Not Added")
    }

    fun list() {
        tournamentView.listTournaments(tournaments)
    }

    fun update() {

        tournamentView.listTournaments(tournaments)
        val searchId = tournamentView.getId()
        val tempTournament = search(searchId)

        if(tempTournament != null) {
            if(tournamentView.updateTournamentData(tempTournament)) {
                tournaments.update(tempTournament)
                tournamentView.showTournament(tempTournament)
                logger.info("Placemark Updated : [ $tempTournament ]")
            }
            else
                logger.info("Placemark Not Updated")
        }
        else
            println("Placemark Not Updated...")
    }

    fun search() {
        val tempTournament = search(tournamentView.getId())!!
        tournamentView.showTournament(tempTournament)
    }


    fun search(id: Long) : TournamentModel? {
        val foundTournament = tournaments.findOne(id)
        return foundTournament
    }

    fun dummyData() {
        tournaments.create(TournamentModel(name = "New York New York", org = "So Good They Named It Twice"))
        tournaments.create(TournamentModel(name= "Ring of Kerry", org = "Some place in the Kingdom"))
        tournaments.create(TournamentModel(name = "Waterford City", org = "You get great Blaas Here!!"))
    }
}