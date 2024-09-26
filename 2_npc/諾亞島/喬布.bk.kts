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

val npcIcon = IconFonts.getNPC(95);
val npcName = "§b● §e喬布§r"
val side06 = "side_copper_06"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7有什麼能夠幫忙的嗎?",
))
    .addPlayerOptions(

        PlayerOption("&7[&b傭兵倉庫&7] &f我想拿(放)點東西")
            .setResult { player ->
                command("storage openfor ${player.name}")},

        PlayerOption("&7[&a對話選項&7] &f目前沒有..")
            //.addCondition{it.hasPermission("")}
            .setResult { player -> player.sendMessage("$npcName: §7有需要幫忙歡迎來找我")},
        // 任務
        PlayerOption("§7[§b銅牌支線§7] §f喬布的煩惱 &8(等級需求:4)")
            .addConditions ( {it.hasPermission("rank.lv1")} )
            .addConditions ({  Utils.getDouble(it,"%class_level%") < 4 })
            .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")}
    )

ScriptedQuests.getInstance().questManager.getQuest(side06).ifPresent { it ->
    question1.addPlayerOptions(it.getStartOption("§7[§b銅牌支線§7] §f喬布的煩惱",false)
        .addConditions(
            {player->player.hasPermission("rank.lv1")},
            { player->Utils.getDouble(player, "%class_level%") >= 4}
        )
    )}
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(95,question1)