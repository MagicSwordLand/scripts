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

val npcIcon = IconFonts.getNPC(184);
val npcName = "§b● §e米歐§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7想去哪勒！",
))
    .addPlayerOptions(

        PlayerOption("&7[&b傳送選項&7] &f傳送到阿納斯塔城")
            .addConditions ( {it.hasPermission("main03.done")} )
            .setResult { player ->
                    command("execute in minecraft:2k run tp  ${player.name} 471.5 77.06 -22.5 -270 0")
                    command("sound entity_enderman_teleport ${player.name} -v:1 -p:0.55")
                },
        PlayerOption("&7[&b傳送選項&7] &f傳送到阿納斯塔城&7(需完成第三章-部落英雄)")
            .addConditions ( {!it.hasPermission("main03.done")} )
            .setResult { player ->
                player.sendMessage(*Utils.getMessage(npcIcon,
                    "${npcName}:",
                    "§7你還沒有資格去阿納斯塔城喔",
                ))
            },

        PlayerOption("&7[&a對話選項&7] &f還沒想到")
            //.addCondition{it.hasPermission("")}
            .setResult { player ->
                player.sendMessage(*Utils.getMessage(npcIcon,
                    "${npcName}:",
                    "§7想去別的地方就來找我",
                ))
            }
        // 任務

    )

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(258,question1)