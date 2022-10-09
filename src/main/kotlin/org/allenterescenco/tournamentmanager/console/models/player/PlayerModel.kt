package org.allenterescenco.tournamentmanager.console.models.player
import org.allenterescenco.tournamentmanager.console.models.team.TeamModel

data class PlayerModel(var id: Long = 0,
                       var fullName: String = "Default Name",
                       var dOB: String = "01/01/99",
                       var playsFor: TeamModel = TeamModel(0,"Default Team",0,0,0, arrayOf(""))
)