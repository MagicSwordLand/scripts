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
    "聖靈圖騰聖域", //同時也是這個技能的id
    "&6聖靈圖騰•聖域",
    3, //最大等級
    30.0, //魔力消耗
    0.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.NETHER_STAR) // 材質
        .setModelData(0) // model
        .setDisplayName("&6聖靈圖騰•聖域 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f30 魔力")
                add("&7▪ 冷卻：&f16 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f圖騰守護神 Lv.5、聖靈圖騰•治癒 Lv.1")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f放置聖靈圖騰，圖騰將存在5秒")
                add("&f使15格內自身及隊友獲得以下效果")
                add("&f◆ 每秒恢復 最大魔力*0.05 魔力")
                add("&f◆ 每秒恢復 智慧*0.3 血量")
                add("&f")
                add("&f造成15格內敵人以下效果")
                add("&f◆ 每秒 攻擊魔傷*1.2+智慧*0.8 傷害")
                add("&f◆ 移動緩速")
                add("")
                add("&7Lv2 ➤ &7技能傷害改為 &f攻擊魔傷*1.5+智慧*1.2")
                add("&7Lv3 ➤ &7技能傷害改為 &f攻擊魔傷*2+智慧*1.5")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("totem" to 29),
        {it.getSkillData("圖騰守護神").level >=5&&it.getSkillData("聖靈圖騰治癒").level >=1}

    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);