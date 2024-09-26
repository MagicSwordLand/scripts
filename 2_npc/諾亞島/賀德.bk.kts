@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import net.brian.scriptedquests.utils.Utils

val npcIcon = IconFonts.getNPC(15);

val npcName = "§b● §e賀德§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7嘿！",
    "§7想去哪阿?",
))
    .addPlayerOptions(
        PlayerOption("&7[&b傳送移動&7] &f我想去古樹鎮")
            .setResult { player ->
                if (isDay(player)||player.hasPermission("npc.ignoretime"))
                    command("cmi warp introtown ${player.name}")
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7夜深了",
                        "§7黎明再來吧！",
                    ))
                }
            },
        PlayerOption("&7[&b商店交易&7] &f我想購買船票")
            .setResult { player ->
                if (isDay(player)||player.hasPermission("npc.ignoretime"))
                    command("crafting station ${player.name} 賀德")
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7夜深了",
                        "§7黎明再來吧！",
                    ))
                }
            },
        PlayerOption("&7[&a對話選項&7] &f沒事了")
            //.addCondition{it.hasPermission("")}
            .setResult { player ->
                if (isDay(player)||player.hasPermission("npc.ignoretime"))
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7想去哪記得來找我喔",
                    ))
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7夜深了",
                        "§7黎明再來吧！",
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


ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(15,question1)