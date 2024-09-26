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

val npcIcon = IconFonts.getNPC(173);
val npcName = "§b● §e泰斯米§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7有什麼能夠幫忙的嗎?",
))
    .addPlayerOptions(

        PlayerOption("&7[&b傭兵倉庫&7] &f我想拿(放)點東西")
            .setResult { player ->
                command("storage openfor ${player.name}")},

        PlayerOption("&7[&a對話選項&7] &f目前沒有")
            .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                "${npcName}:",
                "§7有需要幫忙歡迎來找我",
            ))}
    )

fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(173,question1)