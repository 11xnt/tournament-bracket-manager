package org.allenterescenco.tournamentmanager.console.views

import org.allenterescenco.tournamentmanager.console.models.team.TeamJSONStore
import org.allenterescenco.tournamentmanager.console.models.tournament.TournamentJSONStore
import org.allenterescenco.tournamentmanager.console.models.team.TeamModel
import org.allenterescenco.tournamentmanager.console.models.tournament.TournamentModel

val teams = TeamJSONStore()
val teamView = TeamView()

class TournamentView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println(ANSI_GREEN +"TOURNAMENT MAIN MENU")
        println(ANSI_BLUE + " 1. " + ANSI_YELLOW + "Add Tournament")
        println(ANSI_BLUE + " 2. " + ANSI_YELLOW + "Update a Tournament")
        println(ANSI_BLUE + " 3. " + ANSI_YELLOW + "List All Tournaments")
        println(ANSI_BLUE + " 4. " + ANSI_YELLOW + "Search Tournaments")
        println(ANSI_BLUE + " 5. " + ANSI_YELLOW + "Delete a Tournament")
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

    fun listTournaments(tournaments: TournamentJSONStore) {
        println(ANSI_GREEN + "Listing All Tournaments" + ANSI_RESET)
        println()
        tournaments.logAll()
        println()
    }

    fun showTournament(tournament : TournamentModel) {
        if(tournament != null)
            println(ANSI_YELLOW + "Tournament Details [ $tournament ]" + ANSI_RESET)
        else
            println(ANSI_RED + "Tournament Not Found..." + ANSI_RESET)
    }

    fun addTournamentData(tournament : TournamentModel) : Boolean {

        val tempTeams: ArrayList<TeamModel> = arrayListOf()

        println()
        print(ANSI_YELLOW + "Enter a Name : " + ANSI_RESET)
        tournament.name = readLine()!!
        print(ANSI_YELLOW + "Enter an Organiser : " + ANSI_RESET)
        tournament.org = readLine()!!
        print(ANSI_YELLOW + "Enter the Starting Date : " + ANSI_RESET)
        tournament.startDate = readLine()!!
        print(ANSI_YELLOW + "Enter the Ending Date : " + ANSI_RESET)
        tournament.endDate = readLine()!!
        print(ANSI_YELLOW + "Enter the Max Teams : " + ANSI_RESET)
        tournament.maxTeams = readln().toInt()

        println(ANSI_YELLOW + "Choose a teams to partake in the tournament for [ " + tournament.winner + " ] : " + ANSI_RESET)
        println(ANSI_YELLOW + "How many teams do you wish to add? : " + ANSI_RESET)
        val tempNumOfTeams: Int? = readLine()?.toInt()

        for (i in 0..tempNumOfTeams!!) {
            teamView.listTeams(teams)
            val searchId = teamView.getId("Add")
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
            print(ANSI_YELLOW + "Enter a new Title for [ " + tournament.name + " ] : " + ANSI_RESET)
            tempName = readLine()!!

            print(ANSI_YELLOW + "Enter a new Description for [ " + tournament.org + " ] : " + ANSI_RESET)
            tempOrg = readLine()!!

            print(ANSI_YELLOW + "Enter a new Start Date for [ " + tournament.startDate + " ] : " + ANSI_RESET)
            tempStartDate = readLine()!!

            print(ANSI_YELLOW + "Enter a new End Date for [ " + tournament.endDate + " ] : " + ANSI_RESET)
            tempEndDate = readLine()!!

            print(ANSI_YELLOW + "Choose a new Winner for [ " + tournament.winner + " ] : " + ANSI_RESET)
            teamView.listTeams(teams)
            val searchId = teamView.getId("")
            tempWinner = search(searchId)

            print(ANSI_YELLOW + "Choose a teams to partake in the tournament for [ " + tournament.winner + " ] : " + ANSI_RESET)
            print(ANSI_YELLOW + "How many teams do you wish to add? : " + ANSI_RESET)
            tempNumOfTeams = readLine()?.toInt()

            for (i in 0..tempNumOfTeams!!) {
                teamView.listTeams(teams)
                val searchId = teamView.getId("Update")
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

    fun getId(type : String) : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to ${type} : " + ANSI_RESET)
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}