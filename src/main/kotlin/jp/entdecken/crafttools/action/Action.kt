package jp.entdecken.crafttools.action

import jp.entdecken.crafttools.CraftTools
import jp.entdecken.crafttools.ServerTicks
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitTask

abstract class Action {
    private val changedBlocks: MutableMap<Block, BlockData> = LinkedHashMap()
    private lateinit var task: BukkitTask
    fun setBlock(block: Block, material: Material) {
        changedBlocks[block] = block.blockData
        block.type = material
    }

    fun redo(player: Player): String {
        if (!task.isCancelled) {
            return "running task now"
        }
        task = Bukkit.getScheduler().runTaskTimer(CraftTools.PLUGIN, Runnable { redoTask(player) }, 1, 1)
        return "redo started"
    }

    private fun redoTask(player: Player) {
        while (!ServerTicks.isOverMaxTick()) {
            if (changedBlocks.isEmpty()) {
                task.cancel()
                player.sendMessage("redo ended")
                return
            }
            val block = changedBlocks.keys.first()
            block.blockData = changedBlocks[block] ?: continue
            changedBlocks.remove(block)
        }
    }
}
