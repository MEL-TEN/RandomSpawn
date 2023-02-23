package io.github.mel_ten.randomspawn

import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

class SignalGenerator {
    private val plugin = RandomSpawn.instance
    private var TaskID = -1

    //SignalGenerator() { }
    fun Initialize() {
        gameProcess = GameProcess.inst()
    }

    //SignalGenerator() { gameProcess = GameProcess.inst(); }
    //===============================
    fun Cancel() {
        if (TaskID != -1) Bukkit.getScheduler().cancelTask(TaskID)
    }

    fun Start() {
        TaskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, SignalGenerator20Hz(), 1L, 1L)
    }

    internal inner class SignalGenerator20Hz : Runnable {
        override fun run() {
            gameProcess!!.CycleProgress()
        }
    }

    companion object {
        private var singleton: SignalGenerator? = null
        fun inst(): SignalGenerator? {
            if (singleton == null) {
                singleton = SignalGenerator()
            }
            return singleton
        }

        val plugin: Plugin? = null
        private var gameProcess: GameProcess? = null
    }
}