@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import net.brian.scriptedquests.utils.Utils

val npcIcon = IconFonts.getNPC(30);
val npcName = "§b● §e盧克§r"
val daily10 = "daily_copper_10"
val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7需要什麼嗎?",
))
    .addPlayerOptions(

        PlayerOption("&7[&b商店交易&7] &f我想看看有什麽商品")
            .setResult { player ->
                if (isDay(player)||player.hasPermission("npc.ignoretime"))
                    command("shop open 盧克雜貨店 ${player.name}");
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7對不起",
                        "§7下次早點來吧！",
                    ))
                }
            },

        PlayerOption("&7[&b製作委託&7] &f我想委托訂單")
            .setResult { player ->
                if (isDay(player)||player.hasPermission("npc.ignoretime"))
                    command("crafting station ${player.name} 盧克雜貨店");
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7對不起",
                        "§7下次早點來吧！",
                    ))
                }
            },

        PlayerOption("&7[&a對話選項&7] &f沒事了")
            //.addCondition{it.hasPermission("")}
            .setResult { player->
                if (isDay(player)||player.hasPermission("npc.ignoretime"))
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7歡迎下次光臨",
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
ScriptedQuests.getInstance().questManager.getQuest(daily10).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§a銅牌每日§7] §f盧克的困擾",false)
    )}
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(84,question1)