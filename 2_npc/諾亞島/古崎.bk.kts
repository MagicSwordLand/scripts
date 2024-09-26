@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player

val npcIcon = IconFonts.getNPC(127);
val npcName = "§b● §e古崎§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7這位勇敢的傭兵",
    "§7你有古銀嗎?",
    "§7我這可是有很多新奇的東西！",
))
    .addPlayerOptions(
        PlayerOption("&7[&b商店交易&7] &f我想用古銀換點東西")
            .setResult { player ->
                if (isDay(player)||player.hasPermission("npc.ignoretime"))
                    command("shop open 贊助_古銀 ${player.name}");
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7抱歉欸",
                        "§7這麼晚我怕遭賊",
                        "§7明天早上再來吧！",
                    ))
                }
            },

        PlayerOption("&7[&b製造物品&7] &f我想製造古銀")
            .setResult { player ->
                if (isDay(player)||player.hasPermission("npc.ignoretime"))
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7那個...",
                        "§7諾亞鎮可是不允許製造古銀的",
                    ))
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7抱歉欸",
                        "§7這麼晚我怕遭賊",
                        "§7明天早上再來吧！",
                    ))
                }
            },

        PlayerOption("&7[&a對話選項&7] &f沒事了")
            //.addCondition{it.hasPermission("")}
            .setResult { player ->
                if (isDay(player)||player.hasPermission("npc.ignoretime"))
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7有古銀歡迎隨時找我喔",
                        "§7我這邊的東西可等著你來買！",
                    ))
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7那趕快走吧",
                        "§7等等別人還以為我在做生意",
                    ))
                }
            }
    )
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(127,question1)