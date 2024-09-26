@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.entity.Player

val npcName = "§b● §e麵寶§r"
val npcIcon = IconFonts.getNPC(66);
val side09 = "side_copper_09"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,"${npcName}:","§7有什麼需要嗎?"))
    .addPlayerOptions(
        PlayerOption("&7[&b商店交易&7] &f我想買點東西")
            .setResult { player -> command("shop open 諾雅的烘焙店 ${player.name}") },
        PlayerOption("&7[&b製作委託&7] &f我想委托訂單")
            .setResult { player -> command("crafting station ${player.name} 麵寳烘培")},
        PlayerOption("&7[&a對話選項&7] &f沒事了")
            //.addCondition{it.hasPermission("")}
            .setResult { player ->player.sendMessage(*Utils.getMessage(npcIcon,"§b● §e麵寶§r","§7想吃甚麼可以跟我買喔")) }
    )
ScriptedQuests.getInstance().questManager.getQuest(side09).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§b銅牌支線§7] §f夏日新商品",false)
        .addConditions( {player->player.hasPermission("rank.lv1")} )
    )}
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}


ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(66,question1)