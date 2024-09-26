@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import net.brian.scriptedquests.utils.Utils

val npcIcon = IconFonts.getNPC(29);
val npcName = "§b● §e海盜鴨鴨§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
        "${npcName}:",
        "§7需要溫泉神氣",
        "§7讓鴨鴨更加神氣！",        
))
        .addPlayerOptions(

                PlayerOption("&7[&b溫泉神氣&7] &f兌換溫泉神氣")
                        .setResult { player ->
                            if (isDay(player)||player.hasPermission("npc.ignoretime"))
                                command("dm open hotspringexe ${player.name}");
                            else {
                                player.sendMessage(*Utils.getMessage(npcIcon,
                                        "${npcName}:",
                                        "§7鴨鴨晚上不營業",
                                        "§7呱！",
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
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(215,question1)