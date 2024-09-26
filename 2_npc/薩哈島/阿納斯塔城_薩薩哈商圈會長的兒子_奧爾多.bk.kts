@file:RequiredPlugins("ScriptedQuests")


import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.Bukkit
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player

val npcIcon = IconFonts.getNPC(244);
val npcName = "§b● §e奧爾多§r"
val side01 = "side_gold_08"
val side02 = "side_gold_09"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
        "${npcName}:",
        "§7寵物可是最好的夥伴喲",
        "§7你有戰鬥的寵物了嗎？",
))
        .addPlayerOptions(

                PlayerOption("&7[&a對話選項&7] &f還沒")
                        //.addCondition{it.hasPermission("")}
                        .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                                "${npcName}:",
                                "§7沒寵物陪伴可是很孤獨的呀...",
                                "§7可以看看我的小店或者去野外捉一隻喲！",
                        ))},
                PlayerOption("&7[&a對話選項&7] &f我有了")
                        //.addCondition{it.hasPermission("")}
                        .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                                "${npcName}:",
                                "§7你的寵物等級不夠呀！",
                                "§7快來我的小店購買一些寵物果實吧，為牠們增加經驗",
                        ))},
                // 任務
                PlayerOption("§7[§b金牌支線§7] §f有看到我的刀仔嗎？ &8(前置需求:黃金階級&完成任務-薩哈商圈的師爺)")
                        .addConditions( {!it.hasPermission("rank.lv1")} )
                        .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")},

                PlayerOption("§7[§b金牌支線§7] §f寵物治療劑的補給 &8(前置需求:黃金階級&完成任務-有看到我的刀仔嗎？)")
                        .addConditions( {!it.hasPermission("rank.lv1")} )
                        .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")}
        )

ScriptedQuests.getInstance().questManager.getQuest(side01).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§b金牌支線§7] §f有看到我的刀仔嗎？",false)
            .addConditions( {player->player.hasPermission("side_gold_08,rank.lv3")} ));

    ScriptedQuests.getInstance().questManager.getQuest(side02).ifPresent {
        question1.addPlayerOptions(it.getStartOption("§7[§b金牌支線§7] §f寵物治療劑的補給",false)
                .addConditions( {player->player.hasPermission("side_gold_09,rank.lv3")} )

        )}
    fun isDay(player: Player): Boolean{
        return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
    }

    fun command(commandString: String){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
    }
    ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(244,question1)}