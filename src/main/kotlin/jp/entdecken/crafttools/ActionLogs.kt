package jp.entdecken.crafttools

import jp.entdecken.crafttools.action.Action
import org.bukkit.entity.Player
import java.util.LinkedList

object ActionLogs {
    private val actions: MutableMap<Player, MutableList<Action>> = LinkedHashMap()

    fun getLatest(player: Player): Action? {
        if (actions[player]?.isEmpty() ?: return null) return null
        return actions[player]?.last() ?: return null
    }

    fun rmLatest(player: Player) {
        actions[player]?.removeLast()
    }

    fun rmPlayer(player: Player) {
        actions[player]?.forEach { it.task?.cancel() }
        actions.remove(player)
    }

    fun addLog(player: Player, action: Action) {
        val logs: MutableList<Action> = actions[player] ?: LinkedList<Action>()
        logs.add(action)
        if (logs.size > CraftTools.logSize) {
            logs.first().task?.cancel()
            logs.removeFirst()
        }
        actions[player] = logs
    }
}
