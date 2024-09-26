@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import net.brian.scriptedquests.utils.Utils

val npcIcon = IconFonts.getNPC(145);
val npcName = "§b● §e咒司§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
        "${npcName}:",
        "§7符石的黯然，能力的崛起",
        "§7需要些什麽呢？",        
))
        .addPlayerOptions(

                PlayerOption("&7[&b商店交易&7] &f我想看看有什麽符石")
                        .setResult { player ->
                            if (isDay(player)||player.hasPermission("npc.ignoretime"))
                                command("shop open 諾亞鎮咒司符石 ${player.name}");
                            else {
                                player.sendMessage(*Utils.getMessage(npcIcon,
                                        "${npcName}:",
                                        "§7下次早點來吧",
                                        "§7符石不適合在晚上出售！",
                                ))
                            }
                        },

                PlayerOption("&7[&b符石委託&7] &f我想委托訂單")
                        .setResult { player ->
                            if (isDay(player)||player.hasPermission("npc.ignoretime"))
                                command("crafting station ${player.name} 咒司鑄造符石");
                            else {
                                player.sendMessage(*Utils.getMessage(npcIcon,
                                        "${npcName}:",
                                        "§7下次早點來吧",
                                        "§7我東西已經收拾好了！",
                                ))
                            }
                        },

                PlayerOption("&7[&a對話選項&7] &f沒事了")
                        //.addCondition{it.hasPermission("")}
                        .setResult { player ->
                            if (isDay(player)||player.hasPermission("npc.ignoretime"))
                                player.sendMessage(*Utils.getMessage(npcIcon,
                                        "${npcName}:",
                                        "§7好的",
                                ))
                            else {
                                player.sendMessage(*Utils.getMessage(npcIcon,
                                        "${npcName}:",
                                        "§7早點休息吧",
                                        "§7我累了",
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
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(145,question1)