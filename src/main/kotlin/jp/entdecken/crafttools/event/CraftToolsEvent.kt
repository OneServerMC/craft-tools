package jp.entdecken.crafttools.event

import com.destroystokyo.paper.event.server.ServerTickStartEvent
import jp.entdecken.crafttools.ServerTicks
import org.bukkit.event.EventHandler

class CraftToolsEvent
{
    @EventHandler
    fun tickStart(event: ServerTickStartEvent)
    {
        ServerTicks.startTick()
    }
}