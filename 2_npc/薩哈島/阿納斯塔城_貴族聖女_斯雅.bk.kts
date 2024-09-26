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

val npcIcon = IconFonts.getNPC(94);
val npcName = "§b● §e斯雅§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7尊敬的傭兵閣下",
    "§7有甚麼是我能幫忙的嗎?",
))
    .addPlayerOptions(

        PlayerOption("&7[&b傳送選項&7] &f傳送到諾亞鎮")
            .setResult { player ->
                command("execute in minecraft:2k run tp  ${player.name} -334.50 56.00 290.50 0 0")
                command("sound entity_enderman_teleport ${player.name} -v:1 -p:0.55")},


        PlayerOption("&7[&a對話選項&7] &f還沒想到")
            .setResult { player ->
                player.sendMessage(*Utils.getMessage(npcIcon,
                    "${npcName}:",
                    "§7好的",
                ))
            }

    )

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(225,question1)