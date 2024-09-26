@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player

val npcIcon = IconFonts.getNPC(164);
val npcName = "§b● §e艾莉絲§r"
val daily11 = "daily_copper_11"
val side04 = "side_silver_04"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7哈比...",
))
    .addPlayerOptions(
        PlayerOption("&7[&a對話選項&7] &f(看了一眼就走)")
    )
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}
ScriptedQuests.getInstance().questManager.getQuest(daily11).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§a銅牌每日§7] §f留戀之情",false)
        .addConditions( {player->player.hasPermission("rank.lv1")} )
    )}
ScriptedQuests.getInstance().questManager.getQuest(side04).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§b銀牌支線§7] §f艾莉絲的復仇",false)
        .addConditions( {player->player.hasPermission("rank.lv2")} )
    )}
fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(164,question1)