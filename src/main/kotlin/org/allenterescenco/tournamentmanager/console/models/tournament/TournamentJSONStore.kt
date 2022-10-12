package org.allenterescenco.tournamentmanager.console.models.tournament

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.allenterescenco.tournamentmanager.console.helpers.*
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
        tournaments.forEach { logger.info("${it}") }
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