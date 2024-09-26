@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import net.brian.scriptedquests.utils.Utils

val npcIcon = IconFonts.getNPC(67);
val npcName = "§b● §e塔亞§r"
val side03 = "side_silver_03"
val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7我這裡的藥劑對傭兵可是非常有用的喔",
    "§7花點小錢，關鍵時刻可以救命",
    "§7有甚麼需要的嗎?",
))
    .addPlayerOptions(
        PlayerOption("&7[&b商店交易&7] &f我想買點東西")
            .setResult { player ->
                if (isDay(player)||player.hasPermission("npc.ignoretime"))
                    command("shop open 塔亞的藥劑店 ${player.name}");
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7抱歉，已經打烊了",
                        "§7我還要準備明日的藥劑",
                    ))
                }
            },

        PlayerOption("&7[&b釀造物品&7] &f我想釀造藥水")
            .setResult { player ->
                if (isDay(player)||player.hasPermission("npc.ignoretime"))
                    command("crafting station ${player.name} 塔亞釀造");
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7抱歉，已經打烊了",
                        "§7我還要準備明日的藥劑",
                    ))
                }
            },

        PlayerOption("&7[&a對話選項&7] &f沒事了")
            //.addCondition{it.hasPermission("")}
            .setResult { player ->
                if (isDay(player)||player.hasPermission("npc.ignoretime"))
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7有需要歡迎來找我喔",
                        "§7身為傭兵，藥劑可是很重要的夥伴",
                    ))
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7有需要藥劑歡迎明日光臨",
                    ))
                }
            }
    )
ScriptedQuests.getInstance().questManager.getQuest(side03).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§b銀牌支線§7] §f釀造素材",false)
        .addConditions( {player -> player.hasPermission("rank.lv2")} )
    )}
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(67,question1)