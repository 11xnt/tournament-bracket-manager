package org.allenterescenco.tournamentmanager.console.models.team

interface TeamStore {
    fun findAll(): List<TeamModel>
    fun findOne(id: Long): TeamModel?
    fun create(team: TeamModel)
    fun update(team: TeamModel)
}