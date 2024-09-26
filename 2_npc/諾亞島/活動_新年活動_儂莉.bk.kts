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

val linIcon = IconFonts.getNPC(29);
private val linNPC = "§b● §e儂莉§r:";

val startCh1Q = NPCQuestion(*Utils.getMessage(linIcon,
    "${linNPC}:",
    "§7呀~",
    "§7快把字卡兔出來!",
    "§7讓本小姐狠狠地砸你豐富的獎勵<3",
))
    .addPlayerOptions(
        PlayerOption("&7[&b活動兌換&7] &f我要兌換新年物品！")
            .setResult { player ->
                command("crafting station ${player.name} 諾亞鎮_儂莉")
            },
    )
fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}


ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(260,startCh1Q)