@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.conditions.CanDoQuestCondition
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.entity.Player
import net.brian.scriptedquests.utils.Utils

val npcIcon = IconFonts.getNPC(55);
val npcName = "§b● §e赫巴斯§r"

val side03 = "side_copper_03"
val side04 = "side_copper_04"
val other = "other_hs"
val otherpetcopper1 = "other_copper_pet_01"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7幹嘛?",
))
    .addPlayerOptions(
        PlayerOption("&7[&a對話選項&7] &f痾...沒事")
            .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                "§b● §e赫巴斯:",
                "§7那就去鍛鍊自己！",
            ))}
        ,
        PlayerOption("§7[§d特殊委託§7] §f天賦技能 &8(職業等級不足,需求等級:15)")
            .addConditions ({ Utils.getDouble(it, "%class_level%") <= 15 })
            .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")})


ScriptedQuests.getInstance().questManager.getQuest(side03).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§b銅牌支線§7] §f礦區歷練裡的金生金",false)
        .addConditions( {player->player.hasPermission("rank.lv1")} )
    )}
ScriptedQuests.getInstance().questManager.getQuest(side04).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§b銅牌支線§7] §f稻草的訂單",false)
        .addConditions( {player->player.hasPermission("rank.lv1")} )
    )}
ScriptedQuests.getInstance().questManager.getQuest(other).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§d特殊委託§7] §f天賦技能",false)
        .addConditions ({ player-> Utils.getDouble(player, "%class_level%") >= 15 })
    )}
ScriptedQuests.getInstance().questManager.getQuest(otherpetcopper1).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§d特殊委託§7] §f忠實的夥伴",false)
        .addConditions( {player->player.hasPermission("rank.lv1")} )
    )}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(55,question1)