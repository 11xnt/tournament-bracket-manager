package org.allenterescenco.tournamentmanager.console.models.team
import org.allenterescenco.tournamentmanager.console.models.player.PlayerModel
data class TeamModel(var id: Long = 0,
                     var name: String = "Default Team Name",
                     var wins: Int = 0,
                     var losses: Int = 0,
                     var winPercentage: Int = 0,
                     var players: ArrayList<PlayerModel> = arrayListOf()
)