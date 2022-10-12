package org.allenterescenco.tournamentmanager.console.controllers

import mu.KotlinLogging
import org.allenterescenco.tournamentmanager.console.models.team.TeamModel
import org.allenterescenco.tournamentmanager.console.models.tournament.TournamentJSONStore

import org.allenterescenco.tournamentmanager.console.models.tournament.TournamentModel
import org.allenterescenco.tournamentmanager.console.views.TournamentView


class TournamentController {

    val tournaments = TournamentJSONStore()
    val tournamentView = TournamentView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Tournament Manager Console App" }
        println("Tournament Kotlin App Version 1.0")
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
                -1 -> println(ANSI_RED + "Exiting App" + ANSI_RESET)
                else -> println(ANSI_RED + "Invalid Option" + ANSI_RESET)
            }
            println()
        } while (input != -1)
        println(ANSI_RED +"Shutting Down Team Manager Console App" + ANSI_RESET)
    }

    fun menu() :Int { return tournamentView.menu() }

    fun add(){
        val tempTournament = TournamentModel()

        if (tournamentView.addTournamentData(tempTournament))
            tournaments.create(tempTournament)
        else
            logger.info(ANSI_RED+"Tournament Not Added"+ ANSI_RESET)
    }

    fun list() {
        tournamentView.listTournaments(tournaments)
    }

    fun update() {

        tournamentView.listTournaments(tournaments)
        val searchId = tournamentView.getId("Update")
        val tempTournament = search(searchId)

        if(tempTournament != null) {
            if(tournamentView.updateTournamentData(tempTournament)) {
                tournaments.update(tempTournament)
                tournamentView.showTournament(tempTournament)
                logger.info(ANSI_GREEN+"Tournament Updated : [ $tempTournament ]"+ ANSI_RESET)
            }
            else
                logger.info(ANSI_RED+"Tournament Not Updated"+ ANSI_RESET)
        }
        else
            println(ANSI_RED+"Tournament Not Updated..."+ ANSI_RESET)
    }

    fun search() {
        val tempTournament = search(tournamentView.getId("Search"))!!
        tournamentView.showTournament(tempTournament)
    }


    fun search(id: Long) : TournamentModel? {
        val foundTournament = tournaments.findOne(id)
        return foundTournament
    }

    fun dummyData() {
        tournaments.create(
            TournamentModel(name = "Pro League",
            org = "Pro Org",
            startDate = "11/06/2022",
            endDate = "15/06/2022",
            maxTeams = 64,
            partTeams = arrayListOf(TeamModel(1, "Team 1"), TeamModel(2, "Team 2")),
            winner = TeamModel(1, "Team 1"))
        )
        tournaments.create(
            TournamentModel(name = "Inter League",
            org = "Inter Org",
            startDate = "25/10/2022",
            endDate = "28/10/2022",
            maxTeams = 2,
            partTeams = arrayListOf(TeamModel(1, "Team 1"), TeamModel(2, "Team 2")),
            winner = TeamModel(1, "Team 1"))
        )
        tournaments.create(
            TournamentModel(name = "Rookie League",
            org = "Rookie Org",
            startDate = "25/07/2022",
            endDate = "28/07/2022",
            maxTeams = 32,
            partTeams = arrayListOf(TeamModel(1, "Team 1"), TeamModel(2, "Team 2")),
            winner = TeamModel(1, "Team 1"))
        )
    }
}