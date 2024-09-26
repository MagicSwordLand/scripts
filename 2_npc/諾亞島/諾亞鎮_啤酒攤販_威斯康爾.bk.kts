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

val npcIcon = IconFonts.getNPC(104);
val npcName = "§b● §e威斯康爾§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7成人的快樂",
    "§7這一杯上頭！",
))
    .addPlayerOptions(
        PlayerOption("&7[&b購買酒品&7] &f我想買點酒")
            //.addCondition{it.hasPermission("")}command("shop open 巴哥的漁獲店 ${player.name}");
            .setResult { player -> command("shop open 威斯康爾啤酒 ${player.name}")},
        PlayerOption("&7[&b製作酒品&7] &f我想委託酒")
            //.addCondition{it.hasPermission("")}command("shop open 巴哥的漁獲店 ${player.name}");
            .setResult { player -> command("crafting station ${player.name} 威斯康爾啤酒")},
        PlayerOption("&7[&a對話選項&7] &f有梳打氣泡酒嗎？")
            //.addCondition{it.hasPermission("")}
            .setResult { player ->
                    player.sendMessage("$npcName: 可以不要侮辱酒嗎？")}
    )
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}


ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(121,question1)