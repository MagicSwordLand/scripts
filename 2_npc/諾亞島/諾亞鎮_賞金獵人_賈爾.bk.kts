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

val npcIcon = IconFonts.getNPC(29);
val npcName = "§b● §e亞里士德§r"
val side09 = "daily_copper_09"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7賞金獵人不好當阿...",
))
    .addPlayerOptions(

        PlayerOption("&7[&a對話選項&7] &f(走掉)")
    )

ScriptedQuests.getInstance().questManager.getQuest(side09).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§a銅牌每日§7] §f缺錢的賈爾",false)
    )}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(109,question1)