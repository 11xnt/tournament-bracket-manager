package org.allenterescenco.tournamentmanager.console.models.tournament

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class TournamentMemStore : TournamentStore {

    val tournaments = ArrayList<TournamentModel>()

    override fun findAll(): List<TournamentModel> {
        return tournaments
    }

    override fun findOne(id: Long) : TournamentModel? {
        var foundTournament: TournamentModel? = tournaments.find { p -> p.id == id }
        return foundTournament
    }

    override fun create(tournament: TournamentModel) {
        tournament.id = getId()
        tournaments.add(tournament)
        logAll()
    }

    override fun update(tournament: TournamentModel) {
        var foundTournament = findOne(tournament.id!!)
        if (foundTournament != null) {
            foundTournament.name = tournament.name
            foundTournament.org = tournament.org
            foundTournament.startDate = tournament.startDate
            foundTournament.endDate = tournament.endDate
            foundTournament.maxTeams = tournament.maxTeams
        }
    }

    override fun delete(tournament: TournamentModel) {
        var foundTournament = findOne(tournament.id!!)
        if (foundTournament != null) {
            tournaments.remove(tournament)
        }
    }

    internal fun logAll() {
        tournaments.forEach { logger.info("${it}") }
    }
}