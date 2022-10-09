package org.allenterescenco.tournamentmanager.console.views

import org.allenterescenco.tournamentmanager.console.models.team.TeamMemStore
import org.allenterescenco.tournamentmanager.console.models.team.TeamModel
import org.allenterescenco.tournamentmanager.console.models.tournament.TournamentMemStore
import org.allenterescenco.tournamentmanager.console.models.tournament.TournamentModel

class TeamView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("TEAM MAIN MENU")
        println(" 1. Add a Team")
        println(" 2. Update a Team")
        println(" 3. List All Teams")
        println(" 4. Search Teams")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listTeams(teams : TeamMemStore) {
        println("List All Teams")
        println()
        teams.logAll()
        println()
    }

    fun showTeams(team : TeamModel) {
        if(team != null)
            println("Team Details [ $team ]")
        else
            println("Team Not Found...")
    }

    fun addTeamData(team : TeamModel) : Boolean {

        println()
        print("Enter a Name : ")
        team.name = readLine()!!
        print("Enter their number of wins : ")
        team.wins = readln().toInt()
        print("Enter their number of losses : ")
        team.losses = readln().toInt()
        team.winPercentage = ((team.wins / (team.wins + team.losses))*100).toDouble()
        print("Enter the Team's players : ")
//        team.players = readLine()

        return team.name.isNotEmpty() &&
                team.wins >= 0 &&
                team.losses >= 0 &&
                team.winPercentage >= 0
    }

    fun updateTeamData(team : TeamModel) : Boolean {

        val tempName: String?
        val tempWins: Int
        val tempLosses: Int
        // tempPlayers


        if (team != null) {
            print("Enter a new Name for [ " + team.name + " ] : ")
            tempName = readLine()!!
            print("Enter a new win total for [ " + team.wins + " ] : ")
            tempWins = readln().toInt()
            print("Enter a new win total for [ " + team.losses + " ] : ")
            tempLosses = readln().toInt()

            if (!tempName.isNullOrEmpty() && tempWins >= 0 && tempLosses >= 0) {
                team.name = tempName
                team.wins = tempWins
                team.losses = tempLosses
                team.winPercentage = ((tempWins/tempWins+tempLosses)*100).toDouble()
                // team.players = tempPlayers
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}