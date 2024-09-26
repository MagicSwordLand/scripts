@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player
import java.lang.Compiler.command

val npcIcon = IconFonts.getNPC(115);
val npcName = "§b● §e海斯§r"
val side08 = "side_copper_08"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7這位傭兵",
    "§7有什麼事情嗎?",
))
    .addPlayerOptions(

        PlayerOption("&7[&a對話選項&7] &f沒事")
            //.addCondition{it.hasPermission("")}
            .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                "${npcName}:",
                "§7好的",
            ))},
        // 任務
        PlayerOption("§7[§b銅牌支線§7] §f無法航行的區域 &8(等級需求:3)")
            .addConditions( {it.hasPermission("rank.lv1")} )
            .addConditions({  Utils.getDouble(it,"%class_level%") < 3 })
            .setResult { player-> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")}
    )

ScriptedQuests.getInstance().questManager.getQuest(side08).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§b銅牌支線§7] §f無法航行的區域",false)
        .addConditions( {it.hasPermission("rank.lv1")} )
        .addConditions( { Utils.getDouble(it, "%class_level%") >= 3})
    )}
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(115,question1)