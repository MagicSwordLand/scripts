@file:RequiredPlugins("ScriptedQuests")


import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player

val npcIcon = IconFonts.getNPC(129);
val npcName = "§b● §e亞里士德§r"
val side07 = "side_copper_07"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7為甚麼這個世界有那麼多怪物..",
    "§7這位傭兵有什麼事情嗎?",
))
    .addPlayerOptions(

        PlayerOption("&7[&a對話選項&7] &f沒有..")
            //.addCondition{it.hasPermission("")}
            .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                "${npcName}:",
                "§7...?",
                "§7真是奇怪的人",
            ))},
        // 任務
        PlayerOption("§7[§b銅牌支線§7] §f學者的困惑 &8(等級需求:8)")
            .addConditions( {it.hasPermission("rank.lv1")} )
            .addConditions ({  Utils.getDouble(it,"%class_level%") < 8 })
            .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")}
    )

ScriptedQuests.getInstance().questManager.getQuest(side07).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§b銅牌支線§7] §f學者的困惑",false)
        .addConditions( {player->player.hasPermission("rank.lv1")} )
        .addConditions( { player-> Utils.getDouble(player, "%class_level%") >= 8})
    )}
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(129,question1)