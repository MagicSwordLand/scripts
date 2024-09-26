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
import org.bukkit.Bukkit

val npcIcon = IconFonts.getNPC(59);
private val npcName = "§b● &7[委託指引員] §e梅里:&x&8&B&6&7&3&A";

val side01 = "side_copper_01"
val side02 = "side_copper_02"

fun startCh1(player: Player){
    ScriptedQuests.getInstance().questManager.getQuest("main-ch1-2")
        .ifPresent { it.startQuest(player) }
}

val startCh1Q = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7這位傭兵",
    "§7有什麼事情嗎?",
))
    .addPlayerOptions(
        PlayerOption("&7[&a對話選項&7] &f沒有..")
            .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                "§c● §7[委託指引員] §e梅里:",
                "§7有需要幫忙歡迎來找我",
            ))},
        PlayerOption("§7[§b銅牌支線§7] §f盜賊據點 &8(職業等級不足,需求等級:8)")
            .addConditions ({ Utils.getDouble(it, "%class_level%") <= 7 })
            .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")},
        PlayerOption("§7[§b銀牌支線§7] §f艾靈長廊中的巨龍 &8(職業等級不足,需求等級:18)")
            .addConditions ({ Utils.getDouble(it, "%class_level%") <= 17 })
            .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")}
    )



ScriptedQuests.getInstance().questManager.getQuest(side01).ifPresent {
    startCh1Q.addPlayerOptions(it.getStartOption("§7[§b銅牌支線§7] §f盜賊據點",false)
        .addConditions ({ player-> Utils.getDouble(player, "%class_level%") >= 8 })
    )}
ScriptedQuests.getInstance().questManager.getQuest(side02).ifPresent {
    startCh1Q.addPlayerOptions(it.getStartOption("§7[§b銀牌支線§7] §f艾靈長廊中的巨龍",false)
        .addConditions ({ player -> Utils.getDouble(player, "%class_level%") >= 18})
    )}

ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(59,startCh1Q);