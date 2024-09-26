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
        "test2", BlockFace.NORTH,
        Location(world,438.0,75.0,-89.0),
        Location(world,433.0,78.0,-89.0),12,12)

    //金牌
    board.addQuestSign(QuestSign("daily_saha_gold_01","§7*§a每日委託§7*","§f[穹頂內的亡靈]","§7擊殺30個沙淵亡魂","&e需金牌傭兵")
        .addConditions(HasPermission("rank.lv3")))
    board.addQuestSign(QuestSign("daily_saha_gold_02","§7*§a每日委託§7*","§f[灘地砂粉]","§7收集16個灘地砂粉","&e需金牌傭兵")
        .addConditions(HasPermission("rank.lv3")))
    board.addQuestSign(QuestSign("daily_saha_gold_03","§7*§a每日委託§7*","§f[阿努比斯傳說]","§7擊殺30隻阿努比斯怪物","&e需金牌傭兵")
        .addConditions(HasPermission("rank.lv3")))
    board.addQuestSign(QuestSign("daily_saha_gold_04","§7*§a每日委託§7*","§f[米諾牛角]","§7收集3個米諾牛角","&e需金牌傭兵")
        .addConditions(HasPermission("rank.lv3")))
    board.addQuestSign(QuestSign("daily_saha_gold_05","§7*§a每日委託§7*","§f[紫金蘿蔔]","§7收集16個紫金蘿蔔","&e需金牌傭兵")
        .addConditions(HasPermission("rank.lv3")))
    board.addQuestSign(QuestSign("daily_saha_gold_06","§7*§a每日委託§7*","§f[甘甜西瓜]","§7收集32個甘甜西瓜","&e需金牌傭兵")
        .addConditions(HasPermission("rank.lv3")))
    board.addQuestSign(QuestSign("daily_saha_gold_07","§7*§a每日委託§7*","§f[砂岩巨龜]","§7擊殺砂岩巨龜","&e需金牌傭兵")
        .addConditions(HasPermission("rank.lv3")))
    board.addQuestSign(QuestSign("daily_saha_gold_08","§7*§a每日委託§7*","§f[迷惑離石像]","§7擊殺迷惑離石像","&e需金牌傭兵")
        .addConditions(HasPermission("rank.lv3")))
    board.addQuestSign(QuestSign("daily_saha_gold_09","§7*§a每日委託§7*","§f[米諾陶洛斯]","§7擊殺米諾陶洛斯","&e需金牌傭兵")
        .addConditions(HasPermission("rank.lv3")))
    board.addQuestSign(QuestSign("daily_saha_gold_10","§7*§a每日委託§7*","§f[清理墓地]","§7擊殺30隻墓地迷域怪物","&e需金牌傭兵")
        .addConditions(HasPermission("rank.lv3")))
    //白金
    board.addQuestSign(QuestSign("daily_saha_platinum_01","§7*§a每日委託§7*","§f[烈日之陽]","§7擊殺2次拓笊","&x&c&7&e&c&f&f需白金傭兵")
        .addConditions(HasPermission("rank.lv4")))
    board.addQuestSign(QuestSign("daily_saha_platinum_02","§7*§a每日委託§7*","§f[雙隱之甲]","§7收集3個雙隱之甲","&x&c&7&e&c&f&f需白金傭兵")
        .addConditions(HasPermission("rank.lv4")))
    board.addQuestSign(QuestSign("daily_saha_platinum_03","§7*§a每日委託§7*","§f[喚魔之使]","§7擊殺20隻喚魔之使","&x&c&7&e&c&f&f需白金傭兵")
        .addConditions(HasPermission("rank.lv4")))
    board.addQuestSign(QuestSign("daily_saha_platinum_04","§7*§a每日委託§7*","§f[綠洲部落]","§7擊殺50隻沙原綠洲怪物","&x&c&7&e&c&f&f需白金傭兵")
        .addConditions(HasPermission("rank.lv4")))
    board.addQuestSign(QuestSign("daily_saha_platinum_05","§7*§a每日委託§7*","§f[暮光石頭]","§7收集16個暮光石頭","&x&c&7&e&c&f&f需白金傭兵")
        .addConditions(HasPermission("rank.lv4")))
    board.addQuestSign(QuestSign("daily_saha_platinum_06","§7*§a每日委託§7*","§f[原隱雙刃]","§7擊殺原隱雙刃","&x&c&7&e&c&f&f需白金傭兵")
        .addConditions(HasPermission("rank.lv4")))
    //
    return board;
}

QuestAddon.getInstance().boardManager.register(getBoard())