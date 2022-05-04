package jp.entdecken.crafttools

import java.util.Calendar

object ServerTicks {
    var tickStartedTime: Long = 0
        private set

    fun startTick() {
        ServerTicks.tickStartedTime = Calendar.getInstance().timeInMillis
    }

    fun isOverMaxTick(): Boolean {
        val diff: Long = Calendar.getInstance().timeInMillis - tickStartedTime
        return CraftTools.maxTickTime < diff
    }
}
