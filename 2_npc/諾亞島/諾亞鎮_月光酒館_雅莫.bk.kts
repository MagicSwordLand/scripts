@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player

val npcIcon = IconFonts.getNPC(18);
val npcName = "§b● §e雅莫§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7傭兵你好",
    "§7你是來兌換賽季徽章的嗎?",
))
    .addPlayerOptions(
        PlayerOption("&7[&b賽季徽章&7] &f兌換使用賽季徽章")
            //.addCondition{it.hasPermission("")}
            .setResult { player ->
                if(player.hasPermission("group.default"))
                    Bukkit.dispatchCommand(player,"mali")
                else{
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7你還不能兌換賽季徽章喲~",
                        "§7(請先discord帳號)",
                    ));
                }
            },
        PlayerOption("&7[&b在線獎勵&7] &f查詢在線獎勵")
            .setResult { player ->
                if(player.hasPermission("group.default"))
                    Bukkit.dispatchCommand(player,"cmi prewards")
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7你不能領取在線獎勵",
                        "§7(請先discord帳號)",
                    ))
                }
            },
        PlayerOption("&7[&a對話選項&7] &f你真漂亮")
            //.addCondition{it.hasPermission("")}
            .setResult { player ->
                if (isDay(player))
                    player.sendMessage("$npcName: 這樣我會害羞的⁄(⁄ ⁄•⁄ω⁄•⁄ ⁄)⁄！")
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7謝謝你",
                        "§7^_^",
                    ))
                }
            }
    )
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}


ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(18,question1)