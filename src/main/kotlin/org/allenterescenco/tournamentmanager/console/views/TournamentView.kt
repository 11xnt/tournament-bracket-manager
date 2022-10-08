package org.allenterescenco.tournamentmanager.console.views

import org.allenterescenco.tournamentmanager.console.models.TournamentMemStore
import org.allenterescenco.tournamentmanager.console.models.TournamentModel

class TournamentView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("TOURNAMENT MAIN MENU")
        println(" 1. Add Tournament")
        println(" 2. Update a Tournament")
        println(" 3. List All Tournaments")
        println(" 4. Search Tournaments")
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

    fun listTournaments(tournaments : TournamentMemStore) {
        println("List All Tournaments")
        println()
        tournaments.logAll()
        println()
    }

    fun showTournament(tournament : TournamentModel) {
        if(tournament != null)
            println("Tournament Details [ $tournament ]")
        else
            println("Tournament Not Found...")
    }

    fun addTournamentData(tournament : TournamentModel) : Boolean {

        println()
        print("Enter a Name : ")
        tournament.name = readLine()!!
        print("Enter an Organiser : ")
        tournament.org = readLine()!!

        return tournament.name.isNotEmpty() && tournament.org.isNotEmpty()
    }

    fun updateTournamentData(tournament : TournamentModel) : Boolean {

        val tempName: String?
        val tempOrg: String?

        if (tournament != null) {
            print("Enter a new Title for [ " + tournament.name + " ] : ")
            tempName = readLine()!!
            print("Enter a new Description for [ " + tournament.org + " ] : ")
            tempOrg = readLine()!!

            if (!tempName.isNullOrEmpty() && !tempOrg.isNullOrEmpty()) {
                tournament.name = tempName
                tournament.org = tempOrg
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