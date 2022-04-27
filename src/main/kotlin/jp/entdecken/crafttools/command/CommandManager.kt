package jp.entdecken.cardinal.command

import jp.entdecken.cardinal.util.NmsUtil
import jp.entdecken.crafttools.CraftTools
import org.bukkit.Bukkit
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandMap

/**
 * コマンドの登録などを行う
 *
 * @author kigawa
 * @since 2022-4-10
 */
object CommandManager
{
    private val commandMap: CommandMap
        get()
        {
            val cla = Class.forName(NmsUtil.craftPackage + ".CraftServer")
            val method = cla.getMethod("getCommandMap")
            return method.invoke(Bukkit.getServer()) as CommandMap
        }

    /**
     * 通常のexecutorをセットする(plugin.ymlにコマンドを記述する)
     *
     * @param strCommand コマンド
     * @param executor
     */
    fun setExecutor(strCommand: String, executor: CommandExecutor)
    {
        val command =
            CraftTools.PLUGIN.getCommand(strCommand) ?: throw CommandNotFoundException("you need register command")
        command.setExecutor(executor)
    }

    /**
     * 独自のコマンドで実装する(plugin.ymlに記述しない)
     *
     * @param commands 実装するコマンド
     */
    fun registerCommand(vararg commands: AbstractCommand)
    {
        for (command in commands)
        {
            commandMap.register(command.name, command)
        }
    }
}
