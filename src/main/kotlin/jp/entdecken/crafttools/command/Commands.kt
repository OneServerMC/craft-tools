package jp.entdecken.crafttools.command

import jp.entdecken.cardinal.command.AbstractCommand
import jp.entdecken.crafttools.ActionLogs
import jp.entdecken.crafttools.action.FillSpace
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Commands : AbstractCommand("tool", "use tools", "/tool <subcommand>")
{
    override fun onTabComplete(sender: CommandSender, alias: String, args: Array<out String>): Collection<String>
    {
        return when (args.size)
        {
            1 -> listOf(
                "fill",
                "redo"
            )
            else -> listOf()
        }
    }

    override fun onCommand(sender: CommandSender, commandLabel: String, args: Array<out String>): String?
    {
        return when (args.size)
        {
            1 -> when (args[0])
            {
                "fill" -> fillSpace(sender)
                "redo" -> redo(sender)
                else -> null
            }
            else -> null
        }
    }

    private fun redo(sender: CommandSender): String
    {
        if (sender !is Player) return "this command can send only player"
        val action = ActionLogs.getLatest(sender)
        ActionLogs.rmLatest(sender)
        return action?.redo(sender) ?: "you don't have log"
    }

    private fun fillSpace(sender: CommandSender): String
    {
        if (sender !is Player) return "this command can send only player"
        val fillSpace = FillSpace()
        ActionLogs.addLog(sender, fillSpace)
        return fillSpace.fillSpace(sender)
    }
}