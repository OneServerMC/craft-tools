package jp.entdecken.crafttools

import jp.entdecken.crafttools.action.Action
import org.bukkit.entity.Player
import java.util.*

object ActionLogs
{
    private val actions: MutableMap<Player, MutableList<Action>> = LinkedHashMap()

    fun getLatest(player: Player): Action?
    {
        return actions[player]?.last() ?: return null
    }

    fun rmLatest(player: Player)
    {
        actions[player]?.removeLast()
    }

    fun rmPlayer(player: Player)
    {
        actions.remove(player)
    }

    fun addLog(player: Player, action: Action)
    {
        val logs: MutableList<Action> = actions[player] ?: LinkedList<Action>()
        if (logs.size > CraftTools.logSize) logs.removeFirst()
    }
}