package org.allenterescenco.tournamentmanager.console.controllers

import mu.KotlinLogging
import org.allenterescenco.tournamentmanager.console.models.player.PlayerModel
import org.allenterescenco.tournamentmanager.console.models.team.TeamJSONStore

import org.allenterescenco.tournamentmanager.console.models.team.TeamModel
import org.allenterescenco.tournamentmanager.console.views.TeamView

/** Colour ANSI escape codes found from here:
 * https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
 */
val ANSI_RESET = "\u001B[0m"
val ANSI_RED = "\u001B[31m"
val ANSI_GREEN = "\u001B[32m"

class TeamController {

    val teams = TeamJSONStore()
    val teamView = TeamView()
    val logger = KotlinLogging.logger {}

    init {
        println(ANSI_GREEN + "Launching Team Manager Console App" + ANSI_RESET)
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
                5 -> addPlayerToTeam()
                6 -> delete()
                -99 -> dummyData()
                -1 -> println(ANSI_RED + "Exiting App" + ANSI_RESET)
                else -> println(ANSI_RED + "Invalid Option" + ANSI_RESET)
            }
            println()
        } while (input != -1)
        println(ANSI_RED +"Shutting Down Team Manager Console App" + ANSI_RESET)
    }

    fun menu() :Int { return teamView.menu() }

    fun add(){
        val tempTeam = TeamModel()

        if (teamView.addTeamData(tempTeam))
            teams.create(tempTeam)
        else
            logger.info(ANSI_RED + "Team Not Added" + ANSI_RESET)
    }

    fun list() {
        teamView.listTeams(teams)
    }

    fun update() {

        teamView.listTeams(teams)
        val searchId = teamView.getId("Update")
        val tempTeam = search(searchId)

        if(tempTeam != null) {
            if(teamView.updateTeamData(tempTeam)) {
                teams.update(tempTeam)
                teamView.showTeam(tempTeam)
                logger.info(ANSI_GREEN + "Team Updated : [ $tempTeam ]" + ANSI_RESET)
                println(ANSI_GREEN + "Team Updated : [ $tempTeam ]" + ANSI_RESET)
            }
            else
                logger.info(ANSI_RED +"Team Not Updated" + ANSI_RESET)
        }
        else
            println(ANSI_RED +"Team Not Updated..." + ANSI_RESET)
    }

    fun delete() {
        teamView.listTeams(teams)
        val searchId = teamView.getId("Delete")
        val tempTeam = search(searchId)

        if(tempTeam != null) {
            teams.delete(tempTeam)
            logger.info(ANSI_RED + "Team Deleted : [ $tempTeam ]" + ANSI_RESET)
            println(ANSI_RED + "Team Deleted" + ANSI_RESET)
        }
        else
            println(ANSI_RED + "Team Not Updated..." + ANSI_RESET)
    }
    fun search() {
        val tempTeam = search(teamView.getId("Search"))!!
        teamView.showTeam(tempTeam)
    }


    fun search(id: Long) : TeamModel? {
        val foundTeam = teams.findOne(id)
        return foundTeam
    }

    fun addPlayerToTeam() {
        teamView.listTeams(teams)

        teamView.listTeams(teams)
        val searchId = teamView.getId("Add Players")
        val tempTeam = search(searchId)

        if(tempTeam != null) {
            teamView.addPlayerToTeam(tempTeam)
        }
    }

    fun dummyData() {
        teams.create(
            TeamModel(id = 1,
                name = "Team Red",
                wins = 3,
                losses = 0,
                winPercentage = 100,
                players = arrayListOf(PlayerModel(), PlayerModel()))
        )
        teams.create(
            TeamModel(id = 2,
                name = "Team Blue",
                wins = 2,
                losses = 1,
                winPercentage = 66,
                players = arrayListOf(PlayerModel(), PlayerModel()))
        )
        teams.create(
            TeamModel(id = 3,
                name = "Team Green",
                wins = 0,
                losses = 3,
                winPercentage = 0,
                players = arrayListOf(PlayerModel(), PlayerModel()))
        )
    }
}