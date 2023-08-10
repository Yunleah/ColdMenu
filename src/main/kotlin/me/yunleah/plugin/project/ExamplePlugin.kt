package me.yunleah.plugin.project

import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info

object ExamplePlugin : Plugin() {

    override fun onEnable() {
        info("Successfully running ExamplePlugin!")
    }
}