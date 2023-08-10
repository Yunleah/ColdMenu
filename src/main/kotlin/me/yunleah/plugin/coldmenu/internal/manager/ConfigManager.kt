package me.yunleah.plugin.coldmenu.internal.manager

import me.yunleah.plugin.coldmenu.ColdMenu.plugin
import me.yunleah.plugin.coldmenu.util.ConfigUtils.getFileOrNull
import me.yunleah.plugin.coldmenu.util.ConfigUtils.saveResourceNotWarn
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.Platform
import taboolib.common.platform.function.console
import taboolib.module.lang.sendLang
import taboolib.module.metrics.Metrics
import java.io.File
import java.io.InputStreamReader

/**
 * ColdMenu
 * me.yunleah.plugin.coldmenu.internal.manager
 *
 * @author Yunleah763
 * @since 2023/8/10 20:29
 */
/**
 * 配置文件管理器, 用于管理config.yml文件, 对其中缺少的配置项进行主动补全, 同时释放默认配置文件
 */
object ConfigManager {
    /**
     * 获取默认Config
     */
    private val originConfig: FileConfiguration =
            plugin.getResource("setting.yml")?.let {
                val reader = InputStreamReader(it, "UTF-8")
                val config = YamlConfiguration.loadConfiguration(reader)
                reader.close()
                config
            } ?: YamlConfiguration()

    /**
     * 获取配置文件
     */
    private val config get() = plugin.config

    private var debug = config.getBoolean("Options.Debug", false)

    /**
     * 加载默认配置文件
     */
    @Awake(LifeCycle.INIT)
    fun saveResource() {
        if (getFileOrNull("Scripts") == null) {
            plugin.saveResourceNotWarn("Scripts${File.separator}ExampleScript.js")
            plugin.saveResourceNotWarn("Scripts${File.separator}ItemTime.js")
        }
        plugin.saveDefaultConfig()
        // 加载bstats
        // Metrics(15750, plugin.description.version, Platform.BUKKIT)
    }

    /**
     * 对当前Config查缺补漏
     */
    @Awake(LifeCycle.LOAD)
    fun loadConfig() {
        originConfig.getKeys(true).forEach { key ->
            if (!plugin.config.contains(key)) {
                plugin.config.set(key, originConfig.get(key))
            } else {
                val completeValue = originConfig.get(key)
                val value = plugin.config.get(key)
                if (completeValue is ConfigurationSection && value !is ConfigurationSection) {
                    plugin.config.set(key, completeValue)
                } else {
                    plugin.config.set(key, value)
                }
            }
        }
        plugin.saveConfig()
    }

    /**
     * 重载配置管理器
     */
    fun reload() {
        plugin.reloadConfig()
        loadConfig()
        debug = config.getBoolean("Options.Debug", false)
    }

    fun debug(text: String) {
        if (debug) {
            console().sendLang("Plugin-Debug", text)
        }
    }
}