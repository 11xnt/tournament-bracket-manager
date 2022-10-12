package org.allenterescenco.tournamentmanager.console.views

import org.allenterescenco.tournamentmanager.console.models.player.PlayerJSONStore
import org.allenterescenco.tournamentmanager.console.models.team.TeamModel
import org.allenterescenco.tournamentmanager.console.models.player.PlayerModel
import org.allenterescenco.tournamentmanager.console.models.team.TeamJSONStore
import org.allenterescenco.tournamentmanager.console.models.tournament.TournamentJSONStore

val players = PlayerJSONStore()
val playerView = PlayerView()

class TeamView {

    /**
     * Main Team menu for users to select to bring them into the different options
     */
    fun menu() : Int {

        var option : Int
        var input: String?

        println(ANSI_GREEN +"TEAM MAIN MENU")
        println(ANSI_BLUE + " 1. " + ANSI_YELLOW + "Add a Team")
        println(ANSI_BLUE + " 2. " + ANSI_YELLOW + "Update a Team")
        println(ANSI_BLUE + " 3. " + ANSI_YELLOW + "List All Teams")
        println(ANSI_BLUE + " 4. " + ANSI_YELLOW + "Search Teams")
        println(ANSI_BLUE + " 5. " + ANSI_YELLOW + "Add Players to Team")
        println(ANSI_BLUE + " 6. " + ANSI_YELLOW + "Delete a Team")
        println(ANSI_RED + "-1. Exit" + ANSI_RESET)
        println()
        print( ANSI_GREEN + "Enter Option : " + ANSI_RESET )
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    // list all teams found in the teams.json file
    fun listTeams(teams: TeamJSONStore) {
        println(ANSI_GREEN + "Listing All Teams" + ANSI_RESET)
        println()
        teams.logAll()
        println()
    }

    // list a team found in the teams.json file
    fun showTeam(team : TeamModel) {
        if(team != null)
            println(ANSI_YELLOW + "Team Details [ $team ]" + ANSI_RESET)
        else
            println(ANSI_RED + "Team Not Found..." + ANSI_RESET)
    }

    // takes in user input for details of a new team
    fun addTeamData(team : TeamModel) : Boolean {

        val tempPlayers: ArrayList<PlayerModel> = arrayListOf()

        println()
        print(ANSI_YELLOW + "Enter a Team Name : " + ANSI_RESET)
        team.name = readLine()!!
        print(ANSI_YELLOW + "Enter their number of wins : " + ANSI_RESET)
        team.wins = readln().toInt()
        print(ANSI_YELLOW +"Enter their number of losses : " + ANSI_RESET)
        team.losses = readln().toInt()
        team.winPercentage = ((team.wins.toDouble() / (team.wins+team.losses))*100).toInt()

        println(ANSI_YELLOW +"Choose a players to add to the team for [ " + team.name + " ] : " + ANSI_RESET)
        println(ANSI_YELLOW +"How many players do you wish to add? : " + ANSI_RESET)

        val tempNumOfPlayers: Int? = readLine()?.toInt()

        for (i in 0..tempNumOfPlayers!!) {
            playerView.listPlayers(players)
            val searchId = playerView.getId("Add")
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

    // takes user input to update team data
    fun updateTeamData(team : TeamModel) : Boolean {

        val tempName: String?
        val tempWins: Int
        val tempLosses: Int
        val tempPlayers: ArrayList<PlayerModel> = arrayListOf()


        if (team != null) {
            print(ANSI_YELLOW +"Enter a new Name for [ " + team.name + " ] : " + ANSI_RESET)
            tempName = readLine()!!
            print(ANSI_YELLOW +"Enter a new win total for [ " + team.wins + " ] : " + ANSI_RESET)
            tempWins = readln().toInt()
            print(ANSI_YELLOW +"Enter a new win total for [ " + team.losses + " ] : " + ANSI_RESET)
            tempLosses = readln().toInt()

            println(ANSI_YELLOW +"Choose a players to add to the team for [ " + team.name + " ] : " + ANSI_RESET)
            println(ANSI_YELLOW +"How many players do you wish to add? : " + ANSI_RESET)

            val tempNumOfPlayers: Int? = readLine()?.toInt()

            for (i in 0..tempNumOfPlayers!!) {
                playerView.listPlayers(players)
                val searchId = playerView.getId("Update")
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

    // user gives a player id to add it to the team.players field
    fun addPlayerToTeam(team : TeamModel) {
        playerView.listPlayers(players)
        val searchId = playerView.getId("To Add to Team")
        val chosenPlayer = search(searchId)
        if(chosenPlayer != null) {
            team.players.add(chosenPlayer)

        }
    }

    // finds and returns a player found in the players.json file
    fun search(id: Long) : PlayerModel? {
        val foundPlayer = players.findOne(id)
        return foundPlayer
    }

    // prompts user to input an id
    fun getId(type : String) : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print(ANSI_YELLOW +"Enter id to ${type} : " + ANSI_RESET)
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}