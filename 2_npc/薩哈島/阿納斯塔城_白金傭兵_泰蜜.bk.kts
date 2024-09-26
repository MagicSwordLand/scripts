@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player

val npcIcon = IconFonts.getNPC(232);
val npcName = "§b● §e泰蜜§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7欸，威克斯！",
    "§7都沒有困難一點的委託嗎?",
    "§7嘖，看甚麼看?",
))
    .addPlayerOptions(
        PlayerOption("&7[&a對話選項&7] &f阿...抱歉")
            //.addCondition{it.hasPermission("")}
            .setResult { player ->
                player.sendMessage(*Utils.getMessage(npcIcon,
                    "${npcName}:",
                    "§7沒看過美女嗎?",
                    "§7再看把你眼睛挖掉！",
                ))
            }
    )

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(232,question1)