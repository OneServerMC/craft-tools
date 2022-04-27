package jp.entdecken.crafttools.command

import jp.entdecken.cardinal.command.AbstractCommand
import org.bukkit.command.CommandSender

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
                "fill" -> fillSpace()
                "redo" -> redo()
                else -> null
            }
            else -> null
        }
    }

    private fun redo(): String
    {

    }

    private fun fillSpace(): String
    {

    }
}