@file:RequiredPlugins("ScriptedQuests")


import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player

val npcIcon = IconFonts.getNPC(186);
val npcName = "§b● §e海碧§r"
val side01 = "side_gold_10"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
        "${npcName}:",
        "§7關於花的秘密",
        "§7你想知道哪一個？",        
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
                                command("crafting station ${player.name} 花妖鍛造符石");
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
                        .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                                "${npcName}:",
                                "§7這裡隨時歡迎你~",
                        ))},
                // 任務
                PlayerOption("§7[§b金牌支線§7] §f海碧的奇怪咒術 &8(階級需求:黃金)")
                        .addConditions( {!it.hasPermission("rank.lv3")} )
                        .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")}
        )

ScriptedQuests.getInstance().questManager.getQuest(side01).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§b金牌支線§7] §f海碧的奇怪咒術",false)
            .addConditions( {player->player.hasPermission("rank.lv3")} )
    )}
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(186,question1)