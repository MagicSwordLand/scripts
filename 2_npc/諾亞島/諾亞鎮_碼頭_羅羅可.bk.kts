@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player

val npcIcon = IconFonts.getNPC(134);
val npcName = "§b● §e羅羅可§r"
val sidegoldis = "side_gold_is"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7要傳送到島嶼嗎？",
    "§7只有金牌傭兵才能擁有島嶼噢！",
))
    .addPlayerOptions(
        PlayerOption("&7[&b傳送島嶼&7] &f我想去島嶼")
            //.addCondition{it.hasPermission("")}command("shop open 巴哥的漁獲店 ${player.name}");
            .setResult { player -> command("server island ${player.name}")},
        PlayerOption("&7[&a對話選項&7] &f沒事了")
            //.addCondition{it.hasPermission("")}
            .setResult { player ->
                player.sendMessage("$npcName: 不是金牌傭兵也可以加入已創建的島嶼哦~")}
    )

ScriptedQuests.getInstance().questManager.getQuest(sidegoldis).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§b金牌支線§7] §c金牌傭兵的落腳處",false)
        .addConditions({player->  player.hasPermission("rank.lv3")} )
    )}
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}


ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(134,question1)