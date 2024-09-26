@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import net.brian.scriptedquests.utils.Utils

val npcIcon = IconFonts.getNPC(246);
val npcName = "§b● §e雷克倫§r"
val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7親愛的冒險家?",
    "§7來看看這裡的水晶工藝吧~",
))
    .addPlayerOptions(
        PlayerOption("&7[&b商店交易&7] &f我想買點東西")
            .setResult { player ->
                if (isDay(player)||player.hasPermission("npc.ignoretime"))
                    command("shop open 雷克倫的飾品店 ${player.name}");
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7打烊了喔",
                        "§7不然可要遭賊了~",
                    ))
                }
            },

        PlayerOption("&7[&b製作飾品&7] &f我想製作飾品")
            .setResult { player ->
                if (isDay(player)||player.hasPermission("npc.ignoretime"))
                    command("crafting station ${player.name} 雷克倫鍛造飾品");
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7打烊了喔",
                        "§7不然可要遭賊了~",
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
                    ))
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7明天再來吧！",
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
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(246,question1)