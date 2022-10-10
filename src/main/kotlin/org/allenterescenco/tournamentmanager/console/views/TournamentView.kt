package org.allenterescenco.tournamentmanager.console.views

import org.allenterescenco.tournamentmanager.console.models.team.TeamMemStore
import org.allenterescenco.tournamentmanager.console.models.team.TeamModel
import org.allenterescenco.tournamentmanager.console.models.tournament.TournamentMemStore
import org.allenterescenco.tournamentmanager.console.models.tournament.TournamentModel

val teams = TeamMemStore()
val teamView = TeamView()

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
        val foundTournaments = tournaments.findAll()
        var i = 0;
        for (tournament in foundTournaments) {
            println("${i}. ${tournament.name}")
            i++
        }
        println()
    }

    fun showTournament(tournament : TournamentModel) {
        if(tournament != null)
            println("Tournament Details [ $tournament ]")
        else
            println("Tournament Not Found...")
    }

    fun addTournamentData(tournament : TournamentModel) : Boolean {

        val tempTeams: ArrayList<TeamModel> = arrayListOf()

        println()
        print("Enter a Name : ")
        tournament.name = readLine()!!
        print("Enter an Organiser : ")
        tournament.org = readLine()!!
        print("Enter the Starting Date : ")
        tournament.startDate = readLine()!!
        print("Enter the Ending Date : ")
        tournament.endDate = readLine()!!
        print("Enter the Max Teams : ")
        tournament.maxTeams = readln().toInt()

        println("Choose a teams to partake in the tournament for [ " + tournament.winner + " ] : ")
        println("How many teams do you wish to add? : ")
        val tempNumOfTeams: Int? = readLine()?.toInt()

        for (i in 0..tempNumOfTeams!!) {
            teamView.listTeams(teams)
            val searchId = teamView.getId()
            val chosenTeam = search(searchId)
            if (chosenTeam != null) {
                tempTeams.add(chosenTeam)
            }
        }

        tournament.partTeams = tempTeams

        return tournament.name.isNotEmpty() &&
                tournament.org.isNotEmpty() &&
                tournament.startDate.isNotEmpty() &&
                tournament.endDate.isNotEmpty() &&
                tournament.maxTeams > 0 &&
                tournament.partTeams.isNotEmpty()
    }

    fun updateTournamentData(tournament : TournamentModel) : Boolean {

        val tempName: String?
        val tempOrg: String?
        val tempStartDate: String?
        val tempEndDate: String?
        val tempTeams: ArrayList<TeamModel> = arrayListOf()
        val tempWinner: TeamModel?
        val tempNumOfTeams: Int?


        if (tournament != null) {
            print("Enter a new Title for [ " + tournament.name + " ] : ")
            tempName = readLine()!!

            print("Enter a new Description for [ " + tournament.org + " ] : ")
            tempOrg = readLine()!!

            print("Enter a new Start Date for [ " + tournament.startDate + " ] : ")
            tempStartDate = readLine()!!

            print("Enter a new End Date for [ " + tournament.endDate + " ] : ")
            tempEndDate = readLine()!!

            print("Choose a new Winner for [ " + tournament.winner + " ] : ")
            teamView.listTeams(teams)
            val searchId = teamView.getId()
            tempWinner = search(searchId)

            print("Choose a teams to partake in the tournament for [ " + tournament.winner + " ] : ")
            print("How many teams do you wish to add? : ")
            tempNumOfTeams = readLine()?.toInt()

            for (i in 0..tempNumOfTeams!!) {
                teamView.listTeams(teams)
                val searchId = teamView.getId()
                val chosenTeam = search(searchId)
                if (chosenTeam != null) {
                    tempTeams.add(chosenTeam)
                }
            }


            if (!tempName.isNullOrEmpty() && !tempOrg.isNullOrEmpty()) {
                tournament.name = tempName
                tournament.org = tempOrg
                tournament.startDate = tempStartDate
                tournament.endDate = tempEndDate
                if (tempWinner != null) {
                    tournament.winner = tempWinner
                }
                tournament.partTeams = tempTeams

                return true
            }
        }
        return false
    }

    fun search(id: Long) : TeamModel? {
        val foundTeam = teams.findOne(id)
        return foundTeam
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