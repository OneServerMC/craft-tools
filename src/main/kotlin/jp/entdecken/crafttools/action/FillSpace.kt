package jp.entdecken.crafttools.action

import jp.entdecken.crafttools.CraftTools
import jp.entdecken.crafttools.ServerTicks
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import java.util.LinkedList

class FillSpace : Action() {
    private val blocks: MutableList<Block> = LinkedList()
    private lateinit var material: Material

    companion object {
        private val blockFaces: Array<BlockFace> = arrayOf(
            BlockFace.DOWN,
            BlockFace.EAST,
            BlockFace.WEST,
            BlockFace.NORTH,
            BlockFace.SOUTH
        )
    }

    fun fillSpace(player: Player): String {
        if (task?.isCancelled == true) {
            return "running task now"
        }
        val block = player.location.block.getRelative(BlockFace.DOWN)
        material = block.type

        blockFaces.forEach { blocks.add(block.getRelative(it)) }

        task = Bukkit.getScheduler().runTaskTimer(
            CraftTools.PLUGIN,
            Runnable
            { fillSpaceTask(player) },
            1, 1
        )
        return "fillSpace started"
    }

    private fun fillSpaceTask(player: Player) {
        while (!ServerTicks.isOverMaxTick()) {
            if (blocks.isEmpty()) {
                task?.cancel()
                player.sendMessage("redo ended")
                return
            }
            val block = blocks.first()
            blocks.removeFirst()

            if (!block.isEmpty) continue

            setBlock(block, material)

            blockFaces.forEach {
                val relative = block.getRelative(it)
                if (relative.isEmpty) blocks.add(relative)
            }
        }
    }
}
