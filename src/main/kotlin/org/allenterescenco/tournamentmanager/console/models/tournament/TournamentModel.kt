package org.allenterescenco.tournamentmanager.console.models.tournament

import org.allenterescenco.tournamentmanager.console.models.team.TeamModel

data class TournamentModel(var id: Long = 0,
                           var name: String = "",
                           var org: String = "",
                           var startDate: String = "",
                           var endDate: String = "",
                           var maxTeams: Int = 0,
                           var partTeams: ArrayList<TeamModel> = arrayListOf(),
                           var winner: TeamModel = TeamModel()
)