@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import net.brian.scriptedquests.utils.Utils

val npcIcon = IconFonts.getNPC(143);
val npcName = "§b● §e佰河§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
        "${npcName}:",
        "§7見到你真高興！",
))
        .addPlayerOptions(

                PlayerOption("&7[&b商店交易&7] &f我想看看有什麽飾品")
                        .setResult { player ->
                            if (isDay(player)||player.hasPermission("npc.ignoretime"))
                                command("shop open 佰河飾品店 ${player.name}");
                            else {
                                player.sendMessage(*Utils.getMessage(npcIcon,
                                        "${npcName}:",
                                        "§7深夜了",
                                        "§7早點回家吧！",
                                ))
                            }
                        },

                PlayerOption("&7[&b飾品委託&7] &f我想委托訂單")
                        .setResult { player ->
                            if (isDay(player)||player.hasPermission("npc.ignoretime"))
                                command("crafting station ${player.name} 佰河鍛造飾品");
                            else {
                                player.sendMessage(*Utils.getMessage(npcIcon,
                                        "${npcName}:",
                                        "§7深夜了",
                                        "§7早點回家吧！",
                                ))
                            }
                        },

                PlayerOption("&7[&a對話選項&7] &f沒事了")
                        //.addCondition{it.hasPermission("")}
                        .setResult { player ->
                            if (isDay(player)||player.hasPermission("npc.ignoretime"))
                                player.sendMessage(*Utils.getMessage(npcIcon,
                                        "${npcName}:",
                                        "§7記得再來哦",
                                ))
                            else {
                                player.sendMessage(*Utils.getMessage(npcIcon,
                                        "${npcName}:",
                                        "§7對不起",
                                        "§7下次早點來吧！",
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
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(143,question1)