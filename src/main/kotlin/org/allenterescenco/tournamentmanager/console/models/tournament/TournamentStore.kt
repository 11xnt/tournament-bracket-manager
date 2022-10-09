package org.allenterescenco.tournamentmanager.console.models.tournament

interface TournamentStore {
    fun findAll(): List<TournamentModel>
    fun findOne(id: Long): TournamentModel?
    fun create(tournament: TournamentModel)
    fun update(tournament: TournamentModel)
}