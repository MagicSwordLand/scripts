@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player

val npcIcon = IconFonts.getNPC(89);
val npcName = "§b● §e薩莫§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7喲兄弟",
    "§7我這裏有上好的礦鎬噢！",
))
    .addPlayerOptions(

        PlayerOption("&7[&b商店交易&7] &f我想購買工具")
            .setResult { player ->
                if (isDay(player)||player.hasPermission("npc.ignoretime"))
                    command("shop open 諾亞薩莫礦鎬商人 ${player.name}");
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7對不起",
                        "§7收工了",
                    ))
                }
            },
            

        PlayerOption("&7[&b製作委托&7] &f我想製作工具")
            .setResult { player ->
                if (isDay(player)||player.hasPermission("npc.ignoretime"))
                    command("crafting station ${player.name} 薩莫製作工具");
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7對不起",
                        "§7收工了",
                    ))
                }
            },            

        PlayerOption("&7[&a對話選項&7] &f沒事了")
            //.addCondition{it.hasPermission("")}
            .setResult { player ->
                if (isDay(player)||player.hasPermission("npc.ignoretime"))
                    player.sendMessage("$npcName: 嗯...！")
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7對不起",
                        "§7收工了",
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
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(149,question1)