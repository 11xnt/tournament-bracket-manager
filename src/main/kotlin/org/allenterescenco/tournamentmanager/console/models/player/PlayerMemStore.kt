package org.allenterescenco.tournamentmanager.console.models.player

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PlayerMemStore : PlayerStore {

    val players = ArrayList<PlayerModel>()

    override fun findAll(): List<PlayerModel> {
        return players
    }

    override fun findOne(id: Long) : PlayerModel? {
        var foundPlayer: PlayerModel? = players.find { p -> p.id == id }
        return foundPlayer
    }

    override fun create(player: PlayerModel) {
        player.id = getId()
        players.add(player)
        logAll()
    }

    override fun update(player: PlayerModel) {
        var foundPlayer = findOne(player.id!!)
        if (foundPlayer != null) {
            foundPlayer.fullName = player.fullName
            foundPlayer.dOB = player.dOB
            foundPlayer.playsFor = player.playsFor
        }
    }

    internal fun logAll() {
        players.forEach { logger.info("${it}") }
    }
}