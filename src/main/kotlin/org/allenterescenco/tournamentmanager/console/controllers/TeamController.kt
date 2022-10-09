package org.allenterescenco.tournamentmanager.console.controllers

import mu.KotlinLogging

import org.allenterescenco.tournamentmanager.console.models.team.TeamModel
import org.allenterescenco.tournamentmanager.console.models.team.TeamMemStore
import org.allenterescenco.tournamentmanager.console.views.TeamView

class TeamController {

    val teams = TeamMemStore()
    val teamView = TeamView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Team Manager Console App" }
        println("Team Kotlin App Version 1.0")
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
        logger.info { "Shutting Down Team Manager Team Console App" }
    }

    fun menu() :Int { return teamView.menu() }

    fun add(){
        val tempTeam = TeamModel()

        if (teamView.addTeamData(tempTeam))
            teams.create(tempTeam)
        else
            logger.info("Team Not Added")
    }

    fun list() {
        teamView.listTeams(teams)
    }

    fun update() {

        teamView.listTeams(teams)
        val searchId = teamView.getId()
        val tempTeam = search(searchId)

        if(tempTeam != null) {
            if(teamView.updateTeamData(tempTeam)) {
                teams.update(tempTeam)
                teamView.showTeam(tempTeam)
                logger.info("Team Updated : [ $tempTeam ]")
            }
            else
                logger.info("Team Not Updated")
        }
        else
            println("Team Not Updated...")
    }

    fun search() {
        val tempTeam = search(teamView.getId())!!
        teamView.showTeam(tempTeam)
    }


    fun search(id: Long) : TeamModel? {
        val foundTeam = teams.findOne(id)
        return foundTeam
    }

    fun dummyData() {
        teams.create(
            TeamModel(name = "Team Red",
                wins = 3,
                losses = 0,
                winPercentage = 100,
                players = arrayOf("Player2", "Player1"))
        )
        teams.create(
            TeamModel(name = "Team Blue",
                wins = 2,
                losses = 1,
                winPercentage = 66,
                players = arrayOf("Player18", "Player4"))
        )
        teams.create(
            TeamModel(name = "Team Green",
                wins = 0,
                losses = 3,
                winPercentage = 0,
                players = arrayOf("Player6", "Player3"))
        )
    }
}