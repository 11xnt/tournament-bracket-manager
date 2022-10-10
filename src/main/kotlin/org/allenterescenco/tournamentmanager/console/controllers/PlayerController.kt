package org.allenterescenco.tournamentmanager.console.controllers

import mu.KotlinLogging

import org.allenterescenco.tournamentmanager.console.models.player.PlayerModel
import org.allenterescenco.tournamentmanager.console.models.player.PlayerMemStore
import org.allenterescenco.tournamentmanager.console.models.team.TeamModel
import org.allenterescenco.tournamentmanager.console.views.PlayerView

class PlayerController {

    val players = PlayerMemStore()
    val playerView = PlayerView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Player Manager Console App" }
        println("Player Kotlin App Version 1.0")
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
        logger.info { "Shutting Down Player Manager Player Console App" }
    }

    fun menu() :Int { return playerView.menu() }

    fun add(){
        val tempPlayer = PlayerModel()

        if (playerView.addPlayerData(tempPlayer))
            players.create(tempPlayer)
        else
            logger.info("Player Not Added")
    }

    fun list() {
        playerView.listPlayers(players)
    }

    fun update() {

        playerView.listPlayers(players)
        val searchId = playerView.getId()
        val tempPlayer = search(searchId)

        if(tempPlayer != null) {
            if(playerView.updatePlayerData(tempPlayer)) {
                players.update(tempPlayer)
                playerView.showPlayer(tempPlayer)
                logger.info("Player Updated : [ $tempPlayer ]")
            }
            else
                logger.info("Player Not Updated")
        }
        else
            println("Player Not Updated...")
    }

    fun search() {
        val tempPlayer = search(playerView.getId())!!
        playerView.showPlayer(tempPlayer)
    }


    fun search(id: Long) : PlayerModel? {
        val foundPlayer = players.findOne(id)
        return foundPlayer
    }

    fun dummyData() {
        players.create(
            PlayerModel(fullName = "Paul Green",
                dOB = "21/08/2001",
                playsFor = TeamModel(99,"Team Blue", 2,1, 66, arrayListOf())
            )
        )
        players.create(
            PlayerModel(fullName = "Todd Howard",
                dOB = "15/07/1999",
                playsFor = TeamModel(99,"Team Red", 2,1, 66, arrayListOf())
            )
        )
        players.create(
            PlayerModel(fullName = "Jim Jones",
                dOB = "10/06/2000",
                playsFor = TeamModel(99,"Team Green", 2,1, 66, arrayListOf())
            )
        )
    }
}