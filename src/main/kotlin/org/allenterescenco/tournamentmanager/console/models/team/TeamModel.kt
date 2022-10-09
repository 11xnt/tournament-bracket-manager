package org.allenterescenco.tournamentmanager.console.models.team

data class TeamModel(var id: Long = 0,
                     var name: String = "Default Name",
                     var wins: Int = 0,
                     var losses: Int = 0,
                     var winPercentage: Double = 0.00,
                     var players: Array<String> = arrayOf("",""),
)