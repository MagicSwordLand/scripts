@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.conditions.CanDoQuestCondition
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import org.bukkit.entity.Player
import net.brian.scriptedquests.utils.Utils

val npcIcon = IconFonts.getNPC(5);
val npcName = "§b● §e焱格§r"

val other01 = "other_copper_01"

val rank01 = "other_rank_01"
val rank02 = "other_rank_02"
val week1 = "week_silver_01"

val question1 = NPCQuestion(*Utils.getMessage(npcIcon,
    "${npcName}:",
    "§7有什麼事情嗎?",
))
    .addPlayerOptions(
        PlayerOption("&7[&a對話選項&7] &f沒事")
            .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                "§b● §e焱格:",
                "§7那我先去忙了",
            ))},
        PlayerOption("§7[§d特殊委託§7] §f了解傭兵 &8(需成為銅牌傭兵)")
            .addConditions( {!it.hasPermission("rank.lv1")} )
            .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                "§b● §e焱格:",
                "§7哈哈哈，你連傭兵都還不是",
                "§7想更了解傭兵等你成為傭兵再說吧！",
            ))},
        PlayerOption("§7[§d特殊委託§7] §f晉升銀牌傭兵 &8(傭兵聲望需300、等級需求:10)")
            .addConditions(
                {it.hasPermission("rank.lv1")},
                {!it.hasPermission("rank.lv2")},
                {Utils.getDouble(it, "%variableholder_var_傭兵聲望%") < 300 || Utils.getDouble(it, "%class_level%") < 10 }
            )
            .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")},
        PlayerOption("§7[§d特殊委託§7] §f晉升金牌傭兵 &8(傭兵聲望需1200、等級需求:20")
            .addConditions( {it.hasPermission("rank.lv2")} )
            .addConditions( {!it.hasPermission("rank.lv3")} )
            .addConditions( { Utils.getDouble(it, "%variableholder_var_傭兵聲望%") < 1200 || Utils.getDouble(it,"%class_level%") < 20 })
            .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")},
        PlayerOption("§7[§d特殊委託§7] §f晉升白金傭兵 &8(傭兵聲望需待定、等級需求:未開放")
            .addConditions( {it.hasPermission("rank.lv3")} )
            .addConditions( {!it.hasPermission("rank.lv4")} )
            .addConditions({ Utils.getDouble(it, "%variableholder_var_傭兵聲望%") < 6000 || Utils.getDouble(it,"%class_level%") > 99 })
            .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")},
        PlayerOption("§7[§d特殊委託§7] §f晉升紫金傭兵 &8(傭兵聲望需18000、等級需求:未開放")
            .addConditions( {it.hasPermission("rank.lv4")} )
            .addConditions( {!it.hasPermission("rank.lv5")} )
            .addConditions ({ Utils.getDouble(it, "%variableholder_var_傭兵聲望%") < 18000 || Utils.getDouble(it,"%class_level%") > 99 })
            .setResult { player -> player.sendMessage("§7[§c提示§7] §f任務條件未滿足")})


ScriptedQuests.getInstance().questManager.getQuest(other01).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§d特殊委託§7] §f了解傭兵",false)
        .addConditions( {it.hasPermission("rank.lv1")} )
    )}
ScriptedQuests.getInstance().questManager.getQuest(rank01).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§d特殊委託§7] §f晉升銀牌傭兵",false)
        .addConditions(  {player-> player.hasPermission("rank.lv1")} )
        .addConditions( {player->!player.hasPermission("rank.lv2")} )
        .addConditions( { player->Utils.getDouble(player, "%variableholder_var_傭兵聲望%") >= 300 && Utils.getDouble(player, "%class_level%") >= 10 })
    )}
ScriptedQuests.getInstance().questManager.getQuest(rank02).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§d特殊委託§7] §f晉升金牌傭兵",false)
        .addConditions( {player-> player.hasPermission("rank.lv2")} )
        .addConditions( {player-> !player.hasPermission("rank.lv3")} )
        .addConditions ({ player-> Utils.getDouble(player, "%variableholder_var_傭兵聲望%") >= 1200 && Utils.getDouble(player, "%class_level%") >= 20})
    )}
ScriptedQuests.getInstance().questManager.getQuest(week1).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§e銀牌每周§7] §f熟練傭兵的任務",false)
        .addConditions( { player-> player.hasPermission("rank.lv2")} )
    )}
ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(5,question1)