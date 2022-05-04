package jp.entdecken.crafttools

import java.util.Calendar

object ServerTicks {
    private var tickStartedTime: Long = 0

    fun startTick() {
        tickStartedTime = Calendar.getInstance().timeInMillis
    }

    fun isOverMaxTick(): Boolean {
        val diff: Long = Calendar.getInstance().timeInMillis - tickStartedTime
        return CraftTools.maxTickTime < diff
    }
}
