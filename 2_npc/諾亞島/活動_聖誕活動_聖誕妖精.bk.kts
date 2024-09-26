@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.conditions.CanDoQuestCondition
import net.brian.scriptedquests.api.conditions.FinishedQuestsCondition
import net.brian.scriptedquests.api.conditions.IsDoingQuestCondition
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.entity.Player
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit

val linIcon = IconFonts.getNPC(254);
val activity01 = "daily_activity_01"
val activity02 = "daily_activity_02"
val activity03 = "daily_activity_03"
val activity04 = "daily_activity_04"
private val linNPC = "§b● §e聖誕妖精§r:";

val startCh1Q = NPCQuestion(*Utils.getMessage(linIcon,
    "${linNPC}:",
    "§7叮叮噹~叮叮噹~",
    "§7又到了我最喜歡的日子呢！",
    "§7冷冷的風真是舒服",
))
    .addPlayerOptions(
        PlayerOption("&7[&b活動兌換&7] &f我要兌換聖誕物品！")
            .setResult { player ->
                command("crafting station ${player.name} 諾亞鎮_聖誕精靈")
            },
    )
ScriptedQuests.getInstance().questManager.getQuest(activity01).ifPresent {
    startCh1Q.addPlayerOptions(it.getStartOption("§7[§d活動每日§7] §f遺失的聖誕麋鹿",false))
}
ScriptedQuests.getInstance().questManager.getQuest(activity02).ifPresent {
    startCh1Q.addPlayerOptions(it.getStartOption("§7[§d活動每日§7] §f美麗的聖誕花束",false))
}
ScriptedQuests.getInstance().questManager.getQuest(activity04).ifPresent {
    startCh1Q.addPlayerOptions(it.getStartOption("§7[§d活動每日§7] §f神聖的麋鹿氣息",false))
}
ScriptedQuests.getInstance().questManager.getQuest(activity03).ifPresent {
    startCh1Q.addPlayerOptions(it.getStartOption("§7[§d活動每日§7] §f聖誕裝飾",false))
}
fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}


ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(254,startCh1Q)