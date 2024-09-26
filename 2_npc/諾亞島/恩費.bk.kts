@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import net.brian.scriptedquests.utils.Utils

val npcIcon = IconFonts.getNPC(57);
val npcName = "§b● §e恩費§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
        "${npcName}:",
        "§7有什麼新奇的東西嗎?",
        "§7我這裡可以收購各種東西喔！",
)).addPlayerOptions(

        PlayerOption("&7[&b販賣物品&7] &f我想賣點東西")
                .setResult { player ->command("mmoshop rpg ${player.name}")},
        PlayerOption("&7[&a對話選項&7] &f目前沒有什麼要賣的")
                .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7有需要販賣素材",
                        "§7歡迎隨時來找我喔",
                ))}
)

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}

ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(57,question1)