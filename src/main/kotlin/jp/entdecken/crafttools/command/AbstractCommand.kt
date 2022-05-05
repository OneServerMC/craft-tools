package jp.entdecken.cardinal.command

import org.bukkit.command.CommandSender
import org.bukkit.command.defaults.BukkitCommand

/**
 * 独自のコマンド
 *
 * @author kigawa
 * @since 2022-4-10
 *
 * @param name コマンド
 * @param description 説明
 * @param usage 使用方法
 */
abstract class AbstractCommand(name: String, description: String, usage: String) :
    BukkitCommand(name, description, usage, ArrayList()) {
    override fun tabComplete(sender: CommandSender, alias: String, args: Array<out String>): List<String> {
        return onTabComplete(sender, alias, args).toMutableList()
    }

    /**
     * tab補完のリストを取得する
     *
     * @param sender 送信者
     * @param alias 最初のコマンド
     * @param args サブコマンド以降の配列
     * @return 補完のリスト
     */
    protected abstract fun onTabComplete(
        sender: CommandSender,
        alias: String,
        args: Array<out String>
    ): Collection<String>

    override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
        val result: String = onCommand(sender, commandLabel, args) ?: return false
        if (result != "") sender.sendMessage(result)
        return true
    }

    /**
     * コマンドを実行する
     *
     * @param sender 送信者
     * @param commandLabel 最初のコマンド
     * @param args サブコマンド以降の配列
     * @return コマンド終了時のメッセージnullの場合falseを返すなにも送らない場合空文字を返す
     */
    protected abstract fun onCommand(sender: CommandSender, commandLabel: String, args: Array<out String>): String?
}
