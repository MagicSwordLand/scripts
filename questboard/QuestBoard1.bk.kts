@file:RequiredPlugins("ScriptedQuests","QuestAddon")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.questaddon.QuestAddon
import net.brian.questaddon.board.QuestBoard
import net.brian.questaddon.board.QuestSign
import net.brian.scriptedquests.api.conditions.HasPermission
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.block.BlockFace

val world = Bukkit.getWorld("2k")!!;


fun getBoard(): QuestBoard{
    val board = QuestBoard(
        "test", BlockFace.EAST,
        Location(world,-374.0,76.0,322.0),
        Location(world,-374.0,79.0,326.0),1,5)
    board.minQuests = 3;
    board.maxQuests = 5;
    board.addQuestSign(QuestSign("daily_hunt_01","§7*§a懸賞任務§7*","§f[煩人的小樹妖]","§7擊殺10個小樹妖","&c需銅牌傭兵")
        .addConditions(HasPermission("rank.lv1")))
    board.addQuestSign(QuestSign("daily_hunt_02","§7*§a懸賞任務§7*","§f[惡沼花]","§7採集16個惡沼花","&c需銅牌傭兵")
        .addConditions(HasPermission("rank.lv1")))
    board.addQuestSign(QuestSign("daily_hunt_03","§7*§a懸賞任務§7*","§f[白骨怪物]","§7擊殺10個白骨兵","&c需銅牌傭兵")
        .addConditions(HasPermission("rank.lv1")))
    board.addQuestSign(QuestSign("daily_hunt_04","§7*§a懸賞任務§7*","§f[將軍扇]","§7收集1個將軍的舞扇","&c需銅牌傭兵")
        .addConditions(HasPermission("rank.lv1")))
    //銀牌
    board.addQuestSign(QuestSign("daily_hunt_05","§7*§a懸賞任務§7*","§f[盜賊的首領]","§7討伐1次盜賊首領","&c需銀牌傭兵")
        .addConditions(HasPermission("rank.lv2")))
    board.addQuestSign(QuestSign("daily_hunt_06","§7*§a懸賞任務§7*","§f[收集靈木]","§7收集16個靈木","&c需銀牌傭兵")
        .addConditions(HasPermission("rank.lv2")))
    return board;
}

QuestAddon.getInstance().boardManager.register(getBoard())