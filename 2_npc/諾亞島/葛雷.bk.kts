@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.conditions.CanDoQuestCondition
import net.brian.scriptedquests.api.conditions.FinishedQuestsCondition
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.entity.Player
import net.brian.scriptedquests.utils.Utils

val npcIcon = IconFonts.getNPC(64);
val greyNPC = "§c● &7[傭兵會長] &f葛雷:&x&8&B&6&7&3&A";


fun startCh1(player: Player){
    ScriptedQuests.getInstance().questManager.getQuest("main_ch1_01")
        .ifPresent { it.startQuest(player) }
}

val startCh1Q = NPCQuestion {
    if(CanDoQuestCondition("main_ch1_01",false).test(it)){
        Utils.getMessage(npcIcon,
            "${greyNPC}:",
            "§7小子，你下船啦",
            "§7第一次坐船怎麼樣",
            "§7好玩嗎?",
        )
    }
    else arrayOf("你好呀")
}
    .addPlayerOptions(
        PlayerOption("&7[&a對話選項&7] &f還不錯..就是暈呼呼的…")
            .addConditions(CanDoQuestCondition("main_ch1_01",false))
            .setNpcResponse(NPCQuestion(*Utils.getMessage(npcIcon,
                "${greyNPC}:",
                "§7看來你小心身體素質不好啊",
                "§7坐個船而已",
            ))
                .addPlayerOptions(
                    PlayerOption("&7[&a對話選項&7] &f還不是因為這是第一次嘛")
                        .setResult { player -> startCh1(player) })),
        PlayerOption("&7[&a對話選項&7] &f超好玩~")
            .addConditions(CanDoQuestCondition("main_ch1_01",false))
            .setNpcResponse(*Utils.getMessage(npcIcon,
                "${greyNPC}:",
                "§7那太棒了",
                "§7之後的冒險可是更刺激呢",
            ))
            .setResult { player -> startCh1(player) })


ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(64,startCh1Q);