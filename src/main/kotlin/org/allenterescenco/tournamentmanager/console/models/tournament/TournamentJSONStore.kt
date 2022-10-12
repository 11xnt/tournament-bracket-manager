package org.allenterescenco.tournamentmanager.console.models.tournament

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import org.allenterescenco.tournamentmanager.console.controllers.ANSI_RESET

import org.allenterescenco.tournamentmanager.console.helpers.*
import org.allenterescenco.tournamentmanager.console.main.ANSI_BLUE
import org.allenterescenco.tournamentmanager.console.main.ANSI_GREEN
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "tournaments.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<TournamentModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class TournamentJSONStore : TournamentStore {

    var tournaments = mutableListOf<TournamentModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<TournamentModel> {
        return tournaments
    }

    override fun findOne(id: Long) : TournamentModel? {
        var foundTournament: TournamentModel? = tournaments.find { p -> p.id == id }
        return foundTournament
    }

    override fun create(tournament: TournamentModel) {
        tournament.id = generateRandomId()
        tournaments.add(tournament)
        serialize()
    }

    override fun update(tournament: TournamentModel) {
        var foundTournament = findOne(tournament.id!!)
        if (foundTournament != null) {
            foundTournament.name = tournament.name
            foundTournament.org = tournament.org
            foundTournament.startDate = tournament.startDate
            foundTournament.endDate = tournament.endDate
            foundTournament.maxTeams = tournament.maxTeams
            foundTournament.partTeams = tournament.partTeams
            foundTournament.winner = tournament.winner
        }
        serialize()
    }



    internal fun logAll() {
        tournaments.forEach {
            println(ANSI_BLUE + "ID: " + ANSI_GREEN + "${it.id}" + ANSI_BLUE + "Tournament Name: " + ANSI_GREEN + "${it.name}")
            println(ANSI_BLUE + "Tournament Organiser: " + ANSI_GREEN + "${it.org}")
            println(ANSI_BLUE + "Start Date: " + ANSI_GREEN + "${it.startDate}")
            println(ANSI_BLUE + "Ending Date: " + ANSI_GREEN + "${it.endDate}")
            println(ANSI_BLUE + "Max Teams: " + ANSI_GREEN + "${it.maxTeams}")
            println(ANSI_BLUE + "Participating Teams: ")
            it.partTeams.forEach() {
                println( ANSI_GREEN + "${it.name}")
            }
            println(ANSI_BLUE + "Winner: " + ANSI_GREEN + "${it.winner.name}")
        }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(tournaments, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        tournaments = Gson().fromJson(jsonString, listType)
    }
}