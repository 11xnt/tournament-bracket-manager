package org.allenterescenco.tournamentmanager.console.models.player

interface PlayerStore {
    fun findAll(): List<PlayerModel>
    fun findOne(id: Long): PlayerModel?
    fun create(player: PlayerModel)
    fun update(player: PlayerModel)
    fun delete(player: PlayerModel)
}