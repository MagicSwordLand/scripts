@file:RequiredPlugins("ScriptedQuests")


import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player

val npcIcon = IconFonts.getNPC(52);
val npcName = "§b● §e斐格§r"
val side01 = "side_gold_05"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
        "${npcName}:",
        "§7最近很多傭兵冒險家啊...",
        "§7看起來可真清閒",
))
        .addPlayerOptions(

                PlayerOption("&7[&a對話選項&7] &f你好呀！")
                        //.addCondition{it.hasPermission("")}
                        .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                                "${npcName}:",
                                "§7...?",
                                "§7如果沒什麼事的話",
                                "§7請不要阻擋我的視線！",
                        ))},
                // 任務
                PlayerOption("§7[§b金牌支線§7] §f出征前的準備 &8(階級需求:黃金)")
                        .addConditions( {!it.hasPermission("rank.lv3")} )
                        .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")}
        )

ScriptedQuests.getInstance().questManager.getQuest(side01).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§b金牌支線§7] §f出征前的準備",false)
            .addConditions( {player->player.hasPermission("rank.lv3")} )
    )}
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(249,question1)