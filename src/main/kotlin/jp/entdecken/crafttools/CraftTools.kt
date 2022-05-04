package jp.entdecken.crafttools

import jp.entdecken.cardinal.command.CommandManager
import jp.entdecken.crafttools.command.Commands
import jp.entdecken.crafttools.event.CraftToolsEvent
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class CraftTools : JavaPlugin() {
    companion object {
        lateinit var PLUGIN: CraftTools
            private set
        const val maxTickTime: Long = 50
        const val logSize: Int = 5
    }

    override fun onEnable() {
        PLUGIN = this
        Bukkit.getServer().pluginManager.registerEvents(CraftToolsEvent(), this)
        CommandManager.registerCommand(Commands())
        logger.info("enabled CraftTools")
    }

    override fun onDisable() {
        Bukkit.getServer().onlinePlayers.forEach { ActionLogs.rmPlayer(it) }
        logger.info("disabled CraftTools")
    }
}
