package me.yunleah.plugin.coldmenu.internal.command.subcommand

import me.yunleah.plugin.coldmenu.internal.manager.HookerManager
import me.yunleah.plugin.coldmenu.internal.script.CompiledScript
import org.bukkit.command.CommandSender
import taboolib.common.platform.command.subCommand
import taboolib.platform.util.sendLang

/**
 * ColdMenu
 * me.yunleah.plugin.coldmenu.internal.command.subcommand
 *
 * @author Yunleah763
 * @since 2023/8/10 21:15
 */
object Dev {
    val dev = subCommand {
        literal("runJS", optional = true) {
            execute<CommandSender> { sender, _, content ->
                CompiledScript(content)
            }
        }
    }
}