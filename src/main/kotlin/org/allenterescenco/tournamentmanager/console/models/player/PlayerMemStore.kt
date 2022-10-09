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
        var foundTeam: PlayerModel? = players.find { p -> p.id == id }
        return foundTeam
    }

    override fun create(team: PlayerModel) {
        team.id = getId()
        players.add(team)
        logAll()
    }

    override fun update(team: PlayerModel) {
        var foundTeam = findOne(team.id!!)
        if (foundTeam != null) {
            foundTeam.name = team.name
            foundTeam.wins = team.wins
            foundTeam.losses = team.losses
            foundTeam.winPercentage = team.winPercentage
            foundTeam.players = team.players
        }
    }

    internal fun logAll() {
        players.forEach { logger.info("${it}") }
    }
}