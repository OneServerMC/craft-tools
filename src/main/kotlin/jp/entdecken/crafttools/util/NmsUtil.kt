package jp.entdecken.cardinal.util

import org.bukkit.Bukkit

object NmsUtil {

    val craftPackage: String
        get() {
            val nmsVersion = Bukkit.getServer().javaClass.getPackage().name.split(".")[3]
            return "org.bukkit.craftbukkit.$nmsVersion"
        }
}
