package jp.entdecken.crafttools

import org.bukkit.plugin.java.JavaPlugin

class CraftTools : JavaPlugin()
{
    companion object
    {
        lateinit var PLUGIN: CraftTools
            private set
    }

    override fun onEnable()
    {
        logger.info("enabling CraftTools")
        PLUGIN = this

        logger.info("enabled CraftTools")
    }

    override fun onDisable()
    {
    }
}