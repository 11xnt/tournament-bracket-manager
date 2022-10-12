package org.allenterescenco.tournamentmanager.console.controllers

import mu.KotlinLogging

import org.allenterescenco.tournamentmanager.console.models.player.PlayerModel
import org.allenterescenco.tournamentmanager.console.models.player.PlayerJSONStore
import org.allenterescenco.tournamentmanager.console.models.team.TeamModel
import org.allenterescenco.tournamentmanager.console.views.PlayerView

class PlayerController {

    val players = PlayerJSONStore()
    val playerView = PlayerView()
    val logger = KotlinLogging.logger {}

    init {
        println(ANSI_GREEN + "Launching Player Manager Console App" + ANSI_RESET)
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
                5 -> delete()
                -99 -> dummyData()
                -1 -> println(ANSI_RED + "Exiting App" + ANSI_RESET)
                else -> println(ANSI_RED + "Invalid Option" + ANSI_RESET)
            }
            println()
        } while (input != -1)
        println(ANSI_RED +"Shutting Down Player Manager Console App" + ANSI_RESET)
    }

    fun menu() :Int { return playerView.menu() }

    fun add(){
        val tempPlayer = PlayerModel()

        if (playerView.addPlayerData(tempPlayer))
            players.create(tempPlayer)
        else
            println(ANSI_RED +"Player Not Added"+ ANSI_RESET )
    }

    fun list() {
        playerView.listPlayers(players)
    }

    fun update() {

        playerView.listPlayers(players)
        val searchId = playerView.getId("Update")
        val tempPlayer = search(searchId)

        if(tempPlayer != null) {
            if(playerView.updatePlayerData(tempPlayer)) {
                players.update(tempPlayer)
                playerView.showPlayer(tempPlayer)
                logger.info(ANSI_GREEN + "Player Updated : [ $tempPlayer ]" + ANSI_RESET)
            }
            else
                logger.info(ANSI_RED + "Player Not Updated" + ANSI_RESET)
        }
        else
            println(ANSI_RED + "Player Not Updated..." + ANSI_RESET)
    }

    fun delete() {
        playerView.listPlayers(players)
        val searchId = playerView.getId("Delete")
        val tempPlayer = search(searchId)

        if(tempPlayer != null) {
            players.delete(tempPlayer)
            logger.info(ANSI_RED + "Player Deleted : [ $tempPlayer ]" + ANSI_RESET)
            println(ANSI_RED + "Player Deleted" + ANSI_RESET)
        }
        else
            println(ANSI_RED + "Player Not Updated..." + ANSI_RESET)
    }

    fun search() {
        val tempPlayer = search(playerView.getId("Search"))!!
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