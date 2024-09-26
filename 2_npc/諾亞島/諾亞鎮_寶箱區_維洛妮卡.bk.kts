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

val npcIcon = IconFonts.getNPC(147);
val npcName = "§b● §e維洛妮卡§r"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7想看自己鑰匙庫存也可以去找我的妹妹",
    "§7我能將彩晶幻化成鑰匙的模樣！",
))
    .addPlayerOptions(
        PlayerOption("&7[&b購買鑰匙&7] &f我想購買鑰匙")
            //.addCondition{it.hasPermission("")}command("shop open 巴哥的漁獲店 ${player.name}");
            .setResult { player -> command("shop open 贊助_道具 ${player.name}")},
        PlayerOption("&7[&b查看鑰匙&7] &f我想知道我目前有多少鑰匙")
            //.addCondition{it.hasPermission("")}
            .setResult { player -> Bukkit.dispatchCommand(player,"excellentcrates key show")},
        PlayerOption("&7[&b碎片兌換&7] &f我這邊有些碎片想換東西")
            //.addCondition{it.hasPermission("")}
            .setResult { player -> command("crafting station ${player.name} 寶箱碎片兌換")},
        PlayerOption("&7[&a對話選項&7] &f這些寶箱是？")
            //.addCondition{it.hasPermission("")}
            .setResult { player ->
                player.sendMessage("$npcName: 我和妹妹所守護的寶箱，是古老的守護族們所留下的~")}
    )
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}


ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(147,question1)