@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player

val npcIcon = IconFonts.getNPC(17);
val npcName = "§b● §e可絲§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7傭兵你好",
    "§7你是來領取禮包的嗎?",
))
    .addPlayerOptions(
        PlayerOption("&7[&b禮包領取&7] &f我想領取禮包")
            //.addCondition{it.hasPermission("")}
            .setResult { player ->
                if(player.hasPermission("group.default"))
                    Bukkit.dispatchCommand(player,"kit")
                else{
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7你不能領取禮包",
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
                    player.sendMessage("$npcName: 你真醜")
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7不要煩我",
                        "§7我可是很忙的",
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


ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(17,question1)