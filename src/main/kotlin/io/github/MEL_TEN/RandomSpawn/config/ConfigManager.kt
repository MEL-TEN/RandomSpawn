package io.github.mel_ten.randomspawn.config

import io.github.mel_ten.randomspawn.util.gson
import io.github.mel_ten.randomspawn.util.serializeJsonString
import io.github.mel_ten.randomspawn.RandomSpawn

import io.github.mel_ten.randomspawn.config.Config as ConfigType

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.Listener

import java.nio.file.Files
import java.nio.file.Path
class ConfigManager: Listener {
    var Config: ConfigType = ConfigType()

    private val target: Path = RandomSpawn.instance.dataFolder.toPath().resolve("config.json")

    internal fun load() {
        if (this.target.toFile().exists()) {
            Config = gson.fromJson(
                Files.readAllBytes(this.target).toString(Charsets.UTF_8),
                ConfigType::class.java
            )
        }
    }

    internal fun save() {
        if (!this.target.toFile().exists()) {
            Files.createDirectories(this.target.parent)
            Files.createFile(this.target)
        }

        Files.write(this.target, Config.serializeJsonString().toByteArray())
    }

    var LimitX: Long
        get() {
            return Config.LimitX
        }
        set(value) {
            Config.LimitX = value
        }

    var FixedY: Long
        get() {
            return Config.FixedY
        }
        set(value) {
            Config.FixedY = value
        }

    var LimitZ: Long
        get() {
            return Config.LimitZ
        }
        set(value) {
            Config.LimitZ = value
        }

    fun ChatException(args: String, exception: Exception) {
        Bukkit.getServer().consoleSender.sendMessage(ChatColor.RED.toString() + this.Config.ServerName + " " + args + ":" + exception.message)
    }

    fun ChatAlert(args: String) {
        Bukkit.getServer().consoleSender.sendMessage(ChatColor.GREEN.toString() + Config.ServerName + " " + args)
    }
}