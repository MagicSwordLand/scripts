@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player

val npcIcon = IconFonts.getNPC(148);
val npcName = "§b● §e維洛妮亞§r"
val copper01 = "side_activity_01"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7你好，我是維洛妮亞",
    "§7這些彩色水晶就是寶箱魔力的來源喔~",
))
    .addPlayerOptions(
        PlayerOption("&7[&b購買禮包&7] &f我想購買禮包")
            //.addCondition{it.hasPermission("")}command("shop open 巴哥的漁獲店 ${player.name}");
            .setResult { player -> command("shop open 贊助_禮包 ${player.name}")},
        PlayerOption("&7[&b購買鑰匙&7] &f我想購買鑰匙")
            //.addCondition{it.hasPermission("")}command("shop open 贊助_禮包 ${player.name}");
            .setResult { player -> command("shop open 贊助_道具 ${player.name}")},
        PlayerOption("&7[&b碎片兌換&7] &f我這邊有些碎片想換東西")
            //.addCondition{it.hasPermission("")}
            .setResult { player -> command("crafting station ${player.name} 寶箱碎片兌換")},
        PlayerOption("&7[&b查詢鑰匙&7] &f我想查詢我的鑰匙庫存")
            //.addCondition{it.hasPermission("")}
            .setResult { player ->
                if(player.hasPermission("group.default"))
                    Bukkit.dispatchCommand(player,"crates key show")
                else{
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7你不能領取禮包",
                        "§7(請先discord帳號)",
                    ));
                }
            },
        PlayerOption("&7[&a對話選項&7] &f你們是？")
            //.addCondition{it.hasPermission("")}
            .setResult { player ->
                if (isDay(player))
                    player.sendMessage("$npcName: 我和姐姐是守護寶箱的精靈一族~")
                else {
                    player.sendMessage(*Utils.getMessage(npcIcon,
                        "${npcName}:",
                        "§7夜深了",
                        "§7記得早點睡喲~",
                    ))
                }
            }
    )
ScriptedQuests.getInstance().questManager.getQuest(copper01).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§d活動支線§7] §f艾靈長廊-幻境",false))
}
fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}


ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(148,question1)