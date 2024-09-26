@file:RequiredPlugins("ScriptedQuests")


import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player

val npcIcon = IconFonts.getNPC(203);
val npcName = "§b● §e阿麥菈§r"
val side01 = "side_gold_13"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
        "${npcName}:",
        "§7這裡的環境真糟糕呀...",
))
        .addPlayerOptions(

                PlayerOption("&7[&a對話選項&7] &f沒事了")
                        //.addCondition{it.hasPermission("")}
                        .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                                "${npcName}:",
                                "§7如果有線索",
                                "§7請務必第一時間提供給我",                                
                        ))},
                // 任務
                PlayerOption("§7[§b金牌支線§7] §f沙原部落的異常 &8(階級需求:黃金)")
                        .addConditions( {!it.hasPermission("rank.lv1")} )
                        .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")}
        )

ScriptedQuests.getInstance().questManager.getQuest(side01).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§b金牌支線§7] §f沙原部落的異常",false)
            .addConditions( {player->player.hasPermission("rank.lv3,side_gold_07")} )
    )}
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(203,question1)