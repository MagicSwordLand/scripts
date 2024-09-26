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

val npcIcon = IconFonts.getNPC(97);
val npcName = "§b● §e尤爾達§r"
val side_silver01 = "side_silver_01"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7有什麼事情?",
))
    .addPlayerOptions(

        PlayerOption("&7[&a對話選項&7] &f痾...")
            //.addCondition{it.hasPermission("")}
            .setResult { player-> player.sendMessage(*Utils.getMessage(npcIcon,
                "${npcName}:",
                "§7...?",
            ))},
        // 任務
        PlayerOption("§7[§b銅牌支線§7] §f學者的困惑 &8(等級需求:8)")
            .addConditions(
                {it.hasPermission("rank.lv2")},
                {  Utils.getDouble(it,"%class_level%") < 8 })
            .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")}
    )

ScriptedQuests.getInstance().questManager.getQuest(side_silver01).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§b銀牌支線§7] §f行商幫手",false)
        .addConditions( {player->player.hasPermission("rank.lv2")},
            { player->Utils.getDouble(player, "%class_level%") >= 8})
    )}
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(97,question1)