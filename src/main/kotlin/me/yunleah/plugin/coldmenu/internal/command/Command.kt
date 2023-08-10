package me.yunleah.plugin.coldmenu.internal.command

import me.yunleah.plugin.coldmenu.internal.command.subcommand.Dev
import org.bukkit.command.CommandSender
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.command.subCommand
import taboolib.common.platform.function.pluginVersion
import taboolib.expansion.createHelper
import taboolib.platform.util.sendLang

/**
 * ColdMenu
 * me.yunleah.plugin.coldmenu.internal.command
 *
 * @author Yunleah763
 * @since 2023/8/10 20:51
 */
@CommandHeader(
        name = "ColdMenu",
        aliases = ["cm", "menu"],
        description = "ColdMenu Main Command"
)
object Command {
    @CommandBody
    val main = mainCommand { createHelper() }
    @CommandBody
    val help = subCommand { createHelper() }
    @CommandBody
    val ver = subCommand {
        execute<CommandSender> { sender, _, _ ->
            sender.sendLang("Command-Ver", pluginVersion)
        }
    }
    @CommandBody
    val dev = Dev.dev
}