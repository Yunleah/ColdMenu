package me.yunleah.plugin.coldmenu.internal

import org.bukkit.Bukkit
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.console
import taboolib.module.lang.sendLang

/**
 * ColdMenu
 * me.yunleah.plugin.coldmenu.internal
 *
 * @author Yunleah763
 * @since 2023/8/10 15:21
 */
object ColdMenuLoader {
    @Awake(LifeCycle.LOAD)
    fun load() {
        console().sendMessage("")
        console().sendLang("Plugin-Loading", Bukkit.getServer().version)
        console().sendMessage("")
    }
    @Awake(LifeCycle.ENABLE)
    fun enable() {
        console().sendLang("Plugin-Enabled")
    }
    @Awake(LifeCycle.DISABLE)
    fun disable() {
        console().sendLang("Plugin-Disable")
    }
}