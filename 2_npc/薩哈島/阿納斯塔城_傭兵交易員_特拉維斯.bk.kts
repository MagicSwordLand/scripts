@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import net.brian.scriptedquests.utils.Utils

val npcIcon = IconFonts.getNPC(172);
val npcName = "§b● §e特拉維斯§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7嘿！有什麼東西要賣嗎?",
    "§7我可以把那些東西買走喔！",
)).addPlayerOptions(

    PlayerOption("&7[&b販賣物品&7] &f我想賣點東西")
        .setResult { player ->command("mmoshop rpg ${player.name}")},
    PlayerOption("&7[&a對話選項&7] &f暫時還沒有")
        .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
            "${npcName}:",
            "§7沒有關係！",
            "§7有任何要賣的都可以找我喔！",
        ))}
)

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}

ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(172,question1)