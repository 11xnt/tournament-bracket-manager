package org.allenterescenco.tournamentmanager.console.views

import org.allenterescenco.tournamentmanager.console.models.player.PlayerMemStore
import org.allenterescenco.tournamentmanager.console.models.player.PlayerModel
import org.allenterescenco.tournamentmanager.console.models.team.TeamModel

class PlayerView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("PLAYER MAIN MENU")
        println(" 1. Add a Player")
        println(" 2. Update a Player")
        println(" 3. List All Players")
        println(" 4. Search Players")
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

    fun listPlayers(players : PlayerMemStore) {
        println("List All Players")
        println()
        players.logAll()
        println()
    }

    fun showPlayer(player : PlayerModel) {
        if(player != null)
            println("Team Details [ $player ]")
        else
            println("Team Not Found...")
    }

    fun addPlayerData(player : PlayerModel) : Boolean {

        println()
        print("Enter a Full Name : ")
        player.fullName = readLine()!!
        print("Enter their Date of Birth : ")
        player.dOB = readLine()!!
        print("Choose who they play for: ")
        player.playsFor = TeamModel()

        return player.fullName.isNotEmpty() &&
                player.dOB.isNullOrEmpty() &&
                player.playsFor != null
    }

    fun updatePlayerData(player : PlayerModel) : Boolean {

        val tempFullName: String?
        val tempDOB: String?
        val tempPlaysFor: TeamModel?


        if (player != null) {
            print("Enter a new Name for [ " + player.fullName + " ] : ")
            tempFullName = readLine()!!
            print("Enter a new win total for [ " + player.dOB + " ] : ")
            tempDOB = readLine()!!
            print("Enter a new win total for [ " + player.playsFor + " ] : ")
            tempPlaysFor = TeamModel(0,"Default Team",0,0,0, arrayOf(""))

            if (!tempFullName.isNullOrEmpty() && !tempDOB.isNullOrEmpty() && tempPlaysFor != null) {
                player.fullName = tempFullName
                player.dOB = tempDOB
                player.playsFor = tempPlaysFor
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