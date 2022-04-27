package jp.entdecken.crafttools.event

import com.destroystokyo.paper.event.server.ServerTickStartEvent
import jp.entdecken.crafttools.ActionLogs
import jp.entdecken.crafttools.ServerTicks
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class CraftToolsEvent : Listener
{
    @EventHandler
    fun tickStart(event: ServerTickStartEvent)
    {
        ServerTicks.startTick()
    }

    @EventHandler
    fun rmAction(event: PlayerQuitEvent)
    {
        ActionLogs.rmPlayer(event.player)
    }
}