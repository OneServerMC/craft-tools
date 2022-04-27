package jp.entdecken.crafttools

import java.util.*

object ServerTicks
{
    var tickStartedTime: Long = 0
        private set


    fun startTick()
    {
        ServerTicks.tickStartedTime = Calendar.getInstance().timeInMillis
    }

    fun isOverMilliSec(milliSec: Long): Boolean
    {
        val diff: Long = Calendar.getInstance().timeInMillis - tickStartedTime
        return milliSec < diff
    }
}