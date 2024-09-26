@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player

val npcIcon = IconFonts.getNPC(231);
val npcName = "§b● §e莫雷§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7身上的雜物太多了...",
    "§7賣掉一點好了",
    "§7恩?怎麼了嗎?",
))
    .addPlayerOptions(
        PlayerOption("&7[&a對話選項&7] &f痾痾...沒事")
            //.addCondition{it.hasPermission("")}
            .setResult { player ->
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7真是奇怪的人",
                    ))
            }
    )

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(231,question1)