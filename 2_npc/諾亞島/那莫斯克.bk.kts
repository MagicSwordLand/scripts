@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.conditions.CanDoQuestCondition
import net.brian.scriptedquests.api.conditions.FinishedQuestsCondition
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.entity.Player
import net.brian.scriptedquests.utils.Utils

val npcIcon = IconFonts.getNPC(4);
private val npcName = "§c● §6[黃金傭兵] §e那莫斯克:";
val sidesilver02 = "side_silver_02"

val startCh1Q = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7找我嗎?",
))
    .addPlayerOptions(
        PlayerOption("&7[&a對話選項&7] &f沒有..")
            .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                "§b● §6[黃金傭兵] §e那莫斯克:",
                "§7那我繼續聊天",
            ))},
        PlayerOption("§7[§b銀牌支線§7] §f石像鬼的侵擾 &8(等級需求:15)")
            .addConditions( {it.hasPermission("rank.lv2")} )
            .addConditions ({  Utils.getDouble(it,"%class_level%") < 15 })
            .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")}
    )

ScriptedQuests.getInstance().questManager.getQuest(sidesilver02).ifPresent {
    startCh1Q.addPlayerOptions(it.getStartOption("§7[§b銅牌支線§7] §f石像鬼的侵擾",false)
        .addConditions( {player->player.hasPermission("rank.lv2")} )
        .addConditions( {player-> Utils.getDouble(player, "%class_level%") >= 15})
    )}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(4,startCh1Q);