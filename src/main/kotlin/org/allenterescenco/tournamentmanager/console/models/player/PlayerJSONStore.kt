package org.allenterescenco.tournamentmanager.console.models.player

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.allenterescenco.tournamentmanager.console.helpers.*
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

    internal fun logAll() {
        players.forEach { logger.info("${it}") }
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