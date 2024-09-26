@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils

val npcIcon = IconFonts.getNPC(110);
val npcName = "§b● §e墨里斯§r"
val side05 = "side_silver_05"
val week02 = "week_silver_02"
val daily07 = "daily_silver_07"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7傭兵你好",
    "§7有什麼事情嗎?",
))
    .addPlayerOptions(
        PlayerOption("§7[§b銀牌支線§7] §f被搶走的商會貨物 &8(等級需求:12")
            .addConditions(
                {it.hasPermission("rank.lv2")},
                { Utils.getDouble(it,"%class_level%") < 12 }
            )
            .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")},
        PlayerOption("&7[&a對話選項&7] &f沒事"),
    )

ScriptedQuests.getInstance().questManager.getQuest(side05).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§b銀牌支線§7] §f被搶走的商會貨物",false)
        .addConditions( {player-> player.hasPermission("rank.lv2")} )
        .addConditions ({player-> Utils.getDouble(player, "%class_level%") >= 12 })
    )}
ScriptedQuests.getInstance().questManager.getQuest(week02).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§e銀牌每周§7] §f學不乖的盜賊",false)
        .addConditions( { player-> player.hasPermission("wolf_chamber")} )
    )}
ScriptedQuests.getInstance().questManager.getQuest(daily07).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§a銀牌每日§7] §f商會庫存",false)
        .addConditions( {player->player.hasPermission("wolf_chamber")} )
    )}
fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(110,question1)