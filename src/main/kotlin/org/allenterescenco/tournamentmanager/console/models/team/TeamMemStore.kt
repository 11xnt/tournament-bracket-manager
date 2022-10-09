package org.allenterescenco.tournamentmanager.console.models.team

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class TeamMemStore : TeamStore {

    val teams = ArrayList<TeamModel>()

    override fun findAll(): List<TeamModel> {
        return teams
    }

    override fun findOne(id: Long) : TeamModel? {
        var foundTeam: TeamModel? = teams.find { p -> p.id == id }
        return foundTeam
    }

    override fun create(team: TeamModel) {
        team.id = getId()
        teams.add(team)
        logAll()
    }

    override fun update(team: TeamModel) {
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
        teams.forEach { logger.info("${it}") }
    }
}