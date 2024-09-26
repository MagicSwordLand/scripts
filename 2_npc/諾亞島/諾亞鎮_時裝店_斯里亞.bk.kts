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

val npcIcon = IconFonts.getNPC(137);
val npcName = "§b● §e斯里亞§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7告訴你一個秘密...",
    "§7這裡的時裝可以讓你變酷！",
))
    .addPlayerOptions(
        PlayerOption("&7[&b購買時裝&7] &f我想購買時裝")
            //.addCondition{it.hasPermission("")}command("shop open 巴哥的漁獲店 ${player.name}");
            .setResult { player -> command("dm open fashionshop ${player.name}")},
        PlayerOption("&7[&a對話選項&7] &f我可以白嫖時裝嗎？")
            //.addCondition{it.hasPermission("")}
            .setResult { player ->
                    player.sendMessage("$npcName: 記得查看和參與DC內定期舉行的活動哦，有機會免費獲得~")}
    )
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}


ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(137,question1)