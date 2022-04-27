package jp.entdecken.crafttools.action

import jp.entdecken.crafttools.CraftTools
import jp.entdecken.crafttools.ServerTicks
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitTask
import java.util.*

class FillSpace : Action()
{
    private lateinit var fillSpaceTask: BukkitTask
    private val blocks: MutableList<Block> = LinkedList()
    private lateinit var material: Material

    fun fillSpace(player: Player): String
    {
        if (!fillSpaceTask.isCancelled)
        {
            return "running task now"
        }
        val block = player.location.block.getRelative(BlockFace.DOWN)
        material = block.type
        blocks.addAll(listOf(
            block.getRelative(BlockFace.DOWN),
            block.getRelative(BlockFace.EAST),
            block.getRelative(BlockFace.NORTH),
            block.getRelative(BlockFace.SOUTH),
            block.getRelative(BlockFace.WEST)
        ))

        fillSpaceTask = Bukkit.getScheduler().runTaskTimer(CraftTools.PLUGIN, Runnable { fillSpaceTask(player) }, 1, 1)
        return "fillSpace started"
    }

    private fun fillSpaceTask(player: Player)
    {
        while (!ServerTicks.isOverMaxTick())
        {
            if (blocks.isEmpty())
            {
                fillSpaceTask.cancel()
                player.sendMessage("redo ended")
                return
            }
            val block = blocks.first()
            if (block.isEmpty) setBlock(block, material)
            blocks.removeFirst()
        }
    }
}