@file:RequiredPlugins("ScriptedQuests")


import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player

val npcIcon = IconFonts.getNPC(158);
val npcName = "§b● §e魯柏格§r"
val side01 = "side_gold_03"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7如果月亮一如既往地朦朧",
    "§7那依舊說不定是件好事...",
    "§7有什麼可以幫到你的嗎？",        
))
    .addPlayerOptions(

        PlayerOption("&7[&a對話選項&7] &f沒事了！")
            //.addCondition{it.hasPermission("")}
            .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                "${npcName}:",
                "§7我這裡的部落裝備可是上等的喲",
                "§7要再記得回來看看噢",                    
                "§7抱歉剛剛走神了...",
            ))},
        // 任務
        PlayerOption("§7[§b金牌支線§7] §f月光下朦朧的心 &8(階級需求:黃金)")
            .addConditions( {!it.hasPermission("rank.lv3")} )
            .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")}
    )

ScriptedQuests.getInstance().questManager.getQuest(side01).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§b金牌支線§7] §f月光下朦朧的心",false)
        .addConditions( {player->player.hasPermission("rank.lv3")} )
    )}
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() > 16000 || player.getWorld().getTime() < 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(192,question1)