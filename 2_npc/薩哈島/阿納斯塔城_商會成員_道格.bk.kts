@file:RequiredPlugins("ScriptedQuests")


import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player

val npcIcon = IconFonts.getNPC(144);
val npcName = "§b● §e道格§r"
val side01 = "side_gold_06"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7完蛋了...",       
))
    .addPlayerOptions(

        PlayerOption("&7[&a對話選項&7] &f你還好嗎？")
            //.addCondition{it.hasPermission("")}
            .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                "${npcName}:",
                "§7我不好！",
            ))},
        // 任務
        PlayerOption("§7[§b金牌支線§7] §f薩哈商圈的師爺 &8(階級需求:黃金)")
            .addConditions( {!it.hasPermission("rank.lv1")} )
            .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")}
    )

ScriptedQuests.getInstance().questManager.getQuest(side01).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§b金牌支線§7] §f薩哈商圈的師爺",false)
        .addConditions( {player->player.hasPermission("rank.lv3")} )
    )}
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() > 16000 || player.getWorld().getTime() < 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(245,question1)