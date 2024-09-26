@file:RequiredPlugins("AltSkill2-Totem","PlayerDataSync","HeroesSkills")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.heroesskills.HeroesSkills
import net.brian.heroesskills.core.gui.paths.PathElement
import net.brian.heroesskills.core.gui.paths.conditions.SkillLevelCondition
import net.brian.heroesskills.core.skills.MythicActiveSkill
import net.brian.heroesskills.core.skills.PassiveMultipleStatSkill
import net.brian.heroesskills.core.skills.PassiveStatSkill
import net.brian.heroesskills.core.utils.Icon
import org.bukkit.Material
import java.util.function.BiFunction

var activeSkill = MythicActiveSkill(
    "血債血還", //同時也是這個技能的id
    "&6血債血還",
    3, //最大等級
    15.0, //魔力消耗
    15.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.RED_DYE) // 材質
        .setModelData(0) // model
        .setDisplayName("&6血債血還 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f15 魔力")
                add("&7▪ 冷卻：&f15 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f野獸之王 Lv.5")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f將自身15%的血液轉換成一道野獸利爪")
                add("&f並且快速向前方揮斬造成五名敵人")
                add("&f攻擊傷害*2.5+體魄*2 傷害")
                add("&f擊中敵人時恢復造成傷害的10%血量")
                add("&c&n※所剩血量需大於20%才可發動")
                add("")
                add("&7Lv2 ➤ &7技能傷害改為 &f攻擊傷害*2.75+體魄*2.25")
                add("&7Lv2 ➤ &7擊中敵人時恢復造成傷害的15%血量")
                add("&7Lv3 ➤ &7技能傷害改為 &f攻擊傷害*3.25+體魄*2.5")
                add("&7Lv3 ➤ &7擊中敵人時恢復造成傷害的20%血量")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("wildbeast" to 36),
        {it.getSkillData("嗜血野獸").level >=5}
    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);