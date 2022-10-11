package org.allenterescenco.tournamentmanager.console.views

import org.allenterescenco.tournamentmanager.console.models.player.PlayerJSONStore
import org.allenterescenco.tournamentmanager.console.models.team.TeamModel
import org.allenterescenco.tournamentmanager.console.models.player.PlayerModel
import org.allenterescenco.tournamentmanager.console.models.team.TeamJSONStore
import org.allenterescenco.tournamentmanager.console.models.tournament.TournamentJSONStore

val players = PlayerJSONStore()
val playerView = PlayerView()

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

    fun listTeams(teams: TeamJSONStore) {
        println("List All Teams")
        println()
        teams.logAll()
        println()
    }

    fun showTeam(team : TeamModel) {
        if(team != null)
            println("Team Details [ $team ]")
        else
            println("Team Not Found...")
    }

    fun addTeamData(team : TeamModel) : Boolean {

        val tempPlayers: ArrayList<PlayerModel> = arrayListOf()

        println()
        print("Enter a Name : ")
        team.name = readLine()!!
        print("Enter their number of wins : ")
        team.wins = readln().toInt()
        print("Enter their number of losses : ")
        team.losses = readln().toInt()
        team.winPercentage = ((team.wins.toDouble() / (team.wins+team.losses))*100).toInt()

        println("Choose a players to add to the team for [ " + team.name + " ] : ")
        println("How many players do you wish to add? : ")

        val tempNumOfPlayers: Int? = readLine()?.toInt()

        for (i in 0..tempNumOfPlayers!!) {
            playerView.listPlayers(players)
            val searchId = playerView.getId()
            val chosenTeam = search(searchId)
            if (chosenTeam != null) {
                tempPlayers.add(chosenTeam)
            }
        }

        team.players = tempPlayers

        return team.name.isNotEmpty() &&
                team.wins >= 0 &&
                team.losses >= 0 &&
                team.winPercentage >= 0 &&
                team.players.isNotEmpty()
    }

    fun updateTeamData(team : TeamModel) : Boolean {

        val tempName: String?
        val tempWins: Int
        val tempLosses: Int
        val tempPlayers: ArrayList<PlayerModel> = arrayListOf()


        if (team != null) {
            print("Enter a new Name for [ " + team.name + " ] : ")
            tempName = readLine()!!
            print("Enter a new win total for [ " + team.wins + " ] : ")
            tempWins = readln().toInt()
            print("Enter a new win total for [ " + team.losses + " ] : ")
            tempLosses = readln().toInt()

            println("Choose a players to add to the team for [ " + team.name + " ] : ")
            println("How many players do you wish to add? : ")

            val tempNumOfPlayers: Int? = readLine()?.toInt()

            for (i in 0..tempNumOfPlayers!!) {
                playerView.listPlayers(players)
                val searchId = playerView.getId()
                val chosenTeam = search(searchId)
                if (chosenTeam != null) {
                    tempPlayers.add(chosenTeam)
                }
            }

            team.players = tempPlayers

            if (!tempName.isNullOrEmpty() && tempWins >= 0 && tempLosses >= 0) {
                team.name = tempName
                team.wins = tempWins
                team.losses = tempLosses
                team.winPercentage = ((team.wins.toDouble() / (team.wins+team.losses))*100).toInt()
                team.players = tempPlayers
                return true
            }
        }
        return false
    }

    fun search(id: Long) : PlayerModel? {
        val foundPlayer = players.findOne(id)
        return foundPlayer
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