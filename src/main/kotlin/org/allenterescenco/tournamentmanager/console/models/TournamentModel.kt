package org.allenterescenco.tournamentmanager.console.models

data class TournamentModel(var id: Long = 0,
                           var name: String = "",
                           var org: String = "",
                           var startDate: String = "",
                           var endDate: String = "",
                           var maxTeams: Int = 0,
                           var partTeams: Array<String> = arrayOf("",""),
                           var winner: String = ""
)