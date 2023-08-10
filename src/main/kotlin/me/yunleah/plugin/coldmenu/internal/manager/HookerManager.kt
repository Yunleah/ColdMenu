package me.yunleah.plugin.coldmenu.internal.manager

import me.yunleah.plugin.coldmenu.internal.hook.nashorn.NashornHooker
import me.yunleah.plugin.coldmenu.internal.hook.nashorn.impl.LegacyNashornHookerImpl
import me.yunleah.plugin.coldmenu.internal.hook.nashorn.impl.NashornHookerImpl

/**
 * ColdMenu
 * me.yunleah.plugin.coldmenu.internal.manager
 *
 * @author Yunleah763
 * @since 2023/8/10 18:11
 */
/**
 * 插件兼容管理器, 用于尝试与各个软依赖插件取得联系
 */
object HookerManager {
    private fun check(clazz: String): Boolean {
        return try {
            Class.forName(clazz)
            true
        } catch (error: Throwable) {
            false
        }
    }

    val nashornHooker: NashornHooker =
            when {
                // jdk自带nashorn
                check("jdk.nashorn.api.scripting.NashornScriptEngineFactory") -> LegacyNashornHookerImpl()
                // 主动下载nashorn
                else -> NashornHookerImpl()
            }

}