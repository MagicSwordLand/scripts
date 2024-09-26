@file:RequiredPlugins("ScriptedQuests","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.utils.Utils


val name = "§c● §e明諾克德§r:"
val icon = IconFonts.getNPC(14);

val houseQuestID = "other_no_house"

val npcQuestion = NPCQuestion(*Utils.getMessage(icon,name,"傭兵有甚麼事情嗎？"))

ScriptedQuests.getInstance().questManager.getQuest(houseQuestID).ifPresent {
    npcQuestion.addPlayerOptions(it.getStartOption("好啊!",false))
}

ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(14,npcQuestion);