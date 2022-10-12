package org.allenterescenco.tournamentmanager.console.models.player

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.allenterescenco.tournamentmanager.console.helpers.*
import org.allenterescenco.tournamentmanager.console.main.ANSI_BLUE
import org.allenterescenco.tournamentmanager.console.main.ANSI_GREEN
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "players.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<PlayerModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class PlayerJSONStore : PlayerStore {

    var players = mutableListOf<PlayerModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<PlayerModel> {
        return players
    }

    override fun findOne(id: Long) : PlayerModel? {
        var foundPlayer: PlayerModel? = players.find { p -> p.id == id }
        return foundPlayer
    }

    override fun create(player: PlayerModel) {
        player.id = generateRandomId()
        players.add(player)
        serialize()
    }

    override fun update(player: PlayerModel) {
        var foundPlayer = findOne(player.id!!)
        if (foundPlayer != null) {
            foundPlayer.fullName = player.fullName
            foundPlayer.dOB = player.dOB
            foundPlayer.playsFor = player.playsFor
        }
        serialize()
    }
    override fun delete(player: PlayerModel) {
        var foundPlayer = findOne(player.id!!)
        if (foundPlayer != null) {
            players.remove(player)
        }
        serialize()
    }


    internal fun logAll() {
        players.forEach {
            println(ANSI_BLUE + "ID: " + ANSI_GREEN + "${it.id}" + ANSI_BLUE + " Full Name: " + ANSI_GREEN + "${it.fullName}")
            println(ANSI_BLUE + "Date of Birth: " + ANSI_GREEN + "${it.dOB}")
            println(ANSI_BLUE + "Plays For: " + ANSI_GREEN + "${it.playsFor.name}")
            println()
        }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(players, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        players = Gson().fromJson(jsonString, listType)
    }
}