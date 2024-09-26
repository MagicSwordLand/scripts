@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import net.brian.scriptedquests.utils.Utils

val npcIcon = IconFonts.getNPC(16);
val npcName = "§b● §e莉亞莫:"
val copper01 = "daily_copper_01"
val copper02 = "daily_copper_02"
val copper03 = "daily_copper_03"
val copper04 = "daily_copper_04"
val copper05 = "daily_copper_05"
val copper06 = "daily_copper_06"
val copper07 = "daily_copper_07"
val copper08 = "daily_copper_08"
val silver01 = "daily_silver_01"
val silver02 = "daily_silver_02"
val silver03 = "daily_silver_03"
val silver04 = "daily_silver_04"
val silver05 = "daily_silver_05"
val silver06 = "daily_silver_06"

val copperQuestion = NPCQuestion(*Utils.getMessage(npcIcon,
    npcName,
    "&7這位尊貴的傭兵?",
    "&7你想接受甚麼銅牌委託呢？",))
val silverQuestion = NPCQuestion(*Utils.getMessage(npcIcon,
    npcName,
    "&7這位尊貴的傭兵?",
    "&7你想接受甚麼銀牌委託呢？",))
val goldenQuestion = NPCQuestion(*Utils.getMessage(npcIcon,
    npcName,
    "&7這位尊貴的傭兵?",
    "&7你想接受甚麼金牌委託呢？",))

val question = NPCQuestion {
    if (it.hasPermission("rank.lv1")) {
        Utils.getMessage(
            npcIcon,
            npcName,
            "&7這位尊貴的傭兵?",
            "&7請選擇你想接受的委託階級"
        );
    } else Utils.getMessage(
        npcIcon,
        npcName,
        "&7您好！",
        "&7請問有什麼是我能幫忙的嗎?",
    );
}
    .addPlayerOptions(
        PlayerOption("&7[&a對話選擇&7] &f我想接委託")
            .addConditions( { !it.hasPermission("rank.lv1")} )
            .setNpcResponse(*Utils.getMessage(npcIcon,
                npcName,
                "您目前無法接受委託喔",
                "成為傭兵後才能接受委託",
            )),
        PlayerOption("&7[&a對話選擇&7] &f我想找個銅牌委託")
            .addConditions ({ it.hasPermission("rank.lv1") })
            .setNpcResponse(copperQuestion),
        PlayerOption("&7[&a對話選擇&7] &f我想找個銀牌委託")
            .addConditions({ it.hasPermission("rank.lv2") })
            .setNpcResponse(silverQuestion),
        PlayerOption("&7[&a對話選擇&7] &f我想找個金牌委託")
            .addConditions({ it.hasPermission("rank.lv3") })
            .setNpcResponse(goldenQuestion)
    )


// copper
ScriptedQuests.getInstance().questManager.getQuest(copper01).ifPresent {
    copperQuestion.addPlayerOptions(it.getStartOption("§7[§a銅牌每日§7] §f葡萄園的尤莉",false))
}

ScriptedQuests.getInstance().questManager.getQuest(copper02).ifPresent {
    copperQuestion.addPlayerOptions(it.getStartOption("§7[§a銅牌每日§7] §f清理洞穴",false))
}

ScriptedQuests.getInstance().questManager.getQuest(copper03).ifPresent {
    copperQuestion.addPlayerOptions(it.getStartOption("§7[§a銅牌每日§7] §f收割稻草",false))
}

ScriptedQuests.getInstance().questManager.getQuest(copper04).ifPresent {
    copperQuestion.addPlayerOptions(it.getStartOption("§7[§a銅牌每日§7] §f鍛造素材I",false))
}

ScriptedQuests.getInstance().questManager.getQuest(copper05).ifPresent {
    copperQuestion.addPlayerOptions(it.getStartOption("§7[§a銅牌每日§7] §f鍛造素材II",false))
}

ScriptedQuests.getInstance().questManager.getQuest(copper06).ifPresent {
    copperQuestion.addPlayerOptions(it.getStartOption("§7[§a銅牌每日§7] §f跑腿傭兵",false))
}
ScriptedQuests.getInstance().questManager.getQuest(copper07).ifPresent {
    copperQuestion.addPlayerOptions(it.getStartOption("§7[§a銅牌每日§7] §f討伐哥布林王",false))
}
ScriptedQuests.getInstance().questManager.getQuest(copper08).ifPresent {
    copperQuestion.addPlayerOptions(it.getStartOption("§7[§a銅牌每日§7] §f擊退盜賊",false))
}
ScriptedQuests.getInstance().questManager.getQuest(silver01).ifPresent {
    silverQuestion.addPlayerOptions(it.getStartOption("§7[§a銀牌每日§7] §f交辦事項",false))
}
ScriptedQuests.getInstance().questManager.getQuest(silver02).ifPresent {
    silverQuestion.addPlayerOptions(it.getStartOption("§7[§a銀牌每日§7] §f驅逐盜賊",false))
}
ScriptedQuests.getInstance().questManager.getQuest(silver03).ifPresent {
    silverQuestion.addPlayerOptions(it.getStartOption("§7[§a銀牌每日§7] §f屠鬼達人",false))
}
ScriptedQuests.getInstance().questManager.getQuest(silver04).ifPresent {
    silverQuestion.addPlayerOptions(it.getStartOption("§7[§a銀牌每日§7] §f森林守衛者",false))
}
ScriptedQuests.getInstance().questManager.getQuest(silver05).ifPresent {
    silverQuestion.addPlayerOptions(it.getStartOption("§7[§a銀牌每日§7] §f鱗鑌礦工",false))
}
ScriptedQuests.getInstance().questManager.getQuest(silver06).ifPresent {
    silverQuestion.addPlayerOptions(it.getStartOption("§7[§a銀牌每日§7] §f交付蒼山金",false))
}

//
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(16,question);