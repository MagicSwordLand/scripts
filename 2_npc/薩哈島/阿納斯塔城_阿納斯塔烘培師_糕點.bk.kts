@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import net.brian.scriptedquests.utils.Utils
import net.brian.scriptedquests.utils.Utils.isDay
import org.bukkit.Bukkit
import org.bukkit.entity.Player

val npcName = "§b● §e糕斯§r"
val npcIcon = IconFonts.getNPC(184);

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,"${npcName}:","§7有什麼需要嗎?","§7我製作的食物可以非常美味的！"))
    .addPlayerOptions(
        PlayerOption("&7[&b商店交易&7] &f我想買點東西")
            .setResult { player -> command("shop open 糕斯的烘琣店 ${player.name}") },
        PlayerOption("&7[&b製作委託&7] &f我想委托訂單")
            .setResult { player -> command("crafting station ${player.name} 糕斯烘培")},
        PlayerOption("&7[&a對話選項&7] &f我還不餓")
            .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                "${npcName}:",
                "§7肚子餓了就來找我吧！",
            ))}
    )
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}


ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(184,question1)