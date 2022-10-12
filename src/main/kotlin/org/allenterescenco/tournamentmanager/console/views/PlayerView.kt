package org.allenterescenco.tournamentmanager.console.views

import org.allenterescenco.tournamentmanager.console.models.player.PlayerJSONStore
import org.allenterescenco.tournamentmanager.console.models.player.PlayerModel
import org.allenterescenco.tournamentmanager.console.models.team.TeamModel
/** Colour ANSI escape codes found from here:
 * https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
 */
const val ANSI_RESET = "\u001B[0m"
const val ANSI_RED = "\u001B[31m"
const val ANSI_GREEN = "\u001B[32m"
const val ANSI_YELLOW = "\u001B[33m"
const val ANSI_BLUE = "\u001B[34m"
class PlayerView {

    /**
     * Main Player menu for users to select to bring them into the different options
     */
    fun menu() : Int {

        var option : Int
        var input: String?

        println(ANSI_GREEN +"PLAYER MAIN MENU")
        println(ANSI_BLUE + " 1. " + ANSI_YELLOW + "Add a Player")
        println(ANSI_BLUE + " 2. " + ANSI_YELLOW + "Update a Player")
        println(ANSI_BLUE + " 3. " + ANSI_YELLOW + "List All Players")
        println(ANSI_BLUE + " 4. " + ANSI_YELLOW + "Search Players")
        println(ANSI_BLUE + " 5. " + ANSI_YELLOW + "Delete a Player")
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

    // list all players found in the players.json file
    fun listPlayers(players: PlayerJSONStore) {
        println(ANSI_GREEN + "Listing All Players" + ANSI_RESET)
        println()
        players.logAll()
        println()
    }

    // list a player found in the players.json file
    fun showPlayer(player : PlayerModel) {
        if(player != null)
            println(ANSI_YELLOW + "Player Details [ $player ]" + ANSI_RESET)
        else
            println(ANSI_RED + "Player Not Found..." + ANSI_RESET)
    }

    // takes in user input for details of a new player
    fun addPlayerData(player : PlayerModel) : Boolean {

        println()
        print(ANSI_YELLOW + "Enter a Full Name : " + ANSI_RESET)
        player.fullName = readLine()!!
        print(ANSI_YELLOW + "Enter their Date of Birth : " + ANSI_RESET)
        player.dOB = readLine()!!

        teamView.listTeams(teams)
        println(ANSI_YELLOW +"Choose who they play for: "+ ANSI_RESET)
        val searchId = teamView.getId("Choose")

        val chosenTeam = search(searchId)
        if (chosenTeam != null) {
            player.playsFor = chosenTeam
        } else {
            println("Team not found")
        }

        return player.fullName.isNotEmpty() &&
                player.dOB.isNotEmpty() &&
                player.playsFor != null
    }

    // finds and returns a player found in the players.json file
    fun search(id: Long) : TeamModel? {
        val foundTeam = teams.findOne(id)
        return foundTeam
    }

    // takes user input to update player data
    fun updatePlayerData(player : PlayerModel) : Boolean {

        val tempFullName: String?
        val tempDOB: String?

        if (player != null) {
            print(ANSI_YELLOW + "Enter a new Name for [ " + player.fullName + " ] : " + ANSI_RESET)
            tempFullName = readLine()!!
            print(ANSI_YELLOW + "Enter a new win total for [ " + player.dOB + " ] : " + ANSI_RESET)
            tempDOB = readLine()!!
            print(ANSI_YELLOW + "Enter a new win total for [ " + player.playsFor + " ] : " + ANSI_RESET)

            print(ANSI_YELLOW +"Choose who they play for: "+ ANSI_RESET)
            val searchId = teamView.getId("Choose")
            val tempChosenTeam = search(searchId)
            if (tempChosenTeam == null) {
                println("Team not found")
            }

            if (!tempFullName.isNullOrEmpty() && !tempDOB.isNullOrEmpty() && tempChosenTeam != null) {
                player.fullName = tempFullName
                player.dOB = tempDOB
                player.playsFor = tempChosenTeam
                return true
            }
        }
        return false
    }

    // prompts user to input an id
    fun getId(type : String) : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        println(ANSI_YELLOW + "Enter id to ${type} : " + ANSI_RESET)
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}