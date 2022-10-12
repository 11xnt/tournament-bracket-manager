package org.allenterescenco.tournamentmanager.console.models.team

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.allenterescenco.tournamentmanager.console.helpers.*
import org.allenterescenco.tournamentmanager.console.main.ANSI_BLUE
import org.allenterescenco.tournamentmanager.console.main.ANSI_GREEN
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "teams.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<TeamModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class TeamJSONStore : TeamStore {

    var teams = mutableListOf<TeamModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<TeamModel> {
        return teams
    }

    override fun findOne(id: Long) : TeamModel? {
        var foundTeam: TeamModel? = teams.find { p -> p.id == id }
        return foundTeam
    }

    override fun create(team: TeamModel) {
        team.id = generateRandomId()
        teams.add(team)
        serialize()
    }

    override fun update(team: TeamModel) {
        var foundTeam = findOne(team.id!!)
        if (foundTeam != null) {
            foundTeam.name = team.name
            foundTeam.wins = team.wins
            foundTeam.losses = team.losses
            foundTeam.winPercentage = team.winPercentage
            foundTeam.players = team.players
        }
        serialize()
    }

    override fun delete(team: TeamModel) {
        var foundTeam = findOne(team.id!!)
        if (foundTeam != null) {
            teams.remove(team)
        }
        serialize()
    }

    internal fun logAll() {
        teams.forEach {
            println(ANSI_BLUE + "ID: " + ANSI_GREEN + "${it.id}" + ANSI_BLUE + " Team Name: " + ANSI_GREEN + "${it.name}")
            println(ANSI_BLUE + "Team Wins: " + ANSI_GREEN + "${it.wins}")
            println(ANSI_BLUE + "Team Losses: " + ANSI_GREEN + "${it.losses}")
            println(ANSI_BLUE + "Win Percentage: " + ANSI_GREEN + "${it.winPercentage}")
            println(ANSI_BLUE + "Players: ")
            it.players.forEach() {
                println( ANSI_GREEN + "${it.fullName}")
            }
        }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(teams, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        teams = Gson().fromJson(jsonString, listType)
    }
}