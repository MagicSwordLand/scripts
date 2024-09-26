@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import net.brian.scriptedquests.utils.Utils

val npcIcon = IconFonts.getNPC(183);
val npcName = "§b● §e魯卡§r"
val side01 = "side_gold_12"
val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
        "${npcName}:",
        "§7嗨！需要點甚麼嗎?",
        "§7我的藥劑可是非常厲害的！",
))
        .addPlayerOptions(
                PlayerOption("&7[&b商店交易&7] &f我想買點東西")
                        .setResult { player ->
                            if (isDay(player)||player.hasPermission("npc.ignoretime"))
                                command("shop open 魯卡的藥劑店 ${player.name}");
                            else {
                                player.sendMessage(*Utils.getMessage(npcIcon,
                                        "${npcName}:",
                                        "§7打烊了喔",
                                        "§7藥劑都賣得差不多了",
                                ))
                            }
                        },

                PlayerOption("&7[&b釀造物品&7] &f我想釀造藥水")
                        .setResult { player ->
                            if (isDay(player)||player.hasPermission("npc.ignoretime"))
                                command("crafting station ${player.name} 魯卡釀造");
                            else {
                                player.sendMessage(*Utils.getMessage(npcIcon,
                                        "${npcName}:",
                                        "§7打烊了喔",
                                        "§7藥劑都賣得差不多了",
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
                        },

                // 任務
                PlayerOption("§7[§b金牌支線§7] §f魯卡的愛情煩惱 &8(階級需求:黃金)")
                        .addConditions( {!it.hasPermission("rank.lv3")} )
                        .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")}
        )

ScriptedQuests.getInstance().questManager.getQuest(side01).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§b金牌支線§7] §f魯卡的愛情煩惱",false)
            .addConditions( {player->player.hasPermission("rank.lv3")} )
    )}


fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(183,question1)